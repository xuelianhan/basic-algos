package org.ict.algorithm.sort;

import java.util.Arrays;

/**
 * Counting sort is a sorting technique based on keys between a specific range.
 * It works by counting the number of objects having distinct key values (kind of hashing).
 * Then do some arithmetic to calculate the position of each object in the output sequence.
 *
 * Characteristics of counting sort:
 *
 * 1.Counting sort makes assumptions about the data,
 * for example, it assumes that values are going to be in the range of 0 to 10 or 10 – 99 etc,
 * Some other assumptions counting sort makes are input data will be all real numbers.
 *
 * 2.Like other algorithms this sorting algorithm is not a comparison-based algorithm,
 * it hashes the value in a temporary count array and uses them for sorting.
 *
 * 3.It uses a temporary array making it a non In Place algorithm.
 *
 * Not recommend to use Counting sort when the numbers difference range(max- min) is very large.
 *
 * @see <a href="https://www.geeksforgeeks.org/counting-sort/"></a>
 * @author sniper
 * @date 29 Aug, 2022
 */
public class CountingSort {

    public static void main(String args[]) {
        CountingSort ob = new CountingSort();
        //char[] arr = { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's' };
        //ob.sortV1(arr);

        int[] arr = {1, 4, 1, 2, 7, 5, 2};
        ob.sortV2(arr);

        System.out.print("Sorted array is ");
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i]);
        }
    }


    /**
     * The problem with the previous counting sortV1 and sortV2 was that we could not sort the elements if we have negative numbers in it.
     * Because there are no negative array indices.
     * So what we do is,
     * we find the minimum element and we will store the count of that minimum element at zero index.
     * stable ascending sort
     */
    public static void sortV3(int[] array) {
        int size = array.length;
        int[] minMax = getMinMax(array);
        int min = minMax[0];
        int max = minMax[1];
        int range = max - min + 1;
        int[] counts = new int[range];

        for (int i = 0; i < size; i++) {
            counts[array[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            counts[i] += counts[i-1];
        }

        int[] sortedArray = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            int dataInCountIndex = array[i] - min;
            /**
             * Array index starts at zero,
             * So we subtract 1 here.
             */
            int sortIndex = counts[dataInCountIndex] - 1;
            sortedArray[sortIndex] = array[i];
            counts[dataInCountIndex]--;
        }
    }

    private static int[] getMinMax(int[] array) {
        int min = array[0];
        int max = array[0];
        for(int i = 1; i < array.length; i++) {
            if( array[i] > max ) {
                max = array[i];
            }
            if( array[i] < min ) {
                min = array[i];
            }
        }
        int[] minMax = new int[]{min,max};
        return minMax;
    }


    public void sortV2(int arr[]) {
        int n = arr.length;

        /**
         * The output integer array that will have sorted arr
         */
        int[] output = new int[n];

        /**
         * Create a count array to store count of individual
         * integers and initialize count array as 0
         */
        int count[] = new int[10];
        for (int i = 0; i < 10; ++i) {
            count[i] = 0;
        }

        /**
         * store count of each integer
         */
        for (int i = 0; i < n; ++i) {
            ++count[arr[i]];
        }

        /**
         * Change count[i] so that count[i] now contains actual
         * position of this integer in output array
         * count[i] means the count of less than or equal to number i
         */
        for (int i = 1; i < 10; ++i) {
            count[i] += count[i - 1];
        }
        System.out.println("count before:" + Arrays.toString(count));

        /**
         * Build the output array
         * To make it stable we are operating in reverse order.
         */
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            /**
             * we need put the same number ahead.
             * e.g. original input has two number 1
             * if we put first 1 at index 1, then the second 1 need to put at index 0
             * This is why count[arr[i]]--
             */
            --count[arr[i]];
            System.out.println("output:" + Arrays.toString(output));
            System.out.println("count:" + Arrays.toString(count));
        }

        /**
         * Copy the output array to arr, so that arr now
         * contains sorted integers
         */
        for (int i = 0; i < n; ++i) {
            arr[i] = output[i];
        }
    }

    public void sortV1(char arr[]) {
        int n = arr.length;

        /**
         * The output character array that will have sorted arr
         */
        char output[] = new char[n];

        /**
         * Create a count array to store count of individual
         * characters and initialize count array as 0
         */
        int count[] = new int[256];
        for (int i = 0; i < 256; ++i) {
            count[i] = 0;
        }

        /**
         * store count of each character
         */
        for (int i = 0; i < n; ++i) {
            ++count[arr[i]];
        }

        /**
         * Change count[i] so that count[i] now contains actual
         * position of this character in output array
         */
        for (int i = 1; i <= 255; ++i) {
            count[i] += count[i - 1];
        }
        System.out.println("count:" + Arrays.toString(count));

        /**
         * Build the output character array
         * To make it stable we are operating in reverse order.
         */
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
            System.out.println("output:" + Arrays.toString(output));
            System.out.println("count:" + Arrays.toString(count));
        }

        /**
         * Copy the output array to arr, so that arr now
         * contains sorted characters
         */
        for (int i = 0; i < n; ++i) {
            arr[i] = output[i];
        }

    }
}
