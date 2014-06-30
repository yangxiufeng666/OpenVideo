package com.devil.openvideo.fragment;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class EpisodeFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public Listener<JSONObject> responseListener() {
		return null;
	}

	@Override
	public ErrorListener errorSponseListener() {
		// TODO Auto-generated method stub
		return null;
	}

}
