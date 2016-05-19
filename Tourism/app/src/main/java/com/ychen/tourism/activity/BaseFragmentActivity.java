package com.ychen.tourism.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.ychen.tourism.service.InitService;

/**
 * Created by evast on 16-3-17.
 */
public class BaseFragmentActivity extends FragmentActivity{
    private Fragment mFragmentContent;
    private InitService mInitService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetService();
    }


    /**
     * 对子类提供获取netservice的接口
     * @return
     */
    public InitService getNetService(){
        if (null == mInitService){
            initNetService();
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
     * 初始化网络加载service
     */
    private void initNetService(){
        Intent intent = new Intent(this,InitService.class);
        bindService(intent,con,BIND_AUTO_CREATE);
    }

    /**
     * 切换Fragment
     * @param resId 需要将Fragment放入那个容器中的id
     * @param to 需要切换的Fragment对象（v4包下的Fragment）
     */
    protected void switchFragmentContent(int resId,Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mFragmentContent!=null){
            if (mFragmentContent != to) {
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mFragmentContent).add(resId, to); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mFragmentContent).show(to); // 隐藏当前的fragment，显示下一个
                }
            }
        }else{
            transaction.add(resId, to);
        }
        /**
         * Can not perform this action after onSaveInstanceState
         * onSaveInstanceState方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后
         * 再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成 commitAllowingStateLoss()就行了，其效果是一样的。
         */
        transaction.commitAllowingStateLoss();  //推荐使用此方法，更安全，更方便
        mFragmentContent = to;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(con);
    }
}
