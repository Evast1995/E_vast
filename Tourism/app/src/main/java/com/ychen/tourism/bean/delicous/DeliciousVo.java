package com.ychen.tourism.bean.delicous;

/**
 * Created by 72963 on 2015/10/26.
 */
public class DeliciousVo {
    private int resultcode;//"200",
    private String reason;//"Success",
    private String error_code;//0
    private ResultVo result;

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

    public ResultVo getResult() {
        return result;
    }

    public void setResult(ResultVo result) {
        this.result = result;
    }


    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    @Override
    public String toString() {
        return "DeliciousVo{" +
                "error_code='" + error_code + '\'' +
                ", resultcode=" + resultcode +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
