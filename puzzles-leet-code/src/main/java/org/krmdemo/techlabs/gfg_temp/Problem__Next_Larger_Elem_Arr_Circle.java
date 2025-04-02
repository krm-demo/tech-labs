package org.krmdemo.techlabs.gfg_temp;

import java.util.*;
import java.util.stream.LongStream;

/**
 * TODO: convert to appropriate way (interface + enum + @ParameterizedTest)
 * <a href="https://www.geeksforgeeks.org/problems/next-greater-element/1">
 *     <h3>Next Greater Element in Circular Array</h3>
 * </a>
 * Given an circular array <b><code>arr[]</code></b> of size <b><code>N</code></b> having <b>distinct</b> elements,
 * the task is to find the <i>next greater element</i> for each element of the array .
 * <h4>Note:</h4>
 * The next greater element of an element in the array is the first greater number to its
 * traversing-order next in the array, which means you could search circularly to find its next greater number.
 * If it doesn't exist, return <b><code>-1</code></b> for this number.
 *
 * @see Contest_M5_R1__Next_Larger_Elem_Arr
 * @see my.leetcode.algo_739__daily_temperatures.Solution
 * @see my.leetcode.algo_503__next_greater_elem_ii.Solution
 */
@SuppressWarnings("NewClassNamingConvention")
public class Problem__Next_Larger_Elem_Arr_Circle {

    public static long[] nextLargerElement(long[] arr) {
        return nextLargerElement(arr, arr.length);
    }

    /**
     * Entry point of <a href="https://www.geeksforgeeks.org/">GFG</a>-Puzzle
     *
     * @param arr an array of integers (by requirements they are distinct, but I doubt that it's important)
     * @param N the size of input array <code>arr</code> (usual redundant param for arrays at GFG)
     * @return the array where each
     */
    public static long[] nextLargerElement(long[] arr, int N) {
        System.out.println("nextLargerElement: N = " + N + "; arr --> " + Arrays.toString(arr));
        IndexedLongValueList ivList = new IndexedLongValueList(arr);
        ivList.calculateNextIncrTwice();
        System.out.println("nextLargerValues --> " + ivList.nextLargerValues(-1).boxed().toList());
        return ivList.nextLargerValues(-1).toArray();
    }

    static class IndexedLongValueList {
        final List<IndexedValue> indexedValueList = new ArrayList<>();
        class IndexedValue {
            final int index;
            final long value;
            IndexedValue nextIncr;
            IndexedValue(long value) {
                this.index = indexedValueList.size();
                this.value = value;
                indexedValueList.add(this);
            }
        }
        IndexedLongValueList(long[] valuesArr) {
            this(Arrays.stream(valuesArr));
        }
        IndexedLongValueList(LongStream values) {
            values.forEach(IndexedValue::new);
        }
        public void calculateNextIncrOnce() {
            calculateNextIncr(new ArrayDeque<>()); // <-- monotonically descending stack
        }
        public void calculateNextIncrTwice() {
            calculateNextIncr(calculateNextIncr(new ArrayDeque<>())); // <-- monotonically descending stack
        }
        public Deque<IndexedValue> calculateNextIncr(Deque<IndexedValue> monoDescStack) {
            for (IndexedValue current : indexedValueList) {
                if (monoDescStack.isEmpty()) {
                    monoDescStack.push(current);
                    continue;
                }
                while (!monoDescStack.isEmpty()
                    && monoDescStack.peek().value < current.value) {
                    monoDescStack.pop().nextIncr = current;
                }
                monoDescStack.push(current);
            }
            return monoDescStack;
        }
        public LongStream nextLargerValues(long defaultValue) {
            return indexedValueList.stream()
                .mapToLong(iv -> iv.nextIncr == null ? defaultValue : iv.nextIncr.value);
        }
    }
/*
    // ------------------------------------------------------------------------------------------
    //                            Test-Cases' data and methods:
    // ------------------------------------------------------------------------------------------

    @Test
    public void test_req_01() {
        long[] arr = new long[] { 1, 3, 2, 4 };
        assertThat(nextLargerElement(arr), equalTo(new long[] { 3, 4, 4, -1 }));
    }

    @Test
    public void test_req_02() {
        long[] arr = new long[] { 6, 8, 0, 1, 3 };
        assertThat(nextLargerElement(arr), equalTo(new long[] { 8, -1, 1, 3, 6 }));
    }
*/
}
