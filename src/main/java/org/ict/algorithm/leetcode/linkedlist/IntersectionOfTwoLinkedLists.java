package org.ict.algorithm.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 *
 * The inputs to the judge are given as follows (your program is not given these inputs):
 *
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads,
 * headA and headB to your program.
 * If you correctly return the intersected node, then your solution will be accepted.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1
 * because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references.
 * In other words, they point to two different locations in memory,
 * while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
 * Example 2:
 *
 *
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4].
 * There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 * Example 3:
 *
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5].
 * Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 *
 * Constraints:
 *
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 *
 *
 * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 * @author sniper
 * @date 2022/8/23
 * LC160, Easy
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * Solution provided by MyFavCat
     *
     * I found most solutions here preprocess linked lists to get the difference in len.
     * Actually we don't care about the "value" of difference,
     * we just want to make sure two pointers reach the intersection node at the same time.
     *
     * We can use two iterations to do that.
     * In the first iteration, we will reset the pointer of one linkedlist to the head
     * of another linkedlist after it reaches the tail node.
     * In the second iteration, we will move two pointers until they point to the same node.
     * Our operations in first iteration will help us counteract the difference.
     * So if two linkedlist intersects,
     * the meeting point in second iteration must be the intersection point.
     * If the two linked lists have no intersection at all,
     * then the meeting pointer in second iteration must be the tail node of both lists, which is null
     *
     * see Visualization provided by BryanBoCao
     * Case 1 (Have Intersection & Same Len)
     *
     * Case 2 (Have Intersection & Different Len)
     *
     * Case 3 (Have No Intersection & Same Len)
     *
     * Case 4 (Have No Intersection & Different Len)
     * Notice that if list A and list B have the same length,
     * this solution will terminate in no more than 1 traversal;
     * if both lists have different lengths,
     * this solution will terminate in no more than 2 traversals -- in the second traversal,
     * swapping a and b synchronizes a and b before the end of the second traversal.
     * By synchronizing a and b I mean both have the same remaining steps in the second traversal
     * so that it's guaranteed for them to reach the first intersection node,
     * or reach null at the same time (technically speaking, in the same iteration)
     * -- see Case 2 (Have Intersection & Different Len)
     * and Case 4 (Have No Intersection & Different Len).
     *
     * PS: There are many great explanations of this solution for various cases,
     * I believe to visualize it can resolve most of the doubts posted previously.
     *
     * Below is my commented Java code:
     *
     * @see org.ict.algorithm.leetcode.tree.LowestCommonAncestorOfBTIII
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = (a == null ? headB : a.next);
            b = (b == null ? headA : b.next);
        }
        return a;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Map<ListNode, Boolean> visited = new HashMap<>();
        ListNode a = headA;
        ListNode b = headB;
        while (a != null) {
            if (visited.getOrDefault(a, false) == false) {
                visited.put(a, true);
            }
            a = a.next;
        }

        while (b != null) {
            if (visited.getOrDefault(b, false) == false) {
                visited.put(b, true);
                b = b.next;
            } else {
                return b;
            }
        }
        return null;
    }

    private static class ListNode {
        int val;

        boolean visited;

        ListNode next;

        ListNode(){}

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }
}
