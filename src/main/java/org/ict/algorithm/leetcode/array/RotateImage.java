package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.ict.algorithm.sort.AbstractSortHelper;


/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, 
 * which means you have to modify the input 2D matrix directly. 
 * DO NOT allocate another 2D matrix and do the rotation.
 *  Example 1:
 *  Given input matrix = 
 *  [
 *    [1,2,3],
 *    [4,5,6],
 *    [7,8,9]
 *  ],
 *  rotate the input matrix in-place such that it becomes:
 *  [
 *    [7,4,1],
 *    [8,5,2],
 *    [9,6,3]
 *  ]
 *  Example 2:
 *  
 *  Given input matrix =
 *  [
 *    [ 5, 1, 9,11],
 *    [ 2, 4, 8,10],
 *    [13, 3, 6, 7],
 *    [15,14,12,16]
 *  ], 
 *  
 *  rotate the input matrix in-place such that it becomes:
 *  [
 *    [15,13, 2, 5],
 *    [14, 3, 4, 1],
 *    [12, 6, 8, 9],
 *    [16, 7,10,11]
 *  ]
 *
 *
 */
public class RotateImage {

	public static void main(String[] args) {
		
	}
	
	/**
	 * clockwise rotate
	 * first reverse up to down, then swap the symmetry
	 * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
	 */
	public static void rotateV1(int[][] matrix) {
		for (int col = 0; col < matrix[0].length; col++) {
			AbstractSortHelper.reverse(matrix[][col]);
		}
	}
	
	/**
	 * anti-clockwise rotate
	 * first reverse left to right, then swap the symmetry
	 * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
	 * @param matrix
	 */
	public static void antiRotateV1(int[][] matrix) {
		
	}
	
}
