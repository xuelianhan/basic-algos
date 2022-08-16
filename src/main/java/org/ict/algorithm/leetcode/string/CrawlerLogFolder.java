package org.ict.algorithm.leetcode.string;

import java.util.Stack;

/**
 * he Leetcode file system keeps a log each time some user performs a change folder operation.
 *
 * The operations are described below:
 *
 * "../" : Move to the parent folder of the current folder.
 * (If you are already in the main folder, remain in the same folder).
 * "./" : Remain in the same folder.
 * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 *
 * The file system starts in the main folder, then the operations in logs are performed.
 *
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 *
 * Input: logs = ["d1/","d2/","../","d21/","./"]
 * Output: 2
 * Explanation: Use this change folder operation "../" 2 times and go back to the main folder.
 *
 *
 * Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
 * Output: 3
 *
 *
 * Input: logs = ["d1/","../","../","../"]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= logs.length <= 103
 * 2 <= logs[i].length <= 10
 * logs[i] contains lowercase English letters, digits, '.', and '/'.
 * logs[i] follows the format described in the statement.
 * Folder names consist of lowercase English letters and digits.
 *
 * @author sniper
 * @date 2022/8/15
 * LC1598
 */
public class CrawlerLogFolder {

    public static void main(String[] args) {
        /**
         * Expected 2
         */
        //String[] logs = {"d1/","d2/","../","d21/","./"};

        /**
         * Expected 3
         */
        //String[] logs = {"d1/","d2/","./","d3/","../","d31/"};

        /**
         * Expected 0
         */
        //String[] logs = {"d1/","../","../","../"};
        String[] logs = {"./","../","./"};
        int res = minOperations(logs);
        System.out.println(res);
    }

    public int minOperationsV2(String[] logs) {
        int res = 0;
        for (String s : logs) {
            if ("./".equals(s)) {
                continue;
            } else if ("../".equals(s)) {
                /**
                 * the following codes equals
                 * <code>res = Math.max(0, --res);</code>
                 */
                res--;
                /**
                 * Why? because res's initial value is zero
                 * when operation -- occurs, the value of res might be reduced to negative number
                 * So we need th Math.max(0, res) to amend it.
                 */
                res = Math.max(0, res);
            } else {
                res++;
            }
        }
        return res;
    }

    public static int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String op : logs) {
            if ("./".equals(op)) {
                continue;
            } else if ("../".equals(op)) {
                /**
                 * if-condition here cannot move to the upper if statement
                 * They don't express the same meaning.
                 */
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(op);
            }
        }
        return (stack.isEmpty() ? 0 : stack.size());
    }
}
