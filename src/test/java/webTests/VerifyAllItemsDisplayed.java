package webTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import commonUtilities.CommonFunction;
import webPages.JuiceShopPage;

public class VerifyAllItemsDisplayed extends BaseClass {

	@Test
	public void VerifyAllItemsDisplayedTest() {
		JuiceShopPage JSP = new JuiceShopPage(driver);
		CommonFunction CF = new CommonFunction(driver);

		JSP.DismissAlerts();
		CF.waitForSeconds(5);
		CF.scrollToBottom();
		JSP.clickItemsPerPageDropdown();
		JSP.selectMaxItemsOption();
		CF.scrollToBottom();
		CF.waitForSeconds(5);
		Assert.assertEquals(JSP.getAllItems(), JSP.getProductCount(), "No items are displayed.");
	}

}
