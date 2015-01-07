package com.best.moyu.fragment;

import com.best.moyu.activity.AboutUsActivity;
import com.best.moyu.activity.AccountActivity;
import com.best.moyu.activity.ChatBackgroundActivity;
import com.best.moyu.activity.ChatWithActivity;
import com.best.moyu.activity.MessNoActivity;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class SettingFragrement extends Fragment implements OnClickListener {
	RelativeLayout account_manager_zhanghao,aboutUs,chatchat,message_notification,chat_background;
	CheckBox start_and_close_posit;
	ImageButton back;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_setup,  container,false);
		account_manager_zhanghao = (RelativeLayout) v.findViewById(R.id.account_manager_zhanghao);
		account_manager_zhanghao.setOnClickListener(this);
		start_and_close_posit = (CheckBox) v.findViewById(R.id.start_and_close_posit);
		back = (ImageButton) v.findViewById(R.id.backto_group);
		aboutUs = (RelativeLayout) v.findViewById(R.id.aboutUs);
		chatchat = (RelativeLayout) v.findViewById(R.id.chatchat);
		message_notification = (RelativeLayout) v.findViewById(R.id.message_notification);
		chat_background = (RelativeLayout) v.findViewById(R.id.chat_background);
		chat_background.setOnClickListener(this);
		message_notification.setOnClickListener(this);
		aboutUs.setOnClickListener(this);
		chatchat.setOnClickListener(this);
		return v;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account_manager_zhanghao:
			Intent intent = new Intent(getActivity(),AccountActivity.class);
			startActivity(intent);	
			break;
		case R.id.message_manager://消息管理
		
			break;
		case R.id.start_and_close_posit://关闭或者启动位置服务 点击时需要切换图片（还未处理）
		
			break;
		//更多设置功能以后增加。。。
		case R.id.aboutUs://关于我们
			Intent intent2= new Intent(getActivity(),AboutUsActivity.class);
			startActivity(intent2);
			break;
		case R.id.chatchat://聊天记录
			Intent intent3 = new Intent(getActivity(),ChatWithActivity.class);
			startActivity(intent3);
			break;
		case R.id.message_notification://消息通知
			Intent intent4 = new Intent(getActivity(),MessNoActivity.class);
			startActivity(intent4);
			break;
		case R.id.chat_background://聊天背景
			Intent intent5 = new Intent(getActivity(),ChatBackgroundActivity.class);
			startActivity(intent5);
			break;
		default:
			break;
		}
	}
	
}