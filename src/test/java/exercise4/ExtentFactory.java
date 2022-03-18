package exercise4;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	public static ExtentReports getInstance() {
		ExtentReports extent;
		String path = "src/test/resources/reports/test.html";
		extent = new ExtentReports(path, false);
		extent.addSystemInfo("Selenium Version", "4.1.2").addSystemInfo("Platform", "Mac");

		return extent;
	}
}