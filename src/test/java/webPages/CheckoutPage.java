package webPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.CommonFunction;

public class CheckoutPage {
	private WebDriver driver;
	CommonFunction CF = new CommonFunction(driver);
	static Actions action;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isCheckoutPageDisplayed() {
		return checkout.getText().trim().toLowerCase().contains("select an address");
	}

	public void ClickOnAddAddress() {
		CF.waitForSeconds(2);
		CF.waitForElementToBeClickable(AddNewAddress);
		AddNewAddress.click();
	}

	public void enterAddressInformation() {
		CF.waitForElementToBeClickable(AddNewAddressHeader);
		Country.sendKeys("Saudi Arabia");
		CF.waitForSeconds(2);
		Name.sendKeys("TestAut");
		CF.waitForSeconds(2);
		MobileNumber.sendKeys("9876543210");
		CF.waitForSeconds(2);
		Zipcode.sendKeys("12345");
		CF.waitForSeconds(2);
		Address.sendKeys("123 Test Street");
		CF.waitForSeconds(2);
		City.sendKeys("Riyadh");
		CF.waitForSeconds(2);
		State.sendKeys("Riyadh");
		CF.waitForSeconds(2);
		SubmitButton.click();
		CF.waitForSeconds(2);
		CF.waitForElementToBeClickable(SelectAddress);
		SelectAddress.click();
		CF.waitForSeconds(2);
		CF.waitForElementToBeClickable(ContinueButton);
		ContinueButton.click();

	}

	public void selectDeliveryMethod() {
		CF.waitForSeconds(2);
		CF.waitForElementToBeClickable(StandardDeliveryOption);
		StandardDeliveryOption.click();
		CF.waitForSeconds(2);
		CF.waitForElementToBeClickable(ContinueButton);
		ContinueButton.click();
	}

	public boolean isPaymentPageDisplayed() {
		CF.waitForSeconds(2);
		return payment.getText().trim().toLowerCase().contains("my payment options");
	}

	public boolean isWalletEmpty() {
		try {
			if (Wallet.isDisplayed() && Wallet.isEnabled()) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(Wallet));
				return false;
			}
		} catch (Exception e) {
			return true;
		}
		return true;
	}

	public void addCreditCardInformation(String cardName, String cardNo, String expiryMonth, String expiryYear) {
		CF.waitForElementToBeClickable(AddNewCard);
		AddNewCard.click();
		CF.waitForSeconds(3);
		CardName.sendKeys(cardName);
		CF.waitForSeconds(2);
		CardNo.sendKeys(cardNo);
		CF.waitForSeconds(2);
		CF.SelectByValue(ExpiryMonth, expiryMonth);
		CF.waitForSeconds(2);
		CF.SelectByValue(ExpiryYear, expiryYear);
		CF.waitForElementToBeClickable(SubmitButton);
		SubmitButton.click();
		CF.waitForElementToBeClickable(SelectPaymentCard);
		SelectPaymentCard.click();
	}

	public void clickCompletePurchase() {
		CF.waitForElementToBeClickable(ContinueButton);
		ContinueButton.click();
		CF.waitForElementToBeClickable(ConfirmOrder);
		ConfirmOrder.click();
	}

	public boolean isPurchaseSuccessful() {
		CF.waitForSeconds(3);
		return checkout.getText().trim().toLowerCase().contains("thank you for your purchase!");
	}

	@FindBy(xpath = "//h1")
	private WebElement checkout;
	@FindBy(xpath = "//h1")
	private WebElement payment;

	@FindBy(xpath = "//button[@aria-label='Add a new address']")
	private WebElement AddNewAddress;

	@FindBy(xpath = "//*[contains(text(),'Add New Address')]")
	private WebElement AddNewAddressHeader;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a country.']")
	private WebElement Country;
	@FindBy(xpath = "//input[@data-placeholder='Please provide a name.']")
	private WebElement Name;
	@FindBy(xpath = "//input[@data-placeholder='Please provide a mobile number.']")
	private WebElement MobileNumber;
	@FindBy(xpath = "//input[@data-placeholder='Please provide a ZIP code.']")
	private WebElement Zipcode;
	@FindBy(xpath = "//*[@placeholder='Please provide an address.']")
	private WebElement Address;
	@FindBy(xpath = "//input[@placeholder='Please provide a city.']")
	private WebElement City;
	@FindBy(xpath = "//input[@placeholder='Please provide a state.']")
	private WebElement State;

	@FindBy(id = "submitButton")
	private WebElement SubmitButton;

	@FindBy(xpath = "//span[@class='mat-radio-container']")
	private WebElement SelectAddress;

	@FindBy(xpath = "//*[contains(text(),'Continue')]")
	private WebElement ContinueButton;

	@FindBy(xpath = "(//span[@class='mat-radio-container'])[3]")
	private WebElement StandardDeliveryOption;

	@FindBy(xpath = "//div[@class='custom-card ng-star-inserted']//span[@class='mat-button-wrapper']")
	private WebElement Wallet;

	@FindBy(xpath = "//*[contains(text(),' Add a credit or debit card ')]")
	private WebElement AddNewCard;

	@FindBy(xpath = "//span[@class='mat-radio-container']")
	private WebElement SelectPaymentCard;
	@FindBy(xpath = "//*[contains(text(),'Place your order and pay')]")
	private WebElement ConfirmOrder;

	@FindBy(xpath = "(//input)[2]")
	private WebElement CardName;
	@FindBy(xpath = "(//input)[3]")
	private WebElement CardNo;
	@FindBy(xpath = "(//select)[1]")
	private WebElement ExpiryMonth;
	@FindBy(xpath = "(//select)[2]")
	private WebElement ExpiryYear;

}
