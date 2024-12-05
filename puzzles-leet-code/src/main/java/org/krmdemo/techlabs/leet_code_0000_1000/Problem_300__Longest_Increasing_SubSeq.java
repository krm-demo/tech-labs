package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/longest-increasing-subsequence/description/">
 *     300. Longest Increasing Subsequence
 * </a></h3>
 * Given an integer array <code>nums</code>, return the length of
 * the longest strictly increasing subsequence.
 * <p><small>A <b>subsequence</b> is an array that can be derived from another array
 * by deleting some or no elements without changing the order of the remaining elements.
 * </small></p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_increasing_subsequence">
 *      (wiki) Longest increasing subsequence
 * </a>
 * @see <a href="https://www.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1">
 *      (GFG Puzzle) Longest Increasing Subsequence
 * </a>
 */
public interface Problem_300__Longest_Increasing_SubSeq {

    /**
     * Solution entry-point
     *
     * @param nums an array of any integers
     * @return the length of the longest strictly increasing subsequence
     */
    int lengthOfLIS(int[] nums);

    enum Solution implements Problem_300__Longest_Increasing_SubSeq {
        BINARY_SEARCH_OWN {
            /**
             * This working version of binary-search is a good example when the located value
             * is going to either override/substitute the smaller value or add the new one to the head or to the tail.
             * <hr/>
             * Unlike the standard JDK {@link Collections#binarySearch(List, Object)} it does not return
             * the negative value, but the value of index ready to use for substitution
             *
             * @param valuesList the {@link List} of integers to look up the value
             * @param value the value either to find for substitution/overriding or
             *              for inserting to the head or to the tail
             * @return the index to perform subsequent {@link List#add(Object)} or {@link List#set(int, Object)}
             */
            @Override
            int binarySearchToSubst(List<Integer> valuesList, int value) {
                int left = 0;
                int right = valuesList.size() - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    int midValue = valuesList.get(mid);
                    if (midValue == value) {
                        return mid;
                    }
                    if (midValue < value) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                return left; // <-- always positive value within the index-range of a given list
            }
        },
        BINARY_SEARCH_JDK {
            /**
             * Delegating to {@link Collections#binarySearch(List, Object)}
             * and the negative value is converted to the positive with adding <code>1</code>.
             *
             * @param valuesList the {@link List} of integers to look up the value
             * @param value the value either to find for substitution/overriding or
             *              for inserting to the head or to the tail
             * @return the index to perform subsequent {@link List#add(Object)} or {@link List#set(int, Object)}
             */
            @Override
            int binarySearchToSubst(List<Integer> valuesList, int value) {
                int index = Collections.binarySearch(valuesList, value);
                return index >= 0 ? index : -(index + 1);
            }
        };

        public int lengthOfLIS(int[] nums) {
            System.out.println("... lengthOfLIS ( " + Arrays.toString(nums) + " ) ...");
            ArrayList<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);

            System.out.println("initially (i = 0) sub -> " + sub);
            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                if (num > sub.getLast()) {
                    sub.add(num);
                    System.out.printf("(nums[%d] = %d) adding to sub --> %s;%n", i, num, sub);
                } else {
                    int j = binarySearchToSubst(sub, num);
                    sub.set(j, num);
                    System.out.printf("(nums[%d] = %d) setting at #%d --> %s;%n", i, num, j, sub);

                }
            }
            System.out.println("returning --> " + sub);
            return sub.size();
        }

        /**
         * @param valuesList the list to look up the value
         * @param value the value either to find for substitution/overriding or
         *              for inserting to the head or to the tail
         * @return the index to perform subsequent {@link List#add(Object)} or {@link List#set(int, Object)}
         */
        abstract int binarySearchToSubst(List<Integer> valuesList, int value);
    }
}
