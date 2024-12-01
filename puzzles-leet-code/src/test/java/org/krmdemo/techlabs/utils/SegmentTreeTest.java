package org.krmdemo.techlabs.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Unit-Test to verify the implementation of {@link SegmentTree}
 */
public class SegmentTreeTest {

    RandomHelper rnd = new RandomHelper(-101010);

    @EnumSource
    @ParameterizedTest
    void testManual_Tree(SegmentTree.Factories segmentTreeFactory) {
        final int N = 32;
        SegmentTree st = segmentTreeFactory.create(N);
        assertThat(st.firstValue()).isNull();
        assertThat(st.lastValue()).isNull();
        IntStream.range(0, 10).map(i -> i * 3).forEach(st::incrementCount);
        assertThat(st.lastValue()).isEqualTo(27);
        st.incrementCount(0);
        st.incrementCount(1);
        st.incrementCount(2);
        st.incrementCount(3);
        st.incrementCount(4);
        st.incrementCount(4);
        st.incrementCount(4);
        st.incrementCount(8);
        st.incrementCount(19);
        assertThat(st.lastValue()).isEqualTo(27);
        st.incrementCount(29);
        st.incrementCount(31);
        System.out.println(st);
        List<Integer> countValuesList =
            IntStream.range(0, N).map(st::count).boxed().toList();
        List<Integer> countLessValuesList =
            IntStream.range(0, N).map(st::countLess).boxed().toList();
        System.out.println("    st.count(...) --> " + countValuesList);
        System.out.println("st.countLess(...) --> " + countLessValuesList);
        assertThat(countValuesList).isEqualTo(List.of(
            2, 1, 1, 2, 3, 0, 1, 0, 1, 1,
            0, 0, 1, 0, 0, 1, 0, 0, 1, 1,
            0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1
        ));
        assertThat(countLessValuesList).isEqualTo(List.of(
             0,  2,  3,  4,  6,  9,  9, 10, 10, 11,
            12, 12, 12, 13, 13, 13, 14, 14, 14, 15,
            16, 16, 17, 17, 17, 18, 18, 18, 19, 19, 20, 20
        ));
    }

    @EnumSource
    @ParameterizedTest
    void testRandom(SegmentTree.Factories segmentTreeFactory) {
        final int N = 10_000;
        final int low = 123;
        final int high = 456;
        int[] nums = rnd.ints(N, low, high).toArray();
        SegmentTree st = segmentTreeFactory.create(500);
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

    @EnumSource
    @ParameterizedTest
    void testNegativeValues(SegmentTree.Factories segmentTreeFactory) {
        SegmentTree st = segmentTreeFactory.create(200);
        assertThatIllegalArgumentException().isThrownBy(
            () -> st.updateCount(-123, -345)
        );
        st.updateCount(123, -345);
        assertThat(st.count(123)).isEqualTo(-345);
        assertThat(st.countLess(123)).isEqualTo(0);
        assertThat(st.countLess(124)).isEqualTo(-345);
        assertThat(st.firstValue()).isEqualTo(123);
        assertThat(st.lastValue()).isEqualTo(123);
    }

    @ParameterizedTest
    @EnumSource(names = {"FENWICK_ARRAY","SEGMENT_ARRAY"})  // <-- binary segment-tree does not have capacity
    void testExceedCapacity(SegmentTree.Factories segmentTreeFactory) {
        SegmentTree st = segmentTreeFactory.create();
        assertThatIllegalArgumentException().isThrownBy(
            () -> st.decrementCount(1234)
        ).withMessageMatching("value must be less than \\d+, but it equals to 1234");
    }
}
