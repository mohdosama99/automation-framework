package webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TaskOnePage {
	
	private WebDriver driver;

	public TaskOnePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}