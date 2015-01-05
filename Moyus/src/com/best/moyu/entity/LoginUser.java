package com.best.moyu.entity;

import cn.bmob.v3.BmobUser;


public class LoginUser extends BmobUser {
	/**
	 * 登录注册实体类
	 * ID(int)  
	 * Account(账号)  
	 * Password(密码)  
	 * Longitude(经度)  
	 * Latitude(纬度)   
	 * LonginTime（登陆时间） 
	 * State（当前状态String）
	 * */
	//private int ID;
	//private int Account;
	//private String Password;
	private String Longitude;
	private String Latitude;
	private String LonginTime;
	private String State;
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLonginTime() {
		return LonginTime;
	}
	public void setLonginTime(String longinTime) {
		LonginTime = longinTime;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public LoginUser(String longitude, String latitude, String longinTime,
			String state) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		LonginTime = longinTime;
		State = state;
	}
	public LoginUser() {
		
	}
	
	
	
}
