package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class WelcomeBackPage extends TestBase {

	By welcomeBackButton = By.id("btnContinue");
	By welcomeBackHeader = By.id("headerWelcome");

	public void clickOnWelcomeBackContinueButton() {
		driver.findElement(welcomeBackButton).click();
	}

	public WebElement waitWelcomeBackHeaderDisplay() {
		return driver.findElement(welcomeBackHeader);
	}
}
