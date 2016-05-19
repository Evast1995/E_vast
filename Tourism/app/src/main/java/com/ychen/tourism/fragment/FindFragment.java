package com.ychen.tourism.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.ychen.tourism.R;
import com.ychen.tourism.activity.PoiDetailActivity;
import com.ychen.tourism.baiduapi.overlayutil.PoiOverlay;
import com.ychen.tourism.helper.ContantsUtil;

/**
 * Created by evast on 16-3-17.
 */
public class FindFragment extends Fragment implements View.OnClickListener,
        OnGetPoiSearchResultListener, OnGetSuggestionResultListener{
    private MapView mMapView;
    private Context mContext;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private View mView;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationConfiguration.LocationMode mCurrentMode;
    /** 当前的经纬度*/
    private double mCurLatitude,mCurLongitude;
    /** poi搜索*/
    private PoiSearch mPoiSearch;
    private BitmapDescriptor mCurrentMarker;
    private LinearLayout editLayout;
    private Button searchBtn;
    /** 下一组数据*/
    private Button nextBtn;
    private EditText searchEdit;
    /** 当前搜索的查找关键字*/
    private String mCurrentSearchStr;
    /** 当前城市*/
    private String mCurrentCityStr;
    private EditText citySearchEdit;
    private EditText cityEdit;
    private LinearLayout cityLayout;
    /** 当前第几页的数据*/
    private int mPage = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find,null);
        init();
        return mView;
    }

    /**
     * 初始化poi搜索
     */
    private void initPoiSearch(){
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
    }


    /**
     * 初始化百度地图定位功能
     */
    private void initBaiduMap(){
        mMapView = (MapView) mView.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(mContext);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(200);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 初始化视图
     */
    private void    initView(){
        mView.findViewById(R.id.citySearch).setOnClickListener(this);
        mView.findViewById(R.id.nearbySearch).setOnClickListener(this);
        mView.findViewById(R.id.city_next_btn).setOnClickListener(this);
        mView.findViewById(R.id.city_search_btn).setOnClickListener(this);

        cityLayout = (LinearLayout) mView.findViewById(R.id.city_layout);
        citySearchEdit = (EditText) mView.findViewById(R.id.city_search_edit);
        cityEdit = (EditText) mView.findViewById(R.id.city_edit);
        editLayout = (LinearLayout) mView.findViewById(R.id.edit_layout);
        searchBtn = (Button) mView.findViewById(R.id.search_btn);
        searchEdit = (EditText) mView.findViewById(R.id.search_edit);
        nextBtn = (Button) mView.findViewById(R.id.next_data);
        searchBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }


    /**
     * 初始化
     */
    private void init(){
        initView();
        initBaiduMap();
        initPoiSearch();
    }
    

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        CloudManager.getInstance().destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }



    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null
                || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {// 没有找到检索结果
            Toast.makeText(mContext, "未找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {// 检索结果正常返回
            mBaiduMap.clear();
            MyPoiOverlay poiOverlay = new MyPoiOverlay(mBaiduMap);
            poiOverlay.setData(poiResult);// 设置POI数据
            mBaiduMap.setOnMarkerClickListener(poiOverlay);
            poiOverlay.addToMap();// 将所有的overlay添加到地图上
            poiOverlay.zoomToSpan();
            int totalPage = poiResult.getTotalPageNum();// 获取总分页数
            Toast.makeText(mContext,
                    "总共查到" + poiResult.getTotalPoiNum() + "个兴趣点, 分为"
                            + totalPage + "页", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(mContext, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(mContext, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
                    .show();
            String urlStr = result.getDetailUrl();
            if(null == urlStr && TextUtils.isEmpty(urlStr)){
                return;
            }
            Intent intent = new Intent(mContext,PoiDetailActivity.class);
            intent.putExtra(ContantsUtil.URL_KEY,urlStr);
            startActivity(intent);
        }
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult res) {

    }

    /**
     * 查找周边按钮点击事件
     */
    private void nearbySearchBtnOnClick() {
        mPage = 0;
        if (editLayout.getVisibility() == View.VISIBLE) {
            editLayout.setVisibility(View.GONE);
            mCurrentSearchStr = null;
        } else {
            editLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 城市搜索按钮点击事件
     */
    private void citySearchBtnOnClick(){
        mPage = 0;
        if(cityLayout.getVisibility() == View.VISIBLE){
            cityLayout.setVisibility(View.GONE);
            mCurrentSearchStr = null;
            mCurrentCityStr = null;
        }else{
            cityLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nearbySearch:{
                nearbySearchBtnOnClick();
                break;
            }
            case R.id.citySearch:{
                citySearchBtnOnClick();
                break;
            }
            case R.id.search_btn:{
                mCurrentSearchStr = searchEdit.getText().toString();
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_keystr),Toast.LENGTH_SHORT).show();
                    return;
                }
                nearbySearch(mPage,mCurrentSearchStr);
                mPage++;
                break;
            }
            case R.id.next_data:{
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_search_data),Toast.LENGTH_SHORT).show();
                    return;
                }
                nearbySearch(mPage,mCurrentSearchStr);
                mPage++;
                break;
            }
            case R.id.city_search_btn:{
                mCurrentCityStr = cityEdit.getText().toString();
                mCurrentSearchStr = citySearchEdit.getText().toString();
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_city),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_search_data),Toast.LENGTH_SHORT).show();
                    return;
                }
                citySearch(mPage,mCurrentCityStr,mCurrentSearchStr);
                mPage++;
                break;
            }
            case R.id.city_next_btn:{
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_city),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mCurrentSearchStr)){
                    Toast.makeText(mContext,getString(R.string.no_search_data),Toast.LENGTH_SHORT).show();
                    return;
                }
                citySearch(mPage,mCurrentCityStr,mCurrentSearchStr);
                mPage++;
            }
        }
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            if (isFirstLoc) {
                isFirstLoc = false;
                mCurLatitude = location.getLatitude();
                mCurLongitude = location.getLongitude();
                Log.e("--main--","mCurLatitude:"+mCurLatitude);
                Log.e("--main--","mCurLongitude:"+mCurLongitude);
                LatLng ll = new LatLng(mCurLatitude,
                        mCurLongitude );

                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }

        public void onReceivePoi(BDLocation poiLocation) {
            
        }
    }


    /**
     * 城市内搜索
     */
    private void citySearch(int page,String cityStr,String keyStr) {
        // 设置检索参数
        PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
        citySearchOption.city(cityStr);// 城市
        citySearchOption.keyword(keyStr);// 关键字
        citySearchOption.pageCapacity(15);// 默认每页10条
        citySearchOption.pageNum(page);// 分页编号
        // 发起检索请求
        mPoiSearch.searchInCity(citySearchOption);
    }

    /**
     * 附近检索
     * @param page 分页查找数据
     * @param keyStr　关键字
     */
    private void nearbySearch(int page,String keyStr) {
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
        nearbySearchOption.location(new LatLng(mCurLatitude, mCurLongitude));
        nearbySearchOption.keyword(keyStr);
        nearbySearchOption.radius(10000);// 检索半径，单位是米
        nearbySearchOption.pageNum(page);
        mPoiSearch.searchNearby(nearbySearchOption);// 发起附近检索请求
    }


    private class MyPoiOverlay extends PoiOverlay {
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            return true;
        }
    }

}
