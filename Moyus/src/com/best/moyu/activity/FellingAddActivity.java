package com.best.moyu.activity;
import android.os.Bundle;

import com.best.moyu.baseactivity.BaseActivity;
import com.example.moyu.R;

public class FellingAddActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_felling_add);
		addActivity(this);
	}
}
