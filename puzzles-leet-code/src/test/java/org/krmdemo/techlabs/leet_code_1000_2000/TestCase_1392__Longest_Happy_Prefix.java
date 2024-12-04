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

    @EnumSource
    @ParameterizedTest
    void test_28_ex_01(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("sadbutsad")).isEqualTo("sad");
    }

    @EnumSource
    @ParameterizedTest
    void test_28_ex_02(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("leetcode")).isEmpty();
    }

    @EnumSource
    @ParameterizedTest
    void test_repeated_char(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("aaaaaaaaaa")).hasSize(9).isEqualTo("aaaaaaaaa");
        assertThat(sln.longestPrefix("aaaaabaaaaa")).hasSize(5).isEqualTo("aaaaa");
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_80_of_83(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix(
            "vwantmbocxcwrqtvgzuvgrmdltfiglltaxkjfajxthcppcatddcunpkqsgpnjjgqanrwabgrtwuqbrfl"
        )).isEmpty();
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_82_of_83(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix(
            "aabcccabcbbbccabaabcbcacaccabaabaabccbaabbcbccaabbaaacaaccbaabacbbaabbbcaabbcaacacbccabaaacabcaababbabaaaa"
        )).isEqualTo("aa");
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_71_of_83(Problem_1392__Longest_Happy_Prefix.Solution sln) {
        assertThat(sln.longestPrefix("aacaacaaca")).hasSize(7).isEqualTo("aacaaca");
    }
}
