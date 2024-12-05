package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_300__Longest_Increasing_SubSeq}
 */
class TestCase_300__Longest_Increasing_SubSeq {

    final Problem_300__Longest_Increasing_SubSeq sln =
        Problem_300__Longest_Increasing_SubSeq.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.lengthOfLIS(new int[] { 10,9,2,5,3,7,101,18 })).isEqualTo(4);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.lengthOfLIS(new int[] { 0,1,  0, 3,2,3 })).isEqualTo(4);
        assertThat(sln.lengthOfLIS(new int[] { 0,1, -1, 3,2,3 })).isEqualTo(4);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.lengthOfLIS(new int[] { 7,7,7,7,7,7,7 })).isEqualTo(1);
    }
}
