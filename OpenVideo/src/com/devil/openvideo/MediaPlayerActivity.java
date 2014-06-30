package com.devil.openvideo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devil.openvideo.adapter.PopListViewAdapter;
import com.devil.openvideo.adapter.PopListViewAdapter.PopViewHolder;
import com.devil.openvideo.bean.PlayAddressBean;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_flv;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_hd;
import com.devil.openvideo.bean.PlayAddressBean.Results.M3u8_mp4;
import com.devil.openvideo.net.RequestManager;
import com.devil.openvideo.util.LogUtil;
import com.devil.openvideo.util.MovieURLUtil;
import com.devil.openvideo.view.MyPopWindow;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.utils.StringUtils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MediaPlayerActivity extends Activity implements
		OnBufferingUpdateListener, OnPreparedListener, OnInfoListener,
		OnCompletionListener, SurfaceHolder.Callback, OnTouchListener{
	private static final String TAG = "MediaPlayerActivity";
	private static final int sDefaultTimeout = 5000;
	private static final int FADE_OUT = 1;
	private static final int SHOW_PROGRESS = 2;
	private boolean mShowing;
	private boolean mDragging;
	private long mDuration;
	private MediaPlayer mMediaPlayer;
	private SurfaceView mSurfaceView;
	private SurfaceHolder holder;
	private ProgressBar progressBar;
	private TextView downloadRate;
	private TextView loadRate;
	private LinearLayout bufferLayout;
	private String path;
	private long currentPosition = 0;
	private String videoId;
	private String videoName;
	private PlayAddressBean bean;
	private RelativeLayout topLayout;
	private TextView movieName;
	private TextView systemTime;

	private View playProgressView;
	private ImageButton playBtn;
	private TextView modeBtn;
	private TextView mCurrentTime;
	private TextView endTime;
	private SeekBar seekBar;

	private MyPopWindow popWindow=null;
	private ArrayList<String> modeList = new ArrayList<String>();
	private ArrayList<String> modeVolueList = new ArrayList<String>();
	private ArrayList<HashMap<String, String>> urlList = new ArrayList<HashMap<String,String>>();
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			long pos;
			LogUtil.i("duration", "msg.what = " + msg.what);
			switch (msg.what) {
			case FADE_OUT:
				// hide();
				break;
			case SHOW_PROGRESS:
				pos = setProgress();
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
	Runnable delayRunnable = new Runnable() {

		@Override
		public void run() {
			topLayout.setVisibility(View.GONE);
			playProgressView.setVisibility(View.GONE);
			mShowing = false;
			dismissPopWindow();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.mediaplayer);
		findView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (currentPosition > 0) {
			mMediaPlayer.start();
			mMediaPlayer.seekTo(currentPosition);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		currentPosition = mMediaPlayer.getCurrentPosition();
		mMediaPlayer.pause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeMessages(SHOW_PROGRESS);
		releaseMediaPlayer();
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

	private void releaseMediaPlayer() {
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	private void findView() {
		mSurfaceView = (SurfaceView) findViewById(R.id.surface);
		bufferLayout = (LinearLayout) findViewById(R.id.buffering_layout);
		progressBar = (ProgressBar) findViewById(R.id.probar);
		downloadRate = (TextView) findViewById(R.id.download_rate);
		loadRate = (TextView) findViewById(R.id.load_rate);
		topLayout = (RelativeLayout) findViewById(R.id.top);
		movieName = (TextView) findViewById(R.id.play_title);
		systemTime = (TextView) findViewById(R.id.play_currentSystem_time);
		playProgressView = (View) findViewById(R.id.media_progress_layout);
		playBtn = (ImageButton) findViewById(R.id.mediacontroller_play_pause);
		modeBtn = (TextView) findViewById(R.id.current_fenbianlv);
		mCurrentTime = (TextView) findViewById(R.id.mediacontroller_time_current);
		endTime = (TextView) findViewById(R.id.mediacontroller_time_total);
		seekBar = (SeekBar) findViewById(R.id.mediacontroller_seekbar);
		mSurfaceView.setOnTouchListener(this);
		holder = mSurfaceView.getHolder();
		holder.addCallback(this);
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
		if(urlmap.containsKey("hd")){
			path = urlmap.get("hd");
			modeBtn.setText("超清");
		}else if(urlmap.containsKey("mp4")){
			path = urlmap.get("mp4");
			modeBtn.setText("高清");
		}else if(urlmap.containsKey("flv")){
			path = urlmap.get("flv");
			modeBtn.setText("标清");
		}
		initMediaPlayer(path);
	}

	private void initMediaPlayer(String path) {
		mMediaPlayer = new MediaPlayer(MediaPlayerActivity.this);
		try {
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setDisplay(holder);
			mMediaPlayer.prepareAsync();
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnCompletionListener(this);
			mMediaPlayer.setOnInfoListener(this);
			mMediaPlayer.setOnPreparedListener(this);
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void refreshData() {
		mDuration = mMediaPlayer.getDuration();
		LogUtil.i("duration", "mil = " + mDuration);
		movieName.setText(videoName);
		endTime.setText(StringUtils.generateTime(mMediaPlayer.getDuration()));
		mCurrentTime.setText(StringUtils.generateTime(mMediaPlayer
				.getCurrentPosition()));

	}

	private void startVideoPlayback() {
		Log.v("MediaPlayerActivity", "startVideoPlayback");
		mMediaPlayer.start();
		mMediaPlayer.seekTo(currentPosition);
		refreshData();
		LogUtil.i("duration", "mHandler.sendMessage(msg)... ");
		Message msg = Message.obtain();
		msg.what = SHOW_PROGRESS;
		mHandler.sendMessage(msg);
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
	public void surfaceCreated(SurfaceHolder holder) {
		videoId = getIntent().getStringExtra("videoID");
		videoName = getIntent().getStringExtra("videoName");
		getPlayUrl();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
				bufferLayout.setVisibility(View.VISIBLE);
				downloadRate.setText("");
				loadRate.setText("");
			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			mMediaPlayer.start();
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
		PopListViewAdapter adapter = new PopListViewAdapter(modeList,modeVolueList,MediaPlayerActivity.this);
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
					currentPosition = mMediaPlayer.getCurrentPosition();
					try {
						mMediaPlayer.stop();
						mMediaPlayer.reset();
						mMediaPlayer.setDataSource(url);
						mMediaPlayer.prepareAsync();
						mMediaPlayer.start();
					} catch (IllegalArgumentException | SecurityException
							| IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
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
			dismissPopWindow();
			mShowing = false;
		} else {
			topLayout.setVisibility(View.VISIBLE);
			playProgressView.setVisibility(View.VISIBLE);
			mShowing = true;
			Message msg = Message.obtain();
			msg.what = SHOW_PROGRESS;
			mHandler.sendMessage(msg);
			mHandler.postDelayed(delayRunnable, sDefaultTimeout);
		}
	}

	private void doPauseResume() {
		if (mMediaPlayer.isPlaying())
			mMediaPlayer.pause();
		else{
			mMediaPlayer.start();
		}
		updatePausePlay();
	}

	private void updatePausePlay() {
		if (playBtn == null)
			return;
		if (mMediaPlayer.isPlaying())
			playBtn.setImageResource(R.drawable.mediacontroller_pause);
		else {
			playBtn.setImageResource(R.drawable.mediacontroller_play);
		}
	}

	private long setProgress() {
		if (mMediaPlayer == null || mDragging)
			return 0;

		long position = mMediaPlayer.getCurrentPosition();
		long duration = mMediaPlayer.getDuration();
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
	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			LogUtil.d(TAG, "onStopTrackingTouch.....");
			mDragging = false;
			mHandler.postDelayed(delayRunnable, sDefaultTimeout);
			mMediaPlayer.seekTo((mDuration * seekBar.getProgress()) / 1000);
			mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			LogUtil.d(TAG, "onStartTrackingTouch.....");
			mDragging = true;
			mHandler.removeMessages(SHOW_PROGRESS);
			mHandler.removeCallbacks(delayRunnable);
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			LogUtil.d(TAG, "onProgressChanged.....fromUser = "+fromUser);
		}
	};
}
