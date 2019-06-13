package com.hyweather.android.gson;

/**
 * 空气质量实况（城区）
 */
public class Air {

    public AirCity city;

    public class AirCity{

        /**
         * aqi : 54
         * pm25 : 29
         * qlty : 良
         */

        public String aqi;
        public String pm25;
        public String qlty;
    }
}
