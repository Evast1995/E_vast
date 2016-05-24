package com.ychen.tourism.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.loopj.android.http.RequestParams;
import com.ychen.tourism.R;
import com.ychen.tourism.bean.delicous.DataVo;
import com.ychen.tourism.bean.delicous.DeliciousVo;
import com.ychen.tourism.bean.delicous.ResultVo;
import com.ychen.tourism.bean.delicous.StepsVo;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.util.ImageLoadUtil;
import com.ychen.tourism.util.JSONParseUtil;
import com.ychen.tourism.util.RequestNet;
import com.ychen.tourism.util.StringUtil;
import com.ychen.tourism.widget.adapter.DeliciousAdapter;

import java.util.ArrayList;
import java.util.List;


public class FoodActivity extends BaseActivity{

    private String foodName;
    private List<String> foodArray;
    private TextView contextTv;
    private Bundle bundle;
    private Boolean type = true;//是否为第一次加载
    private int k = 0;
    private static final String DELICIOUS = "http://apis.juhe.cn/cook/query.php";
    private static final String DELICIOUS_KEY = "da3f5b38d594c861c85712df1b77e872";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
    }

    @Override
    protected void init() {
        contextTv = (TextView) findViewById(R.id.food_context_introduce);
        if(bundle == null) {
            bundle = getIntent().getExtras();
        }
        String storeName = bundle.getString(ContantsUtil.FOOD_STORE);
        if(foodArray == null) {
            String foodNameSum = bundle.getString(ContantsUtil.FOOD_KEY);
            foodArray = new ArrayList<>();
            foodArray = StringUtil.getArrayString(foodNameSum);
        }
        if(foodArray.size() == 0) {
            Toast.makeText(this,"抱歉该店为上传菜谱详情",Toast.LENGTH_LONG).show();
            return;
        }
        foodName = foodArray.get(k);
        getSupportActionBar().setTitle(storeName);

        final TextView showAllTv = (TextView) findViewById(R.id.food_context_open);
        showAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showAllTv.getText().toString().equals(getResources().getString(R.string.open_message))) {
                    contextTv.setMaxLines(100);
                    showAllTv.setText(R.string.close_message);
                } else {
                    contextTv.setMaxLines(3);
                    showAllTv.setText(R.string.open_message);
                }
            }
        });

        getInformation(foodName, type);

    }
    private void getInformation(final String foodName, final Boolean type){
        RequestParams requestParams = new RequestParams();
        requestParams.put("menu",foodName);
        requestParams.put("dtype","json");
        requestParams.put("key", DELICIOUS_KEY);
        new RequestNet(this).get(DELICIOUS, requestParams, new RequestNet.ResponseResult() {
            @Override
            public void successful(String response) {
                DeliciousVo deliciousVo = JSONParseUtil.parseObject(response, DeliciousVo.class);
                if(deliciousVo.getResultcode() == 200) {
                    initView(deliciousVo);
                }else{
                    if(!type) {
                        Toast.makeText(FoodActivity.this, "抱歉该菜为上传详细步骤", Toast.LENGTH_LONG).show();
                    }else{
                        k++;
                        getInformation(foodArray.get(k),type);
                    }
                }
            }

            @Override
            public void exception() {

            }
        });
    }
    public void initView(DeliciousVo deliciousVo){
        type = false;
        ResultVo resultVo =  deliciousVo.getResult();
        DataVo[] dataVos = resultVo.getData();
        if(dataVos!=null){
            DataVo data = dataVos[0];
            String tiltleStr = data.getTitle();
            String contextStr = data.getImtro();
            TextView titleTv = (TextView) findViewById(R.id.food_context_name);
            titleTv.setText(tiltleStr);
            contextTv.setText(contextStr);

            String[] imageStrSum = data.getAlbums();
            if(imageStrSum!=null){
                ImageView imageView = (ImageView) findViewById(R.id.food_context_image_head);
                ImageLoadUtil.getInstance().setImageLoader(imageView,imageStrSum[0]);
            }
            StepsVo[] stepsVos = data.getSteps();
            ListView listView = (ListView) findViewById(R.id.food_context_list_view);
            listView.setAdapter(new DeliciousAdapter(this,stepsVos));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        for(int i=0;i<foodArray.size();i++){
            if(id == i){
                getInformation(foodArray.get(i),type);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


}
