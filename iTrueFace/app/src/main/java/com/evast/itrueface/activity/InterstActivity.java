package com.evast.itrueface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.adapter.CourseAdapter;
import com.evast.itrueface.bean.course.CousesVo;
import com.evast.itrueface.core.BaseActivity;
import com.evast.itrueface.xmpp.XmlppManager;

import java.util.List;

/**
 * Created by evast on 16-1-17.
 */
public class InterstActivity extends BaseActivity{
    private TextView titleView;
    private RelativeLayout backLayout;
    private TextView rightView;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interst);
    }

    @Override
    protected void init() {
        initTitle();
        listView = (ListView) findViewById(R.id.interst_list);
        List<CousesVo> list = XmlppManager.getInstance().getInterstTop();
        listView.setAdapter(new CourseAdapter(this,list));
    }


    /**
     * 初始化标题
     */
    private void initTitle(){
        backLayout = (RelativeLayout) findViewById(R.id.back_layout);
        titleView = (TextView) findViewById(R.id.title_center_tv);
        rightView = (TextView) findViewById(R.id.title_right_text);
        rightView.setText(R.string.screen);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setText(R.string.interst);
    }
}
