package org.krmdemo.techlabs.leet_code_0000_1000;

import org.krmdemo.techlabs.utils.BitSetUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * <h3><a href="https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
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
        BITSET_ITER_SET_CLEAR_PAIR;

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

        private static List<Integer> toOneBasedList(BitSet bitSet) {
            return bitSet.stream().boxed()
                .map(bitIndex -> bitIndex + 1)
                .toList();
        }
    }

    default BitSet minimalIndexes(int n, int k) {
        BitSet bitSet = new BitSet();
        bitSet.set(0, k+1);
        return bitSet;
    }

    default BitSet maximalIndexes(int n, int k) {
        BitSet bitSet = new BitSet();
        bitSet.set(n-k+1, n+1);
        return bitSet;
    }
}
