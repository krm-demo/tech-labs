package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_300__Longest_Increasing_SubSeq}
 */
public class TestCase_300__Longest_Increasing_SubSeq {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 10,9,2,5,3,7,101,18 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 0,1,  0, 3,2,3 })).isEqualTo(4);
        assertThat(sln.lengthOfLIS(new int[] { 0,1, -1, 3,2,3 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 7,7,7,7,7,7,7 })).isEqualTo(1);
    }

    /**
     * @see <a href="https://leetcode.com/discuss/general-discussion/1129459/patience-sorting-dp-optimisation">
     *     Patience Sorting ( DP Optimisation )
     * </a>
     */
    @EnumSource
    @ParameterizedTest
    void test_PatienceSortArticle(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 3,7,5,6,4,2,10,9,8 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_PatienceSortArticle_WithDuplicates(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 3,7,5,6,5,2,10,9,8 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_GFG_ex_01(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 5, 8, 3, 7, 9, 1 })).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_GFG_ex_02(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 })).isEqualTo(6);
    }

    @EnumSource
    @ParameterizedTest
    void test_GFG_ex_03(Problem_300__Longest_Increasing_SubSeq.Solution sln) {
        assertThat(sln.lengthOfLIS(new int[] { 3, 10, 2, 1, 20 })).isEqualTo(3);
    }
}
