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
     * Understanding the following solution
     * Time Cost 1ms
     * @author lee215
     * @author Falldawn
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationV3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UF uf = new UF();
        for (int i = 0; i < values.length; i++) {
            uf.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        int n = queries.size();
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (uf.parents.containsKey(x) && uf.parents.containsKey(y) && Objects.equals(uf.find(x), uf.find(y))) {
                res[i] = uf.vals.get(x) / uf.vals.get(y);
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }

    class UF {
        Map<String, String> parents;
        Map<String, Double> vals;

        /**
         * parents: (x, root(x)), vals:(x, x/root(x)),
         * for example, a / b = 2.0, we will have parents(a, b), vals(a, 2.0),
         * both parents and vals have the numerator (which is 'a' here)
         * so that we can search for it and get the value a / parents(a) = vals.get(a) which is a / b = 2.0.
         */
        public UF() {
            parents = new HashMap<>();
            vals = new HashMap<>();
        }

        public void add(String x) {
            if (parents.containsKey(x)) {
                return;
            }
            parents.put(x, x);
            vals.put(x, 1.0);
        }

        /**
         * find the root
         * parents(x, p) = vals(x), x / p = vals(x), parents(p, pp) = vals(p), p / pp = vals(pp),
         * when we are looking for the root, we are doing a path compression here
         * parents(x, pp)  = vals(x) / vals(pp) =  (x / p) * (p / pp) = vals(x) * vals(p)
         * For example, a / b = 2.0, b / c = 3.0,
         * parents(a, b) = 2.0,  parents(b, c) = 3.0, parents(a, c) = 2.0 * 3.0 = 6.0, now vals(a) = 6.0
         * So along the way, we get all the value of a / x where x is the parent of x all the way to root.
         * In the end, only a / root(x) is saved. This is path compression.
         * It's like putting a directly under the root(x)
         */
        public String find(String x) {
            String p = parents.getOrDefault(x, x);
            if (!Objects.equals(p, x)) {
                String rootP = find(p);
                vals.put(x, vals.get(x) * vals.get(p));
                parents.put(x, rootP);
            }
            return parents.getOrDefault(x, x);
        }

        /**
         * x / px = vals.get(x),
         * y / py = vals.get(y),
         * so px / py =  v * vals.get(y) / vals.get(x)
         */
        public void union(String x, String y, double v) {
            add(x);
            add(y);
            String px = find(x);
            String py = find(y);
            parents.put(px, py);
            vals.put(px, v * vals.get(y) / vals.get(x));
        }

    }

    /**
     * Understanding the following solution.
     * Time Cost 1ms
     * Union-Find Solution
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationV2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UnionFind uf = new UnionFind(equations);
        uf.union(equations, values);
        return uf.search(queries);
    }

    class UnionFind {
        Map<String, String> parent = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();

        public UnionFind(List<List<String>> equations) {
            for (List<String> e : equations) {
                parent.put(e.get(0), e.get(0));
                parent.put(e.get(1), e.get(1));
                weights.put(e.get(0), 1.0);
                weights.put(e.get(1), 1.0);
            }
        }

        public void union(List<List<String>> equations, double[] values) {
            for (int i = 0; i < equations.size(); i++) {
                List<String> e = equations.get(i);
                String e0 = e.get(0);
                String e1 = e.get(1);
                String ep = find(e0);
                String eq = find(e1);
                if (Objects.equals(ep, eq)) {
                    continue;
                }
                parent.put(ep, eq);
                weights.put(ep, weights.get(e1) * values[i] / weights.get(e0));
            }
        }

        public double[] search(List<List<String>> queries) {
            int n = queries.size();
            double[] res = new double[n];
            for (int i = 0; i < n; i++) {
                String p = queries.get(i).get(0);
                String q = queries.get(i).get(1);
                /**
                 * Notice here, Don't put connected method before the if-condition.
                 * Because the meaning is different.
                 */
                if (!parent.containsKey(p) || !parent.containsKey(q) || !connected(p, q)) {
                    res[i] = -1.0;
                } else {
                    res[i] = weights.get(p) / weights.get(q);
                }
            }
            return res;
        }

        public boolean connected(String p, String q) {
            return Objects.equals(find(p), find(q));
        }

        public String find(String p) {
            if (!Objects.equals(parent.get(p), p)) {
                String origin = parent.get(p);
                parent.put(p, find(parent.get(p)));
                weights.put(p, weights.get(p) * weights.get(origin));
            }
            return parent.get(p);
        }
    }




    /**
     * Understanding the following solution.
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
            /**
             * a / b = values[i], b / a = 1.0/values[i]
             */
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
