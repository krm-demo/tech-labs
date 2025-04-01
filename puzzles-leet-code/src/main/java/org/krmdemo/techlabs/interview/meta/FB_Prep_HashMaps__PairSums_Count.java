package org.krmdemo.techlabs.interview.meta;

import org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Count_Pairs_With_Given_Sum;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=840934449713537&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Hash Tables #1. Pair Sums.
 * </a></h3>
 * Given a list of n integers <code>arr[0..(<b>n</b>-1)]</code>,
 * determine the number of different pairs of elements within it which sum to <b><code>K</code></b>.
 * <hr/>
 * If an integer appears in the list multiple times, each copy is considered to be different;
 * that is, two pairs are considered different if one pair includes at least one array index which the other doesn't,
 * even if they include the same values.
 *
 * @see GFG_Arrays__Count_Pairs_With_Given_Sum
 */
public interface FB_Prep_HashMaps__PairSums_Count {

    /**
     * @param arr an array of integers in the range <code>[1, 1,000,000,000]</code>;
     *            the length of array is in range <code>[1, 100,000]</code>
     * @param K - target sum in the range <code>[1, 1,000,000,000]</code>.
     * @return the number of different pairs of elements which sum to <b><code>K</code></b>.
     */
    int numberOfWays(int[] arr, int K);

    enum Solution implements FB_Prep_HashMaps__PairSums_Count {
        /**
         * This <b><code>O(N^2)</code></b> uses two nested loop to enumerate all possible pairs
         */
        BRUTE_FORCE {
            @Override
            public int numberOfWays(int[] arr, int K) {
                int numOfWays = 0;
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[i] + arr[j] == K) {
                            numOfWays++;
                        }
                    }
                }
                return numOfWays;
            }
        },
        /**
         * This approach uses <b>counting-map</b>, which is populating together with accumulating the result
         */
        COUNTING_MAP {
            @Override
            public int numberOfWays(int[] arr, int K) {
                Map<Integer, Integer> countngMap = new HashMap<>();
                int numOfWays = 0;
                for (int value : arr) {
                    int valueToK = K - value;
                    int cntValue = countngMap.getOrDefault(value, 0);
                    int cntValueToK = countngMap.getOrDefault(valueToK, 0);
                    numOfWays += cntValueToK;
                    countngMap.put(value, cntValue + 1);
                }
                return numOfWays;
            }
        },
        /**
         * This approach builds the <b>counting-map</b> and process it separately,
         * which makes the solution of more functional style (no loops).
         */
        COUNTING_MAP_EX {
            @Override
            public int numberOfWays(int[] arr, int K) {
                Map<Integer, Long> countngMap = Arrays.stream(arr).boxed()
                    .collect(groupingBy(identity(), counting()));
                long halfCount = K % 2 != 0 ? 0L : countngMap.getOrDefault(K / 2, 0L);
                halfCount = halfCount * (halfCount - 1) / 2;
                long nonHalfCount = countngMap.entrySet().stream()
                    .filter(e -> e.getKey() > K - e.getKey())
                    .mapToLong(e ->
                        e.getValue() * countngMap.getOrDefault(K - e.getKey(), 0L))
                    .sum();
                return (int)(halfCount + nonHalfCount);
            }
        }
    }
}
