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
public class VideoListTest extends TestData
//    extends TestCase
{
	
	@BeforeTest
	public void setUp(){
		//RestAssured.baseURI = "http://183.129.155.244";
		//RestAssured.port = 8833;
		//RestAssured.basePath ="/jellyfish-server";
		RestAssured.baseURI= "http://183.129.155.244:8833/jellyfish-server";
	}
	
    @Test(dataProvider="providerMethod")
    public void testGameVideoList(Map<?, ?> param){	
    	final String _appkey=(String)param.get("_appkey");
    	final String _xappkey=(String)param.get("_xappkey");
    	final String secret=(String)param.get("secret"); 
    	final String gameId=(String)param.get("gameId");
        final String pageSize=(String)param.get("pageSize");
        final String _identifier=(String)param.get("_identifier");
        final String _sdkVersion=(String)param.get("_sdkVersion");    	
    	String bpoint="";
    	String src = secret+"&_appkey=CSAndroidSDK&_xappkey="+_xappkey+"&breakpoint="+bpoint+"&gameId="+gameId+"&pageSize="+pageSize;
    	String _sign = Utils.getMd5(src);
    	   	
    	given().
    	queryParam("_appkey",_appkey).queryParam("_xappkey",_xappkey).queryParam("gameId",gameId).
    	queryParam("pageSize",pageSize).queryParam("_identifier",_identifier).queryParam("_sdkVersion",_sdkVersion).
    	queryParam("_sign",_sign).
    	      expect().log().all().
    	      statusCode(200).content(containsString("12658")).when().get("/xapi/game-video/list.htm").
    	      then().assertThat().body(matchesJsonSchemaInClasspath("gamevideo.json"));	
		Response response=given().
		queryParam("_appkey",_appkey).queryParam("_identifier",_identifier).queryParam("_sdkVersion",_sdkVersion).
		queryParam("_xappkey",_xappkey).queryParam("gameId",gameId).queryParam("pageSize",pageSize).
		queryParam("_sign",_sign).get("/xapi/game-video/list.htm");
    	//assertEquals(from(body).getInt("code"),0);
		  assertEquals(200, response.getStatusCode());
		  String json = response.asString();
		  JsonPath jp = new JsonPath(json);
		  assertEquals(0, jp.get("code"));
    }   
    
 
    
    
    @Test(dataProvider="providerMethod")
    public void testOnlineVideoList(Map<?, ?> param){	
    	final String _appkey=(String)param.get("_appkey");
    	final String _xappkey=(String)param.get("_xappkey");
    	final String secret=(String)param.get("secret"); 
    	final String gameId=(String)param.get("gameId");
        final String pageSize=(String)param.get("pageSize");
        final String _identifier=(String)param.get("_identifier");
        final String _sdkVersion=(String)param.get("_sdkVersion");
    	String bpoint="";
    	String src = secret+"&_appkey=CSAndroidSDK&_xappkey="+_xappkey+"&breakpoint="+bpoint+"&gameId="+gameId+"&pageSize="+pageSize;
    	String _sign = Utils.getMd5(src);
    	System.out.println("_sign_online"+_sign);
    	
    	given().
    	queryParam("_appkey",_appkey).queryParam("_xappkey",_xappkey).queryParam("gameId",gameId).queryParam("pageSize",pageSize).queryParam("_identifier",_identifier).queryParam("_sdkVersion",_sdkVersion).queryParam("_sign",_sign).
    	      expect().log().all().
    	      statusCode(200).content(containsString("天天酷跑online")).when().get("/xapi/online-room/list.htm").
    	      then().assertThat().body(matchesJsonSchemaInClasspath("online.json"));
    	
		Response response=given().
		queryParam("_appkey",_appkey).queryParam("_identifier",_identifier).queryParam("_sdkVersion",_sdkVersion).
		queryParam("_xappkey",_xappkey).queryParam("gameId",gameId).queryParam("pageSize",pageSize).
		queryParam("_sign",_sign).get("/xapi/online-room/list.htm");
		  assertEquals(200, response.getStatusCode());
		  String json = response.asString();
		  JsonPath jp = new JsonPath(json);
		  assertEquals(0, jp.get("code"));
    }
    
    
}
