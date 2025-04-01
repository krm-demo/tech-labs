package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.api.Test;
import org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Allocate_Min_Pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Stacks__Balance_Brackets}
 */
public class TestCase_FB_Prep_Stacks__Balance_Brackets {

    final FB_Prep_Stacks__Balance_Brackets sln =
        FB_Prep_Stacks__Balance_Brackets.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.isBalanced("{[()]}")).isTrue();
    }

    @Test
    void test_ex_02() {
        assertThat(sln.isBalanced("{}()")).isTrue();
    }

    @Test
    void test_ex_03() {
        assertThat(sln.isBalanced("{(})")).isFalse();
    }

    @Test
    void test_main_01() {
        assertThat(sln.isBalanced("{[(])}")).isFalse();
    }

    @Test
    void test_main_02() {
        assertThat(sln.isBalanced("{{[[(())]]}}")).isTrue();
    }
}
