package base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commonUtilities.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class DriverFactory {
	private static WebDriver driver;
	private static AppiumDriver appiumDriver;

	// Get WebDriver based on browser
	public static WebDriver getWebDriver() {
		String platform = ConfigReader.getPlatform();
		String env = ConfigReader.getEnv();
		String browser = ConfigReader.getBrowser();

		if (platform == null || platform.isEmpty()) {
			throw new IllegalArgumentException("Platform not specified. Please set the 'platform' variable.");
		}

		if (browser == null || browser.isEmpty()) {
			throw new IllegalArgumentException("Browser not specified. Please set the 'browser' variable.");
		}
		if (platform.equalsIgnoreCase("web")) {
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--incognito");
				driver = new ChromeDriver(option);
			} else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions option = new FirefoxOptions();
				option.addArguments("--incognito");
				driver = new FirefoxDriver(option);
			} else {
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		}
		driver.manage().window().maximize();
		return driver;
	}

	// Get AppiumDriver based on platform and device
	public static AppiumDriver getAppiumDriver() throws MalformedURLException {
		String platform = ConfigReader.getPlatform(); // Get platform (android/ios) from ConfigReader
		String appPath = ConfigReader.getApkPath();

		if (platform == null || platform.isEmpty()) {
			throw new IllegalArgumentException("Platform not specified. Please set the 'platform' variable.");
		}

		if (appPath == null || appPath.isEmpty()) {
			throw new IllegalArgumentException("App path not specified. Please set the 'apkPath' variable.");
		}

		if (platform.equalsIgnoreCase("android")) {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setPlatformName("Android");
			options.setDeviceName("emulator-5554"); // You can dynamically pass the device name if needed
			options.setAutomationName("UiAutomator2");
			options.setApp(appPath);
			appiumDriver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		} else if (platform.equalsIgnoreCase("ios")) {
			XCUITestOptions options = new XCUITestOptions();
			options.setPlatformName("iOS");
			options.setAutomationName("XCUITest");
			options.setDeviceName("iPhone 13");
			options.setPlatformVersion("15.4");
			options.setUdid("YOUR_DEVICE_UDID");
			options.setApp(appPath);
			appiumDriver = new IOSDriver(new URL("http://0.0.0.0:4723"), options);
		} else {
			throw new IllegalArgumentException("Unsupported platform: " + platform);
		}
		return appiumDriver;
	}
}
