package org.ict.algorithm.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given a stream of records about a particular stock.
 * Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order.
 * Even worse, some records may be incorrect.
 * Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 *
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 *
 * Example 1:
 *
 * Input
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * Output
 * [null, null, null, 5, 10, null, 5, null, 2]
 *
 * Explanation
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
 * stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
 * stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
 * stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
 * stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
 *                           // Timestamps are [1,2] with corresponding prices [3,5].
 * stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
 * stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
 * stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 *
 *
 * Constraints:
 *
 * 1 <= timestamp, price <= 10^9
 * At most 105 calls will be made in total to update, current, maximum, and minimum.
 * current, maximum, and minimum will be called only after update has been called at least once.
 * @author sniper
 * @date 07 May 2023
 * LC2034, Medium, frequency=30
 */
public class StockPriceFluctuation {


    /**
     * <a href="https://grantjenks.com/docs/sortedcontainers/"></a>
     * <a href="https://grantjenks.com/docs/sortedcontainers/sorteddict.html"></a>
     *
     * Sorted Containers is an Apache2 licensed sorted collections' library,
     * written in pure-Python, and fast as C-extensions.
     * Python’s standard library is great until you need a sorted collections type.
     * Many will attest that you can get really far without one,
     * but the moment you really need a sorted list, sorted dict, or sorted set,
     * you’re faced with a dozen different implementations,
     * most using C-extensions without great documentation and benchmarking.
     * In Python, we can do better. And we can do it in pure-Python!
     * --------------------
     * from sortedcontainers import SortedDict
     *
     * class StockPrice:
     *
     *     def __init__(self):
     *         self.time_price_map = SortedDict()
     *         self.counter_map = SortedDict()
     *
     *     def update(self, timestamp: int, price: int) -> None:
     *         if timestamp in self.time_price_map:
     *             old_price = self.time_price_map[timestamp]
     *             self.counter_map[old_price] -= 1
     *             if self.counter_map[old_price] == 0:
     *                 del self.counter_map[old_price]
     *         if price not in self.counter_map:
     *             self.counter_map[price] = 0
     *         self.counter_map[price] += 1
     *         self.time_price_map[timestamp] = price
     *
     *
     *     def current(self) -> int:
     *         return self.time_price_map.values()[-1]
     *
     *     def maximum(self) -> int:
     *         return self.counter_map.keys()[-1]
     *
     *     def minimum(self) -> int:
     *         return self.counter_map.keys()[0]
     * --------------------------------------------
     * from sortedcontainers import SortedDict
     *
     * class StockPrice:
     *
     *     def __init__(self):
     *         self.time_to_prices = SortedDict()
     *         self.rec = SortedDict()
     *
     *     def update(self, timestamp: int, price: int) -> None:
     *         if timestamp in self.time_to_prices:
     *             prev_price = self.time_to_prices[timestamp]
     *             self.rec[prev_price].remove(timestamp)
     *             if len(self.rec[prev_price]) == 0:
     *                 self.rec.pop(prev_price)
     *         if not price in self.rec:
     *             self.rec[price] = set()
     *         self.rec[price].add(timestamp)
     *         self.time_to_prices[timestamp] = price
     *
     *     def current(self) -> int:
     *         return self.time_to_prices.peekitem(-1)[1]
     *
     *     def maximum(self) -> int:
     *         return self.rec.peekitem(-1)[0]
     *
     *     def minimum(self) -> int:
     *         return self.rec.peekitem(0)[0]
     *
     */
    class StockPriceV1 {

        /**
         * (time, price)
         */
        private TreeMap<Integer, Integer> timePriceMap = new TreeMap<>();
        /**
         * (price, count)
         */
        private TreeMap<Integer, Integer> counterMap = new TreeMap<>();
        public StockPriceV1() {}

        /**
         * Time Complexity O(logN)
         * @param timestamp
         * @param price
         */
        public void update(int timestamp, int price) {
            if (timePriceMap.containsKey(timestamp)) {
                int oldPrice = timePriceMap.get(timestamp);
                counterMap.put(oldPrice, counterMap.get(oldPrice) - 1);
                if (counterMap.get(oldPrice) == 0) {
                    counterMap.remove(oldPrice);
                }
            }
            timePriceMap.put(timestamp, price);
            counterMap.put(price, counterMap.get(price) + 1);
        }

        /**
         * Time Complexity O(logN)
         * @return
         */
        public int current() {
            return timePriceMap.lastEntry().getValue();
        }

        /**
         * Time Complexity O(logN)
         * @return
         */
        public int maximum() {
            return counterMap.lastKey();
        }

        /**
         * Time Complexity O(logN)
         * @return
         */
        public int minimum() {
            return counterMap.firstKey();
        }
    }

    /**
     * from sortedcontainers import SortedDict
     *
     * class StockPrice:
     *
     *     def __init__(self):
     *         self.latest_ts = 0
     *         self.time_price_map = {}
     *         self.counter_map = SortedDict()
     *
     *     def update(self, timestamp: int, price: int) -> None:
     *         if timestamp in self.time_price_map:
     *             old_price = self.time_price_map[timestamp]
     *             self.counter_map[old_price] -= 1
     *             if self.counter_map[old_price] == 0:
     *                 del self.counter_map[old_price]
     *         if price not in self.counter_map:
     *             self.counter_map[price] = 0
     *         self.counter_map[price] += 1
     *         self.time_price_map[timestamp] = price
     *         self.latest_ts = max(self.latest_ts, timestamp)
     *
     *
     *     def current(self) -> int:
     *         return self.time_price_map[self.latest_ts]
     *
     *     def maximum(self) -> int:
     *         return self.counter_map.keys()[-1]
     *
     *     def minimum(self) -> int:
     *         return self.counter_map.keys()[0]
     *
     */
    class StockPrice {

        private int latestTime;
        private Map<Integer, Integer> timePriceMap = new HashMap<>();
        private TreeMap<Integer, Integer> counterMap = new TreeMap<>();

        public StockPrice() {}

        public void update(int timestamp, int price) {
            if (timePriceMap.containsKey(timestamp)) {
                int oldPrice = timePriceMap.get(timestamp);
                counterMap.put(oldPrice, counterMap.get(oldPrice) - 1);
                if (counterMap.get(oldPrice) == 0) {
                    counterMap.remove(oldPrice);
                }
            }
            timePriceMap.put(timestamp, price);
            counterMap.put(price, counterMap.getOrDefault(price, 0) + 1);
            latestTime = Math.max(latestTime, timestamp);
        }

        public int current() {
            return timePriceMap.get(latestTime);
        }

        public int maximum() {
            return counterMap.lastKey();
        }

        public int minimum() {
            return counterMap.firstKey();
        }
    }

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
}
