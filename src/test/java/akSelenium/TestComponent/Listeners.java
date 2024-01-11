package akSelenium.TestComponent;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import akSelenium.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	  }
	@Override
	public void onTestSuccess(ITestResult result) {
	    test.log(Status.PASS, "Test passed successfully");
	  }
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
	
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String FilePath = null;
		try {
			FilePath = takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
	  }
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test passed successfully");
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
	public void onStart(ITestContext context) {
	    // not implemented
	  }
	@Override
	public void onFinish(ITestContext context) {
	   extent.flush();
	  }

}
