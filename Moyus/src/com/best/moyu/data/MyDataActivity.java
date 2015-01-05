package com.best.moyu.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.ui.ClipImageActivity;
import com.best.moyu.ui.ScanLocalPicture;
import com.best.moyu.utils.SDCardUtils;
import com.example.moyu.R;

/*
 * 个人信息详情页
 * 其中点击头像可以更换头像
 * */
public class MyDataActivity extends BaseActivity implements OnClickListener{
	ImageView xiangxixinxi;
	RadioButton bianjiziliao;
	Button carema,album,give_up;
	public String path;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mydata);
		xiangxixinxi = (ImageView) findViewById(R.id.xiangxixinxi);
		bianjiziliao = (RadioButton) findViewById(R.id.bianjiziliao);
		bianjiziliao.setOnClickListener(this);
		xiangxixinxi.setOnClickListener(this);
		
		
		
		
		
		Intent in = getIntent();
		
		if(in.getParcelableExtra("imge")!=null){
			xiangxixinxi.setImageBitmap((Bitmap)in.getParcelableExtra("imge"));
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.bianjiziliao:
			Intent intent = new Intent(MyDataActivity.this,EditMyDataActivity.class);
			startActivity(intent);
			break;
		case R.id.xiangxixinxi:
			//调用虚拟更换头像按键
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
				startActivityForResult(intent, 1);
			}
		});
		album.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MyDataActivity.this,ScanLocalPicture.class);
				startActivity(intent);
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
	switch (requestCode) {
	case 1:
		
		 //判断是否有SD卡
		 String sdStatus = Environment.getExternalStorageState();
		 if (!sdStatus.equals(Environment.MEDIA_MOUNTED)){
			 return;
		 }
		 //为随机给名字作的前提条件，获取的是年月日时分秒，逗号后面的是时区
		 String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";     
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
              bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件  
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
          startActivity(intent);
          finish();
		break;

	default:
		break;
	}
	   
	   
	   
	   
	   
	   
	
}
}
