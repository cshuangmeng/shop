package com.gaoling.admin.util;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	// 访问指定URL获取响应内容
	public static String getNetWorkInfo(String url) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {

			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000)
					.setSocketTimeout(3000).setExpectContinueEnabled(false).build();
			connManager.setValidateAfterInactivity(1000);
			// 初始化请求客户端实例
			client = HttpClients.custom().setConnectionManager(connManager).build();
			// 新建GET请求
			HttpGet get = new HttpGet(url);
			// 发送请求
			get.setConfig(requestConfig);
			response = client.execute(get);
			// 判断是否响应正常
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				HttpEntity entity = response.getEntity();
				String result = new String(EntityUtils.toByteArray(entity));
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	// 访问指定URL获取响应内容
	public static String sendHTTPSWithXML(String url, String xml, ContentType contentType) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 初始化请求客户端实例
			SSLContext sslContext = SSLContext.getDefault();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 设置请求体
			HttpEntity entity = new ByteArrayEntity(xml.getBytes(), contentType);
			// 新建请求
			HttpPost post = new HttpPost(url);
			post.setEntity(entity);
			response = client.execute(post);
			// 判断是否响应正常
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				entity = response.getEntity();
				String result = new String(EntityUtils.toByteArray(entity));
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
}
