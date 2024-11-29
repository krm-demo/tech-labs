package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/">
 *     315. Count of Smaller Numbers After Self
 * </a></h3>
 * Given an integer array <code>nums</code>, return an integer array <code>counts</code>
 * where <code>counts[<b>i</b>]</code> is the number of smaller elements
 * to the right of <code>nums[<b>i</b>]</code>.
 * <h5>Constraints:</h5><pre>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public interface Problem_315__Count_Smaller_Numbers {

    /**
     * Solution entry-point.
     *
     * @param nums an integer array
     * @return the list of the number of smaller elements after given index
     */
    List<Integer> countSmaller(int[] nums);

    enum Solution implements Problem_315__Count_Smaller_Numbers {
        /**
         * This simplest approach results in "Time Limit Exceeded" - TODO: optimize using Segment-Tree
         */
        TREE_SET {
            @Override
            public List<Integer> countSmaller(int[] nums) {
                Integer[] smallerCountArr = new Integer[nums.length];
                NavigableSet<ValueIndex> navSet = new TreeSet<>();
                for (int i = nums.length - 1; i >= 0; i--) {
                    ValueIndex vi = new ValueIndex(nums[i], i);
                    smallerCountArr[i] = navSet.headSet(vi).size();
                    navSet.add(new ValueIndex(nums[i], i));
                }
                return Arrays.asList(smallerCountArr);
            }
            record ValueIndex(int value, int index) implements Comparable<ValueIndex> {
                @Override
                public int compareTo(ValueIndex other) {
                    if (this.value != other.value) {
                        return this.value - other.value;
                    } else {
                        return this.index - other.index;
                    }
                }
            }
        }
    }
}
