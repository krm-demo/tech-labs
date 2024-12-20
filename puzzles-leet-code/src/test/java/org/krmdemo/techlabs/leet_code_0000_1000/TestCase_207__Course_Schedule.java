package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_207__Course_Schedule}
 */
public class TestCase_207__Course_Schedule {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_207__Course_Schedule.Solution sln) {
        int[][] prerequisites = new int[][] { { 1, 0 } };
        assertThat(sln.canFinish(2, prerequisites)).isTrue();
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_207__Course_Schedule.Solution sln) {
        int[][] prerequisites = new int[][] { { 1, 0 }, { 0, 1 } };
        assertThat(sln.canFinish(2, prerequisites)).isFalse();
    }

    @EnumSource
    @ParameterizedTest
    void test_no_edges(Problem_207__Course_Schedule.Solution sln) {
        assertThat(sln.canFinish(2, null)).isTrue();
        assertThat(sln.canFinish(2, new int[][]{})).isTrue();
        assertThatIllegalArgumentException().isThrownBy(
            () -> sln.canFinish(0, null)
        ).withMessageContaining("must be positive");
    }

    @EnumSource
    @ParameterizedTest
    void test_edges_tree(Problem_207__Course_Schedule.Solution sln) {
        int[][] prerequisites = new int[][] {
            { 0, 1 }, { 0, 2 }, { 0, 3 },
            { 1, 10 }, { 1, 11 }, { 1, 12 },
            { 2, 21 }, { 2, 22 }, { 2, 23 },
            { 3, 31 }, { 3, 32 }, { 3, 33 },
        };
        assertThat(sln.canFinish(34, prerequisites)).isTrue();

        prerequisites = Stream.concat(
            Arrays.stream(prerequisites),
            Arrays.stream(new int[][] {
                {11, 22}, {22, 33}, {33, 11}  // <-- a loop of edges
            })
        ).toArray(int[][]::new);
        assertThat(sln.canFinish(34, prerequisites)).isFalse();
    }

    @EnumSource
    @ParameterizedTest
    void test_diamond_levels(Problem_207__Course_Schedule.Solution sln) {
        int[][] prerequisites = new int[][] {
            { 0, 1 }, { 0, 2 }, { 0, 3 },
            { 1, 11 }, { 1, 12 }, { 1, 13 },
            { 2, 11 }, { 2, 12 }, { 2, 13 },
            { 3, 11 }, { 3, 12 }, { 3, 13 },
            { 11, 21 }, { 11, 22 }, { 11, 23 },
            { 12, 21 }, { 12, 22 }, { 12, 23 },
            { 13, 21 }, { 13, 22 }, { 13, 23 },
            { 21, 30 }, { 22, 30 }, { 23, 30 },
        };
        assertThat(sln.canFinish(31, prerequisites)).isTrue();

        assertThat(prerequisites[16]).containsExactly(12, 22);
        prerequisites[16][1] = 3;  // <-- change the existing edge
        assertThat(prerequisites[16]).containsExactly(12, 3);
        assertThat(sln.canFinish(31, prerequisites)).isFalse();
    }
}
