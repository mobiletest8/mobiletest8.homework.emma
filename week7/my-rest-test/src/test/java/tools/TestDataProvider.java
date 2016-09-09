/*
 * 测试文件
 */
package tools;


import java.util.Map;

import org.testng.annotations.*;

public class TestDataProvider extends TestData {

    @Test(dataProvider="providerMethod")
    public void testmethod1(Map<?, ?> param){
        System.out.println("method1 received:"+param.get("input"));
    }
     
    @Test(dataProvider="providerMethod")
    public void testmethod2(Map<?, ?> param){
        System.out.println("method2 received:"+param.get("input"));
    }
     
    @Test(dataProvider="providerMethod")
    public void testmethod3(Map<?, ?> param){
        System.out.println("method3 received:"+param.get("input"));
    }
    
    @Test
    public void testmethod4(){
        System.out.println("method4 received:4");
    }

}