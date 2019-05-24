package com.mohsen.game.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.ui.helper.FontHelper;

abstract public class Activity_GeneralBase extends Activity_Base {
	
	ImageView btnRightTool;
	ImageView btnLeftTool;
	TextView txtActionBarTitle;
	Toolbar actionBarView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initBundleData();
		
		setContentView(getActivityLayout());
		
		ViewGroup parent = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
		
		FontHelper.setViewTextStyleTypeFace(parent);
		
		initActionBar();
		
		onActivityFirstInit();
	}
	
	abstract protected void onActivityFirstInit();
	
	protected void initBundleData() {
		
	}
	
	protected abstract int getActivityLayout();
	
	public void initActionBar() {
		
		actionBarView = (Toolbar) findViewById(R.id.supportToolbar);
		
		if (actionBarView != null) {
			
			btnRightTool = (ImageView) actionBarView.findViewById(R.id.btnRightTool);
			btnLeftTool = (ImageView) actionBarView.findViewById(R.id.btnLeftTool);
			txtActionBarTitle = (TextView) actionBarView.findViewById(R.id.txtTitle);
			
			FontHelper.setViewTextStyleTypeFace(actionBarView);
			
			btnRightTool.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onActionBarRightToolClicked();
				}
			});
			
			btnLeftTool.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onActionBarLeftToolClicked();
				}
			});
			txtActionBarTitle.setText(getActionBarTitle());
			setSupportActionBar(actionBarView);
		}
	}
	
	abstract protected void onActionBarRightToolClicked();
	
	abstract protected void onActionBarLeftToolClicked();
	
	abstract protected String getActionBarTitle();
	
	public void setActionBarLeftToolImageId(int id) {
		if (actionBarView != null)
			btnLeftTool = (ImageButton) actionBarView.findViewById(id);
	}
	
	public void setActionBarRightToolImageId(int id) {
		if (actionBarView != null)
			btnRightTool = (ImageButton) actionBarView.findViewById(id);
	}
	
}
