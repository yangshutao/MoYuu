package com.best.moyu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	//时间转换
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(date);
	}
}
