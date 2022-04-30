package com.anserlt.common.util.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 此处尝试使用java8的时间日期新特性(https://cloud.tencent.com/developer/article/1672924)
 * Instant              时间戳
 * Duration             持续时间、时间差
 * LocalDate            只包含日期，比如：2018-09-24
 * LocalTime            只包含时间，比如：10:32:10
 * LocalDateTime        包含日期和时间，比如：2018-09-24 10:32:10
 * Peroid               时间段
 * ZoneOffset           时区偏移量，比如：+8:00
 * ZonedDateTime        带时区的日期时间
 * Clock                时钟，可用于获取当前时间戳
 * DateTimeFormatter   时间格式化类
 *
 * @author Anserlt
 */
public class TimeUtil {

    public void timeUtil() {

        /*
            如何在java8中获取当天的日期
            创建了今天的日期却不包含时间信息，并且格式化了日期
            today = 2020-02-06
         */
        LocalDate today = LocalDate.now();

        /*
            如何在java8中获取当前的年月日
         */
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        /*
            在java8中如何获取某个特定的日期
         */
        LocalDate dateOfBirth = LocalDate.of(2020, 2, 2);

        /*
            在java8中检查两个日期是否相等
         */
        boolean isEqual = dateOfBirth.equals(today);

        /*
            获取1周后的日期
            plus()方法可以用来增加日，星期，月，ChronoUnit则用来表示时间单位，同理可用来增加小时、分钟
         */
        LocalDate oneToday = today.plus(1, ChronoUnit.WEEKS);

        /*
            一年前的日期
         */
        LocalDate previousYear = today.minusYears(1);

        /*
            在java中如何判断某个日期在另一个日期的前面还是后面
            isAfter、isBefore
         */
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("日期: " + tomorrow + "是否在日期: " + today + "之后: " + tomorrow.isAfter(today));
        System.out.println("日期: " + tomorrow + "是否在日期: " + today + "之前: " + tomorrow.isBefore(today));

        /*
            在java8中检查闰年 isLeapYear
         */
        System.out.printf("%s 是否是闰年: %s ", today, today.isLeapYear());

        /*
            两个日期之间包含多少天，多少月
         */
        LocalDate dates = LocalDate.of(2016, Month.MARCH, 14);
        Period period = Period.between(today, dates);
        period.getMonths();
        period.getDays();
        period.getYears();

        /*
            字符串 -> 日期
            parse
         */
        String goodFriday = "01 10 2021";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.printf("字符 %s 转换成功后的日期是 %s%n", goodFriday, holiday);
            // 字符 02 06 2020 转换成功后的日期是 2020-02-06
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", goodFriday);
            e.printStackTrace();
        }

        /*
        ********************************************************************************************************
         */

        /*
            在java8中获取当前时间
            localTime = 21:00:22.404
         */
        LocalTime localTime = LocalTime.now();

        /*
            增加时间的小时
         */
        LocalTime twoHoursLater = localTime.plusHours(2);


        /*
        ********************************************************************************************************
         */


        /*
            日期 -> 字符串
            format
         */
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String landing = arrivalDate.format(formatter);
            System.out.printf("时间日期 %s 转换后的字符是 %s %n", arrivalDate, landing);
            // 时间日期 2020-02-06T22:14:00.380 转换后的字符是 2020-02-06 22:14:00
        } catch (DateTimeException e) {
            System.out.printf("%s 格式转换错误 %n", arrivalDate);
            e.printStackTrace();
        }


        /*
        ********************************************************************************************************
         */

        /*
            获取当前时间戳，秒级、毫秒级
         */
        LocalDateTime now = LocalDateTime.now();
        long timeStampNowSecond = now.toEpochSecond(ZoneOffset.of("+8"));
        long timeStampNowMSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();

    }

}
