package report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import selenium.DriverFactory;
import selenium.SeleniumReusable;
import utility.DateUtility;
import utility.PropertiesFileUtility;
import utility.RandomDataGenerator;

public class Report_Extent extends DriverFactory {

	public static ExtentTest test;
	public static ExtentReports report;
	public static String directoryName;
	public static String screenshotFolderPath;
	public static String testCaseResultFolderName;
	public static String reportFolderPath;
	public static int screenshotNumber = 1;
	public static String resultPath = System.getProperty("user.dir") + File.separator + "results";
	public static String latestScreenshotPath = screenshotFolderPath + File.separator + "Screenshot_"
			+ screenshotNumber;

	public static void makeDirectoryForResults(String testCaseId) {
		testCaseResultFolderName = testCaseId + "_" + DateUtility.generateTimeStamp() + "_"
				+ RandomDataGenerator.generateThreeDigitUniqueValue();
		directoryName = resultPath + File.separator + testCaseResultFolderName;
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs(); // Create directories if they don't exist
		}
	}

	public static void initializeReport() {
		screenshotNumber = 1;
		Report_Extent.makeDirectoryForResults(SeleniumReusable.testCaseId);
		Report_Extent.initializeTestEnvironment();
	}

	public static void takeScreenshot() {
	    TakesScreenshot screenshot = (TakesScreenshot)getDriver() ;
	    screenshotFolderPath = "results" + File.separator + testCaseResultFolderName + File.separator + "Screenshots";
	    File screenshotFolder = new File(screenshotFolderPath);
	    if (!screenshotFolder.exists()) {
	        screenshotFolder.mkdirs();
	    }
	    String screenshotFileName = "Screenshot_" + screenshotNumber++ + ".png";
	    File destinationFile = new File(screenshotFolderPath, screenshotFileName);
	    File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
	    try {
	        FileUtils.copyFile(sourceFile, destinationFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    latestScreenshotPath = ".." + File.separator +"Screenshots" + File.separator + screenshotFileName;
	}

	public static void initializeTestEnvironment() {
	    screenshotFolderPath = directoryName + File.separator + "Screenshots";
	    reportFolderPath = directoryName + File.separator + "html_Report";
	    new File(screenshotFolderPath).mkdirs();
	    report = new ExtentReports(reportFolderPath + File.separator + "ExtentReportResults.html", true);
	    test = report.startTest("ExtentDemo");
	}

	public static void logStatusReportAsPassed(String details) {
		takeScreenshot();
		test.log(LogStatus.PASS, details, test.addScreenCapture(latestScreenshotPath));
	}

	public static void logStatusReportAsFailed(String details) {
		takeScreenshot();
		test.log(LogStatus.FAIL, details);
	}

	public static void logStatusReportAsInfo(String details) {
		takeScreenshot();
		test.log(LogStatus.INFO, details, test.addScreenCapture(latestScreenshotPath));
	}

	public static void logStatusReportAsSkip(String details) {
		takeScreenshot();
		test.log(LogStatus.SKIP, details);
	}

	public static void closeTestReport() {
		report.endTest(test);
		report.flush();
		if (report != null) {
			report = null;
			test = null;
		}
	}

	/*
	 * reports.endTest(); test.log(LogStatus.PASS,"Test Passed");
	 * test.log(LogStatus.FAIL,"Test Failed");
	 * test.log(LogStatus.SKIP,"Test Skipped");
	 * test.log(LogStatus.INFO,"Test Info");
	 * 
	 */
}






//screenshotFolderPath = directoryName + File.separator + "Screenshots";
//reportFolderPath = directoryName + File.separator + "html_Report";
//report = new ExtentReports(reportFolderPath + File.separator + "ExtentReportResults.html", true);
//test = report.startTest("ExtentDemo");
//File directory = new File(screenshotFolderPath);
//if (!directory.exists()) {
//	directory.mkdirs();
//}