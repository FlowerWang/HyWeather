package com.hyweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 天气预报
 * 3-10天天气预报数据，天气预报包含的数据：
 * 日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等数据
 */
public class Forecast {

    /**
     * date : 2019-06-14
     * cond : {"txt_d":"多云"}
     * tmp : {"max":"21","min":"14"}
     */

    public String date;
    public CondBean cond;
    public TmpBean tmp;

    public static class CondBean {
        @SerializedName("txt_d")
        public String txtd;

    }

    public static class TmpBean {
        public String max;
        public String min;
    }
}
