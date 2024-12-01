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

public class CompleteFlow extends BaseClass {
	
	@Test
	public void CompleteFlowTest() {
		
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
		PP.addProductToCart(5);
        Assert.assertEquals(PP.getCartItemCount(), 5, "Cart item count is not correct.");
        PP.clickOnCartIcon();
        Assert.assertTrue(CP.isCartPageDisplayed(), "Cart page is not displayed.");
        double oldTotalPrice = CP.getTotalPrice();
        CP.increaseProductQuantity();
        Assert.assertTrue(CP.isTotalPriceChanged(oldTotalPrice), "Total price did not change after increasing product quantity.");
        oldTotalPrice = CP.getTotalPrice();
        CP.deleteProduct();
        Assert.assertTrue(CP.isTotalPriceChanged(oldTotalPrice), "Total price did not change after deleting a product.");
        CP.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page is not displayed.");
        checkoutPage.ClickOnAddAddress();
        checkoutPage.enterAddressInformation();
        checkoutPage.selectDeliveryMethod();
        Assert.assertTrue(checkoutPage.isPaymentPageDisplayed(), "Payment page is not displayed.");
        Assert.assertTrue(checkoutPage.isWalletEmpty(), "Wallet is not empty.");
        checkoutPage.addCreditCardInformation("testAuto","4111111111111111", "12", "2081");
        checkoutPage.clickCompletePurchase();
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "Purchase was not successful.");
    }
		
}