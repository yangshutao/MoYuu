package com.best.moyu.entity;

public class ChatInfo {
	private String  mnickname;
	private String  mheadsculpture;
	private String  mtime;
	private String  chathistory;
	public ChatInfo(String mnickname, String mheadsculpture, String mtime,
			String chathistory) {
		this.mnickname = mnickname;
		this.mheadsculpture = mheadsculpture;
		this.mtime = mtime;
		this.chathistory = chathistory;
	}
	public ChatInfo() {
		
		// TODO Auto-generated constructor stub
	}
	public String getMnickname() {
		return mnickname;
	}
	public void setMnickname(String mnickname) {
		this.mnickname = mnickname;
	}
	public String getMheadsculpture() {
		return mheadsculpture;
	}
	public void setMheadsculpture(String mheadsculpture) {
		this.mheadsculpture = mheadsculpture;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public String getChathistory() {
		return chathistory;
	}
	public void setChathistory(String chathistory) {
		this.chathistory = chathistory;
	}
	
	
}
