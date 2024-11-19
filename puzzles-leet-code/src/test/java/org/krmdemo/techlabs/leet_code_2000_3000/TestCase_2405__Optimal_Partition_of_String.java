package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2405__Optimal_Partition_of_String}
 */
public class TestCase_2405__Optimal_Partition_of_String {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_2405__Optimal_Partition_of_String.Solution sln) {
        assertThat(sln.partitionString("abacaba")).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_2405__Optimal_Partition_of_String.Solution sln) {
        assertThat(sln.partitionString("ssssss")).isEqualTo(6);
    }

    @EnumSource
    @ParameterizedTest
    void test_three_seq(Problem_2405__Optimal_Partition_of_String.Solution sln) {
        assertThat(sln.partitionString("abcdabcabcd")).isEqualTo(3);
    }
}
