package com.mohsen.game.application.helper;

import android.text.Editable;

public class StringHelper {
	
	public static String convertNumbersInTextToPersian(String value) {
		
		value = value
				.replace('\u0030', '\u06F0')
				.replace('\u0031', '\u06F1')
				.replace('\u0032', '\u06F2')
				.replace('\u0033', '\u06F3')
				.replace('\u0034', '\u06F4')
				.replace('\u0035', '\u06F5')
				.replace('\u0036', '\u06F6')
				.replace('\u0037', '\u06F7')
				.replace('\u0038', '\u06F8')
				.replace('\u0039', '\u06F9');
		
		return value;
	}
	
	public static String convertNumbersInTextToEnglish(String value) {
		
		value = value
				.replace('\u06F0', '\u0030')
				.replace('\u06F1', '\u0031')
				.replace('\u06F2', '\u0032')
				.replace('\u06F3', '\u0033')
				.replace('\u06F4', '\u0034')
				.replace('\u06F5', '\u0035')
				.replace('\u06F6', '\u0036')
				.replace('\u06F7', '\u0037')
				.replace('\u06F8', '\u0038')
				.replace('\u06F9', '\u0039');
		
		
		return value;
	}
	
	public static String convertFaNumberToEn(String value) {
		value = convertNumbersInTextToEnglish(value);
		value = value
				.replace("٠", "0")
				.replace("١", "1")
				.replace("٢", "2")
				.replace("٣", "3")
				.replace("٤", "4")
				.replace("٥", "5")
				.replace("٦", "6")
				.replace("٧", "7")
				.replace("٨", "8")
				.replace("٩", "9");
		
		return value;
	}
	
	public static boolean isNumber(String inputData) {
		return inputData.matches("[-+]?\\d+(\\.\\d+)?");
	}
	
	public static boolean isValidEmailAddress(CharSequence email) {
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	public static boolean isEmptyString(String str) {
		if (str.trim().equals(""))
			return true;
		else
			return false;
	}
	
	public static boolean isEmptyString(Editable editable) {
		return isEmptyString(editable.toString());
	}
	
	public static String extractDigitsFromText(String text) {
		return text.replaceAll("[^-?0-9.]+", "");
	}
	
	
	public static final boolean containsDigit(String s) {
		return extractDigitsFromText(s).length() > 0;
	}
}
