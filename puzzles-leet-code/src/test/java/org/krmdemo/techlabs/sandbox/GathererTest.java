package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Gatherers.scan;
import static java.util.stream.Gatherers.windowFixed;
import static java.util.stream.Gatherers.windowSliding;
import static java.util.stream.IntStream.rangeClosed;

/**
 * Test-Case to play with {@link java.util.stream.Gatherer}
 */
@Tag("enable-preview")
public class GathererTest {

    @Test
    void testPrefixList_scan() {
        List<String> gatherList = rangeClosed(1, 9).boxed()
            .peek(num -> {
                System.out.println("num: " + num);
            })
            .gather(scan(() -> "", (str, num) -> {
                System.out.printf("scanning: str'%s', num(%d);%n", str, num);
                return str + num;
            }))
            .toList();
        System.out.println(gatherList);
    }

    @Test
    void testPrefixList_windowFixed() {
        List<List<Integer>> gatherList = rangeClosed(1, 14).boxed()
            .gather(windowFixed(3))
            .toList();
        System.out.println(gatherList);
    }

    @Test
    void testPrefixList_windowSliding() {
        List<List<Integer>> gatherList = rangeClosed(1, 14).boxed()
            .gather(windowSliding(3))
            .toList();
        System.out.println(gatherList);
    }
}
