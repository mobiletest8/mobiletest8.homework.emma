#mobiletest8.homework.week6
*   [作业:在本机搭建Appium Grid环境，并完成两个以上设备的并行测试]
    *   [1.start up a hub](#hw-1)
    *   [2.set the node configuration](#hw-2)
    *   [3.operate the node server](#hw-3)
    *   [4.check the hub&node start up successfully](#hw-4)
    *   [5.configure the testNG file](#hw-5)
    *   [6.configure the capabalities by device name](#hw-6)
    *   [7.run the test](#hw-7)

*_Environment:_
Eclipse, java, appium, testNG, maven

###<h3 id="hw-1">1.start up a hub:</h3>
```bat
java -jar /Users/huangxiaoshi/Downloadelenium-server-standalone-2.53.0.jar  -role hub

```

###<h3 id="hw-2">2.set the node configuration:</h3>

```json
{
	"capabilities":
	    [
	      {
	        "browserName": "NX511J",
	        "version":"5.0.2",
	        "maxInstances":1,
            "deviceName":"NX511J",
	        "platform":"ANDROID"
	      }
	    ],
	"configuration":
	{
	  "cleanUpCycle":2000,
	  "timeout":30000,
	  "proxy":"org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
	  "url":"http://0.0.0.0:4723/wd/hub",
	  "host":"0.0.0.0",
	  "port":4723,
	  "maxSession": 1,
	  "register": true,
	  "registerCycle": 5000,
	  "hubPort": 4444,
	  "hubHost":"localhost"
	}
}

```
refer to [appium.json],[appium_2724.json] for detail

###<h3 id="hw-3">3.operate the node server:</h3>


```bat 
appium -p 4723 -bp 4725 --nodeconfig /Users/appium/grid/appium.json
```

```bat
appium -p 4724 -bp 4726 --nodeconfig /Users/appium/grid/appium_4724.json
```


###<h3 id="hw-4">4.check the hub&node start up successfully:</h3>
![alt text][img01]

[img01]:https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week6/gridconsole.png


###<h3 id="hw-5">5.configure the testNG file:</h3>

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="grid suite" parallel="tests"
       thread-count="5">
  <test name="NX511J" preserve-order="true" > 
   <parameter name = "device" value = "NX511J"/>  
    <classes>        
      <class name="com.example.myAppiumProject.AndroidAppiumTest" />
    </classes>
  </test>

   <test name="honor" preserve-order="true" >    
   <parameter name = "device" value = "honor"/> 
    <classes>          
      <class name="com.example.myAppiumProject.AndroidAppiumTest" />
    </classes>
  </test>
</suite>  
```


###<h3 id="hw-6"><6.configure the capabalities by device name:</h3>

```java
public class Capabilities1  {
	
	 public static DesiredCapabilities gridSetUp(String device)
	          throws MalformedURLException,InterruptedException {
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 if(device.equalsIgnoreCase("NX511J")){
			 capabilities.setCapability("platformVersion","5.0.2");
			 capabilities.setCapability("deviceName","NX511J");
			 capabilities.setCapability("platformName","Android");
		     capabilities.setCapability("appPackage", "com.kascend.chushou");
		     capabilities.setCapability("appActivity", "com.kascend.chushou.ChuShouTV_");
		     capabilities.setCapability("udid", "NX511J");
		 }
		 if(device.equalsIgnoreCase("honor")){
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

```

###<h3 id="hw-7"><7.run the test:</h3>
right click the testng.xml file ,***Run As > TestNG
