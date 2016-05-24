package com.ychen.tourism.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ychen.tourism.R;
import com.ychen.tourism.bean.ResultVo;
import com.ychen.tourism.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by 72963 on 2015/10/26.
 */
public class FoodAdapter extends BaseAdapter{
    private Context mContext;
    private List<ResultVo> resultVos = new ArrayList<>();
    public FoodAdapter(Context context, List<ResultVo> data) {
        this.mContext = context;
        this.resultVos = data;
    }

    @Override
    public int getCount() {
        return resultVos.size();
    }

    @Override
    public Object getItem(int i) {
        return resultVos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.food_list_item,null);
            holder.headImage = (ImageView) convertView.findViewById(R.id.food_item_image);
            holder.addTv = (TextView) convertView.findViewById(R.id.food_item_address);
            holder.nameTv = (TextView) convertView.findViewById(R.id.food_item_title);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.food_item_phone);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ResultVo resultVo =  resultVos.get(position);
        String nameStr = resultVo.getName();
        String phoneStr = resultVo.getPhone();
        String addStr = resultVo.getAddress();
        String imgStr = resultVo.getPhotos();

        holder.phoneTv.setText(phoneStr);
        holder.nameTv.setText(nameStr);
        holder.addTv.setText(addStr);
        ImageLoadUtil.getInstance().setImageLoader(holder.headImage,imgStr);


        return convertView;
    }

    class ViewHolder{
        ImageView headImage;
        TextView nameTv;
        TextView addTv;
        TextView phoneTv;
    }

}
