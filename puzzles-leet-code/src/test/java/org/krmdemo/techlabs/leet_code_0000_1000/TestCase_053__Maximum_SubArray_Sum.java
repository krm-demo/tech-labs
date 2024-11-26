package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_053__Max_SubArray_Sum}
 */
public class TestCase_053__Maximum_SubArray_Sum {

    @EnumSource
    @ParameterizedTest
    public void test_ex_01(Problem_053__Max_SubArray_Sum.Solution sln) {
        assertThat(sln.maxSubArray(new int[] { -2,1,-3,4,-1,2,1,-5,4 })).isEqualTo(6);
    }

    @EnumSource
    @ParameterizedTest
    public void test_ex_02(Problem_053__Max_SubArray_Sum.Solution sln) {
        assertThat(sln.maxSubArray(new int[] { 1 })).isEqualTo(1);
    }

    @EnumSource
    @ParameterizedTest
    public void test_ex_03(Problem_053__Max_SubArray_Sum.Solution sln) {
        assertThat(sln.maxSubArray(new int[] { 5,4,-1,7,8 })).isEqualTo(23);
    }

    @EnumSource
    @ParameterizedTest
    public void test_tc_195_of_210(Problem_053__Max_SubArray_Sum.Solution sln) {
        assertThat(sln.maxSubArray(new int[] { -1, -2 })).isEqualTo(-1);
    }

    @EnumSource
    @ParameterizedTest
    public void test_tc_105_of_112(Problem_053__Max_SubArray_Sum.Solution sln) {
        assertThat(sln.maxSubArray(new int[] { -3, -2, -1 })).isEqualTo(-1);
    }
}
