package com.best.moyu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.moyu.R;

import android.content.Context;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class BackImgAdapter extends BaseAdapter{
	int[] path = null;
	Context context;
	public BackImgAdapter(Context context, int[] path) {
	// TODO Auto-generated constructor stub
		this.context = context;
		this.path = path;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return path.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return path[position];
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.bg_item, null);
		ImageView iv = (ImageView) view.findViewById(R.id.bg_chat);
		iv.setImageResource(path[position]);
		return view;
	}
}
