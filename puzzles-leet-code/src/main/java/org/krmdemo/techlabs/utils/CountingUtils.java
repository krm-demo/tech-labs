package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Utility-Class to build a counting-map from the {@link Stream} of any objects
 * that have properly implemented {@link Object#equals(Object)} and {@link Object#hashCode()}
 * (and {@link Comparable#compareTo(Object) for sorted counting-maps}).
 */
public class CountingUtils {

    public static void swap(int[] valuesArr, int indexOne, int indexTwo) {
        int valueOne = valuesArr[indexOne];
        valuesArr[indexOne] = valuesArr[indexTwo];
        valuesArr[indexTwo] = valueOne;
    }

    public static Map<Character, Integer> countingCharsMap(String str) {
        Stream<Character> charsStream = str.chars().mapToObj(ch -> (char)ch);
        return countingMap(charsStream);
    }

    public static Stream<String> splitWords(String sentence) {
        return Arrays.stream(sentence.split("\\W"))
            .map(String::trim)
            .filter(word -> !word.isEmpty());
    }
    public static List<String> splitWordsList(String sentence) {
        return splitWords(sentence).collect(Collectors.toList());
    }

    public static <V> Map<V, Long> countingMapLong(Stream<V> valuesStream) {
        return valuesStream.collect(groupingBy(identity(), counting()));
    }
    public static <V> Map<V, Integer> countingMap(Stream<V> valuesStream) {
        return valuesStream.collect(groupingBy(identity(), summingInt(v -> 1)));
    }
    public static <V> NavigableMap<V, Integer> countingSortedMap(Stream<V> valuesStream) {
        return valuesStream.collect(groupingBy(identity(), TreeMap::new, summingInt(v -> 1)));
    }
    public static <V> LinkedHashMap<V, Integer> countingLinkedMap(Stream<V> valuesStream) {
        return valuesStream.collect(groupingBy(identity(), LinkedHashMap::new, summingInt(v -> 1)));
    }

    private static class SegmentTree256 {
        int[] parentsCount = new int[256];
        BitSet leaves = new BitSet();
    }

    // ------------------------------------------------------------------------------------------------------

    private CountingUtils() {
        // prohibit the creation of utility-class instance
        throw new UnsupportedOperationException("Cannot instantiate a utility-class " + getClass().getName());
    }
}
