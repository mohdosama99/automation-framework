package commonUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);
	String repName;

	public void onStart(ITestContext testContext) {
		System.out.println("onStart called");
		logger.debug("onStart called for test suite: " + testContext.getName());
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		repName = "Test-report" + timeStamp + ".html";
		//String currentDir = System.getProperty("user.dir");
		sparkReporter = new ExtentSparkReporter("./reports/" + repName);
		sparkReporter.config().setDocumentTitle("UIAutomationProject");
		sparkReporter.config().setReportName("JSoup");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		// extent.setSystemInfo("Application", "");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		logger.debug("ExtentReport setup completed for: " + repName);
	}

	public void onTestSuccess(ITestResult result) {
		logger.debug("onTestSuccess called for: " + result.getName());
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		System.out.println("onTestSuccess called for: " + result.getName());
		logger.info("Test " + result.getName() + " passed successfully.");

	}

	public void onTestFailure(ITestResult result) {
		logger.debug("onTestFailure called for: " + result.getName());
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		logger.error("Test " + result.getName() + " failed with error: " + result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		logger.debug("onTestSkipped called for: " + result.getName());
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		if (result.getThrowable() != null) {
			test.log(Status.SKIP, result.getThrowable().getMessage());
			logger.info("Test " + result.getName() + " skipped with exception: " + result.getThrowable().getMessage());
		} else {
			test.log(Status.SKIP, "Test Skipped without exception.");
			logger.info("Test " + result.getName() + " skipped without exception.");
		}
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		System.out.println("onFinish called");
		logger.debug("onFinish called for test suite: " + testContext.getName());
	}

}
