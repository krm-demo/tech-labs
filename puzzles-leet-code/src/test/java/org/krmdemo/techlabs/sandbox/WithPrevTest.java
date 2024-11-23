package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Test;
import org.krmdemo.techlabs.utils.RandomHelper;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class WithPrevTest {

    private final RandomHelper rnd = new RandomHelper(0);

    private IntFunction<IntStream> withPrev(ToIntBiFunction<Integer,Integer> withPrevFunc) {
        Integer[] prev = new Integer[] { null };
        return value -> {
            Integer prevValue = prev[0];
            prev[0] = value;
            return prevValue == null ? IntStream.empty() :
                IntStream.of(withPrevFunc.applyAsInt(prevValue, value));
        };
    }

    @Test
    void testPrevNext() {
        IntStream prevNext = range(0,10).flatMap(withPrev(
            (prev, next) -> {
                int multPrevNext = prev * next;
                System.out.printf("( %s, %s ) --> %d;%n", prev, next, multPrevNext);
                return multPrevNext;
            }
        ));
        System.out.println(prevNext.boxed().toList());
    }

    private IntFunction<IntStream> prevDiff() {
        return withPrev((prev, next) -> next - prev);
    }

    @Test
    void testPrevDiff() {
        int[] arr20 = rnd.randomRangeArr(1, 20);
        int[] diff = Arrays.stream(arr20).flatMap(prevDiff()).toArray();
        System.out.println("arr --> " + Arrays.toString(arr20));
        System.out.println("diff: " + Arrays.toString(diff));
    }

    private IntFunction<IntStream> withPrevMin(ToIntBiFunction<Integer,Integer> withMinFunc) {
        Integer[] prevMin = new Integer[] { null };
        return value -> {
            Integer prevMinValue = prevMin[0];
            prevMin[0] = prevMinValue == null ? value : Math.min(prevMinValue, value);
            return prevMinValue == null ? IntStream.empty() :
                IntStream.of(withMinFunc.applyAsInt(prevMinValue, value));
        };
    }

    @Test
    void testElevationFromMin() {
        int[] arr20 = rnd.randomRangeArr(1, 25);
        int[] elevFromMin = Arrays.stream(arr20).flatMap(withPrevMin(
            (prevMinValue, value) -> value - prevMinValue
        )).toArray();
        System.out.println("arr --> " + Arrays.toString(arr20));
        System.out.println("elevFromMin: " + Arrays.toString(elevFromMin));
        System.out.println("max(elevFromMin) = " + Arrays.stream(elevFromMin).max().orElse(0));
    }

    @Test
    void testShuffleTest() {
        record MinElevResult(int rndIndex, int minElev, int[] arr){}
        MinElevResult minElevResult = null;
        RandomHelper rndLocal = new RandomHelper(152);
        for (int i = 0; i < 1000; i++) {
            final int low = 1;
            final int high = 25;
            int[] arr20 = rndLocal.randomRangeClosedArr(low, high);
            int maxElevFromMin = Arrays.stream(arr20).flatMap(withPrevMin(
                (prevMinValue, value) -> value - prevMinValue
            )).max().orElse(0);
            if (minElevResult == null || minElevResult.minElev > maxElevFromMin) {
                minElevResult = new MinElevResult(i, maxElevFromMin, arr20);
            }
        }
        System.out.println("MinElevResult.rndIndex = " + minElevResult.rndIndex);
        System.out.println("MinElevResult.minElev = " + minElevResult.minElev);
        System.out.println("MinElevResult.arr --> " + Arrays.toString(minElevResult.arr));
    }


}
