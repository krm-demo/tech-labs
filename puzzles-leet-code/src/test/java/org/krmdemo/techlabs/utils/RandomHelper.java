package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.krmdemo.techlabs.utils.CountingUtils.swap;
import static org.krmdemo.techlabs.utils.CumulativeUtils.reversedArr;

/**
 * Helper-wrapper over standard {@link Random} to generate random arrays and collections
 */
public class RandomHelper extends Random {

    public RandomHelper() {
        super(); // non-reproducible random sequences !!!
    }

    public RandomHelper(long seed) {
        super(seed);
    }

    public int[] randomRangeArr(int lowBound, int highBound) {
        return shuffle(range(lowBound, highBound));
    }

    public int[] randomRangeClosedArr(int lowBound, int highBound) {
        return shuffle(rangeClosed(lowBound, highBound));
    }

    public int[] shuffle(IntStream values) {
        int[] valuesArr = values.toArray();
        shuffleArr(valuesArr);
        return valuesArr;
    }

    public void shuffleArr(int[] valuesArr) {
        // a little bit more efficient than using Collections.shuffle and subsequence copying
        for (int i = valuesArr.length; i > 1; i--) {
            swap(valuesArr, i - 1, nextInt(valuesArr.length));
        }
    }

    public int[] randomDistinctIntArr(long N, int lowBound, int highBound) {
        return ints(5 * N, lowBound, highBound)
            .distinct().limit(N).toArray();
    }

    public int[] randomSortedIntArr(long N, int lowBound, int highBound) {
        return ints(N, lowBound, highBound)
            .sorted().limit(N).toArray();
    }

    public int[] randomSortedReversedIntArr(long N, int lowBound, int highBound) {
        return reversedArr(randomSortedIntArr(N, lowBound, highBound));
    }

    public int[] randomIncreasingIntArr(long N, int lowBound, int highBound) {
        return ints(5 * N, lowBound, highBound)
            .distinct().limit(N).sorted().toArray();
    }

    public int[] randomDecreasingIntArr(long N, int lowBound, int highBound) {
        return reversedArr(randomIncreasingIntArr(N, lowBound, highBound));
    }

    public int[] zeroIntArr(long N) {
        return constantIntArr(N, 0);
    }

    public int[] constantIntArr(long N, int constant) {
        return IntStream.generate(() -> constant).limit(N).toArray();
    }
}
