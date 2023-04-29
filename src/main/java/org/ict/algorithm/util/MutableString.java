package org.ict.algorithm.util;

import java.lang.reflect.Field;

public class MutableString {

	public static void main(String[] args) {
		String s = "Immutable";
		String t = "Notreally";
		mutate(s, t);
		StdOut.println(s);
		StdOut.println(t);
		StdOut.println("test Immutable!");
	}
  
	public static void mutate(String s, String t) {
		try {
			Field val = String.class.getDeclaredField("value");
			Field off = String.class.getDeclaredField("offset");
			val.setAccessible(true);
			off.setAccessible(true);
			
			int offset = off.getInt(s);
			char[] value = (char[]) val.get(s);
			for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
				value[offset + i] = t.charAt(i);
			}
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
