package com.hyweather.android.util;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    /**
     * @param address 请求地址
     * @param callback 服务器响应回调
     */
    public static void sendOkHttpRequest(@NonNull String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
