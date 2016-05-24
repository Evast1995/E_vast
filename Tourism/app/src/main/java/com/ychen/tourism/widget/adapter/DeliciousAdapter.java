package com.ychen.tourism.widget.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ychen.tourism.R;
import com.ychen.tourism.bean.delicous.StepsVo;
import com.ychen.tourism.util.ImageLoadUtil;


/**
 * Created by 72963 on 2015/10/26.
 */
public class DeliciousAdapter extends BaseAdapter {
    private StepsVo[] datas;
    private Context context;
    public DeliciousAdapter(Context context, StepsVo[] datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.delicious_list_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.delicous_item_image);
            holder.textView = (TextView) convertView.findViewById(R.id.delicous_item_text);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        StepsVo stepsVo = datas[position];
        ImageLoadUtil.getInstance().setImageLoader(holder.imageView,stepsVo.getImg());
        holder.textView.setText(stepsVo.getStep());
        return convertView;
    }
}
