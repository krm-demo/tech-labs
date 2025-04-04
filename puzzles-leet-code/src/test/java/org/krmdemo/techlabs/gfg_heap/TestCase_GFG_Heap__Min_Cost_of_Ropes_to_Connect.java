package org.krmdemo.techlabs.gfg_heap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Heap__Min_Cost_of_Ropes_to_Connect}
 */
public class TestCase_GFG_Heap__Min_Cost_of_Ropes_to_Connect {

    GFG_Heap__Min_Cost_of_Ropes_to_Connect sln =
        GFG_Heap__Min_Cost_of_Ropes_to_Connect.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.minCost(new int[] { 4, 3, 2, 6 })).isEqualTo(29);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.minCost(new int[] { 4, 2, 7, 6, 9 })).isEqualTo(62);
    }
}
