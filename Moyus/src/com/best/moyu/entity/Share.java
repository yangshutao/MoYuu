package com.best.moyu.entity;

public class Share {
	  public int ID; //主键
	  public int LoginUserID;//用户ID
	  public String ShareTime;//发表时间
	  public String  ShareLongitude;//发表经度
	  public String  ShareLatitude;//发表纬度
	  public String Text;//内容
	  public String Image;//图片
	  public String  Music;//音乐
	  public String  Sound;//音频
	  public String  Video;//视频
	  public int  Approves;//赞的次数
	  public String  ApprovesUserInfoID;//赞的人的id
	  public int  Stamps;//踢的次数
	  public String  StampsUserInfoID;//踢的人的id
	  public int  Comments;//评论的人数
	public Share(int iD, int loginUserID, String shareTime, String shareLongitude,
			String shareLatitude, String text, String image, String music,
			String sound, String video, int approves, String approvesUserInfoID,
			int stamps, String stampsUserInfoID, int comments) {
		super();
		ID = iD;
		LoginUserID = loginUserID;
		ShareTime = shareTime;
		ShareLongitude = shareLongitude;
		ShareLatitude = shareLatitude;
		Text = text;
		Image = image;
		Music = music;
		Sound = sound;
		Video = video;
		Approves = approves;
		ApprovesUserInfoID = approvesUserInfoID;
		Stamps = stamps;
		StampsUserInfoID = stampsUserInfoID;
		Comments = comments;
	}
	public Share() {
		super();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getLoginUserID() {
		return LoginUserID;
	}
	public void setLoginUserID(int loginUserID) {
		LoginUserID = loginUserID;
	}
	public String getShareTime() {
		return ShareTime;
	}
	public void setShareTime(String shareTime) {
		ShareTime = shareTime;
	}
	public String getShareLongitude() {
		return ShareLongitude;
	}
	public void setShareLongitude(String shareLongitude) {
		ShareLongitude = shareLongitude;
	}
	public String getShareLatitude() {
		return ShareLatitude;
	}
	public void setShareLatitude(String shareLatitude) {
		ShareLatitude = shareLatitude;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getMusic() {
		return Music;
	}
	public void setMusic(String music) {
		Music = music;
	}
	public String getSound() {
		return Sound;
	}
	public void setSound(String sound) {
		Sound = sound;
	}
	public String getVideo() {
		return Video;
	}
	public void setVideo(String video) {
		Video = video;
	}
	public int getApproves() {
		return Approves;
	}
	public void setApproves(int approves) {
		Approves = approves;
	}
	public String getApprovesUserInfoID() {
		return ApprovesUserInfoID;
	}
	public void setApprovesUserInfoID(String approvesUserInfoID) {
		ApprovesUserInfoID = approvesUserInfoID;
	}
	public int getStamps() {
		return Stamps;
	}
	public void setStamps(int stamps) {
		Stamps = stamps;
	}
	public String getStampsUserInfoID() {
		return StampsUserInfoID;
	}
	public void setStampsUserInfoID(String stampsUserInfoID) {
		StampsUserInfoID = stampsUserInfoID;
	}
	public int getComments() {
		return Comments;
	}
	public void setComments(int comments) {
		Comments = comments;
	}
	@Override
	public String toString() {
		return "Share [ID=" + ID + ", LoginUserID=" + LoginUserID + ", ShareTime="
				+ ShareTime + ", ShareLongitude=" + ShareLongitude
				+ ", ShareLatitude=" + ShareLatitude + ", Text=" + Text
				+ ", Image=" + Image + ", Music=" + Music + ", Sound=" + Sound
				+ ", Video=" + Video + ", Approves=" + Approves
				+ ", ApprovesUserInfoID=" + ApprovesUserInfoID + ", Stamps="
				+ Stamps + ", StampsUserInfoID=" + StampsUserInfoID + ", Comments="
				+ Comments + "]";
	}
	  
	  
	}
