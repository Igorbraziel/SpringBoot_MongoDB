package com.example.demo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class URL {
    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e){
            return "";
        }
    }

    public static Instant convertDate(String dateText, Instant defaultDate){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateText, dateTimeFormatter);
            return Instant.parse(localDate + "T00:00:00Z");
        } catch (Exception e){
            return defaultDate;
        }
    }
}
