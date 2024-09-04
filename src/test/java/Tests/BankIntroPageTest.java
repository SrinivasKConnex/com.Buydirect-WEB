package Tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import pageObjects.CDW_ABA_DDA_Page;
import pageObjects.EnrollPage;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.SQLQuery;
import testBase.ExtentFactory;
import testBase.TestBase;

@Listeners(reusableComponents.ListenersImplementation.class)

public class BankIntroPageTest extends TestBase {

	private ExtentTest parentTest, chaildTest, chaildTest1;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the BankIntro page.\n");
		parentTest = ListenersImplementation.getReport().createTest("BankIntro Page Test");
		chaildTest = parentTest.createNode("=>Pre-required Action To Reach BankIntro Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		int status = Helper.checkPartnerSelectBankIntoPage();
		driver.navigate().refresh();
		chaildTest1 = chaildTest.createNode("=>Click on Continue Button on welcome page");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		WelcomePageObject.clickGetStartButton();
		chaildTest1 = chaildTest.createNode("=>Complete PIV");
		ExtentFactory.getInstance().setExtent(chaildTest1);
		EnrollPage.completePIV("Consumer_data", "Consumer_Validdetails", false);
		Helper.checkPartnerselectedPlasticindicater();
		Helper.checkPartnerSelectBankIntoPage();
		Helper.updateBankIntroStatus(status);
		BankintroPageobject.waitUntilBankIntroPageDisplays();
		// parentTest.log(null, "Test Started On Bank intro Page");
	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		chaildTest = parentTest.createNode(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the BankIntro page has been completed.");
		System.out.println("******************************");

	}

	@Test
	public void Test_BankingIntroPageBodyText() {
		try {
			assertEqualsString_custom(BankintroPageobject.getBodyText(),
					DB_Operations.getSqlResultInMap(SQLQuery.get_BankintroPage_BodyText).get("BankingIntroPageBody"),
					"Test Banking IntroPage BodyText");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_BankingIntroPageBodyText: " + e.getMessage());
		}
	}

	@Test
	public void Test_BankingIntroPageHeader() {
		try {

			assertEqualsString_custom(BankintroPageobject.getHeaderText(), "Link Your Bank Account",
					"Test Banking Intro Page Header");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_BankingIntroPageHeader: " + e.getMessage());
		}
	}

	@Test
	public void Test_BankingIntroPageSubTitle() {
		try {
			assertEqualsString_custom(BankintroPageobject.getSubTitleText(), "Connect your account",
					"Test Banking IntroPage SubTitle");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_BankingIntroPageSubTitle: " + e.getMessage());
		}
	}

	@Test
	public void Test_BydefaultIAVOptionShouldSelect() {
		try {
			Assert.assertTrue(BankintroPageobject.isIAVSelected());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_BydefaultIAVOptionShouldSelect: " + e.getMessage());
		}
	}

	@Test
	public void Test_BydefaultCDWOptionShouldNotSelect() {
		try {
			Assert.assertFalse(BankintroPageobject.isCDWSelected());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_BydefaultCDWOptionShouldNotSelect: " + e.getMessage());
		}
	}

	@Test
	public void Test_HeaderLogo() {
		try {
			assertEqualsString_custom(DB_Operations.getSqlResultInMap(SQLQuery.logo_Query).get("Partner_Image_URL"),
					AbstractPageObject.getLogoUrl(), "Test Header Logo ");
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
			Assert.fail("Exception in Test_HeaderImageisBroken: " + e.getMessage());
		}
	}

	@Test
	public void Test_PartnerTenderName() {
		try {
			assertEqualsString_custom(
					DB_Operations.getSqlResultInMap(SQLQuery.Tender_name_Query).get("Tender_Name_Header"),
					AbstractPageObject.getTenderName(), "Test Partner Tender Name");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_PartnerTenderName: " + e.getMessage());
		}
	}

	@Test
	public void Test_PlaidImageDisplayOrNot() {
		try {
			assertTrue_custom(BankintroPageobject.isPlaidImagedisplay(), "Plaid image displayed", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_PlaidImageDisplayOrNot: " + e.getMessage());
		}
	}

	@Test
	public void Test_CDWImageDisplayOrNot() {
		try {
			assertTrue_custom(BankintroPageobject.isCDWImagedisplay(), "Plaid image displayed", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_CDWImageDisplayOrNot: " + e.getMessage());
		}
	}

	@Test
	public void Test_IAVBankintroHeaderLabel() {
		try {
			assertEqualsString_custom(BankintroPageobject.getIAVBankintroHeaderLabel(), "INSTANT (Most common)",
					"Test iav bank intro lebal text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_IAVBankintroHeaderLabel: " + e.getMessage());
		}
	}

	@Test
	public void Test_CDWBankintroHeaderLabel() {
		try {
			assertEqualsString_custom(BankintroPageobject.getCDWBankintroHeaderLabel(), "MANUAL (1-3 business days)",
					"CDW bank intro lebal text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_IAVBankintroHeaderLabel: " + e.getMessage());
		}
	}

	@Test
	public void Test_IAVBankintroBodyText() {
		try {
			assertEqualsString_custom(BankintroPageobject.getIAVBankintrobodytext(),
					"Securely log in and connect your account using our partner, Plaid, to start transacting right away.",
					"IAV bank intro body");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_IAVBankintroBodyText: " + e.getMessage());
		}
	}

	@Test
	public void Test_CDWBankintroBodyText() {
		try {
			assertEqualsString_custom(BankintroPageobject.getCDWBankintrobodytext(),
					"Enter your routing and account numbers to manually connect your account and start transacting within a few days.",
					"CDW bank intro body");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_CDWBankintroBodyText: " + e.getMessage());
		}
	}

	@Test
	public void Test_securityInfoText() {
		try {
			assertEqualsString_custom(BankintroPageobject.getSecurityInfotext(),
					"Your data is protected by 256-bit encryption.", "Security Info Text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_securityInfoText: " + e.getMessage());
		}
	}

	@Test
	public void Test_securityInfoIcon() {
		try {
			assertTrue_custom(BankintroPageobject.getSecurityIcon(), "Security info Icon", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_securityInfoText: " + e.getMessage());
		}
	}

	@Test
	public void Test_BankIntroPageButonText() {
		try {
			assertEqualsString_custom(BankintroPageobject.getBankIntroPageText(), "Continue",
					"Bank into page Button text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_securityInfoText: " + e.getMessage());
		}
	}

	@Test
	public void Test_BankIntorPageFooterText() {
		try {
			HashMap<String, String> Partner_Phone = DB_Operations
					.getSqlResultInMap(SQLQuery.Get_Partner_namephonenumber_query);
			assertEqualsString_custom(BankintroPageobject.getBankIntroPageFooterText(),
					"Questions? Call us at " + Partner_Phone.get("partner_contact_number"),
					"Bank into page footer text");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_securityInfoText: " + e.getMessage());
		}
	}

	// @Test(dependsOnMethods="Test_securityInfoText")
	public void Test_BankIntroPageButtonFunctionality() {
		try {
			BankintroPageobject.clickContinueButton();
			CDW_ABA_DDA_Page.WaitUntilABADDAPageDisplay();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception in Test_securityInfoText: " + e.getMessage());
		}
	}
}
