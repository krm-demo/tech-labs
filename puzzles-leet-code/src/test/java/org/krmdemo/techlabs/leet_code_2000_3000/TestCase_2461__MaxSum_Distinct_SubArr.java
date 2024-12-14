package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2461__MaxSum_Distinct_SubArr}
 */
public class TestCase_2461__MaxSum_Distinct_SubArr {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_2461__MaxSum_Distinct_SubArr.Solution sln) {
        assertThat(sln.maximumSubarraySum(new int[] { 1,5,4,2,9,9,9 }, 3)).isEqualTo(15);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_2461__MaxSum_Distinct_SubArr.Solution sln) {
        assertThat(sln.maximumSubarraySum(new int[] { 4,4,4 }, 3)).isEqualTo(0);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_3_of_93(Problem_2461__MaxSum_Distinct_SubArr.Solution sln) {
        assertThat(sln.maximumSubarraySum(new int[] { 1,1,1,7,8,9 }, 3)).isEqualTo(24);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_89_of_93(Problem_2461__MaxSum_Distinct_SubArr.Solution sln) {
        assertThat(sln.maximumSubarraySum(new int[] { 3,5,3,4 }, 2)).isEqualTo(8);
    }

//    @Test // TODO: implement loading the parameters from JSON-resources
//    void test_tc_92_of_93() {
//        assertThat(sln.maximumSubarraySum(arr, 37)).isEqualTo(23_320);
//    }
}
