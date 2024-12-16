package org.krmdemo.techlabs.utils;

import java.util.*;

public class BinarySearchUtils {

    public static int countLess(int[] arr, int left, int right, int ceilValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[left] >= ceilValue) {
            return 0;
        }
        if (arr[right - 1] < ceilValue) {
            return right - left;
        }
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue >= ceilValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left + 1;
    }

    public static int countNotGreater(int[] arr, int left, int right, int ceilValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[left] > ceilValue) {
            return 0;
        }
        if (arr[right - 1] <= ceilValue) {
            return right - left;
        }
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (midValue > ceilValue) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left + 1;
    }

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

    public static int countNotLess(int[] arr, int left, int right, int floorValue) {
        left = Math.max(0, left);
        right = Math.min(right, arr.length);
        if (left >= right || arr[right - 1] <= floorValue) {
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
