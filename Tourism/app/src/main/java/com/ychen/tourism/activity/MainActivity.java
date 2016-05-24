package com.ychen.tourism.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ychen.tourism.R;
import com.ychen.tourism.fragment.AccountFragment;
import com.ychen.tourism.fragment.CommentFragment;
import com.ychen.tourism.fragment.FindFragment;
import com.ychen.tourism.fragment.FoodFragment;
import com.ychen.tourism.fragment.PlanFragment;
import com.ychen.tourism.widget.BottomLayout;
import com.ychen.tourism.widget.TitleLayout;

public class MainActivity extends BaseFragmentActivity{
    private BottomLayout bottomLayout;
    private AccountFragment accountFragment;
    private CommentFragment commentFragment;
    private PlanFragment planFragment;
    private FindFragment findFragment;
    private FoodFragment foodFragment;
    private TitleLayout titleLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 当遇到如切换语言重置时防止fragment的重叠
     * @param fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof AccountFragment){
            accountFragment = (AccountFragment) fragment;
        }else if (fragment instanceof CommentFragment){
            commentFragment = (CommentFragment) fragment;
        }else if (fragment instanceof PlanFragment){
            planFragment = (PlanFragment) fragment;
        }else if(fragment instanceof FindFragment){
            findFragment = (FindFragment) fragment;
        }else if(fragment instanceof FoodFragment){
            foodFragment = (FoodFragment) fragment;
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle(){
        titleLayout.setTitle(R.string.visit_plan);
    }

    private void init(){
        titleLayout = (TitleLayout) findViewById(R.id.title_view);
        initTitle();
        initNavigate();
    }

    /**
     * 初始化底部导航兰
     */
    private void initNavigate(){
        bottomLayout = (BottomLayout) findViewById(R.id.bottom_navigation);
        bottomLayout.addNavigate(R.string.visit_plan, R.drawable.plan_log, new BottomLayout.IClickCallBack() {
            @Override
            public void clickCallBack() {
                if(null == planFragment){planFragment = new PlanFragment();}
                switchFragmentContent(R.id.fragment,planFragment);
                titleLayout.setTitle(R.string.visit_plan);
            }
        });
        bottomLayout.addNavigate(R.string.vist_delicous,R.drawable.food_log,new BottomLayout.IClickCallBack(){
            @Override
            public void clickCallBack() {
                if(null == foodFragment) {foodFragment = new FoodFragment();}
                switchFragmentContent(R.id.fragment,foodFragment);
                titleLayout.setTitle(R.string.vist_delicous);
            }
        });
        bottomLayout.addNavigate(R.string.visit_find,R.drawable.find_bg,new BottomLayout.IClickCallBack() {
            @Override
            public void clickCallBack() {
                if(null == findFragment){findFragment = new FindFragment();}
                switchFragmentContent(R.id.fragment,findFragment);
                titleLayout.setTitle(R.string.visit_find);
            }
        });
        bottomLayout.addNavigate(R.string.comments,R.drawable.comment_log,new BottomLayout.IClickCallBack() {
            @Override
            public void clickCallBack() {
                if(null == commentFragment){commentFragment = new CommentFragment();}
                switchFragmentContent(R.id.fragment,commentFragment);
                titleLayout.setTitle(R.string.comments);
            }
        });
        bottomLayout.addNavigate(R.string.account,R.drawable.account_log,new BottomLayout.IClickCallBack() {
            @Override
            public void clickCallBack() {
                if(null == accountFragment) accountFragment = new AccountFragment();
                switchFragmentContent(R.id.fragment,accountFragment);
                titleLayout.setTitle(R.string.account);
            }
        });

    }
}
