package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit-test for {@link BinarySearchUtils}
 */
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
        int[] cntLessArr_all = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countLess(arr, 0, arr.length, floorValue))
            .toArray();
        assertThat(cntLessArr_all).containsExactly(
            0, 0, 0,
            0, 1, 1, 2, 2, 2, 2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            3, 3, 3, 3, 3, 4, 4, 4, 4, 4,
            4, 5, 6, 7, 7, 7, 8, 8, 8, 8
        );
        int[] cntLessArr_mid = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countLess(arr, 2, arr.length - 3, floorValue))
            .toArray();
        assertThat(cntLessArr_mid).containsExactly(
            0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 2, 2, 2, 2, 2,
            2, 3, 3, 3, 3, 3, 3, 3, 3, 3
        );
    }

    @Test
    void testCountNotGreater() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountNotGreater: " + Arrays.toString(arr));
        for (int i = -2; i <= 40; i++) {
            int cntNotGreater = BinarySearchUtils.countNotGreater(arr, 0, arr.length, i);
            System.out.printf("ceilValue = %2d; cntNotGreater = %d; %s %n",
                i, cntNotGreater, dumpPrefixArr(arr, cntNotGreater));
        }
        int[] cntNotGreaterArr_all = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countNotGreater(arr, 0, arr.length, floorValue))
            .toArray();
        assertThat(cntNotGreaterArr_all).containsExactly(
            0, 0, 0,
            1, 1, 2, 2, 2, 2, 2, 2, 2, 3,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            3, 3, 3, 3, 4, 4, 4, 4, 4, 4,
            5, 6, 7, 7, 7, 8, 8, 8, 8, 8
        );
        int[] cntNotGreaterArr_mid = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countNotGreater(arr, 2, arr.length - 3, floorValue))
            .toArray();
        assertThat(cntNotGreaterArr_mid).containsExactly(
            0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 2, 2, 2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3
        );
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
        int[] cntGreaterArr_all = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countGreater(arr, 0, arr.length, floorValue))
            .toArray();
        assertThat(cntGreaterArr_all).containsExactly(
            8, 8, 8,
            7, 7, 6, 6, 6, 6, 6, 6, 6, 5,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 4, 4, 4, 4, 4, 4,
            3, 2, 1, 1, 1, 0, 0, 0, 0, 0
        );
        int[] cntGreaterArr_mid = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countGreater(arr, 2, arr.length - 3, floorValue))
            .toArray();
        assertThat(cntGreaterArr_mid).containsExactly(
            3, 3, 3,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        );
    }

    @Test
    void testCountNotLess() {
        int[] arr = new int[] { 1, 3, 10, 25, 31, 32, 33, 36 };
        System.out.println("testCountNotLess: " + Arrays.toString(arr));
        {
            int floorValue = 35;
            int cntNotLess = BinarySearchUtils.countNotLess(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntNotLess = %d; %s %n",
                floorValue, cntNotLess, dumpSuffixArr(arr, cntNotLess));
        } {
            int floorValue = 36;
            int cntNotLess = BinarySearchUtils.countNotLess(arr, 0, arr.length, floorValue);
            System.out.printf("*** floorValue = %2d; cntNotLess = %d; %s %n",
                floorValue, cntNotLess, dumpSuffixArr(arr, cntNotLess));
        }
        for (int i = -2; i <= 40; i++) {
            int cntNotLess = BinarySearchUtils.countNotLess(arr, 0, arr.length, i);
            System.out.printf("floorValue = %2d; cntNotLess = %d; %s %n",
                i, cntNotLess, dumpSuffixArr(arr, cntNotLess));
        }
        int[] cntNotLessArr_all = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countNotLess(arr, 0, arr.length, floorValue))
            .toArray();
        assertThat(cntNotLessArr_all).containsExactly(
            8, 8, 8,
            8, 7, 7, 6, 6, 6, 6, 6, 6, 6,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 5, 4, 4, 4, 4, 4,
            4, 3, 2, 1, 1, 1, 0, 0, 0, 0
        );
        int[] cntNotLessArr_mid = IntStream.rangeClosed(-2, 40)
            .map(floorValue -> BinarySearchUtils.countNotLess(arr, 2, arr.length - 3, floorValue))
            .toArray();
        assertThat(cntNotLessArr_mid).containsExactly(
            3, 3, 3,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 0, 0
        );
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
