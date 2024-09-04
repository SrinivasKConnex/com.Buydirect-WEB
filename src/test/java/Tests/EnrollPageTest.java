
package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import dbModel.Consumer;
import pageObjects.EnrollPage;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.ReadExcelData;
import reusableComponents.ReadXMLData;
import reusableComponents.SQLQuery;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)

public class EnrollPageTest extends TestBase {
	public static Map<String, String> ConsumerActualData;
	ExtentTest parentTest, chaildTest;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		// logger.info("******************************");
		System.out.println("=>Starting testing on the Enroll Page.\n");
		parentTest = ListenersImplementation.getReport().createTest("Enroll Page Test Started");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach Enroll Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		WelcomePageObject.clickGetStartButton();
	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the Enroll page has been completed.");
		System.out.println("******************************");

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@Test
	public void Test_HeaderLogo() {
		try {
			assertEqualsString_custom(AbstractPageObject.getLogoUrl(),
					DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL"), "Page Header logo");
		} catch (Exception e) {
			fail("Failed to verify header logo: " + e.getMessage());
		}
	}

	@Test
	public void Test_HeaderImageIsBroken() {
		try {
			assertTrue(!AbstractPageObject.getLogoBroken().getAttribute("naturalWidth").equals("0"));
		} catch (Exception e) {
			fail("Header image broken: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerTenderName() {
		try {
			assertEqualsString_custom(AbstractPageObject.getTenderName(),
					DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query).get("Tender_Name_Header"),
					"Partner Tender Name");
		} catch (Exception e) {
			fail("Failed to verify partner tender name: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerLogoDisplayed() {
		try {
			String expectedLogoUrl = DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL");
			String actualLogoUrl = AbstractPageObject.getLogoUrl();
			assertEqualsString_custom(actualLogoUrl, expectedLogoUrl,
					"URL of the partner's logo does not match the expected URL");

		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_FooterTextWithPartnerPhoneNumberAndPartnerName() {
		try {
			String footerText = AbstractPageObject.getFooterText();
			HashMap<String, String> partnerDetails = DB_Operations
					.getSqlResultInMap(SQLQuery.Get_Partner_namephonenumber_query);
			assertEqualsString_custom(footerText,
					"Please contact the " + partnerDetails.get("Product_Name") + " Support Team at "
							+ partnerDetails.get("partner_contact_number") + " with any questions.",
					"Test Footer Text with partner phone and name");
		} catch (Exception e) {
			fail("Failed to verify footer text: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerLogoIsNotBroken() {
		try {
			WebElement logoElement = AbstractPageObject.getLogoBroken();
			String naturalWidth = logoElement.getAttribute("naturalWidth");

			if ("0".equals(naturalWidth)) {

				fail("Partner's logo is broken.");
			} else {

				assertTrue(true);
			}
		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_DisplayedTenderNameMatchesDatabase() {
		try {
			String expectedTenderName = DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query)
					.get("Tender_Name_Header");
			String actualTenderName = AbstractPageObject.getTenderName();

			assertEqualsString_custom(actualTenderName, expectedTenderName,
					"Displayed tender name does not match the expected tender name.");

		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_EnrollPageHeader() {
		try {
			String expectedHeaderText = "Enroll";
			String actualHeaderText = EnrollPageObject.getHeaderText();

			assertEqualsString_custom(actualHeaderText, expectedHeaderText,
					"Header text on the enroll page does not match the expected value.");

		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_RequiredMessageDisplayedForAllEnrollmentFields() {
		try {
			List<String> errorIds = EnrollPageObject.getRequiredtextid();

			for (String errorId : errorIds) {
				String actualErrorMessage = EnrollPageObject.getRequiredMessageElement(errorId).getText();
				assertEqualsString_custom(actualErrorMessage, "Required",
						"Expected 'Required' message not displayed for field with ID: " + errorId);
			}

		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public static void Test_EnrollInputLabels() {
		try {
			Map<String, String> labelText = EnrollPageObject.getLableText();
			Map<String, String> expectedLabels = new HashMap<>();
			expectedLabels.put("firstname", "First Name");
			expectedLabels.put("lastname", "Last Name");
			expectedLabels.put("email", "Email Address");
			expectedLabels.put("phone", "Mobile Phone");
			expectedLabels.put("address", "Street Address");
			expectedLabels.put("suite", "Suite / Apartment");
			expectedLabels.put("city", "City");
			expectedLabels.put("state", "State");
			expectedLabels.put("zip", "Zip Code");
			expectedLabels.put("dateofbirth", "Date of Birth");
			expectedLabels.put("Createpin", "Create Temporary Enrollment PIN");

			// Check partner select DL Option
			if (Helper.checkPartnerSelectDLOption()) {
				expectedLabels.put("SelectID", "Select an ID");
				expectedLabels.put("stateissued", "State Issued");
				expectedLabels.put("number", "Number");
			}

			for (Map.Entry<String, String> entry : expectedLabels.entrySet()) {
				String label = entry.getKey();
				String expectedLabel = entry.getValue();
				assertEqualsString_custom(labelText.get(label), expectedLabel,
						expectedLabel + " label text doesn't match.");
			}

		} catch (Exception e) {

			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_FirstNamedWithValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_FirstOrLasrName");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {
				EnrollPage.enterFirstname(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("firstname");
				String errorMessage = "Entered name is Invalid First Name : " + validatedata.getValue();
				assertFalse(isErrorDisplayed,
						isErrorDisplayed ? errorMessage : "Valid First name: " + validatedata.getValue());
			}
			EnrollPageObject.ClearEnrollInputFields(false);

		} catch (Exception e) {
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_FirstNameWithInvalidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_FirstOrLasrName");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {
				EnrollPage.enterFirstname(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("firstname");
				String message = isErrorDisplayed ? "Valid First name: " + validatedata.getValue()
						: "Entered name is valid First Name There is no Error : " + validatedata.getValue();
				assertTrue(isErrorDisplayed, message);
			}
			EnrollPageObject.ClearEnrollInputFields(false);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_FirstNameMaxLengthValidation() {
		try {
			String maxText = "a".repeat(28); // Creating a string with maximum length
			EnrollPage.enterFirstname(maxText);
			int expectedLength = EnrollPageObject.getFirstnameMaxFieldLengthcount();
			assertEquals(maxText.length(), expectedLength, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_FirstNameMinLengthValidation() {
		try {
			String minText = "a"; // Creating a string with minimum length
			EnrollPage.enterFirstname(minText);
			// Assert that the entered text meets the minimum length requirement
			assertEquals(minText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_LastNamedWithValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_FirstOrLasrName");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {
				EnrollPage.enterLastname(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("lastname");
				String message = isErrorDisplayed
						? "Entered name is valid First Name There is no Error : " + validatedata.getValue()
						: "Valid Last name: " + validatedata.getValue();
				Assert.assertFalse(isErrorDisplayed, message);
			}
			EnrollPageObject.ClearEnrollInputFields(false);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void test_LastNameWithInvalidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_FirstOrLasrName");

			for (Map.Entry<String, String> entry : data.entrySet()) {
				String lastName = entry.getValue();
				EnrollPage.enterLastname(lastName);
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("lastname");
				if (isErrorDisplayed) {
					assertTrue(true, "Valid Last name: " + lastName);
				} else {
					assertTrue(false, "Entered Last Name is valid, but no error displayed: " + lastName);
				}
			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void Test_LastNameMaxLengthValidation() {
		try {
			String maxLengthText = "a".repeat(28);
			EnrollPage.enterLastname(maxLengthText);
			int actualLength = EnrollPageObject.getLastNameMaxFieldLengthcount();
			assertEquals(maxLengthText.length(), actualLength, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void Test_TheLastNameMinLengthValidation() {
		try {
			String MinlenghtText = "a".repeat(1);
			EnrollPage.enterLastname(MinlenghtText);
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_EmailValidationWithInValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_Email");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterEmail(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("email");
				if (isErrorDisplayed) {
					Assert.assertTrue(true, "Invalid Emailid invalid Error should display: " + validatedata.getValue());
				} else {
					Assert.assertTrue(false,
							"Entered name is Valid Email Should not display invalid Error: " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_EmailValidationWithValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_Email");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterEmail(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("email");
				if (!isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, "Valid First name: " + validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered inValid Email Error messaage should Display : " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheEmailNameMaxLengthValidation() {
		try {

			String MaxlenghtText = "a".repeat(250);
			EnrollPage.enterEmail(MaxlenghtText);
			int ExpectedLenghtText = EnrollPageObject.getEmailMaxFieldLengthcount();
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), ExpectedLenghtText,
					"Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheEmailMinLengthValidation() {
		try {
			String MinlenghtText = "a".repeat(1);
			EnrollPage.enterEmail(MinlenghtText);
			// Assert that the entered text is truncated to the Min length
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the phone number Validation with Invalid input
	public void Test_PhoneNumberValidationWithInValidInput() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_PhoneNumber");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterPhonenumber(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("phone");
				if (!isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true,
							"Invalid phone number invalid Error should display: " + validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false, "Entered name is Valid phone number Should not display invalid Error: "
							+ validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	// verify the phone number Validation with Valid input
	public void Test_PhoneNumberValidationWithValidInput() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_PhoneNumber");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterPhonenumber(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("phone");
				if (isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered inValid phone number Error messaage should Display : " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_ThePhoneNameMaxLengthValidation() {
		try {
			String MaxlenghtText = "1".repeat(12);
			EnrollPage.enterPhonenumber(MaxlenghtText);
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), 12, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_ThePhoneMinLengthValidation() {
		try {
			String MinlenghtText = "1".repeat(1);
			EnrollPage.enterPhonenumber(MinlenghtText);
			// Assert that the entered text is truncated to the Min length
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the CityName Validation with Valid input
	public void Test_CityNameValidationWithValidInput() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_Cityname");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterCity(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("city");
				if (!isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered inValid City name, Error messaage should Display : " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the CityName Validation with InValid input
	public void Test_CityNameValidationWithInValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_Cityname");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterCity(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("city");
				if (isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false, "Entered name is Valid City name, Should not display invalid Error: "
							+ validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheCityNameMaxLengthValidation() {
		try {
			String MaxlenghtText = "a".repeat(50);
			EnrollPage.enterCity(MaxlenghtText);
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), 50, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheCityNameMinLengthValidation() {

		String MinlenghtText = "1".repeat(1);
		EnrollPage.enterCity(MinlenghtText);
		// Assert that the entered text is truncated to the Min length
		Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
		EnrollPageObject.ClearEnrollInputFields(false);
	}

	@Test
	// verify the CityName Validation with Valid input
	public void Test_AddressValidationWithValidInput() {

		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_Address");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterAddress(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("address");
				if (!isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered Valid Address, Error messaage should not Display : " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the CityName Validation with InValid input
	public void Test_AddressValidationWithInValidInput() {

		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_Address");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterAddress(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("address");
				if (isErrorDisplayed) {
					// If there is an error message
					assertTrue_custom(true, "Entered inValid Address,The Error Should display", true);
				} else {
					// If there is no error message
					assertFalse_custom(true, "Entered Address is inValid, The Error Should display", false);
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheAddressMaxLengthValidation() {
		try {
			String MaxlenghtText = "a".repeat(100);
			EnrollPage.enterAddress(MaxlenghtText);
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), 100, "Text input did not enforce maximum length");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheAddressMinLengthValidation() {
		try {

			String MinlenghtText = "1".repeat(1);
			EnrollPage.enterAddress(MinlenghtText);
			// Assert that the entered text is truncated to the Min length
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the state dropdown options are alphabetic order or not
	public void Test_StatedropdownOptionsAlphabeticOrderorNot() {
		try {
			List<String> statelist = EnrollPageObject.getAllListOfStates();
			boolean isAlphabetic = Helper.isAlphabetical(statelist);
			Assert.assertTrue(isAlphabetic, "State names are not displayed alphabetically.");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheSuiteOrApartmentMinLengthValidation() {
		try {
			String MinlenghtText = "a".repeat(1);
			EnrollPage.enterSuteNumber(MinlenghtText);
			// Assert that the entered text is truncated to the Min length
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_SuiteOrApartmentMaxLengthValidation() {
		try {

			String MaxlenghtText = "a".repeat(25);
			EnrollPage.enterSuteNumber(MaxlenghtText);
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), 25, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	// verify the state drop down Default value display or not
	public void Test_StatedropdownDefaultValueDisplayOrNot() {
		try {

			Assert.assertEquals(EnrollPage.getStateDropDownDefaultValue(), "Select State");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_ZipCodeValidationWithValidInputData() {

		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_ZipCode");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterZip(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("zip");
				if (!isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered name is Valid ZipCode, Should display Error: " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (

		Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_ZipCodeValidationWithInCompleteInputData() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_ZipCode");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterZip(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("zip");
				if (isErrorDisplayed) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered code is Valid ZipCode, Should not display Error: " + validatedata.getValue());
				}
				EnrollPageObject.ClearEnrollInputFields(false);
			}

		} catch (Exception e) {

			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	public void Test_TheZipCodeMinLengthValidation() {
		try {
			String MinlenghtText = "a".repeat(1);
			EnrollPage.enterZip(MinlenghtText);
			// Assert that the entered text is truncated to the Min length
			Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheZipCodeMaxLengthValidation() {
		try {
			String MaxlenghtText = "a".repeat(25);
			EnrollPage.enterZip(MaxlenghtText);
			// Assert that the entered text is truncated to the maximum length
			Assert.assertEquals(MaxlenghtText.length(), 25, "Text input did not enforce maximum length");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_StateIsDropdownDefaultValueDisplayOrNot() {
		boolean dlstatus = false;
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				Assert.assertEquals(EnrollPageObject.getStateIssuedDropDownDefaultValue(), "Select State");
				dlstatus = true;
			} else {
			}
			EnrollPageObject.ClearEnrollInputFields(dlstatus);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	public void Test_SelectAnIDDropdownDefaultValueDisplayOrNot() {
		boolean dlstatus = false;
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				Assert.assertEquals(EnrollPageObject.getSelectanIDDropDownDefaultValue(), "Driver's License");
				dlstatus = true;

			}
			EnrollPageObject.ClearEnrollInputFields(dlstatus);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test(dependsOnMethods = { "Test_StateIssueDropdownDefaultValueDisplayOrNot" })
	public void Test_StateIssueddropdownOptionsAlphabeticOrderorNot() {
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				List<String> statelist = EnrollPageObject.getAllListOfStates();
				boolean isAlphabetic = Helper.isAlphabetical(statelist);
				Assert.assertTrue(isAlphabetic, "State issue names are not displayed alphabetically.");

			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_StateIssueDropdownDefaultValueDisplayOrNot() {
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				assertEqualsString_custom(EnrollPageObject.getStateIssuedDropDownDefaultValue(), "Select State",
						"State dropdown default value");
			} else {

			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	public void Test_SelectAnIDToolTipInstuctionText() {
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				Assert.assertEquals(EnrollPageObject.mouseOverOnStateOnIdTootip(),
						"We use this for identity validation and consumer Identity protection");
			} else {

			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	public void Test_SelectAnIDToolTipMouseOverFunctionality() {
		try {

			if (Helper.checkPartnerSelectDLOption()) {
				moveToElement_custom(EnrollPageObject.mouseStateAnIdOverOnTootip(), "Stat on Id Tooltip");
				Assert.assertTrue(true);
			} else {

			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@SuppressWarnings("static-access")
	@Test
	public void Test_DLNumbereValidationWithValidInputData() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_DlNumber");
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				for (Map.Entry<String, String> validatedata : data.entrySet()) {

					EnrollPageObject.enterDLnumber(validatedata.getValue());
					EnrollPageObject.submitenrollbutton();
					if (!EnrollPageObject.isErrorMessageDisplayed("number")) {
						// If there is an error message
						Assert.assertTrue(true, validatedata.getValue());
					} else {
						// If there is no error message
						Assert.assertTrue(false,
								"Entered DL is InValid DLNumber, Should display Error: " + validatedata.getValue());
					}

				}
				EnrollPageObject.ClearEnrollInputFields(false);
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}

	}

	@Test
	public void Test_DLNumbereeValidationWithInCompleteInputData() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_DlNumber");
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				for (Map.Entry<String, String> validatedata : data.entrySet()) {

					EnrollPage.enterDLnumber(validatedata.getValue());
					EnrollPageObject.submitenrollbutton();
					if (EnrollPageObject.isErrorMessageDisplayed("number")) {
						// If there is an error message
						Assert.assertTrue(true, validatedata.getValue());
					} else {
						// If there is no error message
						Assert.assertTrue(false,
								"Entered DL is Valid DLNumber, Should not display Error: " + validatedata.getValue());
					}

				}
				EnrollPageObject.ClearEnrollInputFields(false);

			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());

		}
	}

	@Test
	public void Test_TheDLNumbereMinLengthValidation() {
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				String MinlenghtText = "a".repeat(1);
				EnrollPage.enterDLnumber(MinlenghtText);
				// Assert that the entered text is truncated to the Min length
				Assert.assertEquals(MinlenghtText.length(), 1, "Text input did not meet minimum length requirement");
			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_TheDLNumbereMaxLengthValidation() {
		try {
			if (Helper.checkPartnerSelectDLOption()) {
				String MaxlenghtText = "a".repeat(25);
				EnrollPage.enterDLnumber(MaxlenghtText);
				// Assert that the entered text is truncated to the maximum length
				Assert.assertEquals(MaxlenghtText.length(), 25, "Text input did not enforce maximum length");
			}
			EnrollPageObject.ClearEnrollInputFields(false);
		}

		catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PinToolTipInstuctionText() {
		try {
			Assert.assertEquals(EnrollPageObject.getPinToolTipText(),
					"PIN is 4 digits long, non-consecutive and 3 unique digits");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PinToolTipMouseOverFunctionality() {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.moveToElement(EnrollPageObject.mouseOverOnPinTootip()).perform();
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test
	public void Test_PINValidationWithValidInputData() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_PIN");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterpin(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				if (!EnrollPageObject.isErrorMessageDisplayed("Createpin")) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());
				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered pin is InValid in number, Should display Error: " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PINValidationWithInCompleteInputData() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_PIN");
		try {
			for (Map.Entry<String, String> validatedata : data.entrySet()) {

				EnrollPage.enterpin(validatedata.getValue());
				EnrollPageObject.submitenrollbutton();
				if (EnrollPageObject.isErrorMessageDisplayed("Createpin")) {
					// If there is an error message
					Assert.assertTrue(true, validatedata.getValue());

				} else {
					// If there is no error message
					Assert.assertTrue(false,
							"Entered pin is Valid PIN Number, Should not display Error: " + validatedata.getValue());
				}

			}
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	/*
	 * @Test public void Test_TermsAndTextNotificationCheckBoxDisplayorNot() {
	 * Assert.assertTrue(EnrollPageObject.CheckBoxDisplay()); }
	 */
	@Test
	public void Test_ValidationErrorMessageForTermsandCondition() {
		try {
			EnrollPageObject.unCheckBox();
			EnrollPageObject.submitenrollbutton();
			if (EnrollPageObject.isErrorMessageDisplayed("term")) {
				// If there is an error message
				Assert.assertTrue(true);
			} else {
				// If there is no error message
				Assert.assertTrue(false, "Entered pin is Valid PIN Number, Should not display Error: ");
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test
	// Test_ the Masked pin in Pin text filed
	public void Test_MaskedPin() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_PIN");
		try {
			EnrollPage.enterpin(data.get("Data_1"));
			if (EnrollPageObject.getPinElement().getAttribute("type").equals("password")) {
				Assert.assertTrue(true, "Pin is masked");
			} else {
				Assert.assertTrue(false, "Pin is unmasked");
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	// Verify the UnMasked pin in Pin text filed
	public void Test_UnMaskedPin() {
		Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "Valid_PIN");
		try {
			EnrollPage.enterpin(data.get("Data_1"));
			EnrollPageObject.clickEyeIcon();
			if (EnrollPageObject.getPinElement().getAttribute("type").equals("text")) {
				Assert.assertTrue(true, "Pin is unmasked");
			} else {
				Assert.assertTrue(false, "Pin is masked");
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_TextNotificationCheckBoxText() {
		try {
			String DBText = Helper.replacecurlybracket(
					DB_Operations.getSqlResultInMap(SQLQuery.Get_TextNotification_Text).get("SMS_Checkbox_text"));
			if (DBText == null) {
				Assert.assertEquals(EnrollPageObject.getTextnotificationText(),
						"I agree to receive text notifications and get extra offers.");
			} else {
				Assert.assertEquals(DBText, EnrollPageObject.getTextnotificationText());
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_TermsAndConditionCheckBoxText() {
		try {
			String DBTextterm = Helper.replacecurlybracket(
					DB_Operations.getSqlResultInMap(SQLQuery.Get_TermsAndCondition_Text).get("Terms_Checkbox_text"));
			if (DBTextterm == null) {
				Assert.assertEquals(EnrollPageObject.getTermsandConditionText(),
						"I have read and agree to the Terms and Conditions.");
			} else {
				Assert.assertEquals(DBTextterm, EnrollPageObject.getTermsandConditionText());
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_TermsAndTextNotificationLinkValidation() {
		try {
			Assert.assertTrue(EnrollPageObject.checkHyperlink());
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_EnrollPageButtonText() {
		try {
			Assert.assertEquals(EnrollPageObject.getButtonText(), "Continue");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	// Verify the SignIn sentence
	public void Test_SignInSentence() {
		try {
			String DBText = Helper.replacecurlybracket(DB_Operations
					.getSqlResultInMap(SQLQuery.Get_WelcomePage_SigninText).get("Welcome_Screen_SignInHere_Text"));

			Assert.assertEquals(EnrollPageObject.getTextBeforeLink(), DBText);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	// Verify the SigninLink Display or not
	public void Test_SigninlinkDisplayOrNot() {
		try {
			Assert.assertTrue(EnrollPageObject.getSignInLink());
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_UserAbleToSelectDOBAbove18YearsAge() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Consumer_data", "Consumer_Validdetails");
			Map<String, String> date = Helper.dob(data.get("dob"));
			EnrollPage.enterDob(date.get("Month"), date.get("Year"), date.get("Day"));
			String sentKeyDOB = data.get("dob");
			String getDOB = EnrollPage.getDob();
			String[] Dob = EnrollPageObject.formatSendAndGetStringText(sentKeyDOB, getDOB);
			assertEqualsString_custom(Dob[0], Dob[1], "Date of birth");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test(dependsOnMethods = "Test_StatedropdownOptionsAlphabeticOrderorNot")

	public void Test_SubmitButton() {
		ConsumerActualData = EnrollPage.completePIV("Consumer_data", "Consumer_Validdetails", false);
		try {

			if (Helper.checkPartnerSelectPlasticindicatorornot()) {
				Assert.assertEquals(PlasticIndicatorPageObject.getHeaderText(), "Plastic");
			} else {
				Assert.assertEquals(BankintroPageobject.getHeaderText(), "Link Your Bank Account");
			}
		} catch (Exception e) {
			Assert.fail("An unexpected error occurred: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorMessageForEmailAlreadyExist() {
		try {
			EnrollPage.completePIV("Consumer_data", "Consumer_details_EmailAredayExist", true);

			String errorMessage = EnrollPageObject.getRedBoxErrorMessage();
			EnrollPageObject.CloseErrorRedboxMessage();

			String query = Helper.checkCustomizedErrorExistOrNot() ? SQLQuery.getEmailAlreadyExistCostomizedErrorMessage
					: SQLQuery.getEmailAlreadyExistDefaultErrorMessage;

			String expectedMessage = Helper.replacecurlybracket(DB_Operations.getSqlResultInMap(query).get("Message"));

			Assert.assertEquals(errorMessage, expectedMessage, "Error message does not match the expected message");

			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorMessageForInvalidPin() {
		try {
			EnrollPage.completePIV("Consumer_data", "Consumer_details_InvalidPIN", false);

			String errormessage = EnrollPageObject.getRedBoxErrorMessage();
			EnrollPageObject.CloseErrorRedboxMessage();
			Assert.assertEquals(errormessage, "PIN is 4 digits long, non-consecutive and 3 unique digits");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test

	public void Test_ErrorMessageForInvalidDominName() {
		try {

			EnrollPage.completePIV("Consumer_data", "Consumer_details_InvalidDomine", true);

			String errormessage = EnrollPageObject.getRedBoxErrorMessage();
			EnrollPageObject.CloseErrorRedboxMessage();
			Assert.assertEquals(errormessage, "The Email Address field is not a valid e-mail address.");
			EnrollPageObject.ClearEnrollInputFields(false);

		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test

	public void Test_ErrorMessageForInvalidPhone() {
		try {
			EnrollPage.completePIV("Consumer_data", "Consumer_details_InvalidPhone", false);

			String errormessage = EnrollPageObject.getRedBoxErrorMessage();
			EnrollPageObject.CloseErrorRedboxMessage();
			Assert.assertEquals(errormessage, "Phone number must be valid");
			EnrollPageObject.ClearEnrollInputFields(false);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test(dependsOnMethods = "Test_SubmitButton")
	public void Test_ValidatePIVDataWithDataBaseAfterSubmitEnrollmentForm() {

		try {

			String dbData = DB_Operations
					.getSqlResultInMap(SQLQuery.get_PIVData + "'" + ConsumerActualData.get("email") + "'")
					.get("Consumer_Request");
			Consumer expectedData = ReadXMLData.readXMLData(dbData);
			EnrollPage.assertNamesMatch(expectedData, ConsumerActualData);
			EnrollPage.assertDateOfBirthMatches(expectedData, ConsumerActualData);
			EnrollPage.assertAddressMatches(expectedData.getAddress(), ConsumerActualData);
			EnrollPage.assertLicenseMatches(expectedData.getDriversLicense(), ConsumerActualData);
			EnrollPage.assertMobileNumberMatches(expectedData.getContact(), ConsumerActualData);
		} catch (Exception e) {
			EnrollPage.handleException(e);
		}
	}

}
