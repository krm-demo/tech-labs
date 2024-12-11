package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Count_Smaller_Elements}
 */
public class TestCase_GFG_Arrays__Count_Smaller_Elements {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(GFG_Arrays__Count_Smaller_Elements.Solution sln) {
        assertThat(sln.constructLowerArray(new int[] {
            12, 1, 2, 3, 0, 11, 4
        })).containsExactly(
            6, 1, 1, 1, 0, 1, 0
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(GFG_Arrays__Count_Smaller_Elements.Solution sln) {
        assertThat(sln.constructLowerArray(new int[] {
            1, 2, 3, 4, 5
        })).containsExactly(
            0, 0, 0, 0, 0
        );
    }
}
