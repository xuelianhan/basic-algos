package org.ict.algorithm.strings;

/**
 *
 * 1.4.Write a method to replace all spaces in a stirng with '%20'.You may assume that the
 * string has sufficient space at the end of the string to hold the additional characters,
 * and that you are given the "true" length of the string.(Note:if implementing in Java, 
 * please use a character array so that you can perform this operation in place.)
 *
 *
 *
 */
public class ReplaceSpacesTest {
    
    public static void doReplaceSpaces(char[] array, int lengthNeedCopy, int spaceCount, String replacedStr) {
        int newLen = lengthNeedCopy + (replacedStr.length() - 1) * spaceCount; 
        for (int i = lengthNeedCopy - 1; i >= 0; i--) {
           if (array[i] == ' ') {
               char[] r = replacedStr.toCharArray();
               for (int j = r.length - 1; j >= 0; j--) {
                   array[newLen + j - r.length] = r[j];
               }
               newLen = newLen - r.length;
           } else {
               array[newLen - 1] = array[i]; 
               newLen = newLen - 1; 
           }
        }
    }

    public static char[] replaceSpacesInStr(String str, String replacedStr) {
        int len = str.length();   
        int spaceCount = countSpaces(str);
        int newLen = len + (replacedStr.length() - 1) * spaceCount; 
        char[] array = new char[newLen]; 

        System.arraycopy(str.toCharArray(), 0, array, 0, len);   
         
        doReplaceSpaces(array, len, spaceCount, replacedStr); 
        return array;
    }

    public static int countSpaces(String str) {
        int spaceCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }
    
    public static void main(String[] args) {
        String str = "ha ha ha ";
        char[] a = replaceSpacesInStr(str, "%20");
        System.out.println(String.valueOf(a));
    }

}
