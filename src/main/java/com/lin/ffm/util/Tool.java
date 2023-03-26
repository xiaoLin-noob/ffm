package com.lin.ffm.util;

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
}
