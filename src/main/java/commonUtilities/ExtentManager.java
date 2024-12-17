package commonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentSparkReporter spark;

	public static ExtentReports getExtentReports() {
		if (extent == null) {
			spark = new ExtentSparkReporter("target/ExtentReport.html"); // Report Path
			spark.config().setReportName("Automation Test Report");
			spark.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Mohd Osama");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}

	public static ExtentTest createTest(String testName, String description) {
		test = getExtentReports().createTest(testName, description);
		return test;
	}

	public static void flushReports() {
		if (extent != null) {
			extent.flush();
		}
	}

}
