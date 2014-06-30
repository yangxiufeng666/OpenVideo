package com.devil.openvideo.adapter;

import java.util.ArrayList;

import com.devil.openvideo.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PopListViewAdapter extends BaseAdapter{

	private ArrayList<String> itemList;
	private ArrayList<String> itemValueList;
	
	private Context mContext; 
	
	public PopListViewAdapter(ArrayList<String> itemList,ArrayList<String> itemValueList,Context context) {
		super();
		this.itemList = itemList;
		this.itemValueList = itemValueList;
		mContext = context;
	}

	@Override
	public int getCount() {
		if(itemList!=null){
			return itemList.size(); 
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		LinearLayout layout = new LinearLayout(mContext);
//		layout.setOrientation(LinearLayout.VERTICAL);
		PopViewHolder holder;
		if(convertView==null){
			holder = new PopViewHolder();
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.pop_list_item, null);
			holder.textView = (TextView)convertView.findViewById(R.id.pop_item);
			convertView.setTag(holder);
		}else{
			holder = (PopViewHolder)convertView.getTag();
		}
		holder.textView.setText(itemList.get(position));
		holder.url = itemValueList.get(position);
//		TextView textView = new TextView(mContext);
//		textView.setText(itemList.get(position));
//		textView.setHeight((int)mContext.getResources().getDimension(R.dimen.popwindow_w));
//		textView.setWidth((int)mContext.getResources().getDimension(R.dimen.popwindow_w));
//		textView.setGravity(Gravity.CENTER);
//		textView.setTextColor(mContext.getResources().getColor(android.R.color.white));
//		layout.addView(textView);
		return convertView;
	}
	public class PopViewHolder{
		TextView textView;
		String url;
		public TextView getTextView() {
			return textView;
		}

		public String getUrl() {
			return url;
		}
		
	}
}
