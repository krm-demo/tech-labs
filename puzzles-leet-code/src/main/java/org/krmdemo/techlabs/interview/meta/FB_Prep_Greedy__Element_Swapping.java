package org.krmdemo.techlabs.interview.meta;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=838749853303393&c=3513648482275061&psid=275492097255885&practice_plan=0&b=0111122">
 *     Greedy #2. Element Swapping.
 * </a></h3>
 * Given a sequence of <code>N</code> integers <b><code>arr</code></b>,
 * determine the <a href="https://en.wikipedia.org/wiki/Lexicographic_order">lexicographically smallest</a> sequence
 * which may be obtained from it after performing at most <b><code>k</code></b> element swaps,
 * each involving a pair of <u>consecutive elements</u> in the sequence.
 * <hr/>
 * <small>
 * <u><i>Note:</i></u> A <code>list x</code> is lexicographically smaller
 * than a different equal-length <code>list y</code> if and only if, for the earliest index  at which the two lists differ,
 * <code>x</code>'s element at that index is smaller than <code>y</code>'s element at that index.
 * </small>
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.length ≤ 10^3
 *      1 ≤ k ≤ 1000
 *     1 ≤ arr[i] ≤ 10^6
 * </pre>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_031__Next_Permutation (more advanced problem)
 */
public interface FB_Prep_Greedy__Element_Swapping {

    /**
     * Solution entry-point.
     *
     * @param arr an array of positive integers (values don't really matter) of size not more than <code>1000</code>
     * @param k the exact number swaps to perform for <u>only consecutive pair</u> of elements
     * @return the lexicographically smallest array after those <code>k</code> swaps
     */
    int[] findMinArray(int[] arr, int k);

    /**
     * The proposed solution is based on {@link PriorityQueue}
     * and appears to be a classic greedy algorithm (the strict proof is not trivial)
     */
    enum Solution implements FB_Prep_Greedy__Element_Swapping {
        DEFAULT;

        @Override
        public int[] findMinArray(int[] arr, int k) {
            // TODO: to be implemented (brute force approach must be enough) and covered with tests
            return new int[0];
        }
    }
}
