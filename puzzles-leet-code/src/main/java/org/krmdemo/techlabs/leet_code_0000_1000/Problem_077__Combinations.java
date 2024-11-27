package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <h3><a href="https://leetcode.com/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150">
 *     77. Combinations
 * </a></h3>
 * Given two integers <b><code>n</code></b> and <b><code>k</code></b>,
 * return all possible combinations of <b><code>k</code></b> numbers
 * chosen from the range <code>[1, <b>n</b>]</code>.
 * <hr/>
 * You may return the answer in any order.
 *
 * @see <a href="https://docs.python.org/3/library/itertools.html#itertools.combinations">
 *      <code>itertools.combinations</code>
 *     </a>
 * @see Problem_046__Permutations
 */
public interface Problem_077__Combinations {

    /**
     * Solution entry-point.
     */
    List<List<Integer>> combine(int n, int k);

    enum Solution implements Problem_077__Combinations {

        /**
         * This approach is <b>back-tracking</b> when each combination
         * is at the leaf of the tree of recursion.
         */
        ARRAY_BACKTRACKING {
            public List<List<Integer>> combine(int n, int k) {
                List<List<Integer>> resultList = new ArrayList<>();
                new BackTracking(n, k, combList -> {
                    resultList.add(combList.stream()
                        .map(index -> index + 1)
                        .toList());
                }).run();
                return resultList;
            }
            record BackTracking(int N, int K, Consumer<List<Integer>> onNext) implements Runnable {
                @Override
                public void run() {
                    backtrack(new ArrayList<>(K), 0);
                }
                private void backtrack(List<Integer> combList, int firstIndex) {
                    if (combList.size() == K) {
                        onNext.accept(combList);
                        return;
                    }
                    int lastIndex = N - K + combList.size();
                    for (int index = firstIndex; index <= lastIndex; index++) {
                        combList.addLast(index);
                        backtrack(combList, index + 1);
                        combList.removeLast();
                    }
                }
            }
        },

        /**
         * This approach is <b>back-tracking with {@link BitSet}</b> when each combination
         * is at the leaf of the tree of recursion.
         */
        BITSET_BACKTRACKING {
            @Override
            public List<List<Integer>> combine(int n, int k) {
                List<List<Integer>> resultList = new ArrayList<>();
                new BackTracking(n, k, bitSet -> {
                    resultList.add(toOneBasedList(bitSet));
                }).run();
                return resultList;
            }
            record BackTracking(int N, int K, Consumer<BitSet> onNext) implements Runnable {
                @Override
                public void run() {
                    backtrack(new BitSet(), 0);
                }
                private void backtrack(BitSet curr, int firstBit) {
                    if (curr.cardinality() == K) {
                        onNext.accept(curr);
                        return;
                    }
                    int lastBit = N - K + curr.cardinality();
                    for (int bitIndex = firstBit; bitIndex <= lastBit; bitIndex++) {
                        curr.set(bitIndex);
                        backtrack(curr, bitIndex + 1);
                        curr.clear(bitIndex);
                    }
                }
            }
        },

        /**
         * This approach enumerates all combinations one-by-one
         * in a way similar to {@link Problem_031__Next_Permutation}
         */
        BITSET_NEXT_COMBO {
            @Override
            public List<List<Integer>> combine(int n, int k) {
                List<List<Integer>> resultList = new ArrayList<>();
                BitSetIterator iterBitSet = new BitSetIterator(n, k);
                do {
                    resultList.add(iterBitSet.toOneBasedList());
                } while (iterBitSet.next() != null);
                return resultList;
            }
        },

        /**
         * The same iterative approach that is based on {@link BitSetIterator},
         * whose iterative logic is based on {@link #nextCombination(BitSet)}
         */
        BITSET_STREAM {
            @Override
            public List<List<Integer>> combine(int n, int k) {
                return bitSetStream(n, k)
                    .limit(2_598_962)
                    .map(Solution::toOneBasedList)
                    .toList();
            }
        };

        static Stream<BitSet> bitSetStream(int n, int k) {
            BitSetIterator iterBitSet = new BitSetIterator(n, k, false);
            Spliterator<BitSet> splitIterBitSet =
                Spliterators.spliteratorUnknownSize(iterBitSet,
                    Spliterator.NONNULL | Spliterator.IMMUTABLE | Spliterator.ORDERED);
            return StreamSupport.stream(splitIterBitSet, false);
        }

        static class BitSetIterator implements Iterator<BitSet> {
            final int N;
            final int K;
            final BitSet bitSet = new BitSet();
            boolean started;
            BitSetIterator(int n, int k) {
                this(n, k, true);
            }
            BitSetIterator(int n, int k, boolean started) {
                this.N = n;
                this.K = k;
                this.started = started;
                this.bitSet.set(0, k);
            }
            public BitSet current() {
                return bitSet;
            }
            public List<Integer> toOneBasedList() {
                return Solution.toOneBasedList(current());
            }
            public boolean isLast() {
                return IntStream.range(N - K, N).allMatch(bitSet::get);
            }
            @Override
            public boolean hasNext() {
                return !started || !isLast();
            }
            @Override
            public BitSet next() {
                if (!started) {
                    started = true;
                    return bitSet;
                }
                if (!hasNext()) {
                    return null;
                }
                nextCombination(bitSet);
                return bitSet;
            }
        }

        /**
         * Shift the 'set'-bits to the left from lower to higher
         * and keep the number of 'set'-bits the same.
         * <hr/>
         * The lexicographic order of 'set'-bits' indexes is increased,
         * but their sequence numbers are moving to the right on the integer axis
         *
         * @param bitSet an instance of {@link BitSet}, whose 'set'-bits are shifted left
         */
        private static void nextCombination(BitSet bitSet) {
            // TODO: following two 'while'-loops should be optimized
            // looking for the first 'clear'/'set' consecutive pair of bits
            int indexOfClearSet = bitSet.nextClearBit(0);
            if (indexOfClearSet < bitSet.length()) {
                while (!bitSet.get(indexOfClearSet + 1)) {
                    indexOfClearSet++;
                }
            }
            // looking for the first 'set'/'clear' consecutive pair of bits
            int indexOfSetClear = bitSet.nextSetBit(0);
            while (bitSet.get(indexOfSetClear + 1)) {
                indexOfSetClear++;
            }
            bitSet.set(indexOfSetClear + 1);
            if (indexOfClearSet > indexOfSetClear) {
                // flip the pair 'set'/'clear'
                bitSet.clear(indexOfSetClear);
            } else {
                // flip the 'set'-block and 'clear-block
                int lengthSet = indexOfSetClear - indexOfClearSet;
                bitSet.set(0, lengthSet - 1);
                bitSet.clear(lengthSet - 1, indexOfSetClear + 1);
            }
        }

        /**
         * Transform {@link BitSet} into one-based array of {@link Integer}
         *
         * @param bitSet a {@link BitSet} whose indexes of <code>set</code>-bits are collected
         * @return the list of <code>1</code>-incremented bit-indexes
         */
        public static List<Integer> toOneBasedList(BitSet bitSet) {
            return bitSet.stream().boxed()
                .map(bitIndex -> bitIndex + 1)
                .toList();
        }
    }
}
