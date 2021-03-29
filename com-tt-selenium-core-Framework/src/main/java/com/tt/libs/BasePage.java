package com.tt.libs;

import com.tt.ui.Browser;

public class BasePage {

	protected Browser browser;
	
	public BasePage()
	{
		// This is a constructor...
	}
	
	public BasePage(Browser browser)
	{
		setBrowser(browser);
//		this.browser = browser;
	}

	public void setBrowser(Browser browser)
	{
		this.browser = browser;
	}
}
