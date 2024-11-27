package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.stream.IntStream;

public class ArrayUtils {

    public static int[] zeroIntArr(long N) {
        return constantIntArr(N, 0);
    }

    public static int[] constantIntArr(long N, int constant) {
        return IntStream.generate(() -> constant).limit(N).toArray();
    }

    public static int[] concatArr(int[]... arrays) {
        return Arrays.stream(arrays).flatMapToInt(Arrays::stream).toArray();
    }

    public static int[] reversed(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        final int N = arr.length;
        return IntStream.range(0, arr.length)
            .map(i -> arr[N - i - 1])
            .toArray();
    }

    public static List<Integer> listOf(int... valuesArr) {
        if (valuesArr == null) {
            return null;
        }
        return Arrays.stream(valuesArr)
            .boxed().toList();
    }

    public static List<Integer> reversedListOf(int... valuesArr) {
        if (valuesArr == null) {
            return null;
        }
        return listOf(valuesArr).reversed();
    }
}
