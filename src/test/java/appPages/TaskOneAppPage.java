package appPages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class TaskOneAppPage {
	private AppiumDriver appiumDriver;

	public TaskOneAppPage(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(appiumDriver, this);
	}

}
