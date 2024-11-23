package org.krmdemo.techlabs.sandbox;

import com.google.common.collect.Streams;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RecordStreamTest {

    record Pair<First,Second>(First first, Second second) {
        public static<First,Second>
        Pair<First,Second> create(First first, Second second) {
            return new Pair<>(first, second);
        }
    }

//    public static<First,Second>
//    Stream<Pair<First,Second>> pair(Stream<First> streamFirst, Stream<Second> streamSecond) {
//        Iterator<First> iterFirst = streamFirst.iterator();
//        Iterator<Second> iterSecond = streamSecond.iterator();
//    }

    @Test
    void testGuava() {
        IntStream streamOne = IntStream.range(0, 10);
        Stream<String> streamTwo = IntStream.range(0, 10).boxed().map(i -> "(" + i + ")");
        Streams.zip(streamOne.boxed(), streamTwo, Pair::new).forEach(System.out::println);
    }
}
