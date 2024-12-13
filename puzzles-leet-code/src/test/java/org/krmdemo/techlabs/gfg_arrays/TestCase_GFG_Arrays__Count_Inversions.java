package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.ArrayUtils;
import org.krmdemo.techlabs.utils.RandomHelper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Count_Inversions}
 */
public class TestCase_GFG_Arrays__Count_Inversions {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(GFG_Arrays__Count_Inversions.Solution sln) {
        assertThat(sln.inversionCount(new int[] { 2, 4, 1, 3, 5 })).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(GFG_Arrays__Count_Inversions.Solution sln) {
        assertThat(sln.inversionCount(new int[] { 2, 3, 4, 5, 6 })).isEqualTo(0);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(GFG_Arrays__Count_Inversions.Solution sln) {
        assertThat(sln.inversionCount(new int[] { 10, 10, 10 })).isEqualTo(0);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_3_of_1115(GFG_Arrays__Count_Inversions.Solution sln) {
        // 24 18 38 43 14 40 1 54
        assertThat(sln.inversionCount(new int[] { 24, 18, 38, 43, 14, 40, 1, 54 })).isEqualTo(12);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_3_of_1115_last(GFG_Arrays__Count_Inversions.Solution sln) {
        // 24 18 38 43 14 40 1 54
        assertThat(sln.inversionCount(new int[] { 18, 24, 38, 43, 1, 14, 40, 54 })).isEqualTo(9);
    }

    RandomHelper rnd = new RandomHelper(0);

    @EnumSource
    @ParameterizedTest
    void test_ex_sorted_with_dups(GFG_Arrays__Count_Inversions.Solution sln) {
        int[] arr = rnd.randomSortedIntArr(20, 1, 100);
        assertThat(sln.inversionCount(arr)).isEqualTo(0);
        assertThat(sln.inversionCount(ArrayUtils.reversed(arr))).isEqualTo(186);  // <-- (190 - 5 + 1)
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_sorted_no_dups(GFG_Arrays__Count_Inversions.Solution sln) {
        int[] arr = rnd.randomIncreasingIntArr(20, 1, 100);
        assertThat(sln.inversionCount(arr)).isEqualTo(0);
        assertThat(sln.inversionCount(ArrayUtils.reversed(arr)))
            .isEqualTo(arr.length * (arr.length - 1) / 2);  // <-- 190
    }
}
