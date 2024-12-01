package webTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import commonUtilities.CommonFunction;
import webPages.JuiceShopPage;

public class VerifyProductPopupAndReview extends BaseClass {

	@Test()
	public void VerifyProductPopupAndReviewTest() {
		JuiceShopPage JSP = new JuiceShopPage(driver);
		CommonFunction CF = new CommonFunction(driver);

		JSP.DismissAlerts();
		JSP.clickFirstProduct();
		Assert.assertTrue(JSP.isPopupDisplayed(), "The product popup did not appear!");
		Assert.assertTrue(JSP.isProductImageDisplayed(), "The product image is not visible in the popup!");
		if (JSP.isReviewSectionAvailable()) {
			JSP.expandReviewSection();
			CF.waitForSeconds(2);
		}
		JSP.closeProductForm();
	}

}