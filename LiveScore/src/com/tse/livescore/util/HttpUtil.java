package com.tse.livescore.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



 public class HttpUtil
 {
         public static HttpClient httpClient=new DefaultHttpClient();

         public static String getRequest(String url) throws Exception
         {
                 HttpGet get=new HttpGet(url);
                 HttpResponse httpResponse=httpClient.execute(get);
                 if(httpResponse.getStatusLine().getStatusCode()==200)
                 {
                         String result=EntityUtils.toString(httpResponse.getEntity());
                         return result;
                 }
                 return null;
         }

 		public static String postRequest(String str)throws Exception
 		{
 			String url="http://live-score.sqli.cloudbees.net/livescore/live";
 			HttpPost httpPost = new HttpPost(url);
 			StringEntity entity=new StringEntity(str);
 			httpPost.setEntity(entity);
 			HttpResponse httpResponse = httpClient.execute(httpPost);
 			if (httpResponse.getStatusLine().getStatusCode() == 200)
 			{
 				String result = EntityUtils.toString(httpResponse.getEntity());
 				return result;
 			}
 			return null;
 		}

     	public static boolean putRequest(String url,String str) throws Exception{
    		HttpPut httpPut=new HttpPut(url);
    		StringEntity entity=new StringEntity(str);
    		httpPut.setEntity(entity);
    		HttpResponse httpResponse=httpClient.execute(httpPut);
    		if(httpResponse.getStatusLine().getStatusCode()==200)
    		{
    			return true;
    		}
    		return false;
    	}
    	
    	public static boolean DeleteRequest(int id) throws Exception{
    		String url="http://live-score.sqli.cloudbees.net/livescore/live/"+id;
    		HttpDelete httpDelete=new HttpDelete(url);
    		HttpResponse httpResponse=httpClient.execute(httpDelete);
    		if(httpResponse.getStatusLine().getStatusCode()==200)
    		{
    			return true;
    		}
    		return false;
    	}
 }
