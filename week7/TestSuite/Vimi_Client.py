#coding:utf-8
'''
Created on 2016年8月31日

@author: kascend
'''
import os,random,math,urllib2,uuid,base64,datetime,uuid
#import utils, json
from StringIO import StringIO
from hashlib import  md5
import hashlib
import gzip
import urllib
import utils
import json
from urllib import unquote
from collections import OrderedDict

shareUrlIP = "183.129.155.244:8833/jellyfish-server"
IP= "183.129.155.244:8833/jellyfish-server"
BASE_URL='http://'+IP
secret = '45edbb9b8a8943759e72b7bb935b9134'


#获取当前时间
def fetch_time():
    d =datetime.datetime.now()
    return d.strftime('%Y-%m-%d %H:%M:%S +0000')

#获得时间戳
def getTimeStamp():
#    BASE_URL特殊，用原始的urlopen,不用包装的
    s = urllib.urlopen('http://192.168.16.174:8833/jellyfish-server/api/timestamp/get.htm')
    t = s.read()
    ###print   "时间戳：",t
    return t

#返回jason
def json_result(response):
    data = None
    if response.info().get('Content-Encoding') == 'gzip':
        buf = StringIO(response.read())
        f = gzip.GzipFile(fileobj=buf)
        data = f.read().decode('utf-8')
    else:
        data = response.read().decode('utf-8')
    return data
        
        
#计算并返回md5值        
def md5_Calculate(src):
    myMd5 = hashlib.md5()
    myMd5.update(src)
    myMd5_Digest = myMd5.hexdigest()
    return myMd5_Digest
       
#调用服务器api类
class VimiClient:
    
    def urlopen(self,url,fields=None,files=None,multipart=False):
        req =urllib2.Request('%s%s' % (BASE_URL, url))
        #urllib.parse
        print fields
        fields2 = sorted(fields.iteritems(),key = lambda d:d[0])
        foo = OrderedDict(fields)
        fields1 = OrderedDict(sorted(foo.items(),key= lambda x:x[0]))
        #fields1 = json.dumps(fields1)
        print  type(fields1)
        print  fields1
        src = urllib.urlencode(fields2)  # 将参数转为url编码字符串
        src1 = '%s&%s' %(secret,src)
        print "src------"+src1
        md5_String = md5_Calculate(src1)
        print md5_String
        #src2 = '%s&%s%s' %(src,'_sign=',md5_String)
        #fields2 = urllib.unquote(src2)
        if multipart:
            content_type, body = utils.encode_multipart_fromdata(fields,files)
            req.add_header('Content-Type',content_type)
            req.add_header('Content-Length',str(len(body)))
            req.add_header('lang','en')
        #req.add_header('sign',str(md5_String))
        #req.add_header('_sign',str(md5_String))   
        sign = {'_sign':str(md5_String)}  
        #fields1.INSERT(6,sign)
        fields1.update(sign)
        #fields = (fields,{'_sign':md5_String})
        #fields.push({'_sign':md5_String})       
        #fields._sign.push(str(md5_String))
        #fields1 = json.dumps(fields1)
        print fields1
        if multipart:
            return urllib2.urlopen(req, body, 20)
        else:
            if fields:
                #含data的，是post
                print urllib.urlencode(fields1)
                return urllib2.urlopen(req,urllib.urlencode(fields1),20)
            else:
                #不含data的，是get
                return urllib2.urlopen(req)
    
    
    def urlget(self,url,fields,files=None):
        req = urllib2.Request('%s%s%s' % (BASE_URL, url,urllib.urlencode(fields)))
        print fields
        #fields2用来生成MD5
        fields2 = sorted(fields.iteritems(),key = lambda d:d[0])
        #fields1用来生成data
        foo = OrderedDict(fields)
        fields1 = OrderedDict(sorted(foo.items(),key= lambda x:x[0]))
        #fields1 = json.dumps(fields1)
        print  type(fields1)
        print  fields1
        src = urllib.urlencode(fields2)  # 将参数转为url编码字符串
        src1 = '%s&%s' %(secret,src)
        print "src------"+src1
        md5_String = md5_Calculate(src1)
        print md5_String
        sign = {'_sign':str(md5_String)}  
        fields1.update(sign)
        print fields1
        req = urllib2.Request('%s%s%s' % (BASE_URL, url,urllib.urlencode(fields1)))
        return urllib2.urlopen(req)
   

#用户类    
    def rec_user_login(self,_appkey,_xappkey,accessToken,openUid,_t,_sign):        
        fields = {
            '_appkey':str(_appkey),
            '_xappkey':str(_xappkey),
            'accessToken':str(accessToken),
            'openUid':str(openUid),
            '_t':str(getTimeStamp()),
        }
        return json_result(self.urlopen('/xapi/open-login.htm', fields, {}))
    
#列表类   

#上传视频列表
    def game_video_list(self,_appkey,_identifier,_sdkVersion,_xappkey,gameId,pageSize,breakpoint,_sign):
        fields = {
            '_appkey':str(_appkey),
            '_xappkey':str(_xappkey),
            '_identifier':str(_identifier),
            '_sdkVersion':str(_sdkVersion),
            'gameId':str(gameId),
            'pageSize':str(pageSize),
            'breakpoint':str(breakpoint)
            }
        return json_result(self.urlget('/xapi/game-video/list.htm?',fields,{}))
        

#在线视频列表
    def game_online_list(self,_appkey,_identifier,_sdkVersion,_xappkey,gameId,pageSize,breakpoint,_sign):
        fields = {
            '_appkey':str(_appkey),
            '_xappkey':str(_xappkey),
            '_identifier':str(_identifier),
            '_sdkVersion':str(_sdkVersion),
            'gameId':str(gameId),
            'pageSize':str(pageSize),
            'breakpoint':str(breakpoint)
            }
        return json_result(self.urlget('/xapi/online-room/list.htm?',fields,{}))
    
    
    
if __name__ == "__main__":
    Client = VimiClient()
#    Client.GetExpressPrice()