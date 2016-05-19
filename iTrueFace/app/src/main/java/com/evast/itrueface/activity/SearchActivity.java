package com.evast.itrueface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.evast.itrueface.R;
import com.evast.itrueface.core.BaseActivity;

/**
 * Created by evast on 16-1-17.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener{
    private EditText searchEdit;
    private LinearLayout historyLayout;
    private ListView hotListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setIsTemplate(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void init() {
        initView();
    }
    private void initView(){
        findViewById(R.id.cancel_tv).setOnClickListener(this);
        findViewById(R.id.search_searchtv).setOnClickListener(this);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        findViewById(R.id.history_del).setOnClickListener(this);
        historyLayout = (LinearLayout) findViewById(R.id.history_layout);
        hotListView = (ListView) findViewById(R.id.hot_search_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_tv:{
                finish();
                break;
            }
            case R.id.history_search:{
                break;
            }
            case R.id.search_edit:{
                break;
            }
        }
    }
}
