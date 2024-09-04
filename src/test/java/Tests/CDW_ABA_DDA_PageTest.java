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

import pageObjects.CDW_ABA_DDA_Page;
import pageObjects.EnrollPage;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.ReadExcelData;
import reusableComponents.SQLQuery;
import reusableComponents.reusableMethod;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)

public class CDW_ABA_DDA_PageTest extends TestBase {

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		// logger.info("=>Starting testing on the CDW_ABA_DDA page.\n");
		System.out.println("=>Starting testing on the CDW_ABA_DDA page.\n");
		parentTest = ListenersImplementation.getReport().createTest("CDW ABA/DDA Intro Page Test");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach CDW_ABA_DDA Page");
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
		CDW_ABA_DDA_Page.WaitUntilABADDAPageDisplay();
		System.out.println("INFO: Waiting for CDW_ABA_DDA Page to appear.");
	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the CDW_ABA_DDA page has been completed.");
		System.out.println("******************************");

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		// logger.info(result.getMethod().getMethodName());
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());
	}

	@Test
	public void Test_HeaderLogo() {
		try {
			assertEqualsString_custom(DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL"),
					AbstractPageObject.getLogoUrl(), "CDW Header Logo");
		} catch (Exception e) {
			Assert.fail("Exception in Test_HeaderLogo: " + e.getMessage());
		}
	}

	@Test
	public void Test_HeaderImageisBroken() {
		try {
			if (AbstractPageObject.getLogoBroken().getAttribute("naturalWidth").equals("0")) {
				Assert.fail();
			} else {
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			Assert.fail("Exception in Test_HeaderImageisBroken: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerTenderName() {
		try {
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query).get("Tender_Name_Header"),
					AbstractPageObject.getTenderName(), "CDW Partner Tender Name");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_footerTextWithPartnerPhoneNumberandPartnerName() {
		try {
			String footertext = AbstractPageObject.getFooterText();
			HashMap<String, String> partnedetails = DB_Operations
					.getSqlResultInMap(SQLQuery.Get_Partner_namephonenumber_query);
			assertEqualsString_custom(footertext, "Please contact the " + partnedetails.get("Product_Name")
					+ " Support Team at " + partnedetails.get("partner_contact_number") + " with any questions.",
					"CDW Footer Text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_footerTextWithPartnerPhoneNumberandPartnerName: " + e.getMessage());
		}
	}

	@Test
	public void Test_CDWHederBodyText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getCDWBodyHeaderText(),
					"Please enter your routing and account numbers so you can make fast and secure purchases right from your app!",
					"CDW Header Body Text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_ABATextBoxLabelText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getAbaTextBoxLabelText(), "Routing Number",
					"ABA Text Box Label text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_DDATextBoxLabelText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getDdaTextBoxLabelText(), "Account Number",
					"DDA Text Box Label text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_ABATextBoxPlaceHolderText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getAbaPlaceHolderText(), "Routing Number",
					"ABA Text Box PlaceHolder text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_DDATextBoxPlaceHolderText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getDDAPlaceHolderText(), "Account Number",
					"DDA Text Box PlaceHolder text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_ImageGuideText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getAboveImageGuideText(),
					"Use the image of the check below to guide you entering your Routing Number and Account Number.",
					"DDA Text Box PlaceHolder text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_CDWSubmitButtonText() {
		try {
			assertEqualsString_custom(CDWpendingPageObject.getButtonText(), "Continue",
					"CDW ABA/DDA Submit Button Text");
		} catch (Exception e) {
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_InstructionTextbelowTheImage() {
		try {
			String Actual = Helper.removenewLineChar(CDWpendingPageObject.getbelowImageGuideText());
			assertEqualsString_custom(Actual, DB_Operations.getSqlResultInMap(SQLQuery.getBankingRequestBodyTextQuery)
					.get("Banking_Request_Body"), "Instruction Text Below The Image");
		} catch (Exception e) {
			Assert.fail("Exception in Test_InstructionTextbelowTheImage: " + e.getMessage());
		}
	}

	@Test
	public void Test_RequiredValidationErrorMessageInRoutingNumber() {
		try {
			CDWpendingPageObject.clickSubmitabadda();
			assertEqualsString_custom(CDWpendingPageObject.getRequiredRoutingtext(), "Required",
					"Routing number Text box Validation message");
		} catch (Exception e) {
			Assert.fail("Exception in routing number Text box Validation message: " + e.getMessage());
		}
	}

	@Test
	public void Test_RequiredValidationErrorMessageInAccountnumber() {
		try {
			CDWpendingPageObject.clickSubmitabadda();
			assertEqualsString_custom(CDWpendingPageObject.getRequiredAccounttext(), "Required",
					"Routing number Text box Validation message");
		} catch (Exception e) {
			Assert.fail("Exception in Test_Required Validation message: " + e.getMessage());
		}
	}

	@Test
	public void Test_RoutingNumberTextBoxValidationWithInValidInput() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("Validation_Data", "InValid_RoutingNumber");
			for (Map.Entry<String, String> validatedata : data.entrySet()) {
				CDWpendingPageObject.enterABA(validatedata.getValue());
				CDWpendingPageObject.clickSubmitabadda();
				boolean isErrorDisplayed = EnrollPageObject.isErrorMessageDisplayed("RoutingNumber");
				String message = isErrorDisplayed
						? "Entered Routing number: " + validatedata.getValue()
								+ " is Invalid, error Message Should display"
						: "Entered routing number is valid,error should not display : " + validatedata.getValue();
				assertTrue_custom(isErrorDisplayed, message, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void Test_RoutingNumberValidationCheckMod10() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("ABA_DDA_Value's", "Not_MOD10_Match");
			CDWpendingPageObject.enterABA(data.get("Routing_number"));
			CDWpendingPageObject.enterDDA(data.get("Account_number"));
			CDWpendingPageObject.clickSubmitabadda();
			CDWpendingPageObject.getRedBoxErrorMessage();
			assertEqualsString_custom(CDWpendingPageObject.getRedBoxErrorMessage(),
					"An Invalid Routing or Account Number has been entered, please try again",
					"RoutingNumber Mod10 Validation");
			CDWpendingPageObject.CloseRedBoxMessage();
		} catch (Exception e) {
			Assert.fail("Exception in Test_Required Validation message: " + e.getMessage());
		}
	}

	@Test
	public void Test_RoutingNumberFieldMaxLength() {
		try {
			String MaxlenghtText = "1".repeat(11);
			CDWpendingPageObject.enterABA(MaxlenghtText);
			String actualValue = CDWpendingPageObject.getRoutingNumber();
			boolean isValidLength = actualValue.length() <= 10;
			assertTrue_custom(isValidLength, "The routing number input field should not accept more than 10 digits.",
					true);

		} catch (Exception e) {
			Assert.fail("Exception in Test Routing NumberField  Max Length Validation message: " + e.getMessage());
		}
	}

	@Test(dependsOnMethods = "Test_footerTextWithPartnerPhoneNumberandPartnerName")
	public void Test_ABADDABlockedandDecline() {
		try {
			Map<String, String> data = ReadExcelData.Getdatafromexcel("ABA_DDA_Value's", "Blocked_ABA/DDA");
			CDWpendingPageObject.enterABA(data.get("Routing_number"));
			CDWpendingPageObject.enterDDA(data.get("Account_number"));
			CDWpendingPageObject.clickSubmitabadda();
			CDWBankingDeclinePageObject.WaitErrorMessage();
			assertEqualsString_custom(CDWBankingDeclinePageObject.getErrorMessage(),
					"Error, deposit and withdrawal process not allowed.", "Test ABA/DDA Declined Page Error Message");

		} catch (Exception e) {
			Assert.fail("Exception in Test Routing NumberField  Max Length Validation message: " + e.getMessage());
		}
	}

}
