package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    public Weather(String status, Basic basic, Now now, Basic.Update update,
                   List<Forecast> forecastList, List<LifeStyle> lifestyleList, Air air) {
        this.status = status;
        this.basic = basic;
        this.now = now;
        this.update = update;
        this.forecastList = forecastList;
        this.lifestyleList = lifestyleList;
        this.air = air;
    }

    public String status;

    public Basic basic;

    public Now now;

    public Basic.Update update;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

    @SerializedName("lifestyle")
    public List<LifeStyle> lifestyleList;

    @SerializedName("air_now_city")
    public Air air;
}
