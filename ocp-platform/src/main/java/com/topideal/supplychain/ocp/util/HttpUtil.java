package com.topideal.supplychain.ocp.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpUtil {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final int SO_TIME_OUT = 5000;
	public static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
			.setConnectTimeout(SO_TIME_OUT).setConnectionRequestTimeout(SO_TIME_OUT)
			.setSocketTimeout(SO_TIME_OUT).build();

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private HttpUtil() {
	}

	public static HttpPost createHttpPost(String url, String charset, List<NameValuePair> postDatas) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		//httpPost.setURI(new URI(url));
		httpPost.setEntity(new UrlEncodedFormEntity(postDatas, charset));
		httpPost.setConfig(REQUEST_CONFIG);
		//HttpConnectionParams.setSoTimeout(httpPost.getParams(), SO_TIME_OUT);
		return httpPost;
	}

	public static HttpPost createHttpPost(String url, StringEntity entity) {
		HttpPost httpPost = new HttpPost(url);
		//httpPost.setURI(new URI(url));
		httpPost.setEntity(entity);
		httpPost.setConfig(REQUEST_CONFIG);
		//HttpConnectionParams.setSoTimeout(httpPost.getParams(), SO_TIME_OUT);
		return httpPost;
	}

	public static HttpPut createHttpPut(String url, StringEntity entity) {
		HttpPut httpPut = new HttpPut(url);
		//httpPut.setURI(new URI(url));
		httpPut.setEntity(entity);
		httpPut.setConfig(REQUEST_CONFIG);
		//HttpConnectionParams.setSoTimeout(httpPut.getParams(), SO_TIME_OUT);
		return httpPut;
	}

	public static HttpResponse executePost(HttpPost httpPost) throws IOException {
		return HttpClientFactory.getHttpClient().execute(httpPost);
	}

	public static String parseResponseBodyStr(HttpResponse httpResponse) throws IOException {
		HttpEntity responseEntity = httpResponse.getEntity();
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
			String line = null;
			while ((line = reader.readLine()) != null)
				stringBuilder.append(new StringBuilder().append(line).append("\n").toString());
		} finally {
			responseEntity.getContent().close();
		}
		return stringBuilder.toString();
	}

	/**
	 *
	 * @desc 业务逻辑
	 * 解析返回数据
	 * @author zhangzhihao
	 * @date创建时间 2016-8-29 上午11:42:38
	 *
	 * @param httpResponse
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String parseResponseBodyStr(HttpResponse httpResponse, String charset) throws IOException {
		InputStream inputstream = httpResponse.getEntity().getContent();
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		try {
			int ch;
			while ((ch = inputstream.read()) >= 0) {
				buf.write(ch);
			}
			if (buf.size() == 0) {
				return null;
			}

			return buf.toString(charset);
		} finally {
			buf.close();
			inputstream.close();
		}
	}

	public static String parseResponse(HttpResponse httpResponse) throws Exception {
		return parseResponseBodyStr(httpResponse, getCharset(httpResponse));
	}

	private static String getCharset(HttpResponse httpResponse) {
		Header header = httpResponse.getFirstHeader("Content-Type");
		if (header == null) {
			return null;
		}
		String contentType = header.getValue();
		String encoding = null;
		if (contentType != null) {
			String[] types = StringUtils.split(contentType, ";");
			for (String t : types) {
				String tt = t.trim().toUpperCase();
				if (tt.startsWith("CHARSET=")) {
					encoding = tt.substring(8);
					break;
				}
			}
		}
		return encoding;
	}

	public static void releaseHttpPost(HttpPost httpPost, HttpResponse httpResponse) throws IOException {
		if (httpPost != null) {
			httpPost.releaseConnection();
		}
		if ((httpResponse != null) && (httpResponse.getEntity() != null))
			EntityUtils.consume(httpResponse.getEntity());
	}

	public static int getResponseStatusCode(HttpResponse httpResponse) {
		return httpResponse.getStatusLine().getStatusCode();
	}

	public static boolean isResponseStatusOk(HttpResponse httpResponse) {
		return (getResponseStatusCode(httpResponse) == 200);
	}

	public static HttpGet createHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		//httpGet.setURI(new URI(url));
		httpGet.setConfig(REQUEST_CONFIG);
		//HttpConnectionParams.setSoTimeout(httpGet.getParams(), 5000);
		return httpGet;
	}

	public static HttpResponse executeGet(HttpGet httpGet) throws IOException {
		return HttpClientFactory.getHttpClient().execute(httpGet);
	}

	public static void releaseHttpGet(HttpGet httpGet, HttpResponse httpResponse) throws IOException {
		if (httpGet != null) {
			httpGet.releaseConnection();
		}
		if ((httpResponse != null) && (httpResponse.getEntity() != null))
			EntityUtils.consume(httpResponse.getEntity());
	}

	public static String urlEncoder(String str) throws Exception {
		return URLEncoder.encode(str, DEFAULT_ENCODING);
	}

	public static String makeMd5(String srcContent, String charset) throws Exception {
		if (srcContent == null) {
			return null;
		}
		String strDes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(srcContent.getBytes(charset));
			strDes = bytes2Hex(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	private static String bytes2Hex(byte[] byteArray) {
		StringBuilder strBuf = new StringBuilder();
		for (int i = 0; i < byteArray.length; ++i) {
			if ((byteArray[i] >= 0) && (byteArray[i] < 16)) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}


	/**
	 * Http Post请求封装
	 * @param url
	 * @param entity
	 * @param enconding
	 * @return
	 */
	public static String httpPost(String url, StringEntity entity, String enconding) throws Exception {

		String result = "";
		if (StringUtils.isBlank(url)) {
			logger.error("HttpUtil, URL is null ");
			return "";
		}

		HttpPost httpPost = createHttpPost(url, entity);
		HttpResponse httpResponse = executePost(httpPost);
		if (enconding == null) {
			result = parseResponse(httpResponse);
		} else {
			result = parseResponseBodyStr(httpResponse, enconding);
		}

		return result;
	}


	/**
	 * Http Post请求封装
	 * @param url
	 * @param charset
	 * @param postDatas
	 * @param enconding
	 * @return
	 */
	public static String httpPost(String url, String charset, List<NameValuePair> postDatas, String enconding) throws Exception {

		String result = "";
		if (StringUtils.isBlank(url)) {
			logger.error("HttpUtil, URL is null ");
			return "";
		}
		HttpPost httpPost = createHttpPost(url, charset, postDatas);
		HttpResponse httpResponse = executePost(httpPost);
		if (enconding == null) {
			result = parseResponse(httpResponse);
		} else {
			result = parseResponseBodyStr(httpResponse, enconding);
		}
		return result;
	}

}