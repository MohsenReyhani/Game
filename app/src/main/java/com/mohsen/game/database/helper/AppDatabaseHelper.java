package com.mohsen.game.database.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohsen.game.application.MainApplication;
import com.mohsen.game.database.DAO.AppVariableDAO;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "accounting.db";
    public static final int DATABASE_VERSION = 13;


    public AppDatabaseHelper() {
        super(MainApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static void createAllTables(SQLiteDatabase sqLiteDatabase) {
        // Base Classes


        //sqLiteDatabase.execSQL(CurrencyTypeDAO.getCreateTable());
        sqLiteDatabase.execSQL(AppVariableDAO.getCreateTable());
        //sqLiteDatabase.execSQL(MetaSequenceDAO.getCreateTable());

        // User Data Classes
        //sqLiteDatabase.execSQL(AccountDAO.getCreateTable());

    }


    public static void dropAllTables(SQLiteDatabase sqLiteDatabase) {
        // Base Classes
        //sqLiteDatabase.execSQL(CurrencyTypeDAO.getDropTable());
        sqLiteDatabase.execSQL(AppVariableDAO.getDropTable());
        //sqLiteDatabase.execSQL(MetaSequenceDAO.getDropTable());


        // User Data Classes
        //sqLiteDatabase.execSQL(AccountDAO.getDropTable());

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create all tables
        createAllTables(sqLiteDatabase);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        for (int i = oldVersion + 1; i <= newVersion; i++) {
            //TODO Write Upgrade Helper
            //AppDatabaseUpgradeHelper.upgradeDb(i, sqLiteDatabase);
        }


    }


    public static boolean isTableExists(SQLiteDatabase db, String tableName) {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }


}
