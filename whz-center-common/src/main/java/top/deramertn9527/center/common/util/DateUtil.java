package top.deramertn9527.center.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类 util
 * <p>
 */
@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    /**
     * 时间单位到秒级
     */
    public static final int TIME_UNIT = 1000;
    /**
     * 默认时区：东八区
     */
    private static final String DEFAULT_TIME_ZONE = "+8";
    /**
     * 一分钟时间(秒)
     */
    public static final int ONE_MIN_TIME = 60;
    /**
     * 一小时时间(秒)
     */
    public static final int ONE_HOUR_TIME = 60 * ONE_MIN_TIME;
    /**
     * 一天时间(秒)
     */
    public static final int ONE_DAY_TIME = 24 * ONE_HOUR_TIME;
    /**
     * 一天时间差(秒)
     */
    public static final int ONE_DAY_TIME_DIFFERENCE = ONE_DAY_TIME - 1;
    /**
     * 零点三十分
     */
    public static final LocalTime MIDNIGHT_THIRTY = LocalTime.of(0, 30, 0);
    /**
     * 时间格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 当天开始时间
     */
    public static final LocalDateTime BEGIN_LOCAL_DATE_TIME = LocalDate.now().atStartOfDay();

    /**
     * 通过 LocalDateTime对象 得到 LocalDate对象
     *
     * @param localDateTime 当天时间
     * @return LocalDate
     */
    public static LocalDate getLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 获取指定天数的前后几天
     * <p>
     * 正数为后几天，负数为前几天
     *
     * @param localDate 指定天数
     * @param days      天数
     * @return LocalDate
     */
    public static LocalDate getLocalDate(LocalDate localDate, long days) {
        return localDate.plusDays(days);
    }

    /**
     * 通过 日期字符串 得到 LocalDate对象
     * <p>
     * example: 2017-12-01
     *
     * @param day 日期字符串
     * @return LocalDate
     */
    public static LocalDate getLocalDate(String day) {
        return LocalDate.parse(day);
    }

    /**
     * 获取当天 LocalDate对象
     *
     * @return LocalDate
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取今天的前后几天
     *
     * @param days 天数
     * @return LocalDate
     */
    public static LocalDate getLocalDate(long days) {
        return getLocalDate(getLocalDate(), days);
    }

    /**
     * 获取当前时间
     *
     * @return LocalTime
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }

    /**
     * 通过 LocalDate对象 取得 LocalDateTime对象
     *
     * @param localDate 当天日期类
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(LocalDate localDate) {
        return getLocalDateTime(localDate, getLocalTime());
    }

    /**
     * 通过 LocalDate对象和LocalTime对象 得到 LocalDateTime对象
     *
     * @param localDate 当天日期类
     * @param localTime 当天时间类
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * 获取当天以及当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 时间类型转换：Long转为LocalDateTime对象
     *
     * @param date 长整型时间
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(Long date) {
        Instant instant = new Date(date * TIME_UNIT).toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 时间类型转换：LocalDateTime,秒级, 默认东八区
     *
     * @param date LocalDateTime对象
     * @return Long
     */
    public static Long getLongTime(LocalDateTime date) {
        return getLongTime(date, DEFAULT_TIME_ZONE);
    }

    /**
     * 时间类型转换：LocalDateTime,秒级, 通过指定时区
     *
     * @param date     LocalDateTime对象
     * @param timeZone 时区
     * @return Long
     */
    public static Long getLongTime(LocalDateTime date, String timeZone) {
        return date.toInstant(ZoneOffset.of(timeZone)).toEpochMilli() / TIME_UNIT;
    }

    /**
     * 两个日期的时间差，到秒
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return Long
     */
    public static Long diffSecond(LocalDateTime d1, LocalDateTime d2) {
        Duration duration = Duration.between(d1, d2);
        return duration.getSeconds();
    }

    /**
     * 获取今天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getToday() {
        return getLocalDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT_YMD));
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTodayTime() {
        return getLocalDateTime().format(DateTimeFormatter.ofPattern(DATE_FORMAT_TIME));
    }

    /**
     * 获取当前日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getNow(){
        return createDate(getTodayTime(), DATE_FORMAT_YMD);
    }

    /**
     * 获取前/后几天
     *
     * @param num 整数
     * @return
     */
    public static String getNextDate(int num) {
        return getLocalDate(num).format(DateTimeFormatter.ofPattern(DATE_FORMAT_YMD));
    }

    /**
     * 获取前/后几天的当前时间
     *
     * @param num 整数
     * @return
     */
    public static String getNextDateTime(int num) {
        return getLocalDateTime(getLocalDate(num), getLocalTime()).format(DateTimeFormatter.ofPattern(DATE_FORMAT_TIME));
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 返回几天后的时间
     *
     * @param currentDate
     * @param days
     * @return Date
     */
    public static Date getAfterDaysDate(Date currentDate, int days) {
        Date returnDate = null;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
        try {
            returnDate = sdf.parse(sdf.format(d));
        } catch (ParseException e) {
            log.error("DateUtil-->getAfterHoursDate 解析异常", e);
        }
        return returnDate;
    }


    /**
     * 返回当前时间后的几分钟
     *
     * @param currentDate
     * @param minutes
     * @return
     */
    public static Date getAfterMinutesDate(Date currentDate, int minutes) {
        Date returnDate = null;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MINUTE, minutes);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
        try {
            returnDate = sdf.parse(sdf.format(d));
        } catch (ParseException e) {
            log.error("DateUtil-->getAfterHoursDate 解析异常", e);
        }
        return returnDate;
    }

    /**
     * 返回当前时间后的几个小时
     *
     * @param currentDate
     * @param hours
     * @return
     */
    public static Date getAfterHoursDate(Date currentDate, int hours) {
        Date returnDate = null;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.HOUR, hours);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
        try {
            returnDate = sdf.parse(sdf.format(d));
        } catch (ParseException e) {
            log.error("DateUtil-->getAfterHoursDate 解析异常", e);
        }
        return returnDate;
    }

    /**
     * 获取上个月最后一天减去subtractDay的日期
     *
     * @param subtractDay
     * @return 减去之后的日期 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getSubtractDate(int subtractDay, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(weeHours(yesterday(), 0));
        cal.add(Calendar.DAY_OF_MONTH, -subtractDay);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    /**
     * 凌晨
     *
     * @param date
     * @return
     * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     * 1 返回yyyy-MM-dd 23:59:59日期
     */
    public static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        }
        return cal.getTime();
    }

    /**
     * @return 返回表示昨天 0点 0分 0秒 0毫秒 的Date对象
     * @author laichendong
     */
    public static Date yesterday() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        yesterday = DateUtils.truncate(yesterday, Calendar.DATE);
        return yesterday.getTime();
    }

    /**
     * 获取若干分钟前的日期
     *
     * @param currentDate
     * @return
     */
    public static Date getMinsAgo(Date currentDate, int mins) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MINUTE, -mins);
        return c.getTime();
    }

    public static Date beforeDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 0 - day);
        return calendar.getTime();
    }

    /**
     * 获取一个时间范围内的所有日期
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<Date> getDates(Date beginDate, Date endDate) {
        beginDate = formatToDate(beginDate, DATE_FORMAT_YMD);
        endDate = formatToDate(endDate, DATE_FORMAT_YMD);
        List<Date> dates = new ArrayList<Date>();
        ;
        if (endDate.compareTo(beginDate) > 0) {
            Date index = formatToDate(beginDate, DATE_FORMAT_YMD);
            while (index.compareTo(endDate) <= 0) {
                dates.add(index);
                index = getTomorrow(index);
            }
        } else if (endDate.compareTo(beginDate) == 0) {
            dates.add(formatToDate(beginDate, DATE_FORMAT_YMD));
        }
        return dates;
    }

    /**
     * 获取输入日期的后一天
     *
     * @param currentDate 输入日期
     * @return 输入日期的前一天
     */
    public static Date getTomorrow(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD);
        return createDate(sdf.format(d), DATE_FORMAT_YMD);
    }

    public static Date formatToDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return createDate(sdf.format(date), format);
    }

    /**
     * 将时间格式的字符串，转化为Date
     *
     * @param dateFormat exp: 2010-7-22
     * @return java.util.Date
     */
    public static Date createDate(String dateString, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date d = null;
        try {
            d = sdf.parse(dateString);
        } catch (ParseException e) {
            log.error("字符串转化为Date失败,[string=" + dateString + "]", e);
        }
        return d;
    }


    /**
     * 将时间转化为指定格式的字符串
     *
     * @param date   输入日期
     * @param format 日期格式
     * @return 字符串格式的日期
     * @author lishuai
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}