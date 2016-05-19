package com.evast.itrueface.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.evast.itrueface.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public abstract class BaseActivity extends AppCompatActivity {
    /** 使用模板时子类视图放在mainBody中*/
    private RelativeLayout mainBody;
    /** 是否使用模板*/
    private Boolean isTemplate = true;
    /** 标题栏*/
    private Toolbar toolbar;
    /** 模板中的加载齿轮*/
    private ProgressWheel progressWheel;

    private Fragment mFragmentContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isTemplate) {//如果需要模板的时候才加载该视图
            setContentView(R.layout.core_activity_base);
            /** 初始化模板控件*/
            initWidget();
        }
    }


    /**
     * 设置是否使用模板
     * @param isTemplate 默认为true使用模板，false不使用模板
     */
    public void setIsTemplate(Boolean isTemplate){
        this.isTemplate = isTemplate;
    }
    /**
     * 初化控件
     */
    protected void initWidget(){
        progressWheel = (ProgressWheel) findViewById(R.id.base_wheel_loading);
        mainBody = (RelativeLayout) findViewById(R.id.base_mainbody);
        toolbar= (Toolbar) findViewById(R.id.base_title);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

    }

    /**
     * 获取模板齿轮（默认是隐藏需要时让其显示）
     * @return
     */
    protected ProgressWheel getProgressWheel(){
        return progressWheel;
    }


    /**
     * 重写setContentView让子类调用该方法将子类布局放在mainbody中
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        if(layoutResID == R.layout.core_activity_base){
            super.setContentView(R.layout.core_activity_base);
        }else{
            if(mainBody!=null){//不为null说明初始化了模板控件，使用了模板将子类视图添加到mainBody中
                mainBody.removeAllViews();
                mainBody.addView(this.getLayoutInflater().inflate(layoutResID,null),
                        new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.MATCH_PARENT));
            }else{//如果为null说明不需要使用模板，则直接加载
                super.setContentView(layoutResID);
            }
            /** 调用子类实现的方法，该方法用于子类对Activity的操作*/
            init();
        }
    }

    /** 子类实现该方法，该方法在setContentView中调用*/
    protected abstract void init();


    /**
     * 切换Fragment
     * @param resId 需要将Fragment放入那个容器中的id
     * @param to 需要切换的Fragment对象（v4包下的Fragment）
     */
    protected void switchFragmentContent(int resId,Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mFragmentContent!=null){
            if (mFragmentContent != to) {
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mFragmentContent).add(resId, to); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mFragmentContent).show(to); // 隐藏当前的fragment，显示下一个
                }
            }
        }else{
            transaction.add(resId, to);
        }
        /**
         * Can not perform this action after onSaveInstanceState
         * onSaveInstanceState方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后
         * 再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成 commitAllowingStateLoss()就行了，其效果是一样的。
         */
        transaction.commitAllowingStateLoss();  //推荐使用此方法，更安全，更方便
        mFragmentContent = to;
    }

    /**
     * 显示提示弹窗
     * @param messaage
     */
    public void showToast(String messaage){
        Toast.makeText(this,messaage,Toast.LENGTH_SHORT).show();
    }

}
