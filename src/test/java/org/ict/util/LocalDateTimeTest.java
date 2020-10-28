package org.ict.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LocalDateTimeTest {
	
	public static void main(String[] args) {
		testSet();
	}
	
	public static void testLocalDateTime() {
		LocalDate nowDate = LocalDate.now();
		LocalTime t = LocalTime.of(8, 0, 0);
		LocalDateTime time = LocalDateTime.of(nowDate, t);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String s = time.format(formatter);
		System.out.println(s);
		
		LocalDateTime now10 = time.plusMinutes(10);
		LocalDateTime nowDateTime = LocalDateTime.now();
		boolean isAfter = nowDateTime.isAfter(time);
		System.out.println(isAfter);
		boolean isBefore = nowDateTime.isBefore(now10);
		System.out.println(isBefore);
	}
	
	public static void testSet() {
		Set<Long> sets = new HashSet<>();
		sets.add(1l);
		sets.add(2l);
		sets.add(3l);
		System.out.println(sets);
		Set<String>strSets = sets.stream().map(s -> String.valueOf(s)).collect(Collectors.toSet());
		String s = String.join(",", strSets);
		System.out.println(s);
	}

}
