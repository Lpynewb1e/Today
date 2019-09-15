package com.example.Activity.Databese.table;

public class ContentTable {
    /*
        表名
     */
    public static final String TABLENAME = "ContentTable";

    /*
        字段名
     */
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String CONTENT = "content";

    /*
        每一个字段对应表中的序号
     */

    public static final int NAME_NAME = 0;
    public static final int ID_ID = 1;
    public static final int CONTENT_CONTENT = 2;

    /*
        创建表
     */

    public static final String CREATE_TABLE = "create table if not exists " + TABLENAME + "(" +
            ID + " text primary key, " +
            NAME + " text, " +
            CONTENT + " text) ";

}
