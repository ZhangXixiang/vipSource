package com.boomhope.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http请求类工具类
 * @author zy
 *
 */
public class HttpClientUtil {
	
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/**
	 * post请求
	 * @param requestUrl
	 * @param paramMap
	 * @throws Exception 
	 * @throws CameraException 
	 */
	public String  post(String requestUrl,JSONObject paramMap) throws Exception {
		HttpClient client = null;
		try {
			// 创建客户端实例
			client = new DefaultHttpClient();
			// 设置请求参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", paramMap.toString()));
			// 绑定请求
			HttpPost post = new HttpPost(requestUrl);
			// 设置请求参数
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			// 进行请求
			HttpResponse httpResponse = client.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 return EntityUtils.toString(httpResponse.getEntity());
			} else {
				logger.error("http异常返回值："+httpResponse.getStatusLine().getStatusCode());
				throw new Exception("未知异常");
			}
		} catch (Exception e) {
			logger.error("异常",e);
			throw new Exception("未知异常",e);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		long start  = System.currentTimeMillis();
//		String requestUrl = "http://192.168.2.128:8080/facecon-web/camera/saveFaceImgVip";
		String requestUrl = "http://127.0.0.1:8080/bh-vip-web/pad/sendFaceRec";
//		String requestUrl = "http://127.0.0.1:8080/bh-vip-web/pad/uploadPadStatus";
		
		JSONObject paramMap = new JSONObject();
	
//		paramMap.put("img1", ImageUtil.getImageToBase64Str("C:\\Users\\Administrator\\Desktop\\1.jpg"));
//		paramMap.put("unitCode", "140");
		
//		paramMap.put("macAddress", "1234");
//		paramMap.put("ipAddress", "192.168.1.147");
		
		String sss = new HttpClientUtil().post(requestUrl,paramMap);
		logger.info("返回结果"+sss);
		long end  = System.currentTimeMillis();
		logger.info("总时间："+(end - start));
	}
}
