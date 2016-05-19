package com.evast.itrueface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.bean.information.TeacherVo;
import com.evast.itrueface.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by evast on 16-1-16.
 */
public class TeacherAdapter extends BaseAdapter{
    private Context context;
    private List<TeacherVo> list;
    public TeacherAdapter(Context context,List<TeacherVo> list){
        this.context =context;
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_item_layout, null);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.teacher_name);
            viewHolder.phoneTv = (TextView) convertView.findViewById(R.id.teacher_phone);
            viewHolder.introduceTv = (TextView) convertView.findViewById(R.id.teacher_introduce_context);
            viewHolder.authTv = (TextView) convertView.findViewById(R.id.auth_name);
            viewHolder.rewardTv = (TextView) convertView.findViewById(R.id.reward_name);
            viewHolder.addressTv = (TextView) convertView.findViewById(R.id.teacher_address);
            viewHolder.headIv = (ImageView) convertView.findViewById(R.id.head_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TeacherVo teacherVo = list.get(position);
        ImageLoadUtil.getInstance().setImageLoader(viewHolder.headIv, teacherVo.getImageUrl());

        String nameStr = context.getResources().getString(R.string.teacher_name);
        nameStr = String.format(nameStr,teacherVo.getTeacherNameStr());
        viewHolder.nameTv.setText(nameStr);

        String phoneStr = context.getResources().getString(R.string.teacher_phone);
        phoneStr = String.format(phoneStr,teacherVo.getPhoneStr());
        viewHolder.phoneTv.setText(phoneStr);

        viewHolder.introduceTv.setText(teacherVo.getIntroductionStr());


        return convertView;
    }
    class ViewHolder{
        ImageView headIv;
        TextView nameTv;
        TextView phoneTv;
        TextView introduceTv;
        TextView addressTv;
        TextView authTv;
        TextView rewardTv;
    }
}
