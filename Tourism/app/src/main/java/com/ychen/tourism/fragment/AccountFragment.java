package com.ychen.tourism.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.ychen.tourism.R;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.util.SharedPreferenceUtil;
import com.ychen.tourism.util.TimeUtils;

/**
 * Created by evast on 16-3-17.
 */
public class AccountFragment extends Fragment implements View.OnClickListener{
    private TextView nameTv;
    private TextView accountTv;
    private TextView birthdayTv;
    private TextView weixinTv;
    private TextView emailTv;
    private TextView distinctTv;
    private TextView addressTv;
    private ImageView headIv;
    private Context mContext;
    private TextView sexTv;
    private Button emailBtn;
    private Button distinctBtn;
    private Button addressBtn;
    private EditText emailEt;
    private EditText distinctEt;
    private EditText addressEt;

    private static final String SEX = "sex";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String BIRTH = "birth";
    private static final String DISTINCT = "disctinct";
    private static final String ADDRESS = "address";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,null);
        init(view);
        return view;
    }


    protected void init(View view) {
        initView(view);
    }

    private void initView(View view){
        nameTv = (TextView) view.findViewById(R.id.name);
        accountTv = (TextView) view.findViewById(R.id.account);
        birthdayTv = (TextView) view.findViewById(R.id.birthday);
        weixinTv = (TextView) view.findViewById(R.id.weixin);
        emailTv = (TextView) view.findViewById(R.id.email);
        distinctTv = (TextView) view.findViewById(R.id.distinct);
        addressTv = (TextView) view.findViewById(R.id.address);
        headIv = (ImageView) view.findViewById(R.id.information_head_image);
        sexTv = (TextView) view.findViewById(R.id.sex);
        emailBtn = (Button) view.findViewById(R.id.email_btn);
        emailEt = (EditText) view.findViewById(R.id.email_edit);
        addressBtn = (Button) view.findViewById(R.id.address_btn);
        addressEt = (EditText) view.findViewById(R.id.address_edit);
        distinctBtn = (Button) view.findViewById(R.id.distinct_btn);
        distinctEt = (EditText) view.findViewById(R.id.distinct_edit);
        emailBtn.setOnClickListener(this);
        distinctBtn.setOnClickListener(this);
        addressBtn.setOnClickListener(this);
        view.findViewById(R.id.sex_layout).setOnClickListener(this);
        view.findViewById(R.id.birthday_layout).setOnClickListener(this);
        view.findViewById(R.id.email_layout).setOnClickListener(this);
        view.findViewById(R.id.distinct_layout).setOnClickListener(this);
        view.findViewById(R.id.address_layout).setOnClickListener(this);

        getData();
    }

    private void getData(){
        String sexStr = (String) SharedPreferenceUtil.get(mContext,SEX,"男");
        String birthStr = (String) SharedPreferenceUtil.get(mContext,BIRTH,"1995.03.02");
        String emailStr = (String) SharedPreferenceUtil.get(mContext,EMAIL,"729638529@qq.com");
        String distinctStr = (String) SharedPreferenceUtil.get(mContext,DISTINCT,"北京-海淀");
        String addressStr = (String) SharedPreferenceUtil.get(mContext,ADDRESS,"北京市海淀区学院路");

        sexTv.setText(sexStr);
        birthdayTv.setText(birthStr);
        emailTv.setText(emailStr);
        distinctTv.setText(distinctStr);
        addressTv.setText(addressStr);
    }

    private void showSexDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.input_sex);
        builder.setNegativeButton(R.string.man, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferenceUtil.put(mContext,SEX,"男");
                sexTv.setText("男");
            }
        });
        builder.setPositiveButton(R.string.woman, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferenceUtil.put(mContext,SEX,"女");
                sexTv.setText("女");
            }
        });
        builder.show();
    }

    /**
     * 打开选择日期的视图
     */
    private void openChangeDataView(){
        String timeStr = birthdayTv.getText().toString();
        int year = Integer.parseInt(timeStr.substring(0,4));
        int month = Integer.parseInt(timeStr.substring(5,7));
        int day= Integer.parseInt(timeStr.substring(8,10));

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String timeStr = TimeUtils.spellDate(year, monthOfYear, dayOfMonth);
                birthdayTv.setText(timeStr);
                SharedPreferenceUtil.put(mContext,BIRTH,timeStr);
            }
        },year,month-1,day);
        datePickerDialog.show();
    }


    private void showEmail(){
        if(emailBtn.getVisibility() == View.VISIBLE) {
            emailBtn.setVisibility(View.GONE);
            emailEt.setVisibility(View.GONE);
        }else{
            emailBtn.setVisibility(View.VISIBLE);
            emailEt.setVisibility(View.VISIBLE);
        }
    }

    private void showDistinct(){
        if(distinctBtn.getVisibility() == View.VISIBLE) {
            distinctBtn.setVisibility(View.GONE);
            distinctEt.setVisibility(View.GONE);
        }else{
            distinctBtn.setVisibility(View.VISIBLE);
            distinctEt.setVisibility(View.VISIBLE);
        }
    }

    private void showAddress(){
        if(addressBtn.getVisibility() == View.VISIBLE) {
            addressBtn.setVisibility(View.GONE);
            addressEt.setVisibility(View.GONE);
        }else{
            addressBtn.setVisibility(View.VISIBLE);
            addressEt.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sex_layout:{
                showSexDialog();
                break;
            }
            case R.id.birthday_layout:{
                openChangeDataView();
                break;
            }
            case R.id.email_layout:{
                showEmail();
                break;
            }
            case R.id.distinct_layout:{
                showDistinct();
                break;
            }
            case R.id.address_layout:{
                showAddress();
                break;
            }
            case R.id.email_btn:{
                String emailStr = emailEt.getText().toString();
                SharedPreferenceUtil.put(mContext,EMAIL,emailStr);
                emailEt.setVisibility(View.GONE);
                emailBtn.setVisibility(View.GONE);
                emailTv.setText(emailStr);
                break;
            }
            case R.id.distinct_btn:{
                String distinctStr = distinctEt.getText().toString();
                SharedPreferenceUtil.put(mContext,DISTINCT,distinctStr);
                distinctEt.setVisibility(View.GONE);
                distinctBtn.setVisibility(View.GONE);
                distinctTv.setText(distinctStr);
                break;
            }
            case R.id.address_btn:{
                String addressStr = distinctEt.getText().toString();
                SharedPreferenceUtil.put(mContext,ADDRESS,addressStr);
                addressEt.setVisibility(View.GONE);
                addressBtn.setVisibility(View.GONE);
                addressTv.setText(addressStr);
                break;
            }
        }
    }
}
