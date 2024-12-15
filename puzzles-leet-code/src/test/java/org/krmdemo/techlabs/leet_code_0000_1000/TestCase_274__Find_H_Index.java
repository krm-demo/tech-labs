package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_274__Find_H_Index}
 */
public class TestCase_274__Find_H_Index {

    final Problem_274__Find_H_Index sln = Problem_274__Find_H_Index.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.hIndex(new int[] { 3,0,6,1,5 })).isEqualTo(3);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.hIndex(new int[] { 1,3,1 })).isEqualTo(1);
    }

    @Test
    void test_GFG_ex_01() {
        assertThat(sln.hIndex(new int[] { 3,0,5,3,0 })).isEqualTo(3);
    }

    @Test
    void test_GFG_ex_02() {
        assertThat(sln.hIndex(new int[] { 5,1,2,4,1 })).isEqualTo(2);
    }

    @Test
    void test_GFG_ex_03() {
        assertThat(sln.hIndex(new int[] { 0,0 })).isEqualTo(0);
    }
}
