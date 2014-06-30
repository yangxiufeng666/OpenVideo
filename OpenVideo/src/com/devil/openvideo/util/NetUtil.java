package com.devil.openvideo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean isNetWorkOk(Context mCurrentContext) {
		boolean netSataus = true;
		ConnectivityManager connectManager = (ConnectivityManager) mCurrentContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectManager.getActiveNetworkInfo();
		if (info != null) {
			netSataus = connectManager.getActiveNetworkInfo().isAvailable();
		} else {
			netSataus = false;
		}
		return netSataus;
	}
}
