package com.hyweather.android.gson;

/**
 * 生活指数
 * 生活指数和生活指数预报包括：穿衣、洗车、感冒、紫外线、运动、舒适度、旅游、空气污染扩散条件。
 */
public class LifeStyle {
    /**
     * comf : {"type":"comf","brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
     * sport : {"type":"sport","brf":"较适宜","txt":"天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。"}
     * cw : {"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
     */

    public ComfBean comf;
    public SportBean sport;
    public CwBean cw;

    public static class ComfBean {
        /**
         * type : comf
         * brf : 舒适
         * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
         */

        public String type;
        public String brf;
        public String txt;
    }

    public static class SportBean {
        /**
         * type : sport
         * brf : 较适宜
         * txt : 天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。
         */

        public String type;
        public String brf;
        public String txt;
    }

    public static class CwBean {
        /**
         * type : cw
         * brf : 较适宜
         * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
         */

        public String type;
        public String brf;
        public String txt;
    }

}
