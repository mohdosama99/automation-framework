package appTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import appPages.JuiceShop;
import base.BaseClass;
import commonUtilities.CommonFunction;

public class SearchFlow extends BaseClass {

	@Test
	public void SearchFlow() {
		JuiceShop js = new JuiceShop(appiumDriver);
		CommonFunction cf = new CommonFunction(appiumDriver);

		js.DismissAlerts();
		cf.waitForSeconds(2);
		js.verifySearchFunctionality("New York");
		Assert.assertTrue(js.isSearchBarExpandedwithReturnedResults(),"Search Keyword is not asserted with search bar in return result.");
		js.NavigateBackToHomePage();
	}

}
