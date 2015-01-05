package com.best.moyu.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/*
 * UaerInfo
 * ID（主键）
 * LoginUserId(用户id外键)  
 * NickName(昵称)  
 * RealName（真实姓名）
 * Sex（性别） 
 * Age（年龄） 
 * HeadImage(头像String)  
 *  Phone（手机号number） 
 *  MailBox（邮箱）
 *  UnderWrite（个性签名）
 * */
public class UserInfo extends BmobObject{
	private String LoginUserId;
	private String NickName;
	private String RealName;
	private String Sex;
	private int Age;
	private String HeadImage;
	private int Phone;
	private String MailBox;
	private String UnderWrite;
	public String getLoginUserId() {
		return LoginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		LoginUserId = loginUserId;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getRealName() {
		return RealName;
	}
	public void setRealName(String realName) {
		RealName = realName;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getHeadImage() {
		return HeadImage;
	}
	public void setHeadImage(String headImage) {
		HeadImage = headImage;
	}
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getMailBox() {
		return MailBox;
	}
	public void setMailBox(String mailBox) {
		MailBox = mailBox;
	}
	public String getUnderWrite() {
		return UnderWrite;
	}
	public void setUnderWrite(String underWrite) {
		UnderWrite = underWrite;
	}
	public UserInfo(String loginUserId, String nickName,
			String realName, String sex, int age, String headImage, int phone,
			String mailBox, String underWrite) {
		super();
		LoginUserId = loginUserId;
		NickName = nickName;
		RealName = realName;
		Sex = sex;
		Age = age;
		HeadImage = headImage;
		Phone = phone;
		MailBox = mailBox;
		UnderWrite = underWrite;
	}
	public UserInfo() {

	}

	
	
}
