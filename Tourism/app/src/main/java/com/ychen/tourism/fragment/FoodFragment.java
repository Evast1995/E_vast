package com.ychen.tourism.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.loopj.android.http.RequestParams;
import com.ychen.tourism.R;
import com.ychen.tourism.activity.FoodActivity;
import com.ychen.tourism.bean.FoodVo;
import com.ychen.tourism.bean.ResultVo;
import com.ychen.tourism.core.BaseFragment;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.util.JSONParseUtil;
import com.ychen.tourism.util.RequestNet;
import com.ychen.tourism.widget.adapter.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evast on 16-5-19.
 */
public class FoodFragment extends BaseFragment{
    private Context mContext;
    private ListView listView;
    private int page = 1;
    private String city;
    private static final String FOOD = "http://apis.juhe.cn/catering/query";
    private static final String FOOD_KEY = " \t\n" +
            "95875bcb928715d9f4ac31686ffa16e1";
    private FoodAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food,container,false);
    }

    @Override
    protected void init(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.food_list_view);
        init();
    }



    private void init(){
        initMap();
        RequestParams requestParams = new RequestParams();
        requestParams.put("page",page);
        requestParams.put("pagesize",20);
        requestParams.put("lng",mCurLongitude);
        requestParams.put("lat",mCurLatitude);
        requestParams.put("key",FOOD_KEY);
        new RequestNet(mContext).get(FOOD, requestParams, new RequestNet.ResponseResult() {
            @Override
            public void successful(String response) {
                FoodVo foodVo = JSONParseUtil.parseObject(response, FoodVo.class);
                List<ResultVo> list = new ArrayList<>();
                ResultVo[] resultVo = foodVo.getResult();
                if (resultVo!=null) {
                    list = new ArrayList<>();
                    for(int i=0;i<resultVo.length;i++){
                        list.add(resultVo[i]);
                    }
                }
                mAdapter = new FoodAdapter(mContext,list);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void exception() {

            }
        },getProgressWheel(),getErrorLayout());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResultVo resultVo = (ResultVo) mAdapter.getItem(position);
                String foodNameSum = null;
                foodNameSum = resultVo.getRecommended_dishes();
                String storeName = resultVo.getName();
                if(foodNameSum.equals("")){
                    Toast.makeText(mContext,"抱歉该店尚未上传菜谱详情",Toast.LENGTH_LONG).show();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString(ContantsUtil.FOOD_STORE, storeName);
                    bundle.putString(ContantsUtil.FOOD_KEY, foodNameSum);
                    Intent intent = new Intent(mContext, FoodActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
    }
    private void initMap(){
        LocationClient mLocClient = new LocationClient(mContext);
        mLocClient.registerLocationListener(new MyLocationListenner());
    }

    private double mCurLatitude;
    private double mCurLongitude;

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mCurLatitude = location.getLatitude();
            mCurLongitude = location.getLongitude();
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }

}
