package com.tyilack.assist.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 小小黑
 */
public class DateTimeUtil {
    private static final String DATETIME_SIMPLE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_SIMPLE_PATTERN = "yyyy-MM-dd";

    public static String getDateNow() {
        LocalDate todayDate = LocalDate.now();
        return todayDate.toString();
    }

    public static long getFirstDateTimeOfMonth(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millis));
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }


    /**
     * 获取当前时间
     * @return
     */
    public static long currentTimeMillis() {
        Clock clock = Clock.systemUTC();
        return clock.millis();
    }

    /**
     * 格式化当前时间
     * @return
     */
    public static String currentTimeStr() {
        return currentTimeStr(DATETIME_SIMPLE_PATTERN);
    }

    public static String currentDateStr() {
        return currentTimeStr(DATE_SIMPLE_PATTERN);
    }

    /**
     * 格式化当前时间，中文显示
     * @return
     */
    public static String currentDateCnStr() {
        return currentTimeStr("yyyy年MM月dd日");
    }

    /**
     * 根据指定时间戳格式化时间
     * @param millis 时间戳
     * @return
     */
    public static String parseDateTimes(long millis) {
        return parseDateTimes(millis, DATETIME_SIMPLE_PATTERN);
    }

    /**
     * 根据指定时间和格式化正则格式化时间
     * @param millis
     * @param pattern
     * @return
     */
    public static String parseDateTimes(long millis, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault()));
    }

    /**
     * 格式化当前时间
     * @param pattern 正则格式
     * @return
     */
    public static String currentTimeStr(String pattern) {
        Clock clock = Clock.systemUTC();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.now(clock));
    }

    /**
     * 获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
     * @param i
     * @return
     */
    public static Date getDateByDay(int i) {
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);
        dat = cd.getTime();
        SimpleDateFormat dformat = new SimpleDateFormat(DATETIME_SIMPLE_PATTERN);
        Timestamp date = Timestamp.valueOf(dformat.format(dat));
        return date;
    }

    /**
     * 获取当前时间距离当天凌晨的秒数
     * @param millis
     * @return
     */
    public static long currentTimeFromMorning(long millis) {
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        long overTime = 0;
        try {
            overTime = (millis - (sdfOne.parse(sdfOne.format(millis)).getTime()))/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return overTime;
    }

}
