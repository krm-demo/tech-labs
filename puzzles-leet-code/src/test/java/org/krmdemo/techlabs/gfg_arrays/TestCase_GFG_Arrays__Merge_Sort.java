package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Merge_Sort}
 */
public class TestCase_GFG_Arrays__Merge_Sort {

    GFG_Arrays__Merge_Sort sln =
        GFG_Arrays__Merge_Sort.Solution.MERGE_HALF_COPY;

    @Test
    void test_ex_01() {
        int[] arr = new int[] { 4, 1, 3, 9, 7 };
        sln.mergeSort(arr, 0, arr.length - 1);
        assertThat(arr).containsExactly( 1, 3, 4, 7, 9 );
    }

    @Test
    void test_ex_02() {
        int[] arr = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        sln.mergeSort(arr, 0, arr.length - 1);
        assertThat(arr).containsExactly( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
    }

    @Test
    void test_ex_03() {
        int[] arr = new int[] { 1, 3 , 2 };
        sln.mergeSort(arr, 0, arr.length - 1);
        assertThat(arr).containsExactly( 1, 2, 3 );
    }

    @Test
    void test_ex_MID() {
        int[] arr = new int[] { -10, 4, 1, 3, 9, 7, -20 };
        sln.mergeSort(arr, 1, arr.length - 2);
        assertThat(arr).containsExactly( -10, 1, 3, 4, 7, 9, -20 );
    }
}
