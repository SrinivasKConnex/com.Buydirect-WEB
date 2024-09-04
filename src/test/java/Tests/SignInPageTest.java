package Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.ReadExcelData;
import reusableComponents.SQLQuery;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)
public class SignInPageTest extends TestBase {

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the SignIn Page.\n");
		parentTest = ListenersImplementation.getReport().createTest("SignIn Page Test Started");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach SignIn Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		WelcomePageObject.clickSignIn();
	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the SignIn page has been completed.");
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

			assertEqualsString_custom(DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL"),
					AbstractPageObject.getLogoUrl(), "Test partner logo in Signin Page");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_HeaderLogo: " + e.getMessage());
		}
	}

	@Test
	public void Test_HeaderImageisBroken() {
		try {
			if (AbstractPageObject.getLogoBroken().getAttribute("naturalWidth").equals("0")) {
				Assert.fail("Header image is broken");
			} else {
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_HeaderImageisBroken: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerTenderName() {
		try {
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query).get("Tender_Name_Header"),
					AbstractPageObject.getTenderName(), "Test Partner tender name in signin Page");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_footerTextWithPartnerPhoneNumberandPartnerName() {
		try {
			String footertext = AbstractPageObject.getFooterText();
			HashMap<String, String> partnedetails = DB_Operations
					.getSqlResultInMap(SQLQuery.Get_Partner_namephonenumber_query);
			assertEqualsString_custom(footertext,
					"Please contact the " + partnedetails.get("Product_Name") + " Support Team at "
							+ partnedetails.get("partner_contact_number") + " with any questions.",
					"Test Footer Text with partner phone number and name:");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_footerTextWithPartnerPhoneNumberandPartnerName: " + e.getMessage());
		}
	}

	@Test
	public void Test_theEmailtextfiledLabel() {
		try {
			assertEqualsString_custom(SignInpageObject.getEmaillabelText(), "Email", "Email text filed Label");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_theEmailtextfiledLabel: " + e.getMessage());
		}
	}

	@Test
	public void Test_thePintextfiledLabel() {
		try {
			assertEqualsString_custom(SignInpageObject.getPinlabelText(), "PIN", "Email text filed Label");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_thePintextfiledLabel: " + e.getMessage());
		}
	}

	@Test
	public void Test_theEmailTextFiledPlaceHolder() {
		try {
			assertEqualsString_custom(SignInpageObject.getEmailPlaceHolderlText(), "Email",
					"Email Text Filed PlaceHolder");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_theEmailTextFiledPlaceHolder: " + e.getMessage());
		}
	}

	@Test
	public void Test_thePinTextFiledPlaceHolder() {
		try {
			assertEqualsString_custom(SignInpageObject.getPinPlaceHolderlText(), "PIN", "PIN Text Filed PlaceHolder");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_thePinTextFiledPlaceHolder: " + e.getMessage());
		}
	}

	@Test
	public void Test_EnrollLinkText() {
		try {
			assertEqualsString_custom(SignInpageObject.enrollLinkText(), "Enroll", "Enroll Page Link Text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_EnrollLinkText: " + e.getMessage());
		}
	}

	@Test
	public void Test_ResetMyPinLinkText() {
		try {
			assertEqualsString_custom(SignInpageObject.forgetLinkText(), "Reset My PIN", "Reset My Pin Link Text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ResetMyPinLinkText: " + e.getMessage());
		}
	}

	@Test
	public void Test_TextBeforeEnrollLink() {
		try {
			assertEqualsString_custom(SignInpageObject.getTextBeforeLink(), "Don't have an Account?",
					"TextBeforeEnrollLink");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_TextBeforeEnrollLink: " + e.getMessage());
		}
	}

	@Test
	public void Test_SignInPageHeaderText() {
		try {
			assertEqualsString_custom(SignInpageObject.getHeaderText(), "Sign In", "Signin Page Header Text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_SignInPageHeaderText: " + e.getMessage());
		}
	}

	@Test
	public void Test_RequiredErrorInEmailandPinField() {
		try {
			SignInpageObject.consumer_login("", "");
			assertEqualsString_custom(SignInpageObject.getRequiredEmailErrorText(), "Required",
					"Required Error Email  Field");
			assertEqualsString_custom(SignInpageObject.getRequiredPinErrorText(), "Required",
					"Required Error Pin Field");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_RequiredErrorInEmailandPinField: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorforEmailDoesNotExist() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_1");
			SignInpageObject.consumer_login(data.get("Consumer_email"), data.get("Consumer_pin"));
			SignInpageObject.waitFor_ErrorMessageAppear();
			// Validate Error Message
			assertEqualsString_custom(SignInpageObject.getinvalidErrrorText().getText(),
					"The email address or PIN provided is incorrect. Please try again.",
					"Error message for email does not Exist");

			SignInpageObject.closeErrorMessage();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ErrorforEmailDoesNotExist: " + e.getMessage());
		}
	}

	@Test
	public void Test_MaskedPin() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_6");
			// Enter the Invalid Email Id
			SignInpageObject.enterLoginEmail(data.get("Consumer_email"));
			// Enter Valid pin
			SignInpageObject.enterLoginPin(data.get("Consumer_pin"));
			// Click on eye iconF
			// Helper.implicitwait();
			if (SignInpageObject.getPinElement().getAttribute("type").equals("password")) {
				Assert.assertTrue(true, "Pin is masked");
			} else {
				Assert.assertTrue(false, "Pin is unmasked");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_MaskedPin: " + e.getMessage());
		}
	}

	@Test
	public void Test_UnMaskedPin() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_6");
			// Enter the Invalid Email Id
			SignInpageObject.enterLoginEmail(data.get("Consumer_email"));
			// Enter Valid pin
			SignInpageObject.enterLoginPin(data.get("Consumer_pin"));
			// Click on eye icon

			SignInpageObject.clickEyeIcon();
			if (SignInpageObject.getPinElement().getAttribute("type").equals("text")) {
				Assert.assertTrue(true, "Pin is unmasked");
			} else {
				Assert.assertTrue(false, "Pin is masked");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_UnMaskedPin: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorforIncorrectPin() {
		try {

			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_2");
			SignInpageObject.consumer_login(data.get("Consumer_email"), data.get("Consumer_pin"));
			SignInpageObject.waitFor_ErrorMessageAppear();
			Assert.assertEquals(SignInpageObject.getinvalidErrrorText().getText(), "Please enter correct PIN.",
					"Failed");
			SignInpageObject.closeErrorMessage();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ErrorforIncorrectPin: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorForIncompletePin() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_3");
			SignInpageObject.consumer_login(data.get("Consumer_email"), data.get("Consumer_pin"));
			Assert.assertEquals(SignInpageObject.getRequiredPinErrorText(), "Your PIN must be 4 digits long", "Failed");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ErrorForIncompletePin: " + e.getMessage());
		}
	}

	@Test
	public void Test_ErrorForInValidEmail() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Login_crede", "Data_4");
			SignInpageObject.consumer_login(data.get("Consumer_email"), data.get("Consumer_pin"));
			Assert.assertEquals(SignInpageObject.getRequiredEmailErrorText(), "Invalid", "Failed");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ErrorForInValidEmail: " + e.getMessage());
		}
	}

	@Test
	public void Test_ButtonName() {
		try {
			String ActualResult = SignInpageObject.getSigninButonText();
			Assert.assertEquals(ActualResult, "Sign In");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_ButtonName: " + e.getMessage());
		}
	}

}
