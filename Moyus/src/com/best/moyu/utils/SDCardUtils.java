package com.best.moyu.utils;

import java.io.File;

import com.best.moyu.baseactivity.BaseActivity;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class SDCardUtils {
	public static boolean isExistSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	
	
	public SDCardUtils(Context context) {
		
	}



	public static String createAppDir() {
		File file = null;
		if(isExistSDCard()){
			//Toast.makeText(BaseActivity.this,"有SD卡", 1).show();
			file = new File(Environment.getExternalStorageDirectory()
					+ "/moyu");
			if (!file.exists()) {
				file.mkdir();
			}
		}
		return file.getAbsolutePath();
	}
	
}
