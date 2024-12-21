package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_276__Paint_Fence}
 */
public class TestCase_276__Paint_Fence {

    final Problem_276__Paint_Fence sln = Problem_276__Paint_Fence.Solution.DP_BOTTOM_UP;

    @Test
    void test_ex_01() {
        assertThat(sln.numWays(3, 2)).isEqualTo(6);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.numWays(1, 1)).isEqualTo(1);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.numWays(7, 2)).isEqualTo(42);
    }
}
