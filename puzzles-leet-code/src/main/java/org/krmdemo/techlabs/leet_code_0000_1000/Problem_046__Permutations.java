package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        /**
         * Recursive (back-tracking) approach that is based on two queues
         */
        BACKTRACKING {
            public List<List<Integer>> permute(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                new BackTracking(nums, permutation -> {
                    resultList.add(permutation.toList());
                }).run();
                return resultList;
            }
            static class BackTracking implements Runnable {
                final Deque<Integer> prefix, suffix;
                final Consumer<Stream<Integer>> onNext;
                BackTracking(int[] nums, Consumer<Stream<Integer>> onNext) {
                    prefix = new ArrayDeque<>(nums.length);
                    suffix = new ArrayDeque<>(nums.length);
                    Arrays.stream(nums).forEach(suffix::add);
                    this.onNext = onNext;
                }
                @Override
                public void run() {
                    final int N = suffix.size();
                    if (N == 0) {
                        onNext.accept(prefix.stream());
                        return;
                    }
                    for (int i = 0; i < N; i++) {
                        int suffixFirst = suffix.removeFirst();
                        prefix.addLast(suffixFirst);
                        run();
                        prefix.removeLast();
                        suffix.addLast(suffixFirst);
                    }
                }
            }
        },
        /**
         * Iterating approach that is based on {@link IndexesIterator} class,
         * whose implementation is very closed to {@link Problem_031__Next_Permutation}
         */
        ITER_NEXT_PERM {
            public List<List<Integer>> permute(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                IndexesIterator iterIndexes = new IndexesIterator(nums.length);
                do {
                    resultList.add(iterIndexes.valuesList(nums));
                } while (iterIndexes.next() != null);
                return resultList;
            }
        },
        /**
         * Streaming approach that is based on the same {@link IndexesIterator}
         */
        STREAM_NEXT_PERM {
            public List<List<Integer>> permute(int[] nums) {
                return indexesStream(nums.length)
                    .map(indexes -> valuesList(nums, indexes))
                    .toList();
            }
        };

        static Stream<int[]> indexesStream(int size) {
            IndexesIterator iterIndexes = new IndexesIterator(size, false);
            Spliterator<int[]> splitIterIndexes =
                Spliterators.spliteratorUnknownSize(iterIndexes,
                    Spliterator.NONNULL | Spliterator.IMMUTABLE | Spliterator.ORDERED);
            return StreamSupport.stream(splitIterIndexes, false);
        }

        static List<Integer> valuesList(int[] nums, int[] indexes) {
            return Arrays.stream(indexes)
                .mapToObj(i -> nums[i])
                .toList();
        }

        static class IndexesIterator implements Iterator<int[]> {
            final int size;
            final int[] indexes;
            boolean started;
            IndexesIterator(int size) {
                this(size, true);
            }
            IndexesIterator(int size, boolean started) {
                this.size = size;
                this.indexes = ascending(size).toArray();
                this.started = started;
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
                return !started || compareStreams(current(), descending(size)) < 0;
            }

            @Override
            public int[] next() {
                if (!started) {
                    started = true;
                    return indexes;
                }
                if (!hasNext()) {
                    return null;
                }
                int indexOne = lastAscendingPair();
                if (indexOne >= 0) {
                    int indexTwo = indexOfCeil(indexOne + 1, indexes[indexOne]);
                    swap(indexOne, indexTwo);
                }
                // if "indexOne == -1" the whole array, that is fully sorted in descending order
                // become sorted in ascending order and loop of permutation starts another cycle
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
                // returning "-1" means that array "indexes" is sorted in descending order
                return ascPairStart;
            }
            private int indexOfCeil(int begin, int valueToCeil) {
                for (int i = indexes.length - 1; i > begin; i--) {
                    if (indexes[i] > valueToCeil) {
                        return i;
                    }
                }
                return begin;
            }
        }

        static IntStream ascending(int size) {
            return IntStream.range(0, size);
        }

        static IntStream descending(int size) {
            return ascending(size).map(i -> size - i - 1);
        }

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
