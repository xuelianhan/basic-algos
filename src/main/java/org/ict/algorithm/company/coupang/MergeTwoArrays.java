package org.ict.algorithm.company.coupang;

/**
 * I have two arrays. One contains even numbers and the other contains odd numbers.
 * Which sorting algorithm would you use?
 * -------------------
 * There are two sorting algorithms that I would use for this problem:
 *
 * Merge sort: This is a divide-and-conquer algorithm that works by recursively splitting the arrays into smaller and smaller sub-arrays until they are sorted.
 * Then, the sorted sub-arrays are merged together.
 * Merge sort is a stable sorting algorithm, which means that the relative order of equal elements is preserved.
 * This is important for our problem, because we want to keep the even and odd numbers in their respective arrays.
 * Quicksort: This is another divide-and-conquer algorithm that works by randomly selecting an element from the array,
 * and partitioning the array around that element.
 * The elements smaller than the selected element are placed in one sub-array,
 * and the elements larger than the selected element are placed in another sub-array.
 * The quicksort algorithm is not a stable sorting algorithm, but it is generally faster than merge sort for large arrays.
 * I would choose the merge sort algorithm if the arrays are small, because it is a stable sorting algorithm.
 * I would choose the quicksort algorithm if the arrays are large, because it is generally faster than merge sort.
 *
 * Here are some other sorting algorithms that you could use:
 *
 * Bubble sort: This is a simple sorting algorithm that works by repeatedly comparing adjacent elements and swapping them if they are in the wrong order.
 * Bubble sort is not a very efficient sorting algorithm, but it is easy to implement.
 * Selection sort: This is another simple sorting algorithm that works by repeatedly finding the smallest element in the array and swapping it with the first element.
 * Selection sort is not as efficient as bubble sort, but it is still easy to implement.
 * Insertion sort: This is a sorting algorithm that works by repeatedly inserting elements into their correct position in the sorted part of the array.
 * Insertion sort is a stable sorting algorithm, but it is not as efficient as merge sort or quicksort.
 * The best sorting algorithm to use depends on the specific problem you are trying to solve.
 * You should consider the size of the arrays, the desired speed of the sorting algorithm,
 * and whether you need a stable sorting algorithm or not.
 * @author sniper
 * @date 17 Aug 2023
 */
public class MergeTwoArrays {

    /**
     * This code first creates a new array to store the sorted elements.
     * The code then iterates over the elements in the even and odd arrays.
     * For each element, the code checks if the even index is less than the length of the even array and the odd index is less than the length of the odd array.
     * If both conditions are true, then the code checks if the current element in the even array is less than the current element in the odd array.
     * If it is, then the code stores the current element in the even array in the sorted array.
     * Otherwise, the code stores the current element in the odd array in the sorted array.
     *
     * The code then increments the even index or the odd index,
     * depending on which array the current element was stored in.
     *
     * The code finally returns the sorted array.
     * @param even
     * @param odd
     * @return
     */
    public int[] sortTwoArrays(int[] even, int[] odd) {
        int[] sorted = new int[even.length + odd.length];
        int evenIndex = 0;
        int oddIndex = 0;

        for (int i = 0; i < sorted.length; i++) {
            if (evenIndex < even.length && oddIndex < odd.length) {
                if (even[evenIndex] < odd[oddIndex]) {
                    sorted[i] = even[evenIndex++];
                } else {
                    sorted[i] = odd[oddIndex++];
                }
            } else if (evenIndex < even.length) {
                sorted[i] = even[evenIndex++];
            } else {
                sorted[i] = odd[oddIndex++];
            }
        }

        return sorted;
    }
}
