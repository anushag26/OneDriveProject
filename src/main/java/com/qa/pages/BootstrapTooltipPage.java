package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BootstrapTooltipPage {
	
	public static BootstrapTooltipPage getBootstrapTooltipPage(WebDriver driver)
	{
		return PageFactory.initElements(driver, BootstrapTooltipPage.class);
	}

	@FindBy(xpath="//*[text()='Uploaded 1 item to Documents']")
	private WebElement tooltip;
	

	public WebElement gettooltip()
	{
		return tooltip;
	}

	
	@FindBy(xpath="//div[@text='Uploaded 1 item to']/span/span[@text='Documents']")

	private WebElement tooltipEle;
	

	public WebElement gettooltipEle()
	{
		return tooltipEle;
	}
}
