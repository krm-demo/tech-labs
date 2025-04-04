package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.interview.meta.FB_Prep_Sorting__Count_Distinct_Trinagles.Sides;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Sorting__Count_Distinct_Trinagles}
 */
public class TestCase_FB_Prep_Sorting__Count_Distinct_Trinagles {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(FB_Prep_Sorting__Count_Distinct_Trinagles.Solution sln) {
        // [[2, 2, 3], [3, 2, 2], [2, 5, 6]]
        ArrayList<Sides> arr = Stream.of(
            new Sides(2, 2, 3),
            new Sides(3, 2, 2),
            new Sides(2, 5, 6)
        ).collect(Collectors.toCollection(ArrayList::new));
        assertThat(sln.countDistinctTriangles(arr)).isEqualTo(2);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(FB_Prep_Sorting__Count_Distinct_Trinagles.Solution sln) {
        // [[8, 4, 6], [100, 101, 102], [84, 93, 173]]
        ArrayList<Sides> arr = Stream.of(
            new Sides(8, 4, 6),
            new Sides(100, 101, 102),
            new Sides(84, 93, 173)
        ).collect(Collectors.toCollection(ArrayList::new));
        assertThat(sln.countDistinctTriangles(arr)).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_main_01(FB_Prep_Sorting__Count_Distinct_Trinagles.Solution sln) {
        ArrayList<Sides> arr = Stream.of(
            new Sides(7, 6, 5),
            new Sides(5, 7, 6),
            new Sides(8, 2, 9),
            new Sides(2, 3, 4),
            new Sides(2, 4, 3)
        ).collect(Collectors.toCollection(ArrayList::new));
        assertThat(sln.countDistinctTriangles(arr)).isEqualTo(3);
    }

    @EnumSource
    @ParameterizedTest
    void test_main_02(FB_Prep_Sorting__Count_Distinct_Trinagles.Solution sln) {
        // [[8, 4, 6], [100, 101, 102], [84, 93, 173]]
        ArrayList<Sides> arr = Stream.of(
            new Sides(3, 4, 5),
            new Sides(8, 8, 9),
            new Sides(7, 7, 7)
        ).collect(Collectors.toCollection(ArrayList::new));
        assertThat(sln.countDistinctTriangles(arr)).isEqualTo(3);
    }
}
