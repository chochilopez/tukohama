package com.gloit.tukohama.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Helper {
    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("America/Argentina"));
        Date date = cal.getTime();

        return date;
    }

    public static String getFecha(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");

        return formato.format(new Date());
    }

    public static String getAnio(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy");

        return formato.format(new Date());
    }

    public static String getMes(){
        SimpleDateFormat formato = new SimpleDateFormat("M");

        return formato.format(new Date());
    }

    public static String getDia(){
        SimpleDateFormat formato = new SimpleDateFormat("d");

        return formato.format(new Date());
    }


    public static Double get2Decimal(Double numero){
        BigDecimal bigDecimal = new BigDecimal(numero).setScale(2, RoundingMode.UP);

        return bigDecimal.doubleValue();
    }
}
