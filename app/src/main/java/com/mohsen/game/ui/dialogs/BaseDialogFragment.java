package com.mohsen.game.ui.dialogs;

public class BaseDialogFragment extends android.support.v4.app.DialogFragment {
	
	public DialogDismissListener getDialogDismissListener() {
		return mDialogDismissListener;
	}
	
	public void setDialogDismissListener(DialogDismissListener mDialogDismissListener) {
		this.mDialogDismissListener = mDialogDismissListener;
	}
	
	public interface DialogDismissListener {
		public void onCancel();
		
		public void onDismiss();
	}
	
	private DialogDismissListener mDialogDismissListener = null;
	
	
	@Override
	public void onDismiss(android.content.DialogInterface dialog) {
		super.onDismiss(dialog);
		if (mDialogDismissListener != null) {
			mDialogDismissListener.onDismiss();
		}
	}
	
	@Override
	public void onCancel(android.content.DialogInterface dialog) {
		super.onCancel(dialog);
		if (mDialogDismissListener != null) {
			mDialogDismissListener.onCancel();
		}
	}
	
}
