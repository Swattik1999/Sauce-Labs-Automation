package com.tt.libs;

import java.util.HashMap;

import com.tt.ui.Browser;
import com.tt.util.Reporter;

public abstract class BaseTest {

	public final String browserType = "CHROME";
	public Browser browser = null;
	protected HashMap<String, String> testdata = null;
	protected Reporter r = null;
	
	public abstract void initializeTest(String url, String testName);

	public abstract void  executeTest();

	public abstract void setTestData(String...arg);
	
	public abstract void prepareTestData(String filePath);
	
	public abstract void closeBrowser();

	public void closingTest() {
		r.stop();
	}
		
	public void setTestData(String key, String value) 
	{
		if(testdata !=null)
			testdata.put(key, value);
	}
	
	public String d(String key) {
		return testdata.get(key).toString();
	}
	
	public void setTestdata(HashMap<String, String> data)
	{
		this.testdata = data;
	}
	
	public void setReporter(Reporter r) {
		this.r = r;
	}

}

