package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/">
 *     315. Count of Smaller Numbers After Self
 * </a></h3>
 * Given an integer array <code>nums</code>, return an integer array <code>counts</code>
 * where <code>counts[<b>i</b>]</code> is the number of smaller elements
 * to the right of <code>nums[<b>i</b>]</code>.
 * <h5>Constraints:</h5><pre>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public interface Problem_315__Count_Smaller_Numbers {

    /**
     * Solution entry-point.
     *
     * @param nums an integer array
     * @return the list of the number of smaller elements after given index
     */
    List<Integer> countSmaller(int[] nums);

    enum Solution implements Problem_315__Count_Smaller_Numbers {
        /**
         * This simplest approach results in "Time Limit Exceeded"
         */
        TREE_SET {
            @Override
            public List<Integer> countSmaller(int[] nums) {
                Integer[] smallerCountArr = new Integer[nums.length];
                NavigableSet<ValueIndex> navSet = new TreeSet<>();
                for (int i = nums.length - 1; i >= 0; i--) {
                    ValueIndex vi = new ValueIndex(nums[i], i);
                    smallerCountArr[i] = navSet.headSet(vi).size();
                    navSet.add(new ValueIndex(nums[i], i));
                }
                return Arrays.asList(smallerCountArr);
            }
            record ValueIndex(int value, int index) implements Comparable<ValueIndex> {
                @Override
                public int compareTo(ValueIndex other) {
                    if (this.value != other.value) {
                        return this.value - other.value;
                    } else {
                        return this.index - other.index;
                    }
                }
            }
        },
        /**
         * Here the array-based segment-tree is used
         */
        SEGMENT_TREE_ARR {
            @Override
            public List<Integer> countSmaller(int[] nums) {
                System.out.printf("%s.countSmaller ( %s )%n", name(), Arrays.toString(nums));
                Integer[] smallerCountArr = new Integer[nums.length];
                SegmentTreeArray st = new SegmentTreeArray(nums);
                if (st.hasCapacity()) {
                    for (int i = nums.length - 1; i >= 0; i--) {
                        int value = nums[i];
                        smallerCountArr[i] = st.countLess(value);
                        st.incrementCount(value);
                    }
                    System.out.println(st);
                } else {
                    System.out.println("segment tree capacity is zero");
                    Arrays.fill(smallerCountArr, 0);
                }
                return Arrays.asList(smallerCountArr);
            }

            private static class SegmentTreeArray {

                private final int offset;
                private final int capacity;
                private final int[] segmentArr;

                SegmentTreeArray(int[] nums) {
                    int minValue = nums[0];
                    int maxValue = nums[0];
                    for (int i = 1; i < nums.length; i++) {
                        minValue = Math.min(minValue, nums[i]);
                        maxValue = Math.max(maxValue, nums[i]);
                    }
                    this(minValue, maxValue);  // <-- this will not be compiled on JDK prior to 22
                }

                SegmentTreeArray(int minValue, int maxValue) {
                    int capacity = maxValue - minValue;
                    capacity = Integer.highestOneBit(capacity << 1);
                    int segemntSize = capacity << 1;
                    this.segmentArr = new int[segemntSize];
                    this.offset = minValue;
                    this.capacity = capacity;
                }

                public boolean hasCapacity() {
                    return this.capacity > 0;
                }

                public void incrementCount(int value) {
                    updateCount(value, 1);
                }

                public void updateCount(int value, int count) {
                    int index = checkIndex(value);
                    while (index > 0) {
                        segmentArr[index] += count;
                        index = index >> 1;
                    }
                }

                public int count(int value) {
                    int index = checkIndex(value);
                    return segmentArr[index];
                }

                public int countLess(int value) {
                    int totalCountLess = 0;
                    int index = checkIndex(value);
                    while (index > 0) {
                        int parent = index >> 1;
                        if ((index & 1) == 1) {
                            int countLess = segmentArr[parent << 1];
                            totalCountLess += countLess;
                        }
                        index = parent;
                    }
                    return totalCountLess;
                }

                private int checkIndex(int value) {
                    if (value < this.offset) {
                        throw new IllegalArgumentException(String.format(
                            "value must NOT be less than %d, but it equals to %d",
                            offset, value));
                    }
                    int index = value - this.offset;
                    if (index >= this.capacity) {
                        throw new IllegalArgumentException(String.format(
                            "value must be less than %d, but it equals to %d",
                            value, this.capacity - this.offset));
                    }
                    return index + this.capacity;
                }

                @Override
                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%s ( offset = %d, capacity = %d ):%n",
                        getClass().getSimpleName(), this.offset, this.capacity));
                    int maxLevel = Integer.numberOfTrailingZeros(this.capacity) + 1;
                    for (int level = 1; level <= maxLevel; level++) {
                        int start = 1 << (level - 1);
                        int end = start << 1;
                        List<Integer> levelList = Arrays.stream(segmentArr, start, end).boxed().toList();
                        sb.append(String.format("%2d :: %s %n", level, levelList));
                    }
                    return sb.toString();
                }
            }
        };
    }
}
