package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Consumer;

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
                BitSet bitSet = new BitSet();
                bitSet.set(0, k);
                while (bitSet.length() <= n) {
                    resultList.add(toOneBasedList(bitSet));
                    nextCombination(bitSet);
                }
                return resultList;
            }

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
        };

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
