package com.qa.testcases;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.Home;

import com.qa.pages.PasswordPage;
import com.qa.pages.SignInPage;
import com.qa.pages.StartPage;
import com.qa.utility.FileUtility;

public class HomeTest extends TestBase {
	static StartPage startpage;
	static SignInPage signinpage;
	static PasswordPage passwdpage;
	static Home home;
	
	public HomeTest()
	{
		super();
	}

	@BeforeClass
	public static void setup() throws Exception
	{
		initialization();
		startpage=new StartPage();
		signinpage=startpage.signInButton();
		
		signinpage=new SignInPage();	
		passwdpage=signinpage.enterusername(pro.getProperty("username1"));
		passwdpage=new PasswordPage();
		home=new Home();
		home=passwdpage.enterpassword(pro.getProperty("password1"));
		home.click_on_document_folder();
	}
	
	
	@Test(priority=1)
	public void Upload_EmptyFile() throws InterruptedException, IOException, AWTException
	{
		System.out.println("*******Testcase 1*******");
		if(! home.check_if_file_available(pro.getProperty("Emptyfile")))
		{
		home.clickbtn();
		Thread.sleep(1000);
		home.uploadFileWithRobot(pro.getProperty("files_path")+pro.getProperty("Emptyfile"));
		Thread.sleep(1000);
		home.empty_file_upload();
		
		home.track_emptyFile_upload_errorMessage();
		System.out.println("validate upload Empty File Test Completed");
		}
	}
	
	
	
	@Test(priority=2)
	public void Upload_ContentFile() 
	{
		System.out.println("*******Testcase 2*******");
		if(!home.check_if_file_available(pro.getProperty("Contentfile")))
		{
			try {
				home.clickbtn();
				Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
				System.out.println(e.getMessage());
				} 
			home.uploadFileWithRobot(pro.getProperty("files_path")+pro.getProperty("Contentfile"));
			home.verify_upload_msg();
			System.out.println("Validate Upload Content file Test completed");
		}

	}	

	
	@Test(priority=3)
	public void validate_MetaData()
	{
		System.out.println("*******Testcase 3*******");
		try {
			home.select_text_file(pro.getProperty("Contentfile"));
			Thread.sleep(2000);
			home.click_on_info_Link();
			home.verifyMetaData(pro.getProperty("Contentfile"));
		} catch (InterruptedException e) {
			
			System.out.println(e.getMessage());
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println("Validate MetaData Test completed");
		}
	}
	
	
	@Test(priority=4)
	public void updateContent() throws Exception 
	{
		System.out.println("*******Testcase 4*******");
		driver.navigate().refresh();
		Thread.sleep(2000);
		home.select_text_file(pro.getProperty("Contentfile"));
		System.out.println("file is selected****");
		Thread.sleep(2000);
		home.click_texteditor();
		home.enter_text_in_texteditor();
		home.click_on_save_button();
		home.verify_save_msg();
		home.click_on_version_history_link();
		//home.select_version_history();
		home.textpresent();
	}
	
	
	
	@Test(priority=5)
	public void verify_download() throws Exception
	{
		System.out.println("*******Testcase 5*******");
		driver.navigate().refresh();
		Thread.sleep(4000);
		home.clickOnDocumentLink();
		home.select_text_file(pro.getProperty("Contentfile"));
		System.out.println("File selected");
		/*Thread.sleep(2000);
		home.click_on_info_Link();
		home.click_texteditor();
		home.enter_text_in_texteditor();
		home.click_on_save_button();
		Thread.sleep(2000);
		home.verify_save_msg();*/
		Thread.sleep(3000);
		
		home.select_version_history();
		FileUtility.Delete_File_From_Downloads_Folder();
		home.click_new_version_file();
		home.verify_download_fileName(pro.getProperty("Contentfile"));
		FileUtility.CompareTextFiles();
		//home.verifyExpectedFileName();	
			
	}
	
	
	@Test(priority=6)
	public void verify_delete_file() throws InterruptedException   
	{
		System.out.println("*******Testcase 6*******");
		driver.navigate().refresh();
		if(home.check_if_file_available(pro.getProperty("Contentfile")))
		{
		home.select_text_file(pro.getProperty("Contentfile"));
		home.delete_file();
		//Thread.sleep(2000);
			home.verify_delete_error_msg();
		
		}
		else
		{
			System.out.println("File doesnt exist");
		}
	}

	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
	
	

