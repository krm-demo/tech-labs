package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_323__Number_of_Connected_SubGraphs}
 */
public class TestCase_323__Number_of_Connected_SubGraphs {

    private final Problem_323__Number_of_Connected_SubGraphs sln =
        Problem_323__Number_of_Connected_SubGraphs.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}})).isEqualTo(2);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}})).isEqualTo(1);
    }
}
