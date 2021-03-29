package com.tt.poms;

import java.util.Date;

import com.tt.libs.BasePage;
import com.tt.ui.Browser;

public class CartPage extends BasePage
{
	String clickCheckoutText = "CHECKOUT";
	String checkOutXP = "//a[@class ='btn_action checkout_button']";
	String noOfCartItemXP = "//span[@class='fa-layers-counter shopping_cart_badge']";
	String cartItemCardXP = "//div[@class='cart_item']";
	String cartXP="//div[@class='shopping_cart_container']";
	String productTitle="Sauce Labs Backpack";
	String removeBTNxp = "//div[@class='inventory_item_name' and text()='{{PRODUCT_TITLE}}']/ancestor::a[1]/following-sibling::div[2]/button";

	public CartPage(Browser browser)
	{
		super(browser);
	}
	
	public void setProductTitle(String productTitle) {
		this.productTitle= productTitle;	
	}
	
	public String removeBTNxpforProduct(String productTitle)
	{
		setProductTitle(productTitle);
		String removeBTNxpTemp = removeBTNxp.replace("{{PRODUCT_TITLE}}", productTitle);
		return removeBTNxpTemp;
	}
	public int getNumberofItems()
	{
		int items = 0;
		items = browser.getObjectCountByXP(cartItemCardXP);
		System.out.println("Number of different items present in your cart are:" + items);
		return items;
	}
	public void clickCheckout()
	{
		Date d1 = new Date();
		long before = d1.getTime();
		browser.waitObjectIsVisible("LINKTEXT", clickCheckoutText);
		Date d2 = new Date();
		long after = d2.getTime();
		System.out.println("Time taken to wait:" + (after-before));
		
		browser.getObjectByLinkText(clickCheckoutText);
		browser.click();
		Date d3 = new Date();
		long afterClick = d3.getTime();
		System.out.println("Time Taken to wait and click :" + (afterClick-before));
	}
	public void removeProduct(String productTitle)
	{
		browser.getObjectByXP(removeBTNxpforProduct(productTitle));
		browser.click();
	}
}
