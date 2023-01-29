package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.framework.utilities.BrowserFactory;
import com.framework.utilities.ConfigDataProvider;
import com.framework.utilities.ExcelDataProvider;
import com.framework.utilities.ReusableUtilities;

public class BaseClass {

	public WebDriver driver;
	public ConfigDataProvider config;
	public ExcelDataProvider excel;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Setting up the testdata and config files", true);
		config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
		ExtentSparkReporter extent = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/SwagLabs - "+ReusableUtilities.currentDateTime()+".html");
		report = new ExtentReports();
		report.attachReporter(extent);
		Reporter.log("Settings done", true);
		//Added comment
	}
	
	
	@BeforeClass
	public void setup() {
		
		Reporter.log("Launching browser - "+config.getBrowser()+" and and URL - "+config.getTestingUrl(), true);
//		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getTestingUrl());
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getTestingUrl());

	}

	@AfterClass
	public void tearDown() {
		Reporter.log("Closing browser", true);
		BrowserFactory.quitBrowser(driver);

	}

	@AfterMethod
	public void ScreenshotCapture(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

//			ReusableUtilities.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(ReusableUtilities.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(ReusableUtilities.captureScreenshot(driver)).build());
//			ReusableUtilities.captureScreenshot(driver);
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(ReusableUtilities.captureScreenshot(driver)).build());
//			ReusableUtilities.captureScreenshot(driver);
		}
		
		Reporter.log("report generated successfully", true);
		report.flush();
	}

}
