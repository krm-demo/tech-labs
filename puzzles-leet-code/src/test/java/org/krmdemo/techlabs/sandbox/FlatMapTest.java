package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static java.util.stream.IntStream.range;

/**
 * Test-case to play with flat-mappers (like sliding-window, etc...)
 */
public class FlatMapTest {

    @FunctionalInterface
    private interface ToIntTripletFunction {
        int applyAsInt(int prev, int mid, int next);
    }

    private IntFunction<IntStream> prevNext(ToIntBiFunction<Integer, Integer> prevNextFunc) {
        return windowSlide(2, slideFrame ->
            prevNextFunc.applyAsInt(slideFrame.getFirst(), slideFrame.getLast())
        );
    }

    private IntFunction<IntStream> triplet(ToIntTripletFunction tripletFunc) {
        return windowSlide(3, slideFrame -> {
            Iterator<Integer> iter = slideFrame.iterator();
            return tripletFunc.applyAsInt(iter.next(), iter.next(), iter.next());
        });
    }

    private IntFunction<IntStream> windowSlide(int slideSize, ToIntFunction<Deque<Integer>> mapFunc) {
        Deque<Integer> slideFrame = new ArrayDeque<>(slideSize);
        return value -> {
            slideFrame.addLast(value);
            if (slideFrame.size() < slideSize) {
                return IntStream.empty();
            } else if (slideFrame.size() > slideSize) {
                slideFrame.removeFirst();
            }
            int mappedValue = mapFunc.applyAsInt(slideFrame);
            return slideFrame.size() < slideSize ? IntStream.empty() : IntStream.of(mappedValue);
        };
    }

    @Test
    void testPrevNext() {
        IntStream prevNext = range(0,10).flatMap(prevNext(
            (prev, next) -> {
                int multPrevNext = prev * next;
                System.out.printf("( %s, %s ) --> %d;%n", prev, next, multPrevNext);
                return multPrevNext;
            }
        ));
        System.out.println(prevNext.boxed().toList());
    }

    @Test
    void testTriplet() {
        IntStream prevCurrNext = range(0,15).flatMap(triplet(
            (prev, curr, next) -> {
                double trpletRes = 1.0 * prev * next / curr;
                System.out.printf("( %2d, %2d, %2d ) --> %.2f;%n", prev, curr, next, trpletRes);
                return toIntExact(round(trpletRes));
            }
        ));
        System.out.println(prevCurrNext.boxed().toList());
    }

    @Test
    void testGroupByMod() {
        final int K = 5;
        int[] rangeN = range(0, 18).toArray();
        Map<Integer, List<Integer>> mapByMod =
            Arrays.stream(rangeN).boxed()
                .collect(Collectors.groupingBy(
            v -> v % K
            ));
        System.out.println("rangeN --> " + Arrays.toString(rangeN));
        System.out.printf("mapByMod ( %d groups ) :%n", mapByMod.size());
        mapByMod.forEach((mod, modList) ->
            System.out.printf("mod = %d --> %s;%n", mod, modList)
        );
    }
}
