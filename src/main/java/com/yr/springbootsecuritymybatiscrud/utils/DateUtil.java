package com.yr.springbootsecuritymybatiscrud.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

    public static Timestamp getTimestamp(){
        return Timestamp.valueOf(getDate());
    }
}
