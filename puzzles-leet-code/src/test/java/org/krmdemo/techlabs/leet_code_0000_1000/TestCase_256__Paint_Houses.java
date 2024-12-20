package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_256__Paint_Houses}
 */
public class TestCase_256__Paint_Houses {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_256__Paint_Houses.Solution sln) {
        // costs = [[17,2,17],[16,16,5],[14,3,19]]
        int[][] costs = new int[][] {
            { 17, 2, 17}, { 16, 16, 5 }, { 14, 3, 19}
        };
        assertThat(sln.minCost(costs)).isEqualTo(10);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_256__Paint_Houses.Solution sln) {
        // costs = [[7,6,2]]
        assertThat(sln.minCost(new int[][]{{ 7,6,2 }})).isEqualTo(2);
    }

    @EnumSource
    @ParameterizedTest
    void test_tc_23_of_100(Problem_256__Paint_Houses.Solution sln) {
        // [[13,12,19],[17,19,3],[16,11,10],[11,6,19],[5,8,20],[11,4,3],[3,17,6],[11,3,19],
        // [15,16,5],[12,8,13],[8,7,20],[11,8,17],[17,8,4],[14,7,17],[15,7,9],[20,9,15],
        // [14,7,10],[7,18,9],[15,4,17],[12,16,15],[14,11,7],[10,12,20],[7,18,9],[18,3,19]]
        int[][] costs = new int[][] {
            {13,12,19},{17,19,3},{16,11,10},{11,6,19},{5,8,20},{11,4,3},{3,17,6},{11,3,19},
            {15,16,5},{12,8,13},{8,7,20},{11,8,17},{17,8,4},{14,7,17},{15,7,9},{20,9,15},
            {14,7,10},{7,18,9},{15,4,17},{12,16,15},{14,11,7},{10,12,20},{7,18,9},{18,3,19}
//            , { 1, 1, 1 }, { 1, 2, 3 }, { 3, 2, 1 }
        };
        long tsStart = System.nanoTime();
        assertThat(sln.minCost(costs)).isEqualTo(171);
//        assertThat(sln.minCost(costs)).isEqualTo(174);
        long tsFinish = System.nanoTime();
        System.out.printf("sln.minCost(size = %d) takes %,d nanos;%n", costs.length, (tsFinish - tsStart));
    }

}
