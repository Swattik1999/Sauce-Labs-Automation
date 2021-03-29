package com.tt.poms;

import com.tt.libs.BasePage;
import com.tt.ui.Browser;

public class Login extends BasePage{

	String usernameID = "user-name";
	String passwordID = "password";
	String loginbtnID = "login-button";
	String errorMsgXP=  "//h3[@data-test = 'error']";
	
	public Login(Browser browser)
	{
		super(browser);
	}
	
	public void setUserName(String data)
	{
		browser.getObjectByid(usernameID);
		browser.setText(data);
	}
	public void setPassword(String data)
	{
		browser.getObjectByid(passwordID);
		browser.setText(data);
	}
	public void clickLogin()
	{
		browser.getObjectByid(loginbtnID);
		browser.click();
	}
	public String getErrorMessage()
	{
		browser.getObjectByXP("//h3[@data-test = 'error']");
		String errorMsg = browser.getText();
		return errorMsg;
	}
	
}
