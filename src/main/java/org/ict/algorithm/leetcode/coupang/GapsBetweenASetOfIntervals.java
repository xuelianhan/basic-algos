package org.ict.algorithm.leetcode.coupang;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given a set of intervals with possibly non-distinct start and end points,
 * find all maximal gaps.
 * A gap is defined as an interval that does not overlap with any given interval.
 * All endpoints are integers and inclusive.
 *
 * For example, given the following set of intervals:
 * {[2,6],[1,9],[12,19]}
 * The set of all maximal gaps is:
 * {[10,11]}
 *
 * For the following set of intervals:
 * {[2,6],[1,9],[3,12],[18,20]}
 * The set of all maximal gaps is:
 * {[13,17]}
 * because that produces the maximal gap.
 *
 * @see <a href="https://cs.stackexchange.com/questions/133276/algorithm-to-compute-the-gaps-between-a-set-of-intervals"></a>
 * @author sniper
 * @date 17 Aug 2023
 */
public class GapsBetweenASetOfIntervals {

    /**
     * This code first sorts the intervals by their start point.
     * This ensures that we can find the next maximal gap by simply looking at the next interval.
     *
     * The code then iterates over the intervals.
     * For each interval, we check if its start point is greater than the current end point.
     * If it is, then we add a new interval to the list of gaps.
     * The new interval starts at the current end point and ends at the start point of the current interval.
     *
     * The code also keeps track of the current end point.
     * This is used to ensure that we do not add a gap that overlaps with any of the existing intervals.
     *
     * The code returns the list of gaps.
     * @param intervals
     * @return
     */
    public static List<Interval> findMaximalGaps(List<Interval> intervals) {
        List<Interval> gaps = new ArrayList<>();

        // Sort the intervals by start point.
        intervals.sort(Comparator.comparingInt(i -> i.start));

        int currentEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start > currentEnd) {
                gaps.add(new Interval(currentEnd, intervals.get(i).start));
            }
            currentEnd = Math.max(currentEnd, intervals.get(i).end);
        }

        return gaps;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
