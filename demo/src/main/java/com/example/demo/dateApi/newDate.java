package com.example.demo.dateApi;

import java.time.LocalDate;

/**
 * @program: Dash
 * @ClassName: newDate
 * @description:
 * @author: chuf
 * @create: 2021-12-25 23:52
 **/
public class newDate {
    public static void main(String[] args) {
        localDateDemo();
    }

    /**
     * {@link LocalDate} 用于表示当前日期，他只用于表示日期，不包含时间
     * 设置日期不用在像{@link java.util.Date}一样，从 1990 开始计算。
     * 日期所见即所得
     */
    private static void localDateDemo() {
        final LocalDate now = LocalDate.now();

        System.out.println(now); // 获取今天日期
        System.out.println(LocalDate.of(2021, 12, 25)); // 设置日期,并创建对象
        System.out.println(LocalDate.parse("2021-12-25")); // 设置日期,并创建对象

        //当前时间加一天
        final LocalDate plusDays = now.plusDays(1);
        //当前时间减一个月
        final LocalDate plusMonths = now.plusMonths(-1);
        //当前时间加两年
        final LocalDate plusYears = now.plusYears(2);

        System.out.println(now.getDayOfWeek()); // 获取星期几字段，这是一个枚举 DayOfWeek
        System.out.println(now.getDayOfMonth()); // 获取月份字段
    }
}
