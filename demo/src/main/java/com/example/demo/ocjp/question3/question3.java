package com.example.demo.ocjp.question3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class question3 {
    public static void main(String[] args) {
        String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(date);
    }
}
