package com.mohsen.game.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.activities.MainActivity;
import com.mohsen.game.ui.helper.FontHelper;
import com.mohsen.game.ui.helper.UiHelper;

public class Fragment_MainMenu extends Fragment_Base implements View.OnClickListener {
	
	UiHelper.MenuEventListener mMenuEventListener;
	View vBoxUpperImage;
	View vBox_nav_MenuItems;
	View vBoxUpperView;
	View vBox_nav_li_main_board;
	View vBox_nav_li_content;
	View vBox_nav_li_voice;
	View vBox_nav_li_videos;
	View vBox_nav_li_Setting;
	View rootView;
	
	TextView txtUpperBox;
	ImageView imgUpperBox;
	
	View v;
	
	public Fragment_MainMenu() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_main_menu, container, false);
		v.setFocusableInTouchMode(true);
		v.requestFocus();
		initComponents();
		
		return v;
	}
	
	private void initComponents() {
		
		vBox_nav_MenuItems = v.findViewById(R.id.vBox_nav_MenuItems);
		vBoxUpperView = v.findViewById(R.id.vBoxUpperView);
		vBox_nav_li_main_board = v.findViewById(R.id.vBox_nav_li_main_board);
		vBox_nav_li_content = v.findViewById(R.id.vBox_nav_li_content);
		vBox_nav_li_voice = v.findViewById(R.id.vBox_nav_li_voice);
		vBox_nav_li_videos = v.findViewById(R.id.vBox_nav_li_videos);
		vBox_nav_li_Setting = v.findViewById(R.id.vBox_nav_li_Setting);
		
		imgUpperBox = (ImageView) v.findViewById(R.id.imgUpperBox);
		txtUpperBox = (TextView) v.findViewById(R.id.txtUpperBox);
		
		FontHelper.setViewTextStyleTypeFace(v);
		
		vBox_nav_li_main_board.setOnClickListener(this);
		vBox_nav_li_content.setOnClickListener(this);
		vBox_nav_li_voice.setOnClickListener(this);
		vBox_nav_li_videos.setOnClickListener(this);
		vBox_nav_li_Setting.setOnClickListener(this);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onClick(View view) {
		
		int viewId = view.getId();
		
		if (viewId == vBox_nav_li_main_board.getId() ||
				viewId == vBox_nav_li_content.getId() ||
				viewId == vBox_nav_li_voice.getId() ||
				viewId == vBox_nav_li_videos.getId() ||
				viewId == vBox_nav_li_Setting.getId()) {
			
			if (mMenuEventListener != null) {
				mMenuEventListener.OnMenuItemClicked(viewId, false);
				MainActivity.clearAllBackStack();
			}
		}
		
	}
	
	public void setMenuItemClickedListener(UiHelper.MenuEventListener mMenuEventListener) {
		this.mMenuEventListener = mMenuEventListener;
	}
	
}
