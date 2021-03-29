package com.tt.ui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	String browserType = "chrome";
	WebDriver driver = null;
	WebElement currElement = null;
	Select select = null;

	public WebDriver initBrowser()
	{
		if(browserType.equalsIgnoreCase("CHROME"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserType.equalsIgnoreCase("FIREFOX"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver =  new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		return driver;
	}


	public void close()
	{
		//Close the browser
		driver.quit();
	}
	// This method is for to locate WebElement by xPath...
	public WebElement getObjectByXP(String xp)
	{		
		return getobject("XP",xp);
	}

	// To locate WebElement by ID...
	public WebElement getObjectByid(String id)
	{	
		return getobject("ID",id);
	} 

	public void Mousehover()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(currElement).build().perform();
	}

	// To locate WebElement by Name... 
	public WebElement getObjectByName(String name)
	{
		return getobject("NAME",name);
	}

	// To locate WebElement by LinkText... 
	public WebElement getObjectByLinkText(String linkText)
	{
		return getobject("LINKTEXT",linkText);
	}

	// To locate WebElement by className...
	public WebElement getObjectByClassName(String className) 
	{
		return getobject("CLASS", className);
	}

	// To set the text value in the field...
	public void setText(String data)
	{
		if(currElement!=null)
		{
			currElement.clear();
			currElement.sendKeys(data);
		}
	}

	// To get the setText value...
	public String getText()
	{
		String ret = "";
		if(currElement!=null)
		{		
			ret = currElement.getText();						
		}
		return ret;
	}

	// Testing WebSite launch through the browser...
	public void launchBrowser(String url)
	{
		driver.get(url);
		System.out.println("Browser launched the website: "+ url);
	}

	// To click on any button...
	public void click()
	{
		if(currElement!=null)
		{
			currElement.click();
		}
	}

	public void waitObjectIsVisible(String locator, String value)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		try 
		{
			if(locator.equalsIgnoreCase("ID"))
				currElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
			else if(locator.equalsIgnoreCase("XP"))
				currElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
			else if(locator.equalsIgnoreCase("LINKTEXT"))
				currElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(value)));
			else if(locator.equalsIgnoreCase("NAME"))
				currElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
		}
		catch(Exception e)
		{
			System.out.println("Object is not found with locator:" + locator + "and the locator value:" + value);
		}
	}

	// To select any WebElement value...
	public void selectOption(String value) 
	{
		select = new Select(currElement);
		select.selectByVisibleText(value);
	}

	// Return the driver object...
	public WebDriver getDriver()
	{
		return driver;
	}

	// 	To find WebElement through driver object...
	public WebElement getobject(String locatorType, String value) {
		try {
			if (locatorType.equalsIgnoreCase("ID")) {
				currElement = driver.findElement(By.id(value));
			} 
			else if (locatorType.equalsIgnoreCase("XP")) {
				currElement = driver.findElement(By.xpath(value));
			}
			else if (locatorType.equalsIgnoreCase("LINKTEXT")) {
				currElement = driver.findElement(By.linkText(value));
				// System.out.println(currentElement);
			}
			else if (locatorType.equalsIgnoreCase("NAME")) {
				currElement = driver.findElement(By.name(value));
			}
			else if (locatorType.equalsIgnoreCase("CLASS")) {
				currElement = driver.findElement(By.className(value));}
		} catch (NoSuchElementException nsee) {
			System.out.println("Object is not found!");
			currElement = null;
		}

		return currElement;
	}

	// To count total no. of object by xPath...
	public int getObjectCountByXP(String xp) 
	{
		int count = 0;
		try 
		{
			List<WebElement> eles = driver.findElements(By.xpath(xp));
			if (eles != null && eles.size() > 0)
				count = eles.size();
		} catch (Exception e) {
			System.out.println("Elements are not found with xpath!" + xp);
		}
		return count;
	}

	// To print all the options in the list...
	public void printAllOptions() 
	{
		select = new Select(currElement);
		List<WebElement> options = select.getOptions();
		System.out.println("------------Printing all the options of select box ------------");
		for(int i=0;i<options.size();i++) 
		{
			System.out.println(options.get(i).getText());
		}

		System.out.println("------------Ending printing of all options values ------------");
	}

	// Suace labs projects methods end here !!!!!!!!......

	// This all the methods is for iFrame demo Automation-Testing-Site...
	// switch to frame...
	public void switchToFrame(String id)
	{
		WebElement iframe = driver.findElement(By.id(id));
		driver.switchTo().frame(iframe);
	}

	// switch to frame by xPath...
	public void switchToFrameByXP(String xp)
	{
		WebElement iframe = driver.findElement(By.xpath(xp));
		driver.switchTo().frame(iframe);
	}

	// Exit the frame...
	public void exitFrame()
	{
		driver.switchTo().defaultContent();
	}

	// Alert message....
	public void switchToAlert()
	{
		driver.switchTo().alert();
	}

	// Take the alert message...
	public void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}

	// Cancel alert message...
	public void cancelAlert()
	{
		driver.switchTo().alert().dismiss();
	}
	public String getAlertInfo()
	{
		return driver.switchTo().alert().getText();
	}
	public void setTextInAlert(String data)
	{
		driver.switchTo().alert().sendKeys(data);
	}
}