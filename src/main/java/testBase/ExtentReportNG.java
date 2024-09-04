package testBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reusableComponents.PropertiesOperations;

/**
 * @author: Srinivas
 */
public class ExtentReportNG {

	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();

	public static ExtentReports setupExtentReport(ISuite context) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String reportPath = System.getProperty("user.dir") + "/Reports/TestReport/" + context.getName()
				+ "_ExecutionReport_" + actualDate + ".html";

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);

		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("BuyDrirect-WEB");
		sparkReport.config().setDocumentTitle("BuyDrirect-WEB");

		extent.setSystemInfo("Executed on Environment: ", PropertiesOperations.getPropertyValueByKey("url"));
		extent.setSystemInfo("Executed on Browser: ", PropertiesOperations.getPropertyValueByKey("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Tester", "Srinivas");

		return extent;
	}

	public static void parentTest(String message) {
		parentTest.set(extent.createTest(message));
	}

	public static void childTest(String message) {
		childTest.set(parentTest.get().createNode(message));
	}

	public void testLoggerPass(String message) {
		childTest.get().log(Status.PASS, message);
	}

	public void testLoggerFail(String message) {
		childTest.get().log(Status.FAIL, message);
	}

	public void testLoggerInfo(String message) {
		childTest.get().log(Status.INFO, message);
	}

	public void testLoggerWarning(String message) {
		childTest.get().log(Status.WARNING, message);
	}

	public void testLoggerSkip(String message) {
		childTest.get().log(Status.SKIP, message);
	}

	public void flushReport() {
		extent.flush();

	}

}
