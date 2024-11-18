package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_200__Number_of_Islands}
 */
public class TestCase_200__Number_of_Islands {

    Problem_200__Number_of_Islands sln = Problem_200__Number_of_Islands.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        char[][] grid = new char[][] {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        assertThat(sln.numIslands(grid)).isEqualTo(1);
    }

    @Test
    void test_ex_02() {
        char[][] grid = new char[][] {
            {'1','1','1','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        assertThat(sln.numIslands(grid)).isEqualTo(3);
    }
}
