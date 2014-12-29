package com.best.moyu.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatHistorySql extends SQLiteOpenHelper{
    public  static final String  DB_NAME="the_chat_record.db";
    public  static final String  TABLE="chathistry";
    //头像 昵称 内容 时间 
    public  static final int version =1;
    private Context mcontext;
	public ChatHistorySql(Context context) {
		super(context,DB_NAME,null,version);
		this.mcontext = context;
	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table "+TABLE+"( _id integer primary key autoincrement,head_sculpture text,nick_name text,time text,history text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion>oldVersion){
			db.execSQL("DROP TABLE IF EXISTS "+TABLE);
			String sql = "create table "+TABLE+"( _id integer primary key autoincrement,head_sculpture text,nick_name text,time text,history text)";
			db.execSQL(sql);
		}
		
	}

}
