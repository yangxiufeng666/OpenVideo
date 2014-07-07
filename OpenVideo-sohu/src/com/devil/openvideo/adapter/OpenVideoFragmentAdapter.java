package com.devil.openvideo.adapter;

import com.devil.openvideo.bean.CategoryBean;
import com.devil.openvideo.fragment.PagerItemFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class OpenVideoFragmentAdapter extends FragmentStatePagerAdapter{
	private FragmentManager fm;
	private String[] areas;
	private int cat;
	public OpenVideoFragmentAdapter(FragmentManager fm,String[] areas,int cat) {
		super(fm);
		this.fm = fm;
		this.cat = cat;
		this.areas = areas;
	}
	@Override
	public Fragment getItem(int arg0) {
		Log.d("OpenVideoFragmentAdapter", "arg0 = "+arg0);
		return PagerItemFragment.newInstance(areas[arg0],cat);
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return areas[position];
	}
	@Override
	public int getCount() {
		return areas.length;
	}
}
