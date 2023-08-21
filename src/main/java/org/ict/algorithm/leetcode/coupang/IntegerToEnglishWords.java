package org.ict.algorithm.leetcode.coupang;


import java.util.HashMap;
import java.util.Map;

/**
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Constraints:
 * 0 <= num <= 2^31 - 1
 * @author sniper
 * @date 14 Jun 2023
 * LC273, Hard, frequency=12
 */
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        IntegerToEnglishWords instance = new IntegerToEnglishWords();
        String res = instance.numberToWords(1_234_567);
        System.out.println(res);
    }

    /**
     * Understanding the following Solution
     * Time Cost 2ms
     * @param num
     * @return
     */
    public String numberToWordsV2(int num) {
        return num == 0 ? "Zero" : helper(num);
    }

    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String helper(int num) {
        StringBuilder s = new StringBuilder();
        if (num < 20)
            s.append(belowTwenty[num]);
        else if (num < 100)
            s.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        else if (num < 1000)
            s.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        else if (num < 1_000_000)
            s.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
        else if (num < 1_000_000_000)
            s.append(helper(num / 1_000_000)).append(" Million ").append(helper(num % 1_000_000));
        else
            s.append(helper(num / 1_000_000_000)).append(" Billion ").append(helper(num % 1_000_000_000));

        return s.toString().trim();
    }

    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
        map.put(1000, "Thousand");
        map.put(1_000_000, "Million");
        map.put(1_000_000_000, "Billion");
    }

    public String numberToWordsV1(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1000000000; i >= 1000; i /= 1000) {
            if (num >= i) {
                sb.append(get3Digits(num / i)).append(' ').append(map.get(i));
                num %= i;
            }
        }
        if (num > 0) {
            sb.append(get3Digits(num));
        }
        return sb.substring(1);
    }

    private String get3Digits(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(' ').append(map.get(num / 100)).append(' ').append(map.get(100));
            num %= 100;
        }
        if (num > 0) {
            if (num < 20 || num % 10 == 0) {
                sb.append(' ').append(map.get(num));
            } else {
                sb.append(' ').append(map.get(num / 10 * 10)).append(' ').append(map.get(num % 10));
            }
        }
        return sb.toString();
    }

    /**
     * todo
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        StringBuilder convert = new StringBuilder();
        int cnt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            convert.append(arr[i]);
            cnt++;
            if (cnt % 3 == 0 && i > 0) {
                convert.append(",");
            }
        }
        String s = convert.reverse().toString();
        String[] split = s.split(",");
        //todo
        StringBuilder res = new StringBuilder();
        return res.toString();
    }
}
