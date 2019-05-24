package com.mohsen.game.ui.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UiHelper {
	
	public static Handler UIHandler;
	
	static {
		UIHandler = new Handler(Looper.getMainLooper());
	}
	
	public static void runOnUI(Runnable runnable) {
		UIHandler.post(runnable);
	}
	
	public static LayoutInflater getLayoutInflaterFrom(Context context) {
		LayoutInflater LayoutInflater =
				(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (LayoutInflater == null) {
			throw new AssertionError("LayoutInflater not found.");
		}
		return LayoutInflater;
	}
	
	public static void setProgressDialogMessage(final ProgressDialog pd, final String msg) {
		Runnable changeMessage = new Runnable() {
			@Override
			public void run() {
				//Log.v(TAG, strCharacters);
				pd.setMessage(msg);
			}
		};
		runOnUI(changeMessage);
	}
	
	public static View getActivityRootView(Activity activity) {
		return activity.getWindow().getDecorView().findViewById(android.R.id.content);
	}
	
	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		if (activity.getCurrentFocus() != null)
			inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		try {
			ListAdapter listAdapter = listView.getAdapter();
			if (listAdapter == null) {
				// pre-condition
				return;
			}
			
			int totalHeight = 0;
			for (int i = 0; i < listAdapter.getCount(); i++) {
				View listItem = listAdapter.getView(i, null, listView);
				listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
				listItem.measure(0, 0);
				
				totalHeight += listItem.getMeasuredHeight();
			}
			
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			int lstHeight = totalHeight
					+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))
					+ listView.getPaddingTop()
					+ listView.getPaddingBottom();
			
			params.height = lstHeight;
			listView.setLayoutParams(params);
			listView.requestLayout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setScrollViewHeightBasedOnChildren(RecyclerView recyclerView) {
		try {
			RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
			if (layoutManager == null) {
				// pre-condition
				return;
			}
			
			int totalHeight = 0;
			
			for (int i = 0; i < layoutManager.getItemCount(); i++) {
				View listItem = layoutManager.findViewByPosition(i);
				if (listItem != null) {
					listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
							View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
					listItem.measure(0, 0);
					
					totalHeight = layoutManager.getItemCount() * listItem.getMeasuredHeight();
					break;
				}
			}
			
			ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
			int lstHeight = totalHeight
					+ recyclerView.getPaddingTop()
					+ recyclerView.getPaddingBottom();
			
			
			params.height = lstHeight;
			//recyclerView.setLayoutParams(params);
			//recyclerView.requestLayout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView, int maxHeight) {
		try {
			ListAdapter listAdapter = listView.getAdapter();
			if (listAdapter == null) {
				// pre-condition
				return;
			}
			
			int totalHeight = 0;
			for (int i = 0; i < listAdapter.getCount(); i++) {
				View listItem = listAdapter.getView(i, null, listView);
				listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
				listItem.measure(0, 0);
				
				totalHeight += listItem.getMeasuredHeight();
			}
			
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			
			int lstHeight = totalHeight
					+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))
					+ listView.getPaddingTop()
					+ listView.getPaddingBottom();
			
			params.height = (maxHeight > lstHeight) ? lstHeight : maxHeight;
			listView.setLayoutParams(params);
			listView.requestLayout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentInputLanguage(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
		
		String locale = ims.getLocale();
		return locale;
	}
	
	public interface MenuEventListener {
		public void OnMenuItemClicked(int viewResourceId, boolean saveBackStack);
	}
	
	public static <T extends View> ArrayList<T> findAllChildViewsByType(ViewGroup viewGroup, Class<T> type) {
		ArrayList<T> result = new ArrayList<>();
		int count = viewGroup.getChildCount();
		
		for (int i = 0; i < count; i++) {
			View view = viewGroup.getChildAt(i);
			if (view instanceof ViewGroup)
				result.addAll((ArrayList<T>) findAllChildViewsByType((ViewGroup) view, type));
			else if (type.isAssignableFrom(view.getClass())) {
				result.add((T) view);
			}
		}
		
		return result;
		
	}
	
	public interface OnContextMenuItemClickListener {
		public void contextMenuItemClicked(Object entity, int menuId);
	}
	
	public static void expand(final View v) {
		v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		final int targetHeight = v.getMeasuredHeight();
		
		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1
						? ViewGroup.LayoutParams.WRAP_CONTENT
						: (int) (targetHeight * interpolatedTime);
				v.requestLayout();
			}
			
			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};
		
		// 1dp/ms
		a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}
	
	public static void collapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();
		
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}
			
			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};
		
		// 1dp/ms
		a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}
	
}
