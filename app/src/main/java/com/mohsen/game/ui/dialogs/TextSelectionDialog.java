package com.mohsen.game.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.helper.FontHelper;

public class TextSelectionDialog extends BaseDialogFragment
		implements DialogInterface.OnClickListener {
	
	
	AppCompatActivity mContext;
	EditText txtValue;
	TextView txtDescription;
	
	private String mDescription;
	private String mSelectedValue = "";
	private String mTitle;
	private OnValueSelectionDialogResultListener mOnValueSelectionDialogResultListener;
	private int mRequestCode;
	
	
	public TextSelectionDialog() {
		
	}
	
	public static TextSelectionDialog getNewInstance(int requestCode, String title, String Description
			, OnValueSelectionDialogResultListener resultListener
			, String selectedValue) {
		TextSelectionDialog newInstance = new TextSelectionDialog();
		newInstance.setSelectedValue(selectedValue);
		newInstance.setRequestCode(requestCode);
		newInstance.setTitle(title);
		newInstance.setOnValueSelectionDialogResultListener(resultListener);
		newInstance.setDescription(Description);
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
		
		
		createDialogButtons(dialog);
		
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
				
				button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
				button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h3_font_size));
				FontHelper.setViewTextStyleTypeFace(button);
			}
		});
		
		
		return resultDialog;
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		final AlertDialog dialog = (AlertDialog) getDialog();
		if (dialog != null) {
			txtValue.setText(mSelectedValue);
			txtDescription.setText(mDescription);
			txtValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}
				}
			});
			txtValue.setImeOptions(EditorInfo.IME_ACTION_DONE);
			
			final Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
			positiveButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onPositiveButtonClick();
				}
			});
			
			Button negativeButton = (Button) dialog.getButton(Dialog.BUTTON_NEGATIVE);
			negativeButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mOnValueSelectionDialogResultListener != null)
						mOnValueSelectionDialogResultListener.OnCancelDialog(getRequestCode());
					dismiss();
				}
			});
			
			txtValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
						onPositiveButtonClick();
					}
					return false;
				}
			});
		}
	}
	
	private void onPositiveButtonClick() {
		if (mOnValueSelectionDialogResultListener != null)
			mOnValueSelectionDialogResultListener.OnValueSelected(mRequestCode, txtValue.getText().toString());
		dismiss();
	}
	
	
	private void createDialogButtons(AlertDialog.Builder dialog) {
		dialog.setPositiveButton(R.string.dialog_select_button_title, null);
		dialog.setNegativeButton(R.string.dialog_cancel_button_title, null);
	}
	
	private void createBody(AlertDialog.Builder dialog) {
		
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View bodyView = inflater.inflate(R.layout.dialog_text_selection, null);
		
		txtValue = (EditText) bodyView.findViewById(R.id.txtValue);
		txtDescription = (TextView) bodyView.findViewById(R.id.txtDescription);
		
		
		FontHelper.setViewTextStyleTypeFace(txtValue);
		FontHelper.setViewTextStyleTypeFace(txtDescription);
		
		dialog.setView(bodyView);
		
	}
	
	private void createHeader(AlertDialog.Builder dialog) {
		int xSmallMargin = mContext.getResources().getDimensionPixelSize(R.dimen.xsmall_margin);
		int mediumMargin = mContext.getResources().getDimensionPixelSize(R.dimen.medium_margin);
		
		TextView txtCustom = new TextView(mContext);
		txtCustom.setText(getTitle());
		txtCustom.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h2_font_size));
		txtCustom.setPadding(mediumMargin, mediumMargin, mediumMargin, xSmallMargin);
		txtCustom.setGravity(Gravity.RIGHT);
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
	
	public OnValueSelectionDialogResultListener getOnValueDialogResultListener() {
		return mOnValueSelectionDialogResultListener;
	}
	
	public void setOnValueSelectionDialogResultListener(OnValueSelectionDialogResultListener onValueSelectionDialogResultListener) {
		this.mOnValueSelectionDialogResultListener = onValueSelectionDialogResultListener;
	}
	
	
	public int getRequestCode() {
		return mRequestCode;
	}
	
	public void setRequestCode(int mRequestCode) {
		this.mRequestCode = mRequestCode;
	}
	
	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		
	}
	
	public String getSelectedValue() {
		return mSelectedValue;
	}
	
	public void setSelectedValue(String mSelectedValue) {
		this.mSelectedValue = mSelectedValue;
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	
	public interface OnValueSelectionDialogResultListener {
		public void OnValueSelected(int RequestCode, String SelectedValue);
		
		public void OnCancelDialog(int RequestCode);
		
	}
	
	
}
