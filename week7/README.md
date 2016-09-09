# mobiletest8.homework.week7.emma
*      [作业1:用python的request模块去模拟get和post请求](#作业1)
*      [作业2:java,用httpclient,rest-assured去模拟请求,get和post](#作业2)
*      [作业3:实践 mvn testng dataprovider](#作业3)

<h2 id="作业1">作业1</h2>

见目录下工程 [TestSuite](https://github.com/emmahuang/mobiletest8.homework.emma/tree/master/week7/TestSuite "TestSuite")

<h2 id="作业2">作业2</h2>

<h3>用httpclient模拟请求</h3>
见目录下工程文件 [HttpClientTest](https://github.com/emmahuang/mobiletest8.homework.emma/tree/master/week7/my-rest-test/src/test/java/http/client "HttpClientTest")

<h3>用rest-assured去模拟请求</h3>
见目录下工程文件 [RestAssuredTest](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/src/test/java/com/emma/app/my_rest_test/RestAssuredTest.java "RestAssuredTest")

<h2 id="作业3">作业3</h2>

见目录下工程 [my-rest-test](https://github.com/emmahuang/mobiletest8.homework.emma/tree/master/week7/my-rest-test "my-rest-test")
<h4>1.新建maven工程</h4>
<h4>2.在pom.xml中添加依赖包</h4>
在[pom.xml](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/pom.xml "pom.xml")文件中添加依赖包，并更改testng的report为reportNG(需要增加依赖包:velocity,guice,reportng)


```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.emma.app</groupId>
  <artifactId>my-rest-test</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <!--maven运行测试name-->
  <name>my-rest-test</name>
  <url>http://maven.apache.org</url>

  <!--maven参数配置，这里引用不同的testng.xml-->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <xmlFileName>testng.xml</xmlFileName>
  </properties>

  <!--maven引用依赖不同的jar-->
  <dependencies>
	
	<!-- 依赖Guice -->
	<dependency>	
		<groupId>com.google.inject</groupId>	
		<artifactId>guice</artifactId>
		<version>4.0</version>
		<scope>test</scope>
		</dependency>
			
		<dependency>
			<groupId>velocity</groupId>			
			<artifactId>velocity-dep</artifactId>			
			<version>1.4</version>	
		</dependency>
		
		<dependency>
		<groupId>log4j</groupId>
		<artifactId>log4j</artifactId>
		<version>1.2.17</version>
	</dependency>

	<dependency>
	  <groupId>org.apache.httpcomponents</groupId>
	  <artifactId>httpclient</artifactId>
	  <version>4.5.2</version>
	</dependency>
	
    <dependency>
        <groupId>com.jayway.restassured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>2.9.0</version>
        <scope>test</scope>
    </dependency>

    <dependency> 
	    <groupId>com.jayway.restassured</groupId> 
	    <artifactId>json-schema-validator</artifactId> 
	    <version>2.9.0</version>
    </dependency>

    <dependency>
	    <groupId>org.json</groupId>
	    <artifactId>json</artifactId>
	    <version>20090211</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>



    <!-- 依赖reportNg 关联testNg -->
    <dependency>
		<groupId>org.uncommons</groupId>		
		<artifactId>reportng</artifactId>		
		<version>1.1.2</version>		
		<scope>test</scope>		
		<exclusions>		
		<exclusion>		
		<groupId>org.testng</groupId>		
		<artifactId>testng</artifactId>		
		</exclusion>		
		</exclusions>	
	</dependency>


    <dependency>
	    <groupId>dom4j</groupId>
	    <artifactId>dom4j</artifactId>
	    <version>1.6.1</version>
	</dependency>

    <!--依赖testng-->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.8</version>
        <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
        <plugins>
            <!-- 添加插件,添加ReportNg的监听器，修改最后的TestNg的报告 -->
			<plugin>			
				<groupId>org.apache.maven.plugins</groupId>			
				<artifactId>maven-surefire-plugin</artifactId>			
				<version>2.19.1</version>			
				<configuration>
				   <suiteXmlFiles>
			            <suiteXmlFile>res/${xmlFileName}</suiteXmlFile>
			           <!--  <suiteXmlFile>${xmlFileName}</suiteXmlFile> -->
			           <!--  <suiteXmlFile>testng.xml</suiteXmlFile> -->
		           </suiteXmlFiles>	
		          			
					<properties>			
						<property>			
						<name>usedefaultlisteners</name>			
						<value>false</value>		
						</property>		
							
						<property>			
						<name>listener</name>			
						<value>org.uncommons.reportng.HTMLReporter,			
						org.uncommons.reportng.JUnitXMLReporter</value>		
						</property>
					</properties>
					<workingDirectory>target/</workingDirectory>
					<!--<forkMode>always</forkMode>-->		
		
				</configuration>
            </plugin> 

        </plugins>
   </build>
  
  
</project>

```

<h4>3.新建测试方法</h4>
      [LoginTest.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/src/test/java/com/emma/app/my_rest_test/LoginTest.java "LoginTest.java")
      [VideoListTest.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/src/test/java/com/emma/app/my_rest_test/VideoListTest.java "VideoListTest.java")

<h4>4.新建数据源文件</h4>
      [TestData.xml](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/target/TestData.xml "TestData.xml")
      
<h4>5.用DOM4J解析XML</h4>
见[ParserXml.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/src/test/java/tools/ParserXml.java "ParserXml.java")

<h4>6.把解析出来的list转换成Object[][]类型的数据，且结合在@DataProvider中</h4>
见[TestData.java](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/src/test/java/tools/TestData.java "TestData.java")

<h4>7.配置testng文件</h4>
见[testng.xml](https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/my-rest-test/res/testng.xml "testng.xml")

```xml
<?xml version="1.0" encoding="UTF-8"?>
<suite name="test" parallel="true">

    <test name="my_rest_test_login" preserver-order="true">
        <classes>
            <class name="com.emma.app.my_rest_test.LoginTest" >               
                 <methods>  
                    <include name="testLogin"></include>                      
                </methods>                 
                </class>           
        </classes>
    </test> 
    <test name="my_rest_test_list" preserver-order="true">
        <classes>
            <class name="com.emma.app.my_rest_test.VideoListTest" />                       
        </classes>
    </test> 
</suite> 
```

<h4>8.右键pom.xml文件 Run As Maven test</h4>

<h4>9.执行完毕，在target,surefire-reports下得到测试报告，见截图</h4>

![alt text][img01]
  [img01]:https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week7/ReportNG.png




