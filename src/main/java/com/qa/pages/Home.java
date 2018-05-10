package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.utility.FileUtility;

public class Home extends TestBase{

	@FindBy(xpath="//div[@data-automationid='Documents']")
	WebElement docfolder;
	
	@FindBy(xpath="//span[contains(text(),'Upload')]")
	WebElement upload;
	
	@FindBy(xpath="//div[@class='OperationMonitor']")
	WebElement errormsg;
	
	@FindBy(xpath="//div[contains(text(),'uploaded')]")
	WebElement trackerrormsg;
		
	@FindBy(xpath="//i[@data-icon-name='Info']")
	WebElement infoLink;
		
	@FindBy(xpath="//dt[text()='Modified']/following-sibling::dd[1]")
	WebElement expectedtime;
	
	@FindBy(xpath="//dt[text()='Size']/following-sibling::dd[1]")
	WebElement file_size;
	
	@FindBy(xpath="//span[text()='Open in Text Editor']")
	WebElement texteditorlink;
	
	@FindBy(xpath="//div[@class='view-line']")
	WebElement enter_text;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement save_btn;
	
	@FindBy(xpath="//span[text()='Version history']")
	WebElement version_history_btn;
	
	@FindBy(xpath="//a[text()='Just now']")
	WebElement verifytext;
	
	@FindBy(xpath="//ancestor::div[@data-list-index='0']//a[@class='od-modifiedDateColumn-modifiedDate']")
	WebElement newUpdateFile;
	
	@FindBy(xpath="//ancestor::div[@data-list-index='1']//a[@class='od-modifiedDateColumn-modifiedDate']")
	WebElement uploadedFile;
	
	@FindBy(xpath="//button[@name='Open File']")
	WebElement openFile_button;
	
	@FindBy(xpath="//span[text()='Delete']")
	WebElement delete_button;
	
	@FindBy(xpath="//div[contains(text(),'Deleted')]")
	WebElement delete_msg;
	
	@FindBy(xpath="//div[contains(text(),'Uploaded')]")
	WebElement uploaded_msg;
	
	@FindBy(xpath="//div[contains(text(),'Saved')]")
	WebElement save_msg;
	
	@FindBy(xpath="//div[contains(text(),'A file with this name already exists. Would you like to replace the existing one, or rename it and keep them both?')]")
	WebElement upload_error_msg;
	
	@FindBy(xpath="//span[text()='Replace']")
	WebElement replace_button;
	
	@FindBy(xpath="//i[@data-icon-name='More']")
	WebElement dropdown;

	@FindBy(xpath="//button[@aria-label='Version history']")
	WebElement versionlink;
	
	@FindBy(xpath="//a[@title='Documents']")
	WebElement documentlink;
	
	
	public Home()
	{
		PageFactory.initElements(driver, this);
	} 
	
	//Clicking on Document folder
	public void click_on_document_folder() 
	{
		docfolder.click();
		System.out.println("Clicked on Document Folder");
	}
	
	public void clickbtn() throws InterruptedException, IOException
	{
		
		upload.click();
		
		System.out.println("Upload button clicked");
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
	    
		
	//File upload by Robot Class
    public void uploadFileWithRobot (String path) {
        StringSelection stringSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
 
        Robot robot = null;
 
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
 
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
    }
	
	
	
	public boolean empty_file_upload()
	{
		// Checking if error message is displayed 
		boolean b=errormsg.isDisplayed();
		
		//retrieving text from error message
		String actual_errormessage=errormsg.getText();  
		
		//Asserting to check if it contains text
		Assert.assertTrue(actual_errormessage.contains("Sorry, OneDrive can't upload empty folders or empty files. Please try again."));	
		return b;
	}
	
	public String track_emptyFile_upload_errorMessage()
	{
		String msg=trackerrormsg.getAttribute("innerHTML");
		System.out.println("Tracking error message of empty file upload:"+msg);
		return msg;
	}
	
		
	public void select_text_file(String filename) 
	{
		String beforetext="//span[@role='checkbox']/parent::div[@data-automationid='";
		String aftertext="']//i[@data-icon-name='StatusCircleCheckmark']";
		String full_xpath_selectfile=beforetext+filename+aftertext;
		driver.findElement(By.xpath(full_xpath_selectfile)).click();
		System.out.println(filename+" --File is selected");
	}
	
	public boolean check_if_file_available(String filename) 
	{

		boolean bool;
		try{
			String xpathexpression = "//span[text()='"+filename+"']";
			
			bool=driver.findElement(By.xpath(xpathexpression)).isDisplayed();
		}
		catch(Exception a)
		{
		bool=false;
		}
		System.out.println("File " +filename+ " is already Present "+bool);
		return bool;
		
	}
	
	
	public void click_on_info_Link() 
	{
		infoLink.click();
	}
	
	public void verifyMetaData(String filename)
	{

				try {
					//Scroll to end of the Page 
					Actions action = new Actions(driver);
					Thread.sleep(3000);
					action.sendKeys(Keys.PAGE_DOWN).build().perform();
					
					//Declaring a Soft Assert for Asserting
					SoftAssert softassert=new SoftAssert(); 
					
					//getting the file name from properties file
					String f=pro.getProperty("files_path")+filename;
					File file= new File(f);  
					String actual_filename=file.getName();
					System.out.println("Actual source File Name is " + file.getName());
					String xpathexpression = "//div[text()='"+filename+"']";
					String expected_filename=driver.findElement(By.xpath(xpathexpression)).getText();
					softassert.assertEquals(actual_filename, expected_filename, "Files uploaded are not same, titles did not match");
					System.out.println("File names matched and the expected file name is---"+expected_filename);
					
					long val=file.lastModified();
					Date date=new Date(val);
					SimpleDateFormat df2 = new SimpleDateFormat("M/dd/yyyy h:mm a");
					String dateText = df2.format(date);
					System.out.println("Source File last modified date:"+dateText);
					String expected_date_time=expectedtime.getText();
					softassert.assertEquals(dateText, expected_date_time, "Last modified date did not match");
					System.out.println("Dates matched and Expected modified date:"+expected_date_time);
						
					long actual_size=file.length();
					String actual_size_file=actual_size+" bytes";
					System.out.println("Source File size: " + actual_size_file);
					String expected_file_size=file_size.getText();
					softassert.assertEquals(actual_size_file, expected_file_size, "File size did not match with source file");
					System.out.println("Expected file size:"+expected_file_size);
					 
					//softassert.assertAll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
	
		
	}
	
			public void click_texteditor() 
			{
				texteditorlink.click();
				System.out.println("Text Editor link clicked");
			}
			
			public void enter_text_in_texteditor() 
			{
			
				String parent=driver.getWindowHandle();
				Set<String>s1=driver.getWindowHandles();
				Iterator<String> I1= s1.iterator();
				 
				while(I1.hasNext())
				{
				    String child_window=I1.next();
				 
				// Here we will compare if parent window is not equal to child window then we will close
				 
				if(!parent.equals(child_window))
				{
				driver.switchTo().window(child_window);
				 
				System.out.println(driver.switchTo().window(child_window).getTitle());
				Actions act1=new Actions(driver);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				act1.sendKeys(enter_text,"\n***updated text****").build().perform();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
				}
		
			}
			
			public void click_on_save_button()
			{
				save_btn.click();
				System.out.println("Updated file is saved");
			}
			
			public void select_version_history() throws InterruptedException, IOException
			{
				Thread.sleep(1000);
				dropdown.click();
				
				 Thread.sleep(2000);
				/* List<WebElement> list = driver.findElements(By.xpath("//div[@role='application']/div/div"));
				 for (WebElement ele : list)
				  {	     	 
			          if (ele.getAttribute("innerHTML").contains("Version history"))
			          {
			              ele.click();
			              System.out.println("Clicked on Version History link");
			              break;
			          }
				  } */
				 
				 versionlink.click();
			}
			
			public void click_on_version_history_link() 
			{
				version_history_btn.click();
			}
			
		public void textpresent() 
		{
			String text=verifytext.getText();
			
					Assert.assertTrue(text.contains("Just now"), "File not updated");
					
					System.out.println("File updated and new a version of the file is created: "+text );
			
		}
		
		public void click_new_version_file()  
		{
			/*Actions action=new Actions(driver);
			Thread.sleep(3000);
			action.moveToElement(newUpdateFile);
			action.click().build().perform();
			System.out.println("moved the cursor to the file");
			Thread.sleep(3000);
			openFile_button.click();
			System.out.println("clicked on the file");*/
			
			newUpdateFile.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			uploadedFile.click();
			System.out.println("file downloaded successfully");
			
		}
		
		public void clickOnDocumentLink()
		{
			documentlink.click();
		}
					
		public void verify_download_fileName(String filename) 
		{
			 SoftAssert assertion=new SoftAssert();
			 assertion.assertTrue(FileUtility.verify_fileDownloaded(pro.getProperty("downloadFilepath"),filename),"file not downloaded");
		
		}
		
		/*public void verifyExpectedFileName()
		{	
		    File getLatestFile = FileUtility.getLatestFilefromDir(pro.getProperty("downloadFilepath"));
		    String fileName = getLatestFile.getName();
		    Assert.assertTrue(fileName.equals(pro.getProperty("source_filename")), "Downloaded file name is not matching with expected file name");
		    System.out.println("Downloaded file name is matching with expected file name");
		    
		}
		*/
		public void delete_file()
		{
			delete_button.click();
			System.out.println("Clicked on Delete link");
					
		}

		public String verify_delete_error_msg()
		{
			// Explicit wait
			WebDriverWait wait= new WebDriverWait(driver,20);
			WebElement tooltipTextEle=wait.until(ExpectedConditions.visibilityOf(delete_msg));
			boolean bool=tooltipTextEle.isDisplayed();
			SoftAssert softassert=new SoftAssert();
			softassert.assertTrue(bool, "Delete status message not displayed");
			String msg= tooltipTextEle.getText();
			System.out.println("Tracking delete message:"+msg);
			
			return msg;
		}
		
		/*public boolean check_if_upload_error_msg_present()
		{
			
			upload_error_msg.
			return false;
			
		}*/
		
		
		public String verify_upload_error_msg()
		{
			// Explicit wait
			WebDriverWait wait= new WebDriverWait(driver,10);
			WebElement tooltipTextEle=wait.until(ExpectedConditions.visibilityOf(upload_error_msg));
			boolean bool=tooltipTextEle.isDisplayed();
			SoftAssert softassert=new SoftAssert();
			softassert.assertTrue(bool, "upload error status message not displayed");
			String msg= tooltipTextEle.getText();
			System.out.println("Tracking upload error status message:"+msg);
			String expected_text="A file with this name already exists. Would you like to replace the existing one, or rename it and keep them both?";
			
			softassert.assertEquals(msg, expected_text, "Error message does'nt match");
			System.out.println("Error message match");
			replace_button.click();
			System.out.println("Replace button clicked");
			return msg;
		}
		
				
		public String verify_upload_msg()
		{
			// Explicit wait
			WebDriverWait wait= new WebDriverWait(driver,10);
			WebElement tooltipTextEle=wait.until(ExpectedConditions.visibilityOf(uploaded_msg));
			boolean bool=tooltipTextEle.isDisplayed();
			SoftAssert softassert=new SoftAssert();
			softassert.assertTrue(bool, "Upload status message not displayed");
			String msg= tooltipTextEle.getText();
			System.out.println("Tracking upload message:"+msg);
			return msg;
		}
		
		public String verify_save_msg()
		{
			// Explicit wait
			WebDriverWait wait= new WebDriverWait(driver,10);
			WebElement tooltipTextEle=wait.until(ExpectedConditions.visibilityOf(save_msg));
			boolean bool=tooltipTextEle.isDisplayed();
			SoftAssert softassert=new SoftAssert();
			softassert.assertTrue(bool, "Save status message not displayed");
			String msg= tooltipTextEle.getText();
			System.out.println("Tracking save message:"+msg);
			return msg;
		}
	
}

