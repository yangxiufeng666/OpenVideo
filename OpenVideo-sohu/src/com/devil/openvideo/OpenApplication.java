package com.devil.openvideo;

import com.devil.openvideo.net.RequestManager;

import android.app.Application;

public class OpenApplication extends Application{
	private volatile static OpenApplication instance =null;

	public static OpenApplication getInstance() {
		if(instance==null){
			synchronized (instance) {
				instance = new OpenApplication();
			}
		}
		return instance;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		initVolley();
	}
	private void initVolley(){
		RequestManager.init(this);
	}
}
