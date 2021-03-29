package com.tt.tests;

import com.tt.libs.BaseTest;
import com.tt.util.DateUtil;
import com.tt.util.PropertiesUtil;
import com.tt.util.Reporter;

public class Engine {

	public static void main(String[] args)
	{

		/*
		 * String appUrl = "http://saucedemo.com";
		 * 
		 * //It is for login page.... BaseTest bt = new SampleTest();
		 * bt.initializeTest(broUrl); bt.setTestData("standard_user",
		 * "secret_sauce","Epic sadface: Username and Password does not match!");
		 * bt.executeTest(); // bt.closeBrowser();
		 * 
		 * 
		 * // It is for Product page... BaseTest bt = new E2EShoppingTest();
		 * bt.initializeTest(appUrl); bt.executeTest(); bt.closingTest();
		 */

		// Calling the properties file from local directory...
		PropertiesUtil p = new PropertiesUtil("D:\\Selenium-Testing\\Selenium File Generate\\Properties\\env.properties");
		String appUrl = p.get("app_url");
		System.out.println(appUrl);

		// Extent report file from local directory...
		Reporter r = new Reporter("D:\\Selenium-Testing\\Selenium File Generate","DemoReport "+DateUtil.getCurrentDate("ddMMMyyy-HH-mm-ss")+".html");
		
		BaseTest bt = new E2EShoppingTest();
		
		// TestCase data from excel file... 
		bt.prepareTestData("D:\\Selenium-Testing\\Selenium File Generate\\resources\\TestData.xlsx");
		
		bt.setReporter(r);
		bt.setTestData("user_name",p.get("user_name"));
		bt.setTestData("user_password",p.get("user_password"));
		bt.initializeTest(appUrl, "verify if we are able to add products to cart and checkout");
		bt.executeTest();
		bt.closingTest();		
		r.flush();	
	}		
}
