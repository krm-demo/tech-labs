package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * <h3><a href="https://leetcode.com/problems/next-permutation">
 *     31. Next Permutation
 * </a></h3>
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <br/>
 * For example, for <code>arr=[1,2,3]</code>, the following are all the permutations of <code>arr</code>:
 * <pre>    [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]    </pre>
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container
 * according to their lexicographical order, then the next permutation of that array is the permutation
 * that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged
 * as the lowest possible order (i.e., sorted in ascending order). <ul>
 * <li>For example, the next permutation of arr = [1,2,3] is [1,3,2].</li>
 * <li>Similarly, the next permutation of arr = [2,3,1] is [3,1,2].</li>
 * <li>While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1]
 * does not have a lexicographical larger rearrangement.</li>
 * </ul>
 * Given an array of integers <b><code>nums</code></b>, find the next permutation of <b><code>nums</code></b>.
 * <p/>
 * The replacement must be <i><b>in place</b></i> and use only constant extra memory.
 */
public interface Problem_031__Next_Permutation {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers (<b>not distinct</b>, but could have duplications)
     */
    void nextPermutation(int[] nums);

    enum Solution implements Problem_031__Next_Permutation {

        /**
         * the source code of C++ implementation in STL looks like following:
         * <pre>
         * #include &lt;algorithm>
         *
         * template &lt;typename BidirectionalIterator>
         * bool next_permutation(BidirectionalIterator first, BidirectionalIterator last) {
         *     if (first == last) return false;
         *     BidirectionalIterator i = last;
         *     if (first == --i) return false;
         *
         *     while (true) {
         *         BidirectionalIterator i1 = i;
         *         if (*--i < *i1) {
         *             BidirectionalIterator j = last;
         *             while (!(*i < *--j));
         *             std::iter_swap(i, j);
         *             std::reverse(i1, last);
         *             return true;
         *         }
         *         if (i == first) {
         *             std::reverse(first, last);
         *             return false;
         *         }
         *     }
         * }
         * </pre>
         */
        DEFAULT;

        @Override
        public void nextPermutation(int[] nums) {
            int indexOne = lastAscendingPair(nums);
            if (indexOne >= 0) {
                int indexTwo = indexOfCeil(nums, indexOne + 1, nums[indexOne]);
                swap(nums, indexOne, indexTwo);
            }
            reverseTail(nums, indexOne + 1);
        }

        /**
         * Swap two elements of array
         *
         * @param valuesArr an array whose elements to swap
         * @param indexOne the first index to swap
         * @param indexTwo the second index to swap
         */
        private static void swap(int[] valuesArr, int indexOne, int indexTwo) {
            int valueOne = valuesArr[indexOne];
            valuesArr[indexOne] = valuesArr[indexTwo];
            valuesArr[indexTwo] = valueOne;
        }

        /**
         * Reverse the tail of array since the passed index <code>left</code> (inclusive)
         *
         * @param nums an array whose tail to reverse
         * @param left the index since the rest of array to be reversed
         */
        private static void reverseTail(int[] nums, int left) {
            int right = nums.length - 1;
            while (left < right) swap(nums, left++, right--);
        }

        /**
         * Looking for the first consecutive pair that violates
         * the tail of array to be sorted in descending order
         *
         * @param nums an array whose tail is verifying for violation of sorting order
         * @return the last index of element whose subsequent neighbour is greater than it
         */
        private static int lastAscendingPair(int[] nums) {
            int ascPairStart = nums.length - 2;
            while (ascPairStart >= 0 && nums[ascPairStart] >= nums[ascPairStart+1]) {
                ascPairStart--;
            }
            return ascPairStart;  // <-- "-1" means that array is sorted in descending order
        }

        /**
         * Looking for the index of element in the tail of passed array <b><code>nums</code></b>
         * since the index begin, whose value is greater than passed value <b><code>valueToCeil</code></b>,
         * but is the smallest one among other elements in the given tail of array.
         * The tail of array is already sorted in descending order,
         * so - it's just enough to go from the end and pick the item,
         * which is greater than passed parameter <code>valueToCeil</code>
         * <hr/>
         * Semantic of the method name is similar to {@link Math#ceil(double)}
         * or to {@link NavigableSet#ceiling(Object)}
         *
         * @param nums an array in whose tail the proper element is looking for
         * @param begin the start of the array's tail <code>0 <= begin < nums.length</code>
         * @param valueToCeil a pivot-value (the lower boundary) of searching element
         * @return the index of the smallest element in the tail, which is greater than
         */
        private int indexOfCeil(int[] nums, int begin, int valueToCeil) {
            for (int i = nums.length - 1; i > begin; i--) {
                if (nums[i] > valueToCeil) {
                    return i;
                }
            }
            return begin;
        }
    }

    static String dumpTail(int[] nums, int begin) {
        String prefix = begin == 0 ? "{" : "[#" + begin + ": --> ";
        String suffix = begin == 0 ? "}" : "]";
        return range(begin, nums.length).boxed()
            .map(index -> "" + nums[index])
            .collect(joining(",", prefix, suffix));
    }

    static String dump(int[] nums) {
        return dumpTail(nums, 0);
    }
}
