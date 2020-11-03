package com.revature.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

		HttpURLConnection connection;
		try {
			ipapi = new URL(
					"http://api.ipapi.com/" + ipAddress.toString() + "?access_key=74686d18f1d0d99ae28f8a2643e65f22");
			connection = (HttpURLConnection) ipapi.openConnection();
			connection.setRequestMethod("GET");
			
			connection.setRequestProperty("Content-Type", "application/json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			JsonObject geoLocation = new JsonParser().parse(reader).getAsJsonObject();

			location.setCity(geoLocation.get("city").toString());
			location.setState(geoLocation.get("region_name").toString());
			location.setLatitude(geoLocation.get("latitude").toString());
			location.setLongitude(geoLocation.get("longitude").toString());

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
