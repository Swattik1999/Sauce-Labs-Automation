package com.tt.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {

	ExtentReports reports = null;
	ExtentTest test = null;
	WebDriver driver = null;
	// Initially assigning the Extent Report methods name...
	String path = "";
	String reportName = "";
	String testName = "";

	public Reporter(String path, String reportName)
	{
		this.path = path;
		this.reportName = reportName;

		reports = new ExtentReports(path + reportName);
	}

	public void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}

	// To start the test...
	public void startTest(String testName)
	{
		this.testName = testName;
		test = reports.startTest(testName);
	}

	public void info(String msg)
	{
		test.log(LogStatus.INFO, msg);
	}

	public void pass(String msg)
	{
		test.log(LogStatus.PASS, msg);
		String screenshotPath = getScreenshot("Pass");
		test.log(LogStatus.PASS, test.addScreenCapture(screenshotPath));
	}

	public void fail(String msg)
	{
		test.log(LogStatus.FAIL, msg);
		String screenshotPath = getScreenshot("Fail");
		test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
	}

	// To stop the test...
	public void stop()
	{
		reports.endTest(test);
	}

	// This method is using to generate all the HTML file after passing all test cases...
	public void flush()
	{
		reports.flush();
	}

	public String getScreenshot(String screenshotName)
	{
		String destination = "";

		try
		{
			String dateName = DateUtil.getCurrentDate("ddMMMyyyy-HH-mm-ss");
			TakesScreenshot sm = (TakesScreenshot) driver;
			File source = sm.getScreenshotAs(OutputType.FILE);
			destination = this.path + "/Screenshots/" + screenshotName +"-"+ dateName + ".png";
			System.out.println(destination);
			File file = new File(destination);
			FileUtils.copyFile(source, file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return destination;
	}
}

