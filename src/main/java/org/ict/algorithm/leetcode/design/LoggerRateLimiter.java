package org.ict.algorithm.leetcode.design;

import java.util.*;

/**
 * Description
 * Design a logger system that receives a stream of messages along with their timestamps.
 * Each unique message should only be printed at most every 10 seconds
 * (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 * Logger() Initializes the logger object.
 * bool shouldPrintMessage(int timestamp, string message)
 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * Output
 * [null, true, true, false, false, false, true]
 *
 * Explanation
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
 *
 *
 * Constraints:
 *
 * 0 <= timestamp <= 10^9
 * Every timestamp will be passed in non-decreasing order (chronological order).
 * 1 <= message.length <= 30
 * At most 10^4 calls will be made to shouldPrintMessage.
 * @author sniper
 * @date 06 May 2023
 * LC359, Medium, frequency=35
 */
public class LoggerRateLimiter {

    /**
     * Queue + Set
     * ------------------------------------------------------
     * class Logger:
     *   def __init__(self):
     *     # [(timestamp, message)]
     *     self.messageQueue = collections.deque()
     *     self.messageSet = set()
     *
     *   def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
     *     # Remove messages that are 10 secs from the current timestamp
     *     while self.messageQueue:
     *       headTimestamp, headMessage = self.messageQueue[0]
     *       if timestamp < headTimestamp + 10:
     *         break
     *       self.messageQueue.popleft()
     *       self.messageSet.remove(headMessage)
     *
     *     if message in self.messageSet:
     *       return False
     *
     *     self.messageQueue.append((timestamp, message))
     *     self.messageSet.add(message)
     *     return True
     * ------------------------------------------------------
     * class Logger {
     *  public:
     *   bool shouldPrintMessage(int timestamp, string message) {
     *     // Remove messages that are 10 secs from the current timestamp
     *     while (!messageQueue.empty()) {
     *       const auto [headTimestamp, headMessage] = messageQueue.front();
     *       if (timestamp < headTimestamp + 10)
     *         break;
     *       messageQueue.pop_front();
     *       messageSet.erase(headMessage);
     *     }
     *
     *     if (messageSet.count(message))
     *       return false;
     *
     *     messageQueue.emplace_back(timestamp, message);
     *     messageSet.insert(message);
     *     return true;
     *   }
     *
     *  private:
     *   // [(timestamp, message)]
     *   deque<pair<int, string>> messageQueue;
     *   unordered_set<string> messageSet;
     * };
     */
    class LoggerV1 {

        private Deque<Pair> messageQueue = new ArrayDeque<>();
        private Set<String> messageSet = new HashSet<>();

        public LoggerV1() {

        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            while (!messageQueue.isEmpty()) {
                Pair head = messageQueue.peekFirst();
                if (timestamp - head.timestamp < 10) {
                    break;
                }
                messageQueue.pollFirst();//equals poll()
                messageSet.remove(head.message);
            }

            if (messageSet.contains(message)) {
                return false;
            }
            messageQueue.offerLast(new Pair(timestamp, message));//equals offer(item)
            messageSet.add(message);
            return true;
        }

    }

    static class Pair {
        private int timestamp;
        private String message;

        public Pair(int timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }
    }

    /**
     * Map (unrealistic)
     * ------------------------------------
     * class Logger:
     *     def __init__(self):
     *         """
     *         Initialize your data structure here.
     *         """
     *         self.limiter = {}
     *
     *     def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
     *         """
     *         Returns true if the message should be printed in the given timestamp, otherwise returns false.
     *         If this method returns false, the message will not be printed.
     *         The timestamp is in seconds granularity.
     *         """
     *         t = self.limiter.get(message, 0)
     *         if t > timestamp:
     *             return False
     *         self.limiter[message] = timestamp + 10
     *         return True
     * -----------------------------------------------
     * class Logger {
     *  public:
     *   bool shouldPrintMessage(int timestamp, string message) {
     *     if (timestamp < limiter[message])
     *       return false;
     *
     *     limiter[message] = timestamp + 10;
     *     return true;
     *   }
     *
     *  private:
     *   unordered_map<string, int> limiter;  // {message: limiter time}
     * };
     */
    class Logger {
        private Map<String, Integer> limiter;

        public Logger() {
            limiter = new HashMap<>();
        }

        /**
         Returns true if the message should be printed in the given timestamp, otherwise returns
         false. If this method returns false, the message will not be printed. The timestamp is in
         seconds granularity.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (timestamp < limiter.getOrDefault(message, 0)) {
                return false;
            }
            limiter.put(message, timestamp + 10);
            return true;
        }
    }
    /**
     * Your Logger object will be instantiated and called as such:
     * Logger obj = new Logger();
     * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
     */
}
