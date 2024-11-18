package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-interview-150">
 *     53. Maximum Subarray
 * </a></h3>
 * Given an integer array <b><code>nums</code></b>, find the sub-array with the largest sum, and return its sum.
 * <h4>Constraints:</h4><pre>
 *      1 <= nums.length <= 10^5
 *  -10^4 <=   nums[i]   <= 10^4
 * </pre>
 */
public interface Problem_053__Maximum_SubArray_Sum {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers
     * @return the largest sum of sub-array
     */
    int maxSubArray(int[] nums);

    enum Solution implements Problem_053__Maximum_SubArray_Sum {
        DEFAULT {
            public int maxSubArray(int[] nums) {
                int[] cumArr = cumulativeSum(nums);
                int[] cumMinArr = cumulativeMin(cumArr);
                System.out.println("cumArr    --> " + Arrays.toString(cumArr));
                System.out.println("cumMinArr --> " + Arrays.toString(cumMinArr));
                // TODO: implement stream-solution without additional space (cumulativeForwardElevation) !!!
                return IntStream.range(1, cumArr.length)
                    .map(i -> cumArr[i] - cumMinArr[i - 1])
                    .max().orElseThrow();
            }

            private static int[] cumulativeSum(int[] arr) {
                int[] cumSumArr = new int[arr.length + 1];
                cumSumArr[0] = 0;
                for (int i = 0; i < arr.length; i++) {
                    cumSumArr[i + 1] = cumSumArr[i] + arr[i];
                }
                return cumSumArr;
            }

            private static int[] cumulativeMin(int[] arr) {
                int[] cumMinArr = new int[arr.length];
                int minSoFar = arr[0];
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] < minSoFar) {
                        minSoFar = arr[i];
                    }
                    cumMinArr[i] = minSoFar;
                }
                return cumMinArr;
            }
        },

        INT_STREAM {
            @Override
            public int maxSubArray(int[] nums) {
                // TODO: implement the solution, which will consume only constant memory
                return DEFAULT.maxSubArray(nums);
            }
        }
    }
}
