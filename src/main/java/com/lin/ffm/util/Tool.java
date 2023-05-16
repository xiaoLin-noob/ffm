package com.lin.ffm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tool {

    public static boolean isNumber(String str){
        if(str == null || str.length() == 0){
            return false;
        }
        for (int i=0;i<str.length();i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static int getMonthSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int i = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        int month = 0;
        if (i<0)
        {
            month = -i*12;
        }else if(i>0)
        {
            month =  i*12;
        }
        result = (c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH)) + month;

        return result == 0 ? 1 : Math.abs(result);

    }



}
