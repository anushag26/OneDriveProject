package com.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import com.qa.pages.SignInPage;
import com.qa.pages.StartPage;
import com.qa.base.TestBase;

public class StartPageTest extends TestBase{

	StartPage startpage;
	SignInPage signinpage;
	
	public StartPageTest()
	{
		super();
	}

	@BeforeMethod
	public void setup()
	{
		initialization();
		startpage=new StartPage();
		
	}
	@Test
	public void validateStartPageTitle()
	{
		startpage.pageTitle();
	}
	
	
	@Test
	public void validateSignInButton() throws Exception 
	{
		startpage.signInButton();	
	}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
