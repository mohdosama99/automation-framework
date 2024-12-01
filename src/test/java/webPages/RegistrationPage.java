package webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonFunction;

public class RegistrationPage {
	private WebDriver driver;
	CommonFunction CF = new CommonFunction(driver);

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    // Methods for interacting with the registration page
	
	public void NavigateToRegistrationPage() {
		CF.waitForElementToBeClickable(AccountButton);
		AccountButton.click();
		CF.waitForElementToBeClickable(LoginButton);
		LoginButton.click();
		CF.waitForElementToBeClickable(SignUpButton);
		SignUpButton.click();
	}

    public void enterEmail(String emailValue) {
        email.sendKeys(emailValue);
    }

    public void enterPassword(String passwordValue) {
        password.sendKeys(passwordValue);
    }

    public void enterConfirmPassword(String confirmPasswordValue) {
    	repeatPassword.sendKeys(confirmPasswordValue);
    }

    public void clickShowPasswordAdvice() {
        showPasswordAdvice.click();
    }

    public void clickRegister() {
        registerBtn.click();
    }

    public boolean isRegistrationSuccessful() {
        return successMessage.isDisplayed();
    }

    public LoginPage navigateToLoginPage() {
        loginLink.click();
        return new LoginPage(driver); // Assuming you have a LoginPage class
    }

    // Method to trigger validation by clicking on all fields without entering values
    public void triggerValidationMessages() {
    	CF.waitForSeconds(2);
        email.click();
        CF.waitForSeconds(2);
        
        password.click();
        CF.waitForSeconds(2);
        
        repeatPassword.click();
        CF.waitForSeconds(2);
        
//        //securityQues.click();
//        CF.clickUsingJS(securityQues);
//        CF.waitForSeconds(2);
        securityAnswer.click();
        CF.waitForSeconds(2);
        email.click();

    }
    
    public void CheckErrorMessage() {
    	EmailErrorMessage.isDisplayed();
    	PasswordErrorMessage.isDisplayed();
    	RepeatPasswordErrorMessage.isDisplayed();
    	//QuestionErrorMessage.isDisplayed();
    	AnswerErrorMessage.isDisplayed();
    }
    
    public void fillSecurityDetails() {
    	//CF.waitForElementToBeClickable(securityQuesBox);
    	CF.waitForSeconds(2);
    	securityQuesBox.click();
    	//securityQuesBox.clear();
    	//securityQuesBox.click();
    	CF.waitForSeconds(2);
    	securityQuestion.click();
    	securityAnswer.sendKeys("TestAutomation");
    	CF.waitForSeconds(2);
    	
    }
    
    @FindBy(id = "navbarAccount")
    private WebElement AccountButton;
    
    @FindBy(xpath = "(//span[contains(text(),'Login')])[2]")
    private WebElement LoginButton;
    
    @FindBy(xpath = "//*[contains(text(),'Not yet a customer?')]")
    private WebElement SignUpButton;


    @FindBy(id = "emailControl")
    private WebElement email;

    @FindBy(id = "passwordControl")
    private WebElement password;

    @FindBy(id = "repeatPasswordControl")
    private WebElement repeatPassword;
    
    @FindBy(xpath = "//div[@class='mat-select-arrow-wrapper ng-tns-c30-11']/..")
    private WebElement securityQuesBox;
    
    @FindBy(xpath = "(//span[@class='mat-option-text'][contains(text(),'Mother')])[1]")
    private WebElement securityQuestion;
    
    @FindBy(id = "securityAnswerControl")
    private WebElement securityAnswer;

    @FindBy(id = "registerButton")
    private WebElement registerBtn;

    @FindBy(xpath = "//span[@class='mat-slide-toggle-thumb-container']")
    private WebElement showPasswordAdvice;

    @FindBy(xpath = "//*[contains(text(),'Registration successful')]")
    private WebElement successMessage;

    @FindBy(id = "loginLink")
    private WebElement loginLink;
    
    @FindBy(xpath = "//*[contains(text(),'Please provide an email address.')]")
    private WebElement EmailErrorMessage;
    @FindBy(xpath = "//*[contains(text(),'Please provide a password.')]")
    private WebElement PasswordErrorMessage;
    @FindBy(xpath = "//*[contains(text(),' Please repeat your password. ')]")
    private WebElement RepeatPasswordErrorMessage;
    @FindBy(xpath = "//*[contains(text(),' Please select a security question. ')]")
    private WebElement QuestionErrorMessage;
    @FindBy(xpath = "//*[contains(text(),' Please provide an answer to your security question. ')]")
    private WebElement AnswerErrorMessage;


}
