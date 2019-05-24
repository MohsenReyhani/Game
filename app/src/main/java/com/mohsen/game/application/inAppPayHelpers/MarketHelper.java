package com.mohsen.game.application.inAppPayHelpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

public class MarketHelper {
	
	
	public static boolean searchMarketInventory(String value) {
		/*String[] list = GlobalParams.getMarketInventory();
		if (list != null && value != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].equals(value))
					return true;
			}
		}*/
		return false;
	}
	
	public static boolean removeFromMarketInventory(String value) {
		//return GlobalParams.removeItemFromMarketInventory(value);
		return false;
	}
	
	public static boolean addToMarketInventory(String value) {
		/*if (!MarketHelper.searchMarketInventory(value)) {
			return GlobalParams.setMarketInventory(value);
		} else {
			return true;
		}*/
		
		return true;
	}
	
	//******************************************** Helper Methods ************************************************
	
	public static boolean checkIfMarketInstalled(Activity activity) {
		
		Context context = activity.getApplicationContext();
		PackageManager pm = context.getPackageManager();
		
		boolean isMarketInstalled = false;//!isPackageInstalled(BuildConfig.M_NAMESPACE, pm);
		if (isMarketInstalled) {
			//Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.M_URL));
			//activity.startActivity(browserIntent);
			//SnackBarHelper.showSnack(UiHelper.getActivityRootView(activity), SnackBarHelper.SnackState.Error, context.getString(R.string.try_after_market_installed));
			return false;
		} else {
			return true;
		}
	}
	
	private static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
		try {
			packageManager.getPackageInfo(packagename, 0);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	
}
