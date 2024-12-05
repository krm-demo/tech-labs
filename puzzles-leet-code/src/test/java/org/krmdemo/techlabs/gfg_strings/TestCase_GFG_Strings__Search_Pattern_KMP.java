package org.krmdemo.techlabs.gfg_strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Strings__Search_Pattern_KMP}
 */
class TestCase_GFG_Strings__Search_Pattern_KMP {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(GFG_Strings__Search_Pattern_KMP.Solution sln) {
        assertThat(sln.search("ab", "abcab")).containsExactly(0, 3);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(GFG_Strings__Search_Pattern_KMP.Solution sln) {
        assertThat(sln.search("edu", "abesdu")).isEmpty();
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(GFG_Strings__Search_Pattern_KMP.Solution sln) {
        assertThat(sln.search("aaba", "aabaacaadaabaaba")).containsExactly(0, 9, 12);
    }

    @EnumSource
    @ParameterizedTest
    void test_editorial(GFG_Strings__Search_Pattern_KMP.Solution sln) {
        assertThat(sln.search("ababc", "abababcababc")).containsExactly(2, 7);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_default(GFG_Strings__Search_Pattern_KMP.Solution sln) {
        assertThat(sln.search("geek", "geeksforgeeks")).containsExactly(0, 8);
    }
}
