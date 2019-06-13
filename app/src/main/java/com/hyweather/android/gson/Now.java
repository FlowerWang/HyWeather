package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 实况天气
 * 实况天气即为当前时间点的天气状况以及温湿风压等气象指数，
 * 具体包含的数据：体感温度、实测温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水量、能见度等。
 */
public class Now {

    /**
     * cloud : 91
     * cond_code : 101
     * cond_txt : 多云
     * fl : 32
     * hum : 41
     * pcpn : 0.0
     * pres : 999
     * tmp : 32
     * vis : 15
     * wind_deg : 174
     * wind_dir : 南风
     * wind_sc : 3
     * wind_spd : 13
     */

    public String cloud;
    @SerializedName("cond_code")
    public String condCode;
    @SerializedName("cond_txt")
    public String condTxt;
    public String fl;
    public String hum;
    public String pcpn;
    public String pres;
    public String tmp;
    public String vis;
    @SerializedName("wind_deg")
    public String windDeg;
    @SerializedName("wind_dir")
    public String windDir;
    @SerializedName("wind_sc")
    public String windSc;
    @SerializedName("wind_spd")
    public String windSpd;

}
