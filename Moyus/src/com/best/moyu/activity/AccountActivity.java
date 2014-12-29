package com.best.moyu.activity;

import com.example.moyu.R;

import android.app.Activity;  
import android.app.DownloadManager.Request;
import android.os.Bundle;
import android.view.View;

public class AccountActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.account_setting_manager);
	}
	public void backSetting(View v){
		this.finish();
	}
}
