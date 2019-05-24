package com.mohsen.game.application.cloud;

public class CloudSettings {
	
	
	public static String getBackendCDNFilesAddress() {
		return getBackendRawAddress() + "/cdn/files/";
	}
	
	public static String getBackendCDNImagesAddress() {
		return getBackendRawAddress() + "/cdn/img/";
	}
	
	public static String getBackendRawAddress() {
		return null;
	}
	
	public static String getBackendAddress() {
		return getBackendRawAddress() + "/api/v1";
	}
	
}
