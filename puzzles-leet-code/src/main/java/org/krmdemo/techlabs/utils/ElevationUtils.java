package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Utility-Class to build Java-streams that accumulate and emit elevations
 */
public class ElevationUtils {

    public static IntStream forwardElev(IntStream values) {
        Deque<Integer> slidingWindow = new ArrayDeque<>();
        return values; // TODO: complete !!!
    }

    // ------------------------------------------------------------------------------------------------------

    private ElevationUtils() {
        // prohibit the creation of utility-class instance
        throw new UnsupportedOperationException("Cannot instantiate a utility-class " + getClass().getName());
    }
}
