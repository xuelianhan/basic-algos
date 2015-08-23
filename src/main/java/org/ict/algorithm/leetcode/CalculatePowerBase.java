package org.ict.algorithm.leetcode;

public class CalculatePowerBase {
 
  public static long pow(long base , long exponent) {
    if (exponent == 0 ) {
      return 0;
    }
    if (exponent == 1 ) {
      return base;
    }
    if (exponent < 0) {
      base = 1/base;
      exponent = -exponent;
    }
    long ret = pow(base, exponent/2);
    if (exponent % 2 == 0) {
      return ret * ret;
    } else {
      return ret * ret * base;
    }
  }
  
  public static void main(String[] args) {
    System.out.println(Long.MAX_VALUE);
    String temp = "";
    int j = 0;
    int max = 1024;
    for (int i = 0; i < max; i++) {
      if ((i + 1) < 63) {
        System.out.println("" + (i + 1) + " " + pow(2, (i + 1)));
        temp = pow(2, (i + 1))+"";
        j = i + 1;
      } else {
        break;
      }
    }
    for (int k = 0; k < (max - j); k++) {
      StringBuilder sb = new StringBuilder();
      int q = 0;
      int r = 0;
      for (int l = temp.length() - 1; l >= 0; l--) {
         int ti = Character.getNumericValue(temp.charAt(l)); 
         int t = ti * 2; 
         r = (t+q)%10;
         q = (t+q)/10;
         sb.append(r);
      }
      if (q > 0) {
         sb.append(q);
      }
      String x = sb.toString();
      StringBuilder nsb = new StringBuilder();
      for (int m = x.length() - 1; m >=0; m--) {
        nsb.append(x.charAt(m)); 
      }
      temp = nsb.toString();
      System.out.println("" + (j + k + 1) + " " + temp);
    }
  }

}
