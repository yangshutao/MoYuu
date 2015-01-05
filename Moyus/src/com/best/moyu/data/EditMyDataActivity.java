package com.best.moyu.data;


import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.LoginUser;
import com.best.moyu.entity.UserInfo;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditMyDataActivity extends BaseActivity implements OnClickListener{
	EditText edit_nicheng,edit_sex,edit_year,edit_Emile,edit_phone,edit_zhenshi_name,edit_underWrite;
	TextView baocun_shuju,quxiao;
	ProgressDialog pd,pd1;
	static com.best.moyu.entity.UserInfo ui;
	static LoginUser lu;
	static String logUserId ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//主界面
		setContentView(R.layout.activity_editmydata);
		//假如BaseActivity
		addActivity(this);
		Bmob.initialize(this,"6a82445c5203b53ba1acffbf57d2f5cb");
		
		edit_nicheng = (EditText) findViewById(R.id.edit_nicheng);
		edit_sex = (EditText) findViewById(R.id.edit_sex);
		edit_year = (EditText) findViewById(R.id.edit_year);
		edit_Emile = (EditText) findViewById(R.id.edit_Emile);
		edit_phone = (EditText) findViewById(R.id.edit_phone);
		edit_zhenshi_name = (EditText) findViewById(R.id.edit_zhenshi_name);
		edit_underWrite = (EditText) findViewById(R.id.edit_underWrite);//个性签名
		baocun_shuju = (TextView) findViewById(R.id.baocun_shuju);
		baocun_shuju.setOnClickListener(this);
		
		ui = new com.best.moyu.entity.UserInfo();
		//正则表达式
		edit_Emile.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO 自动生成的方法存根
				if (arg0.getId() == EditMyDataActivity.this.edit_Emile
						.getId()) {
					if (!arg1) {
						if (EditMyDataActivity.this.edit_Emile
								.getText().length() > 0) { // 判断输入数据长度
							String msg = EditMyDataActivity.this.edit_Emile
									.getText().toString(); // 取出已输入的内容
							// 正则表达式
							if (msg.matches("\\w+@\\w+\\.\\w+")) { // 判断是否是email
								alert("邮箱格式正确");
							} else {
								alert("邮箱格式不正确");
							}
						} else {
							alert("邮箱格内容不能为空");
						}
					}
				}
			}
		});
		//获取当前用户
		lu = BmobUser.getCurrentUser(this,LoginUser.class);
		//获取LogUserId 作为外键的形式
		logUserId = lu.getObjectId();
		//alert("LogUserID"+logUserId+"");		
		
		//查询表
		final  BmobQuery<UserInfo> uiId = new BmobQuery<UserInfo>();
		//uiId.addWhereEqualTo("LoginUserId",logUserId);
		uiId.getObject(this, logUserId, new GetListener<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo arg0) {
				// TODO 自动生成的方法存根
				alert("查询正确");
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				edit_nicheng.setHint("");
				edit_sex.setHint("");
				edit_year.setHint("");
				edit_Emile.setHint("");
				edit_phone.setHint("");
				edit_zhenshi_name.setHint("");
				edit_underWrite.setHint("");
			}
		});
		/*uiId.findObjects(this,new FindListener<UserInfo>() {
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				alert("查询失败:无此人信息!"+arg1+"");
				edit_nicheng.setHint("");
				edit_sex.setHint("");
				edit_year.setHint("");
				edit_Emile.setHint("");
				edit_phone.setHint("");
				edit_zhenshi_name.setHint("");
				edit_underWrite.setHint("");
			}
			
			@Override
			public void onSuccess(List<UserInfo> arg0) {
				// TODO 自动生成的方法存根
				//if(logUserId!=null && logUserId!=""){
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO 自动生成的方法存根
						uiId.findObjects(EditMyDataActivity.this, new FindListener<UserInfo>() {
							@Override
							public void onSuccess(List<UserInfo> arg0) {
								// TODO 自动生成的方法存根
								alert("查询成功"+arg0.size()+"");
								//alert("查询成功"+arg0.get(0).getNickName()+"~~~");
								//edit_nicheng.setHint(arg0.get(0).getNickName());
								//edit_sex.setHint(arg0.get(0).getSex());
								//edit_year.setHint(arg0.get(0).getAge()+"");
								//edit_Emile.setHint(arg0.get(0).getMailBox());
								//edit_phone.setHint(arg0.get(0).getPhone()+"");
								//edit_zhenshi_name.setHint(arg0.get(0).getRealName());
								//edit_underWrite.setHint(arg0.get(0).getUnderWrite());
							}
							@Override
							public void onError(int arg0, String arg1) {
								// TODO 自动生成的方法存根
								Toast.makeText(EditMyDataActivity.this, "读取数据错误!请检查网络重新获取。。。"+arg1, 1).show();
							}
						});
					}
				}).start();
			}
		});*/
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		switch (arg0.getId()) {
		case R.id.baocun_shuju:
			pd = new ProgressDialog(EditMyDataActivity.this);
			pd.setMessage("保存中,请稍后....");
			pd.show();
			//用户登录注册表
			//昵称
			String pre_nicheng = edit_nicheng.getText().toString();
			//性别
			String pre_sex = edit_sex.getText().toString();
			String pre_year = edit_year.getText().toString();//number型
			String pre_emile = edit_Emile.getText().toString();
			String pre_phone = edit_phone.getText().toString();
			String pre_zhenshi_name = edit_zhenshi_name.getText().toString();
			String pre_underWrite = edit_underWrite.getText().toString();
			
			//alert("LogUserID"+logUserId+"");
			//用户详情表实体类
			
			//添加信息
			ui.setLoginUserId(logUserId);
			//System.out.println("ID"+logUserId+"");	//ID
			ui.setNickName(pre_nicheng);
			ui.setAge(Integer.parseInt(pre_year));
			ui.setSex(pre_sex);
			ui.setMailBox(pre_emile);
			ui.setPhone(Integer.parseInt(pre_phone));
			ui.setRealName(pre_zhenshi_name);
			ui.setUnderWrite(pre_underWrite);
			//alert(pre_nicheng+"~~~~~"+pre_sex);
			//alert(ui.getNickName()+"~~~~~");
			ui.save(this,new SaveListener() {
				@Override
				public void onSuccess() {
					// TODO 自动生成的方法存根
					//alert("保存成功");
					Toast.makeText(EditMyDataActivity.this, "保存成功！", 1).show();
					pd.dismiss();
					//保存成功后跳转到详细信息页面
					Intent is = new Intent(EditMyDataActivity.this,FunctionMainActivity.class);
					startActivity(is);
					finish();
				}
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO 自动生成的方法存根
					pd.dismiss();
					alert("保存失败，请重试！"+arg1);
				}
			});
			/*if(pre_nicheng !="" && pre_nicheng!=null){
				if(pre_sex!="" && pre_sex!=null){
					if(pre_year!="" && pre_year!=null){
						if(pre_emile!="" && pre_emile!=null){
							if(pre_phone!="" && pre_phone!=null){
								if(pre_zhenshi_name!="" && pre_zhenshi_name!=null){
									if(pre_underWrite!="" && pre_underWrite!=null){
										//判断正确后开始添加信息
										ui.save(this,new SaveListener() {
											@Override
											public void onSuccess() {
												// TODO 自动生成的方法存根
												//alert("保存成功");
												Toast.makeText(EditMyDataActivity.this, "保存成功！", 1).show();
												pd.dismiss();
												//保存成功后跳转到详细信息页面
												Intent is = new Intent(EditMyDataActivity.this,FunctionMainActivity.class);
												startActivity(is);
												finish();
											}
											@Override
											public void onFailure(int arg0, String arg1) {
												// TODO 自动生成的方法存根
												pd.dismiss();
												alert("保存失败，请重试！"+arg1);
											}
										});
									}else{
										alert("心情不可为空!");
									}
									
								}else{
									alert("真实姓名不可为空!");
								}
							}else{
								alert("电话不可为空!");
							}
						}else{
							alert("邮箱不可为空!");
						}
					}else{
						alert("年龄不可为空!");
					}
				}else{
					alert("性别不可为空!");
				}
			}else{
				alert("昵称不可为空!");
			}*/
			
			break;
		default:
			break;
		}
	}
	
	
	
}