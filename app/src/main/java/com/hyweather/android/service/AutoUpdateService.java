package com.hyweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.hyweather.android.WeatherActivity;
import com.hyweather.android.gson.Weather;
import com.hyweather.android.util.HttpUtil;
import com.hyweather.android.util.UtilCity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updatePic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int hour = 2 * 60 * 60 * 1000;
        long atTime = SystemClock.elapsedRealtime() + hour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, atTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 更新天气
     */
    public void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String cache = preferences.getString(WeatherActivity.SHARED_ID, null);
        if (!TextUtils.isEmpty(cache)) {
            final Weather weather = UtilCity.handleWeatherResponse(cache);
            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weather.basic.cid + "&key=c165187a82f9444da8f9631d0cb80a6d";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    final Weather w = UtilCity.handleWeatherResponse(responseText);
                    if (w != null && "ok".equals(w.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString(WeatherActivity.SHARED_ID, responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

    public void updatePic() {
        String url = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString(WeatherActivity.BING_ID, responseText);
                editor.apply();
            }
        });

    }
}
