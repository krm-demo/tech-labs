package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIndexOutOfBoundsException;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_004__Median_of_Two_Sorted_Arr}
 */
public class TestCase_004__Median_of_Two_Sorted_Arr {

    private static final int[] EMPTY_ARR = new int[]{};

    @EnumSource
    @ParameterizedTest
    void test_empty_one(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        assertThat(sln.findMedianSortedArrays(new int[] { 2, 3 }, null)).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 123 }, EMPTY_ARR)).isEqualTo(123);
        assertThat(sln.findMedianSortedArrays(EMPTY_ARR, new int[] { 1, 2, 3, 4 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(null, new int[] { 1, 2, 3 })).isEqualTo(2);
    }

    @EnumSource
    @ParameterizedTest
    void test_empty_two(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        assertThatIndexOutOfBoundsException().isThrownBy(() -> sln.findMedianSortedArrays(null, null));
        assertThatIndexOutOfBoundsException().isThrownBy(() -> sln.findMedianSortedArrays(EMPTY_ARR, null));
        assertThatIndexOutOfBoundsException().isThrownBy(() -> sln.findMedianSortedArrays(null, EMPTY_ARR));
        assertThatIndexOutOfBoundsException().isThrownBy(() -> sln.findMedianSortedArrays(EMPTY_ARR, EMPTY_ARR));
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        assertThat(sln.findMedianSortedArrays(new int[] { 2, 3 }, new int[] { 1 })).isEqualTo(2.0);
        assertThat(sln.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 })).isEqualTo(2.0);
        assertThat(sln.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3 })).isEqualTo(2.0);
        assertThat(sln.findMedianSortedArrays(new int[] { 3 }, new int[] { 1, 2 })).isEqualTo(2.0);
        assertThat(sln.findMedianSortedArrays(new int[] { 2 }, new int[] { 1, 3 })).isEqualTo(2.0);
        assertThat(sln.findMedianSortedArrays(new int[] { 1 }, new int[] { 2, 3 })).isEqualTo(2.0);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        assertThat(sln.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2, 4 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 1, 4 }, new int[] { 2, 3 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 2, 3 }, new int[] { 1, 4 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 2, 4 }, new int[] { 1, 3 })).isEqualTo(2.5);
        assertThat(sln.findMedianSortedArrays(new int[] { 3, 4 }, new int[] { 1, 2 })).isEqualTo(2.5);
    }

    @EnumSource
    @ParameterizedTest
    void test_even_01(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        int[] numsA = new int[] { 1, 5, 9, 10, 15, 20 };
        int[] numsB = new int[] { 2, 3, 8, 13 };
        assertThat(sln.findMedianSortedArrays(numsA, numsB)).isEqualTo(8.5);
    }

    @EnumSource
    @ParameterizedTest
    void test_odd_01(Problem_004__Median_of_Two_Sorted_Arr.Solution sln) {
        int[] numsA = new int[] { 1, 5, 9, 10, 15, 20 };
        int[] numsB = new int[] { 2, 3, 8, 13, 120 };
        assertThat(sln.findMedianSortedArrays(numsA, numsB)).isEqualTo(9.0);
    }
}
