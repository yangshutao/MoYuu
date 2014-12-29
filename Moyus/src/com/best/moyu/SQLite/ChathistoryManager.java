package com.best.moyu.SQLite;

import com.best.moyu.entity.ChatInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ChathistoryManager {
	private	ChatHistorySql mchathistorysql;
	
	private Context mcontext;

	public ChathistoryManager(Context mcontext) {
		this.mchathistorysql = new ChatHistorySql(mcontext);
		this.mcontext = mcontext;
	}
	/**
	 * 什么是事务： 把多个操作定义为一个具有完整性，原子性，隔离性，一致性的操作，称为一个事务。 步骤：
	 * 1、 db.beginTransaction(); 开始事务 
	 * 2、 执行多个SQL语句
	 * 3、db.setTransactionSuccessful();设置事务成功标记
	 * 4、 endTransaction(); 结束事务
	 */
	//添加聊天记录
	public void insertchathistory(ChatInfo chatinfo){
		SQLiteDatabase db= mchathistorysql.getWritableDatabase();
		db.beginTransaction();
		
		ContentValues cv = new ContentValues();
		cv.put("head_sculpture",chatinfo.getMheadsculpture());
		cv.put("nickname",chatinfo.getMnickname());
		cv.put("time",chatinfo.getMtime());
		cv.put("chathistory", chatinfo.getChathistory());
		db.insert(ChatHistorySql.TABLE,null,cv);
		db.close();db.setTransactionSuccessful();
		db.enableWriteAheadLogging();
		
	}
	//删除聊天记录
	public void delchthistory(){
		SQLiteDatabase db = mchathistorysql.getWritableDatabase();
		db.beginTransaction();
		String sql = "delete from "+ChatHistorySql.TABLE;
		db.execSQL(sql);
		db.close();db.setTransactionSuccessful();
		db.enableWriteAheadLogging();
	}
	
	
	
		
}
