#coding:utf-8
'''
Created on 2016年8月31日

@author: kascend
'''
import mimetools
import mimetypes

def encode_multipart_fromdata(fields, files): 
    """ 
    fields is a sequence of (name, value) elements for regular form fields. 
    files is a sequence of (name, filename, value) elements for data to be uploaded as files 
    Return (content_type, body) ready for httplib.HTTP instance 
    """ 
    BOUNDARY = mimetools.choose_boundary() 
    CRLF = '\r\n' 
    L = [] 
    for (key, value) in fields: 
        L.append('--' + BOUNDARY) 
        L.append('Content-Disposition: form-data; name="%s"' % key) 
        L.append('') 
        L.append(value) 
    for (key, filename, value) in files: 
        L.append('--' + BOUNDARY) 
        L.append('Content-Disposition: form-data; name="%s"; filename="%s"' % (key, filename)) 
        L.append('Content-Type: %s' % get_content_type(filename)) 
        L.append('') 
        L.append(value) 
    L.append('--' + BOUNDARY + '--') 
    L.append('') 
    body = CRLF.join(L) 
    content_type = 'multipart/form-data; boundary=%s' % BOUNDARY 
    return content_type, body 
  
def get_content_type(filename): 
    return mimetypes.guess_type(filename)[0] or 'application/octet-stream'

