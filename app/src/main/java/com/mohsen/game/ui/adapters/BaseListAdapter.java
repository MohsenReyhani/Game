package com.mohsen.game.ui.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseListAdapter<T> extends BaseAdapter {
	
	
	protected Context context;
	protected List<T> mItemsList;
	protected List<T> mOriginalItems;
	protected boolean isFilterableList = false;
	
	public BaseListAdapter(Context context) {
		this.context = context;
		
		
	}
	
	
	public void clearAll() {
		getItemsList().clear();
		getOriginalItems().clear();
	}
	
	public void addRange(List<T> items) {
		try {
			
			if (isFilterableList) {
				getOriginalItems().addAll(items);
			} else {
				getItemsList().addAll(items);
			}
			this.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItem(T item) {
		
		if (isFilterableList)
			getOriginalItems().add(item);
		else
			getItemsList().add(item);
		
		this.notifyDataSetChanged();
	}
	
	public void removeItem(int position) {
		if (isFilterableList)
			getOriginalItems().remove(position);
		getItemsList().remove(position);
		this.notifyDataSetChanged();
	}
	
	public void removeItem(T item) {
		if (isFilterableList)
			getOriginalItems().remove(item);
		getItemsList().remove(item);
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return getItemsList().size();
	}
	
	@Override
	public Object getItem(int position) {
		return getItemsList().get(position);
	}
	
	public int getPosition(T item) {
		return getItemsList().indexOf(item);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return convertView;
	}
	
	public List<T> getItemsList() {
		if (mItemsList == null)
			mItemsList = new ArrayList<>();
		
		return mItemsList;
	}
	
	public void setItemsList(List<T> mItemsList) {
		if (isFilterableList)
			getOriginalItems().addAll(mItemsList);
		
		getItemsList().addAll(mItemsList);
		
		notifyDataSetChanged();
	}
	
	public boolean isFilterableList() {
		return isFilterableList;
	}
	
	public void setIsFilterableList(boolean isFilterableList) {
		this.isFilterableList = isFilterableList;
	}
	
	public List<T> getOriginalItems() {
		if (mOriginalItems == null)
			mOriginalItems = new ArrayList<>();
		
		return mOriginalItems;
	}
	
	public void setOriginalItems(List<T> mOriginalItems) {
		getOriginalItems().addAll(mOriginalItems);
	}
}
