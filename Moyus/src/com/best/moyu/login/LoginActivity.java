package com.best.moyu.login;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.LoginUser;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 登陆页
 * author:袁锦明
 * time:2014.12.23
 * */
public class LoginActivity extends BaseActivity implements OnClickListener{
	TextView register_user,log_log_text;
	EditText log_name;//用户登陆账号
	EditText log_pass;//密码
	CheckBox remember_pass,voluntarily_login;//记住密码和自动登录
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//获取界面
		setContentView(R.layout.activity_logs);
		
		//注册
		register_user = (TextView) findViewById(R.id.register_user);
		//登陆
		log_log_text = (TextView) findViewById(R.id.log_log_text);
		log_name = (EditText) findViewById(R.id.log_name);
		log_pass = (EditText) findViewById(R.id.log_pass);
		remember_pass = (CheckBox) findViewById(R.id.remember_pass);
		voluntarily_login = (CheckBox) findViewById(R.id.voluntarily_login);
		log_log_text.setOnClickListener(this);//点击登录
		register_user.setOnClickListener(this);//点击注册
		
		//判断是否曾登陆过并记住了密码  如有 则调用出账号和密码
		sp = getSharedPreferences("remuser",0);//请求码
		String sp_username = sp.getString("username", "");
		String sp_password = sp.getString("password", "");
		if(sp_username!=""){
			log_name.setText(sp_username);
			log_pass.setText(sp_password);
		}
		addActivity(this);
		
	}
	//点击事件
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.register_user://进入注册页面
			Intent intent = new Intent(LoginActivity.this,RegisterUser.class);
	    	startActivity(intent);
			break;
		case R.id.log_log_text://登录进入主界面
			final String loguser = log_name.getText().toString();//输入登陆账号
			final String logpass = log_pass.getText().toString();//输入密码
			final BmobQuery<LoginUser> lu = new BmobQuery<LoginUser>();
			final LoginUser ll = new LoginUser();
			lu.addWhereEqualTo("Account",Integer.parseInt(loguser));
			lu.addWhereEqualTo("Password",logpass);
			lu.findObjects(this, new FindListener<LoginUser>() {
				@Override
				public void onSuccess(List<LoginUser> arg0) {
					// TODO Auto-generated method stub
					//Toast.makeText(LoginActivity.this,arg0.get(0).getObjectId(),Toast.LENGTH_LONG).show();
					if(arg0.size()>0){
						//若账号和密码正确   判断是否记住密码 若记住密码就创建一个文件用来盛放用户账号和密码
				    	if(remember_pass.isChecked()){
				    		Editor eduser = sp.edit();//创建文件夹
				    		eduser.putString("username",loguser);
				    		eduser.putString("password",logpass);
				    		
				    	}
						//fintent.putExtra("userid",arg0.get(0).getObjectId());
						//startActivity(fintent);
				    	
						//若账号和密码正确 就跳转到主页
						Intent intentmain = new Intent(LoginActivity.this,FunctionMainActivity.class);
				    	startActivity(intentmain);
						System.exit(0);
					}else{
						Toast.makeText(LoginActivity.this,"用户名密码匹配不正确",Toast.LENGTH_LONG).show();
					}
					
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this, "查询失败"+arg1,Toast.LENGTH_LONG).show();
				}
			});

			
			
	    	
			
	    	
			break;
			
		default:
			break;
		}
	}

}





