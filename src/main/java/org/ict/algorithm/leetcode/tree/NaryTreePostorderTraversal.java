package org.ict.algorithm.leetcode.tree;

import java.util.*;

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

    public static void main(String[] args) {
        NaryNode five = new NaryNode(5, null);
        NaryNode six = new NaryNode(6, null);
        List<NaryNode> threeChildren = new ArrayList<>();
        threeChildren.add(five);
        threeChildren.add(six);
        NaryNode three = new NaryNode(3, threeChildren);

        NaryNode two = new NaryNode(2, null);
        NaryNode four = new NaryNode(4, null);
        List<NaryNode> rootChildren = new ArrayList<>();
        rootChildren.add(three);
        rootChildren.add(two);
        rootChildren.add(four);
        NaryNode root = new NaryNode(1, rootChildren);
        List<Integer> result = postOrder(root);
        System.out.println(result);
    }


    /**
     * left -> middle ... right -> root
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrder(NaryNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (null == root) {
            return list;
        }
        Stack<NaryNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
                NaryNode cur = stack.pop();
                //add from head of linked list
                list.addFirst(cur.val);
                if (cur.children != null) {
                    for (int i = 0; i <= cur.children.size() - 1; i++) {
                        stack.push(cur.children.get(i));
                    }
                }
        }
        return list;
    }

}
