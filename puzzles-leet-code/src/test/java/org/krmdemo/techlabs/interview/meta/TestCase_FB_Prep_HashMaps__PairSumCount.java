package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_HashMaps__PairSums_Count}
 */
public class TestCase_FB_Prep_HashMaps__PairSumCount {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(FB_Prep_HashMaps__PairSums_Count.Solution sln) {
        assertThat(sln.numberOfWays(new int[]{ 1, 2, 3, 4, 3 }, 6)).isEqualTo(2);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(FB_Prep_HashMaps__PairSums_Count.Solution sln) {
        assertThat(sln.numberOfWays(new int[]{ 1, 5, 3, 3, 3 }, 6)).isEqualTo(4);
    }
}
