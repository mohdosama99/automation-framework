package appTests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import appPages.JuiceShop;
import base.BaseClass;
import commonUtilities.CommonFunction;

public class ChangeSetting extends BaseClass {
	
	
	@Test
	public void ChangeSettingFlow() {
		JuiceShop js = new JuiceShop(appiumDriver);
		CommonFunction cf = new CommonFunction(appiumDriver);
		
		js.DismissAlerts();
		cf.waitForSeconds(2);
		js.NavigateToSettingPage();
		js.disableAllOptions();
		js.NavigateBack();
		Assert.assertTrue(js.verifyHomePage(),"User not landed on HomePage");
		
		
	}

}
