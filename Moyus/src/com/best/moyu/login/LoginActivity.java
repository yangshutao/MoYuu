package com.best.moyu.login;
import org.json.JSONObject;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.OtherLoginListener;
import cn.bmob.v3.listener.SaveListener;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.LoginUser;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
	ProgressDialog pd;
	static ImageView tv_qq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//获取界面
		setContentView(R.layout.activity_logs);
		addActivity(this);
		//Bmob秘钥
		
		Bmob.initialize(this,"6a82445c5203b53ba1acffbf57d2f5cb");
		//注册
		register_user = (TextView) findViewById(R.id.register_user);
		//登陆
		log_log_text = (TextView) findViewById(R.id.log_log_text);
		log_name = (EditText) findViewById(R.id.log_name);
		log_pass = (EditText) findViewById(R.id.log_pass);
		remember_pass = (CheckBox) findViewById(R.id.remember_pass);
		voluntarily_login = (CheckBox) findViewById(R.id.voluntarily_login);
		tv_qq = (ImageView) findViewById(R.id.tv_qq);
		log_log_text.setOnClickListener(this);//点击登录
		register_user.setOnClickListener(this);//点击注册
		tv_qq.setOnClickListener(this);//第三方
		//判断是否曾登陆过并记住了密码  如有 则调用出账号和密码
		sp = getSharedPreferences("remuser",0);//请求码
		String sp_username = sp.getString("username", "");
		String sp_password = sp.getString("password", "");
		if(sp_username!=""){
			log_name.setText(sp_username);
			log_pass.setText(sp_password);
		}
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
			pd = new ProgressDialog(this);
			pd.setMessage("正在登陆中，请稍后；");
			pd.show();
			
			final String loguser = log_name.getText().toString();//输入登陆账号
			final String logpass = log_pass.getText().toString();//输入密码
			
			//final BmobQuery<LoginUser> lu = new BmobQuery<LoginUser>();
			
			LoginUser lu = new LoginUser();
			//lu.setAccount(Integer.parseInt(loguser));
			lu.setUsername(loguser);
			lu.setPassword(logpass);
			
			lu.login(this,new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO 自动生成的方法存根
					pd.dismiss();
					//若账号和密码正确   判断是否记住密码 若记住密码就创建一个文件用来盛放用户账号和密码
			    	if(remember_pass.isChecked()){
			    		Editor eduser = sp.edit();//创建文件夹
			    		eduser.putString("username",loguser);
			    		eduser.putString("password",logpass);
			    		eduser.commit();
			    	}
			    	pd.dismiss();
					//若账号和密码正确 就跳转到主页
					Intent intentmain = new Intent(LoginActivity.this,FunctionMainActivity.class);
				//	intentmain.putExtra(name, value)
					intentmain.putExtra("username",loguser);
			    	intentmain.putExtra("password",logpass);
					startActivity(intentmain);
			    	finish();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO 自动生成的方法存根
					pd.dismiss();
					alert("Error!"+arg1);
				}
			});
			/*lu.login(this, new FindListener<LoginUser>() {
				@Override
				public void onSuccess(List<LoginUser> arg0) {
					if(arg0.size()>0){
						//若账号和密码正确   判断是否记住密码 若记住密码就创建一个文件用来盛放用户账号和密码
				    	if(remember_pass.isChecked()){
				    		Editor eduser = sp.edit();//创建文件夹
				    		eduser.putString("username",loguser);
				    		eduser.putString("password",logpass);
				    		eduser.commit();
				    	}
				    	pd.dismiss();
						//若账号和密码正确 就跳转到主页
						Intent intentmain = new Intent(LoginActivity.this,FunctionMainActivity.class);
				    	startActivity(intentmain);
						
					}else{
						pd.dismiss();
						Toast.makeText(LoginActivity.this,"用户名密码匹配不正确！",Toast.LENGTH_LONG).show();
						
					}
				}
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					//Toast.makeText(LoginActivity.this, "查询失败"+arg1,Toast.LENGTH_LONG).show();
					alert("网络错误，请检查网络重试！");
				}
			});*/

			break;
		case R.id.tv_qq:
			//222222--appid,此为腾讯官方提供的AppID,个人开发者需要去QQ互联官网为自己的应用申请对应的AppId
			BmobUser.qqLogin(this, "222222",new OtherLoginListener() {
				@Override
				public void onSuccess(JSONObject userAuth) {
					// TODO Auto-generated method stub
					alert("QQ登陆成功返回:"+userAuth.toString());
					//Log.i("login", "QQ登陆成功返回:"+userAuth.toString());
					//下面则是返回的json字符
					//如果你想在登陆成功之后关联当前用户
					/*Intent intent = new Intent(LoginActivity.this, MainActivityDiSanFang.class);
					intent.putExtra("json", userAuth.toString());
					intent.putExtra("from", "weibo");
					startActivity(intent);*/
				}
				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					alert("第三方登陆失败："+msg);
				}
				@Override
				public void onCancel() {
				}
			});
			break;
		default:
			break;
		}
	}

}





