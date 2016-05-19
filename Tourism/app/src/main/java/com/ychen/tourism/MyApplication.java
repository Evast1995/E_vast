package com.ychen.tourism;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.baidu.mapapi.SDKInitializer;
import com.ychen.tourism.service.InitService;

/**
 * Created by evast on 16-3-20.
 */
public class MyApplication extends Application{
    private InitService mInitService;
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
//        initService();
    }

    /**
     * 对外提供获取netservice的接口
     * @return
     */
    public InitService getService(){
        if (null == mInitService){
            initService();
        }
        return mInitService;
    }

    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mInitService = ((InitService.NetBinder)binder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 初始化数据库操作网络操作等service
     */
    private void initService(){
        Intent intent = new Intent(getApplicationContext(),InitService.class);
        bindService(intent, con, BIND_AUTO_CREATE);
    }
}
