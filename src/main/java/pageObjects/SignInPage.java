
package pageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

/**
 * @author :Srinivas 4:00:32 PM com.Buydirect-WEB
 */
public class SignInPage extends TestBase {
	//WebDriver driver=DriverFactory.getInstance().getDriver();
	// Using By to locate elements
	private By email = By.id("logInEmail");
	private By pin = By.id("logInPin");
	private By loginButton = By.id("btnSignIn");
	private By closeErrorMessage = By.xpath("//button[@id='btn-close--alert']");
	private By clickEyeIcon = By.xpath("//div[@class='form-group']//div//i[@class='mdi mdi-eye zulu-pass-icon']");
	private By getMaskText = By.id("logInPin");
	private By getHeaderText = By.id("headerSignIn");
	private By invalidEmailError = By.id("logInEmail-error");
	private By invalidPinError = By.id("logInPin-error");
	private By invalidError = By.id("errorScreen");
	private By emailLabelText = By.xpath("//label[@for='email'][normalize-space()='Email']");
	private By pinLabelText = By.xpath("//label[@for='pin'][normalize-space()='PIN']");
	//private By buttonElement = By.xpath("//button[@id='btnGetStarted']");
	private By textBeforeLink = By.xpath("//div[@class='col-12 mb-3']//p[@class='text-muted mb-0']");
	private By enrollLinkText = By.id("lnkEnroll");
	private By forgotPinLink = By.id("link-forgot-pin");
	private By selectForgetButton = By.xpath("//a[@id='link-forgot-pin']");

	public void clickForgetPinLink() {

		click_custom(driver.findElement(selectForgetButton), "Click Forget Button");
	}

	public String enrollLinkText() {

		return getText_custom(driver.findElement(enrollLinkText),
				"Get Enroll link Text ");

	}

	public String forgetLinkText() {

		return getText_custom(driver.findElement(forgotPinLink),
				"Get forgotPinLink Text ");

	}

	public String getTextBeforeLink() {
		String extractedText = null;
		String text = getText_custom(driver.findElement(textBeforeLink),
				"Get forgotPinLink Text ");
		String pattern = "(.*)Enroll"; // Regex pattern to match everything before "Enroll"
		Pattern r = Pattern.compile(pattern); // Create a Pattern object
		Matcher m = r.matcher(text);// Create matcher object
		if (m.find()) {
			// The text before "Enroll" is captured in group 1
			extractedText = m.group(1).trim();
		}
		return extractedText;
	}

	public String getEmaillabelText() {

		return getText_custom(driver.findElement(emailLabelText),
				"Get emailLabel Text ");

	}

	public String getEmailPlaceHolderlText() {

		return driver.findElement(email).getAttribute("placeholder");
	}

	public String getPinPlaceHolderlText() {

		return driver.findElement(pin).getAttribute("placeholder");
	}

	public String getPinlabelText() {

		return getText_custom(driver.findElement(pinLabelText), "Get PinLabel Text ");
	}

	public WebElement getinvalidErrrorText() {

		 WebElement element= driver.findElement(invalidError);
		 
		 return element;
		
	}
	
	
	public String getHeaderText() {

		return getText_custom(driver.findElement(getHeaderText), "Get Header Text ");

	}

	
	public void consumer_login(String loginemail,String loginpin)
	{
		clear_custom(driver.findElement(email), "Clear Email input box:");
		sendKeys_custom(driver.findElement(email), "Enter the Email Data:",
				loginemail);
		clear_custom(driver.findElement(pin), "Clear Email input box:");
		sendKeys_custom(driver.findElement(pin), "Enter the Email Data:", loginpin);
		click_custom(driver.findElement(loginButton), "Click login Button");
	}
	public void enterLoginEmail(String loginemail) {

		clear_custom(driver.findElement(email), "Clear Email input box:");
		sendKeys_custom(driver.findElement(email), "Enter the Email Data:",
				loginemail);

	}

	public void enterLoginPin(String loginpin) {
		clear_custom(driver.findElement(pin), "Clear Email input box:");
		sendKeys_custom(driver.findElement(pin), "Enter the Email Data:", loginpin);
	}

	public void submitLogin() {

		click_custom(driver.findElement(loginButton), "Click login Button");

	}

	public void closeErrorMessage() {
		click_custom(driver.findElement(closeErrorMessage), "Close error message");
	}

	public void clickEyeIcon() {
		click_custom(driver.findElement(clickEyeIcon), "Close error message");
	}

	public WebElement getPinElement() {
		return driver.findElement(getMaskText);

	}

	public String getRequiredEmailErrorText() {

		return getText_custom(driver.findElement(invalidEmailError),
				"Get RequiredEmailError Text ");

	}

	public String getRequiredPinErrorText() {

		return getText_custom(driver.findElement(invalidPinError),
				"Get RequiredpinError Text ");

	}

	public String getSigninButonText() {
		return getText_custom(driver.findElement(loginButton),
				"Get SigninButonText Text ");

	}
	
	public void waitFor_ErrorMessageAppear() {
		 waitElementToAppear_custom(driver.findElement(closeErrorMessage),
				"Wait for the display of the error message.");

	}

}
