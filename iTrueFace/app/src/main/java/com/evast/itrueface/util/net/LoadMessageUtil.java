package com.evast.itrueface.util.net;

import android.content.Context;

import com.evast.itrueface.bean.home.ContextVo;
import com.evast.itrueface.bean.home.HomeVo;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载网络数据
 * Created by evast on 16-1-16.
 */
public class LoadMessageUtil{
    /**
     * 获取页面的数据
     * @return
     */
    public static void addMessageData(final Context context){
        RequestParams requestParams = new RequestParams();
        /** 添加参数*/
        requestParams.add("key","value");
        /** get 请求方式*/
        new RequestNet(context).get("url", requestParams, new RequestNet.ResponseResult() {
            @Override
            public void successful(String response) {
                /** 服务器返回的数据*/
            }

            @Override
            public void exception() {

            }
        });
        /** post 请求方式*/
        new RequestNet(context).post("url", requestParams, new RequestNet.ResponseResult() {
            @Override
            public void successful(String response) {

            }

            @Override
            public void exception() {

            }
        });

    }

    /**
     * 获取首页的数据
     */
    public static List<HomeVo> getHomeData(){
        List<HomeVo> list = new ArrayList<>();
        HomeVo homeVo1 = new HomeVo();
        homeVo1.setTop("热门Top10");
        ContextVo contextVo1 = new ContextVo();
        contextVo1.setCourseName("吉他");
        contextVo1.setTeacherName("Learn");

        ContextVo contextVo2 = new ContextVo();
        contextVo2.setCourseName("足球");
        contextVo2.setTeacherName("Jeason");
        homeVo1.setContextVos(new ContextVo[]{contextVo1, contextVo2});
        list.add(homeVo1);

        HomeVo homeVo2 = new HomeVo();
        homeVo2.setTop("兴趣Top10");
        ContextVo contextVo12 = new ContextVo();
        contextVo12.setCourseName("吉他");
        contextVo12.setTeacherName("Learn");

        ContextVo contextVo22 = new ContextVo();
        contextVo22.setCourseName("足球");
        contextVo22.setTeacherName("Jeason");
        homeVo2.setContextVos(new ContextVo[]{contextVo12, contextVo22});
        list.add(homeVo2);
        return list;
    }


}
