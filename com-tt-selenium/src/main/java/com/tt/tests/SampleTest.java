package com.tt.tests;

import java.util.HashMap;
import com.tt.libs.BaseTest;
import com.tt.poms.Login;
import com.tt.ui.Browser;


public abstract class SampleTest extends BaseTest {
	
	Browser browser = null;
	Login lg = null;
	
	String username, password;
	String errorMsg;
	
	public SampleTest()
	{
		browser = new Browser();	
	}

	public void initializeTest(String url)
	{		
		browser.initBrowser();
		browser.launchBrowser(url);
		lg = new Login(browser);
	}
	
	public void executeTest()
	{
		lg.setUserName(this.username);
		lg.setPassword(this.password);
		lg.clickLogin();
		errorMsg = lg.getErrorMessage();		
		if(errorMsg.equals(this.errorMsg))
			System.out.println("Test case is passed !");
		else
			System.err.println("Error occured");
	}

	public void closingTest() {
		// TODO Auto-generated method stub
	}

	// This testData is for username, password and errormsg...
	public void setTestData(String...arg) {
		// TODO Auto-generated method stub
		this.username = arg[0];
		this.password = arg[1];
		this.errorMsg = arg[2];
		
	}

	public void closeBrowser() {
		// TODO Auto-generated method stub
		browser.close();
	}
	
	// This testData is for items ....
	public void setTestdata(HashMap<String, String> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeTest(String url, String testName) {
		// TODO Auto-generated method stub
		
	}

}
