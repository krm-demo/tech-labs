package org.krmdemo.techlabs.leet_code_1000_2000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_1143__Longest_Common_SubSeq}
 */
public class TestCase_1143__Longest_Common_SubSeq {

    Problem_1143__Longest_Common_SubSeq sln = Problem_1143__Longest_Common_SubSeq.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.longestCommonSubsequence("abcde", "ace")).isEqualTo(3);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.longestCommonSubsequence("abc", "abc")).isEqualTo(3);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.longestCommonSubsequence("def", "abc")).isEqualTo(0);
    }

    @Test
    void test_my_3_left() {
        assertThat(sln.longestCommonSubsequence("a1b2c3456", "123")).isEqualTo(3);
    }

    @Test
    void test_my_3_right() {
        assertThat(sln.longestCommonSubsequence("123", "a1b2c3456")).isEqualTo(3);
    }

    @Test
    void test_my_Sequence() {
        assertThat(sln.longestCommonSubsequence("longSequence", "longestCommonSubsequence")).isEqualTo(12);
        assertThat(sln.longestCommonSubsequence("longsequence", "longestCommonSubsequence")).isEqualTo(12);
        assertThat(sln.longestCommonSubsequence("longestone", "longestCommonSubsequence")).isEqualTo(10);
    }
}
