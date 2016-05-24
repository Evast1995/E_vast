package com.ychen.tourism.bean.delicous;

/**
 * Created by 72963 on 2015/10/26.
 */
public class StepsVo {
    private String img;//"http:\/\/img.juhe.cn\/cookbook\/s\/417\/41700_d450061be39603ab.jpg",
    private String step;//"1. 准备好小肥羊火锅底料包。"

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "StepsVo{" +
                "img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}
