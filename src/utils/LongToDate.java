package utils;

import java.time.Instant;

import java.util.Date;

public class LongToDate {
    public static Date convert(Long time) {
        return Date.from(Instant.ofEpochMilli(time));
    }
}
