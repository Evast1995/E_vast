package com.ychen.tourism.widget.adapter;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ychen.tourism.R;
import com.ychen.tourism.helper.Table;

/**
 * Created by evast on 16-3-21.
 */
public class PlanAdapter extends CursorAdapter{

    public PlanAdapter(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.plan_item,null);
        viewHolder.addressTv = (TextView) view.findViewById(R.id.plan_address);
        viewHolder.nameTv  = (TextView) view.findViewById(R.id.plan_name);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String nameStr = cursor.getString(cursor.getColumnIndex(Table.PlanTable.NAME));
        String addresStr = cursor.getString(cursor.getColumnIndex(Table.PlanTable.ADDRESS));
        viewHolder.addressTv.setText(addresStr);
        viewHolder.nameTv.setText(nameStr);
    }

    class ViewHolder{
        TextView nameTv;
        TextView addressTv;
    }
}
