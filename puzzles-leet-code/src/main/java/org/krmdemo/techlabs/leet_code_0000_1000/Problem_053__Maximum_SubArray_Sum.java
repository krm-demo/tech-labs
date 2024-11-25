package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntBiFunction;
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
 *
 * @see Problem_121__Buy_And_Sell_Stock
 * @see Problem_122__Buy_And_Sell_Stock_II
 * @see <a href="https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d">
 *     Kadane’s Algorithm — (Dynamic Programming) — How and Why Does it Work?
 * </a>
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
        /**
         * This is a classic original implementation of
         * @see <a href="https://leetcodethehardway.com/tutorials/basic-topics/kadane">
         *     Kadane's Algorithm
         *     </a>
         */
        KADANE_CLASSIC {
            @Override
            public int maxSubArray(int[] nums) {
                int sum = 0;
                int maxSumDiff = Integer.MIN_VALUE;
                for (int value : nums) {
                    sum += value;
                    maxSumDiff = Math.max(maxSumDiff, sum);
                    if (sum < 0) {
                        // resetting the cumulative sum - is the key mystery
                        // of the original Kadane's algorithm
                        sum = 0;
                    }
                }
                return maxSumDiff;
            }
        },
        /**
         * This variation of classic Kadane's algorithm
         * is a little bit closer to streaming (loop-free) approach,
         * but here we avoid introducing the first zero-sum element,
         * like we do in the next two approaches.
         */
        KADANE_SUM_MIN_SO_FAR {
            @Override
            public int maxSubArray(int[] nums) {
                int sum = 0;
                int sumMinSoFar = 0;
                int maxSumDiff = Integer.MIN_VALUE;
                for (int value : nums) {
                    sum += value;
                    maxSumDiff = Math.max(maxSumDiff, sum - sumMinSoFar);
                    sumMinSoFar = Math.min(sumMinSoFar, sum);
                }
                return maxSumDiff;
            }
        },
        /**
         * This approach introduces 2 additional arrays in order
         * to get final functional version without loops and additional space.
         */
        CUM_SUM_PREV_MIN_DIFF {
            @Override
            public int maxSubArray(int[] nums) {
                int[] cumArr = cumulativeSum(nums);
                int[] cumMinArr = cumulativeMin(cumArr);
                System.out.println("cumArr    --> " + Arrays.toString(cumArr));
                System.out.println("cumMinArr --> " + Arrays.toString(cumMinArr));
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
        /**
         * Functional (streaming) version of Kadane's Algorithm
         */
        KADANE_STREAM {
            @Override
            public int maxSubArray(int[] nums) {
                return cumSum(nums)
                    .flatMap(prevMinDiff())
                    .max().orElseThrow();
            }

            private static IntStream cumSum(int[] arr) {
                IntStream withLeadingZero = IntStream.concat(
                    IntStream.of(0), Arrays.stream(arr)
                );
                return withLeadingZero.map(sumSoFar());
            }

            private static IntUnaryOperator sumSoFar() {
                int[] sum = new int[] { 0 };
                return currentValue -> (sum[0] += currentValue);
            }

            private static IntFunction<IntStream> prevMinDiff() {
                return withPrevMin((prevMin, next) -> next - prevMin);
            }

            private static IntFunction<IntStream> withPrevMin(
                ToIntBiFunction<Integer,Integer> withMinFunc) {
                Integer[] prevMin = new Integer[] { null };
                return value -> {
                    Integer prevMinValue = prevMin[0];
                    prevMin[0] = prevMinValue == null ? value : Math.min(prevMinValue, value);
                    return prevMinValue == null ? IntStream.empty() :
                        IntStream.of(withMinFunc.applyAsInt(prevMinValue, value));
                };
            }
        }
    }
}
