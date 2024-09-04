package pageObjects;

import org.openqa.selenium.By;
import testBase.TestBase;

public class WelcomePage extends TestBase {
	
	private By CLICK_BUTTON = By.id("btnGetStarted");
	private By CLICK_SIGNIN_LINK = By.xpath("(//a[@class='text-primary fw-semibold link-sign-in'])[2]");
	private By HEADER_TEXT = By.id("headerWelcomeStart");
	private By BODY_TEXT = By.id("dynamic-welcome-message");
	private By BUTTON_TEXT = By.id("btnGetStarted");
	private By TEXT_BEFORE_LINK = By.xpath("//div[@id='WelcomeStart']//p[@class='text-muted mb-0 SingInHereText']");
	private By GET_SIGNIN_LINK = By.xpath("(//a[@class='text-primary fw-semibold link-sign-in'])[2]");

	public void clickGetStartButton() {
		click_custom(driver.findElement(CLICK_BUTTON), "Click WelComePage Button");
		//driver.findElement(CLICK_BUTTON).click();
	}

	public void clickSignIn() {
		click_custom(driver.findElement(CLICK_SIGNIN_LINK), "Click Signin Button");
	}

	public String getHeaderText() {

		return getText_custom(driver.findElement(HEADER_TEXT),"Get header text from the web");
	}

	public String getTextBeforeLink() {

		return getText_custom(driver.findElement(TEXT_BEFORE_LINK),"Get header text from the web");

	}

	public void isSignInLinkDisplay() {

		isElementPresent_custom(driver.findElement(GET_SIGNIN_LINK),"Is Display SignIn Link ");

	}

	public String getButtonText() {
		return getText_custom(driver.findElement(BUTTON_TEXT),"Get button text from the web");

	}

	public String getWelcomeBodytext() {
		String ss2 = driver.findElement(BODY_TEXT).getText();
		String ss2WithoutNewlines = ss2.replaceAll("\\r?\\n", " ");
		return ss2WithoutNewlines;
	}

}
