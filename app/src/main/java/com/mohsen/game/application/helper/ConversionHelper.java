package com.mohsen.game.application.helper;

public class ConversionHelper {
	
	public static double StringToDouble(String strValue) {
		
		try {
			return Double.parseDouble(StringHelper.convertNumbersInTextToEnglish(strValue
					.replace(",", "")
					.replace("،", "")
					.replace("+", "")
					.replace("*", "")
					.replace("/", "")));
		} catch (Exception e) {
			
			return 0;
		}
		
	}
	
	public static double StringToDouble(long value) {
		return StringToDouble(value + "");
	}
	
	public static double StringToDouble(int value) {
		return StringToDouble(value + "");
	}
	
	public static long StringToLong(String strValue) {
		try {
			
			strValue = StringHelper.convertNumbersInTextToEnglish(strValue
					.replace(",", "")
					.replace("،", "")
					.replace("+", "")
					.replace("*", "")
					.replace("/", ""));
			
			if (strValue.toLowerCase().contains("e")) {
				return (long) Double.parseDouble(strValue);
			} else
				return Long.parseLong(strValue);
		} catch (Exception e) {
			
			return 0L;
		}
		
	}
	
	public static int StringToInteger(String strValue) {
		return (int) StringToDouble(strValue);
		
	}
	
	public static boolean StringToBoolean(String value) {
		try {
			return Boolean.parseBoolean(value);
		} catch (Exception e) {
			
			return false;
		}
		
	}
	
	public static float StringToFloat(String s) {
		return (float) StringToDouble(s);
	}
}
