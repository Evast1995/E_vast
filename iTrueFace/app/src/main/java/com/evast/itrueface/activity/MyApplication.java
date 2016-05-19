package com.evast.itrueface.activity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by CaiBing.Zhang on 2015/10/13.
 */
public class MyApplication extends Application{

    private static final long serialVersionUID = -9067654039944252043L;
    private RequestQueue requestQueue;
    private static MyApplication context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    /** 输出日志 测试环境为true输出日志 生产环境改为false不输出日志*/
    public static final boolean IS_DEBUG = true;

    public static MyApplication getInstance() {
        return context;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            synchronized (this) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(this);
                }
            }
        }
        return requestQueue;
    }
}
