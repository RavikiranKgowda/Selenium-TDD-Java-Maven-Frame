package com.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ReusableUtilities {
	
	public static String captureScreenshot(WebDriver driver) {
		
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/Screenshots/SwagLabs - "+ReusableUtilities.currentDateTime()+".png";
		try {
			FileHandler.copy(file, new File(path));
		} catch (IOException e) {
			System.out.println("Unable to take screenshot");
			e.printStackTrace();
		}
		
		return path;
	}
	
	public static String currentDateTime() {
		
		DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date date = new Date();
		return df.format(date);
		
	}

}
