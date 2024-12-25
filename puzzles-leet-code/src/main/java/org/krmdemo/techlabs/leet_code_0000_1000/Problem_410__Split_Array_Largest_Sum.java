package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/split-array-largest-sum/description/">
 *     410. Split Array Largest Sum
 * </a></h3>
 * Given an integer array <b><code>nums</code></b> and an integer <b><code>k</code></b>,
 * split nums into <b><code>k</code></b> non-empty sub-arrays
 * such that the largest sum of any sub-array is minimized.
 * Return the minimized largest sum of the split.
 * <hr/>
 * A sub-array is a <b>contiguous</b> part of the array.
 * <h5>Constraints:</h5><pre>
 *    1 <= nums.length <= 1000
 *      0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 * </pre>
 *
 * @see org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Allocate_Min_Pages
 */
public interface Problem_410__Split_Array_Largest_Sum {

    /**
     * Solution entry-point.
     *
     * @param nums an array of non-negative positive integers (no matter whether it's important)
     * @param K the exact number of sub-array to split the array <code>nums</code>
     * @return the minimized largest sum of elements in all sub-arrays
     */
    int splitArray(int[] nums, int K);

    enum Solution implements Problem_410__Split_Array_Largest_Sum {
        DEFAULT;

        @Override
        public int splitArray(int[] nums, int K) {
            int maxValue = Arrays.stream(nums).max().orElseThrow(IllegalArgumentException::new);
            int totalSum = Arrays.stream(nums).sum();
            // bellow is very non-trivial binary search over the target maximum sum of each sub-array
            // - so, we are not looking for the way how to split the array - our optimization argument is the target sum;
            // - we are trying to perform such split for concrete target sum, minimizing the value of that sum;
            // - the main assumption that such sum MUST INCLUDE the value of maximum element;
            // - and another assumption that such sum MUST NOT EXCEED the total sum
            //   (which is possible only if elements are NOT negative);
            int left = maxValue;
            int right = totalSum;

            int minimizedTargetSum = 0;
            while (left <= right) {
                int targetSum = left + (right - left) / 2;
                int subArraysRequired = splitsRequired(targetSum, nums) + 1;
                if (subArraysRequired <= K) {
                    // if number of splits is too low - the target sum should be decreased (down to maxElement)
                    right = targetSum - 1;
                    minimizedTargetSum = targetSum;  // <-- binary-search is not stopped, we try to decrease more
                } else {
                    // if number of splits is too high - the target sum should be increased (up to totalSum)
                    left = targetSum + 1;
                }
            }
            return minimizedTargetSum; // <-- never get here if elements are positive
        }

        private int splitsRequired(int targetSum, int[] nums) {
            int sum = 0;
            int splitsCount = 0;
            for (int numValue : nums) {
                if (sum + numValue > targetSum) {
                    sum = numValue;
                    splitsCount++;
                } else {
                    sum += numValue;
                }
            }
            return splitsCount;
        }
    }
}
