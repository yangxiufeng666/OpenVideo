package com.devil.openvideo;

import com.devil.openvideo.util.Util;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends Activity{
	private TextView cacheSize;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			cacheSize.setText("0KB");
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		cacheSize = (TextView)findViewById(R.id.cache_size);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cacheSize.setText(Util.getCacheSize(this));
	}
	public void clearCache(View view){
		Util.clearAppCache(this, handler);
	}
	public void goToAboutUs(View view){
		
	}
	public void goToFeedback(View view){
		
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
