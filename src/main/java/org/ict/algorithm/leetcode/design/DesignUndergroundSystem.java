package org.ict.algorithm.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * An underground railway system is keeping track of customer travel times between different stations.
 * They are using this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 *
 * The average time is computed from all the previous traveling times from startStation to endStation that happened directly,
 * meaning a check in at startStation followed by a check-out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent.
 * If a customer checks in at time t1 then checks out at time t2, then t1 < t2.
 * All events happen in chronological order.
 *
 * Example 1:
 * Input
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * Output
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 *
 * Explanation
 * UndergroundSystem instance = new UndergroundSystem();
 * instance.checkIn(45, "Leyton", 3);
 * instance.checkIn(32, "Paradise", 8);
 * instance.checkIn(27, "Leyton", 10);
 * instance.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
 * instance.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
 * instance.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
 * instance.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
 * instance.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
 * instance.checkIn(10, "Leyton", 24);
 * instance.getAverageTime("Leyton", "Waterloo");    // return 11.00000
 * instance.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
 * instance.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
 * Example 2:
 *
 * Input
 * ["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
 * [[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
 *
 * Output
 * [null,null,null,5.00000,null,null,5.50000,null,null,6.66667]
 *
 * Explanation
 * UndergroundSystem instance = new UndergroundSystem();
 * instance.checkIn(10, "Leyton", 3);
 * instance.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
 * instance.getAverageTime("Leyton", "Paradise"); // return 5.00000, (5) / 1 = 5
 * instance.checkIn(5, "Leyton", 10);
 * instance.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
 * instance.getAverageTime("Leyton", "Paradise"); // return 5.50000, (5 + 6) / 2 = 5.5
 * instance.checkIn(2, "Leyton", 21);
 * instance.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
 * instance.getAverageTime("Leyton", "Paradise"); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
 *
 *
 * Constraints:
 * 1 <= id, t <= 10^6
 * 1 <= stationName.length, startStation.length, endStation.length <= 10
 * All strings consist of uppercase and lowercase English letters and digits.
 * There will be at most 2 * 10^4 calls in total to check in, check out, and getAverageTime.
 * Answers within 10-5 of the actual value will be accepted.
 *
 * @author sniper
 * @date 28 Apr, 2023
 * LC1396, Medium, frequency=64
 */
public class DesignUndergroundSystem {

    static class CheckIn{
        String stationName;
        int time;

        public CheckIn(String stationName, Integer time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    static class CheckOut {
        int totalTimeCost;

        int numberOfTrips;

        public CheckOut(int totalTimeCost, int numberOfTrips) {
            this.totalTimeCost = totalTimeCost;
            this.numberOfTrips = numberOfTrips;
        }
    }

    /**
     * Two HashMap
     *
     * class UndergroundSystem:
     *   def __init__(self):
     *     # {id: (stationName, time)}
     *     self.checkIns = {}
     *     # {route: (numTrips, totalTime)}
     *     self.checkOuts = collections.defaultdict(lambda: [0, 0])
     *
     *   def checkIn(self, id: int, stationName: str, t: int) -> None:
     *     self.checkIns[id] = (stationName, t)
     *
     *   def checkOut(self, id: int, stationName: str, t: int) -> None:
     *     startStation, startTime = self.checkIns.pop(id)
     *     route = (startStation, stationName)
     *     self.checkOuts[route][0] += 1
     *     self.checkOuts[route][1] += t - startTime
     *
     *   def getAverageTime(self, startStation: str, endStation: str) -> float:
     *     numTrips, totalTime = self.checkOuts[(startStation, endStation)]
     *     return totalTime / numTrips
     */
    class UndergroundSystemV1 {

        private Map<Integer, CheckIn> checkInIdName = new HashMap<>();

        private Map<String, CheckOut> fromTo = new HashMap<>();

        public UndergroundSystemV1() {}

        public void checkIn(int id, String stationName, int t) {
            checkInIdName.put(id, new CheckIn(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            CheckIn checkIn = checkInIdName.get(id);
            String trip = checkIn.stationName + "->" + stationName;
            int timeCost = t - checkIn.time;
            fromTo.putIfAbsent(trip, new CheckOut(0, 0));
            fromTo.get(trip).numberOfTrips++;
            fromTo.get(trip).totalTimeCost += timeCost;
        }

        public double getAverageTime(String startStation, String endStation) {
            String trip = startStation + "->" + endStation;
            CheckOut checkOut = fromTo.get(trip);
            return (double)checkOut.totalTimeCost / checkOut.numberOfTrips;
        }

    }

    /**
     * Three HashMap
     * Time Complexity O(1)
     * Space Complexity O(2*Persons + station^2)
     *
     * class UndergroundSystem:
     *
     *     def __init__(self):
     *         self.ts = {}
     *         self.d = {}
     *
     *     def checkIn(self, id: int, stationName: str, t: int) -> None:
     *         self.ts[id] = (t, stationName)
     *
     *     def checkOut(self, id: int, stationName: str, t: int) -> None:
     *         t0, station = self.ts[id]
     *         x = self.d.get((station, stationName), (0, 0))
     *         self.d[(station, stationName)] = (x[0] + t - t0, x[1] + 1)
     *
     *     def getAverageTime(self, startStation: str, endStation: str) -> float:
     *         x = self.d[(startStation, endStation)]
     *         return x[0] / x[1]
     * ----------------------------------
     * struct CheckIn {
     *     string stationName;
     *     int time;
     * };
     *
     * struct CheckOut {
     *     int numTrips;
     *     int totalTime;
     * };
     *
     * class UndergroundSystem {
     * public:
     *     UndergroundSystem() {
     *
     *     }
     *
     *     void checkIn(int id, string stationName, int t) {
     *         checkIns[id] = {stationName, t};
     *     }
     *
     *     void checkOut(int id, string stationName, int t) {
     *         const auto [startStation, startTime] = checkIns[id];
     *         checkIns.erase(id);
     *         const string& route = startStation + "->" + stationName;
     *         ++checkOuts[route].numTrips;
     *         checkOuts[route].totalTime += t - startTime;
     *     }
     *
     *     double getAverageTime(string startStation, string endStation) {
     *         const auto& [numTrips, totalTime] = checkOuts[startStation + "->" + endStation];
     *         return totalTime / (double)numTrips;
     *     }
     *
     * private:
     *     unordered_map<int, CheckIn> checkIns;
     *     unordered_map<string, CheckOut> checkOuts;
     * };
     */
    class UndergroundSystem {

        private Map<Integer, String> checkInIdName = new HashMap<>();

        private Map<Integer, Integer> checkInIdTime = new HashMap<>();

        private Map<String, int[]> fromTo = new HashMap<>();

        public UndergroundSystem() {}

        public void checkIn(int id, String stationName, int t) {
            checkInIdName.put(id, stationName);
            checkInIdTime.put(id, t);
        }

        public void checkOut(int id, String stationName, int t) {
            String trip = checkInIdName.get(id) + "->" + stationName;
            int[] costAndTimes = fromTo.getOrDefault(trip, new int[2]);
            // total cost time
            costAndTimes[0] += (t - checkInIdTime.get(id));
            // number of trips
            costAndTimes[1]++;
            fromTo.put(trip, costAndTimes);
        }

        public double getAverageTime(String startStation, String endStation) {
            String trip = startStation + "->" + endStation;
            int[] costAndTimes = fromTo.get(trip);
            return (double)costAndTimes[0] / costAndTimes[1];
        }
    }

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
}