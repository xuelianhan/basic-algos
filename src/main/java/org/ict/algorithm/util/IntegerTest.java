package org.ict.algorithm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IntegerTest {

    public static void testBigDecimal() {
        BigDecimal r = new BigDecimal(2596);
        //BigDecimal r = new BigDecimal(0);
        BigDecimal d = new BigDecimal(100);
        BigDecimal result = r.divide(d, 2, RoundingMode.HALF_UP);
        System.out.println(result);
    }
    
    public static void testNullPointer() {
        Integer a = null;
        
        //java.lang.NullPointerException
        int b = a;
        //java.lang.NullPointerException
        
        System.out.println("World is OK...");
    }
    
    public static void main(String[] args) {
        
        testBigDecimal();
    }

}
