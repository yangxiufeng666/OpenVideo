package com.devil.openvideo.fragment;

import com.android.volley.Request;
import com.devil.openvideo.listener.ResponseListener;
import com.devil.openvideo.net.RequestManager;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment implements ResponseListener{
	
	public BaseFragment() {
		super();
	}

	public void executeRequest(Request<?> request,Object tag) {
		RequestManager.addRequest(request, tag);
	}
}
