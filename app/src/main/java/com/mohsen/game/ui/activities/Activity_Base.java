package com.mohsen.game.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mohsen.game.application.GlobalParams;
import com.mohsen.game.application.helper.PermissionHelper;
import com.mohsen.game.database.AppDatabaseManager;

import java.util.List;

abstract public class Activity_Base extends AppCompatActivity {
	
	public PermissionHelper permissionHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		permissionHelper = new PermissionHelper(this);
		GlobalParams.setApplicationActivityContext(getApplicationContext());
		baseInitialization();
		
	}
	
	private void baseInitialization() {
		AppDatabaseManager.initializeInstance();
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPostResume() {
		super.onPostResume();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	public void recreate() {
		super.recreate();
		
		startActivity(getIntent());
		finish();
		
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		if (fragments != null) {
			for (Fragment fragment : fragments) {
				if (fragment != null) {
					fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
				}
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		if (fragments != null) {
			for (Fragment fragment : fragments) {
				if (fragment != null) {
					fragment.onActivityResult(requestCode, resultCode, data);
				}
			}
		}
	}
	
}
