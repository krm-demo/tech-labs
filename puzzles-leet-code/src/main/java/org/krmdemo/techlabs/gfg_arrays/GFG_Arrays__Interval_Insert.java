package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/insert-interval-1666733333/1">
 *     Insert Interval
 * </a></h3>
 * Geek has an array of non-overlapping intervals <b><code>intervalsArr</code></b>
 * where <code>intervals[<b>i</b>] = [start<b>i</b>, end<b>i</b>]</code>
 * represent the start and the end of the ith event
 * and intervals is sorted in ascending order by <code>start<b>i</b></code>.
 * He wants to add a new interval <code>newInterval = [newStart, newEnd]</code>
 * where <code>newStart</code> and <code>newEnd</code> represent the start and end of this interval.
 * <hr/>
 * Help Geek to insert <code>newInterval</code> into intervals
 * such that intervals is still sorted in ascending order by <code>start<b>i</b></code>
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <h5>Constraints:</h5><pre>
 * 1 ≤ intervals.size() ≤ 10^5
 * 0 ≤ start[i], end[i] ≤ 10^9
 * </pre>
 */
public interface GFG_Arrays__Interval_Insert {

    /**
     * GFG-Solution entry-point
     *
     * @param intervalsArr an array of non-overlapping intervals
     * @param newInterval a new interval to merge with existing ones
     * @return the result list of sorted non-overlapping intervals as {@link ArrayList} of <code>int[]</code>
     */
    ArrayList<int[]> insertInterval(int[][] intervalsArr, int[] newInterval);

    enum Solution implements GFG_Arrays__Interval_Insert {
        DEFAULT;

        @Override
        public ArrayList<int[]> insertInterval(int[][] intervalsArr, int[] newInterval) {
            ArrayList<int[]> left = new ArrayList<>();
            Deque<int[]> mid = new ArrayDeque<>();
            List<int[]> right = new ArrayList<>();
            for (int[] interval : intervalsArr) {
                if (interval[1] < newInterval[0]) {
                    left.add(interval);
                } else if (interval[0] > newInterval[1]) {
                    right.add(interval);
                } else {
                    mid.add(interval);
                }
            }
            if (!mid.isEmpty()) {
                newInterval[0] = Math.min(newInterval[0],
                    mid.getFirst()[0]);
                newInterval[1] = Math.max(newInterval[1],
                    mid.getLast()[1]);
            }
            left.add(newInterval);
            left.addAll(right);
            return left;
        }
    }
}
