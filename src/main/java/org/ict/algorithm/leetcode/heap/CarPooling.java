package org.ict.algorithm.leetcode.heap;

/**
 * There is a car with capacity empty seats.
 * The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity, and an array trips where trips[i] = [numPassengers-i, from-i, to-i]
 * indicates that the ith trip has numPassengers-i passengers,
 * and the locations to pick them up and drop them off, are from-i and to-i respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengers-i <= 100
 * 0 <= from-i < to-i <= 1000
 * 1 <= capacity <= 10^5
 * @author sniper
 * @date 06 Jan, 2023
 * LC1094, frequency=10
 */
public class CarPooling {

    public boolean carPoolingV2(int[][] trips, int capacity) {
        return false;
    }

    public boolean carPoolingV1(int[][] trips, int capacity) {
        return false;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        return false;
    }
}
