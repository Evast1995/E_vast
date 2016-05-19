package com.ychen.tourism.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.ychen.tourism.helper.Table;
import com.ychen.tourism.util.TimeUtils;

import java.io.Serializable;

/**
 * Created by evast on 16-3-22.
 */
public class PlanBean implements Serializable{
    private String name;
    private String info;
    private String address;
    private int id;
    private long starttime;
    private long endtime;
    private String overHead;

    public PlanBean(){

    }

    public PlanBean(Cursor cursor){
        if(null == cursor){
            return;
        }
        id = cursor.getInt(cursor.getColumnIndex(Table.PlanTable.ID));
        name = cursor.getString(cursor.getColumnIndex(Table.PlanTable.NAME));
        address = cursor.getString(cursor.getColumnIndex(Table.PlanTable.ADDRESS));
        info = cursor.getString(cursor.getColumnIndex(Table.PlanTable.INFO));
        starttime = cursor.getLong(cursor.getColumnIndex(Table.PlanTable.START_TIME));
        endtime = cursor.getLong(cursor.getColumnIndex(Table.PlanTable.END_TIME));
        overHead = cursor.getString(cursor.getColumnIndex(Table.PlanTable.OVERHEAD));
    }

    public int getId() {
        return id;
    }

    public String getOverHead() {
        return overHead;
    }

    public void setOverHead(String overHead) {
        this.overHead = overHead;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ContentValues toContentValue(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.PlanTable.NAME,name);
        contentValues.put(Table.PlanTable.INFO,info);
        contentValues.put(Table.PlanTable.ADDRESS,address);
        contentValues.put(Table.PlanTable.START_TIME,starttime);
        contentValues.put(Table.PlanTable.END_TIME,endtime);
        contentValues.put(Table.PlanTable.OVERHEAD,overHead);
        return contentValues;
    }

    @Override
    public String toString() {
        return "name : " + name + '\n' +
                "info : " + info + '\n' +
                "address : " + address + '\n' +
                "starttime : " + TimeUtils.getTimeStrByStamp(starttime) +'\n' +
                "endtime : " + TimeUtils.getTimeStrByStamp(endtime) +'\n' +
                "overHead : " + overHead + '\n';
    }
}
