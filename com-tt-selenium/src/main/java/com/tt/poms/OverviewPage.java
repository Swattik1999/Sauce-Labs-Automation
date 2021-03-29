package com.tt.poms;

import com.tt.libs.BasePage;
import com.tt.ui.Browser;

public class OverviewPage extends BasePage{

	String item_totalXP = "//div[@class='summary_subtotal_label']";
	String taxXP = "//div[@class='summary_tax_label']";
	String totalXP = "//div[@class='summary_total_label']";
	String finishXP = "//a[@class='btn_action cart_button']";

	public OverviewPage(Browser browser)
	{
		super(browser);
	}

	public void showDataForItemTotal() {
		browser.getObjectByXP(item_totalXP);
		String it = browser.getText();
		System.out.println(it);
	}

	public void showDataForTax() {
		browser.getObjectByXP(taxXP);
		String t = browser.getText();
		System.out.println(t);
	}

	public void showDataForTotal() {
		browser.getObjectByXP(totalXP);
		String tot = browser.getText();
		System.out.println(tot);
	}

	public void clickFinish() {
		browser.getObjectByXP(finishXP);
		browser.click();
	}
}
