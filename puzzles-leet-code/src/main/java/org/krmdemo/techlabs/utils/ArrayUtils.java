package org.krmdemo.techlabs.utils;

import java.util.stream.IntStream;

public class ArrayUtils {

    public static int[] zeroIntArr(long N) {
        return constantIntArr(N, 0);
    }

    public static int[] constantIntArr(long N, int constant) {
        return IntStream.generate(() -> constant).limit(N).toArray();
    }
}
