package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/contiguous-array/description/">
 *     525. Contiguous Array (longest binary sub-array with equal counts)
 * </a></h3>
 * Given a binary array <b><code>nums</code></b>, return the maximum length
 * of a contiguous sub-array with an equal number of <code>0</code>s and <code>1</code>s.
 */
public interface Problem_525__Contiguos_Binary_SubArray {

    /**
     * Solution entry-point.
     *
     * @param nums a binary array (which contains only <code>0</code>s and <code>1</code>s)
     * @return the maximum length of a contiguous sub-array where counts of <code>0</code>s and <code>1</code>s are the same
     */
    int findMaxLength(int[] nums);

    enum Solution implements Problem_525__Contiguos_Binary_SubArray {
        DEFAULT;

        @Override
        public int findMaxLength(int[] nums) {
            // TODO: to be implemented
            return 0;
        }
    }
}
