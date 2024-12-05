package org.krmdemo.techlabs.leet_code_1000_2000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/">
 *     1671. Minimum Number of Removals to Make Mountain Array
 * </a></h3>
 * You may recall that an array <code>arr</code> is a <i>mountain</i> array if and only if:<ul>
 *     <li><code>arr.length >= 3</code></li>
 *     <li>There exists some index <code>i</code> (<code>0</code>-indexed)
 *          with <code>0 < i < arr.length - 1</code> such that:<pre>
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]</pre></li>
 * </ul>
 * <hr/>
 * Given an integer array <b><code>nums</code></b>,
 * return the minimum number of elements to remove
 * to make <b><code>nums</code></b> a <i>mountain</i> array.
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_300__Longest_Increasing_SubSeq
 */
public interface Problem_1671__Making_Mountain_Array_Min_Removals {

    /**
     * Solution entry-point
     *
     * @param nums an array of positive integers (no idea, whether positiveness is really important)
     * @return the number of removals to make the array <b><code>nums</code></b> to be <i>mountain</i>
     */
    int minimumMountainRemovals(int[] nums);

    /**
     * Calculating the length of the <b>longest increasing subsequence</b>, that is <b>preceding</b> each element
     *
     * @param nums the array of integer to calculate the length of the LIS, that is preceding each element
     * @return the array of LIS-lengths for corresponding element in array <code>nums</code>
     */
    int[] lengthArrLIS(int[] nums);

    /**
     * Calculating the length of the <b>longest decreasing subsequence</b>, that <b>follows</b> each element
     *
     * @param nums the array of integer to calculate the length of the LDS, that follows each element
     * @return the array of LDS-lengths for corresponding element in array <code>nums</code>
     */
    int[] lengthArrLDS(int[] nums);

    enum Solution implements Problem_1671__Making_Mountain_Array_Min_Removals {
        DEFAULT;

        @Override
        public int minimumMountainRemovals(int[] nums) {
            final int N = nums.length;
            int[] longestBefore = lengthArrLIS(nums);
            int[] longestAfter = lengthArrLDS(nums);
            System.out.println("initial nums --> " + Arrays.toString(nums));
            System.out.println("LIS before.  --> " + Arrays.toString(longestBefore));
            System.out.println("LDS after    --> " + Arrays.toString(longestAfter));
            int minRomovals = N;
            for (int i = 0; i < N; i++) {
                System.out.printf("(%2d): %d<=[%d]=>%d --> LIS before: %d, LDS after: %d",
                    nums[i], i + 1, i, N - i, longestBefore[i], longestAfter[i]);
                if (longestBefore[i] < 2 || longestAfter[i] < 2) {
                    System.out.println(":: skip, no mountain");
                    continue;
                }
                int mountainLength = longestBefore[i] + longestAfter[i] - 1;
                minRomovals = Math.min(minRomovals, N - mountainLength);
                System.out.printf(":: mountainLength: %d, removals: %d from %d, minRomovals = %d;%n",
                    mountainLength, N - mountainLength, N, minRomovals);
            }
            return minRomovals;
        }

        @Override
        public int[] lengthArrLIS(int[] nums) {
            int[] longestLengthArr = new int[nums.length];
            List<Integer> incrSubSeq = new ArrayList<>();
            incrSubSeq.add(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                int value = nums[i];
                if (value > incrSubSeq.getLast()) {
                    incrSubSeq.add(value);
                } else {
                    int index = Collections.binarySearch(incrSubSeq, value);
                    index = index >= 0 ? index : -(index + 1);
                    incrSubSeq.set(index, value);
                }
                longestLengthArr[i] = incrSubSeq.size();
            }
            return longestLengthArr;
        }

        @Override
        public int[] lengthArrLDS(int[] nums) {
            final int N = nums.length;
            int[] longestLengthArr = new int[N];
            List<Integer> incrSubSeq = new ArrayList<>();
            incrSubSeq.add(nums[N - 1]);
            for (int i = N - 2; i >= 0; i--) {
                int value = nums[i];
                if (value > incrSubSeq.getLast()) {
                    incrSubSeq.add(value);
                } else {
                    int index = Collections.binarySearch(incrSubSeq, value);
                    index = index >= 0 ? index : -(index + 1);
                    incrSubSeq.set(index, value);
                }
                longestLengthArr[i] = incrSubSeq.size();
            }
            return longestLengthArr;
        }
    }
}
