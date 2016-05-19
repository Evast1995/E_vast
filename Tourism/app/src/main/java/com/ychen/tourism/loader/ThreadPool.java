package com.ychen.tourism.loader;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池用于管理整个应用耗时操作
 * Created by evast on 16-3-27.
 */
public class ThreadPool {
    private ThreadPoolExecutor mExecutor;
    private final int MAX_THREAD = 8;
    private ThreadPool(){
        mExecutor = new ScheduledThreadPoolExecutor(8);
    }
    private static ThreadPool threadPool;
    public static ThreadPool getInstance(){
        if(null == threadPool){
            synchronized (ThreadPool.class){
                if(null == threadPool){
                    threadPool = new ThreadPool();
                }
            }
        }
        return threadPool;
    }

    public void commit(Runnable runnable){
        mExecutor.execute(runnable);
    }
}
