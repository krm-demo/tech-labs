package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Test;
import org.krmdemo.techlabs.utils.RandomHelper;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamStatsTest {

    RandomHelper rnd = new RandomHelper(456);

    @Test
    void test_MinMaxIndex() {
        int[] arr = rnd.ints(-25, 25).limit(15).toArray();
        IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
        System.out.println("arr --> " + Arrays.toString(arr));
        System.out.println("stats --> " + stats);

        class MinMaxIndex {
            int index = 0, minIndex = 0, maxIndex = 0;
            int value() { return arr[index]; }
            int minValue() { return arr[minIndex]; }
            int maxValue() { return arr[maxIndex]; }
            void accept(int index) {
                minIndex = arr[minIndex] > arr[index] ? index : minIndex;
                maxIndex = arr[maxIndex] < arr[index] ? index : maxIndex;
                this.index = index;
            }
            void combine(MinMaxIndex other) {
                minIndex = arr[minIndex] > arr[other.minIndex] ? other.minIndex : minIndex;
                maxIndex = arr[maxIndex] < arr[other.maxIndex] ? other.maxIndex : maxIndex;
            }
            MinMaxIndex copy() {
                MinMaxIndex copy = new MinMaxIndex();
                copy.index = this.index;
                copy.minIndex = this.minIndex;
                copy.maxIndex = this.maxIndex;
                return copy;
            }
            @Override
            public String toString() {
                return String.format("at[%2d]:%3d,  min:[%2d]%3d, max:[%2d]%3d;",
                    index, value(), minIndex, minValue(), maxIndex, maxValue());
            }
        }
        MinMaxIndex totalMinMax = IntStream.range(0, arr.length)
            .collect(
                MinMaxIndex::new,
                MinMaxIndex::accept,
                MinMaxIndex::combine
            );
        System.out.println("totalMinMax --> " + totalMinMax);
        assertThat(totalMinMax.minValue()).isEqualTo(stats.getMin());
        assertThat(totalMinMax.maxValue()).isEqualTo(stats.getMax());

        MinMaxIndex minMax = new MinMaxIndex();
        IntFunction<MinMaxIndex> minMaxSoFar = index -> {
            minMax.accept(index);
            return minMax.copy();
        };

        MinMaxIndex[] cumMinMaxArr = IntStream.range(0, arr.length)
            .mapToObj(minMaxSoFar)
            .toArray(MinMaxIndex[]::new);
        System.out.println("running MinMaxIndex: " +
            Arrays.stream(cumMinMaxArr)
                .map(MinMaxIndex::toString)
                .collect(joining("\n  ", "{\n  ", "\n}"))
        );
    }


    @Test
    void test_MinMaxRecord() {
        int[] arr = rnd.ints(-25, 25).limit(15).toArray();
        IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
        System.out.println("arr --> " + Arrays.toString(arr));
        System.out.println("stats --> " + stats);

        record MinMax(int value, int min, int max) {
            MinMax(int value) {
                this(value, value, value);
            }
            MinMax applyInt(int value) {
                return new MinMax(
                    value,
                    Math.min(min, value),
                    Math.max(max, value)
                );
            }
        }

        MinMax[] minMax = new MinMax[] { new MinMax(arr[0]) };
        MinMax[] cumMinMaxArr = Arrays.stream(arr)
            .mapToObj(value -> {
                minMax[0] = minMax[0].applyInt(value);
                return minMax[0];
            })
            .toArray(MinMax[]::new);
        System.out.println("running MinMaxIndex: " +
            Arrays.stream(cumMinMaxArr)
                .map(MinMax::toString)
                .collect(joining("\n  ", "{\n  ", "\n}"))
        );
    }
}
