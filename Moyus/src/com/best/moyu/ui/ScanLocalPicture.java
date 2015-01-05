package com.best.moyu.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.data.MyDataActivity;
import com.example.moyu.R;
import com.example.stickyheadergridview.GridItem;
import com.example.stickyheadergridview.ImageScanner;
import com.example.stickyheadergridview.ImageScanner.ScanCompleteCallBack;
import com.example.stickyheadergridview.StickyGridAdapter;
import com.example.stickyheadergridview.YMComparator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ScanLocalPicture extends BaseActivity {
	// private ProgressDialog mProgressDialog;
	private ImageScanner mScanner;
	private GridView mGridView;
	private List<GridItem> mGirdList = new ArrayList<GridItem>();
	private static int section = 1;
	private Map<String, Integer> sectionMap = new HashMap<String, Integer>();

	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
			.cacheOnDisc(false).considerExifParams(true)
			.bitmapConfig(Bitmap.Config.ARGB_8888).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanpicture);
		addActivity(this);
		if (!imageLoader.isInited())
			imageLoader.init(ImageLoaderConfiguration
					.createDefault(ScanLocalPicture.this));
		imageLoader.clearDiscCache();

		mGridView = (GridView) findViewById(R.id.asset_grid);
		mScanner = new ImageScanner(this);

		mScanner.scanImages(new ScanCompleteCallBack() {
			{
				// mProgressDialog = ProgressDialog.show(MainActivity.this, "",
				// "正在加载...");
			}

			@Override
			public void scanComplete(Cursor cursor) {
				// 关闭进度条
				// mProgressDialog.dismiss();

				while (cursor.moveToNext()) {
					// 获取图片的路径
					String path = cursor.getString(cursor
							.getColumnIndex(MediaStore.Images.Media.DATA));
					long times = cursor.getLong(cursor
							.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));

					GridItem mGridItem = new GridItem(path,
							paserTimeToYM(times));
					mGirdList.add(mGridItem);

				}
				cursor.close();
				Collections.sort(mGirdList, new YMComparator());

				for (ListIterator<GridItem> it = mGirdList.listIterator(); it
						.hasNext();) {
					GridItem mGridItem = it.next();
					String ym = mGridItem.getTime();
					if (!sectionMap.containsKey(ym)) {
						mGridItem.setSection(section);
						sectionMap.put(ym, section);
						section++;
					} else {
						mGridItem.setSection(sectionMap.get(ym));
					}
				}

				mGridView.setAdapter(new StickyGridAdapter(ScanLocalPicture.this,
						mGirdList, mGridView, imageLoader,options));
				mGridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent intent  = new Intent(ScanLocalPicture.this,MyDataActivity.class);
						intent.putExtra("path",mGirdList.get(arg2).getPath());
						setResult(RESULT_OK,intent);
						finish();
					}
				});
			}
		});
	}

	public static String paserTimeToYM(long time) {
		System.setProperty("user.timezone", "Asia/Shanghai");
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(new Date(time * 1000L));
	}

}