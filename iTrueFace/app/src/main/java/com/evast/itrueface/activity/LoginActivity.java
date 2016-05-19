package com.evast.itrueface.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.evast.itrueface.R;
import com.evast.itrueface.core.BaseActivity;
import com.evast.itrueface.weight.TemplateTitle;
import com.evast.itrueface.xmpp.XmlppManager;

import org.jivesoftware.smack.XMPPException;


/**
 * Created by 72963 on 2015/12/18.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private TemplateTitle titleView;
    private EditText phoneEdit;
    private EditText passwordEdit;
    private Boolean isLogin = false;
    private int REQUESTCODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setIsTemplate(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void init() {
        initTitleView();
        initView();
    }
    private void initView(){
        findViewById(R.id.login_loginbtn).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);
        findViewById(R.id.weibo_btn).setOnClickListener(this);
        findViewById(R.id.weixin_btn).setOnClickListener(this);
        findViewById(R.id.qq_btn).setOnClickListener(this);
        phoneEdit = (EditText) findViewById(R.id.login_phone);
        passwordEdit = (EditText) findViewById(R.id.login_password);
    }

    private void initTitleView(){
        titleView = (TemplateTitle) findViewById(R.id.login_title);
        titleView.setLeftImage(R.mipmap.backbutton_cl);
        titleView.setTitleName(R.string.login_btn);
        titleView.setLeftImageVisable(View.VISIBLE);
        titleView.setTitleTextColor(R.color.black);
        titleView.setRightTextColor(R.color.blue);
        titleView.setrightTextText(R.string.register);

        titleView.setrightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, REQUESTCODE);

            }
        });
        titleView.setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void login(final String username, final String password){
        new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    isLogin = XmlppManager.getInstance().login(username,password);
                } catch (XMPPException e) {
                    e.printStackTrace();
                    isLogin = false;
                }
                return isLogin;
            }
            @Override
            protected void onPostExecute(Boolean isLogined) {
                super.onPostExecute(isLogined);
                isLogin = isLogined;
                if(isLogin){
                    showToast(getResources().getString(R.string.login_successful));
                    if(isLogin == true){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    showToast(getResources().getString(R.string.login_failed));
                }

            }
        }.execute();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_loginbtn:{
                String phoneStr = phoneEdit.getText().toString();
                String passwordStr = passwordEdit.getText().toString();
                if(!TextUtils.isEmpty(phoneStr)&&!TextUtils.isEmpty(passwordStr)){
                    login(phoneStr,passwordStr);

                }else{
                    showToast(getResources().getString(R.string.noinformation));
                }
                break;
            }
            case R.id.weibo_btn:{
                break;
            }
            case R.id.weixin_btn:{
                break;
            }
            case R.id.qq_btn:{
                break;
            }
            case R.id.forget_password:{
                break;
            }
        }
    }
}
