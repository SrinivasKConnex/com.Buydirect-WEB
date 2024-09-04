package reusableComponents;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import pageObjects.ABADDACompleteMessagePage;
import pageObjects.AbstractPage;
import pageObjects.BankIntroPage;
import pageObjects.CDWBankingDeclinePage;
import pageObjects.CDW_ABA_DDA_Page;
import pageObjects.CDW_MicroDeposit_Page;
import pageObjects.CongratulationPage;
import pageObjects.EnrollPage;
import pageObjects.IAVPage;
import pageObjects.PlasticIndicatorPage;
import pageObjects.SignInPage;
import pageObjects.WelcomeBackPage;
import pageObjects.WelcomePage;
import testBase.DriverFactory;
import testBase.ExtentFactory;

/**
 * @author: Srinivas
 */
public class ActionEngine {

	public static WelcomePage WelcomePageObject;
	public static AbstractPage AbstractPageObject;
	public static SignInPage SignInpageObject;
	public static EnrollPage EnrollPageObject;
	public static Helper helper;
	public static PlasticIndicatorPage PlasticIndicatorPageObject;
	public static BankIntroPage BankintroPageobject;
	public static CDW_ABA_DDA_Page CDWpendingPageObject;
	public static IAVPage IAVPageObject;
	public static ABADDACompleteMessagePage ABADDACompleteMessagePageObject;
	public static WelcomeBackPage WelcomeBackPageObject;
	public static CDW_MicroDeposit_Page CDW_MicroDeposit_PageObject;
	public static CongratulationPage CongratulationPageObject;
	public static CDWBankingDeclinePage CDWBankingDeclinePageObject;

	public static void ObjectRepo(WebDriver driver) {
		WelcomePageObject = initPage(driver, WelcomePage.class);
		AbstractPageObject = initPage(driver, AbstractPage.class);
		helper = initPage(driver, Helper.class);
		SignInpageObject = initPage(driver, SignInPage.class);
		EnrollPageObject = initPage(driver, EnrollPage.class);
		PlasticIndicatorPageObject = initPage(driver, PlasticIndicatorPage.class);
		BankintroPageobject = initPage(driver, BankIntroPage.class);
		CDWpendingPageObject = initPage(driver, CDW_ABA_DDA_Page.class);
		IAVPageObject = initPage(driver, IAVPage.class);
		ABADDACompleteMessagePageObject = initPage(driver, ABADDACompleteMessagePage.class);
		WelcomeBackPageObject = initPage(driver, WelcomeBackPage.class);
		CDW_MicroDeposit_PageObject = initPage(driver, CDW_MicroDeposit_Page.class);
		CongratulationPageObject = initPage(driver, CongratulationPage.class);
		CDWBankingDeclinePageObject=initPage(driver, CDWBankingDeclinePage.class);
	}

	private static <T> T initPage(WebDriver driver, Class<T> pageClass) {
		return PageFactory.initElements(driver, pageClass);
	}

	public static void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {

			element.sendKeys(valueToBeSent);
			// log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					fieldName + "==> Enterd value as: " + "<b>" + valueToBeSent + "</b>");
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Value enter in field: " + fieldName + " is failed due to exception: " + e);
		}
	}

	// custom click method to log every click action in to extent report
	public static void click_custom(WebElement element, String fieldName) {
		try {
			element.click();
			// log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.INFO, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to click on field: " + fieldName + " due to exception: " + e);
		}
	}

	// clear data from field
	public static void clear_custom(WebElement element, String fieldName) {
		try {
			element.clear();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, fieldName + "==> Data Cleared Successfully! ");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to clear Data on field: " + fieldName + " due to exception: " + e);

		}
	}

	// custom mouseHover
	public void moveToElement_custom(WebElement element, String fieldName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, fieldName + "==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to hover mouse on field: " + fieldName + " due to exception: " + e);

		}
	}

	// check if element is Present
	public boolean isElementPresent_custom(WebElement element, String fieldName) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, fieldName + "==> Presence of field is: " +"<b>"+ flag+"</b>");
			return flag;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			return flag;
		}
	}

	// Select dropdown value value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement element, String fieldName, String ddVisibleText)
			throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field:  " + fieldName + " due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	public static void selectDropDownByValue_custom(WebElement element, String fieldName, String ddValue) {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					fieldName + "==> Dropdown Value Selected by value text: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	public static void selectDropdownbyindex(WebElement element, String fieldName, String Value) {
		WebElement stateDropdownElement = element;
		try {
			// Create a Select object from the dropdown WebElement
			Select stateDropdown = new Select(stateDropdownElement);
			// Get all options from the dropdown
			List<WebElement> options = stateDropdown.getOptions();
			for (WebElement option : options) {
				// Get the text of the state
				String state = option.getText();
				if (state.equalsIgnoreCase(Value)) {
					String ss = option.getAttribute("value");
					int num = Integer.parseInt(ss);
					// Select the state from the dropdown
					stateDropdown.selectByIndex(num);
					ExtentFactory.getInstance().getExtent().log(Status.INFO,
							fieldName + "==> Dropdown Value Selected by Value: " + "<b>" + Value + "</b>");
					// System.out.println(statename);
					break;

				}
			}

		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}

	}

	public static void selectDropdownbyindex_custom(WebElement element, String indexvalue, String fieldName) {
		try {
			Select stateDropdown = new Select(element);
			List<WebElement> options = stateDropdown.getOptions();
			for (WebElement option : options) {
				// Get the text of the state
				String state = option.getText();
				if (state.equalsIgnoreCase(indexvalue)) {
					String ss = option.getAttribute("value");
					int num = Integer.parseInt(ss);
					stateDropdown.selectByIndex(num);
					ExtentFactory.getInstance().getExtent().log(Status.INFO,
							fieldName + "==> Dropdown Value Selected by index: " + "<b>" + indexvalue + "</b>");
					break;

				}

			}
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// String Asserts
	public static void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) {
		try {
			if (actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS,
						"String Assertion is successful on field: " + "<b>" + locatorName + "</b><br><br>"
								+ " Expected value was: " + "<b>" + expvalue + "<br><br></b>" + " Actual value is: "
								+ "<b>" + actualValue + "</b><br><br>");
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL,
						"String Assertion FAILED on field: " + "<b>" + locatorName + "</b><br><br>"
								+ " Expected value was: " + "<b>" + expvalue + "<br></b><br>" + " actual value is: "
								+ "<b>" + actualValue + "</b><br>");
				Assert.fail(locatorName);
			}
		} catch (Exception e) {
			Assert.fail("Exception in Test_InstructionTextbelowTheImage: " + e.getMessage());
			//Assert.assertTrue(false, e.toString());
		}
	}

	// Assertstrue
	public void assertTrue_custom(Boolean expvalue, String value, Boolean actualvalue) {
		try {
			if (expvalue) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS,
						"Assertion is True: " + "<b>" + value + "</b><br><br>" + " Expected value was: " + "<b>"
								+ expvalue + "<br><br></b>" + " Actual value is: " + "<b>" + actualvalue
								+ "</b><br><br>");
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL,
						"String Assertion FAILED on field: " + "<b>" + value + "<b><br><br>" + " Expected value was: "
								+ "<b>" + expvalue + "<br></b><br>" + " actual value is: " + "<b>" + actualvalue
								+ "</b><br>");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	// Assertfalse
	public void assertFalse_custom(Boolean expvalue, String value, Boolean actualvalue) {
		try {
			if (!expvalue) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS,
						"Assertion is True: " + "<b>" + value + "</b><br><br>" + " Expected value was: " + "<b>"
								+ expvalue + "<br><br></b>" + " Actual value is: " + "<b>" + actualvalue
								+ "</b><br><br>");
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL,
						"String Assertion FAILED on field: " + "<b>" + value + "<b><br><br>" + " Expected value was: "
								+ "<b>" + expvalue + "<br></b><br>" + " actual value is: " + "<b>" + actualvalue
								+ "</b><br>");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	// Get text from webelement
	public String getText_custom(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					fieldName + "==> Text retried is: " + "<b><br>" + text + "</b></br>");
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					fieldName + "==> Text not retried due to exception: " + e);

		}
		return text;
	}

	// Wait Element to appear
	public static void waitElementToAppear_custom(WebElement webElement, String pagetext) {
		try {
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"==> Wait Element to appear: " + "<b>" + pagetext + "</b");

			WebDriverWait ex = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(50));
			ex.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					pagetext + "==> elemennt not appear due to exception: " + e);

		}
	}

	// Wait Element to appear
	public void waitElementToClikable_custom(WebElement webElement, String pagetext) {
		try {
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"==> Wait Element to clickable: " + "<b>" + pagetext + "</b>");
			WebDriverWait ex = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(50));
			ex.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					pagetext + "==> elemennt not appear due to exception: " + e);

		}
	}

	// Get Text box maximum count
	public int TextBoxMaxCount_custom(WebElement webElement, String textcount) {
		int maxLength = 0;
		try {
			maxLength = Integer.parseInt(webElement.getAttribute("maxlength"));
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"==> Text count retried is: " + "<b>" + textcount + "</b>");

		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					textcount + "==> Text count not retried due to exception: " + e);

		}
		return maxLength;
	}

	public static String getTextFromTheInputBox_custom(WebElement webElement, String inputboxtext) {
		String text = null;
		try {
			text = webElement.getAttribute("value");
			//ExtentFactory.getInstance().getExtent().log(Status.INFO,	"==> Text retried is: " + "<b>" + inputboxtext + "</b>");
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					inputboxtext + "==> Text not retried due to exception: " + e);

		}
		return text;

	}

	public List<String> getAllListOfDDValue_custom(WebElement element, String dropdown) {
		List<String> statelist = new ArrayList<String>();
		try {

			Select stateDropdown = new Select(element);
			List<WebElement> options = stateDropdown.getOptions();
			for (int i = 1; i < options.size(); i++) {
				WebElement option = options.get(i);
				String state = option.getText();
				statelist.add(state);
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"==> Text retried is: " + "<b>" + "state" + "</b>");
			}
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					dropdown + "==> Text not retried due to exception: " + e);

		}
		return statelist;
	}

	public static String getStateDropDownDefaultValue_cutom(WebElement element, String DropDown) {
		String defaultvalue = null;
		try {
			Select stateDropdown = new Select(element);
			WebElement defaultSelectedValue = stateDropdown.getFirstSelectedOption();
			defaultvalue = defaultSelectedValue.getText();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					DropDown + "==> Default selected value is: " + defaultvalue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					DropDown + "==> Text not retried due to exception: " + e);

		}
		return defaultvalue;
	}

	public void enterDateOfBirth_custom(String year, String month, String day, WebElement selectyear,
			WebElement selectmonth, List<WebElement> selectDay, String field) {
		try {
			selectDropDownByValue_custom(selectyear, "Year:", year);
			waitElementToAppear_custom(selectmonth, "Month DropDown");
			// selectDropdownbyindex("",selectmonth);
			selectDropDownByValue_custom(selectmonth, "Month:", month);
			List<WebElement> days = selectDay;
			for (int i = 0; i < days.size(); i++) {

				String ss = days.get(i).getText();
				if (ss.equals(day)) {
					ExtentFactory.getInstance().getExtent().log(Status.INFO,
							"==> selected Day is: " + "<b>" + day + "</b>");
					days.get(i).click();
					break;
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"DOB" + "==> Text not retried due to exception: " + ex);

		}
	}

	public static boolean checkInvalidErrorMessage_custom(WebElement element, String fieldname) {
		try {
			String invalidError = element.getText();
			if (invalidError.equals("The Zip must be 5 character long")
					|| invalidError.equals("The Number must be min 3 digits long")
					|| invalidError.equals("Please Select Terms and Conditions") || invalidError.equals("Invalid")
					|| invalidError.equals("The PIN must be 4 digits long")) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						fieldname + "==> Error message: " + "<b><i><u>" + invalidError + "</b></i></u>");
				return true;
			} else {
				return false;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false; // Error message element not found
		}
	}
}
