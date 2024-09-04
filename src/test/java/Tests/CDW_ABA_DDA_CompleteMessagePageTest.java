package Tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.CDW_ABA_DDA_Page;
import pageObjects.EnrollPage;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.SQLQuery;
import reusableComponents.reusableMethod;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)

public class CDW_ABA_DDA_CompleteMessagePageTest extends TestBase {

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		// logger.info("=>Starting testing on the CDW_ABA_DDA page.\n");
		System.out.println("=>Starting testing on the CDW Pending page.\n");
		parentTest = ListenersImplementation.getReport().createTest("CDW Pending Page Test");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach CDW Pending Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println("INFO: Starting Complete PIV process.");
		chaildTest1 = chaildTest.createNode("=>Complete PIV");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		WelcomePageObject.clickGetStartButton();
		EnrollPage.completePIV("Consumer_data", "Consumer_Validdetails", false);
		reusableMethod.checkPartnerselectedPlasticindicater();
		reusableMethod.checkPartnerselectedBankIntroPage();
		System.out.println("INFO: Complete PIV process done.");
		chaildTest1 = chaildTest.createNode("=>Waiting for CDW aba/DDA Page Appear..");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		CDW_ABA_DDA_Page.completeCDWPending(false);
		System.out.println("INFO: Waiting for CDW_ABA_DDA Page to appear.");
		chaildTest1 = chaildTest.createNode("=>Waiting for CDW aba/DDA Complete Page Appear..");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		waitElementToAppear_custom(ABADDACompleteMessagePageObject.messageBodyText(),
				"Wait ABA/DDA Complete Page appear");
		System.out.println("INFO: Waiting for ABA/DDA Complete Page appear.");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\n=>The test on the CDW_ABA_DDA Complete massage page has been completed.");
		System.out.println("******************************");
	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@Test
	public void Test_CDWPendingPageBodyText() {
		try {
			assertEqualsString_custom(ABADDACompleteMessagePageObject.getmessageBodyText(),
					DB_Operations.getSqlResultInMap(SQLQuery.getCDWPendingBodyText).get("CDW_Pending_Body"),
					"CDW Pending page Body Text");
		} catch (Exception e) {

			Assert.fail("Exception in Test_HeaderLogo: " + e.getMessage());
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
