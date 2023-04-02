package com.Salesforce.Automation.Tests;

import org.openqa.selenium.*;
import com.Salesforce.Automation.Base.BaseTest;
import SalesforceHomePages.HomePage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import SalesforceLoginPages.*;
import com.Salesforce.Automation.Base.*;
import com.Salesforce.Automation.Utility.*;
@Listeners  (com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class LoginTests extends BaseTest
{
	
@Test
	public static void salesForceLoginTest01()
{
	report.logTestInfo("Login Tests");
	LoginPage loginpg = new LoginPage(driver);
	String uname = getLogin();
	report.logTestInfo("Username Entered :" + uname);
	String pwd = " ";
	report.logTestInfo("Empty Password field Entered :");
	loginpg.login(uname,pwd);
	Assert.assertTrue(loginpg.chkIfPwdEmpty());
	PwdErrorMsgLoginPage err = new PwdErrorMsgLoginPage(driver);
	Assert.assertTrue(err.foundErrorMessage());
	
}
	
	@Test
public static void salesForceLoginTest02() throws InterruptedException
{
	LoginPage loginpg = new LoginPage(driver);
	String uname = getLogin();
	report.logTestInfo("Username Entered :" + uname);
	String pwd = getPwd();
	report.logTestInfo("Password Entered "+pwd);
	loginpg.login(uname,pwd);
	String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
	report.logTestInfo("Expected Title : " + expectedTitle);
	String actualTitle = getPageTitle();
	report.logTestInfo("Actual Title : " + expectedTitle);
	boolean match = actualTitle.equals(expectedTitle);
	Assert.assertTrue(match);
	
}
	@Test
	public static void salesForceLoginTest03() throws Exception
	{
		LoginPage loginpg = new LoginPage(driver);
		String uname = getLogin();
		report.logTestInfo("Username Entered :" + uname);
		String pwd = getPwd();
		report.logTestInfo("Password Entered "+pwd);
		loginpg.enterUsername(uname);
		Thread.sleep(1000);
		loginpg.enterPwd(pwd);
		Thread.sleep(1000);
		loginpg.selectRememberMeChkbox();
		Thread.sleep(1000);
		loginpg.clickLogin();
		Thread.sleep(4000);
		HomePage homepg = new HomePage(driver);
		homepg.logout();
		Thread.sleep(3000);
		Assert.assertTrue(loginpg.chkIfUnameEmpty());
		Assert.assertTrue(loginpg.chkIfRememberUnameSelected());
	}
//END of class loginTest
	}
	
