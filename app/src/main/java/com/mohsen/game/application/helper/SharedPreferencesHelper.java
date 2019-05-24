package com.mohsen.game.application.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class SharedPreferencesHelper {
	
	public static void putVariable(Context context, String key, Object value) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		
		if (value instanceof Integer)
			editor.putInt(key, ConversionHelper.StringToInteger(value.toString()));
		
		if (value instanceof Float)
			editor.putFloat(key, ConversionHelper.StringToFloat(value.toString()));
		
		if (value instanceof String)
			editor.putString(key, value.toString());
		
		if (value instanceof Boolean)
			editor.putBoolean(key, ConversionHelper.StringToBoolean(value.toString()));
		
		if (value instanceof Long)
			editor.putLong(key, ConversionHelper.StringToLong(value.toString()));
		
		editor.apply();
		
	}
	
	
	@Nullable
	public static Integer getInteger(Context context, String key) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.contains(key))
			return preferences.getInt(key, -1);
		else
			return null;
	}
	
	@Nullable
	public static Float getFloat(Context context, String key) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.contains(key))
			return preferences.getFloat(key, -1.0F);
		else
			return null;
	}
	
	@Nullable
	public static String getString(Context context, String key) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.contains(key))
			return preferences.getString(key, "");
		else
			return null;
	}
	
	@Nullable
	public static Boolean getBoolean(Context context, String key) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.contains(key))
			return preferences.getBoolean(key, false);
		else
			return null;
	}
	
	@Nullable
	public static Long getLong(Context context, String key) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.contains(key))
			return preferences.getLong(key, -1L);
		else
			return null;
	}
	
	
}
