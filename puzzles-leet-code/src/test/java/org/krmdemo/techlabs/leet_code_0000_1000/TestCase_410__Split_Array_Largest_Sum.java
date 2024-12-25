package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_410__Split_Array_Largest_Sum}
 */
public class TestCase_410__Split_Array_Largest_Sum {

    final Problem_410__Split_Array_Largest_Sum sln =
        Problem_410__Split_Array_Largest_Sum.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.splitArray(new int[] {7,2,5,10,8}, 2)).isEqualTo(18);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.splitArray(new int[] {1,2,3,4,5}, 2)).isEqualTo(9);
    }

    @Test
    public void testTheSameValues() {
        assertThat(sln.splitArray(new int[] {1,1,1,1,1}, 2)).isEqualTo(3);
    }

    @Test
    public void testNegativeValues() {
        assertThat(sln.splitArray(new int[] {1,-5,1,1,5}, 2)).isEqualTo(0);  // <-- the right answer is "2"
    }

    @Test
    public void testAllZeros() {
        assertThat(sln.splitArray(new int[] {0,0,0,0,0}, 3)).isEqualTo(0);  // <-- the right answer is "2"
    }

    @Test
    public void testImpossibleSplit() {
        assertThat(sln.splitArray(new int[] {0,0,0,0,0}, 10)).isEqualTo(0);  // <-- it's not possible
        assertThat(sln.splitArray(new int[] {1,1,1}, 10)).isEqualTo(1);      // <-- it's also not valid
    }
}
