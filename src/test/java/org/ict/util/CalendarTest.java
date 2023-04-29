package org.ict.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collection;
import java.util.Set;

public class CalendarTest {
    
  private static final DateTimeFormatter format_one = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    
  private static final DateTimeFormatter format_two = DateTimeFormat.forPattern("yyyy-MM-dd");

  public static void testDateTime() {
      DateTime currentDate = DateTime.now();
      DateTime start = currentDate.withHourOfDay(10).withMinuteOfHour(30).withSecondOfMinute(0);
      String startTime = format_one.print(start);
      DateTime end = currentDate.withHourOfDay(19).withMinuteOfHour(30).withSecondOfMinute(0);
      String endTime = format_one.print(end);
      DateTime orderDate = DateTime.now();
      String orderDateTime = format_two.print(orderDate);
  }
    
  
  public static void testGuavaFilter() {
      Set<String> types = Sets.newHashSet();
      for (int i = 0; i < 5; i++) {
          types.add(""+i);
      }
      Collection<String> filtered = Collections2.filter(types, new Predicate<String>() {
          @Override
          public boolean apply(String input) {
               if (Integer.parseInt(input) > 2) {
                   return false;
               }
              return true;
          }
      });
      System.out.println(filtered);
  }
    
  


}
