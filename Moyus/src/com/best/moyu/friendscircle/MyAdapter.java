package com.best.moyu.friendscircle;

import java.util.ArrayList;

import com.example.moyu.R;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	ArrayList<String> data;
	Context context;

	public MyAdapter(ArrayList<String> list, Context context) {
		data = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.activity_circle_item, null);
			GridView gridView = (GridView) convertView
					.findViewById(R.id.gridView);
			// gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridView.setAdapter(new ImageAdapter(context));
			final TextView tv = (TextView) convertView
					.findViewById(R.id.content);
			final TextView more = (TextView) convertView
					.findViewById(R.id.more);
			final ImageButton button = (ImageButton) convertView
					.findViewById(R.id.imgButton);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				}
			});
			more.getViewTreeObserver().addOnPreDrawListener(
					new OnPreDrawListener() {

						@Override
						public boolean onPreDraw() {
							// TODO Auto-generated method stub
							if (tv.getLineCount() >= 4) {
								more.setVisibility(View.VISIBLE);
							}
							return true;
						}
					});
		}

		return convertView;
	}

	private static class ViewHolder {

	}

}
