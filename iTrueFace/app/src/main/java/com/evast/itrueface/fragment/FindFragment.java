package com.evast.itrueface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evast.itrueface.R;
import com.evast.itrueface.activity.InterstActivity;
import com.evast.itrueface.core.BaseFragment;

/**
 * Created by 72963 on 2015/12/10.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener{


    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find,container,false);
    }

    @Override
    protected void init(View rootView) {
        rootView.findViewById(R.id.find_one_image).setOnClickListener(this);
        rootView.findViewById(R.id.find_two_image).setOnClickListener(this);
        rootView.findViewById(R.id.find_three_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_one_image:{
                Intent intent = new Intent(getActivity(), InterstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.find_two_image:{
                break;
            }
            case R.id.find_three_image:{
                break;
            }
        }
    }
}
