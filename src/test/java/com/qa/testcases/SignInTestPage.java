package com.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.PasswordPage;
import com.qa.pages.SignInPage;
import com.qa.pages.StartPage;

public class SignInTestPage extends TestBase {

	StartPage startpage;
	SignInPage signinpage;
	PasswordPage passwdpage;
	
	public SignInTestPage()
	{
		super();
	}

	@BeforeMethod
	public void setup() throws Exception
	{
		initialization();
		startpage=new StartPage();
		
		signinpage=startpage.signInButton();
		signinpage=new SignInPage();	
	}
	
	/*@Test
	public void validateUsernameTitle()
	{
		signinpage.pageTitle();
	}*/
	
	@Test
	public void validateUsername()
	{
		//new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		signinpage.enterusername(pro.getProperty("username1"));
		//passwdpage=new PasswordPage();
		
	}
	
	/*@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
*/
}
