package com.best.moyu.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.best.moyu.fragment.FellingFragment;
import com.best.moyu.fragment.FriendsFragrement;
import com.best.moyu.fragment.MeetFragment;
import com.best.moyu.fragment.SettingFragrement;
import com.example.moyu.R;

public class FunctionMainActivity extends FragmentActivity implements OnClickListener {
	RadioButton chat,friend,mood,meet,set_up;
	
	//自定颜色
	int Color_whiter = Color.argb(100,255,255,255);
	int Color_balck = Color.argb(255,3,3,3);
	FragmentManager fm;
	LinearLayout ll;
	public static String username,password;
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		context = this;
		//获取密码和账号
		 username = getIntent().getStringExtra("username");
		 password = getIntent().getStringExtra("password");
		chat = (RadioButton) findViewById(R.id.chat);
		friend = (RadioButton) findViewById(R.id.friend);
		mood = (RadioButton) findViewById(R.id.mood);
		meet = (RadioButton) findViewById(R.id.meet);
		set_up = (RadioButton) findViewById(R.id.set_up);
		ll = (LinearLayout) findViewById(R.id.fragment_parent);
		//点击事件
		chat.setOnClickListener(this);
		friend.setOnClickListener(this);
		mood.setOnClickListener(this);
		meet.setOnClickListener(this);
		set_up.setOnClickListener(this);
		
		//Fragemnt切换事件
		if(savedInstanceState ==null){
			fm= getSupportFragmentManager();
			FragmentTransaction ft  =fm.beginTransaction();
			FellingFragment ff =  new FellingFragment();
			ft.add(R.id.fragment_parent, ff, "ff");
			ft.commit();
		}
	}

	@Override
	public void onClick(View arg0) {
		FragmentTransaction ft = fm.beginTransaction();
		
		if (arg0.getId()==R.id.chat||arg0.getId()==R.id.friend
				||arg0.getId()==R.id.mood||arg0.getId()==R.id.meet
				||arg0.getId()==R.id.set_up||arg0.getId()==R.id.set_up){
		
			if (fm.findFragmentByTag("ff")!=null){
				ft.hide(fm.findFragmentByTag("ff"));
			}
			if(fm.findFragmentByTag("Ff")!=null){
				ft.hide(fm.findFragmentByTag("Ff"));
			}
			if(fm.findFragmentByTag("meet")!=null){
				ft.hide(fm.findFragmentByTag("meet"));
			}
			if (fm.findFragmentByTag("set_up")!=null){
				ft.hide(fm.findFragmentByTag("set_up"));
			}
		}
		
		
		switch (arg0.getId()) {
		case R.id.chat:
			if (chat.isChecked()){
				chat.setTextColor(Color_whiter);
				set_up.setTextColor(Color_balck);
				friend.setTextColor(Color_balck);
				mood.setTextColor(Color_balck);
				meet.setTextColor(Color_balck);
			}
			
			break;
		case R.id.friend:
			if (friend.isChecked()){
				friend.setTextColor(Color_whiter);
				set_up.setTextColor(Color_balck);
				chat.setTextColor(Color_balck);
				mood.setTextColor(Color_balck);
				meet.setTextColor(Color_balck);
			}
			if(fm.findFragmentByTag("Ff")!=null){
				ft.show(fm.findFragmentByTag("Ff"));
			}else {
				FriendsFragrement Ff = new FriendsFragrement();
				ft.add(R.id.fragment_parent,Ff,"Ff");
			}
			break;
		case R.id.mood:
			if (mood.isChecked()){
				mood.setTextColor(Color_whiter);
				set_up.setTextColor(Color_balck);
				chat.setTextColor(Color_balck);
				friend.setTextColor(Color_balck);
				meet.setTextColor(Color_balck);
			}
			if(fm.findFragmentByTag("ff")!=null){
				ft.show(fm.findFragmentByTag("ff"));
			} else {
				FellingFragment ff = new FellingFragment();
				ft.add(R.id.fragment_parent, ff,"ff");
				
			}
			break;
		case R.id.meet:
			if (meet.isChecked()){
				meet.setTextColor(Color_whiter);
				set_up.setTextColor(Color_balck);
				chat.setTextColor(Color_balck);
				mood.setTextColor(Color_balck);
				friend.setTextColor(Color_balck);
			}
			if(fm.findFragmentByTag("meet")!=null){
				ft.show(fm.findFragmentByTag("meet"));
			} else {
				MeetFragment mf = new MeetFragment();
				ft.add(R.id.fragment_parent, mf,"meet");
				
			}
			break;
		case R.id.set_up:
			if (set_up.isChecked()){
				set_up.setTextColor(Color_whiter);
				friend.setTextColor(Color_balck);
				chat.setTextColor(Color_balck);
				mood.setTextColor(Color_balck);
				meet.setTextColor(Color_balck);
			}
			if (fm.findFragmentByTag("set_up")!=null){
				ft.show(fm.findFragmentByTag("set_up"));
			}else {
				SettingFragrement sf = new SettingFragrement();
				ft.add(R.id.fragment_parent, sf,"set_up");
			}
			break;
		default:
			break;
		}
		ft.commit();
		
	}

	
}
