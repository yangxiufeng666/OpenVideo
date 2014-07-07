package com.devil.openvideo.listener;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;

public class GridScrollListener implements OnScrollListener{
	// 最后可见条目的索引
    private int lastVisibleIndex;
    private BaseAdapter baseAdapter;
    private Context context;
    
	public GridScrollListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}

}
