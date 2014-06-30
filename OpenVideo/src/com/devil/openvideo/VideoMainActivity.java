package com.devil.openvideo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devil.openvideo.fragment.PagerContentFragment;
import com.devil.openvideo.fragment.MainPageFragmet;
import com.devil.openvideo.slidingmenu.SlidingMenu;
import com.devil.openvideo.slidingmenu.app.SlidingFragmentActivity;
import com.devil.openvideo.util.LogUtil;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class VideoMainActivity extends SlidingFragmentActivity {

	private final String LIST_TEXT = "text";
	private final String LIST_IMAGEVIEW = "img";
	/**
	 * 数字代表列表顺序
	 */
	private int mTag = 0;
	/**
	 * 每个分类对应得ID
	 */
	String[] categoryIds;
	private SlidingMenu sm;
	private ListView slidingItemList;
	private SimpleAdapter lvAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_main);
		initSlidingMenu();
		initView();
		initSildingListView();
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.container_id, new MainPageFragmet(),
						"MainPageFragmet").commit();
	}

	private void initSlidingMenu() {
		setBehindContentView(R.layout.behind_sliding_menu);
		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.slidind_shadow_width);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		sm.setBehindWidth(dm.widthPixels / 2);
		sm.setFadeEnabled(true);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		// sm.setShadowWidth(20);
		sm.setBehindScrollScale(0);
	}

	private void initView() {
		slidingItemList = (ListView) findViewById(R.id.behind_silding_list);
	}

	private void initSildingListView() {

		lvAdapter = new SimpleAdapter(this, getData(),
				R.layout.behind_list_item, new String[] { LIST_TEXT,
						LIST_IMAGEVIEW },
				new int[] { R.id.textview_behind_title,
						R.id.imageview_behind_icon }) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				view.setTag(categoryIds[position]);
				if (position == mTag) {
					view.setBackgroundResource(R.drawable.back_behind_list);
					slidingItemList.setTag(view);
				} else {
					view.setBackgroundColor(Color.TRANSPARENT);
				}
				return view;
			}
		};
		slidingItemList.setAdapter(lvAdapter);
		slidingItemList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mTag = position;
				if (slidingItemList.getTag() != null) {
					if (slidingItemList.getTag() == view) {
						// show content
						showContent();
						return;
					}
					((View) slidingItemList.getTag())
							.setBackgroundColor(Color.TRANSPARENT);
				}
				slidingItemList.setTag(view);
				view.setBackgroundResource(R.drawable.back_behind_list);
				// 跳转到相应的页面
				switch (position) {
				case 0:
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.container_id, new MainPageFragmet(),
									"MainPageFragmet").commit();
					showContent();
					break;
				default:
					getSupportFragmentManager()
							.beginTransaction()
							.replace(
									R.id.container_id,
									PagerContentFragment.newInstance(sm,
											Integer.valueOf((String) view
													.getTag()))).commit();
					break;
				}
				VideoMainActivity.this.showContent();
			}
		});
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> lsMaps = new ArrayList<Map<String, Object>>();
		String[] title = getResources().getStringArray(R.array.category_title);
		TypedArray imgs = getResources().obtainTypedArray(R.array.category_img);
		LogUtil.d("title", "count = " + imgs.length());
		categoryIds = getResources().getStringArray(R.array.categorry_value);
		int i = 0;
		for (String string : title) {
			Map<String, Object> map = new HashMap<>();
			map.put(LIST_IMAGEVIEW, imgs.getResourceId(i, -1));
			map.put(LIST_TEXT, string);
			lsMaps.add(map);
			i++;
		}
		imgs.recycle();
		return lsMaps;
	}

	long currentTimes;

	@Override
	public void onBackPressed() {
		long temCurTimes = System.currentTimeMillis();
		if (temCurTimes - currentTimes > 2000) {
			currentTimes = temCurTimes;
			Toast.makeText(this, "再按一次退出HD-Video", Toast.LENGTH_SHORT).show();
			return;
		}
		super.onBackPressed();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actions, menu);
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
			sm.showMenu();
			break;
		case R.id.action_setting:
			Intent intent = new Intent();
			intent.setClass(this, SettingActivity.class);
			startActivity(intent);
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
