package com.mohsen.game.application.cloud;

import java.util.ArrayList;

public class UserProfile {
	
	public static final String KEY_USER_ID = "UserId";
	public static final String KEY_UPDATED_AT = "UpdatedAt";
	public static final String KEY_USER_ACCOUNTS_INFO = "UserAccountInfo";
	public static final String KEY_ID = "_id";
	public static final String KEY_EDITOR_DEVICE_ID = "EditorDeviceId";
	public static final String KEY_EDITOR = "Editor";
	public static final String KEY_ACCOUNT_TYPE = "AccountType";
	public static final String KEY_TOTAL_CONTACTS = "TotalContacts";
	public static final String KEY_INSTALLATION_ID = "InstallationId";
	
	
	private String AccountType;
	private String Editor;
	private String EditorDeviceId;
	private Long UpdatedAt;
	private String UserId;
	private ArrayList<String> UserAccountsInfo;
	private String Id;
	private int TotalContacts;
	
	
	public static UserProfile getInstance(String profileId) {
		UserProfile result = null;
		return result;
	}
	
	
	public void save() {
	}
	
	
	public String getAccountType() {
		return AccountType;
	}
	
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	
	public String getEditor() {
		return Editor;
	}
	
	public void setEditor(String editor) {
		Editor = editor;
	}
	
	public String getEditorDeviceId() {
		return EditorDeviceId;
	}
	
	public void setEditorDeviceId(String editorDeviceId) {
		EditorDeviceId = editorDeviceId;
	}
	
	public Long getUpdatedAt() {
		return UpdatedAt;
	}
	
	public void setUpdatedAt(Long updatedAt) {
		UpdatedAt = updatedAt;
	}
	
	public String getUserId() {
		return UserId;
	}
	
	public void setUserId(String userId) {
		UserId = userId;
	}
	
	public String getId() {
		return Id;
	}
	
	public void setId(String id) {
		Id = id;
	}
	
	public int getTotalContacts() {
		return TotalContacts;
	}
	
	public void setTotalContacts(int totalContacts) {
		TotalContacts = totalContacts;
	}
	
	
	public ArrayList<String> getUserAccountsInfo() {
		return UserAccountsInfo;
	}
	
	public void setUserAccountsInfo(ArrayList<String> userAccountsInfo) {
		UserAccountsInfo = userAccountsInfo;
	}
}
