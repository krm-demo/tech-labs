package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.RandomHelper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_053__Max_SubArray_Sum}
 *
 * @see org.krmdemo.techlabs.sandbox.WithPrevTest
 */
public class TestCase_122__Buy_And_Sell_Stock_II {

    private final RandomHelper rnd = new RandomHelper(0);

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_122__Buy_And_Sell_Stock_II.Solution sln) {
        assertThat(sln.maxProfit(new int[] { 7,1,5,3,6,4 })).isEqualTo(7);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_122__Buy_And_Sell_Stock_II.Solution sln) {
        assertThat(sln.maxProfit(new int[] { 1,2,3,4,5 })).isEqualTo(4);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_122__Buy_And_Sell_Stock_II.Solution sln) {
        assertThat(sln.maxProfit(new int[] { 7,6,4,3,1 })).isEqualTo(0);
    }

    @EnumSource
    @ParameterizedTest
    void test_rnd25(Problem_122__Buy_And_Sell_Stock_II.Solution sln) {
        int[] prices25 = rnd.randomRangeArr(1, 25);
        assertThat(sln.maxProfit(prices25)).isEqualTo(100);
    }
}
