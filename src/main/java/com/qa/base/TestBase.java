package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class TestBase {


	public static WebDriver driver;
	public static Properties pro;
	
	public TestBase() 
	{
	try {
		File file=new File("C:\\Workspace\\OneDriveProject\\src\\main\\java\\com\\qa\\config\\config.properties");
		
			FileInputStream fis=new FileInputStream(file);
			pro=new Properties();
			pro.load(fis);
		} catch (FileNotFoundException e)
			{
			e.printStackTrace();
			} catch (IOException e)
				{
				e.printStackTrace();
				}
	}
	
	public static void initialization()
	{
		String browsername=pro.getProperty("browser");
		if(browsername.equalsIgnoreCase("Chrome"))
		{
			String downloadFilepath = pro.getProperty("downloadFilepath");
			System.setProperty(pro.getProperty("chrome_driver"),pro.getProperty("chrome_exe"));
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("start-maximized", "disable-popup-blocking");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(pro.getProperty("URL"));
		
		
	}

	}





