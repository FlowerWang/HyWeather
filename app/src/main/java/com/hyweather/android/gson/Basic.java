package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 天气的基础信息
 * 基础信息包括所查询的城市/地区的一些基本信息，例如名称、ID、经纬度等
 */
public class Basic {


    /**
     * cid : CN101011500
     * location : 平谷
     * parent_city : 北京
     * admin_area : 北京
     * cnty : 中国
     * lat : 40.14478302
     * lon : 117.11233521
     * tz : +8.00
     */

    public String cid;
    public String location;
    @SerializedName("parent_city")
    public String parentCity;
    @SerializedName("admin_area")
    public String adminArea;

    public Update update;

    public class Update {

        /**
         * loc : 2019-06-12 15:24
         * utc : 2019-06-12 07:24
         */

        public String loc;
        public String utc;
    }

}
