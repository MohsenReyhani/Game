package com.mohsen.game.application;

import android.content.Context;

import com.mohsen.game.R;
import com.mohsen.game.application.helper.AppVariablesHelper;
import com.mohsen.game.database.DAO.AppVariableDAO;
import com.mohsen.game.database.model.AppVariable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalParams {
	
	public static final String KEY_GLOBAL_VAR_IS_INITIAL_DATA_INSERTED = "isInitialDataInserted";
	
	private static Context mApplicationActivityContext;
	static ConcurrentHashMap<String, Object> variablesCache = new ConcurrentHashMap<String, Object>();
	
	public static void loadVariablesFromDB() {
		List<AppVariable> appVariableList = AppVariableDAO.selectAllByDomainId("param");
		for (int i = 0; i < appVariableList.size(); i++) {
			AppVariable appVariable = appVariableList.get(i);
			variablesCache.put(appVariable.getAppVariableId(), appVariable.getValue());
			
		}
	}
	
	static void cacheVariable(String key, Object value, boolean encrypt) {
		if (value == null) {
			AppVariablesHelper.deleteParameterValue(key, AppVariablesHelper.KEY_DOMAIN_GLOBAL_PARAMS);
			variablesCache.remove(key);
		} else {
			variablesCache.put(key, value);
			AppVariablesHelper.putValue(key, value, AppVariablesHelper.KEY_DOMAIN_GLOBAL_PARAMS);
		}
	}
	
	static void cacheVariable(String key, Object value) {
		cacheVariable(key, value, false);
	}
	
	static Object getVariable(String key, boolean decrypt) {
		if (variablesCache.containsKey(key)) {
			return variablesCache.get(key);
		} else {
			Object result = AppVariablesHelper.getParameterValue(key, AppVariablesHelper.KEY_DOMAIN_GLOBAL_PARAMS);
			cacheVariable(key, result, decrypt);
			return result;
		}
	}
	
	static Object getVariable(String key) {
		return getVariable(key, false);
	}
	
	public static void resetGlobalParamsData() {
		variablesCache = new ConcurrentHashMap<String, Object>();
	}
	
	public static List<String> getDBTablesName() {
		return Arrays.asList(MainApplication.getAppContext().getResources().getStringArray(R.array.db_tables_name));
	}
	
	public static void setIsInitialDataInserted(boolean value) {
		cacheVariable(KEY_GLOBAL_VAR_IS_INITIAL_DATA_INSERTED, value);
	}
	
	public static boolean isInitialDataInserted() {
		Object result = getVariable(KEY_GLOBAL_VAR_IS_INITIAL_DATA_INSERTED);
		if (result == null) {
			setIsInitialDataInserted(false);
			return false;
		} else {
			return result.toString().equals("true");
		}
	}
	
	public static Context getApplicationActivityContext() {
		return mApplicationActivityContext;
	}
	
	public static void setApplicationActivityContext(Context mApplicationActivityContext) {
		GlobalParams.mApplicationActivityContext = mApplicationActivityContext;
	}
	
}
