package com.best.moyu.ui;





import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.data.MyDataActivity;
import com.best.moyu.utils.SDCardUtils;
import com.best.moyu.view.ClipImageLayout;
import com.example.moyu.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClipImageActivity extends BaseActivity implements OnClickListener {
	private ClipImageLayout mClipImageLayout;
	public  static String path;
	Button quxiao,xuanze;
	private String mpath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		setContentView(R.layout.activity_clipimage);
		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		quxiao = (Button) findViewById(R.id.quxiao_c);
		xuanze = (Button) findViewById(R.id.chose_c);
		quxiao.setOnClickListener(this);
		xuanze.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.quxiao_c:
			finish();
			break;
		case R.id.chose_c:
			//判断是否有sd卡
			if (SDCardUtils.isExistSDCard()) {
				mpath = SDCardUtils.createAppDir();
			}else{
				mpath = this.getFilesDir().getPath();
			}
			String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".png";
			name ="moyu_"+name;
			File file= new File(mpath+"/Moyu/Image/"); ;
			FileOutputStream fileoutputSteam = null;
			//判断文件夹是否存在
			if (!file.exists()){
				file.mkdirs();//创建文件夹
			}
			String fileName = mpath+"/Moyu/Image/"+name;
			Bitmap bitmap;
			bitmap = mClipImageLayout.clip();
			try {
				  //将图片打包为文件
				  fileoutputSteam = new FileOutputStream(fileName);
				  //进行压缩处理
	              bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileoutputSteam);// 把数据写入文件  
	          } catch (FileNotFoundException e) {  
	              e.printStackTrace();  
	          } finally {  
	              try {  
	                  fileoutputSteam.flush();  
	                  fileoutputSteam.close();  
	              } catch (IOException e) {  
	                  e.printStackTrace();  
	              }  
	          }
			Intent intent = new Intent(this,MyDataActivity.class);
			intent.putExtra("imge", bitmap);
			intent.putExtra("imagepath",fileName);
			setResult(RESULT_OK, intent);
			finish();
			break;
		default:
			break;
		}
		
	}
}
