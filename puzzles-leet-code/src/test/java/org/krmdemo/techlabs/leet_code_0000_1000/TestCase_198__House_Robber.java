package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_198__House_Robber}
 */
public class TestCase_198__House_Robber {

    final Problem_198__House_Robber sln = Problem_198__House_Robber.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.rob(new int[] { 1,2,3,1 })).isEqualTo(4);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.rob(new int[] { 2,7,9,3,1 })).isEqualTo(12);
    }

    @Test
    void test_II_ex_03() {
        assertThat(sln.rob(new int[] { 1,2,3 })).isEqualTo(4);  // <-- for "House Robber II" the answer is "3"
    }
}
