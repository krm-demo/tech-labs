package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Greedy__Slow_Sum}
 */
public class TestCase_FB_Prep_Greedy__Slow_Sum {

    final FB_Prep_Greedy__Slow_Sum sln =
        FB_Prep_Greedy__Slow_Sum.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.getTotalTime(new int[] { 4, 2, 1, 3 })).isEqualTo(26);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.getTotalTime(new int[] { 2, 3, 9, 8, 4 })).isEqualTo(88);
    }
}
