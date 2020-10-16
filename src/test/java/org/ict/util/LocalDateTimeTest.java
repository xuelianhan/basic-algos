package org.ict.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {
	
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		now.getYear();
		now.getMonth();
		now.getDayOfMonth();
		LocalTime t = LocalTime.of(8, 0, 0);
		LocalDateTime time = LocalDateTime.of(now, t);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String s = time.format(formatter);
		System.out.println(s);
	}

}
