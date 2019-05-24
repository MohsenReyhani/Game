package com.mohsen.game.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohsen.game.application.helper.L;
import com.mohsen.game.database.helper.AppDatabaseHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class AppDatabaseManager {

    private static AppDatabaseManager instance;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    private AppDatabaseManager(SQLiteOpenHelper helper) {
        mDatabaseHelper = helper;
    }

    public static synchronized void initializeInstance() {
        if (instance == null) {
            instance = new AppDatabaseManager(new AppDatabaseHelper());
        }
    }

    public static synchronized AppDatabaseManager getInstance() {
        if (instance == null) {
            initializeInstance();
            L.e(AppDatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }


    private synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
                mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        L.d("Database open counter: " + mOpenCounter.get());
        return mDatabase;
    }

    private synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();

        }
        L.d("Database open counter: " + mOpenCounter.get());
    }

    public Object executeQuery(IQueryExecutor executor) {
        Object result = null;
        SQLiteDatabase database = openDatabase();
        if (database != null)
            result = executor.run(database);

        closeDatabase();
        return result;
    }

    public void dropAllTables(Context mContext) {
        SQLiteDatabase database = openDatabase();
        AppDatabaseHelper.dropAllTables(database);

    }

    public void createAllTables(Context mContext) {
        SQLiteDatabase database = openDatabase();
        AppDatabaseHelper.createAllTables(database);

    }

    public void executeQueryTask(final IQueryExecutor executor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase database = openDatabase();
                executor.run(database);
                closeDatabase();
            }
        }).start();
    }
}
