package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2222__Number_of_101_and_010}
 */
public class TestCase_2222__Number_of_101_and_010 {

    Problem_2222__Number_of_101_and_010 sln = Problem_2222__Number_of_101_and_010.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.numberOfWays("001101")).isEqualTo(6);
    }
}
