package org.ict.algorithm.leetcode.design;

import java.util.*;

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.
 * Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 *
 * Constraints:
 *
 * 1 <= length <= 5 * 10^4
 * 0 <= index < length
 * 0 <= val <= 10^9
 * 0 <= snap_id < (the total number of times we call snap())
 * At most 5 * 10^4 calls will be made to set, snap, and get.
 * @author sniper
 * @date 05 May 2023
 * LC1146, Medium, frequency=41
 */
public class DesignSnapshotArray {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(2);
        System.out.println(list.size());
        list.set(1, 5);
    }

    /**
     * Understanding the following solution
     *
     * Intuition
     * Instead of copy the whole array,
     * we can only record the changes of set.
     *
     * Explanation
     * The idea is, the whole array can be large,
     * and we may take the snap tons of times.
     * (Like you may always ctrl + S twice)
     *
     * Instead of record the history of the whole array,
     * we will record the history of each cell.
     * And this is the minimum space that we need to record all information.
     *
     * For each A[i], we will record its history.
     * With a snap_id and its value.
     * When we want to get the value in history, just binary search the time point.
     *
     * Complexity
     * Time O(logS)
     * Space O(S)
     * where S is the number of set called.
     *
     * SnapshotArray(int length) is O(N) time
     * set(int index, int val) is O(1) in Python and O(logSnap) in Java
     * snap() is O(1)
     * get(int index, int snap_id) is O(logSnap)
     *
     * @author lee215
     */
    class SnapshotArrayV1 {

        private TreeMap<Integer, Integer>[] arr;

        private int snapId;

        /**
         * Time Complexity O(N)
         * @param length
         */
        public SnapshotArrayV1(int length) {
            arr = new TreeMap[length];
            for (int i = 0; i < length; i++) {
                arr[i] = new TreeMap<>();
                arr[i].put(0, 0);
            }
        }

        /**
         * Time Complexity O(logSnapTimes)
         * ["SnapshotArray","snap","set","set","snap","snap","snap"]
         * [[2],[],[1,17],[0,20],[],[],[]]
         *
         * @param index
         * @param val
         */
        public void set(int index, int val) {
            arr[index].put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        /**
         * Time Complexity O(logSnapTimes)
         * Gets the entry corresponding to the specified key; if no such entry
         * exists, returns the entry for the greatest key less than the specified
         * key; if no such entry exists, returns {@code null}.
         */
        public int get(int index, int snap_id) {
            return arr[index].floorEntry(snap_id).getValue();
        }
    }

    /**
     * Understanding the following solution
     * Time Cost 56ms
     * -----------------------
     * class SnapshotArray {
     * public:
     *     SnapshotArray(int length) {
     *         for (int i = 0; i < length; i++) {
     *             snapMap[i] = {{0, 0}};
     *         }
     *     }
     *
     *     void set(int index, int val) {
     *         snapMap[index][snapId] = val;
     *     }
     *
     *     int snap() {
     *         return snapId++;
     *     }
     *
     *     int get(int index, int snap_id) {
     *         auto iterator = snapMap[index].upper_bound(snap_id);
     *         iterator--;
     *         return iterator->second;
     *     }
     *
     *
     * private:
     *      int snapId = 0;
     *      map<int, map<int, int>> snapMap;
     *
     * };
     * -------------------------------------------
     * class SnapshotArray:
     *
     *     def __init__(self, length: int):
     *         self.snaps = [[[0, 0]] for _ in range(length)]
     *         self.snap_id = 0
     *
     *     def set(self, index: int, val: int) -> None:
     *         snap = self.snaps[index][-1]
     *         if snap[0] == self.snap_id:
     *             snap[1] = val
     *         else:
     *             self.snaps[index].append([self.snap_id, val])
     *
     *
     *     def snap(self) -> int:
     *         self.snap_id += 1
     *         return self.snap_id - 1
     *
     *     def get(self, index: int, snap_id: int) -> int:
     *         i = bisect_left(self.snaps[index], [snap_id + 1]) - 1
     *         return self.snaps[index][i][1]
     *-------------------------------------------
     * For those of you wondering why it's
     * i = bisect.bisect(self.A[index], [snap_id + 1]) - 1
     * because in the case of you modifying a same index several times, such as set(0, 15), set(0,4), set(0,7), snap(), set(0,9)
     * you want to retrieve the last value with the snap_id
     * so you go to snap_id+1, and then -1 to get the last value
     */
    class SnapshotArray {
        private List<int[]>[] arr;

        private int snapId;

        /**
         * Time Complexity O(N)
         * @param length
         */
        public SnapshotArray(int length) {
            arr = new List[length];
            Arrays.setAll(arr, k -> new ArrayList<>());
        }

        /**
         * Time Complexity O(1)
         * Only record the mapping of snapshot version and the updated value
         * instead of the whole array, this method can save a lot of spaces.
         * @param index
         * @param val
         */
        public void set(int index, int val) {
            arr[index].add(new int[]{snapId, val});
        }

        /**
         * Time Complexity O(1)
         * @return
         */
        public int snap() {
            return snapId++;
        }

        /**
         * Time Complexity O(logSnapTimes)
         * e.g.["SnapshotArray","set","snap","set","get"], [[3],[0,5],[],[0,6],[0,0]]
         * [ [[0, 5], [1, 6]], [], [] ]
         *
         * @param index
         * @param snap_id
         * @return
         */
        public int get(int index, int snap_id) {
            List<int[]> list = arr[index];
            int lo = 0;
            /**
             * hi is initialized as list.size() instead of list.size() - 1
             */
            int hi = list.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                /**
                 * Notice here we use > snap_id instead of >= snap_id
                 * Why?
                 * The given snap_id may not exist in the inner list,
                 * and we need to find the first mapping array value that is not greater than the given snap_id,
                 * so we find the first location that is greater than the snap_id,
                 * and then back off by one is the target.
                 */
                if (list.get(mid)[0] > snap_id) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return lo == 0 ? 0 : list.get(lo - 1)[1];
        }

    }

    /**
     * Your SnapshotArray object will be instantiated and called as such:
     * SnapshotArray obj = new SnapshotArray(length);
     * obj.set(index,val);
     * int param_2 = obj.snap();
     * int param_3 = obj.get(index,snap_id);
     */
}
