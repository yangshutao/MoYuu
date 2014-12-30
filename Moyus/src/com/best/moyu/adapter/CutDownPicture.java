package com.best.moyu.adapter;

import java.util.List;

import com.best.moyu.utils.NativeImageLoader;
import com.best.moyu.utils.NativeImageLoader.NativeImageCallBack;
import com.example.moyu.R;

import android.app.NativeActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CutDownPicture extends BaseAdapter {

	Context context;
	List<String> list ;
	private Point mpoint = new Point(100,100);
	public CutDownPicture(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_cut_down_item,null);
		}
		final ImageView iv = (ImageView) convertView.findViewById(R.id.cut_down_pictrue);
		String path = list.get(position);
		Log.i("moyu", ">>>>>>"+position+">>>");
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth()/3;
		mpoint.set(width,width);
		LayoutParams para = iv.getLayoutParams();
		para.width = width;
		para.height = width;
		iv.setLayoutParams(para);
		NativeImageLoader.getInstance().loadNativeImage(path, mpoint,new NativeImageCallBack() {
			
			@Override
			public void onImageLoader(Bitmap bitmap, String path) {
				// TODO Auto-generated method stub
				iv.setImageBitmap(bitmap);
			}
		});
		return convertView;
	}

}
