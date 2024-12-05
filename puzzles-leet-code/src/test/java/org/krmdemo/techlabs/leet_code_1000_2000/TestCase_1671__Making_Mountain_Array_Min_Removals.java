package org.krmdemo.techlabs.leet_code_1000_2000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_1671__Making_Mountain_Array_Min_Removals}
 * <hr/>
 * TODO: play with JUnit-5 {@link org.junit.jupiter.params.converter.ArgumentConverter ArgumentConverter}
 */
public class TestCase_1671__Making_Mountain_Array_Min_Removals {

    Problem_1671__Making_Mountain_Array_Min_Removals sln =
        Problem_1671__Making_Mountain_Array_Min_Removals.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.minimumMountainRemovals(new int[] { 1,3,1 })).isEqualTo(0);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.minimumMountainRemovals(new int[] { 2,1,1,5,6,2,3,1 })).isEqualTo(3);
    }

    /**
     * This test-case partially repeats
     * {@link org.krmdemo.techlabs.leet_code_0000_1000.TestCase_300__Longest_Increasing_SubSeq}
     */
    @Test
    void test_forward_LIS() {
        assertThat(sln.lengthArrLIS(new int[] {
            10, 9, 2, 5, 3, 7, 101, 18
        })).containsExactly(
            0, 1, 1, 2, 2, 3, 4, 4
        );
        assertThat(sln.lengthArrLIS(new int[] {
            0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
        })).containsExactly(
            0, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6
        );
    }

    @Test
    void test_backward_LDS() {
        assertThat(sln.lengthArrLDS(new int[] {
            10, 9, 2, 5, 3, 7, 101, 18
        })).containsExactly(
            4, 3, 2, 2, 2, 2, 2, 0
        );
        assertThat(sln.lengthArrLDS(new int[] {
            0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
        })).containsExactly(
            5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 1, 0
        );
    }

    /**
     * Just an example taken from
     * <a href="https://mikemybytes.com/2021/10/19/parameterize-like-a-pro-with-junit-5-csvsource/">
     *      Parameterize like a pro with JUnit 5 @CsvSource
     * </a>
     * @param description some description of concrete CSV-case
     * @param a the first arg to add
     * @param b the second arg to add
     * @param expectedSum expected result of sum
     */
    @ParameterizedTest(name = "{index} => calculates the sum of {0}: ({1}, {2})")
    @CsvSource(delimiter = '|', textBlock = """
        positive numbers      |   10  |      6  |   16
        positive and negative |   -4  |      2  |   -2
        negative numbers      |   -6  |   -100  | -106
    """)
    void calculatesSum(String description, int a, int b, int expectedSum) {
        int actual = a + b;
        assertThat(expectedSum)
            .describedAs("'" + description + "' CSV-case is failed")
            .isEqualTo(actual);
    }
}
