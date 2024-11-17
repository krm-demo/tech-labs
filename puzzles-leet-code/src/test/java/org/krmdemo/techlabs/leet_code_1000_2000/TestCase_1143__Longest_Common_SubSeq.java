package org.krmdemo.techlabs.leet_code_1000_2000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_1143__Longest_Common_SubSeq}
 */
public class TestCase_1143__Longest_Common_SubSeq {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("abcde", "ace")).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("abc", "abc")).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("def", "abc")).isEqualTo(0);
    }

    @EnumSource
    @ParameterizedTest
    void test_my_3_left(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("a1b2c3456", "123")).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_my_3_right(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("123", "a1b2c3456")).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_my_Sequence(Problem_1143__Longest_Common_SubSeq.Solution sln) {
        assertThat(sln.longestCommonSubsequence("longSequence", "longestCommonSubsequence")).isEqualTo(12);
        assertThat(sln.longestCommonSubsequence("longsequence", "longestCommonSubsequence")).isEqualTo(12);
        assertThat(sln.longestCommonSubsequence("longestone", "longestCommonSubsequence")).isEqualTo(10);
    }
}
