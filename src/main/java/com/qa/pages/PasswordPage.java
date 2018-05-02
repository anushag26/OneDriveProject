package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class PasswordPage extends TestBase{

		@FindBy(xpath="//input[@name='passwd']")
		WebElement passwd;
		
		
		@FindBy(xpath="//*[@id='idSIButton9']")
		WebElement signbtn;
		
		
		public PasswordPage()
		{
			PageFactory.initElements(driver, this);
		}


		public String pageTitle()
		{
			String title=driver.getTitle();
			System.out.println("password page title is"+title);
			return title;
		}
		
		public Home enterpassword(String pwd)
		{
			passwd.sendKeys(pwd);
			signbtn.click();
			
			return new Home();
		}
	}