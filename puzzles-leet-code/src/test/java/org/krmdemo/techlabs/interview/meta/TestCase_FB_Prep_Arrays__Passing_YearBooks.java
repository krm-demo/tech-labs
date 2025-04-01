package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Arrays__Passing_YearBooks}
 */
public class TestCase_FB_Prep_Arrays__Passing_YearBooks {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(FB_Prep_Arrays__Passing_YearBooks.Solution sln) {
        assertThat(sln.findSignatureCounts(
            new int[] { 1, 2 }
        )).containsExactly(1, 1);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(FB_Prep_Arrays__Passing_YearBooks.Solution sln) {
        assertThat(sln.findSignatureCounts(
            new int[] { 2, 1 }
        )).containsExactly(2, 2);
    }
}
