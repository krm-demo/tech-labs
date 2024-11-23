package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150">
 *     26. Remove Duplicates from Sorted Array
 * </a></h3>
 * Given an integer array <b><code>nums</code></b> sorted in <b>non-decreasing order</b>,
 * remove the duplicates in-place such that each unique element appears <b>only once</b>.
 * The <b>relative order</b> of the elements should be kept the same.
 * Then return the number of unique elements in <b><code>nums</code></b>.
 *
 * @see Problem_080__Remove_Duplicates_Sorted_Arr_II
 */
public interface Problem_026__Remove_Duplicates_Sorted_Arr {

    /**
     * Solution entry-point.
     *
     * @param nums a sorted array of integer with duplicates
     * @return the same array with all duplicates removed and squashed to the start of array
     */
    int removeDuplicates(int[] nums);

    enum Solution implements Problem_026__Remove_Duplicates_Sorted_Arr {
        DEFAULT;

        @Override
        public int removeDuplicates(int[] nums) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i == 0 || nums[i-1] != nums[i]) {
                    nums[count++] = nums[i];
                }
            }
            return count;
        }
    }
}
