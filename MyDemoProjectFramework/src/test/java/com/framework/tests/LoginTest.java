package com.framework.tests;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.framework.pages.BaseClass;
import com.framework.pages.LoginPage;
import com.framework.utilities.BrowserFactory;
import com.framework.utilities.ExcelDataProvider;

public class LoginTest extends BaseClass {

	@Test(dataProvider = "testData", dataProviderClass = ExcelDataProvider.class)
	public void Testing_loginToMyApplication(Map<String, String> testData) {

		logger = report.createTest("Login test");
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Logging into application");
		loginpage.login(testData.get("Username"), testData.get("Password"));
		logger.pass("Successfully logged in");
	}

}
