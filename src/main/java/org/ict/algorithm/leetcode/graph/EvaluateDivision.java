package org.ict.algorithm.leetcode.graph;

import java.util.*;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries,
 * where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries.
 * If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid.
 * You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 * @author sniper
 * @date 10 May 2023
 * LC399, Medium, frequency=25
 */
public class EvaluateDivision {

    /**
     * Union-Find Solution
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationV2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //todo
        return null;
    }


    /**
     * Breadth-First-Search Solution
     * Time Cost 2ms
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationV1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = queries.size();
        double[] res = new double[n];

        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        for (int i = 0; i < queries.size(); i++) {
            List<String> pair = queries.get(i);
            String start = pair.get(0);
            String end = pair.get(1);
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                res[i]  = -1.0;
                continue;
            }

            Queue<Pair> queue = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            queue.offer(new Pair(start, 1.0));
            visited.add(start);
            boolean found = false;
            while (!queue.isEmpty() && !found) {
                for (int j = queue.size(); j > 0; j--) {
                    Pair cur = queue.poll();
                    if (cur.point.equals(end)) {
                        found = true;
                        res[i] = cur.value;
                        break;
                    }

                    for (Map.Entry<String, Double> entry : graph.get(cur.point).entrySet()) {
                        String middle = entry.getKey();
                        if (visited.contains(middle)) {
                            continue;
                        }
                        visited.add(middle);
                        /**
                         * cur.point / middle = entry.getValue()
                         */
                        double temp = entry.getValue() * cur.value;
                        queue.offer(new Pair(entry.getKey(), temp));
                    }
                }
            }
            if (!found) {
                res[i] = -1.0;
            }
        }
        return res;
    }

    static class Pair {
        String point;
        double value;

        public Pair(String point, double value) {
            this.point = point;
            this.value = value;
        }
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            String a = list.get(0);
            String b = list.get(1);
            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(a).put(b, values[i]);
            graph.get(b).put(a, 1.0 / values[i]);
            graph.get(a).put(a, 1.0);
            graph.get(b).put(b, 1.0);
        }
        return graph;
    }

    /**
     * Understanding the following solution.
     * Time Cost 1ms
     * Depth-First-Search Solution
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = queries.size();
        double[] res = new double[n];

        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            String a = list.get(0);
            String b = list.get(1);
            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(a).put(b, values[i]);
            graph.get(b).put(a, 1.0 / values[i]);
        }

        for (int i = 0; i < queries.size(); i++) {
            List<String> pair = queries.get(i);
            String a = pair.get(0);
            String c = pair.get(1);
            if (!graph.containsKey(a) || !graph.containsKey(c)) {
                res[i]  = -1.0;
            } else {
                res[i] = dfs(graph, a, c, new HashSet<>());
            }
        }
        return res;
    }



    /**
     * start / end = (start/ middle) * (middle/end)
     * graph:
     * {start, {{middle, 1.0 / 4.0}}}
     * {middle, {{start, 4.0}, {end, 4.0 / 5.0}}}
     * {end, {{middle, 5.0 / 4.0}}}
     *
     * start / end = (1.0/4.0) * (4.0/5.0) = 1.0/5.0
     */
    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        if (start.equals(end)) {
            return 1.0;
        }
        visited.add(start);
        for (String middle: graph.get(start).keySet()) {
            if (visited.contains(middle)) {
                continue;
            }
            /**
             * end / middle
             */
            double res = dfs(graph, middle, end, visited);
            if (res > 0) {
                /**
                 * start / end = (start / middle) * (middle / end)
                 */
                return graph.get(start).get(middle) * res;
            }
        }
        return -1.0;
    }
}
