package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/house-robber-ii/">
 *     213. House Robber II
 * </a></h3>
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are <b>arranged in a circle</b>.
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if <b>two adjacent houses</b> were broken into on the same night.
 * <hr/>
 * Given an integer array <b><code>nums</code></b> representing the amount of money of each house,
 * return the <b>maximum amount of money</b> you can rob tonight without alerting the police.
 * <h5>Constraints:</h5><pre>
 * 1 <= nums.length <= 100
 *   0 <= nums[i] <= 1000</pre>
 *
 * @see Problem_198__House_Robber
 */
public interface Problem_213__House_Robber_II {

    /**
     * Solution entry-point.
     *
     * @param nums the amount of money in each house
     * @return <b>maximum amount of money</b> you can rob tonight without alerting the police
     */
    int rob(int[] nums);

    enum Solution implements Problem_213__House_Robber_II {
        DEFAULT;

        @Override
        public int rob(int[] nums) {
            int maxFromZero = robLinear(nums, 0, nums.length - 1);
            int maxFromOne = robLinear(nums, 1, nums.length);
            return Math.max(maxFromZero, maxFromOne);
        }

        /**
         * The same as {@link Problem_198__House_Robber.Solution#rob(int[])},
         * but for range of indexes with passed boundaries.
         *
         * @param nums the amount of money in each house
         * @param indexStart the starting index of houses range (inclusive)
         * @param indexEnd the ending index of houses range (inclusive)
         * @return the result of linear puzzle (not considering the circle ring)
         */
        private int robLinear(int[] nums, int indexStart, int indexEnd) {
            int sumIncl = 0;
            int sumExcl = 0;
            for (int index = indexStart; index < indexEnd; index++) {
                int value = nums[index];
                int sumPrev = Math.max(sumIncl, sumExcl);
                sumIncl = value + sumExcl;
                sumExcl = sumPrev;
            }
            return Math.max(sumIncl, sumExcl);
        }
    }
}
