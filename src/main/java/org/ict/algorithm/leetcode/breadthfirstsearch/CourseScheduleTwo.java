package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 *
 * According to the wiki about what Topological sorting:
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting"></a>
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
 * LC210, Medium, frequency=35
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
            //Removing operation should be replaced with iterator, not list-for-loop
            //for-loop iteration with iterator delete in it will cause problems 
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
