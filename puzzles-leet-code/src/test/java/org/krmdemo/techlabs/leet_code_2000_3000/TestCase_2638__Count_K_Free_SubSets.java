package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2638__Count_K_Free_SubSets}
 */
public class TestCase_2638__Count_K_Free_SubSets {

    final Problem_2638__Count_K_Free_SubSets sln = Problem_2638__Count_K_Free_SubSets.Solution.BRUTE_FORCE;

    @Test
    void test_ex_01() {
        assertThat(sln.countTheNumOfKFreeSubsets(new int[] { 5,4,6 }, 1)).isEqualTo(5);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.countTheNumOfKFreeSubsets(new int[] { 2,3,5,8 }, 5)).isEqualTo(12);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.countTheNumOfKFreeSubsets(new int[] { 10,5,9,11 }, 20)).isEqualTo(16);
    }
}
