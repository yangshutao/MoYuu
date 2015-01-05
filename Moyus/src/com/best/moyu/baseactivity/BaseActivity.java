package com.best.moyu.baseactivity;

import java.util.ArrayList;
import java.util.List;

import com.best.moyu.utils.CreateFile;
import com.best.moyu.utils.SDCardUtils;

import cn.bmob.v3.Bmob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {
	/**
	 * 盛放所有的Activity
	 * 
	 * */
	 public List<Activity> activityManager = new  ArrayList<Activity>();
	 CreateFile file;
	 	@Override
	protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			Bmob.initialize(this,"6a82445c5203b53ba1acffbf57d2f5cb");
			file = new CreateFile(this);
	}
	 
	 
	 
	 
	/**
	 * 添加Activity
	 * 
	 * 返回值：void
	 * 
	 * 参数：Activity a
	 * 
	 * 
	 * 
	 * 
	 * **/
	public void addActivity(Activity a){
		activityManager.add(a);
	}
	
	
	/**
	 * 描述：将加入的的Activity 遍历 finish（）：退出Activity
	 * 
	 * 
	 * 参数：
	 * 
	 * 
	 * 返回值：void
	 * 
	 * 
	 * **/
	public void exitApplication(){
		for(Activity a : activityManager){
			finish();
		}
	}
	public void alert(String s){
		Toast.makeText(this,s,Toast.LENGTH_LONG).show();
	}
	
}
