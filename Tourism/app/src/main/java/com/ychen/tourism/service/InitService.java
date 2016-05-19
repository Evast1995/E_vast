package com.ychen.tourism.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ychen.tourism.helper.DatabaseHelper;
import com.ychen.tourism.loader.LoadHelper;



/**
 * 后台任务放在后台加载　一般用与加载网络请求和数据库等耗时操作
 * Created by evast on 16-3-20.
 */
public class InitService extends Service{

    private LoadHelper mLoadHelper = null;
    private DatabaseHelper databaseHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if(null == mLoadHelper){
            mLoadHelper = LoadHelper.getInstance(this);
        }
        if(null == databaseHelper){
            databaseHelper = new DatabaseHelper(this);
        }
    }

    public class NetBinder extends Binder{
        public InitService getService(){
            return InitService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new NetBinder();
    }

    public void insert(Uri uri,ContentValues contentValues,Runnable iCallBack){
       mLoadHelper.insert(uri,contentValues,iCallBack);
    }

    public void delete(Uri uri, String where,
                       String[] selectionArgs, Runnable iCallBack){
        mLoadHelper.delete(uri, where, selectionArgs, iCallBack);
    }

    public void delete(Uri uri,Runnable iCallBack){
        mLoadHelper.delete(uri,null,null,iCallBack);
    }

    public void updata(Uri uri, ContentValues contentValues,
                        String where, String[] selectionArgs,  Runnable iCallBack){
        mLoadHelper.updata(uri, contentValues, where, selectionArgs, iCallBack);
    }

    public void updata(Uri uri,ContentValues contentValues,Runnable iCallBack){
        mLoadHelper.updata(uri,contentValues,null,null,iCallBack);
    }

}
