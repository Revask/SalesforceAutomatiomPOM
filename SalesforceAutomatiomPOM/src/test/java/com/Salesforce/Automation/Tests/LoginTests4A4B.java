package com.Salesforce.Automation.Tests;

import org.openqa.selenium.*;

import SalesforceHomePages.HomePage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import SalesforceLoginPages.*;
import com.Salesforce.Automation.Base.*;
import com.Salesforce.Automation.Utility.*;

public class LoginTests4A4B extends BaseTest 
{
	static SoftAssert sa = new  SoftAssert();
	
	@Test
	public static void salesForceLoginTest4A() throws InterruptedException
	{
		LoginPage loginpg = new LoginPage(driver);
		
		loginpg.forgotPwdLink();
		ForgotPasswordPage fppPg = new ForgotPasswordPage(driver);
		String uname = getLogin();
		report.logTestInfo("Username Entered :" + uname);
		fppPg.enterUname(uname);
		fppPg.clickOnContinue();
		Thread.sleep(3000);
		String actualTitle = getPageTitle();
		String expectedTitle = "Check Your Email | Salesforce";
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
	@Test
	public static void salesForceLoginTest4B() throws InterruptedException
	{
		LoginPage loginpg = new LoginPage(driver);
		loginpg.login("123", "22131");
		InvalidUsernamePwdPage errPg = new InvalidUsernamePwdPage(driver);
		Assert.assertTrue(errPg.foundErrorMessage());
		
	}
}
