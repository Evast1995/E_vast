package com.evast.itrueface.bean.message;

/**
 * Created by evast on 16-1-16.
 */
public class FriendVo {
    private String friendImageUrl;
    private String nameStr;

    public String getFriendImageUrl() {
        return friendImageUrl;
    }

    public void setFriendImageUrl(String friendImageUrl) {
        this.friendImageUrl = friendImageUrl;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    @Override
    public String toString() {
        return "FriendVo{" +
                "friendImageUrl='" + friendImageUrl + '\'' +
                ", nameStr='" + nameStr + '\'' +
                '}';
    }
}
