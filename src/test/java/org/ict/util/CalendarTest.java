package org.ict.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

  public static void  testDay() {
    //int year = now.get(Calendar.YEAR);
    //int month = now.get(Calendar.MONTH) + 1;// Note: zero based!
    //int day = now.get(Calendar.DAY_OF_MONTH);//
                    
    //String yesterdayStr = year + month + (day-1) + "";
    //int yesterday = Integer.valueOf(yesterdayStr);
    
    for (int i = 30; i > 0; i--) {
      Calendar now = Calendar.getInstance();
      now.add(Calendar.DATE, -i);
      //int year = now.get(Calendar.YEAR);
      //int month = now.get(Calendar.MONTH) + 1;// Note: zero based!
      //int day = now.get(Calendar.DAY_OF_MONTH);//
      //              
      //String ymd = year + month + day + "";
      Date d = now.getTime();
      System.out.println("i="+ i + ", d=" + d); 
    }

  }

  public static void main(String[] args) {
    testDay();
  }

}
