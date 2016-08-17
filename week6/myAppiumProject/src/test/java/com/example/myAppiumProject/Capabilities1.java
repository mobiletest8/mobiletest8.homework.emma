package com.example.myAppiumProject;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities1  {
	
	 public static DesiredCapabilities gridSetUp(String device)
	          throws MalformedURLException,InterruptedException {
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 if(device.equalsIgnoreCase("NX511J")){
	        //File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "apps");
	        //File app = new File(appDir, "com.kascend.chushou_1.0.19.2930.apk");      
	        //capabilities.setCapability("app", app.getAbsolutePath());
			 capabilities.setCapability("platformVersion","5.0.2");
			 capabilities.setCapability("deviceName","NX511J");
			 capabilities.setCapability("platformName","Android");
		     capabilities.setCapability("appPackage", "com.kascend.chushou");
		     capabilities.setCapability("appActivity", "com.kascend.chushou.ChuShouTV_");
		     capabilities.setCapability("udid", "NX511J");
		 }
		 if(device.equalsIgnoreCase("honor")){
	        //File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "apps");
	        //File app = new File(appDir, "com.kascend.chushou_1.0.19.2930.apk");      
	        //capabilities.setCapability("app", app.getAbsolutePath());
			 capabilities.setCapability("platformVersion","5.1.1");
			 capabilities.setCapability("deviceName","SCL-CL00");
			 capabilities.setCapability("platformName","Android");
		     capabilities.setCapability("appPackage", "com.kascend.chushou");
		     capabilities.setCapability("appActivity", "com.kascend.chushou.ChuShouTV_");
		     capabilities.setCapability("udid", "HKR4C15A16007171");
		 }
		 return capabilities;
		 
	 }

}
