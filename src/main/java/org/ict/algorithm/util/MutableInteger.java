package org.ict.algorithm.util;

import java.lang.reflect.Field;

public class MutableInteger {

	public static void main(String[] args) {
		Integer x = 17;
		StdOut.println(x);
		mutate(x);
		StdOut.println(x);
	}
	
	public static void mutate(Integer x) {
		try {
			Field value = Integer.class.getDeclaredField("value");
			value.setAccessible(true);
			
			value.setInt(x, 99999999);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
