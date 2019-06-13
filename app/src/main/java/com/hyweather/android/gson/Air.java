package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 空气质量实况（城区）
 */
public class Air {

    /**
     * 参数	                描述	                       示例
     * pub_time	    数据发布时间,格式yyyy-MM-dd HH:mm	2017-03-20 12:30
     * aqi	        空气质量指数，AQI和PM25的关系	    74
     * main	        主要污染物	                    pm25
     * qlty	        空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染，查看计算方式	良
     * pm10	        pm10	        78
     * pm25	        pm25	66
     * no2	        二氧化氮	40
     * so2	        二氧化硫	30
     * co	        一氧化碳	33
     * o3	        臭氧	    20
     */

    private String aqi;
    private String qlty;
    private String main;
    private String pm25;
    private String pm10;
    private String no2;
    private String so2;
    private String co;
    private String o3;
    @SerializedName("pub_time")
    private String pubTime;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
