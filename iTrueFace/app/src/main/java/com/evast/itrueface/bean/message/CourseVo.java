package com.evast.itrueface.bean.message;

/**
 * Created by evast on 16-1-16.
 */
public class CourseVo {
    private String headImageUrl;
    private String nameStr;

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    @Override
    public String toString() {
        return "CourseVo{" +
                "headImageUrl='" + headImageUrl + '\'' +
                ", nameStr='" + nameStr + '\'' +
                '}';
    }
}
