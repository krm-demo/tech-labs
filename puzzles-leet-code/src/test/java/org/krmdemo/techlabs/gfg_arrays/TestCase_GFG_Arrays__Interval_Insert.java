package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Interval_Insert}
 */
public class TestCase_GFG_Arrays__Interval_Insert {

    GFG_Arrays__Interval_Insert sln =
        GFG_Arrays__Interval_Insert.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.insertInterval(new int[][] {
            {1,3}, {4,5}, {6,7}, {8,10}
        }, new int[] { 5, 6 })).containsExactly(new int[][] {
            {1,3}, {4,7}, {8,10}
        });
    }

    @Test
    void test_ex_02() {
        assertThat(sln.insertInterval(new int[][] {
            {1,2}, {3,5}, {6,7}, {8,10}, {12,16}
        }, new int[] { 4, 9 })).containsExactly(new int[][] {
            {1,2}, {3,10}, {12,16}
        });
    }
}
