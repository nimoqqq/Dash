package com.chuf.javase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;

public class Test {
    public static void main(String[] args) throws ParseException {

        Date month = getMonth(-6);
        System.out.println(month);
        System.out.println(month.getTime());

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
    }

    private static Date getMonth(int month){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH,month);
        return c.getTime();
    }
}
