package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_135__Candy}
 */
public class TestCase_135__Candy {

    Problem_135__Candy sln = Problem_135__Candy.Solution.TWO_ARR;
    Problem_135__Candy slnM = Problem_135__Candy.Solution.MAPPERS;

    @Test
    void test_ex_01() {
        assertThat(sln.candy(new int[] { 1, 0, 2 })).isEqualTo(5);
    }

    @Test
    void test_ex_02() {
        assertThat(sln.candy(new int[] { 1, 2, 2 })).isEqualTo(4);
    }

    @Test
    void test_monoUp() {
        assertThat(sln.candy(new int[] { 1, 2 })).isEqualTo(3);         // 2 + sumArPro(0..1) = 3
        assertThat(sln.candy(new int[] { 1, 2, 3 })).isEqualTo(6);      // 3 + sumArPro(0..2) = 3 + (0+2)*3/2 = 6
        assertThat(sln.candy(new int[] { 1, 2, 3, 4 })).isEqualTo(10);  // 4 + sumArPro(0..3) = 4 + (0+3)*4/2 = 10

        // 5 + sumArPro(0..4) = 5 + (0+4)*5/2 = 15
        assertThat(sln.candy(new int[] { 1, 2, 3, 4, 5 })).isEqualTo(15);
        slnM.candy(new int[] { 1, 2, 3, 4, 5 });
    }

    @Test
    void test_monoDown() {
        assertThat(sln.candy(new int[] { 4, 3 })).isEqualTo(3);
        assertThat(sln.candy(new int[] { 4, 3, 2 })).isEqualTo(6);
        assertThat(sln.candy(new int[] { 4, 3, 2, 1 })).isEqualTo(10);

        // 5 + sumArPro(0..4) = 5 + (0+4)*5/2 = 15
        assertThat(sln.candy(new int[] { 4, 3, 2, 1, 0 })).isEqualTo(15);
        slnM.candy(new int[] { 4, 3, 2, 1, 0 });
    }

    @Test
    void test_Editorial() {
        int[] ratings = new int[] {
            1,2,3,4,5,3,2,1,2,6,5,4,3,3,2,1,1,3,3,3,4,2
        };
        assertThat(sln.candy(ratings)).isEqualTo(47);
        slnM.candy(ratings);
    }
}
