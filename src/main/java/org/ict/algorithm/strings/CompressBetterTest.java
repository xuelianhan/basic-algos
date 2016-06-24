package org.ict.algorithm.strings;
/**
 * Cracking the Coding Interview 1.5.
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3.If the "compressed" string would not become 
 * smaller than the original string, your method should return the original string.You can assumen the string
 * has only upper and lower case letters(a-z).
 * 
 *
 */
public class CompressBetterTest {

    public static String compressBetter(String str) {
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }

        StringBuffer sb = new StringBuffer();
        char last = str.charAt(0);
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (last == str.charAt(i)) {
                count++;
            } else {
                sb.append(last);
                sb.append(count);
                last = str.charAt(i);
                count = 1;
            }
        }
        
        //append the last sequence chars to StringBuffer
        sb.append(last);
        sb.append(count);
        return sb.toString();
    }

    public static int countCompression(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        char last = str.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (last == str.charAt(i)) {
                count++;
            } else {
                last = str.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        //calculate the length of last sequence 
        //e.g. "aabbbcccc", the following code add the "cccc" -> c4 length is 2 to 
        //the result of "aabbb" -> a2b3 length:4
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public static void main(String[] args) {
        String str = "aabbbcccc";
        //System.out.println(countCompression(str));
        System.out.println(compressBetter(str));
    }

}
