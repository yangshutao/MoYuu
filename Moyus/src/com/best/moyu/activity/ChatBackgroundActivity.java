package com.best.moyu.activity;


import com.best.moyu.adapter.BackImgAdapter;
import com.example.moyu.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ChatBackgroundActivity extends Activity{
	Context context;
	GridView gridView_bg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chatbg);
		context = this;
		gridView_bg = (GridView) findViewById(R.id.gridView_bg);
		int[] images = {R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5,R.drawable.bg6,R.drawable.bg7,R.drawable.bg8,R.drawable.bg9,R.drawable.bg9};
		BackImgAdapter back = new BackImgAdapter(context,images);
		gridView_bg.setAdapter(back);
		gridView_bg.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//此处把聊天背景换掉
			}
		});
	}
	public void backSettingBg(View v){
		this.finish();
	}
}
