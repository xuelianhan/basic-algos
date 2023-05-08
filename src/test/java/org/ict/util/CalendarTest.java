package org.ict.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collection;
import java.util.Set;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            types.add("" + i);
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
        System.out.println(month + 1);
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
