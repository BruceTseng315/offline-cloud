package com.turingmaker.common.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class HttpRequestTool {

	// public static final MediaType JSON =
	// MediaType.parse("application/json;charset=utf-8");

	Logger logger = LoggerFactory.getLogger(HttpRequestTool.class);

	SSLConnectionSocketFactory sslsf;

	HttpClientBuilder builder = HttpClientBuilder.create();

	@PostConstruct
	public void init() {

		
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
		clientConnectionManager.setMaxTotal(50);
		clientConnectionManager.setDefaultMaxPerRoute(30);
		clientConnectionManager.closeIdleConnections(5, TimeUnit.MINUTES);
		builder.setConnectionManager(clientConnectionManager);
		SSLContextBuilder sslContextBuilder=new SSLContextBuilder();
		
		try {
			sslsf = new SSLConnectionSocketFactory(sslContextBuilder.build(),
					new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
		
			if(logger.isWarnEnabled()) {
				logger.warn("初始化异常",e);
			}
		}

	}

	/**
	 * Get方式，通过okhttp获取响应结果
	 *
	 * @param url
	 *            get请求的路径
	 * @return 返回响应body的字符串
	 * @throws IOException
	 *             有exception返回null
	 */
	public  String httpGetForResult(String url) {

		HttpGet get = null;
		String result = null;
		try {
			HttpResponse httpResponse = builder.build().execute(get = new HttpGet(url));
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			if(logger.isWarnEnabled()) {
				logger.warn("调用"+url+"异常",e);
			}
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		return result;

	}

	

	/**
	 * http post data数据
	 * @param url
	 * @param data
	 * @return
	 */
	public String httpPostForResult(String url, Map<String, String> data) {
		
		
		String result = null;
		HttpPost post = new HttpPost(url);

		final List<NameValuePair> nameValuePairs=new ArrayList<>();
		data.forEach((k,v)->{
			nameValuePairs.add(new BasicNameValuePair(k, v));
		});
				
		try {			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse = builder.build().execute(post);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			if(logger.isWarnEnabled()) {
				logger.warn("调用"+url+"异常",e);
			}
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return result;
	}

	
	

	/**
	 * http post JSON数据
	 * @param url
	 * @param json 
	 * @return
	 */
	public String httpPostForResult(String url, String json) {
		
		
		String result = null;
		HttpPost post = new HttpPost(url);
		
				
		try {			
			post.setEntity(new StringEntity(json));
			post.setHeader("Content-Type","application/json");
			HttpResponse httpResponse = builder.build().execute(post);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			if(logger.isWarnEnabled()) {
				logger.warn("调用"+url+"异常",e);
			}
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return result;
	}

	
}
