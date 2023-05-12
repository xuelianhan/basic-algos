package org.ict.algorithm.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 * Your system should accept a timestamp parameter (in seconds granularity),
 * and you may assume that calls are being made to the system in chronological order
 * (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 * HitCounter() Initializes the object of the hit counter system.
 * void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 * int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 *
 * Example 1:
 * Input
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * Output
 * [null, null, null, null, 3, null, 4, 3]
 *
 * Explanation
 * HitCounter hitCounter = new HitCounter();
 * hitCounter.hit(1);       // hit at timestamp 1.
 * hitCounter.hit(2);       // hit at timestamp 2.
 * hitCounter.hit(3);       // hit at timestamp 3.
 * hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
 * hitCounter.hit(300);     // hit at timestamp 300.
 * hitCounter.getHits(300); // get hits at timestamp 300, return 4.
 * hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 *
 *
 * Constraints:
 *
 * 1 <= timestamp <= 2 * 10^9
 * All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
 * At most 300 calls will be made to hit and getHits.
 *
 *
 * Follow up: What if the number of hits per second could be huge? Does your design scale?
 * @author sniper
 * @date 13 May 2023
 * LC362, Medium, frequency=22
 */
public class DesignHitCounter {

    /**
     * Understanding the following solution
     * ---------------------------------
     * class HitCounter {
     *  public:
     *   void hit(int timestamp) {
     *     const int i = timestamp % 300;
     *
     *     if (timestamps[i] == timestamp) {
     *       ++hits[i];
     *     } else {
     *       timestamps[i] = timestamp;
     *       hits[i] = 1;  // Reset hit count to 1
     *     }
     *   }
     *
     *   int getHits(int timestamp) {
     *     int countHits = 0;
     *
     *     for (int i = 0; i < 300; ++i)
     *       if (timestamp - timestamps[i] < 300)
     *         countHits += hits[i];
     *
     *     return countHits;
     *   }
     *
     *  private:
     *   vector<int> timestamps = vector<int>(300);
     *   vector<int> hits = vector<int>(300);
     * };
     * ----------------------------------------------
     * class HitCounter:
     *   def __init__(self):
     *     self.timestamps = [0] * 300
     *     self.hits = [0] * 300
     *
     *   def hit(self, timestamp: int) -> None:
     *     i = timestamp % 300
     *     if self.timestamps[i] == timestamp:
     *       self.hits[i] += 1
     *     else:
     *       self.timestamps[i] = timestamp
     *       self.hits[i] = 1  # Reset hit count to 1
     *
     *   def getHits(self, timestamp: int) -> int:
     *     return sum(h for t, h in zip(self.timestamps, self.hits) if timestamp - t < 300)
     */
    class HitCounterV1 {
        private int[] timestamps = new int[300];
        private int[] hits = new int[300];

        public HitCounterV1() {}

        public void hit(int timestamp) {
            int i = timestamp % 300;
            if (timestamps[i] == timestamp) {
                hits[i]++;
            } else {
                timestamps[i] = timestamp;
                /**
                 * Reset hit count to 1
                 */
                hits[i] = 1;
            }
        }

        public int getHits(int timestamp) {
            int cnt = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - timestamps[i] < 300) {
                    cnt += hits[i];
                }
            }
            return cnt;
        }

    }

    class HitCounter {
        private Queue<Integer> queue;

        public HitCounter() {
            this.queue = new LinkedList<>();
        }

        /**
         * Record a hit.
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            queue.offer(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            while (!queue.isEmpty() && (timestamp - queue.peek() >= 300)) {
                queue.poll();
            }
            return queue.size();
        }

    }
}
