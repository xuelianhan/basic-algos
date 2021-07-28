package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 *
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 *
 * @author hxl
 * @date 2021/5/18 6:23 PM
 * lc590
 */
public class NaryTreePostorderTraversal {



    public List<Integer> postOrderTraversalV2(NaryNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (null == root) {
            return list;
        }
        Stack<NaryNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NaryNode cur = stack.pop();
            if (cur != null) {
                //middle
                list.addFirst(cur.val);
                if (cur.children != null) {
                    for (int i = cur.children.size() - 1; i >= 0; i--) {
                        stack.push(cur.children.get(i));
                    }
                }
            }
        }
        return list;
    }
}
