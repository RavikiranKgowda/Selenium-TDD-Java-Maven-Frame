package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage {
	
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	@FindBy(id = "user-name") 
	WebElement txt_username;
	@FindBy(id = "password") 
	WebElement txt_password;
	@FindBy(id = "login-button") 
	WebElement btn_login;
	
	public void login(String username, String password) {
		
		Reporter.log("Entering username - "+username, true);
		txt_username.sendKeys(username);
		Reporter.log("Entering password - "+password, true);
		txt_password.sendKeys(password);
		Reporter.log("Clicking login button", true);
		btn_login.click();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(driver.getTitle());
		
	}
	

}
