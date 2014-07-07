package com.devil.openvideo.fragment;


import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.devil.openvideo.DetailActivity;
import com.devil.openvideo.R;
import com.devil.openvideo.bean.MainPageBean;
import com.devil.openvideo.bean.MovieListBean;
import com.devil.openvideo.fragment.MainPageFragmet.MyBaseAdapter;
import com.devil.openvideo.fragment.MainPageFragmet.ViewHolder;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;
import com.devil.openvideo.view.MultiSwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class OtherMovieFragment extends BaseFragment{
	private int catId;
	protected GridView gridView;
	protected ProgressBar loadImageView;
	private MovieListBean bean;
	protected MultiSwipeRefreshLayout swipeRefreshLayout;
	protected MyBaseAdapter adapter;
	protected TextView tips;
	public static Fragment newInstance(int cat){
		OtherMovieFragment fragment = new OtherMovieFragment();
		fragment.catId = cat;
		return fragment;
	}
	@Override
	public Listener<? extends Object> responseListener() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ErrorListener errorSponseListener() {
		// TODO Auto-generated method stub
		return null;
	}
}
