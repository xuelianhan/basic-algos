package org.ict.algorithm.leetcode.linkedlist;

/**
 * press ⌥ ⌘ L. to format code
 *
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle.
 * Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 * The number of the nodes in the list is in the range [0, 104].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 *
 * @author sniper
 * @date 16 Sep, 2022
 * LC142, Medium
 */
public class LinkedListCycleII {

    /**
     * Floyd's algorithm
     *
     * To understand this solution, you just need to ask yourself these question.
     * Assume the distance from head to the start of the loop is x1
     * the distance from the start of the loop to the point where fast and slow meet is x2
     * the distance from the point where fast and slow meet to the start of the loop is x3
     * What is the distance fast moved?
     * What is the distance slow moved? And their relationship?
     *        x1                  x2                    x3
     * head----->loop-start>----------->meet-point----------------->
     *           ^                                                 |
     *           |                                                 |
     *           |                                                 V
     *           <-----------------<---------------<----------------
     *
     *
     * x1 + x2 + x3 + x2
     * x1 + x2
     * x1 + x2 + x3 + x2 = 2 (x1 + x2)
     * Thus x1 = x3
     *
     * Finally, got the idea.
     * You can draw out a graph to show this relationship.
     *
     * @param head
     * @return
     */
    public ListNode detectCycleV2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                /**
                 * when fast and slow come across,
                 * we can let slow start back at head,
                 * and move fast, slow step one by one until they come across again.
                 * the slow point at the start of the circle.
                 */
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
       return null;
    }


    /**
     * Floyd's algorithm
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
