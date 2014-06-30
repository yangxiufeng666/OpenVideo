package com.devil.openvideo.adapter;

import com.devil.openvideo.bean.CategoryBean;
import com.devil.openvideo.fragment.PagerItemFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class OpenVideoFragmentAdapter extends FragmentStatePagerAdapter{
	private CategoryBean.Results results;
	private CategoryBean.Results.Items items;
	private FragmentManager fm;
	/**
	 * ¿‡–Õ
	 */
	private int cat;
	public OpenVideoFragmentAdapter(FragmentManager fm,CategoryBean.Results results,int cat) {
		super(fm);
		this.results = results;
		this.fm = fm;
		this.cat = cat;
	}
	@Override
	public Fragment getItem(int arg0) {
		Log.d("OpenVideoFragmentAdapter", "arg0 = "+arg0);
		return PagerItemFragment.newInstance(results.getItemsList().get(arg0),cat);
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return results.getItemsList().get(position%results.getItemsList().size()).getTitle();
	}
	@Override
	public int getCount() {
		return results.getItemsList().size();
	}
}
