package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Auxiliary class to dump some system and hardware properties
 */
public class SystemCheck {

    @Test
    void testSystemCheck() {
        main(null);
    }

    public static void main(String[] args) {
        SortedMap<String, Object> sysProps = System.getProperties().entrySet().stream()
            .collect(Collectors.toMap(e -> "" + e.getKey(), e -> "" + e.getValue(), (x, y) -> y, TreeMap::new));
        System.out.println(sysProps.entrySet().stream()
            .map(e -> String.format("sysProp[%s] = `%s`", e.getKey(), e.getValue()))
            .collect(Collectors.joining(System.lineSeparator())));
        System.out.println();

        System.out.println(Runtime.version());
        System.out.println("availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        System.out.printf("totalMemory() = %,d;%n", Runtime.getRuntime().totalMemory());
        System.out.printf("maxMemory()   = %,d;%n", Runtime.getRuntime().maxMemory());
        System.out.printf("freeMemory()  = %,d;%n", Runtime.getRuntime().freeMemory());
    }
}
