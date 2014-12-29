package com.best.moyu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.best.moyu.activity.FellingDetailActivity;
import com.best.moyu.adapter.FellingAdapter;
import com.best.moyu.data.MyDataActivity;
import com.best.moyu.entity.Share;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

public class FellingFragment extends Fragment implements OnClickListener{
	View v;
	ImageView touxiang;
	ListView lv;
	List<Share> shares = new ArrayList<Share>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_felling, container,false);
		touxiang = (ImageView) v.findViewById(R.id.touxiang);
		lv = (ListView) v.findViewById(R.id.share_list);
		lv = (ListView) v.findViewById(R.id.share_list);
		FellingAdapter fa = new FellingAdapter(FunctionMainActivity.context,shares);
		lv.setAdapter(fa);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 点击跳转到心情详情页
				Intent intent  = new Intent(FunctionMainActivity.context,FellingDetailActivity.class);
				startActivity(intent);
			}
		});

		touxiang.setOnClickListener(this);
		return v;
	}
	@Override
	public void onClick(View v) {
		//个人信息
		switch (v.getId()) {
		case R.id.touxiang:
			 Intent intent = new Intent(FunctionMainActivity.context,MyDataActivity.class);
			 startActivity(intent);
			break;

		default:
			break;
		}
	}

}
