package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2638__Count_K_Free_SubSets}
 */
public class TestCase_2638__Count_K_Free_SubSets {

    final Problem_2638__Count_K_Free_SubSets sln = Problem_2638__Count_K_Free_SubSets.Solution.BRUTE_FORCE;
    final Problem_2638__Count_K_Free_SubSets slnMath = Problem_2638__Count_K_Free_SubSets.Solution.MATH;

    @Test
    void test_ex_01() {
        int[] nums = new int[] { 5,4,6 };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 1)).isEqualTo(5);
        slnMath.countTheNumOfKFreeSubsets(nums, 1);
    }

    @Test
    void test_ex_02() {
        int[] nums = new int[] { 2,3,5,8 };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 5)).isEqualTo(12);
        slnMath.countTheNumOfKFreeSubsets(nums, 5);
    }

    @Test
    void test_ex_03() {
        int[] nums = new int[] { 10,5,9,11 };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 20)).isEqualTo(16);
        slnMath.countTheNumOfKFreeSubsets(nums, 20);
    }

    @Test
    void test_Continuous_Range_1_from_7() {
        int[] nums = IntStream.rangeClosed(1, 7).toArray();
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 1)).isEqualTo(34);
        slnMath.countTheNumOfKFreeSubsets(nums, 1);
    }

    @Test
    void test_Continuous_Range_2_from_7() {
        int[] nums = IntStream.rangeClosed(1, 7).toArray();
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 2)).isEqualTo(40);
        slnMath.countTheNumOfKFreeSubsets(nums, 2);
    }

    @Test
    void test_tc_3_of_1235() {
        // 7,8,9,14,11,6,10,13,12
        int[] nums = new int[] { 7,8,9,14,11,6,10,13,12 };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 6)).isEqualTo(216);
        slnMath.countTheNumOfKFreeSubsets(nums, 6);
    }

    @Test
    void test_tc2597_1296_of_1308() {
        // 14, 87, 37, 94, 43, 25, 77, 54, 44, 71, 39, 16, 61, 29, 61, 9, 96
        int[] nums = new int[] {
            14, 87, 37, 94, 43, 25, 77, 54, 44, 71, 39, 16, 61, 29, 61, 9, 96
        };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 88)).isEqualTo(131_072);
        // elapsed time of Solution.BRUTE_FORCE: ~ 98 ms (at LeetCode 541 ms)

        slnMath.countTheNumOfKFreeSubsets(nums, 88);
    }

    @Test
    void test_tc2597_1297_of_1308() {
        // 51, 15, 61, 64, 53, 6, 3, 5, 76, 79, 67, 26, 87, 76, 54, 50, 42, 80, 79
        int[] nums = new int[] {
            51, 15, 61, 64, 53, 6, 3, 5, 76, 79, 67, 26, 87, 76, 54, 50, 42, 80, 79
        };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 74)).isEqualTo(245_760);
        // elapsed time of Solution.BRUTE_FORCE: ~ 256 ms (at LeetCode 458ms)

        slnMath.countTheNumOfKFreeSubsets(nums, 74);
    }

    @Test
    void test_tc_610_of_1235__first_half() {
        // 580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
        // 570 //,576,566,567,592,594,565,588,578,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        int[] nums = new int[] {
            580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
            570 //,576,566,567,592,594,565,588,578,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 6)).isEqualTo(2_322_432L);
        // elapsed time of Solution.BRUTE_FORCE: ~ 3.415 sec

        slnMath.countTheNumOfKFreeSubsets(nums, 6);
    }

    @Test
    @Disabled("too slow on Solution.BRUTE_FORCE")
    void test_tc_610_of_1235__two_third() {
        // 580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
        // 570,576,566,567,592,594,565,588,578 //,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        int[] nums = new int[] {
            580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
            570,576,566,567,592,594,565,588,578 //,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 6)).isEqualTo(2_322_432L);
        // elapsed time of Solution.BRUTE_FORCE: ~ 14 min 12 sec

        slnMath.countTheNumOfKFreeSubsets(nums, 6);
    }

    @Test
    @Disabled("extremely slow on Solution.BRUTE_FORCE") // TODO: investigate Assumptions
    void test_tc_610_of_1235() {
        // 580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
        // 570,576,566,567,592,594,565,588,578,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        int[] nums = new int[] {
            580,599,582,562,593,575,604,572,596,597,591,581,585,587,564,606,605,603,608,584,577,595,600,598,
            570,576,566,567,592,594,565,588,578,561,590,602,563,569,574,601,589,583,568,586,607,579,573,560,571
        };
        assertThat(sln.countTheNumOfKFreeSubsets(nums, 6)).isEqualTo(216);
        // elapsed time of Solution.BRUTE_FORCE: ~ ??? very-very long (maybe couple of weeks)

        slnMath.countTheNumOfKFreeSubsets(nums, 6);
    }


    @Test
    void testCase_two_prohibited() {
        {
            int kNotFreeOne = 7;
            int kNotFreeTwo = 8;
            System.out.printf("%n... kNotFree --> %s : ...%n", asList(kNotFreeOne, kNotFreeTwo));
            for (int N = 1; N < 12; N++) {
                int[] nums10s = IntStream.rangeClosed(1, N).map(v -> 10 * v).toArray();
                int[] nums = IntStream.concat(
                    IntStream.of(kNotFreeOne, kNotFreeTwo),
                    Arrays.stream(nums10s)
                ).toArray();
                long kFreeCount = sln.countTheNumOfKFreeSubsets(nums, 1);
                long totalCount = 1 << (N + 2);
                long totalOthers = 1 << N;
                System.out.printf("%2d) totalCount = %,d; totalOthers = %,d; kFreeCount = %,d; ---> %s; %n",
                    N + 2, totalCount, totalOthers, kFreeCount, Arrays.toString(nums));

                slnMath.countTheNumOfKFreeSubsets(nums, 1);
            }
        } {
            int kNotFreeOne = 8;
            int kNotFreeTwo = 9;
            System.out.printf("%n... kNotFree --> %s : ...%n", asList(kNotFreeOne, kNotFreeTwo));
            for (int N = 1; N < 12; N++) {
                int[] nums10s = IntStream.rangeClosed(1, N).map(v -> 10 * v).toArray();
                int[] nums = IntStream.concat(
                    IntStream.of(kNotFreeOne, kNotFreeTwo),
                    Arrays.stream(nums10s)
                ).toArray();
                long kFreeCount = sln.countTheNumOfKFreeSubsets(nums, 1);
                long totalCount = 1 << (N + 2);
                long totalOthers2 = 1 << N;
                long totalOthers3 = 1 << (N - 1);
                System.out.printf("%2d) totalCount = %,d; totalOthers = %,d / %,d; kFreeCount = %,d; ---> %s; %n",
                    N + 2, totalCount, totalOthers2, totalOthers3, kFreeCount, Arrays.toString(nums));

                slnMath.countTheNumOfKFreeSubsets(nums, 1);
            }
        } {
            int kNotFreeOne = 7;
            int kNotFreeTwo = 8;
            int kNotFreeThree = 9;
            System.out.printf("%n... kNotFree --> %s : ...%n", asList(kNotFreeOne, kNotFreeTwo, kNotFreeThree));
            for (int N = 1; N < 12; N++) {
                int[] nums10s = IntStream.rangeClosed(1, N).map(v -> 10 * v).toArray();
                int[] nums = IntStream.concat(
                    IntStream.of(kNotFreeOne, kNotFreeTwo, kNotFreeThree),
                    Arrays.stream(nums10s)
                ).toArray();
                long kFreeCount = sln.countTheNumOfKFreeSubsets(nums, 1);
                long totalCount = 1 << (N + 3);
                long totalOthers2 = 1 << (N + 1);
                long totalOthers3 = 1 << (N);
                System.out.printf("%2d) totalCount = %,d; totalOthers = %,d / %,d; kFreeCount = %,d; ---> %s; %n",
                    N + 3, totalCount, totalOthers2, totalOthers3, kFreeCount, Arrays.toString(nums));

                slnMath.countTheNumOfKFreeSubsets(nums, 1);
            }
        }
    }

    @Test
    void testCase_two_x_two_prohibited() {
        int kNotFreeOne = 3;
        int kNotFreeTwo = 4;
        int kNotFreeThree = 9;
        System.out.printf("%n... kNotFree --> %s : ...%n", asList(kNotFreeOne, kNotFreeTwo, kNotFreeThree));
        for (int N = 1; N < 12; N++) {
            int[] nums10s = IntStream.rangeClosed(1, N).map(v -> 10 * v).toArray();
            int[] nums = IntStream.concat(
                IntStream.of(kNotFreeOne, kNotFreeTwo, kNotFreeThree),
                Arrays.stream(nums10s)
            ).toArray();
            long kFreeCount = sln.countTheNumOfKFreeSubsets(nums, 1);
            long totalCount = 1 << (N + 3);
            long totalOthers2 = 1 << (N + 1);
            long totalOthers3 = 1 << (N);
            System.out.printf("%2d) totalCount = %,d; totalOthers = %,d / %,d; kFreeCount = %,d; ---> %s; %n",
                N + 3, totalCount, totalOthers2, totalOthers3, kFreeCount, Arrays.toString(nums));

            slnMath.countTheNumOfKFreeSubsets(nums, 1);
        }
    }

    @Test
    void testCase_two_x_two_x_two_prohibited() {
        int kNotFreeOne = 3;
        int kNotFreeTwo = 4;
        int kNotFreeThree = 9;
        int kNotFreeFour = 19;
        System.out.printf("%n... kNotFree --> %s : ...%n", asList(kNotFreeOne, kNotFreeTwo, kNotFreeThree, kNotFreeFour));
        for (int N = 2; N < 12; N++) {
            int[] nums10s = IntStream.rangeClosed(1, N).map(v -> 10 * v).toArray();
            int[] nums = IntStream.concat(
                IntStream.of(kNotFreeOne, kNotFreeTwo, kNotFreeThree, kNotFreeFour),
                Arrays.stream(nums10s)
            ).toArray();
            long kFreeCount = sln.countTheNumOfKFreeSubsets(nums, 1);
            long totalCount = 1 << (N + 4);
            long totalOthers2 = 1 << (N + 2);
            long totalOthers3 = 1 << (N + 1);
            System.out.printf("%2d) totalCount = %,d; totalOthers = %,d / %,d; kFreeCount = %,d; ---> %s; %n",
                N + 4, totalCount, totalOthers2, totalOthers3, kFreeCount, Arrays.toString(nums));

            slnMath.countTheNumOfKFreeSubsets(nums, 1);
        }
    }
}
