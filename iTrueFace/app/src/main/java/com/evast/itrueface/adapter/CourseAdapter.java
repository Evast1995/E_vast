package com.evast.itrueface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.bean.course.CousesVo;
import com.evast.itrueface.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by evast on 16-1-16.
 */
public class CourseAdapter extends BaseAdapter{
    private Context context;
    private List<CousesVo> list;
    public CourseAdapter(Context context,List<CousesVo> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.course_item_layout, null);
            viewHolder.headIv = (ImageView) convertView.findViewById(R.id.head_image);
            viewHolder.courseNameTv = (TextView) convertView.findViewById(R.id.course_name);
            viewHolder.teacherNameTv = (TextView) convertView.findViewById(R.id.course_teachername);
            viewHolder.priceTv = (TextView) convertView.findViewById(R.id.course_price);
            viewHolder.ageTv = (TextView) convertView.findViewById(R.id.course_age);
            viewHolder.accoutTv = (TextView) convertView.findViewById(R.id.course_acount);
            viewHolder.collected = (ImageView) convertView.findViewById(R.id.course_collection_icon);
            viewHolder.authTv = (TextView) convertView.findViewById(R.id.auth_name);
            viewHolder.rewardTv = (TextView) convertView.findViewById(R.id.reward_name);
            viewHolder.countTv = (TextView) convertView.findViewById(R.id.course_acount);
            viewHolder.numTv = (TextView) convertView.findViewById(R.id.humans_num);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CousesVo cousesVo= list.get(position);
        ImageLoadUtil.getInstance().setImageLoader(viewHolder.headIv,cousesVo.getHeadUrl());
        String courseNameStr = context.getResources().getString(R.string.course_name_text);
        courseNameStr = String.format(courseNameStr, cousesVo.getCourseNameStr());

        String teacherNameStr = context.getResources().getString(R.string.teacher_name);
        teacherNameStr = String.format(teacherNameStr,cousesVo.getTeacherNameStr());

        String priceStr = context.getResources().getString(R.string.course_price);
        priceStr = String.format(priceStr,cousesVo.getPriceStr());

        String ageStr = context.getResources().getString(R.string.course_age);
        ageStr = String.format(ageStr,cousesVo.getAgeStr());

        viewHolder.teacherNameTv.setText(teacherNameStr);
        viewHolder.courseNameTv.setText(courseNameStr);
        viewHolder.priceTv.setText(priceStr);
        viewHolder.ageTv.setText(ageStr);

        viewHolder.countTv.setText(""+cousesVo.getFraction());
        viewHolder.accoutTv.setText(""+cousesVo.getAccout());

        viewHolder.numTv.setText(""+cousesVo.getCount());
        return convertView;
    }
    class ViewHolder{
        ImageView headIv;
        TextView teacherNameTv;
        TextView courseNameTv;
        TextView priceTv;
        TextView ageTv;
        TextView authTv;
        TextView rewardTv;
        TextView countTv;
        TextView numTv;
        TextView accoutTv;
        ImageView collected;
    }
}
