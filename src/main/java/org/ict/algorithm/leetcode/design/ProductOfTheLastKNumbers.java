package org.ict.algorithm.leetcode.design;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.
 *
 * Implement the ProductOfNumbers class:
 *
 * ProductOfNumbers() Initializes the object with an empty stream.
 * void add(int num) Appends the integer num to the stream.
 * int getProduct(int k) Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 * The test cases are generated so that, at any time,
 * the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 *
 *
 *
 * Example:
 *
 * Input
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * Output
 * [null,null,null,null,null,null,20,40,0,null,32]
 *
 * Explanation
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
 * productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
 *
 *
 * Constraints:
 *
 * 0 <= num <= 100
 * 1 <= k <= 4 * 10^4
 * At most 4 * 10^4 calls will be made to add and getProduct.
 * The product of the stream at any point in time will fit in a 32-bit integer.
 * @author sniper
 * @date 23 Aug 2023
 * LC1352, Medium
 */
public class ProductOfTheLastKNumbers {

    public static void main(String[] args) {
        ProductOfNumbers instance = new ProductOfNumbers();
        instance.add(3);
        instance.add(0);
        instance.add(2);
        instance.add(5);
        instance.add(4);
        instance.getProduct(2);
        instance.getProduct(3);
        instance.getProduct(4);
        instance.add(8);
        instance.getProduct(2);
    }


    static class ProductOfNumbers {

        private List<Integer> prefix;

        public ProductOfNumbers() {
            prefix = new ArrayList<>(Arrays.asList(1));
        }

        public void add(int num) {
            if (num == 0) {
                /**
                 * Reset the prefix array to 1 if num is zero.
                 */
                prefix = new ArrayList<>(Arrays.asList(1));
            } else {
                prefix.add(prefix.get(prefix.size() - 1) * num);
            }
        }

        /**
         * e.g. add(2),add(5),add(4)
         * ------------------------
         *  index: 0,1, 2,3
         * prefix: 1,2,10,40
         * getProduct(2) = 5 * 4 = 20 = 40 / prefix.get(4-2-1) = 40 / 2 = 20
         *
         * @param k
         * @return
         */
        public int getProduct(int k) {
            int size = prefix.size();
            if (k >= size) {
                return 0;
            }
            return prefix.get(size - 1) / prefix.get(size - k - 1);
        }
    }

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
}
