package com.example.Activity.Databese.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.Activity.Databese.DB;
import com.example.Activity.Databese.table.UserTable;

public class UserTableDao {
    private SQLiteDatabase db;
    private DB db_helper;

    public UserTableDao(Context context) {
        db_helper = new DB(context);
        db = db_helper.openDatabase();
    }

    public boolean add(String id, String name, String password) {
        ContentValues values = new ContentValues();
        values.put(UserTable.ID, id);
        values.put(UserTable.NAME, name);
        values.put(UserTable.PASSWORD, password);
        long result = db.insert(UserTable.TABLENAME, null, values);
        return result != -1;
    }
}
