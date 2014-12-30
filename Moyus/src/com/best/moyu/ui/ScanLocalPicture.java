package com.best.moyu.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.best.moyu.adapter.CutDownPicture;
import com.best.moyu.baseactivity.BaseActivity;
import com.example.moyu.R;

public class ScanLocalPicture extends BaseActivity {
	public static final String TAG ="moyu";
	private ContentResolver mcontentresolver;
	private List<String> mlistpath = new ArrayList<String>();
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_cut_donw_picture);
			addActivity(this);
			mlistpath.add("1 ");
			mcontentresolver = getContentResolver();
			String str[] = {MediaStore.Images.Media._ID,
					MediaStore.Images.Media.DATA};
			Cursor mcursor = mcontentresolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null,null,null);
			if (mcursor==null){
				Toast.makeText(this, "点击了", 1).show();
			}else {
				Log.i(TAG, mcursor.getCount()+"");
				while (mcursor.moveToNext()){
					String path = mcursor.getString(mcursor.getColumnIndex(MediaStore.Images.Media.DATA));
					mlistpath.add(path);
					
					Log.i(TAG,mlistpath.size()+"----+++++++++"+path);
				}
				Log.i(TAG, mlistpath.size()+"adapter");
				
				CutDownPicture cp = new CutDownPicture(this, mlistpath);
				GridView gv  = (GridView) findViewById(R.id.picture_gridview);
			    gv.setAdapter(cp);
			    gv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Log.i(TAG, mlistpath.get(arg2)+"");
						Intent intent  = new Intent(ScanLocalPicture.this,ClipImageActivity.class);
						intent.putExtra("path",mlistpath.get(arg2));
						startActivity(intent);
						
					}
				
			    
			    });
			}
			
			
		}
}
