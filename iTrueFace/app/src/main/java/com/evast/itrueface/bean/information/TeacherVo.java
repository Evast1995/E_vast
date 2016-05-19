package com.evast.itrueface.bean.information;

/**
 * Created by evast on 16-1-16.
 */
public class TeacherVo {
    private String imageUrl;
    private String teacherNameStr;
    private String phoneStr;
    private String introductionStr;
    private Boolean isAuthentication;
    private int kindReward;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getKindReward() {
        return kindReward;
    }

    public void setKindReward(int kindReward) {
        this.kindReward = kindReward;
    }

    public String getIntroductionStr() {
        return introductionStr;
    }

    public void setIntroductionStr(String introductionStr) {
        this.introductionStr = introductionStr;
    }

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public String getTeacherNameStr() {
        return teacherNameStr;
    }

    public void setTeacherNameStr(String teacherNameStr) {
        this.teacherNameStr = teacherNameStr;
    }

    public String getPhoneStr() {
        return phoneStr;
    }

    public void setPhoneStr(String phoneStr) {
        this.phoneStr = phoneStr;
    }

    @Override
    public String toString() {
        return "TeacherVo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", teacherNameStr='" + teacherNameStr + '\'' +
                ", phoneStr='" + phoneStr + '\'' +
                ", introductionStr='" + introductionStr + '\'' +
                ", isAuthentication=" + isAuthentication +
                ", kindReward=" + kindReward +
                '}';
    }
}
