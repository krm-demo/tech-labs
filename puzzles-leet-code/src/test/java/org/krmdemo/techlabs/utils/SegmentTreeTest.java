package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Unit-Test to verify the implementation of {@link SegmentTree}
 */
public class SegmentTreeTest {

    RandomHelper rnd = new RandomHelper(-101010);

    @Test
    void testManual() {
        SegmentTree st = SegmentTree.create();
        IntStream.range(0, 10).map(i -> i * 3).forEach(st::incrementCount);
        st.incrementCount(0);
        st.incrementCount(1);
        st.incrementCount(2);
        st.incrementCount(3);
        st.incrementCount(4);
        System.out.println(st);
        final int N = 35;
        List<Integer> countValuesList =
            IntStream.range(0, N).map(st::count).boxed().toList();
        List<Integer> countLessValuesList =
            IntStream.range(0, N).map(st::countLess).boxed().toList();
        System.out.println("    st.count(...) --> " + countValuesList);
        System.out.println("st.countLess(...) --> " + countLessValuesList);
        assertThat(countValuesList).isEqualTo(List.of(
            2, 1, 1, 2, 1, 0, 1, 0, 0, 1,
            0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
            0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
            0, 0, 0, 0, 0
        ));
        assertThat(countLessValuesList).isEqualTo(List.of(
             0,  2,  3,  4,  6,  7,  7,  8,  8,  8,
             9,  9,  9, 10, 10, 10, 11, 11, 11, 12,
            12, 12, 13, 13, 13, 14, 14, 14, 15, 15,
            15, 15, 15, 15, 15
        ));
    }

    @Test
    void testRandom() {
        final int N = 10_000;
        final int low = 123;
        final int high = 456;
        int[] nums = rnd.ints(N, low, high).toArray();
        SegmentTree st = SegmentTree.create();
        Arrays.stream(nums).forEach(st::incrementCount);
        for (int i = low-10; i < high+10; i++) {
            final int value = i;
            long count = Arrays.stream(nums).filter(v -> v == value).count();
            assertThat(st.count(value))
                .describedAs("st.count(%d) = %d <> %d", value, st.count(value), count)
                .isEqualTo(count);
            long countLess = Arrays.stream(nums).filter(v -> v < value).count();
            assertThat(st.countLess(value))
                .describedAs("st.countLess(%d) = %d <> %d", value, st.countLess(value), countLess)
                .isEqualTo(countLess);
        }
    }

    @Test
    void testNegativeValues() {
        SegmentTree st = SegmentTree.create();
        assertThatIllegalArgumentException().isThrownBy(
            () -> st.updateCount(-123, -345)
        );
        st.updateCount(123, -345);
        assertThat(st.count(123)).isEqualTo(-345);
        assertThat(st.countLess(123)).isEqualTo(0);
        assertThat(st.countLess(124)).isEqualTo(-345);
    }
}
