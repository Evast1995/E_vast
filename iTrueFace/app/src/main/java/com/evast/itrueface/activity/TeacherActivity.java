package com.evast.itrueface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.adapter.TeacherAdapter;
import com.evast.itrueface.bean.information.TeacherVo;
import com.evast.itrueface.core.BaseActivity;
import com.evast.itrueface.xmpp.XmlppManager;

import java.util.List;

/**
 * Created by evast on 16-1-16.
 */
public class TeacherActivity extends BaseActivity{
    private ListView listView;
    private TextView titleView;
    private RelativeLayout backLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.teacher_listview);
        backLayout = (RelativeLayout) findViewById(R.id.back_layout);
        titleView = (TextView) findViewById(R.id.title_center_tv);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setText(R.string.teacher);
    }

    private void initData(){
        List<TeacherVo> list = XmlppManager.getInstance().getTeacherData();
        listView.setAdapter(new TeacherAdapter(this, list));
    }
}
