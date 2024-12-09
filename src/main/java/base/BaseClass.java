package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commonUtilities.ConfigReader;
import io.appium.java_client.AppiumDriver;

public class BaseClass {
	protected WebDriver driver;
	protected AppiumDriver appiumDriver; // For mobile platforms
	protected RemoteWebDriver remoteDriver; // For web platforms

	@BeforeClass
	public void setup() throws Exception {
		driver = WebDriverManager.getInstance().getDriver();

		// Identify and cast the driver based on the platform
		String platform = ConfigReader.getPlatform();
		if (platform.equalsIgnoreCase("web")) {
			remoteDriver = (RemoteWebDriver) driver;
		} else if (platform.equalsIgnoreCase("android") || platform.equalsIgnoreCase("ios")) {
			appiumDriver = (AppiumDriver) driver;
		} else {
			throw new IllegalArgumentException("Unsupported platform: " + platform);
		}

	}

	@AfterClass
	public void teardown() {
		WebDriverManager.quitBrowser();
	}
}
