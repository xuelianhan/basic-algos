package org.ict.util;

import org.ict.algorithm.util.StdOut;
import org.junit.Test;

public class StringTest {
    
    public void testCharToChinese(String input) {
        String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(nums[Integer.parseInt(Character.valueOf(c).toString())]);
            } else {
                sb.append(Character.valueOf(c));
            }
        }
    }
    
    @Test
    public void testCharAt() {
        String s = "abcdefg";
        for (int i = 0; i < s.length(); i++) {
            System.out.println("i=" + i + ", charAt(" + i + ")=" + charAt(s, i));
        }
    }
    
    // return the dth character of s, -1 if d = length of s
    private int charAt(String s, int d) { 
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }
    
    @Test
    public void testNullEquals() {
        
    }
    
    @Test
    public void testModulus() {
        int t = 2001;
        for (int i = 0; i < t % 2000; i++) {
            System.out.println(i);
        }
    }
    
    @Test
    public void testNullCompare() {
        Integer delayMinutes = null; 
        delayMinutes = (delayMinutes == null || delayMinutes < 1) ? 1 : delayMinutes;
        System.out.println(delayMinutes);
    }
    
    @Test
    public void testShift() {
        System.out.println(8>>1);
    }
	
	@Test
	public void testPalindrome() {
		String s1 = "ABA";
		String s2 = "ABC"; 
		StdOut.println(isPalindrome(s1));
		StdOut.println(isPalindrome(s2));
	}
	
	@Test
	public void testSorted() {
		String[] a = new String[]{"ab", "bc", "ca"};
		StdOut.print(isSorted(a));
	}

    public boolean isPalindrome(String s) {
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
