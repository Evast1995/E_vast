package com.ychen.tourism.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ychen.tourism.R;

/**
 * Created by evast on 16-3-14.
 */
public class TitleLayout extends FrameLayout{
    private Context mContext;
    private View mView;
    private TextView titleTv;
    private ImageView leftIv;
    private ImageView rightIv;
    public TitleLayout(Context context) {
        super(context);
        init(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.include_title_layout,this,true);
        titleTv = (TextView) mView.findViewById(R.id.center_title_layout);
        leftIv = (ImageView) mView.findViewById(R.id.back_icon);
        rightIv = (ImageView) mView.findViewById(R.id.right_icon);
    }

    /**
     * 设置标题
     * @param resId
     */
    public void setTitle(int resId){
        titleTv.setText(resId);
    }

    /**
     * 设置左边图标的显示和点击事件
     * @param visibility
     * @param onClickListener
     * @param resId
     */
    public void setLeftVisibility(int visibility,OnClickListener onClickListener,int resId){
        leftIv.setVisibility(visibility);
        if(null != onClickListener && visibility == VISIBLE){
            leftIv.setOnClickListener(onClickListener);
        }
        if(visibility == VISIBLE && resId>0){
            leftIv.setBackgroundResource(resId);
        }
    }

    /**
     * 设置右边图标相应事件
     * @param visibility
     * @param onClickListener
     * @param resId
     */
    public void setRightVisibility(int visibility,OnClickListener onClickListener,int resId){
        rightIv.setVisibility(visibility);
        if(null != onClickListener && visibility == VISIBLE){
            rightIv.setOnClickListener(onClickListener);
        }
        if(visibility == VISIBLE && resId>0){
            rightIv.setBackgroundResource(resId);
        }
    }


}
