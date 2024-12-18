package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_315__Count_Smaller_Numbers}
 */
public class TestCase_315__Count_Smaller_Numbers {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { 5,2,6,1 })).isEqualTo(List.of( 2, 1, 1, 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1 })).isEqualTo(List.of( 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1, -1 })).isEqualTo(List.of( 0, 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_dups_middle(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1, -2, -3, 0, 0, 0, 1, 2, 3 }))
            .isEqualTo(List.of( 2, 1, 0, 0, 0, 0, 0, 0, 0 ));
        assertThat(sln.countSmaller(new int[] { 1, 2, 3, 0, 0, 0, -1, -2, -3 }))
            .isEqualTo(List.of( 6, 6, 6, 3, 3, 3, 2, 1, 0 ));
        assertThat(sln.countSmaller(new int[] { 1, 2, 3, 0, 0, 0, -1, -2, -3, 4, 5 }))
            .isEqualTo(List.of( 6, 6, 6, 3, 3, 3, 2, 1, 0, 0, 0 ));
    }
}
