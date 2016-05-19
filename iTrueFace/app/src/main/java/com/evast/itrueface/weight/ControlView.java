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
public class ControlView extends FrameLayout implements View.OnClickListener{
    private Context context;
    private View view;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;

    private int isSelected =1;
    private OnClickListener oneOnClickListener;
    private OnClickListener twoOnCLickListener;
    private OnClickListener threeOnCLickListener;

    public ControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    public ControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public ControlView(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    private void init(AttributeSet attrs){
        view = LayoutInflater.from(context).inflate(R.layout.switch_control_layoout,this,true);
        oneBtn = (Button) view.findViewById(R.id.one_btn);
        twoBtn = (Button) view.findViewById(R.id.two_btn);
        threeBtn = (Button) view.findViewById(R.id.three_btn);
        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        isSelected = 1;
        oneBtn.setSelected(true);
        setAttr(attrs);
    }

    private void setAttr(AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ControlView);
        int oneBtnId = typedArray.getResourceId(R.styleable.ControlView_onebtn_text, R.string.defalut_string);
        int twoBtnId = typedArray.getResourceId(R.styleable.ControlView_twobtn_text,R.string.defalut_string);
        int threeBtnId = typedArray.getResourceId(R.styleable.ControlView_threebtn_text,R.string.defalut_string);
        oneBtn.setText(getResources().getText(oneBtnId));
        twoBtn.setText(getResources().getText(twoBtnId));
        threeBtn.setText(getResources().getText(threeBtnId));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.one_btn) {
            setOneBtnOnClick();
            if(oneOnClickListener!=null){
                oneOnClickListener.onClick(v);
            }
        }else if(i == R.id.two_btn){
            setTwoBtnOnClick();
            if(twoOnCLickListener!=null) {
                twoOnCLickListener.onClick(v);
            }
        }else if (i == R.id.three_btn){
            setThreeBtnOnClick();
            if(threeOnCLickListener!=null) {
                threeOnCLickListener.onClick(v);
            }
        }


    }

    private void setOneBtnOnClick(){
        if(isSelected != 1){
            oneBtn.setSelected(true);
            oneBtn.setTextColor(getResources().getColor(R.color.white));
            twoBtn.setSelected(false);
            twoBtn.setTextColor(getResources().getColor(R.color.blue));
            threeBtn.setSelected(false);
            threeBtn.setTextColor(getResources().getColor(R.color.blue));
        }
        isSelected = 1;
    }
    private void setTwoBtnOnClick(){
        if(isSelected != 2){
            twoBtn.setSelected(true);
            twoBtn.setTextColor(getResources().getColor(R.color.white));
            oneBtn.setSelected(false);
            oneBtn.setTextColor(getResources().getColor(R.color.blue));
            threeBtn.setSelected(false);
            threeBtn.setTextColor(getResources().getColor(R.color.blue));
        }
        isSelected = 2;
    }
    private void setThreeBtnOnClick(){
        if(isSelected != 3){
            threeBtn.setSelected(true);
            threeBtn.setTextColor(getResources().getColor(R.color.white));
            oneBtn.setSelected(false);
            oneBtn.setTextColor(getResources().getColor(R.color.blue));
            twoBtn.setSelected(false);
            twoBtn.setTextColor(getResources().getColor(R.color.blue));
        }
        isSelected = 3;
    }


    public void addOneBtnListener(OnClickListener onClickListener){
        this.oneOnClickListener = onClickListener;
    }

    public void addTwoBtnListener(OnClickListener onClickListener){
        this.twoOnCLickListener = onClickListener;
    }
    public void addThreeBtnListener(OnClickListener onClickListener){
        this.threeOnCLickListener = onClickListener;
    }

}
