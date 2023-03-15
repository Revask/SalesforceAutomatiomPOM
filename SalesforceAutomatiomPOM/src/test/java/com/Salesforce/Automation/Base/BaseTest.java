package com.Salesforce.Automation.Base;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Salesforce.Automation.Utility.GenerateReports;
import com.Salesforce.Automation.Utility.SalesforceAutomationConstants;
import com.Salesforce.Automation.Utility.SalesforceCommonUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static GenerateReports report = GenerateReports.getInstance();
	
	@BeforeMethod
	@Parameters ("BrowserName")
	public static void LaunchBrowser(@Optional("edge") String browser) throws InterruptedException
	{
		getDriver(browser);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		gotoUrl();
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public static void closeDriver()
	{
		driver.close();
	}
	public static void getDriver(String choice)
	{
		choice = choice.toLowerCase();
		
		switch(choice)
		{
			case "chrome" :
			{
				System.out.println("Chrome");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			case  "edge" :
			{
				System.out.println("edge");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			}
			case "firefox" :
			{
				System.out.println("firefox");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		
	}
	
	public static void gotoUrl()
	{
		String url = SalesforceCommonUtilities.getLoginUrl();
		driver.get(url);
	}
	public static String getPageTitle()
	{
		return driver.getTitle();
	}	
	
	public static String getLogin()
	{
		String uname = SalesforceCommonUtilities.getLoginId();
		return uname;
	}
	
	public static String getPwd()
	{
		String pwd = SalesforceCommonUtilities.getLoginpwd();
		return pwd;
	}
	
	public static String getDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddhhmm");
		LocalDateTime now = LocalDateTime.now(); 
		String s = now.format(dtf);
		return s;
	}
	public static void screenShot(String fileName)
	{
		TakesScreenshot scr = (TakesScreenshot) driver;
		File sourceFile = scr.getScreenshotAs(OutputType.FILE);
		String location = SalesforceAutomationConstants.SCREEN_SHOT_PATH + fileName;
		File scrFile = new File(location);
		try {
			FileUtils.copyFile(sourceFile, scrFile);
			report.logTestInfo("Screen shot captured");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static String getScreenshotBase64()
	{
		TakesScreenshot scr = (TakesScreenshot) driver;
		String img = scr.getScreenshotAs(OutputType.BASE64);
		return img;
	}
}//End of class Base Test
		
		
		
