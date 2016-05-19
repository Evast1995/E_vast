package com.ychen.tourism.helper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by evast on 16-3-22.
 */
public class ProviderHelper extends ContentProvider{
    private static final UriMatcher URI_MATCHER;
    private static final int PLAN_CODE = 1;
    private static final int PLAN_CODE_ID = 2;
    private SQLiteDatabase mSqlDataBase;
    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(Table.AUTHORITY, Table.PLAN_TABLE_NAME, PLAN_CODE);
        URI_MATCHER.addURI(Table.AUTHORITY, Table.PLAN_TABLE_NAME+"/#", PLAN_CODE_ID);
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        mSqlDataBase = dbHelper.getWritableDatabase();
        return (mSqlDataBase == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER.match(uri)){
            case PLAN_CODE:{
                return mSqlDataBase.query(Table.PLAN_TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case PLAN_CODE_ID:{
                String id = uri.getPathSegments().get(1);
                String where = Table.ID +" = ?";
                return mSqlDataBase.query(Table.PLAN_TABLE_NAME,null,where,new String[]{id},null,null,null);
            }
            default:{
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)){
            case PLAN_CODE:{
                return "vnd.android.cursor.dir/vnd.ychen.plan";
            }
            case PLAN_CODE_ID:{
                return "vnd.android.cursor.item/vnd.ychen.plan";
            }
            default:{
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mSqlDataBase.insert(Table.PLAN_TABLE_NAME,null,values);
        if(id > 0){
            uri = ContentUris.withAppendedId(uri, id);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)){
            case PLAN_CODE:{
                getContext().getContentResolver().notifyChange(uri, null);
                return mSqlDataBase.delete(Table.PLAN_TABLE_NAME,selection,selectionArgs);
            }
            case PLAN_CODE_ID:{
                String id = uri.getPathSegments().get(1);
                getContext().getContentResolver().notifyChange(uri, null);
                return mSqlDataBase.delete(Table.PLAN_TABLE_NAME,Table.ID+" = ?",new String[]{id});
            }
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)){
            case PLAN_CODE:{
                getContext().getContentResolver().notifyChange(uri, null);
                return mSqlDataBase.update(Table.PLAN_TABLE_NAME,values,selection,selectionArgs);
            }
            case PLAN_CODE_ID:{
                String id = uri.getPathSegments().get(1);
                getContext().getContentResolver().notifyChange(uri, null);
                return mSqlDataBase.update(Table.PLAN_TABLE_NAME,values,Table.ID+" = ?",new String[]{id});
            }
        }
        return 0;
    }
}
