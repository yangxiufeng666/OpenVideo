package com.devil.openvideo.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
	private JSONObject jsonObject;
	
	public JSONParser(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public JSONObject getJSONObject(String key) {
		try {
			return jsonObject.getJSONObject(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONArray getJSONArray(String key) {
		try {
			return jsonObject.getJSONArray(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getString(String key) {
		String s = "";
		
		try {
			s = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public double getDouble(String key) {
		double d = 0;
		
		try {
			d = jsonObject.getDouble(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	public int getInt(String key) {
		int i = 0;
		
		try {
			i = jsonObject.getInt(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	public boolean getBoolean(String key){
		boolean flag = false;
		
		try {
			flag = jsonObject.getBoolean(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public String[] getStringArray(String key) {
		JSONArray array = null;
		try {
			array = jsonObject.getJSONArray(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(array==null || array.length()==0) {
			return null;
		}
		
		int length = array.length();
		
		String[] sattr = new String[length];
		
		for(int i=0; i<length; i++) {
			sattr[i] = array.optString(i);
		}
		
		return sattr;
	}
}
