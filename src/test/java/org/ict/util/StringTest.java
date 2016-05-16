package org.ict.util;

import org.junit.Test;

public class StringTest {

    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i = 0; i < N/2; i++) {
            if (s.charAt(i) != s.charAt(N - 1 - i)) {
                return false;
            }
        }
        return true; 
    }

    public boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
      
	@Test
	public void testStrMutate() {
		String s1 = "hello";
		String s2 = s1;
		
		s1 = "world";
		System.out.println(s1);
		System.out.println(s2);
	}
	
	@Test
	public void testStrCircularRotation() {
		String s = "ABCDDAB";
		String t = "CDDABAB";
		System.out.println(circularRotate(s, t));
	}
	
	public boolean circularRotate(String s, String t) {
		boolean flag = false;
		if (s.length() == t.length()) {
			int idx = s.concat(s).indexOf(t);
			if (idx >= 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	public static String reverseStr(final String s) {
		if (s == null) {
			return null;
		}
		return new StringBuilder(s).reverse().toString();
	}
}
