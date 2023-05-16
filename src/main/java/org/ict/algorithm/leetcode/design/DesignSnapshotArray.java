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
 * Example 1:
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
 * Constraints:
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
        DesignSnapshotArray.SnapshotArrayV1 ss = new DesignSnapshotArray.SnapshotArrayV1(4);
        //[[4],[],[],[3,1],[2,4],[],[1,4]]
        int snapId1 = ss.snap();
        int snapId2 = ss.snap();
        int res1 = ss.get(3, 1);
        ss.set(2, 4);
        int snapId3 = ss.snap();
        ss.set(1, 4);
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
    static class SnapshotArrayV1 {

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
                arr[i].put(0, 0);//Don't forget this initialization.
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
         * ---------------
         * floorEntry(snap_id):
         * Gets the entry corresponding to the specified key; if no such entry
         * exists, returns the entry for the greatest key less than the specified
         * key; if no such entry exists, returns {@code null}.
         * ----------------------------------------------------
         * For those who are trying to understand why floorEntry() method is used in the get method.
         * There is a possibility that snap() call is made multiple times consecutively,
         * in that case we are not updating the snap_id with any value in the TreeMap but just incrementing the snap_id.
         * So if a get call is made with some input snap_id,
         * we need to find the greatest snap_id lower than input snap_id for which an entry was added to the TreeMap.
         * ----------------------------------------------------
         ** e.g. ["SnapshotArray","snap","snap","get","set","snap","set"],
         * [[4],[],[],[3,1],[2,4],[],[1,4]]
         * If you use arr[index].get(snap_id).intValue(); you will get NPE while get(snap_id) return null.
         * 1.new SnapshotArray(4): [{0, 0}, {0, 0}, {0, 0}, {0, 0}]
         * 2.snap(), snapId:1, arr:[{0, 0}, {0, 0}, {0, 0}, {0, 0}]
         * 3.snap(), snapId:2, arr:[{0, 0}, {0, 0}, {0, 0}, {0, 0}]
         * 4.get(3, 1), return 0
         * 5.set(2, 4), arr:[{0, 0}, {0, 0}, {2, 4}, {0, 0}]
         * 6.snap(), snapId:3
         * 7.set(1, 4), arr:[{0, 0}, {1, 4}, {0, 0}, {0, 0}]
         *
         */
        public int get(int index, int snap_id) {
            //Map.Entry<Integer, Integer> entry = arr[index].floorEntry(snap_id);
            //Integer p = arr[index].get(snap_id);//wrong
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
     * because in the case of you modifying a same index several times,
     * such as set(0, 15), set(0,4), set(0,7), snap(), set(0,9)
     * you want to retrieve the last value with the snap_id
     * so you go to snap_id+1, and then -1 to get the last value
     * ------------------------------------------------------------
     * Important Bisection Functions
     * 1. bisect(list, num, beg, end):
     * This function returns the position in the sorted list,
     * where the number passed in argument can be placed to maintain the resultant list in sorted order.
     * If the element is already present in the list,
     * the rightmost position where element has to be inserted is returned.
     * This function takes 4 arguments,
     * list which has to be worked with, a number to insert,
     * starting position in list to consider, ending position which has to be considered.
     *
     * 2. bisect_left(list, num, beg, end):
     * This function returns the position in the sorted list,
     * where the number passed in argument can be placed to maintain the resultant list in sorted order.
     * If the element is already present in the list, the leftmost position where element has to be inserted is returned.
     *
     * This function takes 4 arguments,
     * list which has to be worked with, number to insert,
     * starting position in list to consider, ending position which has to be considered.
     *
     * 3. bisect_right(list, num, beg, end):
     * This function works similar to the “bisect()” and mentioned above.
     */
    static class SnapshotArray {
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
         * get(0, 0)
         * snap_id:0, arr[0] = [[0, 5], [1, 6]]
         * lo:0, hi:2, mid:1, 1 > snap_id, hi:1
         * lo:0, hi:1, mid:0, 0 == snap_id, lo = mid + 1 = 1
         * lo:1, hi:1, while-loop-ended
         * lo:1, list.get(lo-1)=[0,5], return 5
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
                 * for example, we call snap() multiple times consecutively, the snapId is increasing,
                 * but the snapId in the inner array doesn't change,
                 * and we need to find the greatest mapping array value that is not greater than the given snap_id,
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
