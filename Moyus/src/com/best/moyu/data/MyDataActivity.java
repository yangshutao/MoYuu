package com.best.moyu.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.best.moyu.baseactivity.BaseActivity;
import com.example.moyu.R;

/*
 * 个人信息详情页
 * 其中点击头像可以更换头像
 * */
public class MyDataActivity extends BaseActivity implements OnClickListener{
	ImageView xiangxixinxi;
	RadioButton bianjiziliao;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mydata);
		xiangxixinxi = (ImageView) findViewById(R.id.xiangxixinxi);
		bianjiziliao = (RadioButton) findViewById(R.id.bianjiziliao);
		bianjiziliao.setOnClickListener(this);
		xiangxixinxi.setOnClickListener(this);
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
			openOptionsMenu();
			
			break;
		default:
			break;
		}
	}
	//更换头像点击事件
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
