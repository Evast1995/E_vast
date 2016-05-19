package com.evast.itrueface.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.evast.itrueface.R;
import com.evast.itrueface.core.BaseActivity;
import com.evast.itrueface.util.other.L;
import com.evast.itrueface.weight.TemplateTitle;
import com.evast.itrueface.xmpp.XmlppManager;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;

import java.util.Map;

/**
 * Created by 72963 on 2015/12/18.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private TemplateTitle titleView;
    private EditText phoneEdit;
    private EditText passwordEdit;
    private EditText surepasswordEdit;
    private EditText inputAuthEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setIsTemplate(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void init() {
        initView();
        initTitle();
    }
    private void initView(){
        titleView = (TemplateTitle) findViewById(R.id.register_title);
        phoneEdit = (EditText) findViewById(R.id.register_phone);
        passwordEdit = (EditText) findViewById(R.id.register_password);
        surepasswordEdit = (EditText) findViewById(R.id.register_surepassword);
        inputAuthEdit = (EditText) findViewById(R.id.register_auth);

        findViewById(R.id.register_sendauth_btn).setOnClickListener(this);
        findViewById(R.id.register_but).setOnClickListener(this);
    }
    private void initTitle(){
        titleView.setLeftImage(R.mipmap.backbutton_cl);
        titleView.setTitleName(R.string.register);
        titleView.setLeftImageVisable(View.VISIBLE);
        titleView.setTitleTextColor(R.color.black);

        titleView.setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * 注册
     */
    public void regsiter(final Map<String,String> map){
        new AsyncTask<String, Integer, IQ>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected IQ doInBackground(String... params) {
                return XmlppManager.getInstance().register(map);
            }

            @Override
            protected void onPostExecute(IQ iq) {
                if(iq == null){
                    showToast("服务器连接错误");
                }else if (iq.getType() == IQ.Type.ERROR){
                    L.i(iq.toXML());
                    if (iq.getError().toString().equalsIgnoreCase("conflict(409)")){
                        showToast("账号已存在");
                    }else{
                        showToast("注册失败");
                    }
                }else if (iq.getType() == IQ.Type.RESULT){
                    showToast("注册成功");
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        }.execute();
    }


    /**
     * 发送验证码
     */
    public void requestCode(){

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                Packet packet = XmlppManager.getInstance().getAuthCode();
                XmlppManager.getInstance().getConnection().sendPacket(packet);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_sendauth_btn:{
                break;
            }
            case R.id.register_but:{
                break;
            }
        }
    }
}
