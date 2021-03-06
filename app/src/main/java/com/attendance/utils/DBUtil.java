package com.attendance.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {
    private static DBUtil dbUtil;

    private DBUtil(Context context, String name, SQLiteDatabase.CursorFactory factory,
                   int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (dbUtil == null) {
            // 指定数据库名为attendance，需修改时在此修改；此处使用默认工厂；指定版本为1
            dbUtil = new DBUtil(context, "attendance", null, 1);
        }
        return dbUtil.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String create_course_table_sql = "create table course(id integer primary key, name varchar(60) not null);";
            db.execSQL(create_course_table_sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(newVersion){
            case 2:
                break;
            default:
                break;
        }
        String drop_course_table_sql = "drop table if exists course;";
        db.execSQL(drop_course_table_sql);
        onCreate(db);
    }

}

