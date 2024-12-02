package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_152__Max_Product_SubArr}
 *
 * @see org.krmdemo.techlabs.sandbox.WithPrevTest
 */
public class TestCase_152__Max_Product_SubArr {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_152__Max_Product_SubArr.Solution sln) {
        assertThat(sln.maxProduct(new int[] { 2,3,-2,4 })).isEqualTo(6);
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_152__Max_Product_SubArr.Solution sln) {
        assertThat(sln.maxProduct(new int[] { -2,0,-1 })).isEqualTo(0);
    }
}
