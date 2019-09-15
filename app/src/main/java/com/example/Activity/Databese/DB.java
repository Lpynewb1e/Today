package com.example.Activity.Databese;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.Activity.Databese.table.ContentTable;

public class DB extends SQLiteOpenHelper {
    private Context context;
    private static final String Database_Name = "Today.db";
    private static final int Database_Version = 1;
    private static DB instance = null;
    private SQLiteDatabase db;


   public DB(Context context){
       super(context,Database_Name,null,Database_Version);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContentTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
     * 创建数据库
     */

    public SQLiteDatabase openDatabase(){
        if(db == null)
            db = this.getWritableDatabase();
        return db;
    }
}
