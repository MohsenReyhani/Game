package com.mohsen.game.application.helper;


import com.mohsen.game.database.DAO.AppVariableDAO;
import com.mohsen.game.database.model.AppVariable;

import java.util.Date;

public class AppVariablesHelper {

    public static final String KEY_DOMAIN_NOTIFICATION = "notif";
    public static final String KEY_DOMAIN_GLOBAL_PARAMS = "param";
    public static final String KEY_DOMAIN_GLOBAL_ICON_PACK = "icon";
    public static final String KEY_DOMAIN_PRODUCT = "product";
    public static final String KEY_DOMAIN_WIDGET = "widget";

    public static boolean deleteParameterValue(String paramId ,String domainId) {
        return AppVariableDAO.deleteByAppVariableId(paramId , domainId);
    }

    public static Integer getIntParameterValue(String paramId, String domainId) {
        String value = getStringParameterValue(paramId, domainId);

        if (value == null) return null;

        if (StringHelper.isNumber(value)) {
            return ConversionHelper.StringToInteger(value);
        } else {
            return null;
        }
    }

    public static Long getLongParameterValue(String paramId, String domainId) {
        String value = getStringParameterValue(paramId, domainId);

        if (value == null) return null;

        if (StringHelper.isNumber(value)) {
            return Long.parseLong(value);
        } else {
            return null;
        }
    }

    public static Double getDoubleParameterValue(String paramId, String domainId) {
        String value = getStringParameterValue(paramId, domainId);

        if (value == null) return null;

        if (StringHelper.isNumber(value)) {
            return Double.parseDouble(value);
        } else {
            return null;
        }
    }

    public static Boolean getBooleanParameterValue(String paramId, String domainId) {
        String value = getStringParameterValue(paramId, domainId);
        if (value == null) return null;

        return value.equals("true");

    }

    public static Date getDateParameterValue(String paramId, String domainId) {
        Long value = getLongParameterValue(paramId, domainId);

        if (value == null)
            return null;
        else
            return new Date(value);
    }

    public static String getStringParameterValue(String paramId, String domainId) {
        Object value = getParameterValue(paramId, domainId);

        if (value != null)
            return value.toString();
        else
            return null;
    }

    public static Object getParameterValue(String paramId, String domainId) {
        AppVariable value = AppVariableDAO.selectByAppVariableID(paramId, domainId);

        if (value == null)
            return null;
        else
            return value.getValue();
    }


    public static void putValue(String paramId, String value, String domainId) {
        AppVariable variable = new AppVariable(paramId, value, domainId);

        if (AppVariableDAO.selectByAppVariableID(paramId, domainId) == null)
            AppVariableDAO.insert(variable);
        else
            AppVariableDAO.updateById(variable);
    }

    public static void putValue(String paramId, boolean value, String domainId) {
        putValue(paramId, String.valueOf(value), domainId);
    }

    public static void putValue(String paramId, Long value, String domainId) {
        putValue(paramId, String.valueOf(value), domainId);
    }

    public static void putValue(String paramId, Double value, String domainId) {
        putValue(paramId, String.valueOf(value), domainId);
    }


    public static void putValue(String paramId, Integer value, String domainId) {
        putValue(paramId, String.valueOf(value), domainId);
    }

    public static void putValue(String paramId, Date value, String domainId) {
        putValue(paramId, String.valueOf(value.getTime()), domainId);
    }

    public static void putValue(String paramId, Object value, String domainId) {
        putValue(paramId, String.valueOf(value), domainId);
    }


}
