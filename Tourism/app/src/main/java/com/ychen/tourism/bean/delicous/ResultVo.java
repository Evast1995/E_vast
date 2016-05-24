package com.ychen.tourism.bean.delicous;

import java.util.Arrays;

/**
 * Created by 72963 on 2015/10/26.
 */
public class ResultVo {
    private String totalNum;//"1",
    private String pn;//0,
    private String rn;//30
    private DataVo data[];

    public DataVo[] getData() {
        return data;
    }

    public void setData(DataVo[] data) {
        this.data = data;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "data=" + Arrays.toString(data) +
                ", totalNum='" + totalNum + '\'' +
                ", pn='" + pn + '\'' +
                ", rn='" + rn + '\'' +
                '}';
    }
}
