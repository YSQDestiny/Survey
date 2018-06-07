package com.cykj.survey.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String yyMMdd = "yy-MM-dd";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String HHmmss = "HH:mm:ss";
    public static final String HHmm = "HH:mm";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyMMddHHmmss = "yy-MM-dd HH:mm:ss";

    public static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

    /**
     *
     * @param s
     * @param style
     * @return
     */
    public static Date parseToDate(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 8)
            return null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * @param s
     * @param style
     * @return
     */
    public static java.sql.Date parseToSQLDate(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        java.sql.Date date = null;
        if (s == null || s.length() < 8)
            return null;
        Date dd = parseToDate(s, style);
        date = new java.sql.Date(dd.getTime());
        return date;
    }

    /**
     *
     * @param dat
     * @param style
     * @return
     */
    public static java.sql.Date parseToSQLDate(Date dat, String style) {

        java.sql.Date date = null;
        if (dat == null || dat.toString().length() < 8)
            return null;
        date = new java.sql.Date(dat.getTime());
        return date;
    }

    /**
     *
     * @param s
     * @param style
     * @return
     */
    public static String parseToString(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        String str = null;
        if (s == null || s.length() < 8)
            return null;
        try {
            date = simpleDateFormat.parse(s);
            str = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     *
     * @param date
     * @param style
     * @return
     */
    public static String parseToString(Date date, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        String str = null;
        if (date == null)
            return null;
        str = simpleDateFormat.format(date);
        return str;
    }

    /**
     *
     * @param date
     * @param style
     * @return
     */
    public static String parseSQLDateToString(java.sql.Date date, String style) {
        if (date == null)
            return null;
        Date d = new Date(date.getTime());
        return parseToString(d, style);
    }

    /**
     * Getting java.sql.Date on this time.
     *
     * @return
     */
    public static java.sql.Date getJavaSqlDate() {
        java.sql.Date date = null;
        Date dd = new Date();
        date = new java.sql.Date(dd.getTime());
        return date;
    }

    /**
     *
     * @param timestamp
     * @param style
     * @return
     */
    public static String parseTimestampToString(java.sql.Timestamp timestamp,
                                                String style) {
        if (timestamp == null)
            return null;
        return parseToString(timestamp.toString(), yyyyMMddHHmmss);
    }

    /**
     *
     * @return
     */
    public static String getTimestampDate() {
        Timestamp ts = new Timestamp(new Date().getTime());
        return parseToString(ts.toString(), yyyyMMddHHmmss);
    }

    public static Timestamp getLocalTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获得当天0点时间
     * @return
     */
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得当天24点时间
     * @return
     */
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //
    /**
     * 获得本周一0点时间
     * @return
     */
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     *  获得本周日24点时间
     * @return
     */
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new Date(cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) ;
    }


    /**
     *获得本月第一天0点时间
     * @return
     */
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     *  获得本月最后一天24点时间
     * @return
     */
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

}
