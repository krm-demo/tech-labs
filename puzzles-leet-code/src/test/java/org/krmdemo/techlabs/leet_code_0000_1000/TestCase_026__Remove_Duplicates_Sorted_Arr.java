package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.ArrayUtils.zeroIntArr;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_026__Remove_Duplicates_Sorted_Arr}
 */
public class TestCase_026__Remove_Duplicates_Sorted_Arr {

    Problem_026__Remove_Duplicates_Sorted_Arr sln =
        Problem_026__Remove_Duplicates_Sorted_Arr.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        int[] nums = new int[] { 1,1,2 };
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(2);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 1, 2 });
    }

    @Test
    void test_ex_02() {
        int[] nums = new int[] { 0,0,1,1,1,2,2,3,3,4 };
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(5);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 0, 1, 2, 3, 4 });
    }

    @Test
    void test_the_same_value() {
        int[] nums = zeroIntArr(10);
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(1);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 0 });
    }
}
