package com.devil.openvideo.sql;

import com.devil.openvideo.bean.RecordBean;
import com.devil.openvideo.util.SqliteUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private Context mContext;
	private MovieSQLiteHelper helper;
	private SQLiteDatabase sqLiteDatabase;

	public DBManager(Context mContext) {
		this.mContext = mContext;
		helper = new MovieSQLiteHelper(mContext, SqliteUtil.DATABASE_NAME, SqliteUtil.DATABASE_VERSION);
		sqLiteDatabase = helper.getWritableDatabase();
	}
	public void add(RecordBean recordBean){
		if(findByMovieId(recordBean.getMovieId())==null){
			sqLiteDatabase.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(SqliteUtil._POSITION, recordBean.getPosition());
			values.put(SqliteUtil._MOVIEID, recordBean.getMovieId());
			values.put(SqliteUtil._MOVIE_TITLE, recordBean.getMovieTitle());
			sqLiteDatabase.insert(SqliteUtil.TABLE, null, values);
			sqLiteDatabase.setTransactionSuccessful();
			sqLiteDatabase.endTransaction();
		}else{
			updatePosition(recordBean);
		}
	}
	public void updatePosition(RecordBean recordBean){
		ContentValues values = new ContentValues();
		values.put(SqliteUtil._POSITION, recordBean.getPosition());
		sqLiteDatabase.update(SqliteUtil.TABLE, values, SqliteUtil._MOVIEID+" = ?", new String[]{recordBean.getMovieId()});
	}
	public void deleteRecord(String movieId){
		sqLiteDatabase.delete(SqliteUtil.TABLE, SqliteUtil._MOVIEID+" = ?", new String[]{movieId});
	}
	public RecordBean findByMovieId(String movieId){
		RecordBean recordBean = null;
		Cursor cursor = sqLiteDatabase.query(SqliteUtil.TABLE, new String[]{SqliteUtil._POSITION,SqliteUtil._MOVIEID,SqliteUtil._MOVIE_TITLE}, SqliteUtil._MOVIEID+" = ?", new String[]{movieId}, null, null, null);
		if(cursor!=null){
			while(cursor.moveToNext()){
				recordBean = new RecordBean();
				String position = cursor.getString(cursor.getColumnIndex(SqliteUtil._POSITION));
				String movieid = cursor.getString(cursor.getColumnIndex(SqliteUtil._MOVIEID));
				String movieTitle = cursor.getString(cursor.getColumnIndex(SqliteUtil._MOVIE_TITLE));
				recordBean.setPosition(position);
				recordBean.setMovieId(movieid);
				recordBean.setMovieTitle(movieTitle);
			}
		}
		return recordBean;
	}
	/** 
     * close database 
     */  
    public void closeDB() {  
        sqLiteDatabase.close();  
    }  
}
