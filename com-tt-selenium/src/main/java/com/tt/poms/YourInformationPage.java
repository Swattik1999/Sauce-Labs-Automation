package com.tt.poms;

import com.tt.libs.BasePage;
import com.tt.ui.Browser;

public class YourInformationPage extends BasePage
{
 String setFirstName = "first-name";
 String setLastName = "last-name";
 String setPostalCode = "postal-code";
 String continueButtonXP = "//div/input[@type='submit']";
 
 	public YourInformationPage(Browser browser)
 	{	
	 super(browser);	 
 	}

 	public void setFirstName(String data)
 	{
 		browser.getObjectByid(setFirstName);
 		browser.setText(data);
 	}
 	
 	public void setLastName(String data)
 	{
 		browser.getObjectByid(setLastName);
 		browser.setText(data);
 	}
 	
 	public void setPostalCode(String data)
 	{
 		browser.getObjectByid(setPostalCode);
 		browser.setText(data);
 	}
 	
 	public void clickContinueButton()
 	{
 		browser.getObjectByid(continueButtonXP);
 		browser.click();
 	}
	
}
