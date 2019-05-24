package com.mohsen.game.database.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.mohsen.game.BuildConfig;
import com.mohsen.game.database.AppDatabaseManager;
import com.mohsen.game.database.IQueryExecutor;

import java.util.UUID;

public class DatabaseHelper {
	
	private static final String docIdPartsSeparator = "+";
	
	public static Long getLastInsertedRowId(final String tableName) {
		Long result;
		result = (Long) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
			@Override
			public Object run(SQLiteDatabase database) {
				try {
					Long countResult = -1L;
					String query = "select seq from sqlite_sequence where name = ? ;";
					Cursor cursor = database.rawQuery(query, new String[]{tableName});
					
					if (cursor != null && cursor.moveToFirst()) {
						countResult = cursor.getLong(0);
					}
					return countResult;
				} catch (Exception e) {
					
					return -1L;
				}
			}
		});
		
		return result;
	}
	
	public static Boolean isFieldExist(SQLiteDatabase database, final String tableName, final String fieldName) {
		
		try {
			boolean isExist = false;
			Cursor cursor = database.rawQuery("PRAGMA table_info(" + tableName + ")", null);
			int nameColumnIndex = cursor.getColumnIndex("name");
			if (nameColumnIndex == -1) return false;
			
			if (cursor != null) {
				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					if (cursor.getString(nameColumnIndex).equals(fieldName)) {
						isExist = true;
						break;
					}
					cursor.moveToNext();
				}
			}
			
			if (cursor != null) {
				cursor.close();
			}
			
			return isExist;
		} catch (Exception e) {
			
			return false;
		}
		
		
	}
	
	public static Object evaluateMathString(final String strMath) {
		Object result;
		
		result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
			@Override
			public Object run(SQLiteDatabase database) {
				try {
					double countResult = -1D;
					String query = "select " + strMath + "  as mathValue ;";
					Cursor cursor = database.rawQuery(query, null);
					
					if (cursor != null && cursor.moveToFirst()) {
						countResult = cursor.getLong(0);
					}
					return countResult;
				} catch (Exception e) {
					
					return null;
				}
			}
		});
		
		return result;
	}
	
	public static String getNextTableId(String keyPrefix, String tableName) {
		String Id = keyPrefix
				+ (keyPrefix.length() > 0 ? docIdPartsSeparator : "")
				+ tableName
				+ docIdPartsSeparator
				+ UUID.randomUUID().toString().replace("-", "");
		
		return Id;
	}
	
	public static String getNextTableId(String tableName) {
		return getNextTableId("acc", tableName);
	}
	
	public static String getDatabasePath(String dbName) {
		return Environment.getDataDirectory() + "//data//" + BuildConfig.APPLICATION_ID + "//databases//" + dbName;
	}
	
	
}
