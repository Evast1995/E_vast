package com.evast.itrueface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.core.BaseActivity;

/**
 * Created by evast on 16-1-17.
 */
public class SettingActivity extends BaseActivity{
    private TextView titleView;
    private RelativeLayout backLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setIsTemplate(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void init() {
        initTitle();
    }

    private void initTitle(){
        backLayout = (RelativeLayout) findViewById(R.id.back_layout);
        titleView = (TextView) findViewById(R.id.title_center_tv);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setText(R.string.setting);
    }

}
