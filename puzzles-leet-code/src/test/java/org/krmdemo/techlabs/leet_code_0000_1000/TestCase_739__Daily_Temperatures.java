package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.RandomHelper;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_739__Daily_Temperatures}
 */
public class TestCase_739__Daily_Temperatures {

    final RandomHelper rnd = new RandomHelper(-10);

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_739__Daily_Temperatures.Solution sln) {
        assertThat(sln.dailyTemperatures(
            new int[] { 73, 74, 75, 71, 69, 72, 76, 73 }
        )).containsExactly(1, 1, 4, 2, 1, 1, 0, 0);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_739__Daily_Temperatures.Solution sln) {
        assertThat(sln.dailyTemperatures(
            new int[] { 30, 40, 50, 60 }
        )).containsExactly(1, 1, 1, 0);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_739__Daily_Temperatures.Solution sln) {
        assertThat(sln.dailyTemperatures(
            new int[] { 30, 60, 90 }
        )).containsExactly(1, 1, 0);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_04(Problem_739__Daily_Temperatures.Solution sln) {
        assertThat(sln.dailyTemperatures(
            new int[] { 90, 60, 30, 60, 90 }
        )).containsExactly(0, 3, 1, 1, 0);
    }

    @EnumSource
    @ParameterizedTest
    void test_SortedAsc(Problem_739__Daily_Temperatures.Solution sln) {
        final int N = 25;
        System.out.println("test_SortedAsc(N = " + N + ")");
        int[] arr = rnd.randomSortedIntArr(N, -10, 15);
        System.out.println("random int array  --> " + Arrays.toString(arr));
        int[] nextIncrIntervals = sln.dailyTemperatures(arr);
        System.out.println("nextIncrIntervals --> " + Arrays.toString(nextIncrIntervals));
        assertThat(nextIncrIntervals).containsExactly(
            2, 1, 1, 3, 2, 1, 2, 1, 3, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 0
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_SortedDesc(Problem_739__Daily_Temperatures.Solution sln) {
        final int N = 25;
        System.out.println("test_SortedDesc(N = " + N + ")");
        int[] arr = rnd.randomSortedReversedIntArr(N, -10, 15);
        System.out.println("random int array  --> " + Arrays.toString(arr));
        int[] nextIncrIntervals = sln.dailyTemperatures(arr);
        System.out.println("nextIncrIntervals --> " + Arrays.toString(nextIncrIntervals));
        assertThat(nextIncrIntervals).containsExactly(rnd.zeroIntArr(N));
    }

    @EnumSource
    @ParameterizedTest
    public void test_Increasing(Problem_739__Daily_Temperatures.Solution sln) {
        final int N = 25;
        System.out.println("test_Increasing(N = " + N + ")");
        int[] arr = rnd.randomIncreasingIntArr(N, 10, 95);
        System.out.println("random int array  --> " + Arrays.toString(arr));
        int[] nextIncrIntervals = sln.dailyTemperatures(arr);
        System.out.println("nextIncrIntervals --> " + Arrays.toString(nextIncrIntervals));
        int[] expectedIntervals = rnd.constantIntArr(N, 1);
        expectedIntervals[N-1] = 0;
        assertThat(nextIncrIntervals).containsExactly(expectedIntervals);
    }

    @EnumSource
    @ParameterizedTest
    public void test_Decreasing(Problem_739__Daily_Temperatures.Solution sln) {
        final int N = 25;
        System.out.println("test_Decreasing(N = " + N + ")");
        int[] arr = rnd.randomDecreasingIntArr(N, -10, 15);
        System.out.println("random int array  --> " + Arrays.toString(arr));
        int[] nextIncrIntervals = sln.dailyTemperatures(arr);
        System.out.println("nextIncrIntervals --> " + Arrays.toString(nextIncrIntervals));
        assertThat(nextIncrIntervals).containsExactly(rnd.zeroIntArr(N));
    }
}
