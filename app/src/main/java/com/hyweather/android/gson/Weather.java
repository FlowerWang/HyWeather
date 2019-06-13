package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    public String status;

    public Basic basic;

    public Now now;

    public Basic.Update update;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

    @SerializedName("suggestion")
    public LifeStyle suggestion;

    @SerializedName("aqi")
    public Air air;
}
