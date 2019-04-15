/**
 *
 */
package com.wonders.health.tumor.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String[] parsePatterns = {"yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
            "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到下个月日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getNextMonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, 1);
        return DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null) {
            return "";
        }
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算传入毫秒数，*日*小时*分钟*秒
     *
     * @param mss
     * @return
     */
    public static String formatDuring(Long mss) {
        if (mss == null) {
            return "";
        }
        if (mss == 0) {
            return "0";
        }
        StringBuffer buffer = new StringBuffer(20);
        long days = mss / (1000 * 60 * 60 * 24);
        if (days > 0) {
            buffer.append(days + "天");
        }
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (hours > 0) {
            buffer.append(hours + "时");
        }
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        if (minutes > 0) {
            buffer.append(minutes + "分");
        }
        long seconds = (mss % (1000 * 60)) / 1000;
        if (seconds > 0) {
            buffer.append(seconds + "秒");
        }
        long m = mss % 1000;
        if (m > 0) {
            buffer.append(m + "毫秒");
        }
        return buffer.toString();
    }

    public static Integer getAge(Date birthdate) {
        if (birthdate != null) {
            return (int) (pastDays(birthdate) / 365);
        }
        return null;
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
                * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
                + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        smdate = parseDate(formatDate(smdate, "yyyy-MM-dd"));
        bdate = parseDate(formatDate(bdate, "yyyy-MM-dd"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 比较时间大小
     *
     * @param sdate
     * @param edate
     * @return
     */
    public static String compareDays(Date sdate, Date edate) {

        long s = sdate.getTime() - new Date().getTime();
        long e = new Date().getTime() - edate.getTime();
        if (s > 0) {
            return "未开始";
        }
        if (e > 0) {
            return "已过期";
        }
        if (s <= 0 && e <= 0) {
            return "进行中";
        }
        return null;
    }

    /**
     * 比较两个日期的大小
     *
     * @param d1
     * @param d2
     * @return result>0 :d1晚于d2,result<0 :d1早于d2
     */
    public static int compare(Date d1, Date d2) {
        String str1 = formatDate(d1, "yyyy-MM-dd");
        String str2 = formatDate(d2, "yyyy-MM-dd");

        int result = str1.compareTo(str2);

        return result;
    }

    /**
     * 获取当前年度的第一天
     *
     * @param pattern
     * @return
     */
    public static String getCurrentYearFirstDate(String pattern) {
        return getYearFirstDate(Integer.valueOf(getYear()), pattern);
    }

    /**
     * 获取当前年度的最后一天
     *
     * @param pattern
     * @return
     */
    public static String getCurrentYearLastDate(String pattern) {
        return getYearLastDate(Integer.valueOf(getYear()), pattern);
    }

    /**
     * 获取某年度的第一天
     *
     * @param pattern
     * @return
     */
    public static String getYearFirstDate(int year, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return DateFormatUtils.format(currYearFirst, pattern);
    }

    /**
     * 获取某年度的最后一天
     *
     * @param pattern
     * @return
     */
    public static String getYearLastDate(int year, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return DateFormatUtils.format(currYearLast, pattern);
    }

    /**
     * 查询当前日期前(后)x天的日期
     *
     * @param date 当前日期
     * @param day  天数（如果day数为负数,说明是此日期前的天数）
     * @return yyyy-MM-dd
     */
    public static String beforNumberDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 查询上个月（上上个月。。。)的第一天和最后一天
     *
     * @param num
     * @return
     */
    public static String findLastMonth(int num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

        Calendar cal = Calendar.getInstance();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.MONTH, num);   //-1：表示上个月  -2：表示上上个月
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first_prevM = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first_prevM);
        day_first_prevM = str.toString();
        return day_first_prevM;
    }

    /**
     * 获取本月的第一天（或者当前日期）
     *
     * @return
     */
    public static String currDate(String current) {
        String currdate = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if ("1".equals(current)) {    //current=1:表示获取本月的第一天，否则获取当前日期
            cal.set(Calendar.DAY_OF_MONTH, 1);
        }
        currdate = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
        return currdate;
    }

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 指定日期加上指定天数获得新的日期
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        // System.out.println(formatDate(parseDate("2010/3/6")));
        // System.out.println(getDate("yyyy年MM月dd日 E"));
        // long time = new Date().getTime()-parseDate("2012-11-19").getTime();
        // System.out.println(time/(24*60*60*1000));
        //System.out.println(formatDate("20150712"));
        //System.out.println(getCurrentYearLastDate("yyyy-MM-dd"));
    }
}
