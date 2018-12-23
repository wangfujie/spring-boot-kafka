package com.example.wangfj.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期处理
 * @author wangfujie
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间格式转成 yyyy-MM-dd 格式的字符串
     * @param date
     * @return
     */
    public static String formatYmd(Date date) {
        return formatCustom(date, DATE_PATTERN);
    }

    /**
     * 将时间格式转成 yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param date
     * @return
     */
    public static String formatYmdHms(Date date) {
        return formatCustom(date, DATE_TIME_PATTERN);
    }

    /**
     * 将时间转换成指定格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formatCustom(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 获取当前时间的时分秒到毫秒的字符串
     *
     * @return String 返回字符串
     */
    public static String getDateTimeStr() {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR);
        String MM = Cld.get(Calendar.MONTH) + 1 + "";
        if (Cld.get(Calendar.MONTH) + 1 < 10) {
            MM = "0" + MM;
        }
        String DD = Cld.get(Calendar.DATE) + "";
        if (Cld.get(Calendar.DATE) < 10) {
            DD = "0" + DD;
        }
        String HH = Cld.get(Calendar.HOUR_OF_DAY) + "";
        if (Cld.get(Calendar.HOUR_OF_DAY) < 10) {
            HH = "0" + HH;
        }
        String mm = Cld.get(Calendar.MINUTE) + "";
        if (Cld.get(Calendar.MINUTE) < 10) {
            mm = "0" + mm;
        }
        String SS = Cld.get(Calendar.SECOND) + "";
        if (Cld.get(Calendar.SECOND) < 10) {
            SS = "0" + SS;
        }
        int MI = Cld.get(Calendar.MILLISECOND);
        return YY + MM + DD + HH + mm + SS + MI;
    }

    /**
     * 获取上个月最后一天的后一秒
     */
    public static Date getLastMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

        return calendar.getTime();
    }


    /**
     * 计算两个时间的时间差(向上取整,单位:天)
     */
    public static int getDiffDays(Date yesterday, Date now) {
        if (yesterday == null || now == null) {
            throw new NullPointerException("by calculating the difference between two [java.util.Date]");
        }
        double yDouble = now.getTime() - yesterday.getTime();
        //一天的时间
        double oneDouble = 24 * 60 * 60 * 1000;
        double days = yDouble / oneDouble;
        return (int) Math.ceil(days);
    }

    /**
     * 计算两个时间的时间差(向上取整,单位:天);  只计算工作日 (以24小时为一天)
     */
    public static int getDiffDayWithOutWeekends(Date yesterday, Date now) {
        Calendar calendar = Calendar.getInstance();
        if (yesterday == null || now == null) {
            throw new NullPointerException("by calculating the difference between two [java.util.Date]");
        }
        double yDouble = now.getTime() - yesterday.getTime();
        //一天的时间
        double oneDouble = 24 * 60 * 60 * 1000;
        int days = (int) (yDouble / oneDouble);
        int sum = 0;
        for (int i = 0; i < days; i++) {
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            if (isWeekend(calendar)) {
                sum++;
            }
        }
        //subtract weekends
        days = days - sum;
        return days;

    }

    /**
     * 判断是否是周末
     *
     * @return
     */
    public static boolean isWeekend(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 6 || week == 0;
    }

    /**
     * 根据时间偏移量得到新的时间
     *
     * @param way      (1代表天数，2代表月数)
     * @param time     （时间偏移量）
     * @param type     （时间类型）
     * @param datetime （传入时间）
     * @return
     */
    public static String getDate(int way, int time, int type, Date datetime) {
        //取时间
        Date date = new Date();
        if (datetime != null) {
            date = datetime;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        if (way == 1) {
            //把日期往后增加天数.整数往后推,负数往前移动
            calendar.add(Calendar.DATE, time);
        }
        if (way == 2) {
            //把日期往后增加月数.整数往后推,负数往前移动
            calendar.add(Calendar.MONTH, time);
        }
        //这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        String dateString = "";
        if (type == 1) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateString = format.format(date);
        } else if (type == 2) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateString = formatter.format(date);
        }
        return dateString;
    }

    /**
     * 计算传入参数时间加上N个月之后的日期
     *
     * @param date 当前时间
     * @param moth 月数
     * @return 结果
     */
    public static String dataAddMoth(Date date, int moth) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, moth);

        return fmt.format(calendar.getTime());
    }

    public static Date getDateTime(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date Date = new Date();
        try {
            Date = sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Date;
    }

    /**
     * 计算两个时间天数之差，排除周末，并且不以24小时为一天
     *
     * @param yesterday
     * @param now
     * @return
     */
    public static Integer getDaysWithOutWeekends(Date yesterday, Date now) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String yesterdayString = formatter.format(yesterday) + " 00:00:00";
        String nowString = formatter.format(now) + " 00:00:00";
        Integer days = 0;
        try {
            yesterday = dateFormat.parse(yesterdayString);
            now = dateFormat.parse(nowString);
            days = getDiffDayWithOutWeekends(yesterday, now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static Date getNewTimeWithOutWeekends(Integer days, Date date) {
        Calendar calendar = Calendar.getInstance();
        Date newDate;
        for (int i = 0; i < days; i++) {
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, i);
            if (isWeekend(calendar)) {
                days = days + 1;
            }
        }
        String dateStr = getDate(1, days, 2, date);
        newDate = getDateTime(dateStr);
        return newDate;
    }

    /**
     * 将字符串转为date类型
     * @param strDate
     * @return
     */
    public static Date parse(String strDate) {
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat();

        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }
        sdf.applyPattern("yyyy-MM-dd HH:mm");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy-MM-dd");
        try {
            date = sdf.parse(strDate);
            System.out.println("" + date);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy/MM/dd");
        try {
            sdf.applyPattern("dd/MM/yyyy");
            date = sdf.parse(strDate);
            System.out.println("" + date);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyyMMdd");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月dd日");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年WW周");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyyMM");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 取得前i天所有日期
     * @param i
     * @return
     */
    public static List<String> getDays(int i) {
        List<String> dateList = new ArrayList<String>();
        for (int j = -i; j < 0; j++) {
            Calendar cal = Calendar.getInstance();
            //设置为前j天
            cal.add(Calendar.DAY_OF_MONTH, j);
            String tempDate = formatYmd(cal.getTime());
            dateList.add(tempDate);
        }
        return dateList;
    }

}