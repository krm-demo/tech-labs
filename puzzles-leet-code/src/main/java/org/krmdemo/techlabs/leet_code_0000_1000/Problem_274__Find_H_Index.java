package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/find-h-index--165609/1">
 *     274. H-Index
 * </a></h3>
 * Given an array of integers <b><code>citations</code></b>
 * where <code>citations[<b>i</b>]</code> is the number of citations a researcher received
 * for their <b>i</b>th paper, return the researcher's <b>h-index</b>.
 * <hr/>
 * According to the <a href="https://en.wikipedia.org/wiki/H-index">definition of h-index on Wikipedia</a>:
 * The h-index is defined as the maximum value of <b><code>h</code></b>
 * such that the given researcher has published at least <b><code>h</code></b> papers
 * that have each been cited at least <b><code>h</code></b> times.
 * <h5><i>geometrically:</i></h5>
 * Imagine plotting a histogram where the y-axis represents the number of citations for each paper.
 * After sorting in descending order, <b>h-index</b> is <b>the length of the largest square</b> in the histogram.
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/find-h-index--165609/1">
 *    (GFG) Find H-Index
 * </a>
 */
public interface Problem_274__Find_H_Index {

    /**
     * Solution entry-point.
     *
     * @param citations number of citations for <b>i</b>th publication
     * @return the value of <b>h-index</b>
     */
    int hIndex(int[] citations);

    enum Solution implements Problem_274__Find_H_Index {
        DEFAULT;

        @Override
        public int hIndex(int[] citations) {
            System.out.println("citations --> " + Arrays.toString(citations));
            Arrays.sort(citations);
            System.out.println("   sorted --> " + Arrays.toString(citations));
            reverse(citations);
            System.out.println(" reversed --> " + Arrays.toString(citations));
            int firstUnderCitated = 0;
            while (firstUnderCitated < citations.length
                && citations[firstUnderCitated] > firstUnderCitated)
            {
                firstUnderCitated++;
            }
            System.out.println("firstUnderCitated = " + firstUnderCitated);
            return firstUnderCitated;
        }

        private static void reverse(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            while (left < right) swap(arr, left++, right--);
        }

        private static void swap(int[] arr, int left, int right) {
            // x = x ^ y ^ (y = x);
            arr[left] = arr[left] ^ arr[right] ^ (arr[right] = arr[left]);
        }
    }
}
