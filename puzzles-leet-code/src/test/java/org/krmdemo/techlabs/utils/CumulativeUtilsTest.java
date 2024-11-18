package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.CumulativeUtils.*;

/**
 * Unit-Test to verify the utility-methods of the class {@link CumulativeUtils}
 */
public class CumulativeUtilsTest {

    @Test
    public void testCumSumStream() {
        assertThat(cumSum(1, 2, 3, 4, 5).boxed().toList()).isEqualTo(asList(0, 1, 3, 6, 10, 15));
        assertThat(cumSum(1, 1, 1, 1, 1).boxed().toList()).isEqualTo(asList(0, 1, 2, 3, 4, 5));
        assertThat(cumSum(0, 0, 0, 0, 0).boxed().toList()).isEqualTo(asList(0, 0, 0, 0, 0, 0));
        assertThat(cumSum(1, 0, 1, 0, 1).boxed().toList()).isEqualTo(asList(0, 1, 1, 2, 2, 3));
    }

    @Test
    public void testCumSumList() {
        assertThat(cumSumList(1, 2, 3, 4, 5)).isEqualTo(asList(0, 1, 3, 6, 10, 15));
        assertThat(cumSumList(1, 1, 1, 1, 1)).isEqualTo(asList(0, 1, 2, 3, 4, 5));
        assertThat(cumSumList(0, 0, 0, 0, 0)).isEqualTo(asList(0, 0, 0, 0, 0, 0));
        assertThat(cumSumList(1, 0, 1, 0, 1)).isEqualTo(asList(0, 1, 1, 2, 2, 3));
    }

    @Test
    public void testCumSumNoLeadingZero() {
        assertThat(cumSumList(asList(1, 2, 3, 4, 5), false)).isEqualTo(asList(1, 3, 6, 10, 15));
        assertThat(cumSumList(asList(1, 1, 1, 1, 1), false)).isEqualTo(asList(1, 2, 3, 4, 5));
        assertThat(cumSumList(asList(0, 0, 0, 0, 0), false)).isEqualTo(asList(0, 0, 0, 0, 0));
        assertThat(cumSumList(asList(1, 0, 1, 0, 1), false)).isEqualTo(asList(1, 1, 2, 2, 3));
    }

    @Test
    public void testCumSumDeque() {
        assertThat(cumSumDeque(1, 2, 3, 4, 5)).isEqualTo(asList(0, 1, 3, 6, 10, 15));
        assertThat(cumSumDeque(1, 1, 1, 1, 1)).isEqualTo(asList(0, 1, 2, 3, 4, 5));
        assertThat(cumSumDeque(0, 0, 0, 0, 0)).isEqualTo(asList(0, 0, 0, 0, 0, 0));
        assertThat(cumSumDeque(1, 0, 1, 0, 1)).isEqualTo(asList(0, 1, 1, 2, 2, 3));

        assertThat(cumSumDeque(asList(1, 2, 3, 4, 5))).isEqualTo(asList(0, 1, 3, 6, 10, 15));
        assertThat(cumSumDeque(asList(1, 1, 1, 1, 1))).isEqualTo(asList(0, 1, 2, 3, 4, 5));
        assertThat(cumSumDeque(asList(0, 0, 0, 0, 0))).isEqualTo(asList(0, 0, 0, 0, 0, 0));
        assertThat(cumSumDeque(asList(1, 0, 1, 0, 1))).isEqualTo(asList(0, 1, 1, 2, 2, 3));
    }

    @Test
    public void testCumMinMaxSingle() {
        assertThat(cumMinBefore(0, -1).boxed().toList()).isEqualTo(List.of(0, -1));
        assertThat(cumMaxBefore(0, -1).boxed().toList()).isEqualTo(List.of(0, 0));
    }

    @Test
    public void testCumMinMaxBefore() {
        assertThat(cumMinBefore(19, 44, 33, 54, 54, 77, 63, 34, 63, 17, 10, 7, 12, 3, 89).boxed().toList())
            .isEqualTo(asList(19, 19, 19, 19, 19, 19, 19, 19, 19, 17, 10, 7, 7, 3, 3));
        assertThat(cumMaxBefore(19, 44, 33, 54, 54, 77, 63, 34, 63, 17, 10, 7, 12, 3, 89).boxed().toList())
            .isEqualTo(asList(19, 44, 44, 54, 54, 77, 77, 77, 77, 77, 77, 77, 77, 77, 89));
    }

    @Test
    public void testCumMinMaxBeforeList() {
        assertThat(cumMinBeforeList(19, 44, 33, 54, 54, 77, 63, 34, 63, 17, 10, 7, 12, 3, 89))
            .isEqualTo(asList(19, 19, 19, 19, 19, 19, 19, 19, 19, 17, 10, 7, 7, 3, 3));
        assertThat(cumMaxBeforeList(19, 44, 33, 54, 54, 77, 63, 34, 63, 17, 10, 7, 12, 3, 89))
            .isEqualTo(asList(19, 44, 44, 54, 54, 77, 77, 77, 77, 77, 77, 77, 77, 77, 89));
    }

    @Test
    public void testCumMinMaxAfter() {
        assertThat(cumMinAfter(41, 46, 25, 57, 8, 48, 96, 52, 49, 73, 95, 54, 27, 44, 68).boxed().toList())
            .isEqualTo(asList(8, 8, 8, 8, 8, 27, 27, 27, 27, 27, 27, 27, 27, 44, 68));
        assertThat(cumMinAfter(asList(41, 46, 25, 57, 8, 48, 96, 52, 49, 73, 95, 54, 27, 44, 68)).toList())
            .isEqualTo(asList(8, 8, 8, 8, 8, 27, 27, 27, 27, 27, 27, 27, 27, 44, 68));
        assertThat(cumMinAfterList(41, 46, 25, 57, 8, 48, 96, 52, 49, 73, 95, 54, 27, 44, 68))
            .isEqualTo(asList(8, 8, 8, 8, 8, 27, 27, 27, 27, 27, 27, 27, 27, 44, 68));

        assertThat(cumMaxAfter(67, 24, 37, 42, 58, 83, 79, 50, 42, 47, 65, 44, 9, 49, 59).boxed().toList())
            .isEqualTo(asList(83, 83, 83, 83, 83, 83, 79, 65, 65, 65, 65, 59, 59, 59, 59));
        assertThat(cumMaxAfter(asList(67, 24, 37, 42, 58, 83, 79, 50, 42, 47, 65, 44, 9, 49, 59)).toList())
            .isEqualTo(asList(83, 83, 83, 83, 83, 83, 79, 65, 65, 65, 65, 59, 59, 59, 59));
        assertThat(cumMaxAfterList(67, 24, 37, 42, 58, 83, 79, 50, 42, 47, 65, 44, 9, 49, 59))
            .isEqualTo(asList(83, 83, 83, 83, 83, 83, 79, 65, 65, 65, 65, 59, 59, 59, 59));
    }

    @Test
    public void testJoiningReversed() {
        // TODO: publish following stuff somewhere here https://stackoverflow.com/a/62862901/3738497
        final int N = 10;
        System.out.printf("collecting the sequential stream over the range [ 1, %d ]:%n", N);
        System.out.println(rangeClosedInt(N).boxed()
            .map(String::valueOf)
            .collect(joining(" < ", "- standard { ", " }"))
        );
        System.out.println(rangeClosedInt(N).boxed()
            .collect(joiningReversed(" > ", "- reversed { ", " }"))
        );
        System.out.println("--------------------------------------------------");
        System.out.println("the same collecting, but with current thread name:");
        System.out.println(rangeClosedInt(N).boxed()
            .map(value -> value + "(" + Thread.currentThread().getName() + ")")
            .collect(joining(" < ", "- standard :: ", " ::"))
        );
        System.out.println(rangeClosedInt(N).boxed()
            .map(value -> value + "(" + Thread.currentThread().getName() + ")")
            .collect(joiningReversed(" > ", "- reversed :: ", " ::"))
        );
        System.out.println("------------------------------------------------------");
        System.out.println("the same collecting, but now over the parallel stream:");
        System.out.println(rangeClosedInt(N).boxed()
            .parallel() // <-- thread names are different, but the order is not violated!
            .map(value -> value + "(" + Thread.currentThread().getName() + ")")
            .collect(joining(" < ", "- standard ::: ", " :::"))
        );
        System.out.println(rangeClosedInt(N).boxed()
            .parallel() // <-- our reversed-joiner keeps the reverse order of original stream!
            .map(value -> value + "(" + Thread.currentThread().getName() + ")")
            .collect(joiningReversed(" > ", "- reversed ::: ", " :::"))
        );

        final int M = 100;
        assertThat(rangeClosedInt(M).boxed().collect(joiningReversed(",")))
            .isEqualTo(reversedList(rangeClosedInt(M)).stream().map(String::valueOf).collect(joining(",")));
        assertThat(rangeClosedInt(M).boxed().parallel().collect(joiningReversed(",")))
            .isEqualTo(reversedList(rangeClosedInt(M)).stream().map(String::valueOf).collect(joining(",")));
    }

    private static IntStream rangeClosedInt(int size) {
        return IntStream.rangeClosed(1, size);
    }

//    private static LongStream rangeClosedLong(long size) {
//        return LongStream.rangeClosed(1, size);
//    }
}
