package com.best.moyu.data;

import android.os.Bundle;
import android.view.Window;

import com.best.moyu.baseactivity.BaseActivity;
import com.example.moyu.R;

public class EditMyDataActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addActivity(this);
		setContentView(R.layout.activity_editmydata);
	}
}
