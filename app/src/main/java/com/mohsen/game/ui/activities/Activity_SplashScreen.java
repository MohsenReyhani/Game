package com.mohsen.game.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mohsen.game.R;
import com.mohsen.game.application.AppInitializeHelper;
import com.mohsen.game.application.GlobalParams;

public class Activity_SplashScreen extends Activity_Base {
	
	private int SPLASH_TIME_OUT = 300;
	Bundle mBundle;
	
	public Activity_SplashScreen() {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GlobalParams.loadVariablesFromDB();
		setContentView(R.layout.activity_splash_screen);
		
		checkBundle();
	}
	
	private void checkBundle() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mBundle = bundle;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				initAppStart();
				
				
			}
		}, SPLASH_TIME_OUT);
		
	}
	
	private void initAppStart() {
		
		AppInitializeHelper.initialApp();
		//Do Other Initialization
		//Do Database Upgrade
		
		startCustomActivity();
		
	}
	
	private void startCustomActivity() {
		Intent i = new Intent(this, MainActivity.class);
		
		if (mBundle != null)
			i.putExtras(mBundle);
		
		startActivity(i);
		finish();
	}
}
