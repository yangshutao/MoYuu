package com.best.moyu.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.LoginUser;
import com.best.moyu.entity.UserInfo;
import com.best.moyu.ui.ClipImageActivity;
import com.best.moyu.ui.FunctionMainActivity;
import com.best.moyu.ui.ScanLocalPicture;
import com.best.moyu.utils.SDCardUtils;
import com.example.moyu.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditMyDataActivity extends BaseActivity implements OnClickListener{
	EditText edit_nicheng,edit_sex,edit_year,edit_Emile,edit_phone,edit_zhenshi_name,edit_underWrite;
	TextView baocun_shuju,quxiao;
	ProgressDialog pd,pd1;
	static UserInfo ui;
	static LoginUser lu;
	static String logUserId ;
	RadioButton sex_man,sex_woman;
	ImageView headimage;
	Button carema,album,give_up;
	public String path,path2;
	String pre_year,pre_emile,pre_phone,pre_zhenshi_name,pre_underWrite,pre_nicheng,pre_sex;
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
		//edit_sex = (EditText) findViewById(R.id.edit_sex);
		sex_man = (RadioButton) findViewById(R.id.sex_man);
		sex_woman = (RadioButton) findViewById(R.id.sex_wom);
		edit_year = (EditText) findViewById(R.id.edit_year);
		edit_Emile = (EditText) findViewById(R.id.edit_Emile);
		edit_phone = (EditText) findViewById(R.id.edit_phone);
		edit_zhenshi_name = (EditText) findViewById(R.id.edit_zhenshi_name);
		edit_underWrite = (EditText) findViewById(R.id.edit_underWrite);//个性签名
		baocun_shuju = (TextView) findViewById(R.id.baocun_shuju);
		headimage = (ImageView) findViewById(R.id.headimage);
		baocun_shuju.setOnClickListener(this);
		headimage.setOnClickListener(this);
		
		ui = new UserInfo();
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
				//edit_sex.setHint("");
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
		    pre_nicheng = edit_nicheng.getText().toString();
			//性别
			
			if(sex_man.isChecked()){
				pre_sex = "男";
			}else{
				pre_sex = "女";
			}
			
			//if(sex_man.getc)
			//String pre_sex = edit_sex.getText().toString();
			pre_year = edit_year.getText().toString();//number型
			pre_emile = edit_Emile.getText().toString();
			pre_phone = edit_phone.getText().toString();
			pre_zhenshi_name = edit_zhenshi_name.getText().toString();
			pre_underWrite = edit_underWrite.getText().toString();
			//String pre_headImg = headimage.getDrawable()+"";
			//alert("LogUserID"+logUserId+"");
			//用户详情表实体类
			//添加信息
			final BmobFile bmobFile = new BmobFile(new File(path2));
			bmobFile.upload(this,new UploadFileListener() {
				@Override
				public void onSuccess() {
					ui.setLoginUserId(logUserId);
					ui.setNickName(pre_nicheng);
					ui.setAge(Integer.parseInt(pre_year));
					ui.setSex(pre_sex);
					ui.setMailBox(pre_emile);
					ui.setPhone(Integer.parseInt(pre_phone));
					ui.setRealName(pre_zhenshi_name);
					ui.setUnderWrite(pre_underWrite);
					
					ui.setHeadImage(bmobFile.getFileUrl());
					ui.save(EditMyDataActivity.this, new SaveListener() {
						
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
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					pd.dismiss();
					alert("上传失败"+arg1);
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
		case R.id.headimage:
			showDialog();
			
			break;
		default:
			break;
		}
	}
	//更换头像点击事件
	private void showDialog() {
				View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
				carema = (Button) view.findViewById(R.id.camera);
				album = (Button) view.findViewById(R.id.album);
				give_up = (Button) view.findViewById(R.id.give_up);
				final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
				dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT));
				Window window = dialog.getWindow();
				// 设置显示动画
				window.setWindowAnimations(R.style.main_menu_animstyle);
				WindowManager.LayoutParams wl = window.getAttributes();
				wl.x = 0;
				wl.y = getWindowManager().getDefaultDisplay().getHeight();
				// 以下这两句是为了保证按钮可以水平满屏
				wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
				wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				// 设置显示位置
				dialog.onWindowAttributesChanged(wl);
				// 设置点击外围解散
				dialog.setCanceledOnTouchOutside(true);
				carema.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						//调用本地相册
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(intent, 2);
					}
				});
				album.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						Intent intent = new Intent(EditMyDataActivity.this,ScanLocalPicture.class);
						//startActivity(intent);
						intent.putExtra("jude",1);
						startActivityForResult(intent,1);
					}
				});
				
				give_up.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						
					}
				});
				dialog.show();
			}
		   @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			alert(requestCode+"");
			if (resultCode==RESULT_OK){
			switch (requestCode) {
				case 1:
					Intent intent2 = new Intent(this,ClipImageActivity.class);
					intent2.putExtra("path", data.getStringExtra("path"));
					startActivityForResult(intent2, 3);
					break;
				case 2:
					 //判断是否有SD卡
					 String sdStatus = Environment.getExternalStorageState();
					 if (!sdStatus.equals(Environment.MEDIA_MOUNTED)){
						 return;
					 }
					 //为随机给名字作的前提条件，获取的是年月日时分秒，逗号后面的是时区
					 String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".png";     
			         //alert(name);
			         //获得调用本地相机获得的图片
			         Bundle bundle = data.getExtras();  
			         Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
			         FileOutputStream b = null;  
			         //此处应该放入本地相册  
			          String Paths = "/sdcard/DCIM/100ANDRO/"; 
			          File file = new File("/sdcard/DCIM/100ANDRO/"); 
			          file.mkdirs();// 创建文件夹  
			         //图片存储的路径
			          String fileName = "/sdcard/DCIM/100ANDRO/"+name;  

			          try {  
			              b = new FileOutputStream(fileName);  
			              bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件  
			          } catch (FileNotFoundException e) {  
			              e.printStackTrace();  
			          } finally {  
			              try {  
			                  b.flush();  
			                  b.close();  
			              } catch (IOException e) {  
			                  e.printStackTrace();  
			              }  
			          } 
			          Intent intent = new Intent(this,ClipImageActivity.class);
			          intent.putExtra("path", fileName);
			          startActivityForResult(intent,3);
					break;
				case 3:
					path2 = data.getStringExtra("imagepath");
					Bitmap bitmp = BitmapFactory.decodeFile(path2);
					FileOutputStream files ;
					
				try {
					files =new FileOutputStream(path2);
					bitmp.compress(CompressFormat.PNG, 100, files);
					files.flush();
					files.close();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					headimage.setImageBitmap(bitmp);
					
					break;
				default:
					break;
				}
			}	
		}
	
}