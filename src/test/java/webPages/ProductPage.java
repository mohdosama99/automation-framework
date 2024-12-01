package webPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonUtilities.CommonFunction;

public class ProductPage {

	private WebDriver driver;
	CommonFunction CF = new CommonFunction(driver);

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addProductToCart(int productIndex) {
	    CF.waitForSeconds(5);
	    List<WebElement> productListAddToCart = driver.findElements(By.xpath("//*[contains(text(),'Add to Basket')]"));
	    
	    int count = 0;
	    for (WebElement list : productListAddToCart) {
	        if (count < productIndex) {
	            CF.waitForSeconds(2);
	            CF.waitForElementToBeClickable(list);
	            list.click();
	            CF.waitForSeconds(5);
	          //Assert.assertTrue(successPopup.isDisplayed(),"Success popup not displayed after adding product " + list);
	            count++;
	        } else {
	            break;
	        }
	    }
	}

//	public boolean isSuccessPopupDisplayed() {
//		return successPopup.isDisplayed();
//	}

	public int getCartItemCount() {
		String cartCount = cartItemCount.getText().trim();
		return Integer.parseInt(cartCount);
	}

	public void clickOnCartIcon() {
		CF.waitForElementToBeClickable(cartIcon);
		cartIcon.click();
	}

//	@FindBy(xpath = "//*[@aria-label='Add to Basket']")
//	private List<WebElement> productListAddToCart;

	@FindBy(xpath = "//*[contains(text(),' Your Basket')]")
	private WebElement cartIcon;

	@FindBy(xpath = "//span[@class='fa-layers-counter fa-layers-top-right fa-3x warn-notification']")
	private WebElement cartItemCount;

	@FindBy(xpath = "//*[contains(text(),'Added')]")
	private WebElement successPopup;

}
