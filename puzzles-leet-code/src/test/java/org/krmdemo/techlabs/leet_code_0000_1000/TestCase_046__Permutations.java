package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.NumberUtils;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.ArrayUtils.listOf;
import static org.krmdemo.techlabs.utils.ArrayUtils.reversedListOf;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_046__Permutations}
 */
public class TestCase_046__Permutations {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_046__Permutations.Solution sln) {
        assertThat(sln.permute(new int[] { 1, 2, 3 })).contains(
            asList(1,2,3), asList(1,3,2), asList(2,1,3),
            asList(2,3,1), asList(3,1,2), asList(3,2,1)
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_046__Permutations.Solution sln) {
        assertThat(sln.permute(new int[] { 0, 1 })).contains(
            asList(0, 1), asList(1, 0)
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_046__Permutations.Solution sln) {
        assertThat(sln.permute(new int[] { 123 })).isEqualTo(List.of(List.of(123)));
    }

    @EnumSource
    @ParameterizedTest
    void test_seven_perms(Problem_046__Permutations.Solution sln) {
        int[] nums = IntStream.rangeClosed(1, 7).toArray();
        List<List<Integer>> permutations = sln.permute(nums);
        assertThat(permutations).hasSize(NumberUtils.factorialInt(7));
        assertThat(permutations.getFirst()).isEqualTo(listOf(nums));
        assertThat(permutations.getLast()).isEqualTo(reversedListOf(nums));
    }
}
