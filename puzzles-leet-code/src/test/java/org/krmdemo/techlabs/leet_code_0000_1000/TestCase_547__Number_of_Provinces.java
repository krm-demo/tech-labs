package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_547__Number_of_Provinces}
 */
public class TestCase_547__Number_of_Provinces {

    Problem_547__Number_of_Provinces sln = Problem_547__Number_of_Provinces.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        int[][] isConnected = new int[][] {
            { 1,1,0 },
            { 1,1,0 },
            { 0,0,1 }
        };
        assertThat(sln.findCircleNum(isConnected)).isEqualTo(2);
    }

    @Test
    void test_ex_02() {
        int[][] isConnected = new int[][] {
            { 1,0,0 },
            { 0,1,0 },
            { 0,0,1 }
        };
        assertThat(sln.findCircleNum(isConnected)).isEqualTo(3);
    }
}
