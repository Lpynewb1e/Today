package com.example.Activity.Databese.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Activity.Bean.ContentTableBean;
import com.example.Activity.Databese.DB;
import com.example.Activity.Databese.table.ContentTable;

import java.util.ArrayList;
import java.util.List;


public class ContentTableDao {
    private SQLiteDatabase db;
    private DB db_helper;
    public ContentTableDao(Context context){
        db_helper = new DB(context);
        db = db_helper.openDatabase();
    }

    public boolean add(String name, String id, String content){
        ContentValues values = new ContentValues();
        values.put(ContentTable.NAME,name);
        values.put(ContentTable.ID,id);
        values.put(ContentTable.CONTENT,content);
        long result = db.insert(ContentTable.TABLENAME,null,values);
        return result!=1;
    }

    public boolean delete(String id){
        int num = db.delete(ContentTable.TABLENAME,ContentTable.ID + "=?",new String[]{id});
        return num != 1;
    }

    public List<ContentTableBean> queryAll(){
        Cursor cursor = db.query(ContentTable.TABLENAME,null,null,null,null,null,null);
        List<ContentTableBean> list = new ArrayList<>();
        while (cursor.moveToNext()){
            ContentTableBean contentTableBean = new ContentTableBean();
            contentTableBean.setName(cursor.getString(ContentTable.NAME_NAME));
            contentTableBean.setName(cursor.getString(ContentTable.ID_ID));
            contentTableBean.setName(cursor.getString(ContentTable.CONTENT_CONTENT));
            list.add(contentTableBean);
        }
        cursor.close();
        return list;
    }
}
