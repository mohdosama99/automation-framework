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

		// Step 5: Click on "Show Password Advice"
		RP.clickShowPasswordAdvice();

		// Step 6: Click on "Register"
		RP.clickRegister();

		// Step 7: Assert the registration success message
		Assert.assertTrue(RP.isRegistrationSuccessful(), "Registration failed!");

		// Step 8: Navigate to login page
		LoginPage loginPage = RP.navigateToLoginPage();

		// Step 9: Log in using the self-generated information
		loginPage.enterEmail(selfGeneratedEmail);
		loginPage.enterPassword(selfGeneratedPassword);
		loginPage.clickLogin();

//		// Step 10: Assert successful login
//		Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");

	}

}
