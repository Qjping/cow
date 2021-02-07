package cow.infrastructures.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class DateTimeUtils {

    public static final String FORMATTER_DATE = "yyyy-MM-dd";
    public static final String FORMATTER_TIME = "HH:mm:ss";
    public static final String FORMATTER_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_DATETIME_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_CRON = "ss mm HH dd MM ? yyyy";
    public static final String FORMATTER_DATE_EX = "yyyyMMdd";

    public static final DateTimeFormatter DATE_FORMATTER_YMD = DateTimeFormatter.ofPattern(FORMATTER_DATE_EX);

    public static final DateTimeFormatter DATE_FORMATTER_DATE = DateTimeFormatter.ofPattern(FORMATTER_DATETIME);

    /**
     * 获取当前系统日期字符串 yyyyMMdd
     *
     * @return
     */
    public static String getLocalDateStringYMD() {
        return LocalDate.now().format(DATE_FORMATTER_YMD);
    }

    /**
     * 获取当前系统当天最早时间
     *
     * @return
     */
    public static LocalDateTime getLocalDateTimeMin() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取当前系统当天最晚时间
     *
     * @return
     */
    public static LocalDateTime getLocalDateTimeMax() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59, 999000000));
    }

    /**
     * 获取当天日期
     *
     * @return
     */
    public static LocalDate getToday() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }


    /**
     * 秒转时间
     * ZoneOffset.ofHours(8)-->中国上海时区
     *
     * @param second
     * @return
     */
    public static LocalDateTime secondToTime(long second) {
        return LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.ofHours(8));
    }


    /**
     * 时间转秒
     *
     * @param localDateTime
     * @return
     */
    public static long timeToSecond(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
    }

    /**
     * 日期转天数
     *
     * @return
     */
    public static long dateToSecond(LocalDate localDate) {
        return localDate.toEpochDay();
    }

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),

    //Date转换为LocalDateTime
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    //LocalDateTime转换为Date
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    //获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    //获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    //获取指定时间的指定格式
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    //获取当前时间的指定格式
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    //当前日期转字符串
    public static LocalDate formatDate(String localDate) {
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(FORMATTER_DATE));
    }

    //日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    //日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个时间的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startDate
     * @param endDate
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoDate(LocalDate startDate, LocalDate endDate, ChronoUnit field) {
        return field.between(startDate, endDate);
    }

    //获取一天的开始时间，2017,7,22 00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999000000);
    }

    /**
     * @param date
     * @param i
     * @return
     */
    public static LocalDate addDays(LocalDate date, int i) {
        return date.plusDays(i);
    }


    /**
     * 获取两个日期间隔的所有日期
     *
     * @param start 格式必须为'2018-01-25'
     * @param end   格式必须为'2018-01-25'
     * @return
     */
    public static List<LocalDate> getBetweenDate(String start, String end) {
        List<LocalDate> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 0) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(distance + 1)
                .forEach(list::add);
        return list;
    }

    public static long getSecondNowToNextDay(){
        return ChronoUnit.SECONDS.between(DateTimeUtils.getNow(),DateTimeUtils.getDayEnd(LocalDateTime.now()));
    }

    /**
     * 获取当前系统最大时间
     *
     * @return
     */
    public static LocalDateTime getSystemMax() {
        return LocalDateTime.of(LocalDate.of(2099, 01, 01), LocalTime.MIN);
    }

    /**
     * 获取当前系统最小时间
     *
     * @return
     */
    public static LocalDateTime getSystemMin() {
        return LocalDateTime.of(LocalDate.of(1000, 01, 01), LocalTime.MIN);
    }
}







