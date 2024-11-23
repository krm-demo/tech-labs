package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * <h3><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150">
 *     80. Remove Duplicates from Sorted Array II
 * </a></h3>
 * Given an integer array <b><code>nums</code></b> sorted in <b>non-decreasing order</b>,
 * remove the duplicates in-place such that each unique element appears <b>at most twice</b>.
 * The <b>relative order</b> of the elements should be kept the same.
 * Then return the number of unique elements in <b><code>nums</code></b>.
 *
 * @see Problem_026__Remove_Duplicates_Sorted_Arr
 */
public interface Problem_080__Remove_Duplicates_Sorted_Arr_II {

    /**
     * Solution entry-point.
     *
     * @param nums a sorted array of integer with duplicates
     * @return the same array with removed all duplicates after the second one and squashed to the start of array
     */
    int removeDuplicates(int[] nums);

    int MAX_DUPLICATES = 2;

    default Queue<Integer> createQueue() {
        return new ArrayDeque<>();
    }

    default <T> boolean shouldSkip(Collection<T> buf, T value) {
        return buf.size() >= MAX_DUPLICATES && allEqualTo(buf, value);
    }
    default boolean bufferIsFull(Collection<?> buf) {
        return buf.size() >= MAX_DUPLICATES + 1;
    }
    default <T> boolean allEqualTo(Collection<T> buf, T matchValue) {
        return buf.stream().allMatch(v -> v.equals(matchValue));
    }

    enum Solution implements Problem_080__Remove_Duplicates_Sorted_Arr_II {

        /**
         * This approach uses circular-buffer, whose default implementation is {@link ArrayDeque}
         */
        CIRCLE_BUFFER {
            @Override
            public int removeDuplicates(int[] nums) {
                int count = 0;
                Queue<Integer> circularBuffer = createQueue();
                for (int i = 0; i < nums.length; i++) {
                    int value = nums[i];
                    System.out.printf("%d) value = %d; buf: %s ", i, value, circularBuffer);
                    if (shouldSkip(circularBuffer, value)) {
                        System.out.println("skip");
                        continue;
                    }
                    circularBuffer.offer(value);
                    System.out.printf(" --> %s", circularBuffer);
                    if (bufferIsFull(circularBuffer)) {
                        nums[count++] = circularBuffer.remove();
                        System.out.println(" polled, count is " + count);
                    } else {
                        System.out.println(" ...");
                    }
                }
                while (!circularBuffer.isEmpty()) {
                    nums[count++] = circularBuffer.remove();
                    System.out.println("drained, count is " + count);
                }
                return count;
            }
        },

        /**
         * This approach uses counting-map (based on {@link LinkedHashMap}.
         * The fact that input array is sorted is not used by this solution.
         * In addition to that - extra <code>O(N)</code> space is used, which is one more disadvantage.
         */
        COUNTING_MAP {
            @Override
            public int removeDuplicates(int[] nums) {
                Map<Integer, Integer> cntMap = Arrays.stream(nums)
                    .boxed().collect(groupingBy(
                        identity(), LinkedHashMap::new, summingInt(v -> 1)
                    ));
                int[] count = new int[] { 0 };
                cntMap.entrySet().stream()
                    .flatMapToInt(e -> {
                        int size = Math.min(e.getValue(), 2);
                        return IntStream.generate(e::getKey).limit(size);
                    }).forEach(value -> nums[count[0]++] = value);
                return count[0];
            }
        },

        /**
         * This approach is not such flexibale and universal as others,
         * but it's the most efficient and repeat the same idea,
         * that is ideal for more simple puzzle {@link Problem_026__Remove_Duplicates_Sorted_Arr}
         */
        DEFAULT {
            @Override
            public int removeDuplicates(int[] nums) {
                int count = 0;
                Integer previousValue = null;
                for (int i = 0; i < nums.length; i++) {
                    if (shouldSkip(nums, i)) {
                        continue;
                    }
                    if (previousValue != null) {
                        nums[count - 1] = previousValue;
                    }
                    previousValue = nums[i];
                    count++;
                }
                if (previousValue != null) {
                    nums[count - 1] = previousValue;
                }
                return count;
            }

            private boolean shouldSkip(int[] nums, int lastIndex) {
                if (lastIndex < 2) {
                    return false;
                }
                return nums[lastIndex] == nums[lastIndex - 1]
                    && nums[lastIndex] == nums[lastIndex - 2];
            }
        },

        /**
         * This approach is proposed by leet-code user <a href="https://leetcode.com/u/niits/"?>niits</a>
         * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/solutions/5792415/video-using-two-pointers-coding-exercise/?envType=study-plan-v2&envId=top-interview-150">
         *     at "15 Sep 2024"
         * </a>
         * <hr/>
         * <small>(no idea whether it's one real person or some community of developers)</small>
         */
        TWO_POINTERS {
            @Override
            public int removeDuplicates(int[] nums) {
                if (nums.length < 2) {
                    return nums.length;
                }
                int k = 2;
                for (int i = 2; i < nums.length; i++) {
                    if (nums[i] != nums[k - 2]) {
                        nums[k] = nums[i];
                        k++;
                    }
                }
                return k;
            }
        };
    }
}
