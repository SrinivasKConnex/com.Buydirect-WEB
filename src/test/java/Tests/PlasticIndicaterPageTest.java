package Tests;

import static org.testng.Assert.fail;

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

import pageObjects.EnrollPage;
import reusableComponents.SQLQuery;

import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)
public class PlasticIndicaterPageTest extends TestBase {

	public static Map<String, String> email;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the PlasticIndicater Page.\n");
		parentTest = ListenersImplementation.getReport().createTest("Plastic Indicater Page Test");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach PlasticIndicater Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		int status = Helper.checkPartnerSelectPlasticindicator();
		DriverFactory.getInstance().getDriver().navigate().refresh();

		System.out.println("INFO: Starting Complete PIV process.");
		WelcomePageObject.clickGetStartButton();
		email = EnrollPage.completePIV("Consumer_data", "Consumer_Validdetails", false);
		System.out.println("INFO: Complete PIV process done.");
		PlasticIndicatorPageObject.waitPlasticPageAppear();
		Helper.updatePlasticPageStatus(status);
		System.out.println("INFO: PlasticIndicater Page appeared. Starting Testing..");

	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the PlasticIndicater page has been completed.");
		System.out.println("******************************");

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@Test
	public void Test_ByDefaultSelctNoOption() {
		try {
			assertTrue_custom(PlasticIndicatorPageObject.checkNoRadioOptionSelected(), "", true);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PlasticRequestPagetitle() {
		try {
			assertEqualsString_custom(PlasticIndicatorPageObject.getHeaderText(), "Plastic",
					"Test 	PlasticRequestPage title");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PlasticRequestSubtitle() {
		try {
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.Get_plastic_request_subtitle)
							.get("Plastics_Page_Subtitle"),
					PlasticIndicatorPageObject.getPlasticSubtitleText(), "Test Plastic Request Subtitle");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test
	public void Test_PlasticRequestBodyText() {
		try {
			assertEqualsString_custom(
					PlasticIndicatorPageObject.getPlasticBodyText(), DB_Operations
							.getSqlResultInMap(SQLQuery.Get_plastic_request_BodyText).get("plasticRequestBodyText"),
					"Test PlasticRequestBody Text");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}

	}

	@Test
	public void Test_UserAbleToSelectYesOption() {
		try {
			PlasticIndicatorPageObject.selectPlasticRequestYes();
			assertTrue_custom(PlasticIndicatorPageObject.checkYesRadioOptionSelected(),
					"Test User Able To Select Yes Option", true);
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_PlasticRequestButtonText() {
		try {
			assertEqualsString_custom(PlasticIndicatorPageObject.getButtonText(), "Continue",
					"Test PlasticRequest Button Text");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test(dependsOnMethods = "Test_UserAbleToSelectYesOption")
	public void Test_PlasticRequestButtonFunctionality() {
		try {
			PlasticIndicatorPageObject.selectPlasticRequestYes();
			PlasticIndicatorPageObject.clickPlasticRequest();

			assertEqualsString_custom(BankintroPageobject.getHeaderText(), "Link Your Bank Account",
					"Test PlasticRequest Button Functionality");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test(dependsOnMethods = "Test_PlasticRequestButtonFunctionality")
	public void Test_PlasticRequestFlag_Stored_In_Database() {
		try {
			String Consumer_email = EnrollPage.getConsumerEmail(email);
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.check_plastic_indicator_flag + Consumer_email)
							.get("Consumer_CDF_Value"),
					"True", "Test PlasticRequestFlag_Stored_In_Database");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public void Test_HeaderLogo() {
		try {
			assertEqualsString_custom(DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL"),
					AbstractPageObject.getLogoUrl(), "Test Header Logo");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public static void Test_HeaderImageisBroken() {
		try {
			if (AbstractPageObject.getLogoBroken().getAttribute("naturalWidth").equals("0")) {
				Assert.fail();
			} else {
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}

	@Test
	public static void Test_PartnerTenderName() {
		try {
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query).get("Tender_Name_Header"),
					AbstractPageObject.getTenderName(), "Test Partner TenderName");
		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
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
					"Test footer Text With Partner PhoneNumber and PartnerName");

		} catch (Exception e) {
			fail("Exception occurred:" + e.getMessage());
		}
	}
}
