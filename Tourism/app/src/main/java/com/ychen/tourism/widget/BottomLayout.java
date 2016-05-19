package com.ychen.tourism.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ychen.tourism.R;

/**
 * Created by evast on 16-3-14.
 */
public class BottomLayout extends FrameLayout{
    private Context mContext;
    private LinearLayout mLayoutBody;
    /** 当前选中的位置 默认为０*/
    private int mPosition = 0;
    /** 每添加一个navagate　就增长１用于给添加的navagate赋值id*/
    private int mCount = 0;

    public BottomLayout(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottom_layout,this,true);
        mLayoutBody = (LinearLayout) view.findViewById(R.id.body_layout);
    }

    /**
     * 创建底部导航条的每一个item 的容器
     * @return
     */
    private LinearLayout createContainer(){
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    public interface IClickCallBack{
        void clickCallBack();
    }
    /**
     * 对外提供添加navigate的接口
     * @param strId　文本id
     * @param imageId 图片id
     * @param callBack 点击回调事件
     */
    public void addNavigate(int strId,int imageId, final IClickCallBack callBack){
        LinearLayout linearLayout = createContainer();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        final Navigation navigation = new Navigation(mContext);
        navigation.addNagivation(imageId, strId);
        navigation.setLayoutParams(layoutParams);
        navigation.setId(mCount);
        linearLayout.addView(navigation);
        navigation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.clickCallBack();
                if(v.getId() != mPosition){
                    mPosition = v.getId();
                    cacelAllSelected();
                    navigation.setSelected(true);
                }
            }
        });
        /** 默认添加的第一个显示*/
        if(mCount == 0){
            callBack.clickCallBack();
            navigation.setSelected(true);
        }
        mCount++;
        mLayoutBody.addView(linearLayout);
    }

    /**
     * 取消所有选中
     */
    private void cacelAllSelected(){
        for(int i =0;i<mCount;i++){
            mLayoutBody.findViewById(i).setSelected(false);
        }
    }




}
