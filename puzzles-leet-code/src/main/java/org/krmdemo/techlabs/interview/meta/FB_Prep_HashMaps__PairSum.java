package org.krmdemo.techlabs.interview.meta;

import org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Count_Pairs_With_Given_Sum;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=840934449713537&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Pair Sums
 * </a></h3>
 * TODO: add unit-test and introduce interface like in other puzzles
 * <hr/>
 * Given a list of n integers <code>arr[0..(<b>n</b>-1)]</code>,
 * determine the number of different pairs of elements within it which sum to <b><code>K</code></b>.
 * <hr/>
 * If an integer appears in the list multiple times, each copy is considered to be different;
 * that is, two pairs are considered different if one pair includes at least one array index which the other doesn't,
 * even if they include the same values.
 *
 * @see GFG_Arrays__Count_Pairs_With_Given_Sum
 */
public class FB_Prep_HashMaps__PairSum {

    /**
     * @param arr an array of integers in the range <code>[1, 1,000,000,000]</code>;
     *            the length of array is in range <code>[1, 100,000]</code>
     * @param K - target sum in the range <code>[1, 1,000,000,000]</code>.
     * @return the number of different pairs of elements which sum to <b><code>K</code></b>.
     */
    int numberOfWays(int[] arr, int K) {
        Map<Integer, Long> countngMap = Arrays.stream(arr).boxed()
            .collect(groupingBy(identity(), counting()));
        long numOfWays = 0;
        for (int value : arr) {
            int valueToK = K - value;
            long countValueToK = countngMap.getOrDefault(valueToK, 0L);
            if (valueToK == value) {
                if (countValueToK > 1) {
                    numOfWays += countValueToK * (countValueToK - 1) / 2;
                }
            } else {
                numOfWays += countValueToK;
            }
        }
        return (int)numOfWays;
    }

}
