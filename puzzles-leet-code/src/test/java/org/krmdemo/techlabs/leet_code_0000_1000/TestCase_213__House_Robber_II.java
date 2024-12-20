package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_213__House_Robber_II}
 */
public class TestCase_213__House_Robber_II {

    final Problem_213__House_Robber_II sln = Problem_213__House_Robber_II.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.rob(new int[] { 2,3,2 })).isEqualTo(3);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.rob(new int[] { 1,2,3,1 })).isEqualTo(4);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.rob(new int[] { 1,2,3 })).isEqualTo(3);  // <-- for "House Robber II" the answer is "4"
    }
}
