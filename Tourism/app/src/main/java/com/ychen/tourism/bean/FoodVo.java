package com.ychen.tourism.bean;

import java.util.Arrays;

/**
 * Created by 72963 on 2015/10/26.
 */
public class FoodVo {
    private String resultcode;//"200",
    private String reason;//"查询成功",
    private String error_code;//0
    private ResultVo[] result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultVo[] getResult() {
        return result;
    }

    public void setResult(ResultVo[] result) {
        this.result = result;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    @Override
    public String toString() {
        return "FoodVo{" +
                "error_code='" + error_code + '\'' +
                ", resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
