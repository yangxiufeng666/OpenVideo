package com.devil.openvideo;

import io.vov.vitamio.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.VolleyError;
import com.devil.openvideo.bean.EpisodeBean;
import com.devil.openvideo.bean.MovieDetailBean;
import com.devil.openvideo.bean.RecordBean;
import com.devil.openvideo.fragment.MainPageFragmet;
import com.devil.openvideo.listener.ResponseListener;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.sql.DBManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity implements ResponseListener{
	private String movieId; 
	private String recordMovieId; 
	private MovieDetailBean detailBean;
	private EpisodeBean episodeBean;
	
	private LinearLayout detailContent;
	private ProgressBar detailLoadProgress;
	
	private NetworkImageView imageView;
	private View directorLayer;
	private View performanceLayer;
	private TextView director;
	private TextView performance;
	private TextView desTextView;
	
	private ListView episodeList;
	private LinearLayout detailLayout;
	
	private TextView detailBtn;
	private TextView episodeBtn;
	
	private TextView showDateView;
	private TextView totalClickView;
	private TextView doubanRate;
	private TextView episodeTotal;
	private TextView updateTo;
	private Button playBtn;
	
	private TextView playedEpisode;
	private TextView PlayedTimes;
	
	private episodeAdapter adapter;
	
	private boolean detailLoaded=false;
	private DBManager dbManager;
	private RecordBean recordBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_layout);
		movieId = getIntent().getStringExtra("MOVIE_ID");
		recordMovieId = movieId;
//		dbManager = new DBManager(this);
		findView();
		startLoadData();
	}
	@Override
	protected void onResume() {
		super.onResume();
//		refreshPlayed();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		dbManager.closeDB();
	}
	private void findView(){
		detailContent = (LinearLayout)findViewById(R.id.detail_content);
		detailLoadProgress = (ProgressBar)findViewById(R.id.detail_loading_progress);
		imageView = (NetworkImageView)findViewById(R.id.poster_img);
		director = (TextView)findViewById(R.id.director);
		performance = (TextView)findViewById(R.id.performance);
		imageView.setDefaultImageResId(R.drawable.default_poster_port);
		desTextView = (TextView)findViewById(R.id.detail_des);
		episodeList = (ListView)findViewById(R.id.episode_grid);
		detailLayout =(LinearLayout)findViewById(R.id.detail_layout);
		detailBtn = (TextView)findViewById(R.id.detail_btn);
		episodeBtn = (TextView)findViewById(R.id.eposide_btn);
		showDateView = (TextView)findViewById(R.id.detail_showDate);
		totalClickView = (TextView)findViewById(R.id.detail_total_vv);
		doubanRate = (TextView)findViewById(R.id.detail_douban_rating);
		playBtn= (Button)findViewById(R.id.detai_player);
		directorLayer = (View)findViewById(R.id.director_layout);
		performanceLayer = (View)findViewById(R.id.performance_layout);
		episodeTotal = (TextView)findViewById(R.id.detail_episode_total);
		updateTo = (TextView)findViewById(R.id.detail_update_to);
		playedEpisode = (TextView)findViewById(R.id.played_episode);
		PlayedTimes = (TextView)findViewById(R.id.played_time);
		adapter = new episodeAdapter();
		episodeList.setAdapter(adapter);
		episodeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = (ViewHolder)view.getTag();
				Intent intent = new Intent();
				recordMovieId = holder.videoId;
				intent.putExtra("videoID",holder.videoId);
				intent.putExtra("videoName", holder.videoName);//detailBean.getDetail().getTitle()
				intent.putExtra("videoTitle", episodeBean.getResultsList().get(position).getTitle());
				intent.setClass(DetailActivity.this, VideoViewPlayerActivity.class);
				DetailActivity.this.startActivity(intent);
			}
		});
		playBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(episodeBean==null){
					return;
				}
				Intent intent = new Intent();
				recordMovieId = detailBean.getDetail().getVideoid();
				intent.putExtra("videoID",detailBean.getDetail().getVideoid());
				intent.putExtra("videoName", detailBean.getDetail().getTitle());
				intent.putExtra("videoTitle", episodeBean.getResultsList().get(episodeBean.getResultsList().size()-1).getTitle());
				intent.setClass(DetailActivity.this, VideoViewPlayerActivity.class);
				DetailActivity.this.startActivity(intent);
			}
		});
		detailBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				episodeList.setVisibility(View.INVISIBLE);
				detailLayout.setVisibility(View.VISIBLE);
				detailBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
				episodeBtn.setBackgroundColor(getResources().getColor(R.color.transparent));
			}
		});
		episodeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				episodeList.setVisibility(View.VISIBLE);
				detailLayout.setVisibility(View.GONE);
				episodeBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
				detailBtn.setBackgroundColor(getResources().getColor(R.color.transparent));
			}
		});
	}
	private void refreshData(){
		String dir="";
		String[] direc = detailBean.getDetail().getDirector();
		if(direc!=null){
			for (String string : direc) {
				dir+=string+" ";
			}
			directorLayer.setVisibility(View.VISIBLE);
		}else{
			directorLayer.setVisibility(View.GONE);
		}
		director.setText(dir);
		String per="";
		String[] perf = detailBean.getDetail().getPerformer();
		if(perf!=null){
			for (String string : perf) {
				per+=string+" ";
			}
			performanceLayer.setVisibility(View.VISIBLE);
		}else{
			performanceLayer.setVisibility(View.GONE);
		}
		detailContent.setVisibility(View.VISIBLE);
		detailLoadProgress.setVisibility(View.GONE);
		performance.setText(per);
		if(TextUtils.isEmpty(detailBean.getDetail().getDesc())){
			desTextView.setText("暂无详情介绍。");
		}else{
			desTextView.setText(detailBean.getDetail().getDesc());
		}
		imageView.setImageUrl(detailBean.getDetail().getImg(), RequestManager.getImageLoader());
		showDateView.setText(detailBean.getDetail().getShowdate());
		totalClickView.setText(detailBean.getDetail().getTotal_vv());
		doubanRate.setText(detailBean.getDetail().getReputation());
		episodeTotal.setText(detailBean.getDetail().getEpisode_total());
		updateTo.setText(detailBean.getDetail().getStripe_bottom());
		if(detailBean.getDetail().getCats_id()!=96){
			playBtn.setVisibility(View.GONE);
		}else{
			playBtn.setVisibility(View.VISIBLE);
		}
//		refreshPlayed();
	}
	private void refreshPlayed() {
		recordBean = dbManager.findByMovieId(recordMovieId);
		if(recordBean!=null){
			playedEpisode.setText("播放："+recordBean.getMovieTitle());
			PlayedTimes.setText("已播："+StringUtils.generateTime(Long.valueOf(recordBean.getPosition())));
		}
	}
	private void refreshGridView(){
		adapter.notifyDataSetChanged();;
	}
	private void startLoadData() {
		executeRequest(new JsonObjectRequest(Method.GET,MovieURLUtil.getMovieDetaiUrl(movieId),
				null, responseListener(), errorSponseListener()), MainPageFragmet.class);
	}
	private void loadEpisode(){
		executeRequest(new JsonObjectRequest(Method.GET,MovieURLUtil.getMovieEpisodeUrl(movieId,100,1),
				null, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							LogUtil.d("DetailActivity", "loadEpisode response = "+response);
							episodeBean = new EpisodeBean(response);
							refreshGridView();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						
					}
				}), MainPageFragmet.class);
	}
	public void executeRequest(Request<?> request,Object tag) {
		RequestManager.addRequest(request, tag);
	}
	@Override
	public Listener<JSONObject> responseListener() {
		return new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				try {
					LogUtil.d("DetailActivity", "detail response = "+response);
					detailBean = new MovieDetailBean(response);
					refreshData();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				loadEpisode();
			}
		};
	}

	@Override
	public ErrorListener errorSponseListener() {
		return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(DetailActivity.this, "亲，网络不给力哟！", Toast.LENGTH_SHORT).show();;
			}
		};
	}
	class episodeAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(episodeBean==null){
				return 0;
			}
			return episodeBean.getResultsList().size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null){
				convertView = LayoutInflater.from(DetailActivity.this).inflate(
					R.layout.episode_item, null);
				holder = new ViewHolder();
//				holder.btn = (Button)convertView.findViewById(R.id.eposide_btn);
				holder.imagView = (NetworkImageView)convertView.findViewById(R.id.esposide_poster_id);
				holder.tagsView = (TextView)convertView.findViewById(R.id.esposide_title);
				holder.desView = (TextView)convertView.findViewById(R.id.esposide_des);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			final String videoName = episodeBean.getResultsList().get(position).getTitle();
			holder.tagsView.setText(videoName);
			holder.desView.setText(episodeBean.getResultsList().get(position).getDesc());
			holder.imagView.setDefaultImageResId(R.drawable.default_poster);
			holder.imagView.setImageUrl(episodeBean.getResultsList().get(position).getImg(), RequestManager.getImageLoader());
			final String videoId = episodeBean.getResultsList().get(position).getVideoid();
			holder.videoId = videoId;
			holder.videoName = videoName;
			return convertView;
		}
	}
	class ViewHolder{
		private NetworkImageView imagView;
		private TextView tagsView;
		private TextView desView;
		String videoId;
		String videoName;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return true;
	}
}
