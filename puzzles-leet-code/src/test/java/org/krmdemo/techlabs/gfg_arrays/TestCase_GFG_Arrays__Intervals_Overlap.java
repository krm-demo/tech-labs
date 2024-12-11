package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Intervals_Overlap}
 */
public class TestCase_GFG_Arrays__Intervals_Overlap {

    GFG_Arrays__Intervals_Overlap sln =
        GFG_Arrays__Intervals_Overlap.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.mergeOverlap(new int[][] {
            {1,3}, {2,4}, {6,8}, {9,10}
        })).containsExactly(new int[][] {
            {1,4}, {6,8}, {9,10}
        });
    }

    @Test
    void test_ex_02() {
        assertThat(sln.mergeOverlap(new int[][] {
            {6,8}, {1,9}, {2,4}, {4,7}
        })).containsExactly(new int[][] {
            {1,9}
        });
    }
}
