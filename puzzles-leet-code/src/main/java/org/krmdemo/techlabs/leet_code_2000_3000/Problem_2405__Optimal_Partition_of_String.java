package org.krmdemo.techlabs.leet_code_2000_3000;

import java.util.*;
import java.util.function.IntUnaryOperator;

/**
 * <h3><a href="https://leetcode.com/problems/optimal-partition-of-string/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     2405. Optimal Partition of String
 * </a></h3>
 * Given a string <b><code>str</code></b>, partition the string into one or more sub-strings
 * such that the characters in each substring are <b>unique</b>
 * (no letter appears in a single sub-string more than once).
 * <hr/>
 * Return the minimum number of substrings in such a partition.
 * <hr/>
 * Note that each character should belong to exactly one sub-string in a partition.
 */
public interface Problem_2405__Optimal_Partition_of_String {

    /**
     * Solution entry-point
     *
     * @param str a string to find all prtiotions of
     * @return minimum number of substrings in such a partition
     */
    int partitionString(String str);

    enum Solution implements Problem_2405__Optimal_Partition_of_String {
        BIT_SET {
            @Override
            IntUnaryOperator uniqueCountBefore() {
                BitSet uniqueBefore = new BitSet();
                return ch -> {
                    if (uniqueBefore.get(ch)) {
                        uniqueBefore.clear();
                    }
                    uniqueBefore.set(ch);
                    return uniqueBefore.cardinality() - 1;
                };
            }
        },
        INT_MASK {
            @Override
            IntUnaryOperator uniqueCountBefore() {
                int[] intMask = new int[] { 0 };
                return ch -> {
                    int charBit = 1 << (ch - 'a');
                    if ((intMask[0] & charBit) == charBit) {
                        intMask[0] = 0;
                    }
                    intMask[0] |= charBit;
                    return Integer.bitCount(intMask[0]) - 1;
                };
            }
        },
        HASH_SET {
            @Override
            IntUnaryOperator uniqueCountBefore() {
                Set<Character> uniqueBefore = new HashSet<>();
                return ch -> {
                    if (uniqueBefore.contains((char)ch)) {
                        uniqueBefore.clear();
                    }
                    uniqueBefore.add((char)ch);
                    return uniqueBefore.size() - 1;
                };
            }
        },
        BOOL_ARR {
            private static final int ENGLISH_LETTERS_NUM = 'z' - 'a' + 1;
            @Override
            IntUnaryOperator uniqueCountBefore() {
                int[] uniqueCount = new int[] { 0 };
                boolean[] charsPresence = new boolean[ENGLISH_LETTERS_NUM];
                return ch -> {
                    int charIndex = ch - 'a';
                    if (charsPresence[charIndex]) {
                        Arrays.fill(charsPresence, false);
                        uniqueCount[0] = 0;
                    }
                    charsPresence[charIndex] = true;
                    return uniqueCount[0]++;
                };
            }
        };

        abstract IntUnaryOperator uniqueCountBefore();

        @Override
        public int partitionString(String str) {
            return (int)str.chars()
                .map(uniqueCountBefore())
                .filter(cnt -> cnt == 0)
                .count();
        }
    }
}
