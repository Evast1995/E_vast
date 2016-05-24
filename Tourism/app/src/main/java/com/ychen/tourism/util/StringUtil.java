package com.ychen.tourism.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 72963 on 2015/10/26.
 */
public class StringUtil {
    /**
     * 获取逗号中的数据
     * @param str
     * @return
     */
    public static List<String> getArrayString(String str){
        List<String> list = new ArrayList<>();
        String find = "([^,]+),";
        Pattern p = Pattern.compile(find);
        Matcher matcher = p.matcher(str);
        while(matcher.find()){
            list.add(matcher.group(1));
        }
        return list;
    }
}
