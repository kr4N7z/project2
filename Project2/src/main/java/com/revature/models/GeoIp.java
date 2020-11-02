package com.revature.models;

public class GeoIp {
    private String ipAddress;
    private String city;
    private String state;
    private String latitude;
    private String longitude;
    
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GeoIp [ipAddress=" + ipAddress + ", city=" + city + ", state=" + state + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
    
}
