package com.example.Activity.Databese.table;

public class UserTable {
    /*
     * 表明
     */
    public static final String TABLENAME = "UserTable";

    /*
     * 字段名
     */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    /**
     * 每一个字段代表的序号
     */
    public static final int ID_ID = 0;
    public static final int NAME_NAME = 1;
    public static final int PASSWORD_PASSWORD = 2;

    /**
     * 创建表
     */

    public static final String CREATE_TABLE = "create table if not exists " + TABLENAME + "(" +
            ID + " text primary key, " +
            NAME + " text, " +
            PASSWORD + " text) ";
}
