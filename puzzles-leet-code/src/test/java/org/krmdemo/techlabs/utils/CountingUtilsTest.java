package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.CountingUtils.countingCharsMap;
import static org.krmdemo.techlabs.utils.CountingUtils.countingLinkedMap;
import static org.krmdemo.techlabs.utils.CountingUtils.countingMap;
import static org.krmdemo.techlabs.utils.CountingUtils.countingMapLong;
import static org.krmdemo.techlabs.utils.CountingUtils.countingSortedMap;
import static org.krmdemo.techlabs.utils.CountingUtils.splitWordsList;

/**
 * Unit-Test for {@link CountingUtils}
 */
public class CountingUtilsTest {

    final RandomHelper rnd = new RandomHelper(123);

    @Test
    void testCountingMap() {
        int[] nums = rnd.ints(45, -10, 15).toArray();
        Map<Integer, Integer> cntMap = countingMap(Arrays.stream(nums).boxed());
        Map<Integer, Long> cntMapLong = countingMapLong(Arrays.stream(nums).boxed());
        NavigableMap<Integer, Integer> cntMapSorted = countingSortedMap(Arrays.stream(nums).boxed());
        SequencedMap<Integer, Integer> cntMapLinked = countingLinkedMap(Arrays.stream(nums).boxed());
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("      cntMap --> " + cntMap);
        System.out.println("  cntMapLong --> " + cntMapLong);
        System.out.println("cntMapSorted --> " + cntMapSorted);
        System.out.println("cntMapLinked --> " + cntMapLinked);
        assertThat(cntMapSorted.keySet()).isSubsetOf(cntMap.keySet());
        assertThat(cntMapLinked.keySet()).isSubsetOf(cntMap.keySet());
        assertThat(cntMap.entrySet().stream()
            .filter(e -> Objects.equals(Long.valueOf(e.getValue()), cntMapLong.get(e.getKey())))
            .filter(e -> Objects.equals(e.getValue(), cntMapSorted.get(e.getKey())))
            .filter(e -> Objects.equals(e.getValue(), cntMapLinked.get(e.getKey())))
            .count()).isEqualTo(cntMap.size());

        IntSummaryStatistics stats = Arrays.stream(nums).summaryStatistics();
        System.out.println("stats --> " + stats);
        assertThat(stats.getMin()).isEqualTo(cntMapSorted.firstKey());
        assertThat(stats.getMax()).isEqualTo(cntMapSorted.lastKey());
        assertThat(stats.getSum()).isEqualTo(Arrays.stream(nums).sum());
        assertThat(stats.getCount()).isEqualTo(nums.length);
        assertThat(cntMap.values().stream().reduce(Integer::sum).orElseThrow()).isEqualTo(nums.length);
    }

    @Test
    void testStringSplit() {
        assertThat(splitWordsList("asa;sas asa\tsas asa  sas"))
            .isEqualTo(asList("asa", "sas", "asa", "sas", "asa", "sas"));
    }

    @Test
    void testCountChars() {
        String factorial1000 = NumberUtils.factorial(1000).toString();
        assertThat(countingCharsMap(factorial1000)).isEqualTo(Map.of(
            '0', 472, '1', 239, '2', 248, '3', 216, '4', 229,
            '5', 213, '6', 231, '7', 217, '8', 257, '9', 246
        ));
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
