package org.krmdemo.techlabs.leet_code_0000_1000;

import com.google.common.collect.EvictingQueue;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.krmdemo.techlabs.utils.ArrayUtils.zeroIntArr;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_080__Remove_Duplicates_Sorted_Arr_II}
 */
public class TestCase_080__Remove_Duplicates_Sorted_Arr_II {

    @ParameterizedTest
    @EnumSource(ThirdPartyLibs.class)
    @EnumSource(Problem_080__Remove_Duplicates_Sorted_Arr_II.Solution.class)
    void test_ex_01(Problem_080__Remove_Duplicates_Sorted_Arr_II sln) {
        int[] nums = new int[] { 1,1,1,2,2,3 };
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(5);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 1, 1, 2, 2, 3 });
    }

    @ParameterizedTest
    @EnumSource(ThirdPartyLibs.class)
    @EnumSource(Problem_080__Remove_Duplicates_Sorted_Arr_II.Solution.class)
    void test_ex_02(Problem_080__Remove_Duplicates_Sorted_Arr_II sln) {
        int[] nums = new int[] { 0,0,1,1,1,1,2,3,3 };
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(7);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 0, 0, 1, 1, 2, 3, 3 });
    }

    @ParameterizedTest
    @EnumSource(ThirdPartyLibs.class)
    @EnumSource(Problem_080__Remove_Duplicates_Sorted_Arr_II.Solution.class)
    void test_the_same_value(Problem_080__Remove_Duplicates_Sorted_Arr_II sln) {
        int[] nums = zeroIntArr(10);
        int retValue = sln.removeDuplicates(nums);
        assertThat(retValue).isEqualTo(2);
        int[] valuablePrefix = Arrays.copyOfRange(nums, 0, retValue);
        assertThat(valuablePrefix).isEqualTo(new int[] { 0, 0 });
    }

    @ParameterizedTest
    @EnumSource(ThirdPartyLibs.class)
    @EnumSource(Problem_080__Remove_Duplicates_Sorted_Arr_II.Solution.class)
    void test_short_cases(Problem_080__Remove_Duplicates_Sorted_Arr_II sln) {
        {
            int[] nums = new int[] { 123, 123 };
            assertThat(sln.removeDuplicates(nums)).isEqualTo(2);
            assertThat(nums).isEqualTo(new int[] { 123, 123 });
        } {
            int[] nums = new int[] { 123 };
            assertThat(sln.removeDuplicates(nums)).isEqualTo(1);
            assertThat(nums).isEqualTo(new int[] { 123 });
        } {
            int[] nums = new int[]{};
            assertThat(sln.removeDuplicates(nums)).isEqualTo(0);
            assertThat(nums).isEqualTo(new int[]{});
        } {
            assertThrows(NullPointerException.class, () -> sln.removeDuplicates(null));
        }
    }

    /**
     * This enumeration demonstrates usage of other implementations of {@link Queue}
     * interface, which could be used as a circular buffer.
     */
    enum ThirdPartyLibs implements Problem_080__Remove_Duplicates_Sorted_Arr_II {
        /**
         * This implementation of solution is based on {@link CircularFifoQueue} from
         * <a href="https://commons.apache.org/proper/commons-collections/index.html">
         *     "Apache Commons Collections"
         * </a>
         */
        COMMONS_CIRCULAR_QUEUE {
            @Override
            public Queue<Integer> createQueue() {
                return new CircularFifoQueue<>(MAX_DUPLICATES + 1);
            }
        },
        /**
         * This implementation of solution is based on {@link EvictingQueue} from
         * <a href="https://github.com/google/guava">
         *     "Google Guava"
         * </a>
         */
        GUAVA_EVICTING_QUEUE {
            @Override
            public Queue<Integer> createQueue() {
                return EvictingQueue.create(MAX_DUPLICATES + 1);
            }
        };
        @Override
        public int removeDuplicates(int[] nums) {
            return Solution.CIRCLE_BUFFER.removeDuplicates(nums);
        }
    }

    @Test
    void testCircularQueue() {
        Queue<Integer> circularQueue = new CircularFifoQueue<>(4);
        IntStream.rangeClosed(1, 17).forEach(value -> {
            circularQueue.add(value);
            System.out.println("CircularFifoQueue = " + circularQueue);
        });
        assertThat(circularQueue).contains(14, 15, 16, 17);
    }

    @Test
    void testEvictedQueue() {
        Queue<Integer> circularQueue = EvictingQueue.create(4);
        IntStream.rangeClosed(1, 17).forEach(value -> {
            circularQueue.add(value);
            System.out.println("EvictingQueue = " + circularQueue);
        });
        assertThat(circularQueue).contains(14, 15, 16, 17);
    }
}
