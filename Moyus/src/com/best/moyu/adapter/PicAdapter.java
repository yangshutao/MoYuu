package com.best.moyu.adapter;

import java.util.List;

import com.best.moyu.entity.Pic;

import com.example.moyu.R;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PicAdapter extends BaseAdapter {
	Context context;
	List<Pic> pics;
	
	public PicAdapter(Context context, List<Pic> pics) {
		this.context = context;
		this.pics = pics;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pics.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return pics.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		final ViewHolder vh;
		if(arg1 == null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.fragment_felling_add_biaoqing_item, null);
			vh = new ViewHolder();
			vh.iv = (ImageView) arg1.findViewById(R.id.image_app);
			
			arg1.setTag(vh);
		}else {
			vh = (ViewHolder) arg1.getTag();
		}
		
		vh.iv.setImageResource(Integer.parseInt(pics.get(arg0).getRID()));
		return arg1;
	}
	static class ViewHolder{
		ImageView iv;
	}
}
