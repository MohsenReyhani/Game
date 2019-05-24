package com.mohsen.game.application.helper;

public class KeyHelper {
	
	
	// Application Update State
	public static final long APP_UPDATE_STATE_NEED_UPDATE = 300;
	public static final long APP_UPDATE_STATE_IS_DEAD = 200;
	public static final long APP_UPDATE_STATE_IS_LATEST = 100;
	
	
	// Import AccTransaction intent filter key
	
	public static String KEY_VENDOR_RECORD_ID = "id";
	public static String KEY_VENDOR_CALLER = "caller";
	public static String KEY_VENDOR_ACTION = "action";
	
	// Activity Result Code
	public static final int KEY_ACTIVITY_RESULT_CODE_CANCEL = 0;
	public static final int KEY_ACTIVITY_RESULT_CODE_OK = 1;
	public static final int KEY_ACTIVITY_RESULT_CODE_APP_NOT_INITIALIZED = 3;
	public static final int KEY_ACTIVITY_RESULT_CODE_MARKET_PURCHASE_SUCCESSFUL = 4;
	
	
	// Activity CU Parameters KEY
	public static final String KEY_ACTIVITY_CU_STATE = "ActivityState";
	public static final String KEY_OPEN_FROM_NOTIFICATION = "OpenFromNotification";
	public static final String KEY_ENTITY = "Entity";
	public static final String KEY_RESULT_VALUE = "Result_Value";
	
	// Start Specified activity form notification
	
	public static final String KEY_NOTIFICATION_CHOSEN_ACTIVITY = "NotificationChosenActivity";
	public static final String KEY_ADS_SKU = "RemoveAds";
	
	
	public static final int KEY_NOTIFICATION_ACTIVITY_NEW_TRANSACTION = Integer.MAX_VALUE - 1;
	public static final int KEY_NOTIFICATION_SYNC = Integer.MAX_VALUE - 2;
	public static final int KEY_NOTIFICATION_ACTIVITY_EDIT_TRANSACTION = Integer.MAX_VALUE - 3;
	public static final int KEY_NOTIFICATION_ACTIVITY_WALLET_MAIN = Integer.MAX_VALUE - 4;
	public static final int KEY_NOTIFICATION_ACTIVITY_SETTING = Integer.MAX_VALUE - 5;
	public static final int KEY_NOTIFICATION_ACTIVITY_VENDOR_TRANSACTION = Integer.MAX_VALUE - 6;
	
	
	// Popup items key
	public static final int POPUP_KEY_EDIT = 1;
	public static final int POPUP_KEY_DELETE = 2;
	public static final int POPUP_KEY_MARK_AS_UNFINISHED = 3;
	public static final int POPUP_KEY_MARK_AS_FINISHED = 4;
	public static final int POPUP_KEY_ADD_TRANSACTION = 5;
	public static final int POPUP_KEY_LIST_OF_TRANSACTIONS = 6;
	public static final int POPUP_KEY_TRANSFER_MONEY = 7;
	public static final int POPUP_KEY_SHARE_WALLET = 8;
	public static final int POPUP_KEY_MERGE = 9;
	public static final int POPUP_KEY_REVOKE_SHARE_WALLET = 10;
	
	// Choose Item Dialog Request Code
	public static final int REQUEST_CODE_CHOOSE_ACCOUNT = 1001;
	public static final int REQUEST_CODE_CHOOSE_GENERAL_ICON = 1002;
	public static final int REQUEST_CODE_CHOOSE_ACCOUNT_GROUP = 1003;
	public static final int REQUEST_CODE_CHOOSE_WALLET = 1004;
	public static final int REQUEST_CODE_CHOOSE_MONEY_VALUE = 1005;
	public static final int REQUEST_CODE_CHOOSE_CURRENCY_TYPE = 1006;
	public static final int REQUEST_CODE_CHOOSE_BANK = 1007;
	//public static final int REQUEST_CODE_CHOOSE_EVENT = 1008;
	public static final int REQUEST_CODE_CHOOSE_PROJECT = 1009;
	//public static final int REQUEST_CODE_CHOOSE_STUFF = 1010;
	public static final int REQUEST_CODE_CHOOSE_PEOPLE = 1011;
	public static final int REQUEST_CODE_CHOOSE_TEXT_FONT = 1012;
	public static final int REQUEST_CODE_CHOOSE_DIGIT_FONT = 1013;
	public static final int REQUEST_CODE_CHOOSE_DATE_PERIOD = 1014;
	public static final int REQUEST_SHARE_APP = 1015;
	public static final int REQUEST_CODE_PAY_ALL_LOAN = 1016;
	
	
	// Activity OnResult Request Code
	public static final int REQUEST_CODE_APP_BEGIN = 2000;
	public static final int REQUEST_CODE_LOGIN_PROCESS = 2001;
	public static final int REQUEST_CODE_CU_WALLET = 2002;
	public static final int REQUEST_CODE_CU_BANK = 2003;
	public static final int REQUEST_CODE_CU_LOAN = 2004;
	public static final int REQUEST_CODE_CU_PROJECT = 2005;
	//public static final int REQUEST_CODE_CU_STUFF = 2006;
	public static final int REQUEST_CODE_CU_Budget = 2007;
	public static final int REQUEST_CODE_CU_Saving = 2008;
	public static final int REQUEST_CODE_CU_PEOPLE = 2009;
	public static final int REQUEST_CODE_CU_ACCOUNT = 2010;
	public static final int REQUEST_CODE_CU_ACC_TRANSACTION = 2012;
	public static final int REQUEST_CODE_ACTIVITY_SETTING = 2021;
	public static final int REQUEST_CODE_ACTIVITY_PASSWORD = 2022;
	public static final int REQUEST_CODE_ACTIVITY_CONTACT_US = 2023;
	public static final int REQUEST_CODE_ACTIVITY_CLOUD_SETTING_MAX_SIMULTANEOUS_LOGIN = 2024;
	public static final int REQUEST_CODE_CU_Contact = 2025;
	public static final int REQUEST_CODE_BANK_KEY = 2026;
	public static final int REQUEST_CODE_ACTIVITY_MARKET_DETAILS = 2027;
	public static final int REQUEST_CODE_ACTIVITY_MARKET_PURCHASE = 2028;
	
	
	// YES NO Message Code
	public static final int REQUEST_CODE_DIALOG_DELETE_ENTITY = 3001;
	public static final int REQUEST_CODE_DIALOG_CANCEL_TRAVEL_MODE = 3002;
	
	// External Intent Code
	public static final int REQUEST_IMAGE_FROM_CAMERA = 100;
	public static final int REQUEST_IMAGE_FROM_GALLERY = 101;
	//public static final int REQUEST_IMAGE_FROM_GALLERY_ABOVE_19_API = 102;
	
	//App Activation Mode
	public static final int KEY_APP_ACTIVATED_ONLINE = 1;
	public static final int KEY_APP_ACTIVATED_OFFLINE = 2;
	
	
	public interface ViewMode {
		public int Day = 1;
		public int Week = 2;
		public int Month = 3;
		public int Season = 4;
		public int Year = 5;
		public int All = 6;
		public int Custom = 7;
	}
	
}
