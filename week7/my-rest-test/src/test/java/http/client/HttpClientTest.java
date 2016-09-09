package http.client;

import java.io.IOException;  
  




import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  
import org.testng.Assert;  
import org.testng.annotations.AfterClass;  
import org.testng.annotations.BeforeClass;  
import org.testng.annotations.Parameters;  
import tools.Utils;
  
public class HttpClientTest {  
    private static final int SUCCESS_CODE = 0;  
    private static final int SC_OK = 200;
    private static final String VIDEO_NUM = "12658";  
    private static final String VIDEO_NAME = "125-5120";  
    //初始化httpClient 
    String src="45edbb9b8a8943759e72b7bb935b9134&_appkey=CSAndroidSDK&_identifier=475213605&_sdkVersion=1.0.8.5946&_xappkey=1f7ce3cc55184019&gameId=1008&pageSize=20";
    String _sign=Utils.getMd5(src);
    HttpClient httpClient = new DefaultHttpClient();  
    String URL="http://183.129.155.244:8833/jellyfish-server/xapi/game-video/list.htm?_sign=" + _sign + "&_appkey=CSAndroidSDK&_identifier=475213605&_sdkVersion=1.0.8.5946&_xappkey=1f7ce3cc55184019&gameId=1008&pageSize=20";
    
      
//  @BeforeClass  
//  public void beforeClass() {  
//  }  
      
//  @Parameters({"URL"})  
    @Test   
    public void productInfo() throws ClientProtocolException, IOException, JSONException {  
        //获取httpGet  
        HttpGet httpGet = new HttpGet(URL);  
        System.out.println(URL);
        //执行get请求  
        HttpResponse response = httpClient.execute(httpGet);  
        //判断服务器响应的状态码  
        System.out.println(response.getStatusLine().getStatusCode());  
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode(),"状态码不是200，请求没成功");  
        //读取返回数据  
        String entity = EntityUtils.toString(response.getEntity());  
        //返回的数据类型为json，使用org.json jar包来解析  
        JSONObject json = new JSONObject(entity);  
        
        System.out.println(json.get("code"));  
        System.out.println(json);
        Assert.assertEquals(SUCCESS_CODE, json.get("code"),"返回的code不为0，请求失败");  
        //返回的结果中，data为数组，将其转换为list，获取指定key的值  
        JSONObject data = json.getJSONObject("data");  
        for(int i=0; i<data.length(); i++) {  
            //JSONObject data = object.getJSONObject(i);  
            String phoneID = data.getString("count");  
            Assert.assertEquals(phoneID, VIDEO_NUM, "返回的视频数量不一致");  
            String phoneName = data.getString("breakpoint");  
            Assert.assertEquals(phoneName, VIDEO_NAME, "返回的breakpoint不一致");  
            System.out.println(phoneID+", "+phoneName);  
        }  
    }  
 // @AfterClass  
    @After
  public void afterClass() {  
      //断开连接  
      httpClient.getConnectionManager().shutdown();  
  }  
}