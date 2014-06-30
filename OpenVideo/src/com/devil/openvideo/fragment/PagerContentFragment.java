package com.devil.openvideo.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devil.openvideo.DetailActivity;
import com.devil.openvideo.R;
import com.devil.openvideo.adapter.OpenVideoFragmentAdapter;
import com.devil.openvideo.bean.CategoryBean;
import com.devil.openvideo.indicator.TabPageIndicator;
import com.devil.openvideo.slidingmenu.SlidingMenu;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PagerContentFragment extends BaseFragment{
	private TabPageIndicator indicator;
	private ViewPager pager;
	private ProgressBar loadImageView;
	private CategoryBean bean;
	private OpenVideoFragmentAdapter adapter;
	private SlidingMenu sm;
	private TextView tipsView;
	private int catId;
	public static Fragment newInstance(SlidingMenu sm,int cat){
		PagerContentFragment fragment = new PagerContentFragment();
		fragment.sm = sm;
		fragment.catId = cat;
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.cat_fragment, container, false);
		indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
		pager = (ViewPager)view.findViewById(R.id.pager);
		loadImageView = (ProgressBar)view.findViewById(R.id.loading_progress);
		tipsView = (TextView)view.findViewById(R.id.net_error_tips);
		tipsView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tipsView.setVisibility(View.GONE);
				loadImageView.setVisibility(View.VISIBLE);
				startLoad();
			}
		});
		return view;
	}
	private void startLoad(){
//		startAnimation(getActivity());
		String url = MovieURLUtil.MOVIE_CATEGORY+catId;
		LogUtil.v("fuck", "url = "+url);
		executeRequest(new JsonObjectRequest(url, null,
				responseListener(), errorSponseListener()),
				PagerContentFragment.class);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
//		if(catId==96||catId==97||catId==100||catId==85){
			startLoad();
//		}else {
//			
//		}
		
	}
	private void setAdapter(){
		pager.setVisibility(View.VISIBLE);
		adapter = new OpenVideoFragmentAdapter(getFragmentManager(),bean.getResultsList().get(0),catId);
		pager.setAdapter(adapter);
		indicator.setVisibility(View.VISIBLE);
		indicator.setViewPager(pager);
		indicator.setOnPageChangeListener(pageChangeListener);
	}
	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			LogUtil.d("ContentFragment", "onPageSelected arg == "+arg0);
			if(arg0==0){
				sm.removeIgnoredView(pager);
			}else{
				sm.addIgnoredView(pager);
			}
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	@Override
	public Listener<JSONObject> responseListener() {
		return new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				try {
//					stopAnimation();
					tipsView.setVisibility(View.GONE);
					loadImageView.setVisibility(View.GONE);
					bean = new CategoryBean(response);
					Log.d("content", "res = "+response.toString());
					Log.d("content", "size = "+bean.getResultsList().size());
					Log.d("content", "title = "+bean.getResultsList().get(0).getTitle());
					setAdapter();
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
//				stopAnimation();
				tipsView.setVisibility(View.VISIBLE);
				loadImageView.setVisibility(View.GONE);
				Toast.makeText(getActivity(), "º”‘ÿƒ⁄»› ß∞‹£°", Toast.LENGTH_SHORT).show();;
			}
		};
	}
	private void startAnimation(Context context) {
		final Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_anim);
		Log.d("content", "startAnimation.. ");
		if (loadImageView != null) {
			Log.d("content", "startAnimation..loadImageView != null ");
			if (loadImageView.getAnimation() != null) {
				loadImageView.getAnimation().cancel();
			}
			loadImageView.startAnimation(animation);
		}
	}
	
	private void stopAnimation() {
		Log.d("content", "stopAnimation.. ");
		if (loadImageView != null) {
			Log.d("content", "stopAnimation..loadImageView != null ");
			if (loadImageView.getAnimation() != null) {
				loadImageView.getAnimation().cancel();
			}
			loadImageView.setVisibility(View.GONE);
		}
	}
}
