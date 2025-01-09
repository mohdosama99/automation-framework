package base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commonUtilities.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class WebDriverManager {
	private static volatile WebDriverManager instance;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static String platform = ConfigReader.getPlatform();
	private static String browser = ConfigReader.getBrowser();
	private static String appPath = ConfigReader.getApkPath();

	private WebDriverManager() {
	}

	public void initDriver() throws MalformedURLException {
		if (platform == null || platform.isEmpty()) {
			throw new IllegalArgumentException("Platform not specified. Please set the 'platform' variable.");
		}

		if (platform.equalsIgnoreCase("web")) {
			if (browser == null || browser.isEmpty()) {
				throw new IllegalArgumentException("Browser not specified. Please set the 'browser' variable.");
			}
			switch (browser) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--incognito");
				tlDriver.set(new ChromeDriver(chromeOptions));
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--incognito");
				tlDriver.set(new FirefoxDriver(firefoxOptions));
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--incognito");
				tlDriver.set(new EdgeDriver(edgeOptions));
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
			tlDriver.get().manage().window().maximize();
			tlDriver.get().get(ConfigReader.getUrl());
		} else if (platform.equalsIgnoreCase("android")) {
			if (appPath == null || appPath.isEmpty()) {
				throw new IllegalArgumentException("App path not specified. Please set the 'apkPath' variable.");
			}
			UiAutomator2Options options = new UiAutomator2Options();
			options.setPlatformName("Android");
			options.setDeviceName("emulator-5554"); // You can dynamically pass the device name if needed
			options.setAutomationName("UiAutomator2");
			options.setApp(appPath);
			options.setAutoGrantPermissions(true);
			tlDriver.set(new AndroidDriver(new URL("http://0.0.0.0:4723"), options));
		} else if (platform.equalsIgnoreCase("ios")) {
			if (appPath == null || appPath.isEmpty()) {
				throw new IllegalArgumentException("App path not specified. Please set the 'apkPath' variable.");
			}
			XCUITestOptions options = new XCUITestOptions();
			options.setPlatformName("iOS");
			options.setAutomationName("XCUITest");
			options.setDeviceName("iPhone 13");
			options.setPlatformVersion("15.4");
			options.setUdid("YOUR_DEVICE_UDID");
			options.setApp(appPath);
			tlDriver.set(new IOSDriver(new URL("http://0.0.0.0:4723"), options));
		}

	}

	public static WebDriverManager getInstance() throws MalformedURLException {
		if (instance == null) {
			synchronized (WebDriverManager.class) {
				if (instance == null) {
					instance = new WebDriverManager();
				}
			}
		}
		if (tlDriver.get() == null) {
			instance.initDriver();
		}
		return instance;
	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void quitBrowser() {
		if (tlDriver != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
}
