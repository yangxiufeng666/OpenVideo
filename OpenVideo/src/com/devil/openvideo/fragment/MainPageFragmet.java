package com.devil.openvideo.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.devil.openvideo.DetailActivity;
import com.devil.openvideo.R;
import com.devil.openvideo.bean.MainPageBean;
import com.devil.openvideo.fragment.PagerItemFragment.ViewHolder;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;
import com.devil.openvideo.view.MultiSwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainPageFragmet extends BaseFragment {
	protected GridView gridView;
	protected ProgressBar loadImageView;
	protected MainPageBean bean;
	protected MultiSwipeRefreshLayout swipeRefreshLayout;
	protected MyBaseAdapter adapter;
	protected TextView tips;
//	private View conView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home, container, false);
//		conView = (View)view.findViewById(R.id.content);
		tips = (TextView)view.findViewById(R.id.net_error_tips);
		tips.setVisibility(View.GONE);
		gridView = (GridView) view.findViewById(R.id.main_grid);
		loadImageView = (ProgressBar)view.findViewById(R.id.loading_progress);
		gridView.setVisibility(View.INVISIBLE);
		loadImageView.setVisibility(View.VISIBLE);
		swipeRefreshLayout = (MultiSwipeRefreshLayout)view.findViewById(R.id.swiperefresh);
		swipeRefreshLayout.setColorScheme(
                R.color.swipe_color_1, R.color.swipe_color_2,
                R.color.swipe_color_3, R.color.swipe_color_4);
		adapter = new MyBaseAdapter(view.getContext());
		gridView.setAdapter(adapter);
		swipeRefreshLayout.setSwipeableChildren(R.id.main_grid);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				startLoadData();
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = (ViewHolder)view.getTag();
				Intent intent = new Intent();
				intent.setClass(getActivity(), DetailActivity.class);
				intent.putExtra("MOVIE_ID", holder.tid);
				getActivity().startActivity(intent);
			}
		});
		tips.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tips.setVisibility(View.GONE);
				swipeRefreshLayout.setVisibility(View.VISIBLE);
				gridView.setVisibility(View.VISIBLE);
				loadImageView.setVisibility(View.INVISIBLE);
				startLoadData();
			}
		});
		startLoadData();
		return view;
	}
	
	protected void startLoadData() {
		executeRequest(new JsonObjectRequest(Method.GET, MovieURLUtil.MAIN_PAGE_URL,
				null, responseListener(), errorSponseListener()), MainPageFragmet.class);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}

	protected void showUI() {
		Log.d("OpenVideo", "showUI ");
		adapter.notifyDataSetChanged();
	}

	@Override
	public Listener<JSONObject> responseListener() {
		return new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				LogUtil.d("OpenVideo", "response = " + response.toString());
				try {
					tips.setVisibility(View.GONE);
					swipeRefreshLayout.setVisibility(View.VISIBLE);
					gridView.setVisibility(View.VISIBLE);
					loadImageView.setVisibility(View.INVISIBLE);
					swipeRefreshLayout.setRefreshing(false);
					bean = new MainPageBean(response);
					LogUtil.d("OpenVideo", "responseListener size = " + bean.getResult_movieList().size());
					showUI();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.e("OpenVideo", "response = " + e.toString());
				}
			}
		};
	}

	@Override
	public ErrorListener errorSponseListener() {
		return new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				LogUtil.d("OpenVideo", "errorSponseListener... " );
				swipeRefreshLayout.setRefreshing(false);
				tips.setVisibility(View.VISIBLE);
				swipeRefreshLayout.setVisibility(View.GONE);
				
				gridView.setVisibility(View.VISIBLE);
				loadImageView.setVisibility(View.INVISIBLE);
			}
		};
	}

	class MyBaseAdapter extends BaseAdapter
	{
		Context context;
		
		public MyBaseAdapter(Context context) {
			super();
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.i("OpenVideo", "BaseAdapter position = " + position);
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.grid_item, null);
				holder.imageView = (NetworkImageView) convertView
						.findViewById(R.id.poster_id);
				holder.movieTitle = (TextView) convertView
						.findViewById(R.id.movie_title);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tid = bean.getResult_movieList().get(position).getShowid();
			holder.movieTitle.setText(bean.getResult_movieList().get(position)
					.getTitle());
			holder.imageView.setImageUrl(
					bean.getResult_movieList().get(position)
							.getShow_bannerurl(),
					RequestManager.getImageLoader());
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public int getCount() {
			if (bean != null) {
				Log.i("OpenVideo", "BaseAdapter getCount = "+bean.getResult_movieList().size());
				return bean.getResult_movieList().size()-1;
			}
			Log.i("OpenVideo", "BaseAdapter getCount");
			return 0;
		}
	};
	
	class ViewHolder {
		TextView movieTitle;
		NetworkImageView imageView;
		String tid;
	}
}
