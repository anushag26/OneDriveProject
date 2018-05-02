package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class StartPage extends TestBase {

	
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement signIn;
	//a[text()='Sign in']
	//*[@id='meControl']/div/a
	
	public StartPage()
	{
		PageFactory.initElements(driver, this);
	}


	public String pageTitle()
	{
		String title=driver.getTitle();
		System.out.println("StartPage title is:"+title);
		return title;
	}
	
	public SignInPage signInButton() throws Exception
	{
		Actions action=new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(signIn).click().build().perform();
		return new SignInPage();
	}
	
	
}
