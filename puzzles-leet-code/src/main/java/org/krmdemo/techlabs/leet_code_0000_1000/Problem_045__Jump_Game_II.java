package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150">
 *     45. Jump Game II
 * </a></h3>
 * You are given a 0-indexed array of integers <b><code>nums</code></b> of length <code>n</code>.
 * You are initially positioned at <code>nums[0]</code>.
 * Each element <code>nums[i]</code> represents the maximum length of a forward jump from index <code>i</code>.
 * In other words, if you are at <code>nums[i]</code>, you can jump to any <code>nums[i + j]</code> where:<pre>
 * 0 <= j <= nums[i] and
 * i + j < n</pre>
 * Return the <i>minimum number of jumps to reach</i> <code>nums[n - 1]</code>.
 * The test cases are generated such that you can reach <code>nums[n - 1]</code>.
 * <h5>Constraints:</h5><pre>
 *  1 <= nums.length <= 10^4
 *  0 <= nums[i] <= 1000
 *  it's guaranteed that you can reach nums[n - 1]
 * </pre>
 * TODO: write test-case
 *
 * @see Problem_055__Jump_Game
 */
public interface Problem_045__Jump_Game_II {

    /**
     * Solution entry-point.
     *
     * @param nums an array with maximum forward-jump length at that position
     * @return minimum number of jumps to reach <code>nums[n - 1]</code>
     */
    int jump(int[] nums) ;

    enum Solution implements Problem_045__Jump_Game_II {
        DEFAULT;

        @Override
        public int jump(int[] nums) {
            int[] jumpsCount = new int[nums.length];
            Arrays.fill(jumpsCount, -1);
            jumpsCount[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                if (jumpsCount[i] < 0) {
                    continue;
                }
                int nextCount = jumpsCount[i] + 1;
                // it looks like folowing loop is redundant, but it's not evident why
                for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                    jumpsCount[i + j] = jumpsCount[i + j] < 0 ?
                        nextCount : Math.min(jumpsCount[i + j], nextCount);
                }
                // System.out.println(i + ") jumpsCount --> " +
                //     Arrays.toString(jumpsCount));
            }
            // System.out.println("finally jumpsCount --> " +
            //     Arrays.toString(jumpsCount));
            return jumpsCount[nums.length - 1];
        }
    }
}
