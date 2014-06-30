package com.devil.openvideo.listener;

import org.json.JSONObject;

import com.android.volley.Response;

public interface ResponseListener {
	public Response.Listener<? extends Object> responseListener();
	public Response.ErrorListener errorSponseListener();
}
