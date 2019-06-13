package com.hyweather.android.gson;

/**
 * 生活指数
 * 生活指数和生活指数预报包括：穿衣、洗车、感冒、紫外线、运动、舒适度、旅游、空气污染扩散条件。
 */
public class LifeStyle {

    /**
     * type : comf
     * brf : 较不舒适
     * txt : 白天天气多云，同时会感到有些热，不很舒适。
     */

    private String type;
    private String brf;
    private String txt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
