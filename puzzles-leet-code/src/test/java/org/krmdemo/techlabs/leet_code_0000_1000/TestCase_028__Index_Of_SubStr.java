package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.gfg_strings.GFG_Strings__Search_Pattern_KMP;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.ArrayUtils.zeroIntArr;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_028__Index_Of_SubStr}
 */
public class TestCase_028__Index_Of_SubStr {

    Problem_028__Index_Of_SubStr sln =
        Problem_028__Index_Of_SubStr.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.strStr("sadbutsad", "sad")).isEqualTo(0);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.strStr("leetcode", "leeto")).isEqualTo(-1);
    }

    @Test
    void test_editorial_GFG() {
        assertThat(sln.strStr("abababcababc", "ababc")).isEqualTo(2);
    }
}
