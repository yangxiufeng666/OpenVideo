package com.devil.openvideo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

public class MyPopWindow extends PopupWindow{
	
	public MyPopWindow() {
		super();
	}

	public MyPopWindow(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public MyPopWindow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyPopWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyPopWindow(Context context) {
		super(context);
	}

	public MyPopWindow(int width, int height) {
		super(width, height);
	}

	public MyPopWindow(View contentView, int width, int height,
			boolean focusable) {
		super(contentView, width, height, focusable);
	}

	public MyPopWindow(View contentView, int width, int height) {
		super(contentView, width, height);
	}

	public MyPopWindow(View contentView) {
		super(contentView);
	}

	interface onClick{
		public void setItemonClick();
	}
}
