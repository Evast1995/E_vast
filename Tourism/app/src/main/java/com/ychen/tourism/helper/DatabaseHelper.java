package com.ychen.tourism.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evast on 16-3-21.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库版本号
    private static final int DATABASE_VERSION = 1;
    // 数据库名
    private static final String DATABASE_NAME = "TourismDB.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 构建创建表的SQL语句
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("CREATE TABLE if NOT EXISTS[" + Table.PLAN_TABLE_NAME + "] (");
        sBuffer.append("["+Table.ID+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sBuffer.append("["+Table.PlanTable.NAME+"] TEXT,");
        sBuffer.append("["+Table.PlanTable.INFO+"] TEXT,");
        sBuffer.append("["+Table.PlanTable.START_TIME+"] TEXT,");
        sBuffer.append("["+Table.PlanTable.END_TIME+"] TEXT,");
        sBuffer.append("["+Table.PlanTable.OVERHEAD+"] TEXT,");
        sBuffer.append("["+Table.PlanTable.ADDRESS+"] TEXT)");
//
//        sBuffer.append("CREATE TABLE if NOT EXISTS[" + Table.COMMENT_NAME + "] (");
//        sBuffer.append("["+Table.ID+"] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
//        sBuffer.append("["+Table.CommentTable.TITLE+"] TEXT,");
//        sBuffer.append("["+Table.CommentTable.DESCRIPTION+"] TEXT,");
//        sBuffer.append("["+Table.CommentTable.CONTEXT+"] TEXT)");



        // 执行创建表的SQL语句
        db.execSQL(sBuffer.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == oldVersion){return;}
        db.execSQL("DROP TABLE IF EXISTS " + Table.PLAN_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table.COMMENT_NAME);
        onCreate(db);
    }
}
