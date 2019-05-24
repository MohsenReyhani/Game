package com.mohsen.game.ui.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.application.MainApplication;
import com.mohsen.game.application.helper.AppHelper;

public class EmptyListBackgroundViewHelper {
	
	public static LinearLayout getEmptyListBackgroundView(Context context, String msgTitle, String description, String learnMore, View.OnClickListener learnMoreClickListener) {
		return getEmptyListBackgroundView(context, msgTitle, description, learnMore, ContextCompat.getColor(MainApplication.getAppContext(), R.color.dark_text), learnMoreClickListener);
	}
	
	public static LinearLayout getEmptyListBackgroundErrorView(Context context, String msgTitle, String description, View.OnClickListener learnMoreClickListener) {
		return getEmptyListBackgroundErrorView(context, msgTitle, description, "", ContextCompat.getColor(MainApplication.getAppContext(), R.color.dark_text), learnMoreClickListener);
	}
	
	public static LinearLayout getEmptyListBackgroundView(Context context, String msgTitle, String description, String learnMore, int titleColor, View.OnClickListener learnMoreClickListner) {
		
		
		LinearLayout container = (LinearLayout) UiHelper.getLayoutInflaterFrom(context).inflate(R.layout.empty_list_background_view, null);
		
		TextView txtMsgTitle = (TextView) container.findViewById(R.id.txtMessage);
		TextView txtDescription = (TextView) container.findViewById(R.id.txtDescription);
		TextView txtLearnMore = (TextView) container.findViewById(R.id.txtLearnMore);
		ImageView imgNoTransaction = (ImageView) container.findViewById(R.id.imgNoTransaction);
		
		String iconName = "no_transaction_0" + AppHelper.randInt(1, 3);
		
		int resId = context.getResources().getIdentifier(iconName, "drawable", context.getApplicationInfo().packageName);
		Drawable randomDrawable = AppCompatDrawableManager.get().getDrawable(context, resId);
		imgNoTransaction.setImageDrawable(randomDrawable);
		
		if (msgTitle.trim().equals("") == false) {
			txtMsgTitle.setText(msgTitle);
			txtMsgTitle.setTextColor(titleColor);
			
			FontHelper.setViewTextBoldStyleTypeFace(txtMsgTitle);
		}
		
		
		if (description.trim().equals("") == false) {
			txtDescription.setText(description);
			FontHelper.setViewTextStyleTypeFace(txtDescription);
		}
		
		if (learnMore.trim().equals("") == false) {
			txtLearnMore.setText(learnMore);
			txtLearnMore.setOnClickListener(learnMoreClickListner);
			txtLearnMore.getTypeface();
			FontHelper.setViewTextStyleTypeFace(txtLearnMore);
		}
		
		
		return container;
		
	}
	
	public static LinearLayout getEmptyListBackgroundErrorView(Context context, String msgTitle, String description, String learnMore, int titleColor, View.OnClickListener learnMoreClickListner) {
		
		
		LinearLayout container = (LinearLayout) UiHelper.getLayoutInflaterFrom(context).inflate(R.layout.empty_list_background_view, null);
		
		TextView txtMsgTitle = (TextView) container.findViewById(R.id.txtMessage);
		TextView txtDescription = (TextView) container.findViewById(R.id.txtDescription);
		TextView txtLearnMore = (TextView) container.findViewById(R.id.txtLearnMore);
		ImageView imgNoTransaction = (ImageView) container.findViewById(R.id.imgNoTransaction);
		
		String iconName = "ic_character_error";
		
		int resId = context.getResources().getIdentifier(iconName, "drawable", context.getApplicationInfo().packageName);
		Drawable randomDrawable = AppCompatDrawableManager.get().getDrawable(context, resId);
		imgNoTransaction.setImageDrawable(randomDrawable);
		
		if (msgTitle.trim().equals("") == false) {
			txtMsgTitle.setText(msgTitle);
			txtMsgTitle.setTextColor(titleColor);
			
			FontHelper.setViewTextBoldStyleTypeFace(txtMsgTitle);
		}
		
		
		if (description.trim().equals("") == false) {
			txtDescription.setText(description);
			FontHelper.setViewTextStyleTypeFace(txtDescription);
		}
		
		if (learnMore.trim().equals("") == false) {
			txtLearnMore.setText(learnMore);
			txtLearnMore.setOnClickListener(learnMoreClickListner);
			txtLearnMore.getTypeface();
			FontHelper.setViewTextStyleTypeFace(txtLearnMore);
		}
		
		
		return container;
		
	}
	
}
