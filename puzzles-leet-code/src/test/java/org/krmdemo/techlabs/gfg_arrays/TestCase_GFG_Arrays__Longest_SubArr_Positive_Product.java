package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Longest_SubArr_Positive_Product}
 */
public class TestCase_GFG_Arrays__Longest_SubArr_Positive_Product {

    GFG_Arrays__Longest_SubArr_Positive_Product sln =
        GFG_Arrays__Longest_SubArr_Positive_Product.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.maxLength(new int[] { 0, 1, -2, -3, -4 })).isEqualTo(3);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.maxLength(new int[] { -1, -2, 0, 1, 2 })).isEqualTo(2);
    }

    @Test
    void test_zeroOrNegative_01() {
        assertThat(sln.maxLength(new int[] { -2, 0, -1  })).isEqualTo(0);
        assertThat(sln.maxLength(new int[] { -2, 0, -1  }, 2)).isEqualTo(0);
        assertThat(sln.maxLength(new int[] { -2, 0, -1  }, 1)).isEqualTo(0);
    }

    @Test
    void test_empty() {
        assertThat(sln.maxLength(null)).isEqualTo(0);
        assertThat(sln.maxLength(new int[]{})).isEqualTo(0);
        assertThat(sln.maxLength(null, 123)).isEqualTo(0);
    }
}
