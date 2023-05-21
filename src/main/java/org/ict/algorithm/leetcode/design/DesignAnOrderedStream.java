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
     * --------------------------------
     *
     * class OrderedStream(_n: Int) {
     *
     *     private var arr: Array[String] = new Array[String](_n)
     *     private var idx: Int = 0
     *
     *     def insert(idKey: Int, value: String): List[String] = {
     *         val res = scala.collection.mutable.ListBuffer[String]()
     *         arr(idKey - 1) = value
     *         while (idx < arr.length && arr(idx) != null) {
     *             res += arr(idx)
     *             idx += 1
     *         }
     *         res.toList
     *     }
     * }
     * var obj = new OrderedStream(n)
     * var param_1 = obj.insert(idKey,value)
     * ----------------------------
     * use std::convert::TryInto;
     *
     * struct OrderedStream {
     *     arr: Vec<String>,
     *     idx: usize,
     * }
     *
     * impl OrderedStream {
     *     fn new(n: i32) -> Self {
     *         OrderedStream {
     *             arr: vec![String::new(); n.try_into().unwrap()],
     *             idx: 0,
     *         }
     *     }
     *
     *     fn insert(&mut self, id_key: i32, value: String) -> Vec<String> {
     *         let mut res = Vec::new();
     *         self.arr[id_key as usize - 1] = value;
     *         while self.idx < self.arr.len() && !self.arr[self.idx].is_empty() {
     *             res.push(self.arr[self.idx].clone());
     *             self.idx += 1;
     *         }
     *         res
     *     }
     * }
     *
     * #[test]
     * fn test_ordered_stream() {
     *     let mut stream = OrderedStream::new(3);
     *     stream.insert(1, "a");
     *     stream.insert(2, "b");
     *     stream.insert(3, "c");
     *     assert_eq!(stream.insert(1, "d"), vec!["a", "b", "c", "d"]);
     * }
     * #
     * The arr field is a Vec<String>, and the String type does not implement the Copy trait.
     * This means that when you try to index the arr field, the value at the index will be moved into the res vector.
     * However, the String type is a heap-allocated type,
     * and moving it will invalidate the reference in the arr field.
     * To fix this, you can clone the value at the index before pushing it into the res vector.
     * This will create a new,
     * independent copy of the value, which can be safely pushed into the res vector
     * without invalidating the reference in the arr field
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