package org.krmdemo.techlabs.gfg_arrays;

import org.krmdemo.techlabs.utils.BinarySearchUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1">
 *     Merge Without Extra Space
 * </a></h3>
 * Given two sorted arrays <b><code>a[]</code></b> and <b><code>b[]</code></b>
 * of size <b><code>n</code></b> and <b><code>m</code></b> respectively,
 * the task is to merge them in sorted order <b>without using any extra space</b>.
 * Modify <b><code>a[]</code></b> so that it contains the first <b><code>n</code></b> elements
 * and modify <b><code>b[]</code></b> so that it contains the last <b><code>m</code></b> elements.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ a.size(), b.size() ≤ 10^5
 *     1 ≤ a[i], b[i] ≤ 10^7
 * </pre>
 *
 * @see GFG_Arrays__Merge_Sort
 */
public interface GFG_Arrays__Merge_No_Extra_Space {

    /**
     * GFG-Solution entry-point
     *
     * @param a the first sorted array of integers
     * @param b the second sorted array of integers
     */
    void mergeArrays(int[] a, int[] b);

    void flip(int[] arr, int left, int right, int flipIndex);

    String dumpFlipArr(int[] arr, int left, int right, int flipIndex);

    enum Solution implements GFG_Arrays__Merge_No_Extra_Space {
        FLIP_MERGE;

        @Override
        public void mergeArrays(int[] a, int[] b) {
            int[] arr = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).toArray();
            flipMerge(arr, 0, a.length + b.length, a.length);
            System.arraycopy(arr, 0, a, 0, a.length);
            System.arraycopy(arr, a.length, b, 0, b.length);
        }

        public void flipMerge(int[] arr, int left, int right, int flipIndex) {
            int count = 0;
            int initialLeft = left;
            int initialRight = right;
            while (left < flipIndex && flipIndex < right) {
                System.out.printf("[count = %d] --> %s; flipIndex = %d;%n",
                    ++count, dumpFlipArr(arr, left, right, flipIndex), flipIndex);
                int valueLeftLast = arr[flipIndex - 1];
                int valueRightFirst = arr[flipIndex];
                int leftHead = BinarySearchUtils.countNotGreater(arr, left, flipIndex, valueRightFirst);
                int rightTail = BinarySearchUtils.countNotLess(arr, flipIndex, right, valueLeftLast);
                if (left + leftHead >= flipIndex || right - rightTail <= flipIndex) {
                    System.out.printf("- left(%d) + leftHead(%d) = %d;%n", left, leftHead, left + leftHead);
                    System.out.printf("- right(%d) - rightTail(%d) = %d;%n", right, rightTail, right - rightTail);
                    System.out.printf("- flipIndex = %d :: end of flip-merge !!!%n", flipIndex);
                    break;
                }
                int valueLeftFirst = arr[left + leftHead];
                int valueRightLast = arr[right - 1 - rightTail];
                int leftTail = BinarySearchUtils.countGreater(arr, left + leftHead, flipIndex, valueRightLast);
                int rightHead = BinarySearchUtils.countLess(arr, flipIndex, right - rightTail, valueLeftFirst);
                System.out.printf("- valueLeftFirst = %-2d, valueLeftLast = %-2d;%n", valueLeftFirst, valueLeftLast);
                System.out.printf("- valueRightFirst = %-2d, valueRightLast = %-2d;%n", valueRightFirst, valueRightLast);
                System.out.printf("- left { head: %d, mid: %d, tail: %d }%n",
                    leftHead, flipIndex - left - leftHead - leftTail, leftTail);
                System.out.printf("- right { head: %d, mid: %d, tail: %d }%n",
                    rightHead, right - flipIndex - rightHead - rightTail, rightTail);
                System.out.printf("- (0) %s; %n", dumpFlipParts(arr,
                    left, left + leftHead, flipIndex - leftTail,
                    flipIndex, flipIndex + rightHead, right - rightTail, right));
                // { lH, lM, lT, rH, rM, rT } --> { lH, lM, rH, lT, rM, rT }
                flip(arr, flipIndex - leftTail, flipIndex + rightHead, flipIndex);
                System.out.printf("- (1) %s; %n", dumpFlipParts(arr,
                    left, left + leftHead, flipIndex - leftTail,
                    flipIndex - leftTail + rightHead, flipIndex + rightHead, right - rightTail, right));
                // { lH, lM, rH, lT, rM, rT } --> { lH, rH, lM, lT, rM, rT }
                flip(arr, left + leftHead, flipIndex - leftTail + rightHead, flipIndex - leftTail);
                System.out.printf("- (2) %s; %n", dumpFlipParts(arr,
                    left, left + leftHead, left + leftHead + rightHead,
                    flipIndex - leftTail + rightHead, flipIndex + rightHead, right - rightTail, right));
                // { lH, rH, lM, lT, rM, rT } --> { lH, rH, lM, rM, lT, rT }
                flip(arr, flipIndex - leftTail + rightHead, right - rightTail, flipIndex + rightHead);
                System.out.printf("- (3) %s; %n", dumpFlipParts(arr,
                    left, left + leftHead, left + leftHead + rightHead,
                    flipIndex - leftTail + rightHead, right - leftTail - rightTail, right - rightTail, right));
                flipIndex += rightHead - leftTail;
                left += leftHead;
                right -= rightTail;
            }
            System.out.printf("finally [count = %d] --> %s; flipIndex = %d;%n",
                count, dumpFlipArr(arr, initialLeft, initialRight, flipIndex), flipIndex);
        }

        @Override
        public void flip(int[] arr, int left, int right, int flipIndex) {
            reverse(arr, left, flipIndex);
            reverse(arr, flipIndex, right);
            reverse(arr, left, right);
        }

        private void reverse(int[] arr, int left, int right) {
            right--;
            while (left < right) {
                swap(arr, left++, right--);
            }
        }

        private void swap(int[] arr, int left, int right) {
            int valueLeft = arr[left];
            arr[left] = arr[right];
            arr[right] = valueLeft;
        }

        @Override
        public String dumpFlipArr(int[] arr, int left, int right, int flipIndex) {
            return IntStream.range(left, right)
                .mapToObj(i -> i == flipIndex ? "(" + arr[i] +")" : "" + arr[i])
                .collect(Collectors.joining(",",
                    String.format("{ #%d :: ", left),
                    String.format(" :: #%s }", flipIndex == right ? "(" + right + ")" : "" + right)
                    ));
        }

        private String dumpFlipParts(int[] arr, int... indexes) {
            if (indexes.length != 7) {
                throw new IllegalArgumentException("wrong number of indexes (not 6) - " + indexes.length);
            }
            String leftPart = IntStream.range(0, 3)
                .mapToObj(i -> dumpSubArray(arr, indexes[i], indexes[i+1]))
                .collect(Collectors.joining("|"));
            String rightPart = IntStream.range(3, 6)
                .mapToObj(i -> dumpSubArray(arr, indexes[i], indexes[i+1]))
                .collect(Collectors.joining("|"));
            return "...:" + leftPart + "::" + rightPart + ":...";
        }

        private String dumpSubArray(int[] arr, int left, int right) {
            return Arrays.stream(arr, left, right)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        }
    }
}
