package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/UNKNOWN-LINK">
 *     Merge Sort
 * </a></h3>
 * Given an array <b><code>arr[]</code></b>, its starting position <b><code>left</code></b>
 * and its ending position <b><code>right</code></b>. Sort the array using the <b>merge-sort</b> algorithm.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^5
 * 1 ≤ arr[i] ≤ 10^5
 * </pre>
 */
public interface GFG_Arrays__Merge_Sort {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array, whose sub-array is going to be sorted
     * @param left the left boundary of sub-array (inclusive)
     * @param right the right boundary of sub-array (<b>inclusive</b>)
     */
    void mergeSort(int[] arr, int left, int right);

    enum Solution implements GFG_Arrays__Merge_Sort {
        MERGE_HALF_COPY {
            @Override
            public void mergeSort(int[] arr, int left, int right) {
                if (left == 0 && right + 1 == arr.length) {
                    mergeSort(arr);
                    return;
                }
                System.out.println("... unexpected external invocation ...");
                System.out.println("arr --> " + Arrays.toString(arr));
                System.out.println("left = " + left);
                System.out.println("right = " + right);
                System.out.println("arr.length = " + arr.length);
                int[] arrMid = Arrays.copyOfRange(arr, left, right + 1);
                System.out.println("arrMid --> " + Arrays.toString(arrMid));
                mergeSort(arrMid);
                System.out.println("arrMid (sorted) --> " + Arrays.toString(arrMid));
                System.arraycopy(arrMid, 0, arr, left, arrMid.length);
                System.out.println("arr (MID-sorted) --> " + Arrays.toString(arr));
            }

            private void mergeSort(int[] arr) {
                if (arr == null || arr.length <= 1) {
                    return;
                }
                int half = arr.length / 2;
                int[] arrLeft = Arrays.copyOfRange(arr, 0, half);
                int[] arrRight = Arrays.copyOfRange(arr, half, arr.length);
                mergeSort(arrLeft);
                mergeSort(arrRight);
                int left = 0;
                int right = 0;
                int count = 0;
                while (left < arrLeft.length && right < arrRight.length) {
                    arr[count++] = arrLeft[left] < arrRight[right] ?
                        arrLeft[left++] : arrRight[right++];
                }
                while (left < arrLeft.length) {
                    arr[count++] = arrLeft[left++];
                }
                while (right < arrRight.length) {
                    arr[count++] = arrRight[right++];
                }
            }
        }
    }
}
