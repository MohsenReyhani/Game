package com.mohsen.game.application;


import com.mohsen.game.BuildConfig;

/**
 * Created by Mohsen on 10/9/2017.
 */

public class GlobalSettings {
	
	private static boolean IS_DEBUG_MODE = true;
	private static String LOG_TAG = "Arbaeen";
	private static String MAIN_FOLDER_NAME = "Arbaeen";
	
	public static boolean isDebugMode() {
		if (BuildConfig.DEBUG) {
			return true;
		} else {
			return IS_DEBUG_MODE;
		}
	}
	
	public static String getMainFolderName() {
		return MAIN_FOLDER_NAME;
	}
	
	public static String getLogTag() {
		return LOG_TAG;
	}
	
}
