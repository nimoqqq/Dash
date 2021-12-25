package com.example.demo.dateApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: Dash
 * @ClassName: OldDate
 * @description: 旧时间和日期API
 * @author: chuf
 * @create: 2021-12-25 23:07
 **/
public class OldDate {

    public static void main(String[] args) throws ParseException {
        System.out.println("-------------Date start-----------------");
        dateDemo();
        System.out.println("-------------Date end-----------------");
        System.out.println("-------------Calendar start-----------------");
        calendarDemo();
        System.out.println("-------------Calendar end-----------------");
        System.out.println("-------------SimpleDateFormat start-----------------");
        simpleDateFormatDemo();
        System.out.println("-------------SimpleDateFormat end-----------------");
    }

    /**
     * Date: 最早的时间 API(1.0)
     * {@link Date} java.util.Date
     */
    private static void dateDemo() {
        // 当前日期
        Date date = new Date();
        // 创建时间对象，并指定年月日(年份是从 1900 开始、月份是 0~11，所以 2021/12/25 所以传参为 121、11、25)
        Date date1 = new Date(121, 11, 25);

        System.out.println(date);
        System.out.println(date1);
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        System.out.println(date.getTime()); // 当前时间毫秒数（时间戳），一般使用 System.currentTimeMillis()
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
    }

    /**
     * Calendar: 设置获取时间
     * {@link Calendar} java.util.Calendar
     */
    private static void calendarDemo() {
        Calendar calendar = Calendar.getInstance(); // 获取当前时间
        // 获取当前时间:
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 1 + c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int w = c.get(Calendar.DAY_OF_WEEK);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);
        // 清除所有:
        c.clear();
        // 设置年月日:
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DATE, 25);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 35);
        c.set(Calendar.SECOND, 12);
        // 转为 Date 对象
        System.out.println(c.getTime());
    }

    /**
     * SimpleDateFormat: 时间格式化
     * {@link SimpleDateFormat} java.text.SimpleDateFormat
     */
    private static void simpleDateFormatDemo() throws ParseException {
        //使用指定模型创建对象
        //yyyy-MM-dd hh:mm:ss 十二小时制、yyyy-MM-dd HH:mm:ss 二十四小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Date 转 String
        final String format = sdf.format(new Date());
        System.out.println(format);
        //String 转 Date
        final Date parse = sdf.parse("2021-12-25 23:46:20");
        System.out.println(parse);
    }
}
