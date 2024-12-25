package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1">
 *     Allocate Minimum Pages
 * </a></h3>
 * You are given an array <b><code>arr[]</code></b> of integers,
 * where each element <code>arr[<b>i</b>]</code> represents the number of pages in the <b>i</b>th book.
 * You also have an integer <b><code>k</code></b> representing the number of students.
 * The task is to allocate books to each student such that:<ul>
 *     <li>Each student receives atleast one book</li>
 *     <li>Each student is assigned a contiguous sequence of books</li>
 *     <li>No book is assigned to more than one student</li>
 * </ul>
 * The objective is to minimize the maximum number of pages assigned to any student.
 * In other words, out of all possible allocations, find the arrangement where the student
 * who receives the most pages still has the smallest possible maximum
 * <hr/>
 * Return <b><code>-1</code></b> if a valid assignment is not possible,
 * and allotment should be in contiguous order (see the explanation for better understanding).
 * <h5>Constraints:</h5><pre>
 * 1 <=  arr.size() <= 10^6
 *   1 <= arr[i] <= 10^3
 *     1 <= k <= 10^3
 * </pre>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_410__Split_Array_Largest_Sum
 */
public interface GFG_Arrays__Allocate_Min_Pages {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array of integers , where each element <code>arr[i]</code>
     *            represents the number of pages in the <b>i</b>th book
     * @param k the number of students
     * @return the minimal possible value of maximum total number of pages for each student
     */
    int findPages(int[] arr, int k);

    enum Solution implements GFG_Arrays__Allocate_Min_Pages {
        DEFAULT;

        @Override
        public int findPages(int[] arr, int k) {
            if (k > arr.length) {
                return -1; // <-- at Leet-Code such case is not possible, but here whe need this check
            }
            int maxValue = Arrays.stream(arr).max().orElseThrow(IllegalArgumentException::new);
            int totalSum = Arrays.stream(arr).sum();
            // bellow is very non-trivial binary search over the target maximum sum of each sub-array
            // - so, we are not looking for the way how to split the array - our optimization argument is the target sum;
            // - we are trying to perform such split for concrete target sum, minimizing the value of that sum;
            // - the main assumption that such sum MUST INCLUDE the value of maximum element;
            // - and another assumption that such sum MUST NOT EXCEED the total sum
            //   (which is possible only if elements are NOT negative);
            int left = maxValue;
            int right = totalSum;

            int minimizedTargetSum = 0;
            while (left <= right) {
                int targetSum = left + (right - left) / 2;
                int subArraysRequired = splitsRequired(targetSum, arr) + 1;
                if (subArraysRequired <= k) {
                    // if number of splits is too low - the target sum should be decreased (down to maxElement)
                    right = targetSum - 1;
                    minimizedTargetSum = targetSum;  // <-- binary-search is not stopped, we try to decrease more
                } else {
                    // if number of splits is too high - the target sum should be increased (up to totalSum)
                    left = targetSum + 1;
                }
            }
            return minimizedTargetSum; // <-- never get here if elements are positive
        }

        private int splitsRequired(int targetSum, int[] nums) {
            int sum = 0;
            int splitsCount = 0;
            for (int numValue : nums) {
                if (sum + numValue > targetSum) {
                    sum = numValue;
                    splitsCount++;
                } else {
                    sum += numValue;
                }
            }
            return splitsCount;
        }
    }
}
