package org.ict.algorithm.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a stream of n (idKey, value) pairs arriving in an arbitrary order,
 * where idKey is an integer between 1 and n and value is a string.
 * No two pairs have the same id.
 * Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion.
 * The concatenation of all the chunks should result in a list of the sorted values.
 * Implement the OrderedStream class:
 *
 * OrderedStream(int n) Constructs the stream to take n values.
 * String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream,
 * then returns the largest possible chunk of currently inserted values that appear next in the order.
 *
 * Example:
 * Input
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * Output
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 *
 * Explanation
 * // Note that the values ordered by ID is ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"].
 * OrderedStream os = new OrderedStream(5);
 * os.insert(3, "ccccc"); // Inserts (3, "ccccc"), returns [].
 * os.insert(1, "aaaaa"); // Inserts (1, "aaaaa"), returns ["aaaaa"].
 * os.insert(2, "bbbbb"); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
 * os.insert(5, "eeeee"); // Inserts (5, "eeeee"), returns [].
 * os.insert(4, "ddddd"); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
 * // Concatentating all the chunks returned:
 * // [] + ["aaaaa"] + ["bbbbb", "ccccc"] + [] + ["ddddd", "eeeee"] = ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"]
 * // The resulting order is the same as the order above.
 *
 * Constraints:
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value consists only of lowercase letters.
 * Each call to insert will have a unique id.
 * Exactly n calls will be made to insert.
 * @author sniper
 * @date 16 May 2023
 * LC1656, Easy, frequency=20
 */
public class DesignAnOrderedStream {

    /**
     * Rules for Global and Local variables in Python
     * To avoid UnboundLocalError you should also know the general guidelines of local and global variables in Python.
     * 1.In Python, variables that are only referenced inside a function are implicitly global.
     * 2.If the value is assigned to a variable inside a function,
     * then the scope changes to local unless it's explicitly declared as global.
     * ----------------------------------------------------
     * Conclusion
     * In Python, UnboundLocalError: local variable referenced before assignment occurs
     * when you reference a local variable before assigning any value to it inside a function.
     * To solve the error, you need to make it a global variable inside a function or pass the
     * global variable as a function argument to the method.
     * -----------------------------------------------------
     * class OrderedStream:
     *
     *     def __init__(self, n: int):
     *         self.arr = [None] * n
     *         self.idx = 0
     *
     *
     *     def insert(self, idKey: int, value: str) -> List[str]:
     *         res = []
     *         self.arr[idKey - 1] = value
     *         while self.idx < len(self.arr) and self.arr[self.idx]:
     *             res.append(self.arr[self.idx])
     *             self.idx += 1
     *         return res
     * ------------------------------------------------------------
     * class OrderedStream {
     * public:
     *     vector<string> arr;
     *     int idx = 0;
     *
     *     OrderedStream(int n) {
     *         arr.resize(n, "");
     *     }
     *
     *     vector<string> insert(int idKey, string value) {
     *         arr[idKey - 1] = value;
     *         vector<string> res;
     *         while (idx < arr.size() && arr[idx] != "") {
     *             res.push_back(arr[idx]);
     *             idx++;
     *         }
     *         return res;
     *     }
     * };
     *
     *
     * Your OrderedStream object will be instantiated and called as such:
     * OrderedStream* obj = new OrderedStream(n);
     * vector<string> param_1 = obj->insert(idKey,value);
     *
     */
    static class OrderedStream {
        private String[] arr;
        private int idx;

        public OrderedStream(int n) {
            arr = new String[n];
        }

        public List<String> insert(int idKey, String value) {
            List<String> res = new ArrayList<>();
            arr[idKey - 1] = value;
            while (idx < arr.length && arr[idx] != null) {
                res.add(arr[idx]);
                idx++;
            }
            return res;
        }
    }

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
}