package com.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.PasswordPage;
import com.qa.pages.SignInPage;
import com.qa.pages.StartPage;

public class PasswordTestPage extends TestBase {

		StartPage startpage;
		SignInPage signinpage;
		PasswordPage passwdpage;
		
		public PasswordTestPage()
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
			passwdpage=signinpage.enterusername(pro.getProperty("username1"));
			passwdpage=new PasswordPage();
		}
		
		/*@Test
		public void validateUsernameTitle()
		{
			signinpage.pageTitle();
		}*/
		
		@Test
		public void validatePassword()
		{
			passwdpage.enterpassword(pro.getProperty("password1"));
			
		}
		
}
