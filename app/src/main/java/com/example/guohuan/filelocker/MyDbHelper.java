package com.example.guohuan.filelocker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
   private static final String DB_NAME = "myDb";
    private static final int DB_VERSION = 2;

    // first table for true or false and single answer
    public static final String LOGIN_TABLE_NAME = "Login";
    public static final String LOGIN_COL_PASSWORD = "Topic";
    private static final String CREATE_LOGIN = "CREATE TABLE " +LOGIN_TABLE_NAME
            + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LOGIN_COL_PASSWORD + " TEXT);";


    public MyDbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LOGIN_TABLE_NAME);
        onCreate(db);
    }


}
