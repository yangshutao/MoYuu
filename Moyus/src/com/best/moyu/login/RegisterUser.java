package com.best.moyu.login;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.LoginUser;
import com.example.moyu.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 用户注册页
 * author:袁锦明
 * */
public class RegisterUser extends BaseActivity implements OnClickListener{
	TextView register_next_text,register_back_text;//下一步和返回
	EditText register_name,register_pass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bmob.initialize(this,"6a82445c5203b53ba1acffbf57d2f5cb");
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_registeruser);
		addActivity(this);
		register_next_text = (TextView) findViewById(R.id.register_next_text);
		register_back_text = (TextView) findViewById(R.id.register_back_text);
		register_name = (EditText) findViewById(R.id.register_name);
		register_pass = (EditText) findViewById(R.id.register_pass);
		//下一步
		register_next_text.setOnClickListener(this);
		//返回
		register_back_text.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//开始注册并返回到登陆页面开始登陆
		case R.id.register_next_text:
			//获取输入的账号
			String user_name = register_name.getText().toString();
			//获取输入的密码
			String user_pass = register_pass.getText().toString();
			//实体类
			LoginUser loginUser = new LoginUser();
			
			loginUser.setAccount(Integer.parseInt(user_name));
			loginUser.setPassword(user_pass);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
				}
			});
			loginUser.save(this,new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					Toast.makeText(RegisterUser.this,"恭喜您,注册成功!",1).show();
					//注册完成返回到登陆页面
					Intent intent = new Intent(RegisterUser.this,LoginActivity.class);
					startActivity(intent);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(RegisterUser.this,"注册失败,请重新注册!",1).show();
				}
			});
			
			
			
			break;
			//返回到登陆页面
		case R.id.register_back_text:
			Intent fanhuiid = new Intent(this,LoginActivity.class);
			startActivity(fanhuiid);
		default:
			break;
		}
		
		
	}
	
	
}
