package com.mohsen.game.database;

import android.database.sqlite.SQLiteDatabase;

public interface IQueryExecutor {

    public Object run(SQLiteDatabase database);
}
