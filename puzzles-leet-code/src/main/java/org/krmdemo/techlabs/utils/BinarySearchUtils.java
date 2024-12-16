package org.krmdemo.techlabs.utils;

/**
 * Utility-Class to perform binary-search not to find the given value,
 * but count the elements that are greater or less than given value
 */
public class BinarySearchUtils {

    /**
     * Count the number of elements in the sub-array <code>[left,right)</code>
     * of the passed sorted array <code>arr</code>
     * that are <b>strictly less</b> than the passed <code>ceilValue</code>
     *
     * @param arr an array of integers that is sorted in ascending (natural) order
     * @param left the left boundary of sub-array (inclusive)
     * @param right the right boundary of sub-array (exclusive)
     * @param ceilValue the upper-value (<b>exclusive</b>)
     * @return the number of elements that are <b>strictly less</b> than <code>ceilValue</code>
     */
    public static int countLess(int[] arr, int left, int right, int ceilValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[left] >= ceilValue) {
            return 0;
        }
        if (arr[right - 1] < ceilValue) {
            return right - left;
        }
        int initialLeft = left;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue >= ceilValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left + 1 - initialLeft;
    }

    /**
     * Count the number of elements in the sub-array <code>[left,right)</code>
     * of the passed sorted array <code>arr</code>
     * that are equal or less (<b>not greater</b>) than the passed <code>ceilValue</code>
     *
     * @param arr an array of integers that is sorted in ascending (natural) order
     * @param left the left boundary of sub-array (inclusive)
     * @param right the right boundary of sub-array (exclusive)
     * @param ceilValue the upper-value (<b>inclusive</b>)
     * @return the number of elements that are <b>not greater</b> than <code>ceilValue</code>
     */
    public static int countNotGreater(int[] arr, int left, int right, int ceilValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[left] > ceilValue) {
            return 0;
        }
        if (arr[right - 1] <= ceilValue) {
            return right - left;
        }
        int initialLeft = left;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue > ceilValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left + 1 - initialLeft;
    }

    /**
     * Count the number of elements in the sub-array <code>[left,right)</code>
     * of the passed sorted array <code>arr</code>
     * that are <b>strictly greater</b> than the passed <code>floorValue</code>
     *
     * @param arr an array of integers that is sorted in ascending (natural) order
     * @param left the left boundary of sub-array (inclusive)
     * @param right the right boundary of sub-array (exclusive)
     * @param floorValue the lower-value (<b>exclusive</b>)
     * @return the number of elements that are <b>strictly greater</b> than <code>floorValue</code>
     */
    public static int countGreater(int[] arr, int left, int right, int floorValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[right - 1] < floorValue) {
            return 0;
        }
        if (arr[left] > floorValue) {
            return right - left;
        }
        int initialRight = right;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue > floorValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return initialRight - right;
    }

    /**
     * Count the number of elements in the sub-array <code>[left,right)</code>
     * of the passed sorted array <code>arr</code>
     * that are equal or greater (<b>not less</b>) than the passed <code>floorValue</code>
     *
     * @param arr an array of integers that is sorted in ascending (natural) order
     * @param left the left boundary of sub-array (inclusive)
     * @param right the right boundary of sub-array (exclusive)
     * @param floorValue the lower-value (<b>inclusive</b>)
     * @return the number of elements that are not less than <code>floorValue</code>
     */
    public static int countNotLess(int[] arr, int left, int right, int floorValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[right - 1] < floorValue) {
            return 0;
        }
        if (arr[left] >= floorValue) {
            return right - left;
        }
        int initialRight = right;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue >= floorValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return initialRight - right;
    }
}
