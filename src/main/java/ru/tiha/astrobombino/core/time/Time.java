package ru.tiha.astrobombino.core.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Time {
    private final static SimpleDateFormat SIMPLE_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final static SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private final static SimpleDateFormat SIMPLE_DATE_TIME_SEC_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private final static DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private final static DateTimeFormatter TIME_SEC_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final static DateTimeFormatter DATE_TIME_SEC_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static final Map<Integer, String> daysOfWeek = Map.of(
        1, "Понедельник",
        2, "Вторник",
        3, "Среда",
        4, "Четверг",
        5, "Пятница",
        6, "Суббота",
        7, "Воскресенье"
    );
    public static final String[] DAYS_OF_WEEK_STRINGS = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    public static final Integer[] DAYS_OF_WEEK = {1, 2, 3, 4, 5, 6, 7};
    public static final String[] MONTHS_COMMON = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    public static final String[] MONTHS = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};

    public static long currentSeconds() {
        return currentMilliSeconds() / 1000;
    }

    public static long currentMilliSeconds() {
        return new Date().getTime();
    }

    /**
     * Возвращает название дня недели по его порядковому номеру,
     * где 1 - Пн, 2 - Вт, и т.д.
     *
     * @param day
     * @return
     */
    public static String getDayOfWeekString(Integer day) {
        return day == null ? null : daysOfWeek.get(day);
    }

    /**
     * Возвращает порядковый номер дня недели по его названию.
     *
     * @param day
     * @return
     */
    public static Integer getDayOfWeek(String day) {
        for (Map.Entry<Integer, String> entry : daysOfWeek.entrySet()) {
            if (entry.getValue().equals(day)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getApprovalDocumentDateString(Date date) {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format("«%02d» %s %d г.",
            calendar.get(Calendar.DAY_OF_MONTH),
            MONTHS[calendar.get(Calendar.MONTH)],
            calendar.get(Calendar.YEAR)
        );
    }

    public static Date toDate(LocalDate local) {
        return java.sql.Date.valueOf(local);
    }

    public static Date toDate(LocalDateTime local) {
        return java.sql.Timestamp.valueOf(local);
    }

    public static String nowFormatted() {
        return SIMPLE_DATE_TIME_SEC_FORMAT.format(new Date());
    }

    public static String toHumanDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? DATE_TIME_FORMATTER.format(localDateTime) : "";
    }

    public static LocalDateTime fromHumanDateTime(String localDateTimeString) {
        return localDateTimeString != null ? LocalDateTime.parse(localDateTimeString, DATE_TIME_FORMATTER) : null;
    }

    public static String toHumanDate(LocalDateTime localDateTime) {
        return localDateTime != null ? DATE_FORMATTER.format(localDateTime) : "";
    }

    public static String toHumanTime(LocalTime localTime) {
        return localTime != null ? TIME_FORMATTER.format(localTime) : "";
    }

    public static String toHumanTimeWithSec(LocalTime localTime) {
        return localTime != null ? TIME_SEC_FORMATTER.format(localTime) : "";
    }

    public static String toHumanDate(LocalDate localDate) {
        return localDate != null ? DATE_FORMATTER.format(localDate) : "";
    }

    public static String toHumanDate(Date date) {
        return date != null ? SIMPLE_DATE_FORMAT.format(date) : "";
    }

    public static String toHumanDateTime(Date date) {
        return date != null ? SIMPLE_DATE_TIME_FORMAT.format(date) : "";
    }

    public static String toHumanDateTimeWithSec(Date date) {
        return date != null ? SIMPLE_DATE_TIME_SEC_FORMAT.format(date) : "";
    }

    public static String toHumanDateTimeWithSec(LocalDateTime localDateTime) {
        return localDateTime != null ? DATE_TIME_SEC_FORMATTER.format(localDateTime) : "";
    }

    public static String toTimestampStr(LocalDateTime localDateTime) {
        return localDateTime.format(TIMESTAMP_FORMATTER);
    }

    public static String toTimestampStr(Date date) {
        return SIMPLE_TIMESTAMP.format(date);
    }

    public static String toTimestampStr(long millis) {
        return SIMPLE_TIMESTAMP.format(new Date(millis));
    }

    /**
     * Формат исходной строки 2021-12-29T06:45:34.000Z
     */
    public static String fromSignatureFormat(String date) {
        date = date.replace("T", " ");
        var i = date.indexOf(".");
        if (i != -1) {
            date = date.substring(0, i);
        }
        try {
            return toHumanDateTime(SIMPLE_TIMESTAMP.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }
}