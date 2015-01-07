package com.best.moyu.activity;

import com.example.moyu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class ChatWithActivity extends Activity implements OnClickListener{
	CheckBox start_and_close;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chatwith);
		start_and_close = (CheckBox)findViewById(R.id.start_and_close);
		start_and_close.setOnClickListener(this);
	}
	public void backSettingChat(View v){
		this.finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start_and_close://此处可以设置开启和关闭状态的事情
			
			break;

		default:
			break;
		}
	}
}
