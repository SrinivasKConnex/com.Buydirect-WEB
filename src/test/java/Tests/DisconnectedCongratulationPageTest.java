package Tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;

import org.testng.annotations.Test;

import reusableComponents.DB_Operations;
import reusableComponents.SQLQuery;
import testBase.TestBase;

public class DisconnectedCongratulationPageTest extends TestBase {

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
