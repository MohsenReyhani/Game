package com.mohsen.game.database.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mohsen.game.R;
import com.mohsen.game.application.MainApplication;
import com.mohsen.game.application.helper.L;
import com.mohsen.game.database.AppDatabaseManager;
import com.mohsen.game.database.IQueryExecutor;
import com.mohsen.game.database.model.AppVariable;

import java.util.ArrayList;
import java.util.List;

public class AppVariableDAO {

    public static String getCreateTable() {
        return MainApplication.getAppContext().getString(R.string.sql_App_Variable_CreateTable);
    }

    public static String getDropTable() {
        return MainApplication.getAppContext().getString(R.string.sql_App_Variable_DropTable);
    }

    public static Boolean deleteAll() {
        Boolean result;
        result = (Boolean) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    String deleteAllCommand = MainApplication.getAppContext().getString(R.string.sql_App_Variable_DeleteAll);
                    database.execSQL(deleteAllCommand);
                    return true;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - deleteAll : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        return result;

    }

    public static Boolean deleteByAppVariableId(final String id, final String domainId) {
        Boolean result;
        result = (Boolean) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    String[] bindArgs = {
                            id,
                            domainId
                    };

                    String deleteAllCommand = MainApplication.getAppContext().getString(R.string.sql_App_Variable_DeleteByAppVariableId);
                    database.execSQL(deleteAllCommand, bindArgs);
                    return true;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - deleteByAppVariableId : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        return result;

    }


    public static List<AppVariable> insert(final List<AppVariable> AppVariables) {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    for (AppVariable AppVariable : AppVariables) {


                        String[] bindArgs = {
                                String.valueOf(AppVariable.getAppVariableId()),
                                AppVariable.getValue(),
                                AppVariable.getDomainId()
                        };
                        database.execSQL(MainApplication.getAppContext().getString(R.string.sql_App_Variable_Insert), bindArgs);


                    }
                    return AppVariables;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - insert : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        return (List<AppVariable>) result;
    }

    public static AppVariable insert(final AppVariable AppVariable) {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {

                    String[] bindArgs = {
                            String.valueOf(AppVariable.getAppVariableId()),
                            AppVariable.getValue(),
                            AppVariable.getDomainId()
                    };
                    database.execSQL(MainApplication.getAppContext().getString(R.string.sql_App_Variable_Insert), bindArgs);


                    return AppVariable;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - insert : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        return (AppVariable) result;

    }

    public static Boolean updateById(final AppVariable AppVariable) {
        Boolean result;
        result = (Boolean) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    String[] bindArgs = {
                            AppVariable.getValue(),
                            AppVariable.getAppVariableId(),
                            AppVariable.getDomainId()
                    };
                    database.execSQL(MainApplication.getAppContext().getString(R.string.sql_App_Variable_Update), bindArgs);
                    return true;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - update : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        return result;
    }


    public static int getCountItems() {

        int result;
        result = (Integer) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    int countResult = -1;
                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_CountItems);
                    Cursor cursor = database.rawQuery(query, null);

                    if (cursor != null && cursor.moveToFirst()) {
                        countResult = cursor.getInt(0);
                    }
                    return countResult;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - getCountItems : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });

        return result;

    }


    public static int getCountItemsByDomainID(final String domainId) {

        int result;
        result = (Integer) AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public Object run(SQLiteDatabase database) {
                try {
                    int countResult = -1;

                    String[] bindArgs = {
                            String.valueOf(domainId)};

                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_CountItems_By_DomainId);
                    Cursor cursor = database.rawQuery(query, bindArgs);

                    if (cursor != null && cursor.moveToFirst()) {
                        countResult = cursor.getInt(0);
                    }
                    return countResult;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - getCountItems : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });

        return result;

    }


        public static AppVariable selectByAppVariableID(final String Id, final String domainId) {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public AppVariable run(SQLiteDatabase database) {
                try {
                    String[] bindArgs = {
                            String.valueOf(Id),
                            domainId};

                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_SelectByAppVariableID);

                    Cursor cursor = database.rawQuery(query, bindArgs);

                    List<AppVariable> dataList = manageCursor(cursor);

                    closeCursor(cursor);

                    return (dataList.size() > 0) ? dataList.get(0) : null;
                } catch (Exception e) {

                    String msg = "AppVariableDAO - selectByAppVariableID : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }

            }
        });

        return (result == null) ? null : (AppVariable) result;
    }
    
    @SuppressWarnings("unchecked")
    public static List<AppVariable> selectAllLikeAppVariableID (final String Id) {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public List<AppVariable> run(SQLiteDatabase database) {
                try {
                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_SelectAllLikeAppVariableID);
                    
                    String[] bindArgs = {String.valueOf(Id)};
                    
                    Cursor cursor = database.rawQuery(query, bindArgs);
                    
                    List<AppVariable> dataList = manageCursor(cursor);
                    
                    closeCursor(cursor);
                    
                    return dataList;
                    
                } catch (Exception e) {
                    
                    String msg = "AppVariableDAO - selectAll : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });
        
        return (List<AppVariable>) result;
    }

    @SuppressWarnings("unchecked")
    public static List<AppVariable> selectAllByDomainId(final String domainId) {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public List<AppVariable> run(SQLiteDatabase database) {
                try {
                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_SelectByDomain);

                    String[] bindArgs = {String.valueOf(domainId)};

                    Cursor cursor = database.rawQuery(query, bindArgs);

                    List<AppVariable> dataList = manageCursor(cursor);

                    closeCursor(cursor);

                    return dataList;

                } catch (Exception e) {

                    String msg = "AppVariableDAO - selectAll : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });

        return (List<AppVariable>) result;
    }

    @SuppressWarnings("unchecked")
    public static List<AppVariable> selectAll() {
        Object result;
        result = AppDatabaseManager.getInstance().executeQuery(new IQueryExecutor() {
            @Override
            public List<AppVariable> run(SQLiteDatabase database) {
                try {
                    String query = MainApplication.getAppContext().getString(R.string.sql_App_Variable_SelectAll);

                    Cursor cursor = database.rawQuery(query, null);

                    List<AppVariable> dataList = manageCursor(cursor);

                    closeCursor(cursor);

                    return dataList;

                } catch (Exception e) {

                    String msg = "AppVariableDAO - selectAll : " + e.getMessage().toString();
                    L.e(msg);
                    throw new RuntimeException(msg);
                }
            }
        });

        return (List<AppVariable>) result;
    }

    protected static AppVariable cursorToData(Cursor cursor) {
        try {
            int idIndex = cursor.getColumnIndex(Table.COLUMN_ID);
            int valueIndex = cursor.getColumnIndex(Table.COLUMN_VALUE);
            int domainIdIndex = cursor.getColumnIndex(Table.COLUMN_DOMAIN_ID);


            AppVariable AppVariable = new AppVariable();
            AppVariable.setAppVariableId(cursor.getString(idIndex));
            AppVariable.setDomainId(cursor.getString(domainIdIndex));
            AppVariable.setValue(cursor.getString(valueIndex));

            return AppVariable;
        } catch (Exception e) {

            String msg = "AppVariableDAO - cursorToMasterData : " + e.getMessage().toString();
            L.e(msg);
            throw new RuntimeException(msg);
        }
    }

    protected static void closeCursor(Cursor cursor) {
        try {


            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {

            String msg = "AppVariableDAO - closeCursor : " + e.getMessage().toString();
            L.e(msg);
            throw new RuntimeException(msg);
        }
    }

    protected static List<AppVariable> manageCursor(Cursor cursor) {
        try {
            List<AppVariable> dataList = new ArrayList<AppVariable>();

            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    AppVariable account = cursorToData(cursor);
                    dataList.add(account);
                    cursor.moveToNext();
                }
            }
            return dataList;
        } catch (Exception e) {

            String msg = "AppVariableDAO - manageCursor : " + e.getMessage().toString();
            L.e(msg);
            throw new RuntimeException(msg);
        }
    }


    public interface Table {

        String COLUMN_ID = "AppVariableId";
        String COLUMN_VALUE = "Value";
        String COLUMN_DOMAIN_ID = "DomainId";
        String TABLE_NAME = "AppVariable";

    }
}