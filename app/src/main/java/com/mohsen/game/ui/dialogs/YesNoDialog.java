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
import android.widget.CheckBox;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.helper.FontHelper;

public class YesNoDialog extends BaseDialogFragment
		implements DialogInterface.OnClickListener {
	
	
	Context mContext;
	private String mTitle;
	private String mBodyMessage;
	private String mQuestionTwo;
	private OnYesNoDialogResultListener mOnYesNoDialogResultListener;
	private int mRequestCode;
	private Object mDataObject;
	private String mYesButtonLabel;
	private String mNoButtonLabel;
	private boolean mOnTouchDismiss;
	
	public YesNoDialog() {
		
	}
	
	public static YesNoDialog getNewInstance(int requestCode,
	                                         String title,
	                                         String body,
	                                         String questionTwo,
	                                         String yesButtonLabel,
	                                         String noButtonLabel,
	                                         OnYesNoDialogResultListener resultListener,
	                                         Object dataObject,
	                                         boolean onTouchDismiss) {
		
		YesNoDialog newInstance = new YesNoDialog();
		
		newInstance.setRequestCode(requestCode);
		newInstance.setTitle(title);
		newInstance.setBodyMessage(body);
		newInstance.setOnYesNoDialogResultListener(resultListener);
		newInstance.setDataObject(dataObject);
		newInstance.setYesButtonLabel(yesButtonLabel);
		newInstance.setNoButtonLabel(noButtonLabel);
		newInstance.setOnTouchDismiss(onTouchDismiss);
		newInstance.setQuestionTwo(questionTwo);
		
		return newInstance;
	}
	
	
	public static YesNoDialog getNewInstance(int requestCode,
	                                         String title, String body,
	                                         String yesButtonLabel,
	                                         String noButtonLabel,
	                                         OnYesNoDialogResultListener resultListener,
	                                         Object dataObject,
	                                         boolean onTouchDismiss) {
		return getNewInstance(requestCode,
				title,
				body,
				"",
				yesButtonLabel,
				noButtonLabel,
				resultListener,
				dataObject,
				onTouchDismiss);
	}
	
	
	public int getRequestCode() {
		return mRequestCode;
	}
	
	public void setRequestCode(int mRequestCode) {
		this.mRequestCode = mRequestCode;
	}
	
	public Object getDataObject() {
		return mDataObject;
	}
	
	public void setDataObject(Object mDataObject) {
		this.mDataObject = mDataObject;
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
		View bodyContent = inflater.inflate(R.layout.dialog_question, null);
		((TextView) bodyContent.findViewById(R.id.txtMessage)).setText(getBodyMessage());
		final CheckBox chkQuestion2 = (CheckBox) bodyContent.findViewById(R.id.chkQuestion2);
		if (getQuestionTwo().length() > 0) {
			chkQuestion2.setText(getQuestionTwo());
			chkQuestion2.setVisibility(View.VISIBLE);
		}
		FontHelper.setViewTextStyleTypeFace(bodyContent);
		dialog.setView(bodyContent);
		
		if (getYesButtonLabel().equals("") == false)
			dialog.setPositiveButton(getYesButtonLabel(), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (mOnYesNoDialogResultListener != null)
						mOnYesNoDialogResultListener.OnYesDialogResult(getRequestCode(), getDataObject(), chkQuestion2.isChecked());
				}
			});
		
		if (getNoButtonLabel().equals("") == false)
			dialog.setNegativeButton(getNoButtonLabel(), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (mOnYesNoDialogResultListener != null)
						mOnYesNoDialogResultListener.OnNoDialogResult(getRequestCode(), getDataObject());
				}
			});
		
		
		AlertDialog resultDialog = dialog.create();
		
		
		resultDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {
				if (mOnYesNoDialogResultListener != null)
					mOnYesNoDialogResultListener.OnCancelDialogResult(getRequestCode(), getDataObject());
			}
		});
		
		resultDialog.getWindow().getAttributes().windowAnimations = R.style.DialogFadeInAnimation;
		resultDialog.setCancelable(isOnTouchDismiss());
		resultDialog.setCanceledOnTouchOutside(isOnTouchDismiss());
		resultDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				AlertDialog alertDialog = (AlertDialog) dialogInterface;
				Button button;
				
				if (getNoButtonLabel().equals("") == false) {
					button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
					button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h3_font_size));
					FontHelper.setViewTextStyleTypeFace(button);
				}
				if (getYesButtonLabel().equals("") == false) {
					button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
					button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h3_font_size));
					FontHelper.setViewTextStyleTypeFace(button);
				}
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
	
	public OnYesNoDialogResultListener getOnYesNoDialogResultListener() {
		return mOnYesNoDialogResultListener;
	}
	
	public void setOnYesNoDialogResultListener(OnYesNoDialogResultListener mOnYesNoDialogResultListener) {
		this.mOnYesNoDialogResultListener = mOnYesNoDialogResultListener;
	}
	
	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		
	}
	
	public String getYesButtonLabel() {
		return mYesButtonLabel;
	}
	
	public void setYesButtonLabel(String mYesButtonLabel) {
		this.mYesButtonLabel = mYesButtonLabel;
	}
	
	public String getNoButtonLabel() {
		return mNoButtonLabel;
	}
	
	public void setNoButtonLabel(String mNoButtonLabel) {
		this.mNoButtonLabel = mNoButtonLabel;
	}
	
	public boolean isOnTouchDismiss() {
		return mOnTouchDismiss;
	}
	
	public void setOnTouchDismiss(boolean mOnTouchDismiss) {
		this.mOnTouchDismiss = mOnTouchDismiss;
	}
	
	public String getQuestionTwo() {
		return mQuestionTwo;
	}
	
	public void setQuestionTwo(String mQuestionTwo) {
		this.mQuestionTwo = mQuestionTwo;
	}
	
	public interface OnYesNoDialogResultListener {
		public void OnYesDialogResult(int RequestCode, Object TagValue, boolean questionTwo);
		
		public void OnNoDialogResult(int RequestCode, Object TagValue);
		
		public void OnCancelDialogResult(int RequestCode, Object TagValue);
		
	}
}
