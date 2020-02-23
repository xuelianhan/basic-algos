package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take
 * course 1, which is expressed as a pair:[0, 1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of
 * courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is 
 * impossible to finish all courses, return an empty array;
 *
 * Example 1:
 * Input: 2, [[1, 0]]
 * Output: [0, 1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So the correct course order is[0, 1].
 *
 * Example 2:
 * Input 4, [[1, 0], [2, 0], [3, 1], [3, 2]]
 * Output: [0, 1, 2, 3] or [0, 2, 1, 3]
 *
 * Note:
 * 1. The input prerequisites is a graph represented by a list of edges, not adjacency
 * matrices. Read more about how a graph is represented.
 * 2. You may assume that there are no duplicate edges in the input prerequisites.
 *
 * According to the wiki about what Topological sorting:
 * @see https://en.wikipedia.org/wiki/Topological_sorting
 * and the Kahn's algorithm as shown below:
 *
 * L: Emply list that will contain the sorted elements
 * S: Set of all nodes with no incoming edges
 * while S is non-empty do
 *     remove a node n from S
 *     add n to tail of L
 *     for each node m with an edge e from n to m do
 *         remove edge e from the graph
 *         if m has no other incoming edges then
 *             insert m into S
 * if graph has edges then
 *     return error(graph has at least one cycle)
 * else 
 *     return L(a topologically sorted order)
 */
public class CourseScheduleTwo {

    public static void main(String[] args) {
        CourseScheduleTwo obj = new CourseScheduleTwo();
        int numbCourses = 4;
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}, {3,2}};
        int[] order = obj.findOrder(numbCourses, prerequisites);
        System.out.println(Arrays.toString(order));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // numCourses is the vertices of graph
        // prerequisites is the edges of graph
        // Allocate memory for this graph
        int[] indegree = new int[numCourses];
        List<Integer>[] adj  = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new LinkedList<Integer>();
        }

        // Initializes the graph, including adj and indegree
        addEdge(prerequisites, adj, indegree);

        // Initializes the Set of nodes with no incoming edges.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // the core steps of this algorithm as following
        int edgeCnt = prerequisites.length;
        Queue<Integer> order = new LinkedList<>();
        while (!queue.isEmpty()) {
        	// dequeue the node from
            int from  = queue.poll();
            // enqueue from into order
            order.offer(from);
            List<Integer> list = adj[from];
            //Removing ele should be replaced with iterator, not list-for-loop
            //for-loop iteration with iterator delete in it will cause 
            Iterator<Integer> iter = list.iterator();
            while (iter.hasNext()) {
            	  Integer to = iter.next();
            	  // remove edge from -> to of the graph.
            	  iter.remove();
                  edgeCnt--;
                  indegree[to]--;
                  if (indegree[to] == 0) {
                      queue.offer(to);
                  } 
            }
        }
        if (edgeCnt != 0) {
            return new int[0];
        } 
        return order.stream().mapToInt(i->i).toArray();

    }

    private void addEdge(int[][] prerequisites, List<Integer>[] adj, int[] indegree) {
        for (int[] pair : prerequisites) {
            adj[pair[1]].add(pair[0]);
            indegree[pair[0]]++;
        }
    }
}
