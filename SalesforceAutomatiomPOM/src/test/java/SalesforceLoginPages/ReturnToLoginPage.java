package SalesforceLoginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Salesforce.Automation.Base.*;

public class ReturnToLoginPage extends BasePage{

	
	public ReturnToLoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[contains(text(),'Return to Login')]") WebElement ret;
	
	public void returnToLogin()
	{
		waitExplicit(ret);
		clickOn(ret);
	}
}
