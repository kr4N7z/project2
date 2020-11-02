package com.revature.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.models.GeoIp;

@Service
public class GeoIpService {
	public GeoIp getLocation(InetAddress ipAddress) {

		GeoIp location = new GeoIp();
		URL ipapi;

		URLConnection connection;
		try {
			ipapi = new URL("https://ipapi.co/" + ipAddress.toString() + "/json/");
			connection = ipapi.openConnection();
			connection.setRequestProperty("User-Agent", "java-ipapi-client");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			JsonObject geoLocation = new JsonParser().parse(reader).getAsJsonObject();
			JsonArray locationArray = geoLocation.getAsJsonArray();
			for (int i = 0; i < locationArray.size(); i++) {
				location.setCity(locationArray.get(i).getAsJsonObject().get("city").getAsString());
				location.setState(locationArray.get(i).getAsJsonObject().get("region").getAsString());
				location.setLatitude(locationArray.get(i).getAsJsonObject().get("latitude").getAsString());
				location.setLongitude(locationArray.get(i).getAsJsonObject().get("longitude").getAsString());
			}
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return location;
	}
}
