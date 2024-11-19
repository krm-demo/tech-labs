package org.krmdemo.techlabs.utils;

import java.util.stream.IntStream;

/**
 * Utility-class with types and factory-methods to work with indexes,
 * which are used to re-arrange the elements of source collections.
 */
public class IndexesUtils {

    public static IntStream ascending(int size) {
        return IntStream.range(0, size);
    }

    public static IntStream descending(int size) {
        return ascending(size).map(i -> size - i - 1);
    }

    // TODO: implement FactorialDigits and MultiFactotialDigits classes
}
