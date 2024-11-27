package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * <h3><a href="https://leetcode.com/problems/permutations-ii/description/">
 *     47. Permutations II
 * </a></h3>
 * Given a collection of numbers, <b><code>nums</code></b>, that might contain <b>duplicates</b>,
 * return all possible unique permutations in any order.
 * <hr/>
 * You can return the answer in any order.
 * <h5>Constraints:</h5><pre>
 *  1 <= nums.length <= 8
 *  -10 <= nums[i] <= 10</pre>
 *
 * @see Problem_046__Permutations
 */
public interface Problem_047__Permutations_II {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers with <b>duplicates</b>
     * @return the largest sum of sub-array
     */
    List<List<Integer>> permuteUnique(int[] nums);

    enum Solution implements Problem_047__Permutations_II {
        /**
         * Recursive (back-tracking) approach that is based
         * on a single list with current permutation.
         */
        BACKTRACKING_LIST_UNIQUE {
            @Override
            public List<List<Integer>> permuteUnique(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                new BackTracking(nums, permutation -> {
                    resultList.add(permutation.toList());
                }).run();
                return resultList;
            }
            static class BackTracking implements Runnable {
                final Consumer<Stream<Integer>> onNext;
                final int[] valuesArr;
                final List<Integer> current;
                final Map<Integer,Integer> countingMap;
                BackTracking(int[] valuesArr, Consumer<Stream<Integer>> onNext) {
                    this.onNext = onNext;
                    this.valuesArr = valuesArr;
                    current = new ArrayList<>();
                    countingMap = Arrays.stream(valuesArr).boxed().collect(
                        groupingBy(identity(), summingInt(v -> 1))
                    );
                }
                @Override
                public void run() {
                    if (current.size() == valuesArr.length) {
                        onNext.accept(current.stream());
                        return;
                    }
                    for (Map.Entry<Integer, Integer> entry : countingMap.entrySet()) {
                        int value = entry.getKey();
                        int count = entry.getValue();
                        if (count == 0) {
                            continue;
                        }
                        current.addLast(value);
                        entry.setValue(count - 1);
                        run();
                        entry.setValue(count);
                        current.removeLast();
                    }
                }
            }
        },
        /**
         * The same approach as {@link Problem_046__Permutations.Solution#ITER_NEXT_PERM},
         * but iterating the permutations over the values with {@link ValuesIterator}.
         */
        ITER_NEXT_PERM_UNIQUE {
            @Override
            public List<List<Integer>> permuteUnique(int[] nums) {
                List<List<Integer>> resultList = new ArrayList<>();
                ValuesIterator iterValues = new ValuesIterator(nums);
                do {
                    resultList.add(iterValues.valuesList());
                } while (iterValues.next() != null);
                return resultList;
            }
        },
        /**
         * Streaming approach that is based on the same {@link ValuesIterator}
         */
        STREAM_NEXT_PERM_UNIQUE {
            @Override
            public List<List<Integer>> permuteUnique(int[] nums) {
                return valuesStream(nums)
                    .map(Solution::toValueList)
                    .toList();
            }
        };

        static List<Integer> toValueList(int[] valuesArr) {
            return Arrays.stream(valuesArr).boxed().toList();
        }

        static Stream<int[]> valuesStream(int[] nums) {
            ValuesIterator iterValues = new ValuesIterator(nums, false);
            Spliterator<int[]> splitIterValues =
                Spliterators.spliteratorUnknownSize(iterValues,
                    Spliterator.NONNULL | Spliterator.IMMUTABLE | Spliterator.ORDERED);
            return StreamSupport.stream(splitIterValues, false);
        }

        static class ValuesIterator implements Iterator<int[]> {
            final int[] values;
            final int[] valuesLast;
            boolean started;
            ValuesIterator(int[] nums) {
                this(nums, true);
            }
            ValuesIterator(int[] nums, boolean started) {
                this.values = ascendingArr(nums);
                this.valuesLast = descendingArr(nums);
                this.started = started;
            }
            public List<Integer> valuesList() {
                return toValueList(values);
            }
            @Override
            public boolean hasNext() {
                return !started || compareArrays(values, valuesLast) != 0;
            }
            @Override
            public int[] next() {
                if (!started) {
                    started = true;
                    return values;
                }
                if (!hasNext()) {
                    return null;
                }
                int indexOne = lastAscendingPair();
                if (indexOne >= 0) {
                    int indexTwo = indexOfCeil(indexOne + 1, values[indexOne]);
                    swap(indexOne, indexTwo);
                }
                // if "indexOne == -1" the whole array, that is fully sorted in descending order
                // become sorted in ascending order and loop of permutation starts another cycle
                reverseTail(indexOne + 1);
                return values;
            }
            private void swap(int indexOne, int indexTwo) {
                int valueOne = values[indexOne];
                values[indexOne] = values[indexTwo];
                values[indexTwo] = valueOne;
            }
            private void reverseTail(int left) {
                int right = values.length - 1;
                while (left < right) swap(left++, right--);
            }
            private int lastAscendingPair() {
                int ascPairStart = values.length - 2;
                while (ascPairStart >= 0 && values[ascPairStart] >= values[ascPairStart+1]) {
                    ascPairStart--;
                }
                // returning "-1" means that array "indexes" is sorted in descending order
                return ascPairStart;
            }
            private int indexOfCeil(int begin, int valueToCeil) {
                for (int i = values.length - 1; i > begin; i--) {
                    if (values[i] > valueToCeil) {
                        return i;
                    }
                }
                return begin;
            }
        }

        static int[] ascendingArr(int[] arr) {
            return Arrays.stream(arr).sorted().toArray();
        }

        static int[] descendingArr(int[] arr) {
            final int N = arr.length;
            int[] ascending = ascendingArr(arr);
            return IntStream.range(0, N)
                .map(i -> ascending[N - i - 1])
                .toArray();
        }

        static int compareArrays(int[] intsOne, int[] intsTwo) {
            return compareStreams(Arrays.stream(intsOne), Arrays.stream(intsTwo));
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
