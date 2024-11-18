package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

/**
 * <h3><a href="https://leetcode.com/problems/permutations/description/?envType=study-plan-v2&envId=top-interview-150">
 *     46. Permutations
 * </a></h3>
 * Given an array <b><code>nums</code></b> of distinct integers,
 * return all the possible permutations.
 * <hr/>
 * You can return the answer in any order.
 */
public interface Problem_046__Permutattions {

    /**
     * Solution entry-point.
     *
     * @param nums an array of distinct integers
     * @return the largest sum of sub-array
     */
    List<List<Integer>> permute(int[] nums);

    enum Solution implements Problem_046__Permutattions {
        ITER_NEXT_PERM {
            public List<List<Integer>> permute(int[] nums) {
                return emptyList(); // TODO: to be done
            }
        }
    }
}
