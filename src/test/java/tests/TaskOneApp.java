package tests;

import org.testng.annotations.Test;

import appPages.TaskOneAppPage;
import base.BaseClass;

public class TaskOneApp extends BaseClass {
	
	@Test
	public void TaskOneApp() {
		TaskOneAppPage hp = new TaskOneAppPage(appiumDriver);

	}

}
