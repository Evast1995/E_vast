package com.evast.itrueface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.bean.message.FriendVo;
import com.evast.itrueface.util.ImageLoadUtil;

import java.util.List;


/**
 * Created by evast on 16-1-16.
 */
public class FriendAdapter extends BaseAdapter{
    private Context context;
    private List<FriendVo> list;
    public FriendAdapter(Context context, List<FriendVo> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_item_layout,null);
            viewHolder.headIv = (ImageView) convertView.findViewById(R.id.friend_head_image);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.friend_name_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FriendVo friendVo = list.get(position);
        ImageLoadUtil.getInstance().setImageLoader(viewHolder.headIv, friendVo.getFriendImageUrl());
        viewHolder.nameTv.setText(friendVo.getNameStr());
        return convertView;
    }
    class ViewHolder{
        ImageView headIv;
        TextView nameTv;
    }
}
