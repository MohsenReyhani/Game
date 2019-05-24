package com.mohsen.game.application.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionHelper {
	
	public static final int REQUEST_CODE_RECEIVE_SMS = 3;
	public static final int REQUEST_CODE_EXTERNAL_STORAGE = 0;
	public static final int REQUEST_CODE_READ_CONTACT = 1;
	public static final int REQUEST_CODE_READ_SMS = 2;
	public static final int REQUEST_CODE_GET_ACCOUNTS = 3;
	public static final int REQUEST_CODE_CAMERA = 4;
	public static final int REQUEST_CODE_LOCATION = 5;
	Activity mActivity;
	
	public PermissionHelper(Activity mActivity) {
		this.mActivity = mActivity;
	}
	
	public PermissionStatus checkPermission(String permission) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return PermissionStatus.Granted;
		
		int result = ContextCompat.checkSelfPermission(mActivity, permission);
		if (result == PackageManager.PERMISSION_GRANTED) {
			SharedPreferencesHelper.putVariable(mActivity, permission, PermissionStatus.Granted.toString());
			return PermissionStatus.Granted;
		} else {
			if (SharedPreferencesHelper.getString(mActivity, permission) == null) {
				return PermissionStatus.NotRequested;
			} else {
				if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
					SharedPreferencesHelper.putVariable(mActivity, permission, PermissionStatus.Denied.toString());
					return PermissionStatus.Denied;
				} else {
					SharedPreferencesHelper.putVariable(mActivity, permission, PermissionStatus.NeverAskMe.toString());
					return PermissionStatus.NeverAskMe;
				}
			}
			
		}
	}
	
	public void requestPermission(int requestCode, String permission) {
		PermissionStatus permissionStatus = checkPermission(permission);
		SharedPreferencesHelper.putVariable(mActivity, permission, permissionStatus.toString());
		
		
		if (permissionStatus == PermissionStatus.NeverAskMe) {
			openPermissionScreen();
		} else {
			ActivityCompat.requestPermissions(mActivity, new String[]{permission}, requestCode);
		}
	}
	
	public PermissionStatus checkPermissionForExternalStorage() {
		
		return checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
	}
	
	
	public PermissionStatus checkPermissionForReadContacts() {
		
		return checkPermission(Manifest.permission.READ_CONTACTS);
	}
	
	public PermissionStatus checkPermissionForReadSMS() {
		
		return checkPermission(Manifest.permission.READ_SMS);
		
	}
	
	
	public PermissionStatus checkPermissionForReceiveSMS() {
		
		return checkPermission(Manifest.permission.RECEIVE_SMS);
		
	}
	
	
	public PermissionStatus checkPermissionForGetAccounts() {
		
		return checkPermission(Manifest.permission.GET_ACCOUNTS);
		
	}
	
	public PermissionStatus checkPermissionForCamera() {
		
		return checkPermission(Manifest.permission.CAMERA);
	}
	
	
	public PermissionStatus checkFineLocationPermission() {
		
		return checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
		
		
	}
	
	public PermissionStatus checkCoarseLocationPermission() {
		return checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
		
	}
	
	public void requestPermissionForFineLocation() {
		requestPermission(REQUEST_CODE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
	}
	
	public void requestPermissionForCoarseLocation() {
		requestPermission(REQUEST_CODE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
	}
	
	public void requestPermissionForExternalStorage() {
		requestPermission(REQUEST_CODE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
	}
	
	public void requestPermissionForReadContacts() {
		requestPermission(REQUEST_CODE_READ_CONTACT, Manifest.permission.READ_CONTACTS);
	}
	
	public void requestPermissionForReadSMS() {
		requestPermission(REQUEST_CODE_READ_SMS, Manifest.permission.READ_SMS);
	}
	
	public void requestPermissionForReceiveSMS() {
		requestPermission(REQUEST_CODE_RECEIVE_SMS, Manifest.permission.RECEIVE_SMS);
	}
	
	public void requestPermissionForGetAccounts() {
		requestPermission(REQUEST_CODE_GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
	}
	
	public void requestPermissionForCamera() {
		
		requestPermission(REQUEST_CODE_CAMERA, Manifest.permission.CAMERA);
	}
	
	public void openPermissionScreen() {
		Intent intent = new Intent();
		intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
		intent.setData(uri);
		mActivity.startActivity(intent);
	}
	
	
	public enum PermissionStatus {
		Granted,
		NotRequested,
		Denied,
		NeverAskMe
	}
	
	
}



