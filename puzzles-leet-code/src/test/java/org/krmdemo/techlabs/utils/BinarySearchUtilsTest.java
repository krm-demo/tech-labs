package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BinarySearchUtilsTest {

    @Test
    void testCountLess() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountLess: " + Arrays.toString(arr));
        for (int i = -2; i <= 40; i++) {
            int cntLess = BinarySearchUtils.countLess(arr, 0, arr.length, i);
            System.out.printf("ceilValue = %2d; cntLess = %d; %s %n",
                i, cntLess, dumpPrefixArr(arr, cntLess));
        }
    }

    @Test
    void testCountNotGreater() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountNotGreater: " + Arrays.toString(arr));
        for (int i = -2; i <= 40; i++) {
            int cntNG = BinarySearchUtils.countNotGreater(arr, 0, arr.length, i);
            System.out.printf("ceilValue = %2d; cntNG = %d; %s %n",
                i, cntNG, dumpPrefixArr(arr, cntNG));
        }
    }

    @Test
    void testCountGreater() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountGreater: " + Arrays.toString(arr));
        {
            int floorValue = 35;
            int cntGreater = BinarySearchUtils.countGreater(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntGreater = %d; %s %n",
                floorValue, cntGreater, dumpSuffixArr(arr, cntGreater));
        } {
            int floorValue = 36;
            int cntGreater = BinarySearchUtils.countGreater(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntGreater = %d; %s %n",
                floorValue, cntGreater, dumpSuffixArr(arr, cntGreater));
        }
        for (int i = -2; i <= 40; i++) {
            int cntGreater = BinarySearchUtils.countGreater(arr, 0, arr.length, i);
            System.out.printf("floorValue = %2d; cntGreater = %d; %s %n",
                i, cntGreater, dumpSuffixArr(arr, cntGreater));
        }
    }

    @Test
    void testCountNotLess() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountNotLess: " + Arrays.toString(arr));
        {
            int floorValue = 35;
            int cntNL = BinarySearchUtils.countNotLess(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntNL = %d; %s %n",
                floorValue, cntNL, dumpSuffixArr(arr, cntNL));
        } {
            int floorValue = 36;
            int cntNL = BinarySearchUtils.countNotLess(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntNL = %d; %s %n",
                floorValue, cntNL, dumpSuffixArr(arr, cntNL));
        }
//        for (int i = -2; i <= 40; i++) {
//            int cntNL = BinarySearchUtils.countNotLess(arr, 0, arr.length, i);
//            System.out.printf("floorValue = %2d; cntNL = %d; %s %n",
//                i, cntNL, dumpSuffixArr(arr, cntNL));
//        }
    }

    private String dumpPrefixArr(int[] arr, int prefixLength) {
        prefixLength = Math.max(0, prefixLength);
        return Arrays.stream(arr)
            .limit(prefixLength)
            .boxed().toList().toString();
    }

    private String dumpSuffixArr(int[] arr, int suffixLength) {
        int prefixLength = Math.max(0, arr.length - suffixLength);
        return Arrays.stream(arr)
            .skip(prefixLength)
            .limit(suffixLength)
            .boxed().toList().toString();
    }
}
