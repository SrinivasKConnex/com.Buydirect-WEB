package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class ErrorPage extends TestBase {

	By errorPageHeader = By.id("bank-error--header");
	By errorPageBody = By.id("bank-error--body");

	public WebElement waitUntilErrorPageDisplay() {
		return driver.findElement(errorPageHeader);
	}

	public String getErrorMessageHeader() {
		return driver.findElement(errorPageHeader).getText();
	}

	public String getErrorPageBodyText() {
		return driver.findElement(errorPageBody).getText();
	}
}
