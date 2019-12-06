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
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

final class GoogleGeocode {
	private final String lat;
	private final String lng;

	public GoogleGeocode(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	private static String getAPIKey() {
		String apiKey = null;
		try {
			Scanner sc = new Scanner(new File("src/main/resources/api.info"));
			apiKey = sc.nextLine();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("API key file not found.");
		}
		return apiKey;
	}

	public static GoogleGeocode getLatLng(String street, String city, String zipCode) {
		street = street.replace(" ", "?");
		city = city.replace(" ", "?");
		zipCode = zipCode.replace(" ", "?");
		String address = street + ",?" + city + ",?" + zipCode;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		Logger logger = Logger.getLogger(GoogleGeocode.class);
		String lat = null;
		String lng = null;
		try {
			// create httpget.
			String apiKey = getAPIKey();
			String http = "https://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false&key="
					+ apiKey;
			HttpGet httpget = new HttpGet(http);
			logger.debug("HTTP request url: " + httpget.getURI());
			// Sent GET!!
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// GET response
				HttpEntity entity = response.getEntity();
				logger.debug("--------------------------------------");

				// System.out.println(response.getStatusLine());// Print status
				if (entity != null) {
					// open json
					String str = EntityUtils.toString(entity);
					JSONObject o = (JSONObject) JSONValue.parse(str);
					JSONArray o2 = (JSONArray) o.get("results");
					JSONObject o3 = (JSONObject) o2.get(0);
					JSONObject o4 = (JSONObject) o3.get("geometry");
					JSONObject o5 = (JSONObject) o4.get("location");
					lat = o5.get("lat").toString();
					lng = o5.get("lng").toString();
					logger.debug("lat====<<<" + o5.get("lat") + ">>>lng====<<<" + o5.get("lng")+">>>");
				}
				logger.debug("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			// close connection
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return new GoogleGeocode(lat, lng);
	}

	public static void main(String[] args) {
		GoogleGeocode geocode = getLatLng("1780 Y ST", "Lincoln", "68588");
		System.out.println(geocode.getLat() + geocode.getLng());
	}
}
