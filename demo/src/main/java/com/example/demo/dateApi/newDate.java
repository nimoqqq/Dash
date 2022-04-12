package com.example.demo.dateApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: Dash
 * @ClassName: newDate
 * @description:
 * @author: chuf
 * @create: 2021-12-25 23:52
 **/
public class newDate {
    public static void main(String[] args) {
        final LocalDate now = LocalDate.now();
        final LocalDate localDate = now.withDayOfMonth(1);

        localDateDemo();
        System.out.println("---------localDateDemo end---------");
        localTimeDemo();
        System.out.println("---------localTimeDemo end---------");
        localDateTimeDemo();

        dateTimeFormatterDemo();
    }

    /**
     * {@link LocalDate} 用于表示当前日期，他只用于表示日期，不包含时间
     * 设置日期不用在像{@link java.util.Date}一样，从 1990 开始计算。
     * 日期所见即所得
     */
    private static void localDateDemo() {
        final LocalDate now = LocalDate.now();
        // 获取今天日期
        System.out.println(now);
        // 设置日期,并创建对象
        System.out.println(LocalDate.of(2021, 12, 25));
        // 设置日期,并创建对象
        System.out.println(LocalDate.parse("2021-12-25"));

        //当前时间加一天
        final LocalDate plusDays = now.plusDays(1);
        //当前时间减一个月
        final LocalDate plusMonths = now.plusMonths(-1);
        //当前时间加两年
        final LocalDate plusYears = now.plusYears(2);
        // 获取星期几字段，这是一个枚举 DayOfWeek
        System.out.println(now.getDayOfWeek());
        // 获取月份字段
        System.out.println(now.getDayOfMonth());
    }

    /**
     * {@link LocalTime} 类似于 {@link LocalDate}，一个只用来表示时间的类
     * 默认格式：hh:mm:ss:nnn
     */
    private static void localTimeDemo() {
        LocalTime now = LocalTime.now();
        System.out.println("获取当前时间：" + now);

        LocalTime noon = LocalTime.NOON;
        System.out.println("中午时间：" + noon);

        System.out.println("从一小时分钟获取一个 LocalTime的实例：" + LocalTime.of(12, 31));
        System.out.println("从一小时分钟获取一个 LocalTime的实例：" + LocalTime.parse("12:31"));
        System.out.println(now.atDate(LocalDate.now()));
    }

    /**
     * {@link java.time.LocalDateTime} 表示日期和时间的结合
     */
    private static void localDateTimeDemo() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("获取当前的日期时间：" + now);

        System.out.println("指定年月日 时分秒：" + LocalDateTime.of(2021, 12, 27, 12, 52, 20));
    }


    private static void dateTimeFormatterDemo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String format = now.format(isoLocalDateTime);
        System.out.println("format = " + format);
    }
}
