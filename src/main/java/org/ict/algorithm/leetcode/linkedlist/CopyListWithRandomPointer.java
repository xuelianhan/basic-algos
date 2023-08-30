package org.ict.algorithm.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list
 * such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example,
 * if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 *
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 *
 *
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 *
 * Constraints:
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 * @author sniper
 * @date 15 Sep, 2022
 * LC138, Medium
 * Amazon
 */
public class CopyListWithRandomPointer {

    /**
     * Solution provided by liaison
     *
     * An intuitive solution is to keep a hash table for each node in the list,
     * via which we just need to iterate the list in 2 rounds respectively to create nodes and assign the values
     * for their random pointers.
     * As a result, the space complexity of this solution is O(N),
     * although with a linear time complexity.
     *
     * Note: if we do not consider the space reversed for the output,
     * then we could say that the algorithm does not consume any additional memory during the processing,
     * i.e. O(1) space complexity
     *
     * As an optimised solution, we could reduce the space complexity into constant.
     * The idea is to associate the original node with its copy node in a single linked list.
     * In this way, we don't need extra space to keep track of the new nodes.
     *
     * The algorithm is composed of the follow three steps which are also 3 iteration rounds.
     *
     * 1.Iterate the original list and duplicate each node. The duplicate
     * of each node follows its original immediately.
     *
     * 2.Iterate the new list and assign the random pointer for each
     * duplicated node.
     *
     * 3.Restore the original list and extract the duplicated nodes.
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param head
     * @return
     */
    public Node copyRandomListV2(Node head) {
        if (null == head) {
            return null;
        }
        /**
         * First round, make copy of each node,
         * and link the original node with the copy node one-by-one.
         */
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;

            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;

            cur = next;
        }

        /**
         * Second round, assign random pointers for the copy nodes.
         */
        cur = head;
        Node copy = null;
        while (cur != null) {
            copy = cur.next;
            if (cur.random != null) {
                /**
                 * here may easily get wrong with following code:
                 * copy.random = cur.random;
                 */
                copy.random = cur.random.next;
            }
            cur = copy.next;
        }

        /**
         * Third round, restore the original list, and extract the copy list.
         * The following codes can solve LC328(Odd Even Linked List).
         */
        cur = head;
        copy = null;
        Node dummy = new Node(0);
        Node copyPre = dummy;
        while (cur != null) {
            next = cur.next.next;

            /**
             * Extract the copy list
             */
            copy = cur.next;
            copyPre.next = copy;
            copyPre = copy;

            /**
             * Restore the original list
             */
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }

    /**
     * Using HashMap to store the relation between original node and the copied node.
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        /**
         * Using a map to store the relation of original Node and the copied Node.
         * Create each node and copy the data.
         */
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        /**
         * Copy the next pointer and random pointer one by one.
         */
        cur = head;
        while (cur != null) {
            Node copy = map.get(cur);
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }


    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
