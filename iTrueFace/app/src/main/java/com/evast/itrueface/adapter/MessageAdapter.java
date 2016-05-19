package com.evast.itrueface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.bean.message.MessageVo;
import com.evast.itrueface.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by evast on 16-1-15.
 */
public class MessageAdapter extends BaseAdapter{
    private Context context;
    private List<MessageVo> list;
    public MessageAdapter(Context context,List<MessageVo> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.message_item_layout,null);
            viewHolder.headIv = (ImageView) convertView.findViewById(R.id.message_head_image);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.message_name_text);
            viewHolder.contantTv = (TextView) convertView.findViewById(R.id.message_contact_text);
            viewHolder.dateTv = (TextView) convertView.findViewById(R.id.message_time_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageVo messageVo = list.get(position);
        ImageLoadUtil.getInstance().setImageLoader(viewHolder.headIv,messageVo.getImageUrl());
        viewHolder.nameTv.setText(messageVo.getNameStr());
        viewHolder.contantTv.setText(messageVo.getContantStr());
        viewHolder.dateTv.setText(messageVo.getDateStr());
        return convertView;
    }
    class ViewHolder{
        ImageView headIv;
        TextView nameTv;
        TextView contantTv;
        TextView dateTv;
    }
}
