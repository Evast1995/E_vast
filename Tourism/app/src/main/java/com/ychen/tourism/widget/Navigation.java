package com.ychen.tourism.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ychen.tourism.R;


/**
 * Created by 72963 on 2015/12/6.
 */
public class Navigation extends FrameLayout{
    private Context context;
    private ImageView imageView;
    private TextView textView;
    public Navigation(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Navigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public Navigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    private void init(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.navigation, this);
        imageView = (ImageView) findViewById(R.id.navigation_image);
        textView = (TextView) findViewById(R.id.navigation_text);
    }

    public void addNagivation(int imageId,int strId){
        imageView.setImageResource(imageId);
        textView.setText(strId);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        imageView.setSelected(selected);
        textView.setSelected(selected);
    }
}
