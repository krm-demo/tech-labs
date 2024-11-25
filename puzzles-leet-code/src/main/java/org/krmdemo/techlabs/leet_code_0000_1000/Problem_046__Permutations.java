package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/permutations/description/?envType=study-plan-v2&envId=top-interview-150">
 *     46. Permutations
 * </a></h3>
 * Given an array <b><code>nums</code></b> of distinct integers,
 * return all the possible permutations.
 * <hr/>
 * You can return the answer in any order.
 * <h5>Constraints:</h5><pre>
 *  1 <= nums.length <= 6
 *  -10 <= nums[i] <= 10</pre>
 * All the integers of nums are unique.
 *
 * @see <a href="https://docs.python.org/3/library/itertools.html#itertools.permutations">
 *      <code>itertools.permutations</code>
 *     </a>
 * @see Problem_077__Combinations
 */
public interface Problem_046__Permutations {

    /**
     * Solution entry-point.
     *
     * @param nums an array of distinct integers
     * @return the largest sum of sub-array
     */
    List<List<Integer>> permute(int[] nums);

    enum Solution implements Problem_046__Permutations {
        ITER_NEXT_PERM {
            public List<List<Integer>> permute(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                // TODO: improve the following loop maybe with spliterators and streams:
                IndexesIterator iterIndexes = new IndexesIterator(nums.length);
                do {
                    resultList.add(iterIndexes.valuesList(nums));
                } while (iterIndexes.next() != null);
                return resultList;
            }

            static class IndexesIterator implements Iterator<int[]> {
                final int size;
                final int[] indexes;
                IndexesIterator(int size) {
                    this.size = size;
                    this.indexes = ascending(size).toArray();
                }

                public IntStream current() {
                    return Arrays.stream(indexes);
                }
                public IntStream values(int[] valuesArr) {
                    return current().map(i -> valuesArr[i]);
                }
                public List<Integer> valuesList(int[] valuesArr) {
                    return values(valuesArr).boxed().toList();
                }

                @Override
                public boolean hasNext() {
                    return compareStreams(current(), descending(size)) < 0;
                }

                @Override
                public int[] next() {
                    if (!hasNext()) {
                        return null;
                    }
                    int indexOne = lastAscendingPair(); // <-- it looks like this result depend on previous one
                    if (indexOne >= 0) {
                        int indexTwo = indexOfCeil(indexOne + 1, indexes[indexOne]);
                        swap(indexOne, indexTwo);
                    }
                    reverseTail(indexOne + 1);
                    return indexes;
                }
                private void swap(int indexOne, int indexTwo) {
                    int valueOne = indexes[indexOne];
                    indexes[indexOne] = indexes[indexTwo];
                    indexes[indexTwo] = valueOne;
                }
                private void reverseTail(int left) {
                    int right = indexes.length - 1;
                    while (left < right) swap(left++, right--);
                }
                private int lastAscendingPair() {
                    int ascPairStart = indexes.length - 2;
                    while (ascPairStart >= 0 && indexes[ascPairStart] >= indexes[ascPairStart+1]) {
                        ascPairStart--;
                    }
                    return ascPairStart;  // <-- "-1" means that array is sorted in descending order
                }
                private int indexOfCeil(int begin, int valueToCeil) {
                    for (int i = indexes.length - 1; i > begin; i--) {
                        if (indexes[i] > valueToCeil) {
                            return i;
                        }
                    }
                    return begin;
                }

                static IntStream ascending(int size) {
                    return IntStream.range(0, size);
                }

                static IntStream descending(int size) {
                    return ascending(size).map(i -> size - i - 1);
                }
            }
        };

        static int compareStreams(IntStream intsOne, IntStream intsTwo) {
            PrimitiveIterator.OfInt iterOne = intsOne.iterator();
            PrimitiveIterator.OfInt iterTwo = intsTwo.iterator();
            while (iterOne.hasNext() && iterTwo.hasNext()) {
                int diff = iterOne.nextInt() - iterTwo.nextInt();
                if (diff != 0) {
                    return diff;
                }
            }
            if (iterOne.hasNext() || iterTwo.hasNext()) {
                throw new IllegalArgumentException(
                    "integer streams are of different length");
            }
            return 0;  // <-- this means that streams are identical
        }
    }
}
