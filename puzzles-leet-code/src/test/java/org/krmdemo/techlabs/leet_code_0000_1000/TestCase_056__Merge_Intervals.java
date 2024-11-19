package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_056__Merge_Intervals}
 */
public class TestCase_056__Merge_Intervals {

    Problem_056__Merge_Intervals sln = Problem_056__Merge_Intervals.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        int[][] intervals = new int[][] {
            {1,3}, {2,6}, {8,10}, {15,18}
        };
        assertThat(sln.merge(intervals)).isEqualTo(new int[][] {
            {1,6}, {8,10}, {15,18}
        });
    }

    @Test
    void test_ex_02() {
        int[][] intervals = new int[][] {
            {1,4}, {4,5}
        };
        assertThat(sln.merge(intervals)).isEqualTo(new int[][] {
            {1,5}
        });
    }
}
