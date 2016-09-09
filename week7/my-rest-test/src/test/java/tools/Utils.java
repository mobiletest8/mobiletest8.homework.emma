/*
 * 工具类
 */
package tools;

import static org.hamcrest.Matchers.equalTo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.RestAssured.*;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;


public class Utils {


	public static  String getTimeStamp(){
        Response response = given().get("http://192.168.16.174:8833/jellyfish-server/api/timestamp/get.htm");
        String json = response.asString();
        //JsonPath jp = new JsonPath(json);
	    System.out.println(json);
	    System.out.println(json.getClass().getName());
	    return json;
   }
	
	//静态方法，便于作为工具类,MD5加密，32位，小写
		public static String getMd5(String plainText) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[] = md.digest();

				int i;

				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				//32位加密
				return buf.toString();
				// 16位的加密
				//return buf.toString().substring(8, 24);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}

		}
}
