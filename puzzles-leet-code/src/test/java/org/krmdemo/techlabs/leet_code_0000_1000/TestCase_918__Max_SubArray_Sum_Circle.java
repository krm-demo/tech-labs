package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.RandomHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_918__Max_SubArray_Sum_Circle}
 */
public class TestCase_918__Max_SubArray_Sum_Circle {

    final RandomHelper rnd = new RandomHelper(234);

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThat(sln.maxSubarraySumCircular(new int[] { 1, -2, 3, -2 })).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThat(sln.maxSubarraySumCircular(new int[] { 5, -3, 5 })).isEqualTo(10);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThat(sln.maxSubarraySumCircular(new int[] { -3, -2, -3 })).isEqualTo(-2);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_105_of_112(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThat(sln.maxSubarraySumCircular(new int[] { -3, -2, -1 })).isEqualTo(-1);
    }

    @EnumSource
    @ParameterizedTest
    void test_empty(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> sln.maxSubarraySumCircular(null)
        );
        assertThatIllegalArgumentException().isThrownBy(
            () -> sln.maxSubarraySumCircular(new int[] {})
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_single(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        assertThat(sln.maxSubarraySumCircular(new int[] { 123 })).isEqualTo(123);
    }

    @EnumSource
    @ParameterizedTest
    void test_random(Problem_918__Max_SubArray_Sum_Circle.Solution sln) {
        // -5, 4, 0, 5, 2, 1, 3, -3, -2, -4, -1
        int[] numsOne = rnd.randomRangeClosedArr(-5, 5);
        assertThat(sln.maxSubarraySumCircular(numsOne)).isEqualTo(15);
        // -4, 4, 5, 3, 2, 1, 0, -1, 6, -3, -2, 7
        int[] numsTwo = rnd.randomRangeClosedArr(-4, 7);
        assertThat(sln.maxSubarraySumCircular(numsTwo)).isEqualTo(23);
    }
}
