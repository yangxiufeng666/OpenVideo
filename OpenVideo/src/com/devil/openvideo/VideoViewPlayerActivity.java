package com.devil.openvideo;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devil.openvideo.adapter.PopListViewAdapter;
import com.devil.openvideo.adapter.PopListViewAdapter.PopViewHolder;
import com.devil.openvideo.bean.PlayAddressBean;
import com.devil.openvideo.bean.RecordBean;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_flv;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_hd;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_mp4;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.sql.DBManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;
import com.devil.openvideo.util.Util;
import com.devil.openvideo.view.MyPopWindow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VideoViewPlayerActivity extends Activity implements
		OnBufferingUpdateListener, OnPreparedListener, OnInfoListener,
		OnCompletionListener,OnTouchListener{
	private static final String TAG = "VideoViewPlayerActivity";
	private static final int sDefaultTimeout = 5000;
	private static final int FADE_OUT = 1;
	private static final int SHOW_PROGRESS = 2;
	private boolean mShowing;
	private boolean mDragging;
	private long mDuration;
	private int mVideoLayout = 1;
	private VideoView mVideoView;
	private ProgressBar progressBar;
	private TextView downloadRate;
	private TextView loadRate;
	private LinearLayout bufferLayout;
	private String path;
	private long currentPosition = 0;
	private String videoId;
	private String videoName;
	private String videoTitle;
	private String playMode;
	private PlayAddressBean bean;
	
	private RelativeLayout topLayout;
	private TextView movieName;
	private TextView systemTimes;
	private ImageView batteryImg;
	
	private View playProgressView;
	private ImageButton playBtn;
	private TextView modeBtn;
	private TextView mCurrentTime;
	private TextView endTime;
	private SeekBar seekBar;
	private Button displayModebtn;
	
	private MyPopWindow popWindow=null;
	private ArrayList<String> modeList = new ArrayList<String>();
	private ArrayList<String> modeVolueList = new ArrayList<String>();
	private DBManager dbManager;
	private boolean isOnPause = false;
	private boolean isComPlete = false;
	
	Runnable delayRunnable = new Runnable() {

		@Override
		public void run() {
			topLayout.setVisibility(View.GONE);
			playProgressView.setVisibility(View.GONE);
			displayModebtn.setVisibility(View.GONE);
			mShowing = false;
			dismissPopWindow();
		}
	};
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			LogUtil.i("duration", "msg.what = " + msg.what);
			switch (msg.what) {
			case FADE_OUT:
				// hide();
				break;
			case SHOW_PROGRESS:
				setProgress();
				LogUtil.i("duration", "mDragging = " + mDragging);
				LogUtil.i("duration", "mShowing = " + mShowing);
				if (!mDragging && mShowing) {
					msg = obtainMessage(SHOW_PROGRESS);
					sendMessageDelayed(msg, 1000);// - (pos % 1000)
					updatePausePlay();
				}
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.video_view);
		videoId = getIntent().getStringExtra("videoID");
		videoName = getIntent().getStringExtra("videoName");
		videoTitle = getIntent().getStringExtra("videoTitle");
		dbManager = new DBManager(this);
		findView();
		getPlayUrl();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(isOnPause){
			startVideoPlayback();
			isOnPause = false;
		}
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(broadcastReceiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		isOnPause = true;
		Log.v(TAG, "onPause()....");
		if(!isComPlete){
			upDateRecord();
		}
		unregisterReceiver(broadcastReceiver);
	}

	private void upDateRecord() {
		currentPosition = mVideoView.getCurrentPosition();
		LogUtil.v(TAG, "upDateRecord....currentPosition = "+currentPosition);
		LogUtil.v(TAG, "upDateRecord....mDuration = "+mDuration);
		if (currentPosition <= mDuration - 1000) {
			RecordBean bean = new RecordBean();
			bean.setMovieId(videoId);
			bean.setPosition(""+currentPosition);
			bean.setMovieTitle(videoTitle);
			dbManager.add(bean);
		}else{
			dbManager.deleteRecord(videoId);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbManager.closeDB();
	}

	long currentTimes;

	@Override
	public void onBackPressed() {
		long temCurTimes = System.currentTimeMillis();
		if (temCurTimes - currentTimes > 2000) {
			currentTimes = temCurTimes;
			Toast.makeText(this, "再按一次退出播放", Toast.LENGTH_SHORT).show();
			return;
		}
		super.onBackPressed();

	}

	private void findView() {
		mVideoView = (VideoView) findViewById(R.id.videoView);
		bufferLayout = (LinearLayout) findViewById(R.id.buffering_layout);
		progressBar = (ProgressBar) findViewById(R.id.probar);
		downloadRate = (TextView) findViewById(R.id.download_rate);
		loadRate = (TextView) findViewById(R.id.load_rate);
		topLayout = (RelativeLayout)findViewById(R.id.top);
		movieName = (TextView)findViewById(R.id.play_title);
		systemTimes = (TextView)findViewById(R.id.system_time);
		batteryImg = (ImageView)findViewById(R.id.battery_icon);
		if(TextUtils.isEmpty(videoTitle)){
			movieName.setText(videoName);
		}else{
			movieName.setText(videoTitle);
		}
		playProgressView = (View) findViewById(R.id.media_progress_layout);
		playBtn = (ImageButton) findViewById(R.id.mediacontroller_play_pause);
		modeBtn = (TextView) findViewById(R.id.current_fenbianlv);
		mCurrentTime = (TextView) findViewById(R.id.mediacontroller_time_current);
		endTime = (TextView) findViewById(R.id.mediacontroller_time_total);
		seekBar = (SeekBar) findViewById(R.id.mediacontroller_seekbar);
		displayModebtn = (Button)findViewById(R.id.display_mode_btn);
		playBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doPauseResume();
			}
		});
		seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		modeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(popWindow == null){
					showPopWindow();
				}else{
					if(popWindow.isShowing()){
						dismissPopWindow();
					}else{
						showPopWindow();
					}
				}
			}
		});
	}

	private void playVideo() {
		// 超清
		List<M3u8_hd> hd = bean.getResults().getM3u8_hdList();
		// 高清
		List<M3u8_mp4> mp4 = bean.getResults().getM3u8_mp4List();
		// 标清
		List<M3u8_flv> flv = bean.getResults().getM3u8_flvList();
		HashMap<String, String> urlmap = new HashMap<String, String>();
		if (hd.size() > 0) {
			modeList.add("超清");
			modeVolueList.add(hd.get(0).getUrl());
			urlmap.put("hd", hd.get(0).getUrl());
		}
		if (mp4.size() > 0) {
			modeList.add("高清");
			modeVolueList.add(mp4.get(0).getUrl());
			urlmap.put("mp4", mp4.get(0).getUrl());
		}
		if (flv.size() > 0) {
			modeList.add("标清");
			modeVolueList.add(flv.get(0).getUrl());
			urlmap.put("flv", flv.get(0).getUrl());
		}
		if (urlmap.containsKey("hd")) {
			path = urlmap.get("hd");
			playMode = "超清";
			modeBtn.setText("超清");
		} else if (urlmap.containsKey("mp4")) {
			path = urlmap.get("mp4");
			modeBtn.setText("高清");
			playMode = "高清";
		} else if (urlmap.containsKey("flv")) {
			path = urlmap.get("flv");
			modeBtn.setText("标清");
			playMode = "标清";
		}
		try {
			Uri uri = Uri.parse(path);
			mVideoView.setVideoURI(uri);
			mVideoView.requestFocus();
			mVideoView.setOnBufferingUpdateListener(this);
			mVideoView.setOnCompletionListener(this);
			mVideoView.setOnInfoListener(this);
			mVideoView.setOnPreparedListener(this);
			mVideoView.setOnTouchListener(this);
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	private void startVideoPlayback() {
		Log.v(TAG, "startVideoPlayback");
		RecordBean bean = dbManager.findByMovieId(videoId);
		mVideoView.start();
		if(bean != null){
			mVideoView.seekTo(Long.valueOf(bean.getPosition()));
		}
	}

	private void getPlayUrl() {
		executeRequest(
				new JsonObjectRequest(Method.GET,
						MovieURLUtil.getMoviePlayUrl(videoId), null,
						new Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								try {
									bean = new PlayAddressBean(response);
									playVideo();
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}, new Response.ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {

							}
						}), MediaPlayerActivity.class);
	}

	public void executeRequest(Request<?> request, Object tag) {
		RequestManager.addRequest(request, tag);
	}
	private int mCurrentBufferPercentage;
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		mCurrentBufferPercentage = percent;
		// 缓冲百分比
		loadRate.setText(percent + "%");
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		startVideoPlayback();
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				bufferLayout.setVisibility(View.VISIBLE);
				downloadRate.setText("");
				loadRate.setText("");
			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			mVideoView.start();
			bufferLayout.setVisibility(View.GONE);
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			downloadRate.setText("" + extra + "kb/s" + "  ");
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.v(TAG, "onCompletion....");
		isComPlete = true;
		dbManager.deleteRecord(videoId);
		this.finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		DisplayFloatView();
		return false;
	}
	private void showPopWindow(){
		if(popWindow!=null&&popWindow.isShowing()){
			return;
		}
		LogUtil.d(TAG, "showPopWindow() modeList = "+modeList.size());
		LayoutInflater mLayoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View v = (View)mLayoutInflater.inflate(R.layout.popwindown_layout, null);
		ListView popList = (ListView)v.findViewById(R.id.pop_listview);
		PopListViewAdapter adapter = new PopListViewAdapter(modeList,modeVolueList,this);
		popList.setAdapter(adapter);
		int w = (int)getResources().getDimension(R.dimen.popwindow_w);
		int h=0;
		if(modeList.size()==3){
		  h = (int)getResources().getDimension(R.dimen.popwindow_h_1);
		}
		if(modeList.size()==2){
			h = (int)getResources().getDimension(R.dimen.popwindow_h_2);
		}
		if(modeList.size()==1){
			h = (int)getResources().getDimension(R.dimen.popwindow_w);
		}
		popList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PopViewHolder holder = (PopViewHolder)view.getTag();
				LogUtil.d(TAG, "popList.setOnItemClickListener text = "+holder.getTextView().getText());
				if(holder.getTextView().getText().equals(modeBtn.getText())){
					dismissPopWindow();
				}else{
					dismissPopWindow();
					String url = holder.getUrl();
					playMode = holder.getTextView().getText().toString();
					currentPosition = mVideoView.getCurrentPosition();
					try {
						mVideoView.stopPlayback();
						Uri uri = Uri.parse(url);
						mVideoView.setVideoURI(uri);
						mVideoView.start();
						mVideoView.seekTo(currentPosition);
						modeBtn.setText(holder.getTextView().getText());
					} catch (IllegalArgumentException | SecurityException
							| IllegalStateException e) {
						e.printStackTrace();
					}
				}
			}
		});
		popWindow = new MyPopWindow(v, w,h);
		popWindow.setBackgroundDrawable(new ColorDrawable(
				Color.TRANSPARENT));
		popWindow.showAsDropDown(modeBtn);
		popWindow.setFocusable(true);
		//设置popwindow如果点击外面区域，便关闭。
		popWindow.setOutsideTouchable(true);
		popWindow.update();
	}
	private void dismissPopWindow(){
		if(popWindow!=null){
			popWindow.dismiss();
			popWindow = null;
		}
	}
	private void DisplayFloatView() {
		mHandler.removeCallbacks(delayRunnable);
		if (topLayout.isShown() || playProgressView.isShown()) {
			topLayout.setVisibility(View.GONE);
			playProgressView.setVisibility(View.GONE);
			displayModebtn.setVisibility(View.GONE);
			dismissPopWindow();
			mShowing = false;
		} else {
			systemTimes.setText(Util.getCurrentSystemTimes());
			topLayout.setVisibility(View.VISIBLE);
			playProgressView.setVisibility(View.VISIBLE);
			displayModebtn.setVisibility(View.VISIBLE);
			modeBtn.setText(playMode);
			mShowing = true;
			Message msg = Message.obtain();
			msg.what = SHOW_PROGRESS;
			mHandler.sendMessage(msg);
			mHandler.postDelayed(delayRunnable, sDefaultTimeout);
		}
	}

	private void doPauseResume() {
		if (mVideoView.isPlaying())
			mVideoView.pause();
		else{
			mVideoView.start();
		}
		updatePausePlay();
	}

	private void updatePausePlay() {
		if (playBtn == null)
			return;
		if (mVideoView.isPlaying())
			playBtn.setImageResource(R.drawable.mediacontroller_pause);
		else {
			playBtn.setImageResource(R.drawable.mediacontroller_play);
		}
	}

	private long setProgress() {
		if (mVideoView == null || mDragging)
			return 0;

		long position = mVideoView.getCurrentPosition();
		long duration = mVideoView.getDuration();
		mDuration = duration;
		if (seekBar != null) {
			if (duration > 0) {
				long pos = 1000L * position / duration;
				seekBar.setProgress((int) pos);
			}
			int percent = mCurrentBufferPercentage;
			seekBar.setSecondaryProgress(percent * 10);
		}
		if (endTime != null)
			endTime.setText(StringUtils.generateTime(duration));
		if (mCurrentTime != null)
			mCurrentTime.setText(StringUtils.generateTime(position));

		return position;
	}
	public void changeLayout(View view) {
		mVideoLayout++;
		if (mVideoLayout == 4) {
			mVideoLayout = 0;
		}
		switch (mVideoLayout) {
		case 0:
			mVideoLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
			view.setBackgroundResource(R.drawable.mediacontroller_sreen_size_100);
			break;
		case 1:
			mVideoLayout = VideoView.VIDEO_LAYOUT_SCALE;
			view.setBackgroundResource(R.drawable.mediacontroller_screen_fit);
			break;
		case 2:
			mVideoLayout = VideoView.VIDEO_LAYOUT_STRETCH;
			view.setBackgroundResource(R.drawable.mediacontroller_screen_size);
			break;
		case 3:
			mVideoLayout = VideoView.VIDEO_LAYOUT_ZOOM;
			view.setBackgroundResource(R.drawable.mediacontroller_sreen_size_crop);

			break;
		}
		mVideoView.setVideoLayout(mVideoLayout, 0);
	}
	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			LogUtil.d(TAG, "onStopTrackingTouch.....seekBar.getProgress() = "+seekBar.getProgress());
			mDragging = false;
			mHandler.postDelayed(delayRunnable, sDefaultTimeout);
			long position = (mDuration * seekBar.getProgress())/1000;
			LogUtil.d(TAG, "onStopTrackingTouch.....position = "+position);
			LogUtil.d(TAG, "onStopTrackingTouch.....mDuration = "+mDuration);
			mVideoView.seekTo(position);
			mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			LogUtil.d(TAG, "onStartTrackingTouch.....seekBar.getProgress() = "+seekBar.getProgress());
			mDragging = true;
			mHandler.removeMessages(SHOW_PROGRESS);
			mHandler.removeCallbacks(delayRunnable);
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			LogUtil.d(TAG, "onProgressChanged.....fromUser = "+fromUser+" progress = "+progress);
			if (!fromUser)
		        return;
		}
	};
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
				int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
				int max = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
				int state = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
				switch (state) {
				case BatteryManager.BATTERY_STATUS_CHARGING:
					batteryImg.setImageResource(R.drawable.battery_charging);
					return;
				case BatteryManager.BATTERY_STATUS_FULL:
					batteryImg.setImageResource(R.drawable.battery_full);
					return;
				default:
					break;
				}
				if(0<=level && level<=20){
					batteryImg.setImageResource(R.drawable.battery_10);
					return;
				}
				if(20<level && level<=50){
					batteryImg.setImageResource(R.drawable.battery_20);
					return;
				}
				if(50<level && level<=70){
					batteryImg.setImageResource(R.drawable.battery_50);
					return;
				}
				if(70<level && level<=90){
					batteryImg.setImageResource(R.drawable.battery_80);
					return;
				}
				if(90<level && level<=190){
					batteryImg.setImageResource(R.drawable.battery_100);
				}
			}
		}
	};
}
