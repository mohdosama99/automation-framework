package appPages;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.WebDriverManager;
import commonUtilities.CommonFunction;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

public class JuiceShop {
	private AppiumDriver appiumDriver;
	CommonFunction cf;

	public JuiceShop(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(appiumDriver, this);
		cf = new CommonFunction(appiumDriver);
	}

	public void DismissAlerts() {
		dismissAlert.click();
	}

	public void scrollToBottom() {
		try {
			appiumDriver.executeScript("mobile: scroll", Map.of("direction", "down"));
			cf.waitForSeconds(2);
		} catch (Exception e) {
			System.err.println("Failed to scroll to the bottom: " + e.getMessage());
		}
	}

	public void scrollToTop() {
		try {
			appiumDriver.executeScript("mobile: scroll", Map.of("direction", "up"));
			cf.waitForSeconds(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckNavigationOnThreeIcon() {
		MyLists.click();
		cf.waitForSeconds(3);
		History.click();
		cf.waitForSeconds(3);
		nearby.click();
		cf.waitForSeconds(3);
		browse.click();
	}

	public void verifySearchFunctionality(String data) {
		cf.waitForElementToBeClickable(searchBar);
		searchBar.click();
		cf.waitForSeconds(2);
		searchBox.sendKeys(data);
		cf.waitForSeconds(5);
		FirstSuggestion.click();
		// searchBar.sendKeys(Keys.ENTER);
		cf.waitForSeconds(5);
	}

	public boolean isSearchBarExpandedwithReturnedResults() {
		return searchBoxWithKeyword.isDisplayed();
	}

	public void NavigateBackToHomePage() {
		try {
			doubleClickElement(NavigateBack);
		} catch (Exception e) {
			e.printStackTrace();
			NavigateBack.click();
		}

	}

	public void doubleClickElement(WebElement element) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		Sequence clickSequence = new Sequence(finger, 0);
		clickSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		clickSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		clickSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		clickSequence.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), x, y));
		clickSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		clickSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		appiumDriver.perform(Arrays.asList(clickSequence));
	}

	public void NavigateToSettingPage() {
		HamburgerMenu.click();
		cf.waitForSeconds(1);
		SettingButton.click();
		cf.waitForSeconds(1);
	}

	public void disableAllOptions() {
		cf.waitForElementToBeClickable(ShowImages);
		ShowImages.click();
		cf.waitForElementToBeClickable(ShowLink);
		ShowLink.click();
		cf.waitForElementToBeClickable(SendUsageReport);
		SendUsageReport.click();
		cf.waitForElementToBeClickable(SendCrashReport);
		SendCrashReport.click();
		cf.waitForSeconds(2);
	}

	public void NavigateBack() {
		NavigateBack.click();
		cf.waitForSeconds(2);
	}

	public boolean verifyHomePage() {
		return HomePage.isDisplayed();
	}

	@FindBy(id = "android:id/button1")
	private WebElement dismissAlert;

	@FindBy(id = "org.wikipedia.alpha:id/single_fragment_toolbar")
	private WebElement HomePage;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[2]")
	private WebElement MyLists;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[3]")
	private WebElement History;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[4]")
	private WebElement nearby;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[1]")
	private WebElement browse;

	@FindBy(id = "org.wikipedia.alpha:id/fragment_feed_header")
	private WebElement searchBar;

	@FindBy(id = "org.wikipedia.alpha:id/search_src_text")
	private WebElement searchBox;

	@FindBy(id = "org.wikipedia.alpha:id/fragment_feed_header")
	private WebElement FirstSuggestion;

	@FindBy(xpath = "//android.widget.TextView[@text=\"New York\"]")
	private WebElement searchBoxWithKeyword;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private WebElement NavigateBack;

	@FindBy(id = "org.wikipedia.alpha:id/menu_overflow_button")
	private WebElement HamburgerMenu;
	@FindBy(id = "org.wikipedia.alpha:id/explore_overflow_settings")
	private WebElement SettingButton;

	@FindBy(xpath = "(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[1]")
	private WebElement ShowImages;
	@FindBy(xpath = "(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[2]")
	private WebElement ShowLink;
	@FindBy(xpath = "(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[3]")
	private WebElement SendUsageReport;
	@FindBy(xpath = "(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[4]")
	private WebElement SendCrashReport;

}
