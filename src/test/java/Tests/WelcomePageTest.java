package Tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import testBase.TestBase;
import reusableComponents.DB_Operations;
import reusableComponents.Helper;
import reusableComponents.ListenersImplementation;
import reusableComponents.SQLQuery;
import testBase.DriverFactory;
import testBase.ExtentFactory;

@Listeners(reusableComponents.ListenersImplementation.class)
public class WelcomePageTest extends TestBase {
	ExtentTest parentTest, chaildTest;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("******************************");
		System.out.println("=>Starting testing on the Welcome page.\n");
		parentTest = ListenersImplementation.getReport().createTest("Welcome Page Test");
		ExtentFactory.getInstance().setExtent(parentTest);
		String currentUrl = driver.getCurrentUrl();
		chaildTest = parentTest.createNode("=>Welcome Page Test Begin.");
		ExtentFactory.getInstance().setExtent(chaildTest);
		chaildTest.info("Current Page URL:" + currentUrl);

	}

	@AfterClass
	public void afterClass() {

		System.out.println("\n=>The test on the Welcome page has been completed.");
		System.out.println("******************************");

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {

		chaildTest = parentTest.createNode("=>" + result.getMethod().getMethodName() + " in_Welcome Page");
		ExtentFactory.getInstance().setExtent(chaildTest);
		System.out.println(Helper.getcurrentDate() + " INFO: " + result.getMethod().getMethodName());

	}

	@Test
	public void Test_Title() {
		try {
			assertEqualsString_custom(DriverFactory.getInstance().getDriver().getTitle(), "BuyDirect", "Page Title:");
			// ExtentFactory.getInstance().setExtent(chaildTest.log(Status.PASS, "Test
			// Method Verify PageTitle passed"));
		} catch (AssertionError e) {
			fail("Title mismatch: " + e.getMessage());
		}
	}

	@Test
	public void Test_ThemeColor() {
		try {

			Map<String, String> themeColor = DB_Operations.getSqlResultInMap(SQLQuery.Get_Theamcolor);
			assertEqualsString_custom(AbstractPageObject.getTheamColor(), helper.getExpectedColor(themeColor),
					"Theam colour");
		} catch (Exception e) {
			fail("Failed to verify theme color: " + e.getMessage());
		}
	}

	// @Test
	public void Test_ProgressIndicatorOnceURLLaunched() {
		try {
			assertTrue_custom(AbstractPageObject.prosessIndicater().isDisplayed(), "", true);
		} catch (Exception e) {
			fail("Progress indicator not displayed: " + e.getMessage());
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
	public void Test_WelcomePageHeader() {
		try {
			assertEqualsString_custom(WelcomePageObject.getHeaderText(), "Welcome", "Welcome Page Header");
		} catch (Exception e) {
			fail("Failed to verify welcome page header: " + e.getMessage());
		}
	}

	@Test
	public void Test_WelcomeBodyText() {
		try {
			Map<String, String> welcomeBodyText = DB_Operations.getSqlResultInMap(SQLQuery.Welcomebody_Text3_Query);
			assertEqualsString_custom(WelcomePageObject.getWelcomeBodytext(), welcomeBodyText.get("WelcomeMessage"),
					"Welcome Page Body Text");
		} catch (Exception e) {
			fail("Failed to verify welcome body text: " + e.getMessage());
		}
	}

	@Test
	public void Test_WelcomePageButtonName() {
		try {
			assertEqualsString_custom(
					WelcomePageObject.getButtonText(), DB_Operations
							.getSqlResultInMap(SQLQuery.Get_WelcomePage_ButtonText).get("Welcome_Screen_Button_Text"),
					"Welcome Page button text");
		} catch (Exception e) {
			fail("Failed to verify button name: " + e.getMessage());
		}
	}

	@Test
	public void Test_SignInSentence() {
		try {

			String Expected = DB_Operations.getSqlResultInMap(SQLQuery.Get_WelcomePage_SigninText)
					.get("Welcome_Screen_SignInHere_Text").replace("{", "").replace("}", "");

			assertEqualsString_custom(WelcomePageObject.getTextBeforeLink(), Expected, "SignInSentence");
		} catch (Exception e) {
			fail("Failed to verify sign-in sentence: " + e.getMessage());
		}
	}

	@Test
	public void Test_SignInLinkDisplayed() {
		try {
			WelcomePageObject.isSignInLinkDisplay();
		} catch (Exception e) {
			fail("Sign-in link not displayed: " + e.getMessage());
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
