package com.sunilos.proj0.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format Input data.
 * 
 * @author Chain of Responsibility
 * @version 1.0 Copyright (c) Chain of Responsibility
 * 
 */
public class Util {

	public static Date getDate(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = null;

        try {
        	if(date != null){
            date1 = formatter.parse(date);
        	}
            //String d1=convertStringToDate(date1);
            
            
            //System.out.println(date1);
//            System.out.println("----------------------MMMMMMM--===="+formatter.format(date1));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date1;
    }

    public static String getDate(Date indate) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
        /*
         * you can also use DateFormat reference instead of SimpleDateFormat
         * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
         */
        try {
            dateString = sdfr.format(indate);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static String convertStringToDate(Date indate) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        /*
         * you can also use DateFormat reference instead of SimpleDateFormat
         * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
         */
        try {
            dateString = sdfr.format(indate);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }
}
