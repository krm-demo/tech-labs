package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1">
 *     Count Inversions
 * </a></h3>
 * Given an array of integers <b><code>arr[]</code></b>. Find the <b>Inversion Count</b> in the array.
 * Two elements <code>arr[<b>i</b>]</code> and <code>arr[<b>j</b>]</code>
 * form an inversion if <code>arr[<b>i</b>] > arr[<b>j</b>]</code> and <b><code>i < j</code></b>.
 * <hr/>
 * For an array, <b>Inversion Count</b> indicates how far (or close) the array is from being sorted.
 * If the array is already sorted then the inversion count is zero.
 * If an array is sorted in the reverse order then the inversion count is the maximum.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^5
 * 0 ≤ arr[i] ≤ 10^4
 * </pre>
 *
 * @see GFG_Arrays__Count_Min_Swaps_To_Sort
 */
public interface GFG_Arrays__Count_Inversions {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array containing positive integers
     * @return total number of <b>Inversion Count</b>
     */
    int inversionCount(int[] arr);

    enum Solution implements GFG_Arrays__Count_Inversions {
        MERGE_SORT__SHIFT {
            @Override
            public int inversionCount(int[] arr) {
                System.out.println("inversionCount: arr --> " + Arrays.toString(arr));
                int swapCount = mergeSort(arr, 0, arr.length - 1);
                System.out.printf("swapCount = %d; arr --> %s;%n", swapCount, Arrays.toString(arr));
                return swapCount;
            }

            private void shiftRight(int[] arr, int start, int count) {
                for (int i = start + count; i > start; i--) {
                    arr[i] = arr[i - 1];
                }
            }

            private int mergeSort(int[] arr, int left, int right) {
//                StringBuilder sb = new StringBuilder(String.format(
//                    "mergeSort: %s, left = %d, right = %d; --> ",
//                    Arrays.stream(arr, left, right + 1).boxed().toList(), left, right));
                if (left == right) {
//                    System.out.println(sb + "- single value");
                    return 0;
                }
                int mid = (left + right) / 2;
                int swapCount = 0;
                swapCount += mergeSort(arr, left, mid);
//                sb.insert(0, String.format("- leftSort:  %s swapCount = %d;%n",
//                    Arrays.stream(arr, left, mid + 1).boxed().toList(), swapCount));
                swapCount += mergeSort(arr, mid + 1, right);
//                sb.insert(0, String.format("- rightSort: %s swapCount = %d;%n",
//                    Arrays.stream(arr, mid + 1, right + 1).boxed().toList(), swapCount));
                int i = left;
                int j = mid + 1;
                while (i <= mid && j <= right) {
//                    System.out.printf("arr[%d] = %d, arr[%d] = %d :: ", i, arr[i], j, arr[j]);
                    if (arr[i] > arr[j]) {
                        int value = arr[j];
                        shiftRight(arr, i, j - i);
                        arr[i] = value;
//                        System.out.printf("%d + (%d - %d) = %d --> %s;%n",
//                            swapCount, j, i, swapCount + (j - i),
//                            Arrays.stream(arr, left, right + 1).boxed().toList());
                        swapCount += j - i;
                        j++;
                        mid++;
//                    } else {
//                        System.out.println("no shift right");
                    }
                    i++;
                }
//                System.out.printf("%s %s, swapCount = %d;%n", sb,
//                    Arrays.stream(arr, left, right + 1).boxed().toList(), swapCount);
                return swapCount;
            }
        },
        MERGE_SORT__COPY {
            @Override
            public int inversionCount(int[] arr) {
                System.out.println("inversionCount: arr --> " + Arrays.toString(arr));
                int swapCount = mergeSort(arr);
                System.out.printf("swapCount = %d; arr --> %s;%n", swapCount, Arrays.toString(arr));
                return swapCount;
            }

            private int mergeSort(int[] arr) {
                int swapCount = 0;
                int half = arr.length / 2;
//                System.out.printf("mergeSortCopy ( length = %d, half = %d ) --> %s %n",
//                    arr.length, half, Arrays.toString(arr));
                int[] arrLeft = Arrays.stream(arr, 0, half).toArray();
//                System.out.println("- arrLeft --> " + Arrays.toString(arrLeft));
                if (arrLeft.length > 1) {
                    swapCount += mergeSort(arrLeft);
                }
                int[] arrRight = Arrays.stream(arr, half, arr.length).toArray();
//                System.out.println("- arrRight --> " + Arrays.toString(arrRight));
                if (arrRight.length > 1) {
                    swapCount += mergeSort(arrRight);
                }
//                System.out.printf("merging arrLeft(%d)%s and arrRight(%d)%s %n",
//                    arrLeft.length, Arrays.toString(arrLeft),
//                    arrRight.length, Arrays.toString(arrRight));
                int left = 0;
                int right = 0;
                int count = 0;
                while (left < arrLeft.length && right < arrRight.length) {
                    if (arrLeft[left] > arrRight[right]) {
                        arr[count++] = arrRight[right++];
                        swapCount += arrLeft.length - left;
                    } else {
                        arr[count++] = arrLeft[left++];
                    }
                }
                while (left < arrLeft.length) {
                    arr[count++] = arrLeft[left++];
                }
                while (right < arrRight.length) {
                    arr[count++] = arrRight[right++];
                }
//                System.out.println("swapCount = " + swapCount + "; --> " + Arrays.toString(arr));
                return swapCount;
            }
        },
        BRUTE_FORCE {
            @Override
            public int inversionCount(int[] arr) {
                int invCount = 0;
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[i] > arr[j]) {
                            invCount++;
                        }
                    }
                }
                return invCount;
            }
        }
    }
}
