package com.mohsen.game.application.persian.calendar.util;

public class ConversionHelper {

    public static double StringToDouble(String strValue) {

        try {
            return Double.parseDouble(StringHelper.convertNumbersInTextToEnglish(strValue
                    .replace(",", "")

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
            return Long.parseLong(StringHelper.convertNumbersInTextToEnglish(strValue
                    .replace(",", "")

                    .replace("+", "")
                    .replace("*", "")
                    .replace("/", "")));
        } catch (Exception e) {
           
            return 0L;
        }

    }

    public static int StringToInteger(String strValue) {
        return (int) StringToDouble(strValue);

    }

}
