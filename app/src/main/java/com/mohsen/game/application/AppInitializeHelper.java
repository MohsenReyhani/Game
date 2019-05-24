package com.mohsen.game.application;


public class AppInitializeHelper {
	
	
	public synchronized static Boolean initialApp() {
		
		initialBasicData();
		
		return true;
	}
	
	private synchronized static void initialBasicData() {
		
		if (GlobalParams.isInitialDataInserted() == false)
			initBasicData();
		
		
	}
	
	private static void initBasicData() {
		GlobalParams.setIsInitialDataInserted(true);
		initAllGlobalParamsMethods();
		//initOtherData
	}
	
	private static void initAllGlobalParamsMethods() {
		GlobalParams.isInitialDataInserted();
		GlobalParams.getApplicationActivityContext();
	}
	
	
}
