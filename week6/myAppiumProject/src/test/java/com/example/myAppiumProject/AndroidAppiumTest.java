package com.example.myAppiumProject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.myAppiumProject.*;

import static org.junit.Assert.assertEquals;

public class AndroidAppiumTest {
	
	//private AppiumDriver<WebElement> driver;
	public AndroidDriver driver = null;

	//private AndroidDriver driver;	
	  	
	
    @BeforeClass
    @Parameters({ "device" })
    public void SetUpBeforeClass(String device) throws IOException, InterruptedException{
        // set up appium
    	System.out.println(device);
    	Capabilities1 caps = new Capabilities1();
    	DesiredCapabilities capability = caps.gridSetUp(device);
    	System.out.println(capability);
    	//public AndroidDriver driver = null;
    	driver = new AndroidDriver(new URL("http://localhost:4444/wd/hub"), capability);  
    	driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
 
    	
    }     

	@AfterClass
    public void tearDownAfterClass() throws Exception {
		//mem.stopRunning();
        driver.quit();
    }

    @Test
    public void apidemo() throws InterruptedException{       
       driver.findElementById("com.kascend.chushou:id/btn_news").click();
       
    }

}