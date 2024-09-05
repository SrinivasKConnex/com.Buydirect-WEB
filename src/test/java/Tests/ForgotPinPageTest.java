package Tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.SQLQuery;
import testBase.ExtentFactory;
import testBase.TestBase;

public class ForgotPinPageTest extends TestBase {
	
	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the ForgotPin Page.\n");
		parentTest = ListenersImplementation.getReport().createTest("ForgotPin Page Test Started");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach ForgotPin Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		WelcomePageObject.clickSignIn();
		SignInpageObject.clickForgetPinLink();
	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the ForgotPin page has been completed.");
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
}
