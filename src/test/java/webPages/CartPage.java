package webPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonFunction;

public class CartPage {
	private WebDriver driver;
	CommonFunction CF = new CommonFunction(driver);

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isCartPageDisplayed() {
		return cart.getText().trim().toLowerCase().contains("your basket");
	}

	public double getTotalPrice() {
		String text = totalPrice.getText().trim();
		String priceText = text.replaceAll("[^0-9.]", "");
		System.out.println(priceText);
	    return Double.parseDouble(priceText);
	}

	public void increaseProductQuantity() {
		CF.waitForElementToBeClickable(quantitySelectors);
		CF.waitForSeconds(2);
		quantitySelectors.click();
		CF.waitForSeconds(2);
	}

	public boolean isTotalPriceChanged(double oldPrice) {
		return getTotalPrice() != oldPrice;
	}

	public void deleteProduct() {
		CF.waitForElementToBeClickable(deleteButtons);
		deleteButtons.click();
		CF.waitForSeconds(1);
	}

	public void clickCheckoutButton() {
		CF.waitForElementToBeClickable(checkoutButton);
		checkoutButton.click();
		CF.waitForSeconds(1);
	}

	@FindBy(xpath = "//h1")
	private WebElement cart;

	@FindBy(id = "price")
	private WebElement totalPrice;

	@FindBy(xpath = "(//*[@data-icon='plus-square'])[1]")
	private WebElement quantitySelectors;

	@FindBy(xpath = "(//*[@data-icon='minus-square'])[1]")
	private WebElement deleteButtons;

	@FindBy(id = "checkoutButton")
	private WebElement checkoutButton;

}
