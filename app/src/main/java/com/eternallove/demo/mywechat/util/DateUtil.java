package com.eternallove.demo.mywechat.util;

import android.content.Context;

import com.eternallove.demo.mywechat.R;

import java.util.Date;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/7
 */
public class DateUtil {
    private static final long DAY = 1000 * 60 * 60 * 24;
    private static final long WEEK = DAY * 7;
    private static final long MONTH = DAY * 30;
    private static final long YEAR = DAY * 365;
    private static final String COUNT_REPLACEMENT = "{count}";

    public static String getDateString(Context context, Date date) {
        if (date == null) {
            date = new Date();
        }

        long last = date.getTime();
        long now = new Date().getTime();
        long offset = now - last;
        if (offset <= DAY) {
            return context.getString(R.string.today);
        } else if (offset <= DAY * 2) {
            return context.getString(R.string.yesterday);
        } else if (offset <= WEEK) {
            return context.getString(R.string.days_ago)
                    .replace(COUNT_REPLACEMENT, String.valueOf(offset / DAY));
        }  else if (offset <= MONTH) {
            return context.getString(R.string.months_ago)
                    .replace(COUNT_REPLACEMENT, String.valueOf(offset / WEEK));
        } else if (offset <= YEAR) {
            return context.getString(R.string.months_ago)
                    .replace(COUNT_REPLACEMENT, String.valueOf(offset / MONTH));
        } else {
            return context.getString(R.string.years_ago)
                    .replace(COUNT_REPLACEMENT, String.valueOf(offset / YEAR));
        }
    }
}