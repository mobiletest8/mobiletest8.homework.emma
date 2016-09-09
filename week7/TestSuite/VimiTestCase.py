#coding:utf-8
'''
Created on 2016年8月31日

@author: kascend
'''

import Vimi_Client,json, utils,unittest,os,datetime,random,time
from urllib2 import Request,urlopen,URLError,HTTPError
from StringIO  import StringIO
import gzip
from time import strftime
import HTMLTestRunner  
import utils

'''
程序结构如下：

用户方面：用户登录，校验手机号码
列表方面：不同游戏的直播列表的测试用例


'''

Client = Vimi_Client.VimiClient()

def json_print_value(json,key,number):
    number = number +1
    #number代表第几层结构
    key_value = "not found"
    if isinstance(json,dict):
        for k in json.keys():
            if k == key:
                #print json.get(key),number
                return json.get(key),number
            else:
                #print k
                #print json.get(k);
                s = json_print_value(json.get(k), key, number)
                if s != 'not found':
                    #print  s
                    return s
    elif isinstance(json,list):
        print "is a list"
        for json_array in json:
            s = json_print_value(json_array, key, number)
            if s != 'not found':
                #print s
                return s
    #print key_value
    return key_value

class VimiTestCase(unittest.TestCase):
    def setup(self):
        print 'test is begin'
    def tearDown(self):
        print 'over'
        

    #测试用户登录
    def testUserLogin(self):
        _t = Vimi_Client.getTimeStamp()
        return_json = Client.rec_user_login('CSRecAndroidSDK', '1f7ce3cc55184019', '96e79218965eb72c92a549dd5a330112', '20000000808','_t','_sign')
        print "return_json"
        print return_json
        return_json = json.loads(return_json)
        print type(return_json)
        #self.assertEqual(json_print_value(return_json, 'roomList', 0),(1,1))
        self.assertEqual(json_print_value(return_json, 'code', 0),(0,1))
        #self.assertEqual(json_print_value(return_json, 'message', 0),(NULL,1))
        self.assertEqual(json_print_value(return_json, 'gender', 0),(u'unknow',5))
        #中文字符的校验
        #self.assertEqual(json_print_value(return_json, 'nickname', 0),(u'表面看',5))
        self.assertEqual(json_print_value(return_json, 'uid', 0),(13281041,5))
        self.assertEqual(json_print_value(return_json,'id',0),(4136052,4))
        #self.assertEqual(json_print_value(return_json,'name',0),(u'gdjvsjv',4))
        self.assertEqual(json_print_value(return_json,'professional',0),(0,4))
        self.assertEqual(json_print_value(return_json,'subscriberCount',0),(0,4))
        
        
#上传视频列表    
    def testGameVideoList(self):
        #第一页数据
        bpoint = ""
        return_json = Client.game_video_list('CSAndroidSDK', '475213605', '1.0.8.5946', '1f7ce3cc55184019','1008', '20',bpoint, '_sign')
        return_json = json.loads(return_json)
        print return_json
        #作页面的简单判断
        self.assertEqual(json_print_value(return_json,u'count',0),(12652,2))
        self.assertEqual(json_print_value(return_json,u'code',0),(0,1))
        #第二页以上数据
        bpoint = json_print_value(return_json,'breakpoint',0)
        print 'bpoint的值'
        print str(bpoint[0])   
        print '翻页查询第二页'
        return_json = Client.game_video_list('CSAndroidSDK', '475213605', '1.0.8.5946', '1f7ce3cc55184019','1008', '20',str(bpoint[0]), '_sign')
        return_json = json.loads(return_json)
        #作页面的简单判断
        self.assertEqual(json_print_value(return_json,u'code',0),(0,1))
        bpoint = json_print_value(return_json,'breakpoint',0)
        print '第二页bpoint的值'
        print str(bpoint[0])

#在线视频列表
    def testOnlineList(self):
                #第一页数据
        bpoint = ""
        return_json = Client.game_online_list('CSAndroidSDK', '475213605', '1.0.8.5946', '1f7ce3cc55184019','0', '20',bpoint, '_sign')
        return_json = json.loads(return_json)
        print "------------在线直播列表-------------"
        print return_json
        #作页面的简单判断
        self.assertEqual(json_print_value(return_json,u'code',0),(0,1))
        #第二页以上数据
        bpoint = json_print_value(return_json,'breakpoint',0)
        print 'online-bpoint的值'
        print str(bpoint[0])   
        print '翻页查询第二页'
        return_json = Client.game_online_list('CSAndroidSDK', '475213605', '1.0.8.5946', '1f7ce3cc55184019','0', '20',str(bpoint[0]), '_sign')
        return_json = json.loads(return_json)
        #作页面的简单判断
        self.assertEqual(json_print_value(return_json,u'code',0),(0,1))
        bpoint = json_print_value(return_json,'breakpoint',0)
        print 'online-第二页bpoint的值'
        print str(bpoint[0])
        


# 获取测试套件
def suite():
    suite = unittest.TestSuite()   
    suite.addTest(VimiTestCase("testOnlineList"))    
    suite.addTest(VimiTestCase("testUserLogin"))
    suite.addTest(VimiTestCase("testGameVideoList"))
    return suite

suite = unittest.TestLoader().loadTestsFromTestCase(VimiTestCase)
file_name = "D:/workspace/CSSDK_API_Test/testreport_"+datetime.datetime.now().strftime('%Y-%m-%d %H-%M')+".html"#定义报告路径
fp = file(file_name,'wb')#定义报告文件权限，wb，表示有读写权限
runner = HTMLTestRunner.HTMLTestRunner(
                stream = fp,
                title='Vimi Api Test',
                description= 'The python'
                )


#运行测试套件
if __name__ == "__main__":
    runner.run(suite)
    fp.close()#关闭文件，否则会无法生成文件