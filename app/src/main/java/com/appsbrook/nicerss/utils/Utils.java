package com.appsbrook.nicerss.utils;

import android.text.format.DateFormat;

import java.util.Date;

public class Utils {

    public static String formatDate(Date date) {

        if (date == null) return "";

        return String.valueOf(DateFormat.format("yyyy.MM.dd HH:mm", date));
    }
}
