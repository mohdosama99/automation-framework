package webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonFunction;

public class LoginPage {

	private WebDriver driver;
	CommonFunction CF = new CommonFunction(driver);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void NavigateToLoginPage() {
		CF.waitForElementToBeClickable(AccountButton);
		AccountButton.click();
		//CF.waitForElementToBeClickable(LoginButton);
		LoginButton.click();
		CF.waitForSeconds(2);
	}

	public void enterEmail(String emailValue) {
		CF.waitForSeconds(2);
		//CF.waitForElementToVisible("//*[@name='email']");
		emailField.sendKeys(emailValue);
	}

	public void enterPassword(String passwordValue) {
		CF.waitForSeconds(2);
		//CF.waitForElementToVisible("//*[@name='password']");
		passwordField.sendKeys(passwordValue);
	}

	public void clickLogin() {
		CF.waitForElementToBeClickable(loginButton);
		loginButton.click();
	}

	public boolean isLoginSuccessful() {
		CF.waitForSeconds(2);
		return AddToBasket.isDisplayed();
	}

	@FindBy(id = "navbarAccount")
	private WebElement AccountButton;

	@FindBy(xpath = "(//span[contains(text(),'Login')])[2]")
	private WebElement LoginButton;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	@FindBy(xpath = "//*[contains(text(),'Add to Basket')]")
	private WebElement AddToBasket;

}
