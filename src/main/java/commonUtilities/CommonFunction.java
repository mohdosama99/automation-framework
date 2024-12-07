package commonUtilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunction {

	public WebDriver driver;
	private Actions actions;

	public CommonFunction(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollToElement(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SelectByValue(WebElement ele, String value) {
		Select select = new Select(ele);
		ele.click();
		waitForSeconds(2);
		select.selectByValue(value);
	}

	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementToVisible(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

}
