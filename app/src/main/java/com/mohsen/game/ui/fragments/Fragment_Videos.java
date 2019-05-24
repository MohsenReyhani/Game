package com.mohsen.game.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mohsen.game.R;


public class Fragment_Videos extends Fragment_Base {
	
	
	RelativeLayout emptyListViewContainer;
	View v;
	ListView mListView;
	ImageView btnAddNewEntity;
	
	public Fragment_Videos() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.fragment_settings, container, false);
		v.setFocusableInTouchMode(true);
		v.requestFocus();
		initComponents();
		
		return v;
	}
	
	private void initComponents() {
		
		Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.mainToolBar).findViewById(R.id.supportToolbar);
		btnAddNewEntity = (ImageView) toolbar.findViewById(R.id.btnLeftTool);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		refreshData();
	}
	
	private void refreshData() {
		
	}
	
}
