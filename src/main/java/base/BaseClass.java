package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import commonUtilities.ConfigReader;
import io.appium.java_client.AppiumDriver;

public class BaseClass {
	protected WebDriver driver;
	protected AppiumDriver appiumDriver;
	protected RemoteWebDriver remoteDriver;
	protected static final Logger logger = LogManager.getLogger(BaseClass.class);

	@BeforeClass
	public void setup() throws Exception {
		driver = WebDriverManager.getInstance().getDriver();

		String platform = ConfigReader.getPlatform();
		String browser = ConfigReader.getBrowser();
		logger.debug("Starting test setup...");
		logger.debug("Platform: " + platform + ", Browser: " + browser);

		if (platform.equalsIgnoreCase("web")) {
			logger.debug("Initializing WebDriver for web testing...");
			remoteDriver = (RemoteWebDriver) driver;
		} else if (platform.equalsIgnoreCase("android") || platform.equalsIgnoreCase("ios")) {
			logger.debug("Initializing AppiumDriver for mobile testing...");
			appiumDriver = (AppiumDriver) driver;
		} else {
			logger.error("Error during driver setup: ");
			throw new IllegalArgumentException("Unsupported platform: " + platform);
		}
	}

	@AfterClass
	public void teardown() {
		logger.info("Tearing down the driver...");
		WebDriverManager.quitBrowser();
		logger.info("Driver quit successfully.");
	}
}
