package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 *
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W',
 * each representing moving one unit north, south, east, or west, respectively.
 * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return true if the path crosses itself at any point, that is,
 * if at any time you are on a location you have previously visited.
 * Return false otherwise.
 *
 * Example 1:
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 10^4
 * path[i] is either 'N', 'S', 'E', or 'W'.
 *
 *
 * @author sniper
 * @date 12 Aug, 2022
 * LC1496
 */
public class PathCrossing {

    public static void main(String[] args) {
        String path = "NESWW";
        boolean result = isPathCrossing(path);
        System.out.println(result);
    }

    public boolean isPathCrossingV2(String path) {
        Set<String> seen = new HashSet<>();
        seen.add(0 + ", " + 0);
        for (int i = 0, x = 0, y = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (c == 'N') {
                y += 1;
            }else if (c == 'S') {
                y -= 1;
            }else if (c == 'E') {
                x -= 1;
            }else {
                x += 1;
            }
            if (!seen.add(x + ", " + y)) {
                return true;
            }
        }
        return false;
    }



    public static boolean isPathCrossing(String path) {
        if (path.length() == 1) {
            return false;
        }
        Map<Point, Boolean> visited = new HashMap<>();
        Point origin = new Point(0, 0);
        visited.put(origin, true);
        Point last = origin;
        for(char c : path.toCharArray()) {
            /**
             * move up(N)
             */
            if (c == 'N') {
                Point p = new Point(last.x, last.y + 1);
                last = p;
                if (visited.getOrDefault(p, false) == false) {
                    visited.put(p, true);
                    continue;
                } else {
                    return true;
                }
            }
            /**
             * move down(S)
             */
            if (c == 'S') {
                Point p = new Point(last.x, last.y - 1);
                last = p;
                if (visited.getOrDefault(p, false) == false) {
                    visited.put(p, true);
                    continue;
                } else {
                    return true;
                }
            }
            /**
             * move right(E)
             */
            if (c == 'E') {
                Point p = new Point(last.x + 1, last.y);
                last = p;
                if (visited.getOrDefault(p, false) == false) {
                    visited.put(p, true);
                    continue;
                } else {
                    return true;
                }
            }
            /**
             * move left(W)
             */
            if (c == 'W') {
                Point p = new Point(last.x - 1, last.y);
                last = p;
                if (visited.getOrDefault(p, false) == false) {
                    visited.put(p, true);
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    static class Point {
        int x = 0;
        int y = 0;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
