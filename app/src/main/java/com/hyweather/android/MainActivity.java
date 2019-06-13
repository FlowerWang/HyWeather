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

        if (!TextUtils.isEmpty(preferences.getString(WeatherActivity.SHARED_ID, null)) ) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
