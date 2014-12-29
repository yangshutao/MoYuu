package com.best.moyu.fragment;

import com.best.moyu.activity.GroupActivity;
import com.example.moyu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class FriendsFragrement extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_friends, container,false);
		v.findViewById(R.id.group_btn);
		return v;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.group_btn:
			Intent intent = new Intent(getActivity(),GroupActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
 
}
