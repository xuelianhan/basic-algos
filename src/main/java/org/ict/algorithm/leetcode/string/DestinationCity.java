package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * You are given the array paths, where paths[i] = [cityAi, cityBi]
 * means there exists a direct path going from cityAi to cityBi.
 * Return the destination city, that is, the city without any path outgoing to another city.
 *
 * It is guaranteed that the graph of paths forms a line without any loop,
 * therefore, there will be exactly one destination city.
 *
 *
 *
 * Example 1:
 *
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city.
 * Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 * Example 2:
 *
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 * Example 3:
 *
 * Input: paths = [["A","Z"]]
 * Output: "Z"
 *
 *
 * Constraints:
 *
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * All strings consist of lowercase and uppercase English letters and the space character.
 * @author sniper
 * @date 2022/8/12
 * LC1436
 */
public class DestinationCity {

    public static void main(String[] args) {
        String[][] s = {{"A","Z"}};
        List<List<String>> paths = new ArrayList<>();
        for(int i = 0; i < s.length; i++) {
            String[] arr = s[i];
            List<String> list = new ArrayList<>();
            for(String item : arr) {
                list.add(item);
            }
            paths.add(list);
        }

        String result = destCity(paths);
        System.out.println(result);
    }

    /**
     * [
     * ["London","New York"]
     * ["New York","Lima"]
     * ["Lima","Sao Paulo"]
     * ]
     * Solution provided by FunBam
     * Obeservation : destination city will always be the right one
     *
     * we traverse the input for two times
     * first time, we add all the cities on the right side to a set
     * second time, we check if the city occurs on the left side, if so, remove it from the set
     * result will be the city left in the set
     *
     * @param paths
     * @return
     */
    public String destCityV2(List<List<String>> paths) {
        Set<String> set= new HashSet<>();
        for (List<String> l: paths) {
            set.add(l.get(1));
        }
        for (List<String> l: paths) {
            set.remove(l.get(0));
        }
        return set.iterator().next();
    }


    /**
     * The destination city is the one which frequency count is 1 of the right side;
     * @param paths
     * @return
     */
    public static String destCity(List<List<String>> paths) {
        if (paths.size() == 1) {
            return paths.get(0).get(1);
        }
        Map<String, String> map = new HashMap<>(paths.size());
        Map<String, Integer> cnt = new HashMap<>(8);
        for (int i = 0; i < paths.size(); i++) {
            List<String> path = paths.get(i);
            map.put(path.get(0), path.get(1));
            cnt.put(path.get(0), cnt.getOrDefault(path.get(0), 0) + 1);
            cnt.put(path.get(1), cnt.getOrDefault(path.get(1), 0) + 1);
        }
        for(String v : map.values()) {
            if (cnt.get(v) == 1) {
                return v;
            }
        }
        return "";
    }
}
