package com.myspring.xixi;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class tests {
    public static void main(String[] args) {
        LocalDate localDate = LocalDateTimeUtil.parseDate("2020-01-23T12:23:56", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(localDate);
    }
}
