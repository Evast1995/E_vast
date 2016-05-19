package com.evast.itrueface.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evast.itrueface.R;
import com.evast.itrueface.activity.CollectionActivity;
import com.evast.itrueface.activity.CourseActivity;
import com.evast.itrueface.activity.SettingActivity;
import com.evast.itrueface.activity.TeacherActivity;
import com.evast.itrueface.core.BaseFragment;

/**
 * Created by 72963 on 2015/12/10.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener{
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    @Override
    protected void init(View rootView) {
        initView(rootView);
    }
    private void initView(View view){
        view.findViewById(R.id.me_course_layout).setOnClickListener(this);
        view.findViewById(R.id.me_teacher_layout).setOnClickListener(this);
        view.findViewById(R.id.me_account_layout).setOnClickListener(this);
        view.findViewById(R.id.me_collection_layout).setOnClickListener(this);
        view.findViewById(R.id.me_setting_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_teacher_layout:{
                Intent intent = new Intent(context, TeacherActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.me_course_layout:{
                Intent intent = new Intent(context, CourseActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.me_account_layout:{
                break;
            }
            case R.id.me_collection_layout:{
                Intent intent = new Intent(context, CollectionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.me_setting_layout:{
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
