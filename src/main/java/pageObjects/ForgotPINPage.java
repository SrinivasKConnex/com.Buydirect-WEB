package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class ForgotPINPage extends TestBase {

	By forgetPinHeader = By.id("headerForgotPin");
	By labelText = By.xpath("//label[@for='forgotPinEmail']");
	By emailInputLabel = By.id("forgotPinEmail");
	By getButtonText = By.id("btnForgotPin");
	By getLoginBackText = By.xpath("//div[@id='forgotPin']//a[@class='text-primary fw-semibold link-sign-in']");

	public String getForgetPinPageButtonText() {
		return driver.findElement(getButtonText).getText();
	}

	public WebElement waitUntilForgetPinPageDisplay() {
		return driver.findElement(forgetPinHeader);
	}

	public String getForgotPinPageHeader() {
		return driver.findElement(forgetPinHeader).getText();
	}

	public String getLabelText() {
		return driver.findElement(labelText).getText();
	}

	public String getEmailPlaceHolderText() {
		return driver.findElement(emailInputLabel).getAttribute("placeholder");
	}

	public String getLoginBackText() {
		return driver.findElement(getLoginBackText).getText();
	}
}
