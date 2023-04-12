package org.ict.algorithm.leetcode.stack;

import java.util.*;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system,
 * convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory
 * to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 *
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 * @author sniper
 * @date 12 Apr, 2023
 * LC71, Medium, frequency=112
 */
public class SimplifyPath {

    public static void main(String[] args) {
        String path = "/home//foo/";
        SimplifyPath instance = new SimplifyPath();
        String res = instance.simplifyPathV2(path);
        System.out.println(res);
    }

    /**
     * def simplifyPath(self, path: str) -> str:
     *         stack = []
     *         for x in path.split('/'):
     *             if '..' == x and stack:
     *                 stack.pop()
     *             elif x not in ['', '.', '..']:
     *                 stack.append(x)
     *         return '/' + '/'.join(stack)
     * @param path
     * @return
     */
    public String simplifyPathV2(String path) {
        /**
         * Notice using Stack here instead of ArrayDeque
         * e.g. "/home//foo/",
         * stack[0]:home
         * stack[1]:foo
         * Why? Stack implements based on Vector.
         * stack.push(item) invoke vector.addElement to add item at the end of the vector.
         */
        Stack<String> stack = new Stack<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if ("..".equals(s) && !stack.isEmpty()) {
                stack.pop();
            } else if (!"".equals(s) && !".".equals(s) && !"..".equals(s)) {
                stack.push(s);
            }
        }
        /**
         * Because Stack add item at the end of the vector, so we can access from 0 to N directly
         * without reversing.
         */
        return "/" + String.join("/", stack);
    }

    public String simplifyPathV1(String path) {
        /**
         * Notice using ArrayDeque here instead of Stack
         * e.g. "/home//foo/",
         * stack[0]:foo
         * stack[1]:home
         * Why?
         * Because Deque.push(item) always push the item at the head of the queue.
         * Deque.pop() always pop the first element of the queue.
         */
        Deque<String> stack = new ArrayDeque<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if ("..".equals(s) && !stack.isEmpty()) {
                stack.pop();
            } else if (!"".equals(s) && !".".equals(s) && !"..".equals(s)) {
                stack.push(s);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, "/" + stack.pop());
        }
        return res.length() == 0 ? "/" : res.toString();
    }


    /**
     * e.g. path="/../", expected "/"
     * e.g. path="/home//foo/", expected "/home/foo"
     * e.g. path="/home/", expected "/home"
     * e.g. path="/a/./b/../../c/", expected "/c"
     *
     * Notice the following codes can not write as:
     * if (!stack.isEmpty()) {
     *     if ("..".equals(s)) {
     *         stack.pop();
     *     }
     * } else if (!"".equals(s) && !".".equals(s) && !"..".equals(s)) {
     *     stack.push(s);
     * }
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!"".equals(s) && !".".equals(s) && !"..".equals(s)) {
                stack.push(s);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, "/" + stack.pop());
        }
        return res.length() == 0 ? "/" : res.toString();
    }

}
