package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/count-pairs-with-given-sum--150253/1">
 *     Count pairs with given sum
 * </a></h3>
 * Given an array <b><code>arr[]</code></b> and an integer <b><code>target</code></b>.
 * You have to find numbers of pairs in array <b><code>arr[]</code></b>
 * which sums up to given <b><code>target</code></b>.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^5
 * -10^4 ≤ arr[i] ≤ 10^4
 *   1 ≤ target ≤ 10^4
 * </pre>
 */
public interface GFG_Arrays__Count_Pairs_With_Given_Sum {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array containing positive integers
     * @return minimum number of swaps required to sort the array in strictly increasing order
     */
    int countPairs(int[] arr, int target);

    enum Solution implements GFG_Arrays__Count_Pairs_With_Given_Sum {
        /**
         * The brute-force solution has <code>O(N^2)</code> time complexity and does not satisfy time-limit
         */
        BRUTE_FORCE {
            @Override
            public int countPairs(int[] arr, int target) {
//                System.out.printf("arr.length = %d; target = %d:%n", arr.length, target);
                int count = 0;
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[i] + arr[j] == target) {
                            count++;
//                            System.out.printf("%2d) arr[%d] = %d, arr[%d] = %d;%n",
//                                count, i, arr[i], j, arr[j]);
                        }
                    }
                }
                return count;
            }
        },
        /**
         * Unlike the <b>counting-map</b> solution here, one of more compact and less straight-forward
         * is based on calculating the count on constructing the counting-map (see editorial)
         */
        COUNTING_MAP {
            @Override
            public int countPairs(int[] arr, int target) {
                // System.out.println("target = " + target);
                // System.out.println("arr --> " + Arrays.toString(arr));
                Map<Integer,Integer> cntMap = Arrays.stream(arr).boxed()
                    .collect(java.util.stream.Collectors.groupingBy(
                        java.util.function.Function.identity(),
                        java.util.stream.Collectors.summingInt(v -> 1)
                    ));
                // System.out.println("cntMap --> " + cntMap);
                int halfCount = target % 2 != 0 ? 0 :
                    cntMap.getOrDefault(target / 2, 0);
                halfCount = halfCount * (halfCount - 1) / 2;
//                System.out.println("halfCount = " + halfCount);
                return halfCount + cntMap.entrySet().stream()
                    .filter(e -> e.getKey() > target - e.getKey())
//                    .peek(e -> {
//                        if (cntMap.containsKey(target - e.getKey())) {
//                            System.out.printf("%d:%d --> %d:%d;%n",
//                                e.getKey(), e.getValue(),
//                                target - e.getKey(),
//                                cntMap.getOrDefault(target - e.getKey(), 0)
//                            );
//                        }
//                    })
                    .mapToInt(e -> e.getValue() * cntMap.getOrDefault(target - e.getKey(), 0))
                    .sum();
            }
        };
    }
}