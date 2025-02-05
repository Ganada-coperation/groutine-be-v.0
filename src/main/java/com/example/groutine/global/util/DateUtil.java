package com.example.groutine.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatDate(LocalDateTime date) {
        return date.format(FORMATTER);
    }

    public static int calculateDaysRemaining(LocalDateTime endDate) {
        return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), endDate);
    }
}
