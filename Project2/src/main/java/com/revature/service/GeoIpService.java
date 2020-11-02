package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.revature.models.GeoIp;
@Service
public class GeoIpService {
	public GeoIp getLocation(InetAddress ipAddress) {
		
		GeoIp location = new GeoIp();
		String state = null;
		File database = new File("/Project2/src/main/resources/GeoLite2-City.mmdb");
		CityResponse response;
		try {
			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
			response = dbReader.city(ipAddress);
			location.setCity(response.getCity().getName());
			location.setLatitude(response.getLocation().getLatitude().toString());
			location.setLongitude(response.getLocation().getLongitude().toString());
		} catch (IOException | GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return location;	
	}
}
