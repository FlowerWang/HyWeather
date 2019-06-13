package com.hyweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyweather.android.gson.Forecast;
import com.hyweather.android.gson.LifeStyle;
import com.hyweather.android.gson.Weather;
import com.hyweather.android.util.HttpUtil;
import com.hyweather.android.util.UtilCity;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView tmpText;

    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    public static final String SHARED_ID_NOW = "weather_now";
    public static final String SHARED_ID_FORECAST = "weather_forecast";
    public static final String SHARED_ID_LIFESTYLE = "weather_lifestyle";
    public static final String SHARED_ID_AIR = "weather_air";

    private String mWeatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        //初始化UI组件
        weatherLayout = findViewById(R.id.weather_scroll);
        titleCity = findViewById(R.id.title_city);
        titleUpdateTime = findViewById(R.id.title_update_time);
        tmpText = findViewById(R.id.now_tmp);
        weatherInfoText = findViewById(R.id.weather_info_text);
        forecastLayout = findViewById(R.id.forecast_layout);
        aqiText = findViewById(R.id.aqi_text);
        pm25Text = findViewById(R.id.pm25_text);
        comfortText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.car_wash_text);
        sportText = findViewById(R.id.sport_text);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String now = preferences.getString(SHARED_ID_NOW, null);
        String forecast = preferences.getString(SHARED_ID_FORECAST, null);
        String lifestyle = preferences.getString(SHARED_ID_LIFESTYLE, null);
        String air = preferences.getString(SHARED_ID_AIR, null);

        if (!TextUtils.isEmpty(now) && !TextUtils.isEmpty(forecast) && !TextUtils.isEmpty(lifestyle)
            && !TextUtils.isEmpty(air)) {
            //有缓存数据
            Weather n = UtilCity.handleWeatherResponse(now);
            Weather f = UtilCity.handleWeatherResponse(forecast);
            Weather l = UtilCity.handleWeatherResponse(lifestyle);
            Weather a = UtilCity.handleWeatherResponse(air);
            Weather weather = new Weather(n.status, n.basic, n.now, n.update, f.forecastList, l.lifestyleList, a.air);
            showWeatherInfo(weather);
        } else {
            //无缓存
            final String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

    }


    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather(final String weatherId) {
        //实况天气
        String nowUrl = "https://free-api.heweather.net/s6/weather/now?location=" + weatherId + "&key=c165187a82f9444da8f9631d0cb80a6d";
        //3-10天预报
        String forecastUrl = "https://free-api.heweather.net/s6/weather/forecast?location="+ weatherId +"&key=c165187a82f9444da8f9631d0cb80a6d";
        //生活指数
        String lifestyleUrl = "https://free-api.heweather.net/s6/weather/lifestyle?location=" + weatherId +"&key=c165187a82f9444da8f9631d0cb80a6d";
        //空气质量实况
        String airUrl = "https://free-api.heweather.net/s6/air/now?location="+ weatherId +"&key=c165187a82f9444da8f9631d0cb80a6d";
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = UtilCity.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString(SHARED_ID_NOW, responseText);
                        editor.apply();
                        mWeatherId = weather.basic.getCid();
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show() );
            }
        });

        HttpUtil.sendOkHttpRequest(forecastUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = UtilCity.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString(SHARED_ID_FORECAST, responseText);
                        editor.apply();
                        mWeatherId = weather.basic.getCid();
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        HttpUtil.sendOkHttpRequest(lifestyleUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = UtilCity.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString(SHARED_ID_LIFESTYLE, responseText);
                        editor.apply();
                        mWeatherId = weather.basic.getCid();
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        HttpUtil.sendOkHttpRequest(airUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = UtilCity.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString(SHARED_ID_AIR, responseText);
                        editor.apply();
                        mWeatherId = weather.basic.getCid();
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.getLocation();
        String updateTime = weather.update.getLoc().split(" ")[1];
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);

        if (weather.now != null) {
            String tmp = weather.now.getTmp() + "℃";
            String weatherInfo = weather.now.getCondTxt();
            tmpText.setText(tmp);
            weatherInfoText.setText(weatherInfo);
        }

        forecastLayout.removeAllViews();
        if (weather.forecastList != null && weather.forecastList.size() > 0) {
            for (Forecast forecast : weather.forecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = view.findViewById(R.id.date_text);
                TextView infoText = view.findViewById(R.id.info_text);
                TextView maxText = view.findViewById(R.id.max_text);
                TextView minText = view.findViewById(R.id.min_text);
                dateText.setText(forecast.getDate());
                infoText.setText(forecast.getCondTxtd());
                maxText.setText(forecast.getTmpMax());
                minText.setText(forecast.getTmpMin());
                forecastLayout.addView(view);
            }
        }

        if (weather.air != null) {
            aqiText.setText(weather.air.getAqi());
            pm25Text.setText(weather.air.getPm25());
        }

        if (weather.lifestyleList != null && weather.lifestyleList.size() > 0) {
            for (LifeStyle lifeStyle : weather.lifestyleList) {
                switch (lifeStyle.getType()) {
                    case "comf":
                        comfortText.setText(("舒适度：" + lifeStyle.getTxt()));
                        break;
                    case "cw":
                        carWashText.setText(("洗车指数：" + lifeStyle.getTxt()));
                        break;
                    case "sport":
                        sportText.setText(("运行建议：" + lifeStyle.getTxt()));
                        break;
                    default:
                        break;
                }
            }
        }

        weatherLayout.setVisibility(View.VISIBLE);
    }
}
