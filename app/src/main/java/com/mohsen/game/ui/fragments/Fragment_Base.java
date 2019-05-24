package com.mohsen.game.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mohsen.game.application.helper.PermissionHelper;
import com.mohsen.game.ui.activities.Activity_Base;

import java.util.List;

public class Fragment_Base extends Fragment {
	
	public PermissionHelper permissionHelper;
	protected boolean enableScreenTracking = true;
	
	public boolean onBackPressed() {
		return true;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		permissionHelper = ((Activity_Base) getActivity()).permissionHelper;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		// notifying nested fragments (support library bug fix)
		final FragmentManager childFragmentManager = getChildFragmentManager();
		
		if (childFragmentManager != null) {
			final List<Fragment> nestedFragments = childFragmentManager.getFragments();
			
			if (nestedFragments == null || nestedFragments.size() == 0) return;
			
			for (Fragment childFragment : nestedFragments) {
				if (childFragment != null && childFragment.isVisible() && !childFragment.isDetached() && !childFragment.isRemoving()) {
					childFragment.onActivityResult(requestCode, resultCode, data);
				}
			}
		}
	}
	
}
