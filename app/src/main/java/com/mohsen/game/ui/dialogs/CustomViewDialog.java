package com.mohsen.game.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.helper.FontHelper;

public class CustomViewDialog extends BaseDialogFragment
		implements DialogInterface.OnClickListener {
	
	
	AppCompatActivity mContext;
	String mTitle;
	private View mBody;
	private OnDialogResultListener mOnDialogResultListener = null;
	private String mPositiveButtonTitle;
	private String mNegativeButtonTitle;
	private int mRequestCode;
	private boolean mOnTouchDismiss;
	
	
	public CustomViewDialog() {
		
	}
	
	public static CustomViewDialog getNewInstance(int requestCode, String title
			, String PositiveButtonTitle, String NegativeButtonTitle, OnDialogResultListener DialogResultListener, View Body, boolean OnTouchDismiss) {
		CustomViewDialog newInstance = new CustomViewDialog();
		newInstance.setBody(Body);
		newInstance.setTitle(title);
		newInstance.setNegativeButtonTitle(NegativeButtonTitle);
		newInstance.setPosetiveButtonTitle(PositiveButtonTitle);
		newInstance.setOnDialogResultListener(DialogResultListener);
		newInstance.setRequestCode(requestCode);
		newInstance.setOnTouchDismiss(OnTouchDismiss);
		
		return newInstance;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			
			mContext = (AppCompatActivity) activity;
		} catch (final ClassCastException e) {
			
			throw new ClassCastException(activity.toString() + " must implement OnDialogCompleteResult");
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		
		
		createHeader(dialog);
		
		createBody(dialog);
		
		createButtons(dialog);
		
		
		AlertDialog resultDialog = dialog.create();
		resultDialog.getWindow().getAttributes().windowAnimations = R.style.DialogPushDownAnimation;
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
	
	private void createButtons(AlertDialog.Builder dialog) {
		
		if (mPositiveButtonTitle.equals("") == false)
			dialog.setPositiveButton(mPositiveButtonTitle, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (mOnDialogResultListener != null)
						mOnDialogResultListener.OnPositiveDialogResult(getRequestCode());
				}
			});
		
		if (mNegativeButtonTitle.equals("") == false)
			dialog.setNegativeButton(mNegativeButtonTitle, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (mOnDialogResultListener != null)
						mOnDialogResultListener.OnNegativeDialogResult(getRequestCode());
					
				}
			});
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		final AlertDialog dialog = (AlertDialog) getDialog();
		if (dialog != null) {
			
		}
	}
	
	
	private void createBody(AlertDialog.Builder dialog) {
		mBody.setClickable(true);
		if (isOnTouchDismiss()) {
			mBody.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					dismiss();
				}
			});
		}
		dialog.setView(mBody);
		
	}
	
	private void createHeader(AlertDialog.Builder dialog) {
		if (mTitle.equals("")) return;
		
		TextView txtCustom = new TextView(mContext);
		txtCustom.setText(getTitle());
		txtCustom.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h2_font_size));
		txtCustom.setPadding(5, 15, 5, 5);
		txtCustom.setGravity(Gravity.CENTER);
		txtCustom.setTextColor(getResources().getColor(R.color.dialog_title_color));
		FontHelper.setViewTextStyleTypeFace(txtCustom);
		dialog.setCustomTitle(txtCustom);
	}
	
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		
	}
	
	public View getBody() {
		return mBody;
	}
	
	public void setBody(View mBody) {
		this.mBody = mBody;
	}
	
	public OnDialogResultListener getOnDialogResultListener() {
		return mOnDialogResultListener;
	}
	
	public void setOnDialogResultListener(OnDialogResultListener mOnDialogResultListener) {
		this.mOnDialogResultListener = mOnDialogResultListener;
	}
	
	public String getPositiveButtonTitle() {
		return mPositiveButtonTitle;
	}
	
	public void setPosetiveButtonTitle(String mPositiveButtonTitle) {
		this.mPositiveButtonTitle = mPositiveButtonTitle;
	}
	
	public String getNegativeButtonTitle() {
		return mNegativeButtonTitle;
	}
	
	public void setNegativeButtonTitle(String mNegativeButtonTitle) {
		this.mNegativeButtonTitle = mNegativeButtonTitle;
	}
	
	public int getRequestCode() {
		return mRequestCode;
	}
	
	public void setRequestCode(int mRequestCode) {
		this.mRequestCode = mRequestCode;
	}
	
	public boolean isOnTouchDismiss() {
		return mOnTouchDismiss;
	}
	
	public void setOnTouchDismiss(boolean mOnTouchDismiss) {
		this.mOnTouchDismiss = mOnTouchDismiss;
	}
	
	public interface OnDialogResultListener {
		public void OnPositiveDialogResult(int RequestCode);
		
		public void OnNegativeDialogResult(int RequestCode);
		
	}
}
