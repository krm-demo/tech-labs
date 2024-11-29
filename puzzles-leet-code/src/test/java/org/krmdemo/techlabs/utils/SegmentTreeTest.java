package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SegmentTreeTest {

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
}
