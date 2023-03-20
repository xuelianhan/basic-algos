package org.ict.algorithm.leetcode.linkedlist;


import java.util.ArrayDeque;

/**
 * Double-Stack Solution
 * @author sniper
 * @date 20 Mar, 2023
 * LC1472, Medium
 */
public class DesignBrowserHistoryV2 {

    private ArrayDeque<String> history = new ArrayDeque<>();

    private ArrayDeque<String> future = new ArrayDeque<>();

    /**
     * Your DesignBrowserHistory object will be instantiated and called as such:
     * DesignBrowserHistory obj = new DesignBrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */
    public DesignBrowserHistoryV2(String homepage) {
        history.push(homepage);
        future.clear();
    }

    public void visit(String url) {
        history.push(url);
        future.clear();
    }

    public String back(int steps) {
        /**
         * Notice here always keep at least one element in the history stack.
         */
        while (history.size() > 1 && steps > 0) {
            future.push(history.pop());
            steps--;
        }
        return history.peek();
    }

    public String forward(int steps) {
        while (future.size() > 0 && steps > 0) {
            history.push(future.pop());
            steps--;
        }
        return history.peek();
    }
}
