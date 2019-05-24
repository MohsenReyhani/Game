package com.mohsen.game.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mohsen.game.R;

public class ToastHelper {
	
	public static void showMessageOnUiThread(final Context ctx, final String title
			, final String message, final int duration, final int gravity) {
		
		UiHelper.runOnUI(new Runnable() {
			public void run() {
				showMessage(ctx, title, message, duration, gravity);
			}
		});
	}
	
	public static void showMessageOnUiThread(final Context ctx, final String title
			, final String message, final int duration) {
		
		UiHelper.runOnUI(new Runnable() {
			public void run() {
				showMessage(ctx, title, message, duration, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
			}
		});
	}
	
	
	public static void showMessage(Context mContext, String title, String message, int duration, int gravity) {
		
		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View mCustomView = mInflater.inflate(R.layout.toast_message, null);
		
		TextView txtMessage = (TextView) mCustomView.findViewById(R.id.txtMessage);
		TextView txtDetailMessage = (TextView) mCustomView.findViewById(R.id.txtDetailMessage);
		
		FontHelper.setViewTextStyleTypeFace(mCustomView);
		
		Toast toast = new Toast(mContext);
		toast.setGravity(gravity, 0, 0);
		toast.setDuration(duration);
		toast.setView(mCustomView);
		
		if (title.equals(""))
			txtMessage.setVisibility(View.GONE);
		txtMessage.setText(title);
		txtDetailMessage.setText(message);
		toast.setDuration(duration);
		toast.show();
		
	}
	
	public static void showMessage(Context mContext, String Title, String BodyMessage) {
		showMessage(mContext, Title, BodyMessage);
	}
	
	public static void showMessage(Context mContext, String Title, String BodyMessage, int duration) {
		showMessage(mContext, Title, BodyMessage, duration, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
	}
	
	
}
