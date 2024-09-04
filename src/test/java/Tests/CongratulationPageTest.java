package Tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pageObjects.CDW_ABA_DDA_Page;
import pageObjects.CDW_MicroDeposit_Page;
import pageObjects.EnrollPage;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.SQLQuery;
import reusableComponents.reusableMethod;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)
public class CongratulationPageTest extends TestBase {
	public static Map<String, String> loginData;
	ExtentTest chaildTest1;

	@BeforeClass
	public void beforeClass() {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the Congratulations page.\n");
		parentTest = ListenersImplementation.getReport().createTest("Congratulation Page Test");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach Congratulation Page");
		ExtentFactory.getInstance().setExtent(chaildTest);

		// Log info before starting PIV
		System.out.println("INFO: Starting Complete PIV process.");
		chaildTest1 = chaildTest.createNode("=>Complete PIV");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		WelcomePageObject.clickGetStartButton();
		loginData = EnrollPage.completePIV("Consumer_data", "Consumer_Validdetails", false);
		reusableMethod.checkPartnerselectedPlasticindicater();
		reusableMethod.checkPartnerselectedBankIntroPage();
		// Log info after completing PIV
		System.out.println("INFO: Complete PIV process done.");

		// Log info before starting CDW ABA/DDA
		System.out.println("INFO: Starting Complete CDW ABA/DDA process.");
		chaildTest1 = chaildTest.createNode("=>Complete CDW ABA/DDA");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		CDW_ABA_DDA_Page.completeCDWPending(false);
		waitElementToAppear_custom(ABADDACompleteMessagePageObject.messageBodyText(),
				"Wait for ABA/DDA complete page to appear");
		// Log info after completing CDW ABA/DDA
		System.out.println("INFO: Complete CDW ABA/DDA process done.");

		// Log info before starting CDW MicroDeposit
		System.out.println("INFO: Starting Complete CDW MicroDeposit process.");
		chaildTest1 = chaildTest.createNode("=>Complete CDW MicroDeposit");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		CDW_MicroDeposit_Page.completeloginAndNavigateToMIcrodepositPage(loginData);
		CDW_MicroDeposit_Page.completeMicroDeposit();
		// Log info after completing CDW MicroDeposit
		System.out.println("INFO: Complete CDW MicroDeposit process done.");

		// Log info before waiting for Congratulation page
		System.out.println("INFO: Waiting for Congratulation Page to appear.");
		chaildTest1 = chaildTest.createNode("=>Wait For Congratulation Page");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		waitElementToAppear_custom(CongratulationPageObject.waitUntilConfirmationPageDisplay(),
				"Wait for Congratulation page to appear");
		// Log info when Congratulation page appears
		System.out.println("INFO: Congratulation Page appeared. Starting Testing..");
	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the Congratulation page has been completed.");
		System.out.println("******************************");

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@Test
	public void Test_CongratulationText() {
		try {
			Assert.assertEquals(CongratulationPageObject.getConfirmationPageHeaderText(), "Confirmation");
			chaildTest.info("Test_CongratulationText Completed");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test_CongratulationText failed: " + e.getMessage());
		}
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
