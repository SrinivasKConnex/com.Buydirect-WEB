package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testBase.TestBase;

public class CongratulationPage extends TestBase {

	By welcomePageHeader = By.id("headerConfirmation");

	public WebElement waitUntilConfirmationPageDisplay() {
		return driver.findElement(welcomePageHeader);
	}

	public String getConfirmationPageHeaderText() {
		return driver.findElement(welcomePageHeader).getText();
	}
}
