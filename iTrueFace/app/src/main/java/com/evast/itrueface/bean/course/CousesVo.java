package com.evast.itrueface.bean.course;

/**
 * Created by evast on 16-1-17.
 */
public class CousesVo {
    private String headUrl;
    private String courseNameStr;
    private String teacherNameStr;
    private String priceStr;
    private String ageStr;
    private Boolean isAuth;
    private int type;
    private Boolean isUnsubscribe;
    private float fraction;
    private int count;
    private Boolean isPaid;
    private Boolean isCollected;
    private int accout;

    public int getAccout() {
        return accout;
    }

    public void setAccout(int accout) {
        this.accout = accout;
    }

    public Boolean getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(Boolean isCollected) {
        this.isCollected = isCollected;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getCourseNameStr() {
        return courseNameStr;
    }

    public void setCourseNameStr(String courseNameStr) {
        this.courseNameStr = courseNameStr;
    }

    public String getTeacherNameStr() {
        return teacherNameStr;
    }

    public void setTeacherNameStr(String teacherNameStr) {
        this.teacherNameStr = teacherNameStr;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getAgeStr() {
        return ageStr;
    }

    public void setAgeStr(String ageStr) {
        this.ageStr = ageStr;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getIsUnsubscribe() {
        return isUnsubscribe;
    }

    public void setIsUnsubscribe(Boolean isUnsubscribe) {
        this.isUnsubscribe = isUnsubscribe;
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "CousesVo{" +
                "headUrl='" + headUrl + '\'' +
                ", courseNameStr='" + courseNameStr + '\'' +
                ", teacherNameStr='" + teacherNameStr + '\'' +
                ", priceStr='" + priceStr + '\'' +
                ", ageStr='" + ageStr + '\'' +
                ", isAuth=" + isAuth +
                ", type=" + type +
                ", isUnsubscribe=" + isUnsubscribe +
                ", fraction=" + fraction +
                ", count=" + count +
                ", isPaid=" + isPaid +
                ", isCollected=" + isCollected +
                ", accout=" + accout +
                '}';
    }
}
