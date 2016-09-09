package com.emma.app.my_rest_test;

import org.junit.Before;
import org.junit.Test;

import tools.Utils;
import static org.junit.Assert.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest{
	
	@Before
	public void setUp() {
		//BASE_URL='http://'+IP
		//IP= "183.129.155.244:8833/jellyfish-server"
		RestAssured.baseURI= "http://183.129.155.244:8833/jellyfish-server";
		//RestAssured.port = 8833;
		//RestAssured.basePath = "/xapi/open-login.htm";
		//RestAssured.registerParser("text/plain",Parser.JSON);
	}	
	
	@Test
	public void testGameVideo() {
		final String _appkey="CSAndroidSDK";
		final String _identifier="475213605";
		final String _sdkVersion="1.0.8.5946";
		final String _xappkey="1f7ce3cc55184019";
		final String gameId="1008";
		final String pageSize="20";
	    String src="45edbb9b8a8943759e72b7bb935b9134&_appkey=CSAndroidSDK&_identifier=475213605&_sdkVersion=1.0.8.5946&_xappkey=1f7ce3cc55184019&gameId=1008&pageSize=20";
	    String _sign=Utils.getMd5(src);
		//_sign
		Response response=given().
		queryParam("_appkey",_appkey).queryParam("_identifier",_identifier).queryParam("_sdkVersion",_sdkVersion).
		queryParam("_xappkey",_xappkey).queryParam("gameId",gameId).queryParam("pageSize",pageSize).
		queryParam("_sign",_sign).get("/xapi/game-video/list.htm");
		  assertEquals(200, response.getStatusCode());
		  String json = response.asString();
		  JsonPath jp = new JsonPath(json);
		  assertEquals(0, jp.get("code"));
		  
	//	  statusCode(200).body().
	//	  body(
		   // "message", is(nullValue()),
	//	    "code",equalTo(0)).
	//	    "user.id",equalTo(),
	//	    "user.name",equalTo(0)).
	//	   when().
		   
	}
	

}