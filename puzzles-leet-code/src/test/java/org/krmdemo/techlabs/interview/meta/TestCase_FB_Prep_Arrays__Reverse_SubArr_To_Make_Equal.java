package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal}
 */
public class TestCase_FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 1, 2, 3, 4 },
            new int[] { 1, 4, 3, 2 }
        )).isTrue();
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 1, 2, 3, 4 },
            new int[] { 1, 4, 3, 3 }
        )).isFalse();
    }

    @EnumSource
    @ParameterizedTest
    void test_LeetCode_ex_01(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 1, 2, 3, 4 },
            new int[] { 2, 4, 1, 3 }
        )).isTrue();
    }

    @EnumSource
    @ParameterizedTest
    void test_LeetCode_ex_02(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 7 },
            new int[] { 7 }
        )).isTrue();
    }

    @EnumSource
    @ParameterizedTest
    void test_LeetCode_ex_03(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 3, 7, 9 },
            new int[] { 3, 7, 11 }
        )).isFalse();
    }

    @EnumSource
    @ParameterizedTest
    void test_DifferentSize(FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal.Solution sln) {
        assertThat(sln.areTheyEqual(
            new int[] { 1, 2, 3, 4, 5 },
            new int[] { 1, 2, 3, 5 }
        )).isFalse();
    }
}
