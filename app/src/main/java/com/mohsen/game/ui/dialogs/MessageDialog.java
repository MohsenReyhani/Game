package com.mohsen.game.ui.dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.helper.FontHelper;

public class MessageDialog extends BaseDialogFragment {


	Context mContext;
	private String mTitle;
	private String mBodyMessage;
	private int mRequestCode;
	
	public MessageDialog() {
		
	}
	
	public static MessageDialog getNewInstance(int requestCode, String title, String body) {
		MessageDialog newInstance = new MessageDialog();
		
		newInstance.setRequestCode(requestCode);
		newInstance.setTitle(title);
		newInstance.setBodyMessage(body);
		
		return newInstance;
	}
	
	public int getRequestCode() {
		return mRequestCode;
	}
	
	public void setRequestCode(int mRequestCode) {
		this.mRequestCode = mRequestCode;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			
			mContext = activity;
		} catch (final ClassCastException e) {
			
			throw new ClassCastException(activity.toString() + " must implement OnDialogCompleteResult");
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		super.onCreateDialog(savedInstanceState);
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		
		int smallMargin = mContext.getResources().getDimensionPixelSize(R.dimen.small_margin);
		int mediumMargin = mContext.getResources().getDimensionPixelSize(R.dimen.medium_margin);
		
		TextView txtCustom = new TextView(mContext);
		txtCustom.setText(getTitle());
		txtCustom.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h2_font_size));
		txtCustom.setPadding(smallMargin, mediumMargin, smallMargin, smallMargin);
		txtCustom.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		txtCustom.setTextColor(getResources().getColor(R.color.dialog_title_color));
		FontHelper.setViewTextStyleTypeFace(txtCustom);
		dialog.setCustomTitle(txtCustom);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View bodyContent = inflater.inflate(R.layout.dialog_message, null);
		((TextView) bodyContent.findViewById(R.id.txtMessage)).setText(getBodyMessage());
		FontHelper.setViewTextStyleTypeFace(bodyContent);
		dialog.setView(bodyContent);
		
		
		dialog.setNegativeButton(R.string.dialog_close_button_title, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		AlertDialog resultDialog = dialog.create();
		resultDialog.getWindow().getAttributes().windowAnimations = R.style.DialogFadeInAnimation;
		resultDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				AlertDialog alertDialog = (AlertDialog) dialogInterface;
				Button button;
				
				button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
				button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h3_font_size));
				FontHelper.setViewTextStyleTypeFace(button);
				
			}
		});
		
		
		return resultDialog;
		
	}
	
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public String getBodyMessage() {
		return mBodyMessage;
	}
	
	public void setBodyMessage(String mBodyMessage) {
		this.mBodyMessage = mBodyMessage;
	}
	
	
}
