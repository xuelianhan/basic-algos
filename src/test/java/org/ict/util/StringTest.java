package org.ict.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ict.algorithm.util.StdOut;

public class StringTest {
	private static final String PHONE_REGEX = "(\\d{3})\\d{4}(\\d{4})";
	private static final String PHONE_FORMAT = "$1****$2";
	private String formatPhone(String phone) {
    	if (StringUtils.isBlank(phone)) {
    		return "";
    	}
    	return phone.replaceAll(PHONE_REGEX , PHONE_FORMAT);
	}

	public void testPhones() {
		String phone = "15901486954";
	    String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	    System.out.println(phoneNumber);
	    System.out.println(formatPhone("15901486954"));
	}

	public void testAndMod()  {
		int len = 16;
		for (int i = 0; i < 20; i++) {
			System.out.println(i % (len));
			System.out.println(i & (len - 1));// (i & (len - 1)) equal with (i % (len))
		}
	}
	
	
	
	/**
	 * @see https://stackoverflow.com/questions/12925988/how-to-generate-strings-that-share-the-same-hashcode-in-java
	 * @see https://stackoverflow.com/questions/30163368/two-string-instances-seems-same-but-their-hashcode-are-different
	 * 
	 */
	public void testHashCode() {
		String s = "c265afb8a126d4d9ace17bc951c321ec7e8ce89b85bc64c24ca8f42b00ffd5c3";
		for (int i = 0; i < s.length(); i++) {
			int x = (int)s.charAt(i);
			System.out.print(x);
		}
		System.out.println();
		int hashcode = s.hashCode();
		int mod = hashcode % 45;
		System.out.println("hashcode:" + hashcode);
		System.out.println("mod:" + mod);
		String m = "c265afb8a126d4d9ace17bc951c321ec7e8ce89b85bc64c24ca8f42b00ffd5c3";
	    String n = "c265afb8a126d4d9ace17bc951c321ec7e8ce89b85bc64c24ca8f42b00ffd5c3";

	    System.out.println(m.hashCode());
	    System.out.println(n.hashCode());
	    
	    String a = "success"; 
	    String b = "U+FEFFsuccess"; 
	    System.out.println(a.hashCode()); 
	    System.out.println(b.hashCode());
	    System.out.println(System.identityHashCode(a)); 
	    System.out.println(System.identityHashCode(b));
	}

	public void testList() {
		List<String> list = new ArrayList<String>();
		String s = list.get(0);
		System.out.println(s);
	}
    
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

    public void testNullEquals() {
        String s = null;
        s.equals(null);
    }

    public void testModulus() {
        int t = 2001;
        for (int i = 0; i < t % 2000; i++) {
            System.out.println(i);
        }
    }

    public void testNullCompare() {
        Integer delayMinutes = null; 
        delayMinutes = (delayMinutes == null || delayMinutes < 1) ? 1 : delayMinutes;
        System.out.println(delayMinutes);
    }

    public void testShift() {
        System.out.println(8>>1);
    }

	public void testPalindrome() {
		String s1 = "ABA";
		String s2 = "ABC"; 
		StdOut.println(isPalindrome(s1));
		StdOut.println(isPalindrome(s2));
	}

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

	public void testStrMutate() {
		String s1 = "hello";
		String s2 = s1;
		
		s1 = "world";
		System.out.println(s1);
		System.out.println(s2);
	}

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
