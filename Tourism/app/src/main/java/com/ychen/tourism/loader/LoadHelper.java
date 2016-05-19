package com.ychen.tourism.loader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;

/**
 *
 * 单例模式管理网络加载方法
 * Created by evast on 16-3-20.
 */
public class LoadHelper {
    private ContentResolver mContentResolver;
    private Handler handler = new Handler();
    private ThreadPool mThreadPool;
    private LoadHelper(Context context){
        mContentResolver = context.getContentResolver();
        mThreadPool = ThreadPool.getInstance();
    }
    private static LoadHelper mLoadHelper;
    public static LoadHelper getInstance(Context context){
        if(null == mLoadHelper){
            synchronized (LoadHelper.class){
                if(null == mLoadHelper) {
                    mLoadHelper = new LoadHelper(context);
                }
            }
        }
        return mLoadHelper;
    }

    /**
     * 向数据库写了数据
     * @param uri　操作数据库的uri
     * @param contentValues　插入的值
     * @param iCallBack　数据库操作完后的回调
     */
    public void insert(final Uri uri, final ContentValues contentValues,
                       final Runnable iCallBack){
        mThreadPool.commit(new Runnable() {
            @Override
            public void run() {
                mContentResolver.insert(uri,contentValues);
                handler.post(iCallBack);
            }
        });
    }

    /**
     * 对数据库的删除操作
     * @param uri
     * @param where
     * @param selectionArgs
     * @param iCallBack
     */
    public void delete(final Uri uri, final String where,
                       final String[] selectionArgs,final Runnable iCallBack){
        mThreadPool.commit(new Runnable() {
            @Override
            public void run() {
                int update = mContentResolver.delete(uri, where, selectionArgs);
                if(update > 0){
                    handler.post(iCallBack);
                }

            }
        });
    }


    /**
     * 对数据库更新
     * @param uri
     * @param contentValues
     * @param where
     * @param selectionArgs
     * @param iCallBack
     */
    public void updata(final Uri uri, final ContentValues contentValues,
                       final String where, final String[] selectionArgs, final Runnable iCallBack){
        mThreadPool.commit(new Runnable() {
            @Override
            public void run() {
                int update = mContentResolver.update(uri,contentValues,where,selectionArgs);
                if(update > 0) {
                    handler.post(iCallBack);
                }
            }
        });
    }

}
