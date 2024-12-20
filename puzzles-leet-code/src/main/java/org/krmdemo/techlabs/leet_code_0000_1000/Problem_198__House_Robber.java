package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/house-robber/description/">
 *     198. House Robber
 * </a></h3>
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
 * is that adjacent houses have security systems connected, and it will automatically contact the police
 * if <b>two adjacent houses</b> were broken into on the same night.
 * <hr/>
 * Given an integer array <b><code>nums</code></b> representing the amount of money of each house,
 * return the <b>maximum amount of money</b> you can rob tonight without alerting the police.
 * <h5>Constraints:</h5><pre>
 * 1 <= nums.length <= 100
 *   0 <= nums[i] <= 400</pre>
 */
public interface Problem_198__House_Robber {

    /**
     * Solution entry-point.
     *
     * @param nums the amount of money in each house
     * @return <b>maximum amount of money</b> you can rob tonight without alerting the police
     */
    int rob(int[] nums);

    enum Solution implements Problem_198__House_Robber {
        DEFAULT;

        @Override
        public int rob(int[] nums) {
            int sumIncl = 0;
            int sumExcl = 0;
            for (int value : nums) {
                int sumPrev = Math.max(sumIncl, sumExcl);
                sumIncl = value + sumExcl;
                sumExcl = sumPrev;
            }
            return Math.max(sumIncl, sumExcl);
        }
    }
}
