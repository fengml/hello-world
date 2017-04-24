package com.fsl.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * DateUtil
 *
 * @author: 杨永川
 * @version: 1.0, 2015年10月26日
 */
public class DateUtils {
    public static final SimpleDateFormat sdf        = new SimpleDateFormat(ContentUtils.TIME_FORMAT1);

    public static final SimpleDateFormat sdf_simple = new SimpleDateFormat(ContentUtils.TIME_FORMAT_SIMPLE);



    /**
     * 将时间格式转化为String类型
     * 
     * @param newdate
     * @return
     */
    public static String formatDateToString(Date newdate) {
        if (null == newdate) {
            return null;
        }
        // yyyy-MM-dd HH:mm:ss
        String date = sdf.format(newdate);
        return date;
    }



    /**
     * 将时间格式转化为String类型
     * 
     * @param newdate
     * @return
     */
    public static String formatDateToSimpleString(Date newdate) {
        if (null == newdate) {
            return null;
        }
        // yyyy-MM-dd HH:mm:ss
        String date = sdf_simple.format(newdate);
        return date;
    }



    /**
     * 
     * @param newdate
     * @param count
     * @return
     */
    public static Date setTime(Date newdate, int count) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newdate);
        calendar.add(Calendar.MONTH, count);

        return calendar.getTime();
    }



    /**
     * 判断哪个时间相差几天 date1>date2
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int dateGap(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return 0;
        }
        int dayGap = 0;
        try {
            long timeGap = date1.getTime() - date2.getTime();
            dayGap = (int) (timeGap / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
        }
        return dayGap;
    }



    /**
     * 判断哪个时间相差几天 timestamp1>timestamp2
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int dateGap(Timestamp timestamp1, Date timestamp2) {
        if (null == timestamp1 || null == timestamp2) {
            return 0;
        }
        int dayGap = 0;
        try {
            long timeGap = timestamp1.getTime() - timestamp2.getTime();
            dayGap = (int) (timeGap / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
        }
        return dayGap;
    }



    /**
     * 
     * @return
     */
    public static Date getCurrentDate() {
        Date currentTime = new Date(getTime());
        return currentTime;

    }



    /**
     * 
     * @Title: getTime @Description:获取毫秒 @param @return @return long @throws
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }



    /**
     * 
     * @Title: getSecond @Description:获取秒 @param @return @return int @throws
     */
    public static int getSecond() {
        return (int) (getTime() / 1000);
    }



    /**
     * 
     * @Title: getSecond @Description:指定时间 获取秒 @param @return @return
     *         int @throws
     */
    public static int getSecond(Date date) {
        return (int) (date.getTime() / 1000);
    }



    /**
     * 
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }



    /**
     * 
     * @Title: getFirstDay @Description:当月第一天 字符串 @param @return @return
     *         String @throws
     */
    public static String getFirstDayStr() {
        SimpleDateFormat df = new SimpleDateFormat(ContentUtils.YYYY_MM_DD);
        String day_first = df.format(getFirstDay());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }



    /**
     * 
     * @Title: getFirstDay @Description:当月第一天 date @param @return @return
     *         Date @throws
     */
    public static Date getFirstDay() {
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.add(Calendar.MONTH, -1);
        return gcLast.getTime();

    }



    /**
     * 
     * @Title: getLastMonthToDay @Description:上个月的今天 @param @return @return
     *         String @throws
     */
    public static String getLastMonthToDayStr() {
        SimpleDateFormat df = new SimpleDateFormat(ContentUtils.YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.add(Calendar.MONTH, -1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }



    /**
     * 
     * @Title: getLastMonthToDay @Description:上个月的今天 @param @return @return
     *         Date @throws
     */
    public static Date getLastMonthToDay() {
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.add(Calendar.MONTH, -1);
        return gcLast.getTime();

    }



    /**
     * 
     * @Title: timeToDate @Description:时间戳转化为时间 字符串 @param @param
     *         time @param @return @return String @throws
     */
    public static String timeToDate(long time) {
        Date date = new Date(time);
        return formatDateToString(date);
    }



    /**
     * 
     * @Title: timeToDate @Description:时间戳转化为时间 字符串 @param @param
     *         time @param @return @return String @throws
     */
    public static String timeToDate(long time, String dateFormat) {
        Date date = new Date(time);
        // yyyy.MM.dd HH:mm
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateStr = sdf.format(date);
        return dateStr;
    }



    /**
     * 
     * @Title: timeToDate @Description:时间戳转化为时间 字符串 @param @param
     *         time @param @return @return String @throws
     */
    public static String timeToDate(long time, SimpleDateFormat format) {
        Date date = new Date(time);
        if (format.equals(sdf_simple)) {
            return formatDateToSimpleString(date);
        } else if (format.equals(sdf)) {
            return formatDateToString(date);
        }
        return null;
    }



    /**
     * 
     * @Title: getLastDay @Description:当月最后一天 @param @return @return
     *         String @throws
     */
    public static String getLastDayStr() {
        SimpleDateFormat df = new SimpleDateFormat(ContentUtils.YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }



    /**
     * 
     * @Title: getLastDay @Description:当月最后一天 @param @return @return
     *         Date @throws
     */
    public static Date getLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();

    }



    /**
     * 
     * @Title: compareTimeWithNow @Description:与现在时间
     *         ，比较两个时间(开始，结束时间)是否有效 @param @param startTime @param @param
     *         endTime @param @return @return boolean @throws
     */
    public static boolean compareTimeWithNow(int startTime, int endTime) {
        return startTime < getSecond() && getSecond() < endTime;
    }



    /**
     * 
     * @Title: getTodayStartTime @Description:获取今天 0点时间戳
     *         毫秒 @param @return @return Long @throws
     */
    public static Long getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }



    /**
     * 
     * @Title: getTodayEndTime @Description:获取今天 24点时间戳
     *         毫秒 @param @return @return Long @throws
     */
    public static Long getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }



    /**
     * 
     * @Title: getTodayStartSecond @Description: 获取今天0点时间戳
     *         秒 @param @return @return int @throws
     */
    public static int getTodayStartSecond() {
        return (int) (getTodayStartTime() / 1000);
    }



    /**
     * 
     * @Title: getTodayEndSecond @Description: 获取今天24点时间戳
     *         秒 @param @return @return int @throws
     */
    public static int getTodayEndSecond() {
        return (int) (getTodayEndTime() / 1000);
    }



    /**
     * 
     * @Title: getTodayEndSecond @Description:
     *         获取指定月的第一天第一秒 @param @return @return int @throws
     */
    public static long getMonthBeginInSeconds(String monthTime) {
        long millis = 0;

        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(monthTime);
            millis = date.getTime();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return millis / 1000;
    }



    /**
     * 
     * @Title: getTodayEndSecond @Description:
     *         获取指定月的最后一天最后一秒 @param @return @return int @throws
     */
    public static long getMonthEndInSeconds(String monthTime) {
        long millis = 0;

        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(monthTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

            millis = c.getTimeInMillis();
            millis += 1000 * 60 * 60 * 24 - 1; // 一天的毫秒-1
            c.setTimeInMillis(millis);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return millis / 1000;
    }



    /**
     * 日期转换为时间戳
     * 
     * @return
     */
    public static Long dateToTime(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = ContentUtils.TIME_FORMAT1;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Long time = null;
        try {
            Date date = format.parse(dateStr);
            time = date.getTime();
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return time;
    }



    /**
     * 字符串转化为时间
     * 
     */
    public static Date stringToDate(String dateStr) {
        Date thisDate = null;
        List<DateFormat> fmtList = new ArrayList<DateFormat>();
        DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat fmt2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        DateFormat fmt3 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat fmt4 = new SimpleDateFormat("yyyy-MM");
        DateFormat fmt5 = new SimpleDateFormat("yyyy");
        fmtList.add(fmt1);
        fmtList.add(fmt2);
        fmtList.add(fmt3);
        fmtList.add(fmt4);
        fmtList.add(fmt5);
        ParseException lastException = null;
        for (DateFormat dateFormat : fmtList) {
            try {
                thisDate = dateFormat.parse(dateStr);
                return thisDate;
            } catch (ParseException e) {
                lastException = e;
            }
        }
        throw new IllegalArgumentException("Could not parse date: " + lastException.getMessage(), lastException);
    }

}
