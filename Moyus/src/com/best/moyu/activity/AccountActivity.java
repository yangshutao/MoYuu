package com.best.moyu.activity;

import com.best.moyu.login.LoginActivity;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

import android.app.Activity;  
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends Activity implements OnClickListener{
	RelativeLayout exit_now,addAccount,zaixian,yinshen;
	ImageView yinshenImg,zaixianImg;
	public TextView account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_setting_manager);  
		Data(); 
	}
	public void backSetting(View v){
		this.finish();
	}
	@Override
	public void onClick(View v){
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.exit_now:
			Intent intent = new Intent(this,LoginActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.add_account:
			Intent intent2 = new Intent(this,LoginActivity.class);
			startActivity(intent2);
			break;
		case R.id.zaixian:
			//此处是在线操作
			yinshenImg.setVisibility(View.INVISIBLE);
			zaixianImg.setVisibility(View.VISIBLE);
			break;
		case R.id.yinshen:
			//此处是隐身等一系列操作
			zaixianImg.setVisibility(View.INVISIBLE);
			yinshenImg.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	public void Data(){	
		account = (TextView) findViewById(R.id.account);
		Toast.makeText(getApplicationContext(),FunctionMainActivity.username,1).show();
		account.setText(FunctionMainActivity.username);
		exit_now = (RelativeLayout) findViewById(R.id.exit_now);
		addAccount = (RelativeLayout) findViewById(R.id.add_account);
		addAccount.setOnClickListener(this);
		exit_now.setOnClickListener(this);
		zaixian = (RelativeLayout) findViewById(R.id.zaixian);
		zaixian.setOnClickListener(this);
		yinshen = (RelativeLayout) findViewById(R.id.yinshen);
		yinshen.setOnClickListener(this);
		zaixianImg = (ImageView) findViewById(R.id.zaixianImg);
		yinshenImg =  (ImageView) findViewById(R.id.yinshenImg);
		yinshenImg.setVisibility(View.INVISIBLE);
	}
}
