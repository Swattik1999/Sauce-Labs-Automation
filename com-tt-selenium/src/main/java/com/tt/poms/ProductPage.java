package com.tt.poms;

import com.tt.libs.BasePage;
import com.tt.ui.Browser;

public class ProductPage extends BasePage{

	String productTitle = "Sauce Labs Backpack";
	String productTitleXP = "//div[@class='inventory_item_name' and text()='{{PRODUCT_TITLE}}']";
	String addToCartXP = "/ancestor::div[1]/following-sibling::div//button";
	String itemPriceXP = "/ancestor::div[1]/following-sibling::div//div";
	String shoppingCartXP = "//div[@id='shopping_cart_container']/a";
	String shoppingCartBadgexp = "//span[@class='fa-layers-counter shopping_cart_badge']";
	String orderClassName = "product_sort_container";
	
	public ProductPage(Browser browser)
	{
		super(browser);
	}
	
	public void setProductTitle(String productTitle)
	{
		this.productTitle = productTitle;
	}
	
	public String getItemPriceXP(String productTitle)
	{
		setProductTitle(productTitle);
		String productTitleXPTemp = this.productTitleXP.replace("{{PRODUCT_TITLE}}", this.productTitle);
		String itemPriceXPTemp = productTitleXPTemp + itemPriceXP;
		return itemPriceXPTemp;
	}
	
	public String getAddToCartXP(String productTitle)
	{
		setProductTitle(productTitle);
		String productTitleXPTemp = this.productTitleXP.replace("{{PRODUCT_TITLE}}", this.productTitle);
		String addToCartXPTemp = productTitleXPTemp + addToCartXP;
		return addToCartXPTemp;
	}
	
	public void clickAddToCart(String productTitle)
	{
		String addToCartXPTemp = getAddToCartXP(productTitle);
		String itemPriceXPTemp = getItemPriceXP(productTitle);
		
		this.productTitle = productTitle;
		
		browser.getObjectByXP(itemPriceXPTemp);
		String itemPrice = browser.getText();
		System.out.println("Item price for \""+ productTitle + "\" is "+ itemPrice );
		
		browser.getObjectByXP(addToCartXPTemp);
		browser.click();
		
	}
	
	public void selectDisplayOrder(String orderValue) {
		browser.getObjectByClassName(orderClassName);
		browser.printAllOptions();
		browser.getObjectByClassName(orderClassName);
		browser.selectOption(orderValue);
	}

	public void clickShoppingCart()
	{
		browser.getObjectByXP(shoppingCartXP);
		browser.click();
	}
	
	public String getNumberofItemsinCart()
	{
		browser.getObjectByXP(shoppingCartBadgexp);
		String sm = browser.getText();
		System.out.println("Number of Items in the cart is/are:" + sm);
		return sm;	
	}
}
