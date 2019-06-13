package com.hyweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyweather.android.gson.Forecast;
import com.hyweather.android.gson.Weather;
import com.hyweather.android.util.HttpUtil;
import com.hyweather.android.util.UtilCity;


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

    private TextView pqltyText;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    private ImageView bingPic;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String SHARED_ID = "weather_cache";
    public static final String BING_ID = "bing_pic";

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
        pqltyText = findViewById(R.id.pqlty_text);
        comfortText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.car_wash_text);
        sportText = findViewById(R.id.sport_text);
        bingPic = findViewById(R.id.bing_pic);
        swipeRefreshLayout = findViewById(R.id.refresh_weather);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String id;

        final String preferencesString = preferences.getString(SHARED_ID, null);
        if (!TextUtils.isEmpty(preferencesString)) {
            //有缓存数据
            final Weather weather = UtilCity.handleWeatherResponse(preferencesString);
            id = weather.basic.cid;
            showWeatherInfo(weather);
        } else {
            //无缓存
            final String weatherId = getIntent().getStringExtra("weather_id");
            id = weatherId;
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        swipeRefreshLayout.setOnRefreshListener(() -> requestWeather(id) );
        final String bing = preferences.getString(BING_ID, null);
        if (!TextUtils.isEmpty(bing)) {
            Glide.with(this).load(bing).into(bingPic);
        } else {
            loadBingPic();
        }
    }


    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=c165187a82f9444da8f9631d0cb80a6d";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = UtilCity.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString(SHARED_ID, responseText);
                        editor.apply();
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                });
            }
        });
    }


    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.location;
        String updateTime = weather.update.loc.split(" ")[1];
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);

        if (weather.now != null) {
            String tmp = weather.now.tmp + "℃";
            String weatherInfo = weather.now.condTxt;
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
                dateText.setText(forecast.date);
                infoText.setText(forecast.cond.txtd);
                maxText.setText(forecast.tmp.max);
                minText.setText(forecast.tmp.min);
                forecastLayout.addView(view);
            }
        }

        if (weather.air.city != null) {
            aqiText.setText(weather.air.city.aqi);
            pm25Text.setText(weather.air.city.pm25);
            pqltyText.setText(weather.air.city.qlty);
        }

        if (weather.suggestion != null) {
            comfortText.setText(("舒适度：" + weather.suggestion.comf.txt));
            carWashText.setText(("洗车指数：" + weather.suggestion.cw.txt));
            sportText.setText(("运行建议：" + weather.suggestion.sport.txt));
        }

        weatherLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bing = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString(BING_ID, bing);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bing).into(bingPic);
                    }
                });
            }
        });

    }
}
