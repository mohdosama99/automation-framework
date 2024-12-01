package webTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import commonUtilities.CommonFunction;
import webPages.CartPage;
import webPages.CheckoutPage;
import webPages.JuiceShopPage;
import webPages.LoginPage;
import webPages.ProductPage;

public class Dummy extends BaseClass{
	
	
	@Test
	public void checkout() {
		JuiceShopPage JSP = new JuiceShopPage(driver);
		CommonFunction CF = new CommonFunction(driver);
		LoginPage LP = new LoginPage(driver);
		ProductPage PP = new ProductPage(driver);
		CartPage CP = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		JSP.DismissAlerts();
		CF.waitForSeconds(5);
		LP.NavigateToLoginPage();
		LP.enterEmail("test@gmail.com");
		LP.enterPassword("Test@123");
		LP.clickLogin();
		CF.waitForSeconds(5);
		driver.get("https://juice-shop.herokuapp.com/#/payment/shop");
		checkoutPage.addCreditCardInformation("testAuto","4111111111111111", "12", "2081");
        checkoutPage.clickCompletePurchase();
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Purchase was not successful.");
	}

}
