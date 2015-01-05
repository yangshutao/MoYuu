package com.best.moyu.utils;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageUtils {

	/**
	 * 加载网络图片工具
	 * 
	 * @param context
	 *            上下文
	 * @param iv
	 *            ImageView组件
	 * @param inSampleSize
	 *            缩小比例
	 * @param url
	 *            网络地址
	 */
	public static void viewNetImage(final Context context, final ImageView iv,
			final int inSampleSize, String url) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				Toast.makeText(context, "加载数据失败", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] data) {
				if (arg0 == 200) {
					BitmapFactory.Options opts = new BitmapFactory.Options();
					opts.inSampleSize = inSampleSize;
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length, opts);
					iv.setImageBitmap(bitmap);
//					bitmap.recycle();
				}
			}
		});
	}

	/**
	 * 加载本地图片工具
	 * 
	 * @param iv
	 * @param inSampleSize
	 * @param imagePath
	 */
	public static void viewLocalImage(ImageView iv, int inSampleSize,
			String imagePath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = inSampleSize;
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, opts);
		iv.setImageBitmap(bitmap);
	}
}
