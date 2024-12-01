package appTests;

import org.testng.annotations.Test;

import appPages.JuiceShop;
import base.BaseClass;
import commonUtilities.CommonFunction;

public class TestAppFlow extends BaseClass {

	@Test
	public void TestAppFlow() {
		JuiceShop js = new JuiceShop(appiumDriver);
		CommonFunction cf = new CommonFunction(appiumDriver);

		js.DismissAlerts();
		js.scrollToBottom();
		js.CheckNavigationOnThreeIcon();
		js.scrollToTop();

	}

}
