package com.best.moyu.fragment;

import com.best.moyu.activity.AccountActivity;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class SettingFragrement extends Fragment implements OnClickListener {
	RelativeLayout rl;
	CheckBox start_and_close_posit;
	ImageButton back;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_setup,  container,false);
		rl = (RelativeLayout) v.findViewById(R.id.account_manager_zhanghao);
		rl.setOnClickListener(this);
		start_and_close_posit = (CheckBox) v.findViewById(R.id.start_and_close_posit);
		back = (ImageButton) v.findViewById(R.id.backto_group);
		return v;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account_manager_zhanghao:
			Intent intent = new Intent(FunctionMainActivity.context,AccountActivity.class);
			startActivity(intent);
			
			
			break;
			
		case R.id.account_manager://账号管理
			
			break;
		case R.id.message_manager://消息管理
		
			break;
		case R.id.start_and_close_posit://关闭或者启动位置服务 点击时需要切换图片（还未处理）
		
			break;
		//更多设置功能以后增加。。。
		default:
			break;
		}
	}
	
}

















/*package com.moyu.fragment;

import com.example.moyu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class SettingFragrement extends Fragment implements OnClickListener {

	CheckBox start_and_close_posit;
	ImageButton back;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_setup,  container,false);
		start_and_close_posit = (CheckBox) v.findViewById(R.id.start_and_close_posit);
		back = (ImageButton) v.findViewById(R.id.backto_group);
		back.setOnClickListener(this);
		return v;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account_manager://账号管理
			
			break;
		case R.id.message_manager://消息管理
		
			break;
		case R.id.start_and_close_posit://关闭或者启动位置服务 点击时需要切换图片（还未处理）
		
			break;
		//更多设置功能以后增加。。。
		default:
			break;
		}
	}
	
}
*/