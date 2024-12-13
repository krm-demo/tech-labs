package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Count_Min_Swaps_To_Sort}
 */
public class TestCase_GFG_Arrays__Count_Min_Swaps_To_Sort {

    private final GFG_Arrays__Count_Min_Swaps_To_Sort sln =
        GFG_Arrays__Count_Min_Swaps_To_Sort.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.minSwaps(new int[] { 2, 8, 5, 4 })).isEqualTo(1);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.minSwaps(new int[] { 10, 19, 6, 3, 5 })).isEqualTo(2);
    }

    @Test
    void test_ex_03() {
        assertThat(sln.minSwaps(new int[] { 1, 3, 4, 5, 6 })).isEqualTo(0);
    }

    @Test
    void test_reverse_order_even() {
        final int N = 10;
        int[] arr = IntStream.range(0, N).map(i -> N - i).toArray();
        assertThat(sln.minSwaps(arr)).isEqualTo(N / 2);
    }

    @Test
    void test_reverse_order_odd() {
        final int N = 11;
        int[] arr = IntStream.range(0, N).map(i -> N - i).toArray();
        assertThat(sln.minSwaps(arr)).isEqualTo(N / 2);
    }

    @Test
    void test_shiftRight() {
        final int N = 15;
        int[] arr = IntStream.concat(
            IntStream.of(N),
            IntStream.rangeClosed(1, N - 1)
        ).toArray();
        assertThat(sln.minSwaps(arr)).isEqualTo(N - 1);
    }

    @Test
    void test_shiftLeft() {
        final int N = 12;
        int[] arr = IntStream.concat(
            IntStream.rangeClosed(2, N),
            IntStream.of(1)
        ).toArray();
        assertThat(sln.minSwaps(arr)).isEqualTo(N - 1);
    }

    @Test
    void test_rotate() {
        final int M = 3;
        final int N = 12;
        int[] arr = IntStream.concat(
            IntStream.rangeClosed(M + 1, M + N),
            IntStream.rangeClosed(1, M)
        ).toArray();
        assertThat(sln.minSwaps(arr)).isEqualTo(N);
    }
}
