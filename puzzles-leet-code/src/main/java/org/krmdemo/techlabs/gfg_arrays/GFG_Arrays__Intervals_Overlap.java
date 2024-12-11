package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/overlapping-intervals--170633/1">
 *     Overlapping Intervals
 * </a></h3>
 * Given an array of Intervals <b><code>rangesArr[][]</code></b>,
 * where <code>rangesArr[<b>i</b>] = [start<b>i</b>, end<b>i</b>]</code>.
 * The task is to merge all the <b>overlapping intervals</b>.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^5
 * 0 ≤ starti ≤ endi ≤ 10^5
 * </pre>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_056__Merge_Intervals
 */
public interface GFG_Arrays__Intervals_Overlap {

    /**
     * GFG-Solution entry-point
     *
     * @param rangesArr an array of integers
     * @return the list of merged all overlapped intervals
     */
    List<int[]> mergeOverlap(int[][] rangesArr);

    enum Solution implements GFG_Arrays__Intervals_Overlap {
        DEFAULT;

        final static Comparator<int[]> CMP_RANGES =
            Comparator.<int[]>comparingInt(range -> range[0])
                .thenComparingInt(range -> range[1]);

        @Override
        public List<int[]> mergeOverlap(int[][] rangesArr) {
            Arrays.sort(rangesArr, CMP_RANGES);
            int[] lastRange = null;
            List<int[]> resultList = new ArrayList<>();
            for (int[] range : rangesArr) {
                if (lastRange == null || lastRange[1] < range[0]) {
                    resultList.add(range);
                    lastRange = range;
                } else {
                    lastRange[1] = Math.max(lastRange[1], range[1]);
                }
            }
            return resultList;
        }
    }
}
