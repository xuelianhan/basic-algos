package org.ict.algorithm.fundamentals;

import java.util.Arrays;
import java.util.List;

/**
 * You can use an upper bounded wildcard to relax the restrictions on a variable. 
 * For example, say you want to write a method that works on List<Integer>, List<Double>, and List<Number>; 
 * you can achieve this by using an upper bounded wildcard.
 * To declare an upper-bounded wildcard, use the wildcard character ('?'), followed by the extends keyword, 
 * followed by its upper bound. 
 * Note that, in this context, extends is used in a general sense to mean either "extends" (as in classes) or "implements" (as in interfaces).
 * To write the method that works on lists of Number and the subtypes of Number, such as Integer, Double, and Float, 
 * you would specify List<? extends Number>. 
 * The term List<Number> is more restrictive than List<? extends Number> because the former matches a list of type Number only, 
 * whereas the latter matches a list of type Number or any of its subclasses.
 * @see https://docs.oracle.com/javase/tutorial/java/generics/upperBounded.html
 *
 */
public class GenericUpperBoundDemo {

    public static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }
    
    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1, 2, 3);
        System.out.println("sum = " + sumOfList(li));

        List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("sum = " + sumOfList(ld));
    }
}
