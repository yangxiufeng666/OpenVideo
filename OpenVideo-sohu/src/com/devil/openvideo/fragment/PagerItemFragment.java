package com.devil.openvideo.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.devil.openvideo.DetailActivity;
import com.devil.openvideo.R;
import com.devil.openvideo.VideoViewPlayerActivity;
import com.devil.openvideo.bean.CategoryBean;
import com.devil.openvideo.bean.MainRecommand;
import com.devil.openvideo.bean.CategoryBean.Results.Items;
import com.devil.openvideo.bean.MovieListBean.Results;
import com.devil.openvideo.bean.MovieListBean;
import com.devil.openvideo.bean.MoviesListBean;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;
import com.devil.openvideo.view.MultiSwipeRefreshLayout;

public final class PagerItemFragment extends BaseFragment implements OnScrollListener{
	private static final String KEY_CONTENT = "TestFragment:Content";
	private String mContent;
	private GridView gridView;
	private MyBaseAdapter adapter;
	private MultiSwipeRefreshLayout swipeRefreshLayout;
	//GridView加载
	private View moreView;
	private ProgressBar progressBar;
	private int currentPage=1;
	private int tempPage = 0;
	private static final int PAGE_SIZE = 30;
	/**
	 * 类型
	 */
	private int cat;
	private boolean isLoading;
	
	private String area;
	private ArrayList<MoviesListBean> moviesListBeans = new ArrayList<MoviesListBean>();
	public static Fragment newInstance(String area,int cat) {
		PagerItemFragment fragment = new PagerItemFragment();
		fragment.cat = cat;
		fragment.area = area;
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
	            mContent = savedInstanceState.getString(KEY_CONTENT);
	        }
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.grid_page, container, false);
		progressBar = (ProgressBar)view.findViewById(R.id.load_more_progress);
		gridView = (GridView) view.findViewById(R.id.main_grid);
		swipeRefreshLayout = (MultiSwipeRefreshLayout)view.findViewById(R.id.swiperefresh);
		adapter = new MyBaseAdapter(view.getContext());
		gridView.setAdapter(adapter);
		gridView.setOnScrollListener(this);
		swipeRefreshLayout.setColorScheme(
                R.color.swipe_color_1, R.color.swipe_color_2,
                R.color.swipe_color_3, R.color.swipe_color_4);
		swipeRefreshLayout.setSwipeableChildren(R.id.main_grid);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				startLoadData(1,PAGE_SIZE);
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = (ViewHolder)view.getTag();
//				if(cat==96||cat==97||cat==100||cat==85){
//					Intent intent = new Intent();
//					intent.setClass(getActivity(), DetailActivity.class);
//					intent.putExtra("MOVIE_ID", holder.tid);
//					getActivity().startActivity(intent);
//				}else{
//					Intent intent = new Intent();
//					intent.putExtra("videoID",holder.tid);
//					intent.putExtra("videoName",holder.movieTitle.getText());
//					intent.setClass(getActivity(), VideoViewPlayerActivity.class);
//					getActivity().startActivity(intent);
//				}
				
			}
		});
		startLoadData(1,PAGE_SIZE);
		return view;
	}
	private void startLoadData(int pageNum,int pageSize) {
//		String url = MovieURLUtil.getMovieListUrl(cat, pageNum, pageSize, mItems.getValue());
		String url = MovieURLUtil.getSohuMovieList(cat, "all", pageNum,pageSize, area, "all", "upTime");
		LogUtil.d("URL", "startLoadData url = "+url);
		executeRequest(new JsonArrayRequest( url,
				responseListener(), errorSponseListener()), PagerItemFragment.class);
	}
	@Override
	public Listener<JSONArray> responseListener() {
		return new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				try {
					LogUtil.d("ItemFragment", "response = "+response);
					swipeRefreshLayout.setRefreshing(false);
					progressBar.setVisibility(View.GONE);
					currentPage++;
					isEnd = false;
					int jSize = response.length();
					for (int i = 0; i < jSize; i++) {
						MoviesListBean mainRecommandBean = new MoviesListBean(response.getJSONObject(i));
						moviesListBeans.add(mainRecommandBean);
					}
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public ErrorListener errorSponseListener() {
		return new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				LogUtil.d("ItemFragment", "onErrorResponse = "+error.getLocalizedMessage());
				String result = error.getLocalizedMessage();
				if("[]".equalsIgnoreCase(result)){
					isEnd = true;
					Toast.makeText(getActivity(), "已经到底了，(ˇˍˇ） ～", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), "您的网络有点不太给力哟，(ˇˍˇ） ～", Toast.LENGTH_SHORT).show();
				}
				swipeRefreshLayout.setRefreshing(false);
				progressBar.setVisibility(View.GONE);
				tempPage=0;
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
			LogUtil.i("OpenVideo", "BaseAdapter position = " + position);
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.grid_item, null);
				holder.imageView = (NetworkImageView) convertView
						.findViewById(R.id.poster_id);
				holder.movieTitle = (TextView) convertView
						.findViewById(R.id.movie_title);
				holder.updateTips = (TextView)convertView.findViewById(R.id.update_tips);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.movieTitle.setText(moviesListBeans.get(position).getSubjectTitle());
			holder.tid = moviesListBeans.get(position).getSubjectId();
//			holder.updateTips.setText(results.get(position).getMiddle_stripe());
			holder.imageView.setDefaultImageResId(R.drawable.default_poster);
			holder.imageView.setImageUrl(moviesListBeans.get(position)
					.getVerBigPic(), RequestManager.getImageLoader());
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
			Log.i("OpenVideo", "BaseAdapter getCount");
			return moviesListBeans.size();
		}
	};
	
	class ViewHolder {
		TextView movieTitle;
		TextView updateTips;
		NetworkImageView imageView;
		String tid;
	}
	//
	private boolean isEnd = false;
	// 最后可见条目的索引
    private int lastVisibleIndex;
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		LogUtil.d("OpenVideo", "onScrollStateChanged.........");
		LogUtil.d("OpenVideo", "onScrollStateChanged......lastVisibleIndex..."+lastVisibleIndex);
		LogUtil.d("OpenVideo", "onScrollStateChanged.........adapter.getCount() = "+adapter.getCount());
		if (isEnd) {
			return;
		}
		// 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && lastVisibleIndex == adapter.getCount() && !isLoading) {
        	if(tempPage == currentPage){
    			return;
    		}
    		tempPage = currentPage;
        	progressBar.setVisibility(View.VISIBLE);
        	startLoadData(currentPage,PAGE_SIZE);
        }
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		LogUtil.d("OpenVideo", "onScroll.........");
		if (isEnd) {
			return;
		}
		// 计算最后可见条目的索引
        lastVisibleIndex = firstVisibleItem + visibleItemCount ;
        LogUtil.d("OpenVideo", "onScroll.........lastVisibleIndex = "+lastVisibleIndex);
//        LogUtil.d("OpenVideo", "onScroll.........bean.getTotal() = "+bean.getTotal());
        LogUtil.d("OpenVideo", "onScroll.........totalItemCount = "+totalItemCount);
        // 所有的条目已经和最大条数相等，则移除底部的View
//        if (totalItemCount ==  bean.getTotal()) {
//            Toast.makeText(getActivity(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_SHORT).show();
//        }
	}
	
}
