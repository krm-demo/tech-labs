package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150">
 *     55. Jump Game
 * </a></h3>
 * You are given an integer array <b><code>nums</code></b>.
 * You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * <hr/>
 * Return <code>true</code> if you can reach the last index, or <code>false</code> otherwise.
 * <h4>Constraints:</h4><pre>
 *  1 <= nums.length <= 10^4
 *  0 <= nums[i] <= 10^5</pre>
 * TODO: write test-case
 */
public interface Problem_055__Jump_Game {

    /**
     * Solution entry-point.
     *
     * @param nums an array with maximum forward-jump length at that position
     * @return <code>true</code> if you can reach the last index, or <code>false</code> otherwise
     */
    boolean canJump(int[] nums);

    enum Solution implements Problem_055__Jump_Game {
        DEFAULT;

        @Override
        public boolean canJump(int[] nums) {
            BitSet reachable = new BitSet();
            reachable.set(0);
            for (int i = 0; i >= 0 && i < nums.length; i = reachable.nextSetBit(i + 1)) {
                if (nums[i] > 0) {
                    reachable.set(i + 1, i + nums[i] + 1);
                }
                if (reachable.get(nums.length - 1)) {
                    return true;
                }
            }
            return false;
//            for (int i = 0; i >= 0 && i < nums.length; i = reachable.nextSetBit(i + 1)) {
//                if (!reachable.get(i)) {
//                    continue;
//                }
//                if (nums[i] > 0) {
//                    reachable.set(i + 1, i + nums[i] + 1);
//                }
//            }
//            return reachable.get(nums.length - 1);
        }
    }
}
