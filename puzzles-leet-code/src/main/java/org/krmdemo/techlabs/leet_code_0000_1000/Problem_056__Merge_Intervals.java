package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     56. Merge Intervals
 * </a></h3>
 * Given an array of intervals where <code>intervals[i] = [start_<b>i</b>, end_<b>i</b>]</code>,
 * merge all overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * @see org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Intervals_Overlap
 */
public interface Problem_056__Merge_Intervals {

    /**
     * Solution entry-point.
     *
     * @param intervals an array of integer pairs (<code>intervals[i] = [start_<b>i</b>, end_<b>i</b>]</code>)
     * @return the array of the same strucure, but all overlapped intervals should be merged
     */
    int[][] merge(int[][] intervals);

    enum Solution implements Problem_056__Merge_Intervals {
        DEFAULT;

        final static Comparator<int[]> CMP_INTERVALS =
            Comparator.<int[]>comparingInt(range -> range[0])
                .thenComparingInt(range -> range[1]);

        @Override
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, CMP_INTERVALS);
            Deque<int[]> resultList = new ArrayDeque<>();
            for (int[] range : intervals) {
                int[] lastRange = resultList.peekLast();
                if (lastRange == null || lastRange[1] < range[0]) {
                    resultList.addLast(range);
                    continue;
                }
                lastRange[1] = Math.max(lastRange[1], range[1]);
            }
            return resultList.toArray(int[][]::new);
        }
    }
}
