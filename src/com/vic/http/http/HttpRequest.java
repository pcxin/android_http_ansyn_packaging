package com.vic.http.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpStatus;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 异步数据请求
 * @author chen
 * @date 2012-10-25 下午2:57:33
 */
public class HttpRequest {

	public static final int REQUEST_TIMEOUT = 10*1000;//设置请求超时10秒钟
	public static final int SO_TIMEOUT = 10*1000;  //设置等待数据超时时间10秒钟

	public DefaultHttpClient mHttpClient = null;
	public String doRequest(String path) throws Exception{
//		BasicHttpParams httpParams = new BasicHttpParams();
//	    HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
//	    HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
//	    Log.i("httpurl", url);
////	    if(mHttpClient == null)
//		    mHttpClient = new DefaultHttpClient(httpParams);
//	    HttpGet get = new HttpGet(url);
//	    HttpResponse response = null;
//		response = mHttpClient.execute(get);
//		String data = null;

		URL url = new URL(path);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setConnectTimeout(5 * 1000);
		urlConn.connect();
		BufferedReader buffer = null;
		String line = null;
		StringBuffer sb = new StringBuffer();
		if (urlConn.getResponseCode() == HttpStatus.SC_OK) {

			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	        while ((line = buffer.readLine()) != null) {
	          sb.append(line);
	        }
	        return sb.toString();
		} else {
			return null;
		}
	}
}
