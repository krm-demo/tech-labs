package org.krmdemo.techlabs.leet_code_2000_3000;

import java.util.*;
import java.util.stream.Stream;

/**
 * <h3><a href="https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     2461. Maximum Sum of Distinct Subarrays With Length <code>K</code>
 * </a></h3>
 * You are given an integer array <b><code>nums</code></b> and an integer <b><code>K</code></b>.
 * Find the maximum sub-array sum of all the sub-arrays of <code>nums</code> that meet the following conditions:<ul>
 * <li>The length of the subarray is <code>K</code></li>
 * <li>All the elements of the subarray are distinct.</li>
 * </ul>
 * Return the maximum sub-array sum of all the sub-arrays that meet the conditions.
 * If no sub-array meets the conditions, return <b><code>0</code></b>.
 * <hr/>
 * A <i>sub-array</i> is a contiguous non-empty sequence of elements within an array.
 * <h5>Constraints:</h5><pre>
 * 1 <= K <= nums.length <= 10^5
 *      1 <= nums[i] <= 10^5
 * </pre>
 */
public interface Problem_2461__MaxSum_Distinct_SubArr {

    /**
     * Solution entry-point
     *
     * @param nums an array of integers
     * @param K the length of sub-arrays to find
     * @return the maximum sum of distinct sub-array of length <code>K</code>
     */
    long maximumSubarraySum(int[] nums, int K);

    enum Solution implements Problem_2461__MaxSum_Distinct_SubArr {
        /**
         * This approach is based on sliding-window as a
         * {@link org.krmdemo.techlabs.utils.CountingUtils#countingMap(Stream) counting map}.
         */
        COUNTING_MAP {
            @Override
            public long maximumSubarraySum(int[] nums, int K) {
                if (nums == null || K <= 0 || K > nums.length) {
                    // the length of sub-array is greater than the size of the whole array
                    System.out.println("maximumSubarraySum - invalid array or value of K:" + K);
                    return 0;
                }
                System.out.printf("maximumSubarraySum( nums.lemgth = %d, K = %d ) - %s %n",
                    nums.length, K, Arrays.toString(nums));
//            SequencedMap<Integer, Integer> cntMap = Arrays.stream(nums)
//                .limit(K)
//                .boxed()
//                .collect(groupingBy(
//                    Function.identity(),
//                    LinkedHashMap::new,
//                    summingInt(v -> 1)));
                long sum = 0;
                long maxSum = Long.MIN_VALUE;
                Map<Integer, Integer> cntMap = new HashMap<>();
                for (int i = 0; i < nums.length; i++) {
                    int value = nums[i];
                    sum += value;
                    cntMap.merge(value, 1, Integer::sum);
                    if (i >= K) {
                        int firstValue = nums[i - K];
                        sum -= firstValue;
                        int firstValueCount = cntMap.get(firstValue);
                        if (firstValueCount > 1) {
                            cntMap.put(firstValue, firstValueCount - 1);
                        } else {
                            cntMap.remove(firstValue);
                        }
                    }
                    int start = Math.max(0, i + 1 - K);
                    System.out.printf("%2d) %d :: %s <--> %s :: ",
                        i, value, Arrays.stream(nums, start, i + 1).boxed().toList(), cntMap);
                    if (cntMap.size() == K) {
                        // if the size of counting-map equals to the number of elements,
                        // it automatically means that all counters equal to "1",
                        // in other words - it corresponds to distinct sub-array
                        maxSum = Math.max(maxSum, sum);
                        System.out.printf("sum = %d, maxSum = %d;%n", sum, maxSum);
                    } else {
                        System.out.println(i >= K - 1 ? "non distinct" : "too short sub-array");
                    }
                }
                maxSum = maxSum == Long.MIN_VALUE ? 0 : maxSum;
                System.out.println("- returning: " + maxSum);
                return maxSum;
            }
        },
        /**
         * In this approach also uses sliding window with the last index of checked value
         *
         * @see <a href="https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/editorial/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
         *      (taken from Leet-Code puzzle's editorial)
         * </a>
         */
        LAST_INDEX_MAP {
            @Override
            public long maximumSubarraySum(int[] nums, int K) {
                long ans = 0;
                long currentSum = 0;
                int begin = 0;
                int end = 0;
                Map<Integer, Integer> numToIndex = new HashMap<>();
                while (end < nums.length) {
                    int currNum = nums[end];
                    int lastOccurrence = numToIndex.getOrDefault(currNum, -1);
                    // if current window already has number or if window is too big, adjust window
                    while (begin <= lastOccurrence || end - begin + 1 > K) {
                        currentSum -= nums[begin];
                        begin++;
                    }
                    numToIndex.put(currNum, end);
                    currentSum += nums[end];
                    if (end - begin + 1 == K) {
                        ans = Math.max(ans, currentSum);
                    }
                    end++;
                }
                return ans;
            }
        },
        /**
         * Quite the same sliding window appraoch as above, but just with {@link HashSet}
         *
         * @see <a href="https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/solutions/6060159/beats-100-video-list-most-common-array-interview/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
         *      (taken from solution by <b>Piotr Maminski</b>)
         * </a>
         */
        HASH_SET {
            @Override
            public long maximumSubarraySum(int[] nums, int k) {
                long res = 0;
                Map<Integer, Integer> count = new HashMap<>();
                long curSum = 0;
                int l = 0;
                for (int r = 0; r < nums.length; r++) {
                    curSum += nums[r];
                    count.put(nums[r], count.getOrDefault(nums[r], 0) + 1);
                    if (r - l + 1 > k) {
                        count.put(nums[l], count.get(nums[l]) - 1);
                        if (count.get(nums[l]) == 0) {
                            count.remove(nums[l]);
                        }
                        curSum -= nums[l];
                        l++;
                    }
                    if (count.size() == r - l + 1 && r - l + 1 == k) {
                        res = Math.max(res, curSum);
                    }
                }
                return res;
            }
        }
    }
}
