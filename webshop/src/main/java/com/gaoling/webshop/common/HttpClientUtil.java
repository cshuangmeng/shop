package com.gaoling.webshop.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
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
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {

	// 访问指定URL获取响应内容
	public static String getNetWorkInfo(String url,String saveFile) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(5000)
					.setExpectContinueEnabled(false).build();
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
				if(StringUtils.isNotEmpty(saveFile)){
					// 下载文件
					InputStream is=response.getEntity().getContent();
					OutputStream os=new FileOutputStream(saveFile);
					byte[] byteArray=new byte[2048];
					int bytes=0;
					while((bytes=is.read(byteArray))!=-1){
						os.write(byteArray, 0, bytes);
					}
					os.flush();
					os.close();
				}else{
					// 获取相应内容
					HttpEntity entity = response.getEntity();
					String result = new String(EntityUtils.toByteArray(entity));
					Logger.getLogger("file").info(DateUtil.getCurrentTime()+"|"+url+"|"+result);
					return result;
				}
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
	public static String sendHTTPS(String url) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(5000)
					.setExpectContinueEnabled(false).build();
			connManager.setValidateAfterInactivity(1000);
			// 初始化请求客户端实例
			SSLContext sslContext = SSLContext.getDefault();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			client = HttpClients.custom().setConnectionManager(connManager).setSSLSocketFactory(sslsf).build();
			// 新建GET请求
			HttpGet get = new HttpGet(url);
			// 发送请求
			get.setConfig(requestConfig);
			response = client.execute(get);
			// 判断是否响应正常
			String result="";
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				HttpEntity entity = response.getEntity();
				result = new String(EntityUtils.toByteArray(entity));
				Logger.getLogger("file").info(DateUtil.getCurrentTime()+"|"+url+"|"+result);
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
			String result="";
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				entity = response.getEntity();
				result = new String(EntityUtils.toByteArray(entity));
				Logger.getLogger("file").info(DateUtil.getCurrentTime()+"|"+url+"|"+result);
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
	public static String sendHTTPSWithP12(String url, String xml, String certPwd, String certFile) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 初始化请求客户端实例
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream cert = new FileInputStream(certFile);
			keyStore.load(cert, certPwd.toCharArray());
			cert.close();
			SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, certPwd.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 设置请求体
			HttpEntity entity = new ByteArrayEntity(xml.getBytes(), ContentType.APPLICATION_XML);
			// 新建请求
			HttpPost post = new HttpPost(url);
			post.setEntity(entity);
			response = client.execute(post);
			// 判断是否响应正常
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				entity = response.getEntity();
				String result = new String(EntityUtils.toByteArray(entity));
				Logger.getLogger("file").info(DateUtil.getCurrentTime()+"|"+url+"|"+result);
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
	public static String getNetWorkInfo(String url, HashMap<String, Object> headers) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(5000)
					.setExpectContinueEnabled(false).build();
			connManager.setValidateAfterInactivity(1000);
			// 初始化请求客户端实例
			client = HttpClients.custom().setConnectionManager(connManager).build();
			// 新建GET请求
			HttpGet get = new HttpGet(url);
			// 设置头信息
			if (null != headers) {
				for (Iterator<String> iterator = headers.keySet().iterator(); iterator.hasNext();) {
					String key = iterator.next();
					get.setHeader(key, headers.get(key).toString());
				}
			}
			// 发送请求
			get.setConfig(requestConfig);
			response = client.execute(get);
			// 判断是否响应正常
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取相应内容
				HttpEntity entity = response.getEntity();
				String result = new String(EntityUtils.toByteArray(entity));
				Logger.getLogger("file").info(DateUtil.getCurrentTime()+"|"+url+"|"+result);
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
