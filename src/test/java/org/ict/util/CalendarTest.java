package org.ict.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

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
  
  public void testOne() {
      //testDay();
      /* DateTime dateTime = DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(6);
       System.out.println(dateTime);
       DateTime today = DateTime.now().millisOfDay().withMaximumValue();
       System.out.println(today);*/ 
  }
  
  public void testTwo() {
      String date = "2017-07-25T23:59:59.000+02:00";
      DateTime dateTime = new DateTime(date);
      System.out.println(dateTime.toString());
      System.out.println(dateTime.getYear());
      System.out.println(dateTime.getMonthOfYear());
      System.out.println(dateTime.getDayOfMonth());
      System.out.println(dateTime.getHourOfDay());
      System.out.println(dateTime.getMinuteOfHour());
      System.out.println(dateTime.getMillis());
      
  }
  
  public void testThree() {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      int day = calendar.get(Calendar.HOUR_OF_DAY);
      int hour = calendar.get(Calendar.HOUR);
      int minute = calendar.get(Calendar.MINUTE);
      int month = calendar.get(Calendar.MONTH);
      System.out.println(day);
      System.out.println(hour);
      System.out.println(minute);
      System.out.println(month+1);  
  }
  
  public static void testFour() throws ParseException {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date d = sdf.parse("2017-07-25 23:59:59");
      DateTime dateTime = new DateTime(d); 
      System.out.println(dateTime.toString());
  }

  public static void main(String[] args) {
      try {
          testFour(); 
      } catch (Exception e) {
          e.printStackTrace();
      }
   
     
  }

}
