package com.best.moyu.ui;





import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.view.ClipImageLayout;
import com.example.moyu.R;

import android.content.Intent;
import android.os.Bundle;

public class ClipImageActivity extends BaseActivity {
	private ClipImageLayout mClipImageLayout;
	public  static String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		setContentView(R.layout.activity_clipimage);
		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		
		
	}
}
