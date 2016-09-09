package com.emma.app.my_rest_test;


import java.util.Map;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import tools.TestData;
import tools.Utils;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.containsString;

import com.jayway.restassured.module.jsv.JsonSchemaValidator;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Unit test for simple App.
 */
public class LoginTest extends TestData
//    extends TestCase
{
	
	@BeforeTest
	public void setUp(){
		//RestAssured.baseURI = "http://183.129.155.244";
		//RestAssured.port = 8833;
		//RestAssured.basePath ="/jellyfish-server";
		RestAssured.baseURI= "http://183.129.155.244:8833/jellyfish-server";
	}
	 
   //备注:@Test(dataProvider="providerMethod",dataProviderClass=TestData.class)时默认跳过该case
    @Test(dataProvider="providerMethod")
    public void testLogin(Map<?, ?> param){
    	String t = Utils.getTimeStamp();
    	String _t=t;
    	final String _appkey=(String)param.get("_appkey");
    	final String _xappkey=(String)param.get("_xappkey");
    	final String secret=(String)param.get("secret");  	
    	final String accessToken=(String)param.get("accessToken");
    	final String openUid=(String)param.get("openUid");    	
    	String src = secret+"&_appkey="+_appkey+"&_t="+t+"&_xappkey="+_xappkey+"&accessToken="+accessToken+"&openUid="+openUid;
    	String _sign = Utils.getMd5(src);
    	
    	given().
    	params(
    			"_appkey",_appkey,
    			"_xappkey",_xappkey,
    			"accessToken",accessToken,
    			"openUid",openUid,
    			"_sign",_sign,
    			 "_t",t).
    	      expect().log().all().
    	      statusCode(200).content(containsString("demo测试号13")).when().post("/xapi/open-login.htm").then().
    	      assertThat().body(matchesJsonSchemaInClasspath("login-schema.json"));   	
    	
		Response response=	given().params(
    			"_appkey",_appkey,
    			"_xappkey",_xappkey,
    			"accessToken",accessToken,
    			"openUid",openUid,
    			"_sign",_sign,
    			 "_t",t).post("/xapi/open-login.htm");
		  assertEquals(200, response.getStatusCode());
		  String json = response.asString();
		  JsonPath jp = new JsonPath(json);
		  assertEquals(0, jp.get("code"));		  	  	  
    }

    
}
