package com.evast.itrueface.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.evast.itrueface.R;


/**
 * Created by evast on 16-1-9.
 */
public class TitleView extends FrameLayout implements View.OnClickListener{
    private Context context;
    private View view;
    private Button leftBtn;
    private Button rightBtn;
    /** 默认是true即是左边选中，右边不选中 false右边选中左边不选中*/
    private Boolean leftIsSelected =true;
    private OnClickListener leftOnClickListener;
    private OnClickListener rightOnCLickListener;

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public TitleView(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    private void init(AttributeSet attrs){
        view = LayoutInflater.from(context).inflate(R.layout.title_layout_centerbut,this,true);
        leftBtn = (Button) view.findViewById(R.id.left_btn);
        rightBtn = (Button) view.findViewById(R.id.right_btn);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        leftIsSelected = true;
        leftBtn.setSelected(true);
        setAttr(attrs);
    }

    private void setAttr(AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        int leftBtnId = typedArray.getResourceId(R.styleable.TitleView_left_text, R.string.defalut_string);
        int rightBtnId = typedArray.getResourceId(R.styleable.TitleView_right_text,R.string.defalut_string);
        leftBtn.setText(leftBtnId);
        rightBtn.setText(rightBtnId);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.left_btn) {
            setLeftOnClick(v);

        }else if(i == R.id.right_btn){
            setRightOnClick(v);

        }

    }

    private void setLeftOnClick(View v){
        if(!leftIsSelected){
            rightBtn.setSelected(false);
            rightBtn.setTextColor(getResources().getColor(R.color.blue));
            leftBtn.setSelected(true);
            leftBtn.setTextColor(getResources().getColor(R.color.white));
            if(leftOnClickListener!=null){
                leftOnClickListener.onClick(v);
            }
        }
        leftIsSelected = !leftIsSelected;

    }
    private void setRightOnClick(View v){
        if(leftIsSelected){
            leftBtn.setSelected(false);
            leftBtn.setTextColor(getResources().getColor(R.color.blue));
            rightBtn.setSelected(true);
            rightBtn.setTextColor(getResources().getColor(R.color.white));
            if(rightOnCLickListener!=null) {
                rightOnCLickListener.onClick(v);
            }
        }
        leftIsSelected = !leftIsSelected;

    }


    public void addLeftBtnListener(OnClickListener onClickListener){
        this.leftOnClickListener = onClickListener;
    }

    public void addRightBtnListener(OnClickListener onClickListener){
        this.rightOnCLickListener = onClickListener;
    }

}
