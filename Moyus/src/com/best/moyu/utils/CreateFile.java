package com.best.moyu.utils;

import java.io.File;
import java.util.logging.FileHandler;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class CreateFile {
	Context context;
	FileHelper filehelper;
	private String pathString = Environment.getExternalStorageDirectory().getPath();
	private String name=pathString+"//Moyu";
	private String img = "//Image";
    public CreateFile(Context context){
		filehelper = new FileHelper(context);
		//name = pathString+name;
		File fname = new File(name);
		File fimg = new File(name+img);
		Log.i("moyu", fimg+">>>>>>>>..-----------------");
		//判断文件夹是否存在
		if(!fname.exists()){
			filehelper.createSDDir(name); 	
			Log.i("moyu","===qqq");
		}
		if(!fimg.exists()){
			filehelper.createSDDir(name);
			Log.i("moyu","===q");
		}
		
		Log.i("moyu",pathString+"dddddd");
		Log.i("moyu",fname.exists()+">>>>>>>>>>>"+fimg.exists()+"lallala");
		
	}
	/*public void CreateSdDir(){
		if(Environment.getExternalStorageDirectory().getPath()==null){
			name = "//moyu";
			Log.i("moyu",name);
		}else{ 
			name = "moyu";
			Log.i("moyu",name);
		}
		name = pathString+name;
		File fname = new File(name);
		File fimg = new File(name+img);
		Log.i("moyu", fimg+"-----------------");
		//判断文件夹是否存在
		if(!fname.exists()){
			createSDDir(name);
			Log.i("moyu","===qqq");
		}
		if(!fimg.exists()){
			createSDDir(name+img);
			Log.i("moyu","===q");
		}
		
		Log.i("moyu",pathString+"dddddd");
		Log.i("moyu",fname.exists()+"lallala");
		
	}
	public File createSDDir(String dirName){
		File dir = new File(pathString + dirName);
		Log.i("moyu",dir+"nicaiciacccccccccccc");
		dir.mkdir();
		return dir;
	}*/
}
