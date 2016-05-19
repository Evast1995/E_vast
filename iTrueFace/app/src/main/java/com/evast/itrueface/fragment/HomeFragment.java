package com.evast.itrueface.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.evast.itrueface.R;
import com.evast.itrueface.adapter.HomeListAdapter;
import com.evast.itrueface.bean.home.HomeVo;
import com.evast.itrueface.core.BaseFragment;
import com.evast.itrueface.util.net.LoadMessageUtil;
import com.evast.itrueface.weight.ImageScorll;

import java.util.List;

/**
 * Created by 72963 on 2015/12/10.
 */
public class HomeFragment extends BaseFragment {
    private int[] imageIds=new int[]{R.mipmap.home_test_one,R.mipmap.home_test_two,R.mipmap.home_test_three};
    private ListView listView;
    private List<HomeVo> list;
    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    protected void init(View rootView) {
        RelativeLayout imageScorllContainer = (RelativeLayout) rootView.findViewById(R.id.image_scroll_container);
        ImageScorll  imageScorll = new ImageScorll(getContext(),imageIds);
        imageScorll.setLayoutParams(new ViewGroup.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        imageScorllContainer.addView(imageScorll);
        listView = (ListView) rootView.findViewById(R.id.home_list);
        initData();
        setListData();
    }
    private void initData(){
        list = LoadMessageUtil.getHomeData();

    }
    private void setListData(){
        HomeListAdapter adapter = new HomeListAdapter(getContext(),list);
        listView.setAdapter(adapter);
    }
}
