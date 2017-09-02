package org.lord.scaffold.util

import java.text.ParsePosition
import java.text.SimpleDateFormat

/**
 *
 * Created by Yuan Chaochao on 2017/9/2
 */
class IntervalUtil {

    /**
     * 计算时间间隔：多久之前（做了什么事）
     * @param createtime 起初的创建时间，格式：2012-8-21 17:53:20
     * @return
     */
    static String getInterval(String createtime) {
        def interval

        def sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        def pos = new ParsePosition(0)
        def d1 = (Date) sd.parse(createtime, pos)

        //用现在距离1970年的时间间隔new Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔  
        def time = new Date().getTime() - d1.getTime()// 得出的时间间隔是毫秒

        if (time / 1000 < 10 && time / 1000 >= 0) {
            //如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒  
            interval = "刚刚"
        } else if (time / 3600000 < 24 && time / 3600000 >= 0) {
            //如果时间间隔小于24小时则显示多少小时前  
            def h = (int) (time / 3600000)//得出的时间间隔的单位是小时
            interval = h + "小时前"
        } else if (time / 60000 < 60 && time / 60000 > 0) {
            //如果时间间隔小于60分钟则显示多少分钟前  
            def m = (int) ((time % 3600000) / 60000)//得出的时间间隔的单位是分钟
            interval = m + "分钟前"
        } else if (time / 1000 < 60 && time / 1000 > 0) {
            //如果时间间隔小于60秒则显示多少秒前  
            def se = (int) ((time % 60000) / 1000)
            interval = se + "秒前"
        } else {
            //大于24小时，则显示正常的时间，但是不显示秒  
            def sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm")

            def pos2 = new ParsePosition(0)
            def d2 = (Date) sdf.parse(createtime, pos2)

            interval = sdf.format(d2)
        }

        interval
    }

    /**
     * 计算时间间隔：结束时间和开始时间之间的相差
     * @param startTime 开始时间：距离1970年的时间间隔，单位ms
     * @param endTime 结束时间：距离1970年的时间间隔，单位ms
     * @return
     */
    static String getInterval(long startTime, long endTime) {
        getInterval(endTime - startTime)
    }

    /**
     * 计算时间间隔
     * @param duration 时间差，单位ms
     * @return
     */
    static String getInterval(long duration) {
        def result = ''

        def day = (int) (duration / (1000 * 60 * 60 * 24))
        if (day > 0) {
            result = day + '天'
        }
        def hour = (int) (duration / (1000 * 60 * 60))
        if (hour > 0) {
            result += hour + '小时'
        }
        def minute = (int) ((duration % 3600000) / 60000)
        if (minute > 0) {
            result += minute + '分钟'
        }
        def second = (int) ((duration % 60000) / 1000)
        if (second > 0) {
            result += second + '秒'
        }

        result
    }

    static String ms2Date(String timeInMillis) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zZ")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        sdf.setTimeZone(TimeZone.getDefault())
        sdf.format(new Date(Long.valueOf(timeInMillis)))
    }

    static String ms2Date(long timeInMillis) {
        ms2Date(String.valueOf(timeInMillis))
    }

}
