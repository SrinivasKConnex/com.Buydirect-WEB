
package pageObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import dbModel.Consumer;
import dbModel.Consumer.Address;
import dbModel.Consumer.Contact;
import dbModel.Consumer.DriversLicense;
import reusableComponents.Helper;
import reusableComponents.ReadExcelData;
import testBase.TestBase;

/**
 * @author :Srinivas com.Buydirect-WEB
 */
public class EnrollPage extends TestBase {



	// Locators
	private static By firstname = By.id("firstname");
	private static By lastname = By.id("lastname");
	private static By email = By.id("email");
	private static By phone = By.id("phone");
	private static By address = By.id("address");
	private static By suite = By.id("suite");
	private static By city = By.id("city");
	private static By state = By.id("state");
	private static By zip = By.id("zip");
	private static By dateofbirth = By.id("dateofbirth");
	private static By selectyear = By.xpath("//select[@title='Select a year']");
	private static By selectmonth = By.xpath("//select[@title='Select a month']");
	private static By selectDay = By.xpath("//div[contains(@class,'picker__day picker__day--infocus')]");
	private static By Createpin = By.id("Createpin");
	private By Clickterm = By.id("term");
	private By clicknotifications = By.id("notifications");
	private static By SelectID = By.id("SelectID");
	private static By stateissued = By.id("stateissued");
	private static By dlnumber = By.id("number");
	private By RequiredId = By
			.xpath("//div[@class='form-group mb-3 has-error']//div[@class='help-block animation-slideUp']");
	private By ClearTextField = By.xpath(
			"//div[@id='tabEnroll']//input[@type='text'] | //div[@id='tabEnroll']//input[@type='email']|//div[@id='tabEnroll']//input[@type='password']");
	private By labellist = By.xpath("//form[@id='form-Enroll']//label[contains(@class, 'form-label')]");
	private By headertext = By.id("headerEnroll");
	private By placeholder = By.xpath("//form[@id='form-Enroll']//input[contains(@class, 'form-control')]");
	private By StateanIDToolTip = By.xpath("//label[@for='SelectID']//i[@class='mdi mdi-information-variant']");
	private By PinToolTip = By.xpath("//label[@for='Createpin']//i[@class='mdi mdi-information-variant']");
	private By checkBox = By.xpath("//input[@type='checkbox']");
	private By checkHyperLink = By.xpath("//div[@class='col-md-12 d-flex justify-content-center']//a");
	private By termsclick = By.id("term");
	private By termsboxstatus = By.xpath("//input[@aria-invalid='false']");
	private By eyeelement = By.xpath("(//div[@class='input-group auth-pass-inputgroup']//i)[1]");
	private By getmasktext = By.id("Createpin");
	private By uncheckboxterms = By.xpath("//input[@id='term']|//input[@id='notifications']");
	private By gettextnotification = By.xpath("//label[@for='notifications']");
	private By gettextterms = By.xpath("//label[@for='term']");
	private By buttonText = By.id("btnEnroll");
	private By getsigninlink = By.xpath("(//a[@class='text-primary fw-semibold link-sign-in'])[1]");
	private By textbeforelink = By.xpath("//div[@id='tabEnroll']//p[@class='text-muted mb-0 SingInHereText']");
	private By error_EmailAlreadyExist = By.id("errorMessage");
	private By closeRederrormessage = By.id("btn-close--alert");

	public static void enterFirstname(String fname) {
		clear_custom(driver.findElement(firstname), "Clear First Name InputBox Data");
		sendKeys_custom(driver.findElement(firstname), "Enter First Name:", fname);
	}

	public static String getFirstName() {

		return getTextFromTheInputBox_custom(driver.findElement(firstname), "Get First Name Text Box");

	}

	public int getFirstnameMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(firstname), "First Name");
	}

	public static void enterLastname(String Lastname) {
		clear_custom(driver.findElement(lastname), "Clear Last Name InputBox Data");
		sendKeys_custom(driver.findElement(lastname), "Enter Last Name:", Lastname);
	}

	public static String getLastName() {
		return getTextFromTheInputBox_custom(driver.findElement(lastname), "Get last Name");

	}

	public int getLastNameMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(lastname), "Last Name");
	}

	public static void enterEmail(String gmail) {
		clear_custom(driver.findElement(email), "Clear Last Email InputBox Data");
		sendKeys_custom(driver.findElement(email), "Enter Email Name:", gmail);
	}

	public int getemaillengthValue() {
		return TextBoxMaxCount_custom(driver.findElement(lastname), "Get last Name Text Box count:");

	}

	public static String getEnterdemailValue() {
		return getTextFromTheInputBox_custom(driver.findElement(email), "Get email");

	}

	public int getEmailMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(email), "Email text box maximum count");
	}

	public static void enterPhonenumber(String phonenumber) {
		clear_custom(driver.findElement(phone), "Clear Last phone number InputBox Data");
		sendKeys_custom(driver.findElement(phone), "Enter phone number:", phonenumber);
	}

	public int getPhoneMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(phone), "phonenumber text box maximum count");

	}

	public static String getPhoneNumber() {
		return getTextFromTheInputBox_custom(driver.findElement(phone), "Get last Name Text Box");

	}

	public static void enterAddress(String address1) {
		clear_custom(driver.findElement(address), "Clear address InputBox Data");
		sendKeys_custom(driver.findElement(address), "Enter Address:", address1);
	}

	public int getAddressMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(address), "phonenumber text box maximum count");

	}

	public static String getAddress() {
		return getTextFromTheInputBox_custom(driver.findElement(address), "Get last Name Text Box");

	}

	public static void enterSuteNumber(String suiteid) {
		clear_custom(driver.findElement(suite), "Clear Last address1 InputBox Data");
		sendKeys_custom(driver.findElement(suite), "Enter suite :", suiteid);
	}

	public int getsuiteMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(address), "suite text box maximum count");

	}

	public static String getsuite() {
		return getTextFromTheInputBox_custom(driver.findElement(suite), "Get last Name Text Box");

	}

	public static void enterCity(String cityname) {
		clear_custom(driver.findElement(city), "Clear Last address1 InputBox Data");
		sendKeys_custom(driver.findElement(city), "Enter city name:", cityname);
	}

	public int getCityMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(city), "City text box maximum count");
	}

	public static String getCity() {
		return getTextFromTheInputBox_custom(driver.findElement(city), "Get city name Text Box");

	}

	public static void selectstate(String statename) {

		selectDropDownByValue_custom(driver.findElement(state), "Select State Drop Down value ", statename);
		// selectDropdownbyindex_custom(driver.findElement(state), statename, "Select
		// State Drop Down value ");
	}

	public static String getstate() {
		return getTextFromTheInputBox_custom(driver.findElement(state), "Get state value ");

	}

	public List<String> getAllListOfStates() {

		return getAllListOfDDValue_custom(driver.findElement(state), "Staate Drop down list of Values");
	}

	public static String getStateDropDownDefaultValue() {

		return getStateDropDownDefaultValue_cutom(driver.findElement(state), "State Dropdown");
	}

	public static void enterZip(String zipid) {
		clear_custom(driver.findElement(zip), "Clear Last zip InputBox Data");
		sendKeys_custom(driver.findElement(zip), "Enter zip:", zipid);
	}

	public int getzipMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(zip), "zip text box maximum count");

	}

	public static String getZip() {
		return getTextFromTheInputBox_custom(driver.findElement(zip), "Get zip value ");

	}

	/*
	 * public void enterDob(String month, String year, String day) {
	 * 
	 * WebElement eyear = driver.findElement(selectyear); WebElement emonth =
	 * driver.findElement(selectmonth); List<WebElement> eday =
	 * driver.findElements(selectDay); click_custom(driver.findElement(dateofbirth),
	 * "Clcik on Date of birth :"); enterDateOfBirth_custom(year, month, day, eyear,
	 * emonth, eday, "Select Date Of birth:");
	 * 
	 * }
	 */

	public static void enterDob(String month, String year, String day) {
		try {
			click_custom(driver.findElement(dateofbirth), "Clcik on Date of birth :");
			selectDropDownByValue_custom(driver.findElement(selectyear), "Year:", year);
			selectDropdownbyindex(driver.findElement(selectmonth), "Month:", month);
			List<WebElement> days = driver.findElements(selectDay);
			for (int i = 0; i < days.size(); i++) {

				String ss = days.get(i).getText();
				if (ss.equals(day)) {
					click_custom(days.get(i), day);
					break;
				}

			}

		} catch (Exception ex) {
			// ex.printStackTrace();
		}

	}

	public static String getDob() {
		return getTextFromTheInputBox_custom(driver.findElement(dateofbirth), "Date Of Birth ");
	}

	public int getDobMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(dateofbirth), "zip text box maximum count");
	}

	public static void enterpin(String pin) {
		clear_custom(driver.findElement(Createpin), "Clear Last pin InputBox Data");
		sendKeys_custom(driver.findElement(Createpin), "Enter pin:", pin);
	}

	public static String getEnterdPinValue() {
		return getTextFromTheInputBox_custom(driver.findElement(Createpin), "Get pin value");
	}

	public int getPinMaxFieldLengthcount() {
		return TextBoxMaxCount_custom(driver.findElement(Createpin), "Pin text box maximum count");
	}

	public void selecttearm() {
		waitElementToClikable_custom(driver.findElement(Clickterm), "Wait for tearm link appear");
		click_custom(driver.findElement(Clickterm), "Click tearm check box:");
	}

	public void selectsms() {
		click_custom(driver.findElement(clicknotifications), "Click tearm check box");
	}

	public void submitenrollbutton() {

		click_custom(driver.findElement(buttonText), "Click enroll button:");
	}

	public static String getDLoption() {
		return getTextFromTheInputBox_custom(driver.findElement(SelectID), "Get pin value ");
	}

	public static void selectDLIssuestate(String statename) {

		selectDropDownByValue_custom(driver.findElement(stateissued), "Select State Drop Down value ", statename);

	}

	public List<String> getAllListOfStatesFromStateIssued() {
		return getAllListOfDDValue_custom(driver.findElement(stateissued), "Staate Drop down list of Values");

	}

	public String getSelectanIDDropDownDefaultValue() {
		return getStateDropDownDefaultValue_cutom(driver.findElement(stateissued), "state issued Dropdown");

	}

	public static String getdlstate() {
		return getTextFromTheInputBox_custom(driver.findElement(stateissued), "Get state value ");

	}

	public String getStateIssuedDropDownDefaultValue() {
		return getStateDropDownDefaultValue_cutom(driver.findElement(stateissued), "State Dropdown");
	}

	public static void enterDLnumber(String Dlnumber) {
		clear_custom(driver.findElement(dlnumber), "Clear Last Dlnumber InputBox Data");
		sendKeys_custom(driver.findElement(dlnumber), "Enter Dlnumber:", Dlnumber);
	}

	public static String getdlNumber() {
		return getTextFromTheInputBox_custom(driver.findElement(dlnumber), "Get dlnumber value ");

	}

	public void ClearEnrollInputFields(boolean status) {
		List<WebElement> inputFields = driver.findElements(ClearTextField);
		getselectStateDropDownDefaultValue(status);
		unCheckBox();
		for (WebElement inputField : inputFields) {
			try {
				inputField.clear();
			} catch

			(org.openqa.selenium.InvalidElementStateException e) {
			}
		}
	}

	public void changeToDefaultValue() {

		Select stateDropdown = new Select(driver.findElement(SelectID));
		stateDropdown.selectByIndex(0);
	}

	public void getselectStateDropDownDefaultValue(boolean status) {

		if (status == true) {
			Select stateDropdown1 = new Select(driver.findElement(state));
			stateDropdown1.selectByIndex(0);
			Select stateDropdown = new Select(driver.findElement(stateissued));
			stateDropdown.selectByIndex(0);

		} else {
			Select stateDropdown1 = new Select(driver.findElement(state));
			stateDropdown1.selectByIndex(0);
		}

	}

	public List<String> getRequiredtextid() {

		List<WebElement> Error_id = driver.findElements(RequiredId);
		List<String> id = new ArrayList<>();
		for (int i = 0; i < Error_id.size(); i++) {
			id.add(Error_id.get(i).getAttribute("id"));

		} // System.out.println(id);
		return id;
	}

	public Map<String, String> getLableText() {

		Map<String, String> labeltext = new HashMap<>();
		for (WebElement label : driver.findElements(labellist)) {
			labeltext.put(label.getAttribute("for"), label.getText());
		}
		return labeltext;
	}

	public String getHeaderText() {
		return getText_custom(driver.findElement(headertext), "Get Enroll page Header Text");
	}

	public Map<String, String> getPlaceHolder() {
		Map<String, String> placeholdertext = new HashMap<>();
		for (WebElement placeholder1 : driver.findElements(placeholder)) {
			placeholdertext.put(placeholder1.getAttribute("name"), placeholder1.getAttribute("placeholder")); //
			System.out.println(placeholder1.getAttribute("placeholder"));
		}
		return placeholdertext;

	}

	public boolean isErrorMessageDisplayed(String field) {
		try {
			By getinvalidError = By.id(field + "-error");
			driver.findElement(termsclick).isSelected();
			return checkInvalidErrorMessage_custom(driver.findElement(getinvalidError),
					"Get " + field + " name field error messgae:");
		} catch (Exception ex) {
			return false;
		}
	}

	public WebElement mouseStateAnIdOverOnTootip() {

		return driver.findElement(StateanIDToolTip);

	}

	public String mouseOverOnStateOnIdTootip() {

		String ss = driver.findElement(StateanIDToolTip).getAttribute("data-bs-original-title");
		return ss;

	}

	public String getPinToolTipText() {

		String ss = driver.findElement(PinToolTip).getAttribute("data-bs-original-title"); //
		return ss;

	}

	public WebElement mouseOverOnPinTootip() {

		return driver.findElement(PinToolTip);

	}

	public WebElement getRequiredMessageElement(String rr) {
		WebElement ss = null;
		try {
			ss = driver.findElement(By.id(rr));
			return ss;

		} catch (Exception ex) {
		}
		return ss;
	}

	public boolean CheckBoxDisplay() {
		boolean status = false;
		List<WebElement> checkBoxElement = driver.findElements(checkBox);
		for (WebElement check : checkBoxElement) {
			status = check.isDisplayed();
		}
		return status;

	}

	public boolean checkHyperlink() {
		boolean status = false;
		List<WebElement> link = driver.findElements(checkHyperLink);
		for (WebElement linkstatus : link) {
			String hrefURL = linkstatus.getAttribute("href");
			status = Helper.verifyLink(hrefURL);
		}
		return status;
	}

	public void ClcikCheckBox() {
		click_custom(driver.findElement(termsclick), "click term and condition checkBox");

	}

	public boolean GetCheckBoxStatus() {

		return driver.findElement(termsboxstatus).getAttribute("aria-invalid").equals("false");

	}

	public void clickEyeIcon() {

		click_custom(driver.findElement(eyeelement), "Click on EyeIcon");

	}

	public WebElement getPinElement() {

		return driver.findElement(getmasktext);
	}

	public void unCheckBox() {

		for (WebElement ele : driver.findElements(uncheckboxterms)) {
			if (ele.isSelected()) {
				ele.click();
			}
		}

	}

	public String getTextnotificationText() {
		return getText_custom(driver.findElement(gettextnotification), "Get Notification Text");

	}

	public String getTermsandConditionText() {

		return getText_custom(driver.findElement(gettextterms), "Get test term and condition text");
	}

	public String getButtonText() {

		return getText_custom(driver.findElement(buttonText), "Get Button text");
	}

	public boolean getSignInLink() {
		return isElementPresent_custom(driver.findElement(getsigninlink), "SignIn Link");

	}

	public String getTextBeforeLink() {

		return getText_custom(driver.findElement(textbeforelink), "Get Text Before sign In Text");

	}

	public String getRedBoxErrorMessage() {
		waitElementToAppear_custom(driver.findElement(error_EmailAlreadyExist), "Wait for Error message to display");
		return getText_custom(driver.findElement(error_EmailAlreadyExist), "Get Error Message");

	}

	public void CloseErrorRedboxMessage() {
		click_custom(driver.findElement(closeRederrormessage), "Close RedBoxError Message");
	}

	public String[] formatSendAndGetStringText(String sentKeyDOB, String getDOB) {
		String[] dob = new String[2];
		try {
			SimpleDateFormat sendKeyDateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
			SimpleDateFormat getStringDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat commonDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Parse the dates
			Date parsedSendKeyDate = sendKeyDateFormat.parse(sentKeyDOB);
			Date parsedGetStringDate = getStringDateFormat.parse(getDOB);

			// Convert both dates to the same format
			String sendKeyDateCommon = commonDateFormat.format(parsedSendKeyDate);

			String getStringDateCommon = commonDateFormat.format(parsedGetStringDate);
			dob[0] = sendKeyDateCommon;
			dob[1] = getStringDateCommon;
			// Assert the dates are the same

		} catch (Exception ex) {

		}
		return dob;
	}

	public static void selectID() {
		Select ss = new Select(driver.findElement(SelectID));
		ss.selectByIndex(0);
	}

	public static Map<String, String> completePIV(String sheetname, String columnName, boolean email) {
		Map<String, String> data = ReadExcelData.Getdatafromexcel(sheetname, columnName);
		Map<String, String> date = Helper.dob(data.get("dob"));
		Map<String, String> ActualDate = new HashMap<>();

		enterFirstname(data.get("Fname"));
		ActualDate.put("fname", getFirstName());

		enterLastname(data.get("Lname"));
		ActualDate.put("lname", getLastName());

		String emailAddress = email ? data.get("mail_address") : Helper.randomEmail();

		enterEmail(emailAddress);
		ActualDate.put("email", getEnterdemailValue());

		enterPhonenumber(data.get("Phone_number"));
		ActualDate.put("phone", getPhoneNumber());

		enterAddress(data.get("Address1"));
		ActualDate.put("address", getAddress());

		enterSuteNumber(data.get("address2"));
		ActualDate.put("address2", getsuite());

		enterCity(data.get("City"));
		ActualDate.put("city", getCity());

		selectstate(data.get("state"));
		ActualDate.put("state", getstate());

		enterZip(data.get("zip"));
		ActualDate.put("zip", getZip());

		if (Helper.checkPartnerSelectDLOption()) {
			selectID();
			ActualDate.put("selectDl", getDLoption());

			selectDLIssuestate(data.get("Dl_state"));
			ActualDate.put("dlstate", getdlstate());

			enterDLnumber(data.get("Dl_number"));
			ActualDate.put("dlnumber", getdlNumber());
		}

		enterDob(date.get("Month"), date.get("Year"), date.get("Day"));
		ActualDate.put("dob", getDob());

		enterpin(data.get("pin"));
		ActualDate.put("pin", getEnterdPinValue());

		EnrollPageObject.selecttearm();
		EnrollPageObject.submitenrollbutton();

		return ActualDate;
	}

	public static void assertNamesMatch(Consumer expectedData, Map<String, String> ConsumerActualData) {
		assertEqualsString_custom(expectedData.getName().getFirstName(), ConsumerActualData.get("fname"), "First name");
		assertEqualsString_custom(expectedData.getName().getLastName(), ConsumerActualData.get("lname"), "Last name ");
	}

	public static void assertDateOfBirthMatches(Consumer expectedData, Map<String, String> ConsumerActualData) {
		assertEqualsString_custom(Helper.formetDob(expectedData.getDob()), ConsumerActualData.get("dob"),
				"Date of birth ");
	}

	public static void assertAddressMatches(Address expectedAddress, Map<String, String> ConsumerActualData) {
		assertEqualsString_custom(expectedAddress.getAddress1(), ConsumerActualData.get("address"), "Address1 ");
		assertEqualsString_custom(expectedAddress.getAddressLine2(), ConsumerActualData.get("address2"),
				"Address line");
		assertEqualsString_custom(expectedAddress.getZip(), ConsumerActualData.get("zip"), "ZIP code ");
		assertEqualsString_custom(expectedAddress.getCity(), ConsumerActualData.get("city"), "City");
		assertEqualsString_custom(expectedAddress.getState().getStateAbbreviation(), ConsumerActualData.get("state"),
				"State abbreviation");
	}

	public static void assertLicenseMatches(DriversLicense expectedLicense, Map<String, String> ConsumerActualData) {
		if (Helper.checkPartnerSelectDLOption()) {
			assertEqualsString_custom(expectedLicense.getLicense(), ConsumerActualData.get("dlnumber"),
					"Driver's license");
			assertEqualsString_custom(expectedLicense.getState().getStateAbbreviation(),
					ConsumerActualData.get("dlstate"), "Driver's license state");
		}

	}

	public static void assertMobileNumberMatches(Contact expectedContact, Map<String, String> ConsumerActualData) {
		assertEqualsString_custom(expectedContact.getMobileNumber(), Helper.trimchar(ConsumerActualData.get("phone")),
				"Mobile number");
	}

	public static void handleException(Exception e) {
		e.printStackTrace();
		Assert.fail("Test failed: " + e.getMessage());
	}

	public static String getConsumerEmail(Map<String, String> data) {
		String ss = data.get("email");
		String d = "'" + ss + "'";
		return d;

	}

}
