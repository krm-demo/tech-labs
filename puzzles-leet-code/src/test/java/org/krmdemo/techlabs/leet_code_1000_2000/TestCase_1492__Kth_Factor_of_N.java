package org.krmdemo.techlabs.leet_code_1000_2000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_1492__Kth_Factor_of_N}
 */
public class TestCase_1492__Kth_Factor_of_N {

    Problem_1492__Kth_Factor_of_N sln = Problem_1492__Kth_Factor_of_N.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.kthFactor(12, 1)).isEqualTo(1);
        assertThat(sln.kthFactor(12, 2)).isEqualTo(2);
        assertThat(sln.kthFactor(12, 3)).isEqualTo(3);
        assertThat(sln.kthFactor(12, 4)).isEqualTo(4);
        assertThat(sln.kthFactor(12, 5)).isEqualTo(6);
        assertThat(sln.kthFactor(12, 6)).isEqualTo(12);
        assertThat(sln.kthFactor(12, 7)).isEqualTo(-1);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.kthFactor(7, 2)).isEqualTo(7);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.kthFactor(4, 4)).isEqualTo(-1);
    }
}
