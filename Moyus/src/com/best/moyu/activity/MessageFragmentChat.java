package com.best.moyu.activity;

import com.best.moyu.baseactivity.BaseActivity;
import com.example.moyu.R;

import android.app.Activity;
import android.os.Bundle;

public class MessageFragmentChat extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_message_chat);
		addActivity(this);
	}
}
