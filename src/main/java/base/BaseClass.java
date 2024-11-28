package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import commonUtilities.ConfigReader;
import io.appium.java_client.AppiumDriver;

public class BaseClass {
	protected WebDriver driver;
	protected AppiumDriver appiumDriver;

	@BeforeMethod
	public void setup() throws Exception {
		// Fetch environment and platform
		String env = ConfigReader.getEnv();
		String platform = ConfigReader.getPlatform();

		if (platform == null || platform.isEmpty()) {
			throw new IllegalArgumentException("Platform not specified. Please set the 'platform' variable.");
		}

		// Initialize WebDriver for web or AppiumDriver for mobile.
		if (platform.equalsIgnoreCase("web")) {
			driver = DriverFactory.getWebDriver(); // Initialize WebDriver for web automation
			driver.get(ConfigReader.getUrl()); // Navigate to the URL
		} else if (platform.equalsIgnoreCase("android") || platform.equalsIgnoreCase("ios")) {
			appiumDriver = DriverFactory.getAppiumDriver(); // Initialize AppiumDriver for mobile automation
		} else {
			throw new IllegalArgumentException("Invalid platform: " + platform
					+ ". Please set the 'platform' variable to 'web', 'android', or 'ios'.");
		}
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
		if (appiumDriver != null) {
			appiumDriver.quit();
		}
	}
}
