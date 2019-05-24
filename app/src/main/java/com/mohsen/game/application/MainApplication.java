package com.mohsen.game.application;

import android.content.Context;
import android.content.res.Configuration;

import com.mohsen.game.database.AppDatabaseManager;

import java.util.Locale;

public class MainApplication extends android.app.Application {


    private static Context context;

    private static MainApplication singletonInstance;


    public MainApplication() {


    }

    public static MainApplication getInstance() {
        return singletonInstance;
    }

    public static Context getAppContext() {

        Context ctx = MainApplication.context;
        if (ctx == null) {
            ctx = GlobalParams.getApplicationActivityContext();
        }

        return ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MainApplication.context = getApplicationContext();

        singletonInstance = this;

        AppDatabaseManager.initializeInstance();

        setDefaultLocale();

    }

    private void setDefaultLocale() {
        String languageToLoad = "en_US";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
    }

}
