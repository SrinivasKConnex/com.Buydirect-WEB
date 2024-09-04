package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ISuiteListener;
import org.testng.ISuite;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.ExtentReportNG;

/**
 * @author: Srinivas
 */
public class ListenersImplementation implements ITestListener,ISuiteListener {

	static ExtentReports report;
	ExtentTest test;

	 public static ExtentReports getReport() {
	        return report;
	    }
	@Override
	public void onTestStart(ITestResult result) {
		
		
		/*
		 * // before each test case test =
		 * report.createTest(result.getMethod().getMethodName());
		 * ExtentFactory.getInstance().setExtent(test);
		 */
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.PASS,
				"Test Case: " + result.getMethod().getMethodName() + " is Passed.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.FAIL,
				"Test Case: " + result.getMethod().getMethodName() + " is Failed.");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

		// add screenshot for failed test.
		File src = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String screenshotPath = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + actualDate + ".jpeg";
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath,
				"Test case failure screenshot");
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP,
				"Test Case: " + result.getMethod().getMethodName() + " is skipped.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onStart(ISuite suite) {
		try {
			report = ExtentReportNG.setupExtentReport(suite);
			test = report.createTest(suite.getName());
			ExtentFactory.getInstance().setExtent(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ISuite context) {
		// close extent
		if (report != null) {
			ExtentFactory.getInstance().removeExtentObject();
			report.flush();
		}
	}
	public void onFinish(ITestResult result) {
		// close extent
		if (report != null) {
			System.out.println("INFO:Test Ended On Page:"+result.getClass().getName());
			ExtentFactory.getInstance().removeExtentObject();
			report.flush();
		}
	}
}
