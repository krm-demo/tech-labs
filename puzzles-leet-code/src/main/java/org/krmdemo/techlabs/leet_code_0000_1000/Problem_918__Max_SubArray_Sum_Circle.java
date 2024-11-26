package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/maximum-sum-circular-subarray/description/">
 *     918. Maximum Sum Circular Sub-Array
 * </a></h3>
 * Given a circular integer array <b><code>nums</code></b> of length <b><code>n</code></b>,
 * return the maximum possible sum of a non-empty sub-array of <b><code>nums</code></b>.
 * <hr/>
 * A circular array means the end of the array connects to the beginning of the array.
 * Formally, the next element of <code>nums[i]</code> is <code>nums[(i + 1) % n]</code>
 * and the previous element of <code>nums[i]</code> is <code>nums[(i - 1 + n) % n]</code>.
 * <hr/>
 * A sub-array may only include each element of the fixed buffer nums at most once.
 * Formally, for a sub-array <code>nums[i], nums[i + 1], ..., nums[j]</code>,
 * there does not exist <code>i <= k1, k2 <= j with k1 % n == k2 % n</code>.
 * <h5>Constraints:</h5><pre>
 *      n == nums.length
 *     1 <= n <= 3 * 10^4
 * -3 * 104 <= nums[i] <= 3 * 10^4
 * </pre>
 *
 * @see Problem_053__Max_SubArray_Sum
 */
public interface Problem_918__Max_SubArray_Sum_Circle {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers
     * @return the largest sum of sub-array
     */
    int maxSubarraySumCircular(int[] nums);

    enum Solution implements Problem_918__Max_SubArray_Sum_Circle {
        /**
         * In this approach the maximum between <code>prefixSum + suffixSum</code>
         * and a result of regular puzzle {@link Problem_053__Max_SubArray_Sum} is returned.
         * In order to do that some additional cumulative arrays are calculated,
         */
        CUM_SUM_BEFORE_AFTER {
            @Override
            public int maxSubarraySumCircular(int[] nums) {
                final int N = checkInputArrayLen(nums);
                if (N == 1) {
                    System.out.println("returning single element " + nums[0]);
                    return nums[0];
                }

                int[] cumSumBeforeArr = cumSumBefore(nums);
                int[] cumSumBeforeMinArr = cumulativeMin(cumSumBeforeArr);
                int maxSum = IntStream.range(1, N + 1)
                    .map(i -> cumSumBeforeArr[i] - cumSumBeforeMinArr[i - 1])
                    .max().orElseThrow();
                System.out.println("cumSumBeforeArr    --> " + Arrays.toString(cumSumBeforeArr));
                System.out.println("cumSumBeforeMinArr --> " + Arrays.toString(cumSumBeforeMinArr));
                System.out.println("maxSum = " + maxSum);

                int[] cumSumAfterArr = cumSumAfter(nums);
                int[] cumSumBeforeMaxArr = cumulativeMax(cumSumBeforeArr);
                int maxPrefixSuffix = IntStream.range(1, N)
                    .map(i -> cumSumBeforeMaxArr[i] + cumSumAfterArr[i + 1])
                    .max().orElseThrow();
                System.out.println("cumSumAfterArr     --> " + Arrays.toString(cumSumAfterArr));
                System.out.println("cumSumBeforeMaxArr --> " + Arrays.toString(cumSumBeforeMaxArr));
                System.out.println("maxPrefixSuffix = " + maxPrefixSuffix);

                System.out.println("returning max is " + Math.max(maxSum, maxPrefixSuffix));
                return Math.max(maxSum, maxPrefixSuffix);
            }

            private static int[] cumSumBefore(int[] arr) {
                int[] cumSumArr = new int[arr.length + 1];
                cumSumArr[0] = 0;
                for (int i = 0; i < arr.length; i++) {
                    cumSumArr[i + 1] = cumSumArr[i] + arr[i];
                }
                return cumSumArr;
            }

            private static int[] cumSumAfter(int[] arr) {
                int[] cumSumArr = new int[arr.length + 1];
                cumSumArr[arr.length] = 0;
                for (int i = arr.length - 1; i >= 0; i--) {
                    cumSumArr[i] = cumSumArr[i + 1] + arr[i];
                }
                return cumSumArr;
            }

            private static int[] cumulativeMin(int[] arr) {
                int[] cumMinArr = new int[arr.length];
                int minSoFar = arr[0];
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] < minSoFar) {
                        minSoFar = arr[i];
                    }
                    cumMinArr[i] = minSoFar;
                }
                return cumMinArr;
            }

            private static int[] cumulativeMax(int[] arr) {
                // we have to skip the last element here
                int[] cumMaxArr = new int[arr.length - 1];
                int maxSoFar = arr[1];
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] > maxSoFar) {
                        maxSoFar = arr[i];
                    }
                    cumMaxArr[i - 1] = maxSoFar;
                }
                return cumMaxArr;
            }
        },
        /**
         * In this approach we calculate minimum and maximum sub-array's sums
         * using a regular (classic) Kadane's algorithm.
         * @see {@link Problem_053__Max_SubArray_Sum.Solution#KADANE_CLASSIC}
         */
        DOUBLE_KADANE_CLASSIC {
            public int maxSubarraySumCircular(int[] nums) {
                if (checkInputArrayLen(nums) == 1) {
                    System.out.println("returning single element " + nums[0]);
                    return nums[0];
                }

                int totalSum = 0;
                int currSumPos = 0;
                int currSumNeg = 0;
                int maxCurrSum = nums[0];
                int minCurrSum = nums[0];
                for (int value : nums) {
                    totalSum += value;
                    // reset the negative cumulative sum 'currSumPos' to zero
                    currSumPos = Math.max(0, currSumPos) + value;
                    maxCurrSum = Math.max(maxCurrSum, currSumPos);
                    // reset the positive cumulative sum 'currSumNeg' to zero
                    currSumNeg = Math.min(0, currSumNeg) + value;
                    minCurrSum = Math.min(minCurrSum, currSumNeg);
                }
                System.out.println("totalSum = " + totalSum);
                System.out.println("minCurrSum = " + minCurrSum);
                System.out.println("maxCurrSum = " + maxCurrSum);

                int maxSumCircle = totalSum == minCurrSum ? maxCurrSum :
                    Math.max(maxCurrSum, totalSum - minCurrSum);
                int minSumCircle = totalSum == maxCurrSum? minCurrSum :
                    Math.min(minCurrSum, totalSum - maxCurrSum);
                System.out.println("minSumCircle = " + minSumCircle);
                System.out.println("maxSumCircle = " + maxSumCircle);
                return maxSumCircle;
            }
        },
        /**
         * This variation of classic Kadane's algorithm
         * is a little bit closer to streaming (loop-free) approach.
         */
        DOUBLE_KADANE_MIN_MAX_SO_FAR {
            @Override
            public int maxSubarraySumCircular(int[] nums) {
                if (checkInputArrayLen(nums) == 1) {
                    System.out.println("returning single element " + nums[0]);
                    return nums[0];
                }

                int sum = 0;
                int sumMinSoFar = 0;
                int sumMaxSoFar = 0;
                int minSumDiff = Integer.MAX_VALUE;
                int maxSumDiff = Integer.MIN_VALUE;
                for (int value : nums) {
                    sum += value;
                    minSumDiff = Math.min(minSumDiff, sum - sumMaxSoFar);
                    maxSumDiff = Math.max(maxSumDiff, sum - sumMinSoFar);
                    sumMinSoFar = Math.min(sumMinSoFar, sum);
                    sumMaxSoFar = Math.max(sumMaxSoFar, sum);
                }
                System.out.println("totalSum = " + sum);
                System.out.println("minSumDiff = " + minSumDiff);
                System.out.println("maxSumDiff = " + maxSumDiff);

                int maxSumCircle = sum == minSumDiff ? maxSumDiff :
                    Math.max(maxSumDiff, sum - minSumDiff);
                int minSumCircle = sum == maxSumDiff? minSumDiff :
                    Math.min(minSumDiff, sum - maxSumDiff);
                System.out.println("minSumCircle = " + minSumCircle);
                System.out.println("maxSumCircle = " + maxSumCircle);
                return maxSumCircle;
            }
        };

        int checkInputArrayLen(int[] nums) {
            if (nums == null || nums.length < 1) {
                throw new IllegalArgumentException("invalid input array - " + Arrays.toString(nums));
            }
            System.out.printf("%n---- %s.maxSumCircle ( N = %d ): ----%n", name(), nums.length);
            System.out.println("nums --> " + Arrays.toString(nums));
            return nums.length;
        }
    }
}
