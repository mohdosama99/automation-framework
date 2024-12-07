package webPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonFunction;

public class JuiceShopPage {

	private WebDriver driver;
	CommonFunction CF;

	public JuiceShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		CF = new CommonFunction(driver);
	}

	// Actions for the elements

	public void clickItemsPerPageDropdown() {
		CF.waitForElementToBeClickable(itemsPerPageDropdown);
		itemsPerPageDropdown.click();
	}

	public void selectMaxItemsOption() {
		CF.waitForElementToBeClickable(maxItemsOption);
		maxItemsOption.click();
	}

	public int getAllItems() {
		int count = allItems.size();
		System.out.println(count);
		return count;
	}

	public int getProductCount() {
		String productCount = totalCount.getText().trim();
		String[] s = productCount.split(" ");
		String count = s[s.length - 1];
		System.out.println(count);
		return Integer.parseInt(count);
	}

	public void DismissAlerts() {
		// CF.waitForElementToVisible("//span[contains(text(),'Dismiss')]");
		CF.waitForElementToBeClickable(DismissButton);
		DismissButton.click();
		CF.waitForElementToBeClickable(PopUp);
		PopUp.click();
	}

	// Actions
	public void clickFirstProduct() {
		CF.waitForElementToBeClickable(firstProduct);
		firstProduct.click();
	}

	public boolean isPopupDisplayed() {
		CF.waitForElementToBeClickable(CardPopUp);
		return CardPopUp.isDisplayed();
	}

	public boolean isProductImageDisplayed() {
		// CF.waitForElementToBeClickable(productImage);
		return productImage.isDisplayed();
	}

	public boolean isReviewSectionAvailable() {
		// CF.waitForElementToBeClickable(reviewExpandButton);
		return reviewExpandButton.isDisplayed();
	}

	public void expandReviewSection() {
		CF.waitForElementToBeClickable(reviewExpandButton);
		reviewExpandButton.click();
	}

	public void closeProductForm() {
		closePopupButton.click();
	}

	@FindBy(xpath = "//mat-select[@aria-label='Items per page:']")
	private WebElement itemsPerPageDropdown;

	@FindBy(xpath = "//mat-option/span[contains(text(),'48')]")
	private WebElement maxItemsOption;

	@FindBy(xpath = "//mat-card")
	private List<WebElement> allItems;

	@FindBy(xpath = "//*[@class='mat-paginator-range-label']")
	private WebElement totalCount;

	@FindBy(xpath = "//span[contains(text(),'Dismiss')]")
	private WebElement DismissButton;

	@FindBy(xpath = "//a[contains(text(),'Me want it!')]")
	private WebElement PopUp;

	// Locators
	@FindBy(xpath = "(//mat-card[contains(@class, 'mat-card')])[1]")
	private WebElement firstProduct;

	@FindBy(xpath = "//div[@class='cdk-overlay-pane']")
	private WebElement CardPopUp;

	@FindBy(xpath = "//div[@class='cdk-overlay-pane']//*[contains(@class, 'img-thumbnail')]")
	private WebElement productImage;

	@FindBy(xpath = "//span[contains(text(),'Reviews')]")
	private WebElement reviewExpandButton;

	@FindBy(xpath = "//button[@aria-label='Close Dialog']")
	private WebElement closePopupButton;

}