package com.ychen.tourism.activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ychen.tourism.MyApplication;
import com.ychen.tourism.R;
import com.ychen.tourism.bean.PlanBean;
import com.ychen.tourism.helper.Table;
import com.ychen.tourism.service.InitService;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.util.TimeUtils;

/**
 * Created by evast on 16-3-22.
 */
public class AddPlanActivity extends BaseFragmentActivity implements View.OnClickListener{
    private EditText nameEt;
    private EditText addressEt;
    private EditText infoEt;
    private EditText spendEt;
    private Button startTimeBtn;
    private Button endTimeBtn;
    private Button sureBtn;
    private PlanBean mPlanBean = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);
        init();
        initData();
    }

    /**
     * 如果是编辑旅游计划则初始化数据
     */
    private void initData(){
        Intent intent = getIntent();
        mPlanBean = (PlanBean) intent.getSerializableExtra(ContantsUtil.PLAN_BEAN_KEY);
        if(null == mPlanBean){
            return;
        }
        nameEt.setText(mPlanBean.getName());
        addressEt.setText(mPlanBean.getAddress());
        infoEt.setText(mPlanBean.getInfo());
        spendEt.setText(mPlanBean.getOverHead());
        startTimeBtn.setText(TimeUtils.getTimeStrByStamp(mPlanBean.getStarttime()));
        startTimeBtn.setText(TimeUtils.getTimeStrByStamp(mPlanBean.getEndtime()));
        sureBtn.setText(R.string.update_data);
    }

    private void init(){
        nameEt = (EditText) findViewById(R.id.plan_name);
        addressEt = (EditText) findViewById(R.id.plan_address);
        infoEt = (EditText) findViewById(R.id.plan_info);
        spendEt = (EditText) findViewById(R.id.plan_overhead);
        startTimeBtn = (Button) findViewById(R.id.plan_starttime);
        endTimeBtn = (Button) findViewById(R.id.plan_endtime);
        sureBtn = (Button) findViewById(R.id.sava_plan);

        startTimeBtn.setText(TimeUtils.getCurrentDate());
        endTimeBtn.setText(TimeUtils.getCurrentDate());

        startTimeBtn.setOnClickListener(this);
        endTimeBtn.setOnClickListener(this);
        sureBtn.setOnClickListener(this);
    }

    /**
     * 打开选择日期的视图
     */
    private void openChangeDataView(final Button timeBtn){
        String timeStr = timeBtn.getText().toString();
        int year = Integer.parseInt(timeStr.substring(0,4));
        int month = Integer.parseInt(timeStr.substring(5,7));
        int day= Integer.parseInt(timeStr.substring(8,10));

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                timeBtn.setText(TimeUtils.spellDate(year, monthOfYear, dayOfMonth));
            }
        },year,month-1,day);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plan_starttime:{
                openChangeDataView(startTimeBtn);
                break;
            }
            case R.id.plan_endtime:{
                openChangeDataView(endTimeBtn);
                break;
            }
            case R.id.sava_plan:{
                savePlan();
                break;
            }
        }
    }

    /**
     * 获取时间
     */
    private long getWindowTime(Button timeBtn){
        String dateStr = timeBtn.getText().toString();
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(5, 7));
        int day= Integer.parseInt(dateStr.substring(8, 10));
        return TimeUtils.getTime(year,month,day,0,0);
    }

    private void savePlan(){
        PlanBean planBean = new PlanBean();
        planBean.setName(nameEt.getText().toString());
        planBean.setInfo(infoEt.getText().toString());
        planBean.setAddress(addressEt.getText().toString());
        planBean.setStarttime(getWindowTime(startTimeBtn));
        planBean.setEndtime(getWindowTime(endTimeBtn));
        planBean.setOverHead(spendEt.getText().toString());
        if(TextUtils.isEmpty(planBean.getName())){
            Toast.makeText(this,getString(R.string.plan_name_isnull),Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(planBean.getAddress())){
            Toast.makeText(this,getString(R.string.plan_address_isnull),Toast.LENGTH_SHORT).show();
            return;
        }

        /** 插入数据*/
        MyApplication myApplication  = (MyApplication) getApplication();
        InitService service = this.getNetService();
        ContentValues contentValues = planBean.toContentValue();
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.VISIBLE);

        /**  当数据未更新数据时*/
        if(null != mPlanBean){
            service.updata(Table.PlanTable.CONTENT_URI, planBean.toContentValue(), Table.ID + " = ?",
                    new String[]{String.valueOf(mPlanBean.getId())}, new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AddPlanActivity.this, getString(R.string.update_sucessful), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            return;
        }


        service.insert(Table.PlanTable.CONTENT_URI, contentValues, new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddPlanActivity.this,getString(R.string.add_sucessful),Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }




}
