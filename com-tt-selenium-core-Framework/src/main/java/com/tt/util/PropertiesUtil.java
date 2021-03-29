package com.tt.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	 public String filePath = "";
	 public String onEnv = "";
	 Properties prop;
	 String currInstance = "";
	 
	 public PropertiesUtil(String filePath) {
		 this.filePath = filePath;
		 prop = new Properties();
		 try {
			 InputStream is = new FileInputStream(this.filePath);
			 prop.load(is);
			 is.close();
			 currInstance = get("run_on_instance");
			 System.out.println("Scripts needs to run on: "+currInstance);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public String get(String propName) {
		 return get(propName,currInstance);
	 }
	 
	 public String get(String propName,String prefix) {
		 String ret = "";
		 if(prefix!=null && !"".equals(prefix))
			 propName=prefix+"_"+propName;
		 ret = prop.getProperty(propName);
		 
		 System.out.println("Value of property ["+propName+"]:"+ret);
		 return ret;
	 }
		 
	public String getFilePath() 
	{
		return filePath;
	}

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String getOnEnv() {
		return onEnv;
	}

	public void setOnEnv(String onEnv) 
	{
		this.onEnv = onEnv;
	}

	public Properties getProp() 
	{
		return prop;
	}

	public void setProp(Properties prop) 
	{
		this.prop = prop;
	}

	public String getCurrInstance() 
	{
		return currInstance;
	}

	public void setCurrInstance(String currInstance) 
	{
		this.currInstance = currInstance;
	}

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		
		 PropertiesUtil props = new PropertiesUtil("D:\\Selenium-Testing\\Selenium File Generate\\Properties\\env.properties");
		 String userName = props.get("user_name");
		 String userPassword = props.get("user_password");
		 String appUrl = props.get("app_url");
	 }
}

