package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/minimum-swaps/1">
 *     Minimum Swaps to Sort
 * </a></h3>
 * Given an array <b><code>arr[]</code></b> of distinct elements.
 * Find the minimum number of swaps required to sort the array in strictly increasing order.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^6
 * 0 ≤ arr[i] ≤ 10^9
 * </pre>
 *
 * @see GFG_Arrays__Count_Inversions
 */
public interface GFG_Arrays__Count_Min_Swaps_To_Sort {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array containing positive integers
     * @return minimum number of swaps required to sort the array in strictly increasing order
     */
    int minSwaps(int[] arr);

    enum Solution implements GFG_Arrays__Count_Min_Swaps_To_Sort {
        DEFAULT;

        @Override
        public int minSwaps(int[] arr) {
            @SuppressWarnings("SimplifyStreamApiCallChains")
            List<IndexedValue> sortedList = java.util.stream.IntStream.range(0, arr.length)
                .mapToObj(i -> new IndexedValue(i, arr[i]))
                .sorted()
                .collect(java.util.stream.Collectors.toList());
            System.out.println("arr --> " + Arrays.toString(arr));
            System.out.println("sortedList --> " + sortedList);
            int totalSwaps = 0;
            for (int i = 0; i < arr.length; i++) {
                System.out.print(i + ") ");
                IndexedValue iv = sortedList.get(i);
                if (iv == null) {
                    System.out.println("already swapped");
                    continue;
                } else if (iv.index == i) {
                    System.out.println("at proper place");
                    continue;
                }
                int j = i;
                int cycleLen = 0;
                do {
                    cycleLen++;
                    sortedList.set(j, null);
                    j = iv.index;
                    iv = sortedList.get(iv.index);
                } while (iv != null);
                totalSwaps += (cycleLen - 1);
                System.out.printf("cycleLen: %d, totalSwaps: %d, sortedList: %s;%n",
                    cycleLen, totalSwaps, sortedList);
            }
            System.out.println("returning " + totalSwaps);
            return totalSwaps;
        }

        private static class IndexedValue implements Comparable<IndexedValue> {
            final int index;
            final int value;
            private IndexedValue(int index, int value) {
                this.index = index;
                this.value = value;
            }
            @Override
            public int compareTo(IndexedValue that) {
                return this.value - that.value;
            }
            @Override
            public String toString() {
                return String.format("[#%d]%d", index, value);
            }
        }
    }
}
