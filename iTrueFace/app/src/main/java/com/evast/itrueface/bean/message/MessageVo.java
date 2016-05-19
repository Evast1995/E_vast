package com.evast.itrueface.bean.message;

import java.io.Serializable;

/**
 * Created by evast on 16-1-15.
 */
public class MessageVo implements Serializable{
    private String imageUrl;
    private String nameStr;
    private String contantStr;
    private String dateStr;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getContantStr() {
        return contantStr;
    }

    public void setContantStr(String contantStr) {
        this.contantStr = contantStr;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", nameStr='" + nameStr + '\'' +
                ", contantStr='" + contantStr + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}
