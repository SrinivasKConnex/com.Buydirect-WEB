package testBase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pageObjects.EnrollPage;
import reusableComponents.ActionEngine;
import reusableComponents.DeleteOldData;
import reusableComponents.Helper;
import reusableComponents.PropertiesOperations;

/**
 * @author: Srinivas
 */

public class TestBase extends ActionEngine {

	protected static WebDriver driver = null;
	static ExtentReports report;
	public static String partnerid;
	protected ExtentTest parentTest, chaildTest, test, chaildTest1;
	protected static final Logger logger = Logger.getLogger(EnrollPage.class.getName());
	

	@BeforeSuite
	public void deleteOldExtendedReportData() {
		int day = Integer.parseInt(PropertiesOperations.getPropertyValueByKey("deleteOldDatabyDay"));
		DeleteOldData.DeleteOldExtendedData(System.getProperty("user.dir") + "\\Reports\\Screenshots", day);
		DeleteOldData.DeleteOldExtendedData(System.getProperty("user.dir") + "\\Reports\\TestReports", day);
		ExtentFactory.getInstance().setExtent(test);

	}

	@BeforeClass
	public void LaunchWebApplication() {

		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");
		String partnerId = System.getProperty("partner_id") != null ? System.getProperty("partner_id")
				: PropertiesOperations.getPropertyValueByKey("partner_id");

		DriverFactory.getInstance().setDriver(BrowserFactory.createBrowserInstance(browser));
		driver = DriverFactory.getInstance().getDriver();
		if (driver != null) {

			driver.manage().window().maximize();
			driver.navigate().to(url + partnerId);
			partnerid = Helper.encryptString(partnerId);
			ObjectRepo(driver);
		} else {
			throw new IllegalStateException("WebDriver initialization failed");
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			DriverFactory.getInstance().closeDriver();

		}
	}

}
