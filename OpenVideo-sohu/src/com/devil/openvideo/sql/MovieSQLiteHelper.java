package com.devil.openvideo.sql;

import com.devil.openvideo.util.SqliteUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieSQLiteHelper extends SQLiteOpenHelper{

	public MovieSQLiteHelper(Context context, String name,int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SqliteUtil.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.delete("record", null, null);
		onCreate(db);
	}

}
