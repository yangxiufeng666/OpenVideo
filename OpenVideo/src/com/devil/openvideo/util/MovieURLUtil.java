package com.devil.openvideo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MovieURLUtil {
	public static final String MAIN_PAGE_URL = "http://api.3g.youku.com/layout/smarttv/1_2/main?pid=a05d70ef9aef15f4&ver=1.5.2&network=WIFI";
	
	/**
	 * 某一类型的子分类，如电影（大陆，香港）
	 */
	public static final String MOVIE_CATEGORY = "http://api.3g.youku.com/openapi-wireless/filters?pid=a05d70ef9aef15f4&guid=3f75aa661c871182c283533d7e7ee09e&ver=1.5.2&network=WIFI&type=show&cid=";
	/**
	 * 影片列表
	 */
	public static final String MOVIE_LIST = "http://api.3g.youku.com/layout/android3_0/item_list?pid=a05d70ef9aef15f4&guid=6c46c8db00258277cd8383d06b999032&ver=1.5.2&network=ethernet&image_hd=3&cid=96&format=6,1,5,7&pz=96&pg=1&filter=&ob=1";
	
	public static final String MOVIE_DETAIL = "http://api.3g.youku.com/layout/android3_0/play/detail?pid=a05d70ef9aef15f4&guid=6c46c8db00258277cd8383d06b999032&ver=1.5.2&network=ethernet&format=6,1,5,7&id=";
	
	public static final String MOVIE_EPISODE = "http://api.3g.youku.com/openapi-wireless/shows/84933d227a4911e1b2ac/reverse/videos?pid=a05d70ef9aef15f4&guid=6c46c8db00258277cd8383d06b999032&ver=1.5.2&network=ethernet&format=6,1,5,7&pz=100&pg=1&order=2";
	
	public static String getCategoryUrl(String cat){
		return MOVIE_CATEGORY+cat;
	}
	public static String getMovieListUrl(int cid,int pageNum,int pageSize,String area){
		try {
			area = URLEncoder.encode(area,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			LogUtil.e("MovieURLUtil", "URLEncoder.encode is exception = "+e1.getMessage());
		}
		String url = "http://api.3g.youku.com/layout/android3_0/item_list?"
				+ "pid=a05d70ef9aef15f4&guid=6c46c8db00258277cd8383d06b999032&ver=1.5.2"
				+ "&network=ethernet&image_hd=3"
				+ "&cid=" + cid 
				+ "&format=6,1,5,7"
				+ "&pz="+pageSize
				+ "&pg="+pageNum
				+ "&filter="+"area:"+area
				+ "&ob=1";
		LogUtil.d("MovieURLUtil", "url = "+url);
		return url;
	}
	public static String getMovieDetaiUrl(String id){
		return MOVIE_DETAIL+id;
	}
	/**
	 * 
	 * @param id 影片ID
	 * @param pz 每一页的大小
	 * @param pg 第几页
	 * @return
	 */
	public static String getMovieEpisodeUrl(String id,int pz,int pg){
		String episodeUrl = "http://api.3g.youku.com/openapi-wireless/shows/"+id
				+ "/reverse/videos?pid=a05d70ef9aef15f4&guid=6c46c8db00258277cd8383d06b999032&ver=1.5.2&network=ethernet&format=6,1,5,7"
				+ "&pz="+pz
				+ "&pg="+pg
				+ "&order=2";
		return episodeUrl;
	}
	public static String getMoviePlayUrl(String videoId){
		String url = "http://api.3g.youku.com/openapi-wireless/videos/"+videoId
				+ "/playurl?pid=a05d70ef9aef15f4&format=1,4,5,6,7&point=1";
		return url;
	}
}
