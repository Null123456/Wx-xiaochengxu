package com.ssk.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by W on 2018/2/2.
 */
public class HttpUtil {

	private static final String ENCODE = "utf-8";

	public static String post(String url, Map<String, String> param) {

		RequestConfig config = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
				.setConnectionRequestTimeout(50000).build();
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(config).build();
//        CloseableHttpClient client = HttpClients.createDefault();
		// 请求超时
//        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Global.getConst("connection_timeout"));
		// 读取超时
//        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Global.getConst("so_timeout"));
		HttpPost httpPost = new HttpPost(url);

		// 请求参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = param.keySet();

		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, param.get(key)));
		}
//		System.out.println("nvps=" + nvps.toString());
		HttpEntity entity;
		CloseableHttpResponse response;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE));
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			response = client.execute(httpPost);
			if (response == null || response.getStatusLine().getStatusCode() != 200) {
				throw new IllegalArgumentException("参数异常");
			}
			entity = response.getEntity();
			String body = "";
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, ENCODE);
//				System.out.println("http get- <== response body=" + body);
			}
			// 释放链接
			EntityUtils.consume(entity);
			response.close();
			return body;
		} catch (Exception e) {
			System.out.println("http post 请求发送失败。url=" + url + "param=" + param);
			e.printStackTrace();
		}
		return "";
	}

	public static String get(String url, Map<String, String> param) {
		CloseableHttpClient client = HttpClients.createDefault();
		Set<String> keySet = param.keySet();
		if (!param.isEmpty()) {
			url = url + "?";
		}
		Iterator<String> keyIter = keySet.iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			url = url + key + "=" + param.get(key);
			if (keyIter.hasNext()) {
				url = url + "&";
			}
		}
		System.out.println("http get - ==> url=" + url);
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity;
		CloseableHttpResponse response;
		try {
			response = client.execute(httpGet);
			if (response == null || response.getStatusLine().getStatusCode() != 200) {
				throw new IllegalArgumentException("参数异常");
			}
			entity = response.getEntity();
			String body = "";
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, ENCODE);
				System.out.println("http get- <== response body=" + body);
			}
			// 释放链接
			EntityUtils.consume(entity);
			response.close();
			return body;
		} catch (Exception e) {
			System.out.println("http post 请求发送失败。url=" + url + "param=" + param);
			e.printStackTrace();
		}
		return "";
	}

}
