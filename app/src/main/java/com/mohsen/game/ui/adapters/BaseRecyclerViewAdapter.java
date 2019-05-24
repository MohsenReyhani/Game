package com.mohsen.game.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {
	protected Context mContext;
	private List<T> mDataSet;
	private List<Integer> mSelectedItems = new ArrayList<>();
	
	RecyclerViewEventListener recyclerViewEventListener;
	
	public List<Integer> getSelectedItems() {
		return mSelectedItems;
	}
	
	public void addToSelectedItems(int position) {
		if (!getSelectedItems().contains(position))
			this.mSelectedItems.add(position);
	}
	
	public void removeFromSelectedItems(int position) {
		this.mSelectedItems.remove(new Integer(position));
	}
	
	public RecyclerViewEventListener getRecyclerViewEventListener() {
		return recyclerViewEventListener;
	}
	
	public void setRecyclerViewEventListener(RecyclerViewEventListener recyclerViewEventListener) {
		this.recyclerViewEventListener = recyclerViewEventListener;
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View v) {
			super(v);
		}
	}
	
	public BaseRecyclerViewAdapter(Context context, RecyclerViewEventListener recyclerViewEventListener) {
		setContext(context);
		this.setRecyclerViewEventListener(recyclerViewEventListener);
	}
	
	
	@Override
	public int getItemCount() {
		return getDataSet().size();
	}
	
	public T getItem(int position) {
		return getDataSet().get(position);
	}
	
	public int getPosition(T item) {
		return getDataSet().indexOf(item);
	}
	
	public void clearAll() {
		getDataSet().clear();
	}
	
	public void addRange(List<T> items) {
		try {
			getDataSet().addAll(items);
			this.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItem(T item) {
		getDataSet().add(item);
		this.notifyDataSetChanged();
	}
	
	public void removeItem(int position) {
		getDataSet().remove(position);
		this.notifyDataSetChanged();
	}
	
	public void removeItem(T item) {
		getDataSet().remove(item);
		this.notifyDataSetChanged();
	}
	
	public Context getContext() {
		return mContext;
	}
	
	public void setContext(Context mContext) {
		this.mContext = mContext;
	}
	
	public List<T> getDataSet() {
		if (mDataSet == null)
			mDataSet = new ArrayList<T>();
		return mDataSet;
	}
	
	public void setDataSet(List<T> mDataSet) {
		this.mDataSet = mDataSet;
	}
	
	
	public interface RecyclerViewEventListener {
		public void onItemSelectionChanged(int position, boolean checkedState);
		
		public void onSelectionModeChanged(boolean selectionMode);
		
		public void onViewTapped(int viewType, int position);
		
		public void onContextMenuTapped(int position, int actionId);
		
		public void onDataChanged(int viewType, int position);
	}
}
