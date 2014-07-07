package com.devil.openvideo.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.devil.openvideo.R;

import android.content.Context;
import android.os.Handler;

public class Util {
	public static String getCurrentSystemTimes(){
		Date date = new Date();  
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm");//24小时制  
        String LgTime = sdformat.format(date);  
		return LgTime;
	}
	public static String getCacheSize(Context mContext){
		// 计算缓存大小
		long fileSize = 0;
		String cacheSize = "0KB";
		File filesDir = mContext.getFilesDir();
		File cacheDir = mContext.getCacheDir();

		fileSize += getDirSize(cacheDir);

		if (fileSize > 0)
			cacheSize = formatFileSize(fileSize);
		return cacheSize;
	}
	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}
	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	/**
	 * 清除app缓存
	 */
	/** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "volley";
	public static void clearAppCache(final Context context,final Handler handler)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				File cacheDir = new File(context.getCacheDir().getPath(),DEFAULT_CACHE_DIR);
				clearCacheFolder(cacheDir,System.currentTimeMillis());
				handler.sendEmptyMessage(1);
			}
		}).start();
		
	}
	/**
	 * 清除缓存目录
	 * @param dir 目录
	 * @param numDays 当前系统时间
	 * @return
	 */
	private static int clearCacheFolder(File dir, long curTime) {          
	    int deletedFiles = 0;         
	    if (dir!= null && dir.isDirectory()) {             
	        try {                
	            for (File child:dir.listFiles()) {    
	                if (child.isDirectory()) {              
	                    deletedFiles += clearCacheFolder(child, curTime);          
	                }  
	                if (child.lastModified() < curTime) {     
	                    if (child.delete()) {                   
	                        deletedFiles++;           
	                    }    
	                }    
	            }             
	        } catch(Exception e) {       
	            e.printStackTrace();    
	        }     
	    }
	    return deletedFiles;     
	}
	/**
	 * 1：电影；2：电视剧；16：动漫；8：记录片；7：综艺
	 * @param type
	 * @param context
	 * @return
	 */
	public static String[] getAreaArrayByType(int type,Context context){
		String[] area = null;
		switch (type) {
		case 1:
			area = context.getResources().getStringArray(R.array.movie_area);
			break;
		case 2:
			area = context.getResources().getStringArray(R.array.tv_area);
			break;
		case 16:
			area = context.getResources().getStringArray(R.array.carton_area);
			break;
		case 8:
			area = context.getResources().getStringArray(R.array.jilupian_area);
			break;
		case 7:
			area = context.getResources().getStringArray(R.array.zongyi_area);
			break;
		default:
			break;
		}
		return area;
	}
}
