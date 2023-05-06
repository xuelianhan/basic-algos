package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * LeetCode company workers use key-cards to unlock office doors.
 * Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used.
 * The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 *
 * You are given a list of strings keyName,
 * and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
 * Return a list of unique worker names who received an alert for frequent key card use.
 * Sort the names in ascending order alphabetically.
 *
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period,
 * while "22:51" - "23:52" is not considered to be within a one-hour period.
 *
 * Example 1:
 * Input:
 * keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"],
 * keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the key-card 3 times in a one-hour period ("10:00","10:40", "11:00").
 *
 * Example 2:
 * Input:
 * keyName = ["alice","alice","alice","bob","bob","bob","bob"],
 * keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
 * Output: ["bob"]
 * Explanation: "bob" used the key-card 3 times in a one-hour period ("21:00","21:20", "21:30").
 *
 * Constraints:
 * 1 <= keyName.length, keyTime.length <= 10^5
 * keyName.length == keyTime.length
 * keyTime[i] is in the format "HH:MM".
 * [keyName[i], keyTime[i]] is unique.
 * 1 <= keyName[i].length <= 10
 * keyName[i] contains only lowercase English letters.
 *
 * @author sniper
 * @date 06 May 2023
 * LC1604, Medium, frequency=33
 */
public class AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod {

    public static void main(String[] args) {
        String[] keyName = {"john","john","john"};
        String[] keyTime = {"23:58","23:59","00:01"};
        AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod instance = new AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod();
        List<String> res = instance.alertNamesV2(keyName, keyTime);
        System.out.println(res);
    }

    /**
     * The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
     * Return a list of unique worker names who received an alert for frequent key card use.
     * Sort the names in ascending order alphabetically.
     * -------------------------------------------------
     *     def alertNames(self, keyName: List[str], keyTime: List[str]) -> List[str]:
     *         name_to_time = collections.defaultdict(list)
     *         for name, hour_minute in zip(keyName, keyTime):
     *             hour, minute = map(int, hour_minute.split(':'))
     *             time = hour * 60 + minute
     *             name_to_time[name].append(time)
     *         names = []
     *         for name, time_list in name_to_time.items():
     *             time_list.sort()
     *             dq = collections.deque()
     *             for time in time_list:
     *                 dq.append(time)
     *                 if dq[-1] - dq[0] > 60:
     *                     dq.popleft()
     *                 if len(dq) >= 3:
     *                     names.append(name)
     *                     break
     *         return sorted(names)
     *
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNamesV3(String[] keyName, String[] keyTime) {
        TreeMap<String, TreeSet<Integer>> map = new TreeMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            map.computeIfAbsent(name, k -> new TreeSet<>()).add(t);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()) {
            /**
             * TreeSet sort time automatically
             */
            Deque<Integer> queue = new ArrayDeque<>();
            for (int t : entry.getValue()) {
                queue.offer(t);
                if (queue.peekLast() - queue.peek() > 60) {
                    queue.poll();
                }
                /**
                 * Because we are asked to find the one who press the key at least 3 times in 60 minutes.
                 * Starting from i = 2 to make sure the size of the list is at least 3;
                 * If the list size is less than 3, then the corresponding employee will be ignored by the code,
                 * because he/she is definitely NOT among the ones we are looking for.
                 */
                if (queue.size() >= 3) {
                    res.add(entry.getKey());
                    break;
                }
            }
        }
        return res;
    }

    /**
     * TreeMap + Deque
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNamesV2(String[] keyName, String[] keyTime) {
        TreeMap<String, List<Integer>> map = new TreeMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            map.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            /**
             * Sorting here is very important, because we need subtract later
             */
            Collections.sort(list);
            Deque<Integer> queue = new ArrayDeque<>();
            for (int t : list) {
                queue.offer(t);
                if (queue.peekLast() - queue.peek() > 60) {
                    queue.poll();
                }
                /**
                 * Because we are asked to find the one who press the key at least 3 times in 60 minutes.
                 * Starting from i = 2 to make sure the size of the list is at least 3;
                 * If the list size is less than 3, then the corresponding employee will be ignored by the code,
                 * because he/she is definitely NOT among the ones we are looking for.
                 */
                if (queue.size() >= 3) {
                    res.add(entry.getKey());
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Time Cost 113ms
     * TreeMap
     * -----------------------------------------------------
     * class Solution:
     *     def alertNames(self, keyName: List[str], keyTime: List[str]) -> List[str]:
     *         d = defaultdict(list)
     *         for name, t in zip(keyName, keyTime):
     *             t = int(t[:2]) * 60 + int(t[3:])
     *             d[name].append(t)
     *         ans = []
     *         for name, ts in d.items():
     *             if (n := len(ts)) > 2:
     *                 ts.sort()
     *                 for i in range(n - 2):
     *                     if ts[i + 2] - ts[i] <= 60:
     *                         ans.append(name)
     *                         break
     *         ans.sort()
     *         return ans
     * -----------------------------------------------------
     * def alertNames(self, keyName: List[str], keyTime: List[str]) -> List[str]:
     *         name_to_time = collections.defaultdict(list)
     *         for name, hour_minute in zip(keyName, keyTime):
     *             hour, minute = map(int, hour_minute.split(':'))
     *             time = hour * 60 + minute
     *             name_to_time[name].append(time)
     *         names = []
     *         for name, time_list in name_to_time.items():
     *             time_list.sort()
     *             for i, time in enumerate(time_list):
     *                 if i >= 2 and time - time_list[i - 2] <= 60:
     *                     names.append(name)
     *                     break
     *         return sorted(names)
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNamesV1(String[] keyName, String[] keyTime) {
        TreeMap<String, List<Integer>> map = new TreeMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            /**
             * Convert hours into minutes
             */
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            map.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }
        List<String> res = new ArrayList<>();
        /**
         * The set's iterator returns the entries in ascending key order.
         * Because we use a TreeMap.
         * If you use HashMap instead, you need to sort the res before returning back.
         * e.g. Collections.sort(res);
         */
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            int m = list.size();
            /**
             * Because we are asked to find the one who press the key at least 3 times in 60 minutes.
             * Starting from i = 2 to make sure the size of the list is at least 3;
             * If the list size is less than 3, then the corresponding employee will be ignored by the code,
             * because he/she is definitely NOT among the ones we are looking for.
             */
            if (m > 2) {
                Collections.sort(list);
                for (int i = 0; i < m - 2; i++) {
                    if (list.get(i + 2) - list.get(i) <= 60) {
                        res.add(entry.getKey());
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Time Cost 62ms
     * HashMap + Sort
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            /**
             * Convert hours into minutes
             */
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            map.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }
        List<String> res = new ArrayList<>();
        /**
         * The set's iterator returns the entries in ascending key order.
         * Because we use a TreeMap.
         * If you use HashMap instead, you need to sort the res before returning back.
         * e.g. Collections.sort(res);
         */
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            int m = list.size();
            /**
             * Because we are asked to find the one who press the key at least 3 times in 60 minutes.
             * Starting from i = 2 to make sure the size of the list is at least 3;
             * If the list size is less than 3, then the corresponding employee will be ignored by the code,
             * because he/she is definitely NOT among the ones we are looking for.
             */
            if (m > 2) {
                Collections.sort(list);
                for (int i = 0; i < m - 2; i++) {
                    if (list.get(i + 2) - list.get(i) <= 60) {
                        res.add(entry.getKey());
                        break;
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
