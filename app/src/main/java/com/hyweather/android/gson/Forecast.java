package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 天气预报
 * 3-10天天气预报数据，天气预报包含的数据：
 * 日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等数据
 */
public class Forecast {

    /**
     * cond_code_d : 101
     * cond_code_n : 302
     * cond_txt_d : 多云
     * cond_txt_n : 雷阵雨
     * date : 2019-06-12
     * hum : 62
     * mr : 14:09
     * ms : 01:39
     * pcpn : 0.0
     * pop : 25
     * pres : 984
     * sr : 04:41
     * ss : 19:41
     * tmp_max : 33
     * tmp_min : 21
     * uv_index : 5
     * vis : 24
     * wind_deg : 169
     * wind_dir : 东南风
     * wind_sc : 1-2
     * wind_spd : 5
     */

    @SerializedName("cond_code_d")
    private String condCoded;
    @SerializedName("cond_code_n")
    private String condCoden;
    @SerializedName("cond_txt_d")
    private String condTxtd;
    @SerializedName("cond_txt_n")
    private String condTxtn;
    private String date;
    private String hum;
    private String mr;
    private String ms;
    private String pcpn;
    private String pop;
    private String pres;
    private String sr;
    private String ss;
    @SerializedName("tmp_max")
    private String tmpMax;
    @SerializedName("tmp_min")
    private String tmpMin;
    @SerializedName("uv_index")
    private String uvIndex;
    private String vis;
    @SerializedName("wind_deg")
    private String windDeg;
    @SerializedName("wind_dir")
    private String windDir;
    @SerializedName("wind_sc")
    private String windSc;
    @SerializedName("wind_spd")
    private String windSpd;

    public String getCondCoded() {
        return condCoded;
    }

    public void setCondCoded(String condCoded) {
        this.condCoded = condCoded;
    }

    public String getCondCoden() {
        return condCoden;
    }

    public void setCondCoden(String condCoden) {
        this.condCoden = condCoden;
    }

    public String getCondTxtd() {
        return condTxtd;
    }

    public void setCondTxtd(String condTxtd) {
        this.condTxtd = condTxtd;
    }

    public String getCondTxtn() {
        return condTxtn;
    }

    public void setCondTxtn(String condTxtn) {
        this.condTxtn = condTxtn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTmpMax() {
        return tmpMax;
    }

    public void setTmpMax(String tmpMax) {
        this.tmpMax = tmpMax;
    }

    public String getTmpMin() {
        return tmpMin;
    }

    public void setTmpMin(String tmpMin) {
        this.tmpMin = tmpMin;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindSc() {
        return windSc;
    }

    public void setWindSc(String windSc) {
        this.windSc = windSc;
    }

    public String getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(String windSpd) {
        this.windSpd = windSpd;
    }
}
