package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Upload')]")
	WebElement upload;
	
	@FindBy(xpath="//div[@class='OperationMonitor']")
	WebElement errormsg;
	
	@FindBy(xpath="//div[contains(text(),'uploaded')]")
	WebElement trackerrormsg;
	
	
	//*[@id="appRoot"]/div/div[5]/div/comment()[4]
	
	/*@FindBy(xpath="//span[contains(text(),'Files')]//parent::button[@tabindex='0']")
	WebElement filebtn;
	*/
	/*
	@FindBy(xpath="//div[@role='menu']/div/ul")
	WebElement items;
	*/
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	} 
	
	public void clickbtn() throws InterruptedException, IOException
	{
		upload.click();
		 Thread.sleep(2000);
		 List<WebElement> list = driver.findElements(By.xpath("//div[@role='menu']/div/ul"));
		 for (WebElement ele : list)
		  {	     	 
	          if (ele.getAttribute("innerHTML").contains("Files"))
	          {
	              ele.click();
	              break;
	          }
		  } 
	}
	     /*Thread.sleep(2000);
		 Runtime.getRuntime().exec("C:\\Users\\vijay\\Desktop\\AutoIT\\UploadFile.exe");
	*/
	public void setClipboardData(String string) throws InterruptedException, AWTException
	{
	    StringSelection stringSelection = new StringSelection(string);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	 
	 
		 Robot robot = new Robot();
		 Thread.sleep(1000);
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V); 
		 robot.keyRelease(KeyEvent.VK_V); 
		 robot.keyRelease(KeyEvent.VK_CONTROL); 
		 robot.keyPress(KeyEvent.VK_ENTER); 
		 robot.keyRelease(KeyEvent.VK_ENTER); 
	}
	
	public boolean errormessage()
	{
		boolean b=errormsg.isDisplayed();
		String actual_errormessage=errormsg.getText();

		Assert.assertTrue(actual_errormessage.contains("Sorry, OneDrive can't upload empty folders or empty files. Please try again."));	
		return b;
	}
	public String errortrackmessage()
	{
		String msg=trackerrormsg.getAttribute("innerHTML");
		System.out.println(msg);
		return msg;
	}
	
	public void selectFile()
	{
		
		
	}
	
}

