# mobiletest8.homework.week5
*     [作业1: 使用robotium去做上周的作业](#作业1)
*     [作业2: 扩展robotium的solo下面的api,然后去使用新api来做测试;](#作业2)


<h2 id="作业1">作业1</h2>  

见目录下工程 [MyFirstApp](https://github.com/emmahuang/mobiletest8.homework.emma/tree/master/week5/MyFirstApp "MyFirstApp")

<h2 id="作业2">作业2</h2>  
extend **assertCurrentActivity**,whether it is succeed or failed ,take screenshot of the current activity,for the convenience of further analysis.
I imported [com.robotium.solo],in [Asserter.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week5/Robotium_sourcecode_modify/eclipse/MyFirstAppTest/src/com/robotium/solo/Asserter.java "Asserter.java"),I added the following method:

```java
         	/**
        	 * Asserts that an expected {@link Activity} is currently active one and take screenshot.
        	 *
        	 * @param message the message that should be displayed if the assert fails
        	 * @param name the name of the {@code Activity} that is expected to be active e.g. {@code "MyActivity"}
        	 */
        	public void assertCurrentActivity(String message,String name,Solo solo)
        	{
        		boolean foundActivity = waiter.waitForActivity(name);
        
        		if(!foundActivity){
        			Activity activity = activityUtils.getCurrentActivity();
        			if(activity != null){
        				if(!(name==activity.getClass().getSimpleName())){
        					solo.takeScreenshot("test_"+message+"_Failure");
        					Assert.assertTrue(message,false);
        				}else{
        					solo.takeScreenshot("test_"+message+"_Success");					
        				}
        			}
        			else{
        				solo.takeScreenshot("test_"+message+"_Failure");
        				Assert.assertEquals(message, name, "No actvity found");				
        			}
        		}
        		solo.takeScreenshot("test_"+message+"_Success");
        	}
        	
```
then in [Solo.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week5/Robotium_sourcecode_modify/eclipse/MyFirstAppTest/src/com/robotium/solo/Solo.java "solo.java"),I added the following method:
```java
         	/**
        	 * Asserts that the Activity matching the specified name is active.
        	 *
        	 * @param message the message to display if the assert fails
        	 * @param name the name of the {@link Activity} that is expected to be active. Example is: {@code "MyActivity"}
        	 * @author huangxiaoshi take screenshot of the current activity
        	 */
        	public void assertCurrentActivityScreen(String message,String name,boolean takeScreenshot)
        	{
        		if(takeScreenshot){
        			asserter.assertCurrentActivity(message,name,this);
        		}else{
        			asserter.assertCurrentActivity(message,name);
        		}
        	}
        	
  ```
  
  finally in my test case [MainAcitivityTest.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week5/Robotium_sourcecode_modify/eclipse/MyFirstAppTest/src/com/example/myfirstapp/test/MainActivityTest.java "MainActivityTest.java"),I used this method:
  ```java
  
            solo.assertCurrentActivity("Activity jump failed", "DisplayMessageActivity",true);
            
 ```

