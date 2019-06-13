package com.hyweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化LitePal
        LitePal.initialize(this);
        setContentView(R.layout.activity_main);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String now = preferences.getString(WeatherActivity.SHARED_ID_NOW, null);
        String forecast = preferences.getString(WeatherActivity.SHARED_ID_FORECAST, null);
        String lifestyle = preferences.getString(WeatherActivity.SHARED_ID_LIFESTYLE, null);
        String air = preferences.getString(WeatherActivity.SHARED_ID_AIR, null);
        if (!TextUtils.isEmpty(now) && !TextUtils.isEmpty(forecast) && !TextUtils.isEmpty(lifestyle) && !TextUtils.isEmpty(air)) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
