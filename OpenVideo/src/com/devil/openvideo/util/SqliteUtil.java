package com.devil.openvideo.util;

public class SqliteUtil {
	public static final String DATABASE_NAME = "record.db";  
	public static final int DATABASE_VERSION = 1;  
	public static final String TABLE = "record_table";  
	public static final String _ID = "_id";  
	public static final String _POSITION = "_position";  
	public static final String _MOVIEID = "_movieId";  
	public static final String _MOVIE_TITLE = "_movieTitle";  
	public static final String CREATE_TABLE ="create table "+TABLE
			+ "( "+_ID+" integer primary key, "
			+ _POSITION + " text, "
			+ _MOVIEID + " text, "
			+ _MOVIE_TITLE + " text)";
	public static final String DROP_TABLE = "drop table record if exist";
	public static final String QUERY_ALL = "SELECT * FROM record_table";
	public static final String QUERY_MIVIEID = "select * from record_table where _movieId =";
}
