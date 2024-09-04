package reusableComponents;

import java.awt.Color;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testBase.TestBase;

public class Helper extends TestBase {

	static DB_Operations dbOps = new DB_Operations();

	public static String randomEmail() {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100000);
		String email = "Srinivas_" + randomInt + "_TestAutomation@Hep.com";
		return email;
	}

	public static String encryptString(String string) {
		byte[] bytesEncoded = Base64.getDecoder().decode(string.getBytes());
		return (new String(bytesEncoded));
	}

	@SuppressWarnings("deprecation")
	public static boolean verifyLink(String url) {
		boolean URlstatus = false;
		try {
			URL link = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
			httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
				// System.out.println(url + " - " + httpURLConnection.getResponseMessage());
				return URlstatus = true;
			} else {
				// System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " -
				// " + "is a broken link");
				return URlstatus = false;
			}
		} catch (Exception e) {
			// System.out.println(url + " - " + "is a broken link");
			e.printStackTrace();
		}
		return URlstatus;

	}

	public static String hexToRGB(String hexColor) {
		hexColor = hexColor.replace("#", "");

		int r = Integer.valueOf(hexColor.substring(0, 2), 16);
		int g = Integer.valueOf(hexColor.substring(2, 4), 16);
		int b = Integer.valueOf(hexColor.substring(4, 6), 16);
		int a = 255;
		if (hexColor.length() == 8) {
			a = Integer.parseInt(hexColor.substring(6, 8), 16);
		}

		Color c = new Color(r, g, b, a);
		String colors = colorToString(c);
		return colors;
	}

	public static String colorToString(Color color) {
		return String.format("rgb(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
	}

	public String getExpectedColor(Map<String, String> butonColor) {
		String[] colorcode = butonColor.get("Theme_Color").split("\\|");
		String color = colorcode[0];
		String expectedColor = Helper.hexToRGB(color);
		return expectedColor;
	}

	public static boolean checkPartnerSelectDLOption() {

		return DB_Operations.getSqlResultInMap(SQLQuery.dl_status).get("IsDLRequired").equals("1");
	}

	public static void checkPartnerselectedPlasticindicater() {
		if (Helper.checkPartnerSelectPlasticindicator() == 2 || Helper.checkPartnerSelectPlasticindicator() == 3) {

			PlasticIndicatorPageObject.clickPlasticRequest();
		}
	}

	// Function to validate first name using the provided regular expression
	public static boolean validateFirstName(String firstName) {
		// Regular expression pattern for first name validation
		String regex = "^[a-zA-Z]+(?:[\\s-_.’']+?[a-zA-Z]+)*$";
		return Pattern.matches(regex, firstName);
	}

	public static boolean isAlphabetical(List<String> stateNames) {
		// Check if the list is empty or has only one element
		if (stateNames == null || stateNames.size() <= 1) {
			return true;
		}

		// Check if each state name is in alphabetical order
		for (int i = 1; i < stateNames.size(); i++) {
			// Compare each pair of adjacent state names
			String current = stateNames.get(i);
			String previous = stateNames.get(i - 1);

			// If the current state is not greater than the previous, it's not in
			// alphabetical order
			if (current.compareToIgnoreCase(previous) < 0) {
				return false;
			}
		}

		// If all state names are in alphabetical order
		return true;
	}

	public static void selectDropdownValue(String Value, WebElement element) {
		WebElement stateDropdownElement = element;
		// Create a Select object from the dropdown WebElement
		Select stateDropdown = new Select(stateDropdownElement);
		// Get all options from the dropdown
		List<WebElement> options = stateDropdown.getOptions();
		for (WebElement option : options) {
			// Get the text of the state
			String state = option.getText();
			if (state.equals(Value)) {
				// Select the state from the dropdown
				stateDropdown.selectByValue(Value);
				// System.out.println(statename);
				break;

			}

		}
	}

	public static void selectDropdownbyindex(String Value, WebElement element) {
		WebElement stateDropdownElement = element;
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
				// System.out.println(statename);
				break;

			}

		}
	}

	public static String replacecurlybracket(String text) {
		try {
			return text.replace("{", "").replace("}", "");
		} catch (Exception ex) {
		}
		return text;
	}

	public static int checkPartnerSelectPlasticindicator() {

		String plasticpagevalue = DB_Operations.getSqlResultInMap(SQLQuery.plastic_status)
				.get("Is_Plastics_Page_Displayed");
		int number = Integer.parseInt(plasticpagevalue);
		if (number == 2 || number == 3) {
			System.out.println(
					"INFO: Partner Selected Plastic indicator option and current partner status are as follows:"
							+ number
							+ "\nINFO: Options 2 and 3 indicate that the Partner Selected Plastic page will be displayed");
			return number;
		} else {
			System.out.println(
					"INFO: Partner Not Selected Plastic indicator option and current partner status are as follows:"
							+ number
							+ "\nINFO: Option 1 indicate that the Partner not Selected Plastic page will be displayed");
			DB_Operations.executeUpdate(SQLQuery.Update_plastic_statustoTwo);
			System.out.println(
					"INFO: Update the Plastic Indicator status to 2 to test the Plastic Indicator page functionality.");
			return number;
		}

	}

	public static  boolean checkPartnerSelectPlasticindicatorornot() {

		String plasticpagevalue = DB_Operations.getSqlResultInMap(SQLQuery.plastic_status)
				.get("Is_Plastics_Page_Displayed");
		int number = Integer.parseInt(plasticpagevalue);
		if (number == 2 || number == 3) {
			return true;
		} else {

			return false;
		}

	}

	public static void updatePlasticPageStatus(int status) {
		if (status == 1) {
			DB_Operations.executeUpdate(SQLQuery.Update_plastic_statustoone);
			System.out.println("INFO: Update the Plastic Indicator option to remain as it is");
		} else {
			if (status == 0) {
				DB_Operations.executeUpdate(SQLQuery.Update_plastic_statustoZero);
			}

		}
	}

	public static int checkPartnerSelectBankIntoPage() {

		String Bankintropagevalue = DB_Operations.getSqlResultInMap(SQLQuery.bank_intro_pageOption)
				.get("Is_Banking_Intro_Page_Displayed");
		int number = Integer.parseInt(Bankintropagevalue);
		if (number == 1) {
			return number;
		} else {
			DB_Operations.getSqlResultInMap(SQLQuery.Update_BankIntroPage_statustoOne);
			System.out.println("update to one");
			return number;
		}

	}

	public  boolean checkPartnerSelectBankIntoPageOrNot() {

		String plasticpagevalue = DB_Operations.getSqlResultInMap(SQLQuery.bank_intro_pageOption)
				.get("Is_Banking_Intro_Page_Displayed");
		int number = Integer.parseInt(plasticpagevalue);
		if (number == 1) {
			return true;
		} else {

			return false;
		}

	}

	public static void updateBankIntroStatus(int status) {

		if (status == 0) {
			DB_Operations.getSqlResultInMap(SQLQuery.Update_BankIntroPage_statusZero);
			System.out.println("update to Zero");
		}

	}

	public static boolean checkCustomizedErrorExistOrNot() {

		if (DB_Operations.getSqlResultInMap(SQLQuery.check_EmailAlredayExistStatus).get("Category_ID").equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	public static HashMap<String, String> dob(String date) {
		HashMap<String, String> ss = new HashMap<String, String>();
		{
			String[] d = date.split("/");
			ss.put("Day", d[0]);
			ss.put("Month", d[1]);
			ss.put("Year", d[2]);
		}
		return ss;

	}

	public static int getTextBoxMaxFieldLengthcount(WebElement element) {
		int maxLength = Integer.parseInt(element.getAttribute("maxlength"));
		return maxLength;

	}

	public static boolean checkInvalidErrorMessageforTextBoxvalid(WebElement element) {
		try {
			boolean status = element.isDisplayed();
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public static String formetDob(String dob) {

		String date = dob;
		// Extract month, day, and year parts
		String month = date.substring(0, 2);
		String day = date.substring(2, 4);
		String year = date.substring(4);

		// Form the formatted date string
		String formattedDate = month + "/" + day + "/" + year;
		return formattedDate;
	}

	public static String trimchar(String phone) {
		return phone.replaceAll("-", "");
	}

	public static String removenewLineChar(String text) {
		return text.replace("\n", " ");
	}

	public static void zoomOutChromeWindow(WebDriver driver) {
		driver.get("chrome://settings/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("chrome.settingsPrivate.setDefaultZoom(0.6)");
	}
	
	public static String getcurrentDate()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		Date date = new Date();
		String actualDate = format.format(date);
		return actualDate;
		
	}

}
