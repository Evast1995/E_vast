package com.ychen.tourism.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by evast on 16-3-27.
 */
public class TimeUtils {
    /**
     * 拼接出日期
     * 通过年月日计算周几 这里以20世纪为准
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String spellDate(int year,int month,int day){
        String myWeek = null;
        int c = 20;//表示多少世纪
        int m = month;
        if(m == 1){
            m = 13;
        }else if(m ==2){
            m = 14;
        }
        int week = (year+(year/4)+(c/4)-2*c+(26*(m+1)/10)+day-1)%7;
        switch(week) {
            case 0:
                myWeek="日";
                break;
            case 1:
                myWeek="一";
                break;
            case 2:
                myWeek="二";
                break;
            case 3:
                myWeek="三";
                break;
            case 4:
                myWeek="四";
                break;
            case 5:
                myWeek="五";
                break;
            case 6:
                myWeek="六";
                break;
            default:
                break;
        }

        return year + "年" +String.format("%02d",(month+1)) + "月" + String.format("%02d",day)+"日"+"星期"+myWeek;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return mYear + "年" +String.format("%02d",Integer.valueOf(mMonth)) + "月" +
                String.format("%02d",Integer.valueOf(mDay))+"日"+"星期"+mWay;
    }


    /**
     * 获取时间戳
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return
     */
    public static long getTime(int year,int month,int day,int hour,int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        long timeCurrent = calendar.getTimeInMillis();
        return timeCurrent;
    }

    /**
     * 通过时间戳获取时间字符串
     * @param stamp
     * @return
     */
    public static String getTimeStrByStamp(long stamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(stamp);
        return time;
    }
}
