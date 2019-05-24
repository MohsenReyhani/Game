package com.mohsen.game.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.mohsen.game.R;

public class SnackBarHelper {
	
	public static void showSnackOnUiThread(final Activity activity, final SnackState state, final String message) {
		showSnackOnUiThread(UiHelper.getActivityRootView(activity), state, message);
	}
	
	public static void showSnackOnUiThread(final View coordinatorLayout, final SnackState state, final String message) {
		
		UiHelper.runOnUI(new Runnable() {
			public void run() {
				showSnack(coordinatorLayout, state, message);
			}
		});
	}
	
	public static void showSnackOnUiThread(final Activity activity, final SnackState state, final String message, final String actionTitle, final View.OnClickListener onClickListener) {
		showSnackOnUiThread(UiHelper.getActivityRootView(activity), state, message, actionTitle, onClickListener);
	}
	
	public static void showSnackOnUiThread(final View coordinatorLayout, final SnackState state, final String message, final String actionTitle, final View.OnClickListener onClickListener) {
		
		UiHelper.runOnUI(new Runnable() {
			public void run() {
				showSnack(coordinatorLayout, state, message, actionTitle, onClickListener);
			}
		});
	}
	
	public static void showSnack(Activity activity, SnackState state, String message, String actionTitle, View.OnClickListener onClickListener) {
		showSnack(UiHelper.getActivityRootView(activity), state, message, actionTitle, onClickListener);
	}
	
	public static void showSnack(View coordinatorLayout, SnackState state, String message, String actionTitle, View.OnClickListener onClickListener) {
		Context context = coordinatorLayout.getContext();
		
		Snackbar snackbar = Snackbar
				.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
		if (onClickListener != null) {
			snackbar = snackbar.setAction(actionTitle, onClickListener);
		}
		
		if (onClickListener != null)
			snackbar.setDuration(Snackbar.LENGTH_LONG);
		else
			snackbar.setDuration(Snackbar.LENGTH_SHORT);
		int bgColor = ContextCompat.getColor(context, R.color.red);
		int txtColor = ContextCompat.getColor(context, R.color.white);
		int actionColor = ContextCompat.getColor(context, R.color.white);
		//TODO : ADD Drawable To SnackBars
		//Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_nt_success);
		
		switch (state) {
			case Warning:
				if (onClickListener != null)
					snackbar.setDuration(Snackbar.LENGTH_LONG);
				else
					snackbar.setDuration(Snackbar.LENGTH_SHORT);
				
				bgColor = ContextCompat.getColor(context, R.color.yellow);
				txtColor = ContextCompat.getColor(context, R.color.white);
				actionColor = ContextCompat.getColor(context, R.color.white);
				//TODO : ADD Drawable To SnackBars
				//drawable = ContextCompat.getDrawable(context, R.drawable.ic_nt_exclamation);
				break;
			
			case Info:
				if (onClickListener != null)
					snackbar.setDuration(Snackbar.LENGTH_LONG);
				else
					snackbar.setDuration(Snackbar.LENGTH_SHORT);
				
				bgColor = ContextCompat.getColor(context, R.color.yellow);
				txtColor = ContextCompat.getColor(context, R.color.white);
				actionColor = ContextCompat.getColor(context, R.color.white);
				//TODO : ADD Drawable To SnackBars
				//drawable = ContextCompat.getDrawable(context, R.drawable.ic_nt_exclamation);
				break;
			
			case Error:
				if (onClickListener != null)
					snackbar.setDuration(Snackbar.LENGTH_LONG);
				else
					snackbar.setDuration(Snackbar.LENGTH_LONG);
				
				bgColor = ContextCompat.getColor(context, R.color.red);
				txtColor = ContextCompat.getColor(context, R.color.white);
				actionColor = ContextCompat.getColor(context, R.color.white);
				//TODO : ADD Drawable To SnackBars
				//drawable = ContextCompat.getDrawable(context, R.drawable.ic_nt_error);
				break;
		}
		
		View snackBarView = snackbar.getView();
		snackBarView.setBackgroundColor(bgColor);
		
		snackbar.setActionTextColor(actionColor);
		
		View sbView = snackbar.getView();
		
		TextView snack_text = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
		TextView snack_action = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_action);
		
		FontHelper.setViewTextStyleTypeFace(snack_text);
		FontHelper.setViewTextBoldStyleTypeFace(snack_action);
		
		snack_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.h5_font_size));
		snack_action.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.h4_font_size));
		
		
		snack_text.setTextColor(txtColor);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			snackbar.getView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
			//snack_text.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
		} else {
			//snack_text.setCompoundDrawablesWithIntrinsicBounds(drawable, null,null , null);
		}
		
		
		snackbar.show();
		
	}
	
	public static void showSnack(Activity activity, SnackState state, String message) {
		showSnack(UiHelper.getActivityRootView(activity), state, message, "", null);
	}
	
	public static void showSnack(View coordinatorLayout, SnackState state, String message) {
		showSnack(coordinatorLayout, state, message, "", null);
	}
	
	
	public enum SnackState {
		Error,
		Info,
		Success,
		Warning
	}
	
	
}
