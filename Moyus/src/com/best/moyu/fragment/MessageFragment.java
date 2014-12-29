package com.best.moyu.fragment;

import com.best.moyu.activity.MessageFragmentChat;
import com.best.moyu.adapter.MessageAdapter;
import com.example.moyu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


/*消息页
 * 聊天*/


public class MessageFragment extends FragmentActivity {
	ListView lv_message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_message);
		lv_message = (ListView) findViewById(R.id.lv_message);
		//initData();
		//创建适配器
		lv_message.setAdapter(new MessageAdapter());
		//点击跳转到聊天界面
		lv_message.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MessageFragment.this,MessageFragmentChat.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	} 

}
