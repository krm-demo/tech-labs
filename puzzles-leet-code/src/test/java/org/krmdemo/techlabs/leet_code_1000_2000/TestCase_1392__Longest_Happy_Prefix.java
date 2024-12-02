package org.krmdemo.techlabs.leet_code_1000_2000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_1392__Longest_Happy_Prefix}
 */
public class TestCase_1392__Longest_Happy_Prefix {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("level")).isEqualTo("l");
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("ababab")).isEqualTo("abab");
    }
}
