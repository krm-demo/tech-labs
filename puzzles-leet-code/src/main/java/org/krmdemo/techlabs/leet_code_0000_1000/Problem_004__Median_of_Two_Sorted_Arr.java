package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.concat;

/**
 * <h3><a href="https://leetcode.com/problems/median-of-two-sorted-arrays/description/">
 *     4. Median of Two Sorted Arrays
 * </a></h3>
 * Given two sorted arrays <code>numsA</code> and <code>numsB</code>
 * (of size <code>lenA</code> and <code>lenB</code> respectively),
 * return <b>the median</b> of the two sorted arrays.
 * <hr/>
 * A median is treated either as a value of element <code>merged(numsA,numsB) [ (lenA + lenB) / 2 ]</code>,
 * if the total length <code>(lenA + lenB)</code> is odd, and if total length is even the average of
 * two values <code>merged(numsA,numsB) [ (lenA + lenB) / 2 ]</code> and
 * <code>merged(numsA,numsB) [ ((lenA + lenB) / 2) - 1 ]</code> is considered as median.
 * <hr/>
 * The overall run time complexity should be <code>O(log(lenA+lenB))</code>.
 */
public interface Problem_004__Median_of_Two_Sorted_Arr {

    /**
     * Solution entry-point.
     *
     * @param numsA the first sorted array of integers (assume that of size <code>lenA</code>)
     * @param numsB the second sorted array of integers (asume that of size <code>lenB</code>)
     * @return the median of sorted arrays ( kind of <code>merged(numsA,numsB) [ (lenA + lenB) / 2 ]</code> )
     */
    double findMedianSortedArrays(int[] numsA, int[] numsB);

    enum Solution implements Problem_004__Median_of_Two_Sorted_Arr {
        /**
         * Brute-force approach does not use the fact that arrays were sorted
         * and merges and sorts two arrays that results in<pre>
         *     time-complexity: O( (lenA+lenB) * log(lenA+lenB) )
         *     additional-space: O( (lenA+lenB) )
         * </pre>
         */
        BRUTE_FORCE {
            @Override
            public double findMedianSortedArrays(int[] numsA, int[] numsB) {
                if (numsA == null) numsA = new int[]{};
                if (numsB == null) numsB = new int[]{};
                System.out.printf("%s.findMedianSortedArrays(numsA,numsB):%n", name());
                System.out.printf("numsA(%d) --> %s;%n", numsA.length, Arrays.toString(numsA));
                System.out.printf("numsB(%d) --> %s;%n", numsB.length, Arrays.toString(numsB));
                int[] mergedSorted = concat(stream(numsA), stream(numsB)).sorted().toArray();
                if (mergedSorted.length == 0) {
                    throw new IndexOutOfBoundsException("empty array does not have a median");
                }
                System.out.printf("mergedSorted(%d) --> %s;%n", mergedSorted.length, Arrays.toString(mergedSorted));
                int mid = mergedSorted.length / 2;
                double retValue = mergedSorted.length % 2 == 0 ?
                    ( mergedSorted[mid - 1] + mergedSorted[mid] ) / 2.0 :
                    mergedSorted[mid];
                System.out.printf("%s returning %.2f;%n%n", name(), retValue);
                return retValue;
            }
        },
        FIND_Kth_RECURSIVE {
            @Override
            public double findMedianSortedArrays(int[] numsA, int[] numsB) {
                System.out.printf("%s.findMedianSortedArrays:%n", name());
                Slice sliceA = new Slice(numsA);
                Slice sliceB = new Slice(numsB);
                System.out.println("sliceA --> " + sliceA);
                System.out.println("sliceB --> " + sliceB);
                int totalSize = sliceA.size() + sliceB.size();
                if (totalSize == 0) {
                    throw new IndexOutOfBoundsException("empty array does not have a median");
                }
                int totalMid = totalSize / 2;
                double retValue = findElemK(totalMid, sliceA, sliceB);
                if (totalSize % 2 == 0) {
                    retValue = (retValue + findElemK(totalMid - 1, sliceA, sliceB)) / 2.0;
                }
                System.out.printf("%s returning %.2f;%n%n", name(), retValue);
                return retValue;
            }

            int findElemK(int K, Slice sliceA, Slice sliceB) {
                if (sliceA.isEmpty()) {
                    return sliceB.elem(K);
                }
                if (sliceB.isEmpty()) {
                    return sliceA.elem(K);
                }
                int midA = sliceA.size() / 2;
                int midB = sliceB.size() / 2;
                if (K > midA + midB) {
                    if (sliceA.elem(midA) <= sliceB.elem(midB)) {
                        K -= (midA + 1);
                        sliceA = sliceA.suffixExcl(midA);
                    } else {
                        K -= (midB + 1);
                        sliceB = sliceB.suffixExcl(midB);
                    }
                } else {
                    if (sliceA.elem(midA) > sliceB.elem(midB)) {
                        sliceA = sliceA.prefixExcl(midA);
                    } else {
                        sliceB = sliceB.prefixExcl(midB);
                    }
                }
                return findElemK(K, sliceA, sliceB);
            }
        };

        private static class Slice {
            final int[] arr;
            final int begin;
            final int end;
            public Slice(int[] arr) {
                this(arr, 0, arr == null ? 0 : arr.length);
            }
            public Slice(int[] arr, int begin, int end) {
                this.arr = arr;
                this.begin = begin;
                this.end = end;
            }
            int size() {
                return arr != null && end > begin ? end - begin : 0;
            }
            boolean isEmpty() {
                return size() == 0;
            }
            int elem(int index) {
                if (index < -size() || index >= size()) {
                    throw new IndexOutOfBoundsException(String.format(
                        "index %d is not in range [ -%2$d, %2$d )", index, size()));
                }
                return index < 0 ? arr[end - index] : arr[begin + index];
            }
            int elem(int index, int newValue) {
                int oldValue = elem(index);
                if (index < 0) {
                    arr[end - index] = newValue;
                } else {
                    arr[begin + index] = newValue;
                }
                return oldValue;
            }
            Slice prefixIncl(int index) {
                return new Slice(this.arr, this.begin, this.begin + index + 1);
            }
            Slice prefixExcl(int index) {
                return new Slice(this.arr, this.begin, this.begin + index);
            }
            Slice suffixIncl(int index) {
                return new Slice(this.arr, this.begin + index, this.end);
            }
            Slice suffixExcl(int index) {
                return new Slice(this.arr, this.begin + index + 1, this.end);
            }
            @Override
            public String toString() {
                return isEmpty() ? "{ EMPTY }" : IntStream.range(begin, end)
                    .mapToObj(i -> "" + arr[i])
                    .collect(Collectors.joining(",",
                        String.format("{ #%d :: ", begin),
                        String.format(" :: #%s }", end)
                    ));
            }
        }
    }
}
