package org.ict.algorithm.leetcode.graph;


import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [from-i, to-i]
 * represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * All the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries,
 * you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary.
 * You must use all the tickets once and only once.
 *
 * Example 1:
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * Constraints:
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * from-i.length == 3
 * to-i.length == 3
 * from-i and toi consist of uppercase English letters.
 * from-i != to-i
 *
 * @author sniper
 * @date 12 Jun 2023
 *
 * This Problem is similar with
 * {@link org.ict.algorithm.leetcode.breadthfirstsearch.CourseSchedule}
 * {@link org.ict.algorithm.leetcode.breadthfirstsearch.CourseScheduleTwo}
 * LC332, Hard, frequency=16
 */
public class ReconstructItinerary {


    /**
     * Iterative Solution
     *
     * @see <a href="https://leetcode.com/problems/reconstruct-itinerary/solutions/78768/short-ruby-python-java-c/"></a>
     * @author StefanPochmann
     * @param tickets
     * @return
     */
    public List<String> findItineraryV1(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        Map<String, Queue<String>> graph = new HashMap();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, a -> new PriorityQueue<>()).add(to);
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()) {
                stack.push(graph.get(stack.peek()).poll());
            }
            res.addFirst(stack.pop());
        }
        return res;
    }

    /**
     * Depth-First-Search Solution
     * -----------------------
     * All the airports are vertices and tickets are directed edges.
     * Then all these tickets form a directed graph.
     * The graph must be Eulerian since we know that a Eulerian path exists.
     * Thus, start from "JFK", we can apply the Hierholzer's algorithm
     * to find an Eulerian path in the graph which is a valid reconstruction.
     *
     * Since the problem asks for lexical order the smallest solution,
     * we can put the neighbors in a min-heap.
     * In this way, we always visit the smallest possible neighbor first in our trip.
     * -------------------------------------------------
     * Actually, in this problem we are asked to find Euler path, smallest lexically.
     * There is classical algorithm with complexity O(E).
     * Starting from the starting vertex v,
     * we build a path by adding at each step an edge that has not yet been passed and is adjacent to the current vertex.
     * The vertices of the path are accumulated in stack S.
     * When the moment comes when for the current node w all the incident edges have already passed,
     * we write the vertices from S in output until we meet the node where the incident has not passed yet edges.
     * Then we continue our traversal of the unattended edges.
     * It can be written both with recursion or with stack, recursion version is shorter.
     *
     * Here is a link, where you can plunge deeper into this:
     * http://www.graph-magics.com/articles/euler.php
     *
     * If you neves saw this problem and even if you know what Euler path is,
     * I think it is almost impossible to invent this algorithm by yourself,
     * and this problem should be marked as hard.
     *
     * Complexity: time and space complexity of usual Euler Path Finding algorighm is O(E+V) = O(E), because we traverse each edge only once and number of edges is more than number of vertixes - 1 in Eulerian graph. However as @ainkartik203 mentioned, here we sort our list for every node, so complexity will be O(E log E).
     * @see <a href="https://leetcode.com/problems/reconstruct-itinerary/solutions/709590/python-short-euler-path-finding-o-e-log-e-explained/"></a>
     * @see <a href="https://leetcode.com/problems/reconstruct-itinerary/solutions/78766/share-my-solution/"></a>
     * @author DBabichev
     * @author dietpepsi
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        Map<String, Queue<String>> graph = new HashMap();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, a -> new PriorityQueue<>()).add(to);
        }
        dfs(graph, "JFK", res);
        return res;
    }

    private void dfs(Map<String, Queue<String>> graph, String from, LinkedList<String> res) {
        Queue<String> arrivals = graph.get(from);
        /**
         * Notice arrivals != null here.
         * We have to travel to all its neighbors starting from the smallest one lexicographically.
         */
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(graph, arrivals.poll(), res);
        }
        res.addFirst(from);
    }

}
