package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.base.TestBase;

public class SignInPage extends TestBase{

	
	//*[@id="About.default.F.U"]  //input[@class='form-control' and @type='email']

	
	@FindBy(xpath="//iframe[@class='SignIn']") 
	WebElement frame;
	
	@FindBy(xpath="//main//input[@type='email']")
	WebElement username;

	@FindBy(xpath="//input[@type='submit']")
	WebElement nextbutton;
	
	public SignInPage()
	{
		PageFactory.initElements(driver, this);
	}

/*
	public String pageTitle()
	{
		String title=driver.getTitle();
		System.out.println("signIn page title is"+title);
		return title;
	}*/
	
	public PasswordPage enterusername(String uname)
	{
		driver.switchTo().frame(frame);	
		username.sendKeys(uname);
		
		nextbutton.click();
		return new PasswordPage();
	}
	
	
}
