package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Greedy__Seating_Arrangements}
 */
public class TestCase_FB_Prep_Greedy__Seating_Arrangements {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(FB_Prep_Greedy__Seating_Arrangements.Solution sln) {
        assertThat(sln.minOverallAwkwardness(new int[]{ 5, 10, 6, 8 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(FB_Prep_Greedy__Seating_Arrangements.Solution sln) {
        assertThat(sln.minOverallAwkwardness(new int[]{ 1, 2, 5, 3, 7 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ChatGPT(FB_Prep_Greedy__Seating_Arrangements.Solution sln) {
        assertThat(sln.minOverallAwkwardness(new int[]{ 10, 1, 5, 8, 12, 6 })).isEqualTo(5);
    }

    @EnumSource
    @ParameterizedTest
    void test_DeepSeek(FB_Prep_Greedy__Seating_Arrangements.Solution sln) {
        assertThat(sln.minOverallAwkwardness(new int[]{ 4, 2, 1, 5 })).isEqualTo(3);
    }
}
