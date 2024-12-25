package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.leet_code_0000_1000.Problem_410__Split_Array_Largest_Sum;
import org.krmdemo.techlabs.utils.ArrayUtils;
import org.krmdemo.techlabs.utils.RandomHelper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Allocate_Min_Pages}
 */
public class TestCase_GFG_Arrays__Allocate_Min_Pages {

    final GFG_Arrays__Allocate_Min_Pages sln =
        GFG_Arrays__Allocate_Min_Pages.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.findPages(new int[] {12, 34, 67, 90}, 2)).isEqualTo(113);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.findPages(new int[] {15, 17, 20}, 5)).isEqualTo(-1);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.findPages(new int[] {22, 23, 67}, 1)).isEqualTo(112);
    }
}
