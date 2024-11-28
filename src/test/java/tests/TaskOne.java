package tests;

import org.testng.annotations.Test;


import base.BaseClass;
import webPages.TaskOnePage;

public class TaskOne extends BaseClass {

	@Test
	public void TaskOne() {
		TaskOnePage hp = new TaskOnePage(driver);

	}

}
