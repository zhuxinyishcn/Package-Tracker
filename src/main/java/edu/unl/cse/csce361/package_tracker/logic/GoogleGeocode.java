package edu.unl.cse.csce361.package_tracker.logic;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GoogleGeocode {

	public static String apiInfo() {
		String apiKey = null;
		try {
			Scanner sc = new Scanner(new File("src/main/resources/api.info"));
			apiKey = sc.nextLine();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("file didn't found");
		}
		return apiKey;
	}

	public static void getGoogleLatLng(String address) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			
			// 创建httpget.
			String api = apiInfo();
			String http = "https://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false&key="
					+ api;
			HttpGet httpget = new HttpGet(http);
//			logger.debug("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
//				logger.debug("--------------------------------------");
				// 打印响应状态
				// System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容
					String str = EntityUtils.toString(entity);
					JSONObject o = (JSONObject) JSONValue.parse(str);
					JSONArray o2 = (JSONArray) o.get("results");
					JSONObject o3 = (JSONObject) o2.get(0);
					JSONObject o4 = (JSONObject) o3.get("geometry");
					JSONObject o5 = (JSONObject) o4.get("location");
					System.out.println(o5.get("lat"));
					System.out.println(o5.get("lng"));
					// logger.debug("lat====>>>" + o5.get("lat") + ";lng=====>>>" + o5.get("lng"));
				}
				// logger.debug("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// logger.debug(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			// logger.debug(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.debug(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				// logger.debug(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		getGoogleLatLng("1780?Y?ST?Lincoln?NE?68508");
	}
}
