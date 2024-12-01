package webTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import commonUtilities.CommonFunction;
import webPages.JuiceShopPage;
import webPages.LoginPage;
import webPages.RegistrationPage;

public class RegistrationFlow extends BaseClass {

	@Test
	public void RegistrationFlowTest() {
		JuiceShopPage JSP = new JuiceShopPage(driver);
		CommonFunction CF = new CommonFunction(driver);
		RegistrationPage RP = new RegistrationPage(driver);
		LoginPage LP = new LoginPage(driver);

		JSP.DismissAlerts();
		CF.waitForSeconds(5);
		RP.NavigateToRegistrationPage();
		RP.triggerValidationMessages();
		RP.CheckErrorMessage();
		RP.fillSecurityDetails();
		String selfGeneratedEmail = "testuser" + System.currentTimeMillis() + "@example.com";
		String selfGeneratedPassword = "TestPassword123";
		RP.enterEmail(selfGeneratedEmail);
		RP.enterPassword(selfGeneratedPassword);
		RP.enterConfirmPassword(selfGeneratedPassword);
		RP.clickShowPasswordAdvice();
		RP.clickRegister();
		Assert.assertTrue(RP.isRegistrationSuccessful(), "Registration failed!");
		LP.enterEmail(selfGeneratedEmail);
		LP.enterPassword(selfGeneratedPassword);
		LP.clickLogin();
		Assert.assertTrue(LP.isLoginSuccessful(), "Login failed!");

	}

}
