package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.CountingUtils.splitWordsList;

/**
 * Unit-Test for {@link CountingUtils}
 */
public class CountingUtilsTest {

    @Test
    void testStringSplit() {
        assertThat(splitWordsList("asa;sas asa\tsas asa  sas"))
            .isEqualTo(asList("asa", "sas", "asa", "sas", "asa", "sas"));
    }

    @Test
    void testDisjointSet() {
        List<String> wordsA = List.of("a", "b", "cde", "la-la-la");
        List<String> wordsB = List.of("1", "2", "cde", "la-la-la");
        Set<String> uniqueA = new HashSet<>(wordsA);
        Set<String> uniqueB = new HashSet<>(wordsB);

        Set<String> intersection = new HashSet<>(wordsA);
        intersection.retainAll(uniqueB);

        Set<String> uniqueDisjoint = new TreeSet<>(uniqueA);
        uniqueDisjoint.addAll(uniqueB);
        uniqueDisjoint.removeAll(intersection);
        assertThat(uniqueDisjoint).contains("1", "2", "a", "b");

    }
}
