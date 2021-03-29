package com.tt.tests;

import java.util.HashMap;
import com.tt.libs.BaseTest;
import com.tt.poms.CartPage;
import com.tt.poms.Login;
import com.tt.poms.OverviewPage;
import com.tt.poms.ProductPage;
import com.tt.poms.YourInformationPage;
import com.tt.ui.Browser;
import com.tt.util.XLUtil;

public class E2EShoppingTest extends BaseTest {

	String username, password, firstname, lastname, Postalcode; 
	Browser browser = null;
	ProductPage pg =  null;
	Login lg = null;
	CartPage cp = null;
	YourInformationPage ip = null;
	OverviewPage op = null;

	public E2EShoppingTest() {
		//This is a constructor of the child class...
	}
	public E2EShoppingTest(HashMap<String, String> testdata) {
		setTestdata(testdata);
	}

	public void initializeTest(String url, String testName) 
	{
		browser = new Browser();	
		browser.initBrowser();
		browser.launchBrowser(url);

		pg = new ProductPage(browser);
		lg = new Login(browser);
		cp = new CartPage(browser);
		ip = new YourInformationPage(browser);
		op = new OverviewPage(browser);

		r.startTest(testName);
		r.setDriver(browser.getDriver());

	}

	//		lg.setUserName("standard_user");
	//		lg.setPassword("secret_sauce");
	//		lg.clickLogin();
	//		pg.clickAddToCart("Sauce Labs Bike Light");
	//		pg.clickToCart();
	//		cp.cartCount();
	//		cp.clickCheckOut();
	//		ip.setFirstName("Swattik");
	//		ip.setLastName("Samanta");
	//		ip.setPostalCode("12345");
	//		ip.clickContinueButton();
	//		op.showDataForItemTotal();
	//		op.showDataForTax();
	//		op.showDataForTotal();
	//		op.clickFinish();
	//		browser.close();
	//		
	//		String errorMsg= lg.errorMessage();

	public void executeTest() {
		lg.setUserName(d("user_name"));
		r.info("entered username:" + (d("user_name")));
		lg.setPassword(d("user_password"));
		r.info("entered password:" + (d("user_password")));
		lg.clickLogin();
		r.info("Clicked login Button");

		String itemsString = d("add_items");
		String[] items = itemsString.split("##");
		pg.selectDisplayOrder("Price (low to high)");

		r.info("Selected to order for products as:" + "Price (low to high)");
		int itemCountToAdd = items.length;
		for (int i = 0; i < itemCountToAdd; i++) {
			pg.clickAddToCart(items[i]);
			r.info("Added item :" + items[i] + "to cart");
		}
		String num = pg.getNumberofItemsinCart();
		r.info("No. of items added to cart: " + num);
		pg.clickShoppingCart();
		r.info("Clicked on shopping cart");
		int itemCount = cp.getNumberofItems();
		if (num.equals("" + itemCount))
			r.pass("Your Cart page has correct No. of items");
		else
			r.fail("Your Cart Page does not have correct no. of items");
		String removeItemsString = d("remove_items");
		String[] removeItems = removeItemsString.split("##");

		int removeItemCount = removeItems.length;
		for (int i = 0; i < removeItemCount; i++) {
			cp.removeProduct(removeItems[i]);
			r.info("Removed item :" + removeItems[i] + "from cart");
		}
		
		itemCount = cp.getNumberofItems();
		r.info("No. of items available in the cart as of now: " + itemCount);


		cp.clickCheckout();
		r.info("Clicked on Checkout Button");
		ip.setFirstName(d("first_name"));
		r.info("entered First name: " + d("first_name") + "in your information page");
		ip.setLastName(d("last_name"));
		r.info("entered Last name: " + d("last_name") + "in your information page");
		ip.setPostalCode(d("postal_code"));
		r.info("entered Postal Code: " + d("postal_code") + "in your information page");
		ip.clickContinueButton();
		r.info("Clicked on continue Button");
		op.showDataForItemTotal();
		op.showDataForTax();
		op.showDataForTotal();
		op.clickFinish();
		// co.overviewValues();
	}

	public void setTestData(String... arg) {
		this.username = arg[0];
		this.password = arg[1];
		this.firstname = arg[2];
		this.lastname = arg[3];
		this.Postalcode = arg[4];
	}

	public void closeBrowser() {
		// TODO Auto-generated method stub

	}

	public void setTestdata(String... args) {
		// TODO Auto-generated method stub

	}

	public void closingBrowser() {
		// TODO Auto-generated method stub

	}
	
	public void prepareTestData(String filePath) {
		
		XLUtil xl = new XLUtil(filePath);
		testdata = new HashMap();

		//get data from Data sheet...
		testdata.put("first_name",xl.getCellValue("FirstName", 1) );
		testdata.put("last_name",xl.getCellValue("LastName", 1));
		testdata.put("postal_code",xl.getCellValue("PostalCode", 1));    

		//get data from items sheet...
		xl.loadSheet("Items");

		int numOfItemRows=xl.getRowCount();
		String itemsToAdd = "";
		String itemsToRemove = "";

		for(int i=1;i<=numOfItemRows;i++) {
			String action=xl.getCellValue("Actions", i);
			if(action.equalsIgnoreCase("Add To Cart")) {
				itemsToAdd=itemsToAdd+"##"+xl.getCellValue("Item", i);

			}
			else
			{
				itemsToRemove=itemsToRemove+"##"+xl.getCellValue("Item", i);
				//itemsToRemove=xl.getCellValue("Item", i);
			}
		}
		if(itemsToAdd.length()>0)
			itemsToAdd=itemsToAdd.substring(2);
		if(itemsToRemove.length()>0)
			itemsToRemove=itemsToRemove.substring(2);

		testdata.put("add_items",itemsToAdd );
//		testdata.put("remove_items",itemsToAdd );
		testdata.put("remove_items",itemsToRemove );
		
		xl.close();
	}
}
