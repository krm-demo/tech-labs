package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.summingInt;

/**
 * <h3><a href="https://leetcode.com/problems/permutations-ii/description/">
 *     47. Permutations II
 * </a></h3>
 * Given a collection of numbers, <b><code>nums</code></b>, that might contain <b>duplicates</b>,
 * return all possible unique permutations in any order.
 * <hr/>
 * You can return the answer in any order.
 * <h5>Constraints:</h5><pre>
 *  1 <= nums.length <= 8
 *  -10 <= nums[i] <= 10</pre>
 *
 * @see Problem_046__Permutations
 */
public interface Problem_047__Permutations_II {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers with <b>duplicates</b>
     * @return the largest sum of sub-array
     */
    List<List<Integer>> permuteUnique(int[] nums);

    enum Solution implements Problem_047__Permutations_II {
        /**
         * Recursive (back-tracking) approach that is based
         * on a single list with current permutation.
         */
        BACKTRACKING_LIST {
            @Override
            public List<List<Integer>> permuteUnique(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                new BackTracking(nums, permutation -> {
                    resultList.add(permutation.toList());
                }).run();
                return resultList;
            }
            static class BackTracking implements Runnable {
                final Consumer<Stream<Integer>> onNext;
                final int[] valuesArr;
                final List<Integer> current;
                final Map<Integer,Integer> countingMap;
                BackTracking(int[] valuesArr, Consumer<Stream<Integer>> onNext) {
                    this.onNext = onNext;
                    this.valuesArr = valuesArr;
                    current = new ArrayList<>();
                    countingMap = Arrays.stream(valuesArr).boxed().collect(
                        Collectors.groupingBy(identity(), summingInt(v -> 1))
                    );
                }
                @Override
                public void run() {
                    if (current.size() == valuesArr.length) {
                        onNext.accept(current.stream());
                        return;
                    }
                    for (Map.Entry<Integer, Integer> entry : countingMap.entrySet()) {
                        int value = entry.getKey();
                        int count = entry.getValue();
                        if (count == 0) {
                            continue;
                        }
                        current.addLast(value);
                        entry.setValue(count - 1);
                        run();
                        entry.setValue(count);
                        current.removeLast();
                    }
                }
            }
        };
    }
}
