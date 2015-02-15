package com.songwie.task.base.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpPostUtil {

	static Logger logger = LoggerFactory.getLogger(HttpPostUtil.class);

	/**
	 * Http Post参数
	 * @param url
	 * @param postdata
	 * @return
	 */
    public static String postRaw(String url, String postdata) {
    	logger.debug("post url is : " + url);
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpPost httpPost = new HttpPost(url);
    	try {
    		//httpPost.setEntity(new StringEntity(postdata,"UTF-8"));
    		httpPost.setEntity(new StringEntity(postdata,"UTF-8"));
    		httpPost.setHeader("Content-Type", "text/html; charset=UTF-8");
    		//httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        	HttpResponse response2 = httpclient.execute(httpPost);
    		logger.debug("http post status is : " + response2.getStatusLine());//状态码，一般状态码为200时，为正常状态
    	    HttpEntity entity2 = response2.getEntity();
    	    //EntityUtils.consume(entity2);
    	    body = EntityUtils.toString(entity2);
    	    logger.debug("response body : " + body);
    	}
    	catch(Exception e){
    		logger.debug("Http Post Method Failure : " + e.getMessage());
    	}
    	finally {
    	    httpPost.releaseConnection();//释放连接
    	}
        return body;
    }

    public static String postForm(String url,Map<String,String> reqMap){
    	logger.debug("post url is : " + url);
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpPost httpPost = new HttpPost(url);
    	try {
    		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    		Set<Entry<String, String>> paramsEntry = reqMap.entrySet();
    		for (Entry<String,String> paramEntry : paramsEntry) {
    			nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
    		}
    		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
    		//httpPost.setHeader("Content-Type", "text/html; charset=UTF-8");
    		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    		HttpResponse response2 = httpclient.execute(httpPost);
    		logger.debug("http post status is : " + response2.getStatusLine());//状态码，一般状态码为200时，为正常状态
    	    HttpEntity entity2 = response2.getEntity();
    	    body = EntityUtils.toString(entity2);
    	    logger.debug("response body : " + body);
		} catch (Exception e) {
			logger.debug("Http Post Method Failure : " + e.getMessage());
		}
    	finally {
    		httpPost.releaseConnection();//释放连接
    	}
    	return body;
    }

    //把key=value中value为空的值去掉
    public static String postFormNotNull(String url,Map<String,String> reqMap){
    	logger.debug("post url is : " + url);
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpPost httpPost = new HttpPost(url);
    	try {
    		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    		Set<Entry<String, String>> paramsEntry = reqMap.entrySet();
    		for (Entry<String,String> paramEntry : paramsEntry) {
    			if("msg_sys_sn".equalsIgnoreCase(paramEntry.getKey())){
    				nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
    			}
    			else{
    				//if(!(paramEntry.getKey().equalsIgnoreCase("page_size")||"start_page".equalsIgnoreCase(paramEntry.getKey()))){
    	   				if(paramEntry.getValue()!= null && paramEntry.getValue().length()>0){
            				nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
        				}
    				//}
    			}
    		}
    		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
    		//httpPost.setHeader("Content-Type", "text/html; charset=UTF-8");
    		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    		HttpResponse response2 = httpclient.execute(httpPost);
    		logger.debug("http post status is : " + response2.getStatusLine());//状态码，一般状态码为200时，为正常状态
    	    HttpEntity entity2 = response2.getEntity();
    	    body = EntityUtils.toString(entity2);
    	    logger.debug("response body : " + body);
		} catch (Exception e) {
			logger.debug("Http Post Method Failure : " + e.getMessage());
		}
    	finally {
    		httpPost.releaseConnection();//释放连接
    	}
    	return body;
    }

    //把key=value中value为空的值去掉
    public static String postFormString(String url,String rawString){
    	logger.debug("post url is : " + url);
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpPost httpPost = new HttpPost(url);
    	try {
    		httpPost.setEntity(new StringEntity(rawString,"UTF-8"));
    		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    		HttpResponse response2 = httpclient.execute(httpPost);
    		logger.debug("http post status is : " + response2.getStatusLine());//状态码，一般状态码为200时，为正常状态
    	    HttpEntity entity2 = response2.getEntity();
    	    body = EntityUtils.toString(entity2);
    	    logger.debug("response body : " + body);
		} catch (Exception e) {
			logger.debug("Http Post Method Failure : " + e.getMessage());
		}
    	finally {
    		httpPost.releaseConnection();//释放连接
    	}
    	return body;
    }
    /**
     * http get方法
     * @param url
     * @return
     */
    public static String get(String url){
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpGet httpGet = new HttpGet(url);
    	try {
    		HttpResponse response1 = httpclient.execute(httpGet);
    		logger.debug("http get status is : " + response1.getStatusLine());//状态码，一般状态码为200时，为正常状态
    	    HttpEntity entity1 = response1.getEntity();
    	    body =  EntityUtils.toString(entity1,"UTF-8");
    	}
    	catch(Exception e){
    		logger.debug("Http Get Method Failure : " + e.getMessage());
    	}
    	finally {
    	    httpGet.releaseConnection();//释放连接
    	}

    	return body;
    }

    /**
     * 带参数的get请求
     * @param url
     * @param parMap
     * @return
     */
    public static String get(String url,Map<String,String> parMap ){
    	String body = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpGet httpget = new HttpGet(url);
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> paramsEntry = parMap.entrySet();
		for (Entry<String,String> paramEntry : paramsEntry) {
				nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
		}
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(nvps,"UTF-8"));
			 httpget.setURI(new URI(httpget.getURI().toString() + "?" +str));
			 HttpResponse response = httpclient.execute(httpget);
			 logger.debug("http get status is : " + response.getStatusLine());//状态码，一般状态码为200时，为正常状态
	    	    HttpEntity entity = response.getEntity();
	    	    body =  EntityUtils.toString(entity,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return body;
    }

}
