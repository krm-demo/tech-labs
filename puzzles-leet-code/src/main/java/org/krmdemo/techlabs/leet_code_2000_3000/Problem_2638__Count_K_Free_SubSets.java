package org.krmdemo.techlabs.leet_code_2000_3000;

import java.math.BigInteger;
import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/count-the-number-of-k-free-subsets/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     2638. Count the Number of K-Free Subsets
 * </a></h3>
 * You are given an integer array <b><code>nums</code></b>,
 * which contains <b>distinct</b> elements and an integer <b><code>K</code></b>.
 * <hr/>
 * A <b>subset</b> of an array is a selection of elements (possibly none) of the array.
 * A subset is called a <b>k-Free</b> subset if it contains no two elements
 * with an absolute difference equal to <b><code>K</code></b>.
 * Notice that the empty set is a k-Free subset.
 * <hr/>
 * Return the number of k-Free subsets of nums.
 * <h4>Constraints:</h4><pre>
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 1000
 * 1 <= K <= 1000
 * </pre>
 */
public interface Problem_2638__Count_K_Free_SubSets {

    long countTheNumOfKFreeSubsets(int[] nums, int K);

    enum Solution implements Problem_2638__Count_K_Free_SubSets {
        BRUTE_FORCE {
            @Override
            public long countTheNumOfKFreeSubsets(int[] nums, int K) {
                Arrays.sort(nums);
                BigInteger kFreeCount = BigInteger.ZERO;
                BigInteger subSetSeqNum = BigInteger.ONE.shiftLeft(nums.length);
                do {
                    subSetSeqNum = subSetSeqNum.subtract(BigInteger.ONE);
                    BitSet indexesSet = fromBigInt(subSetSeqNum);
                    System.out.println("indexesSet --> " + indexesSet);
                    BitSet current = new BitSet(); // <-- HashSet will occupy less memory
                    boolean kFreeViolated = indexesSet.stream().anyMatch(index -> {
                        int value = nums[index];
                        System.out.printf("value = nums[%d] = %d; ", index, value);
                        if (current.get(value + K) || (value >= K && current.get(value - K))) {
                            System.out.println("violating");
                            return true;
                        } else {
                            current.set(value);
                            System.out.println("OK, current --> " + current);
                            return false;
                        }
                    });
                    if (!kFreeViolated) {
                        kFreeCount = kFreeCount.add(BigInteger.ONE);
                        System.out.printf("%3d) k-Free --> %s; count = %3d;%n",
                            subSetSeqNum, current, kFreeCount);
                    } else {
                        System.out.printf("%3d) k-Free is violated", subSetSeqNum);
                    }
                } while(subSetSeqNum.signum() > 0);
                return kFreeCount.longValueExact();
            }

            public static BigInteger toBigInt(BitSet bitSet) {
                return new BigInteger(bitSet.toByteArray());
            }

            public static BitSet fromBigInt(BigInteger bigInt) {
                return BitSet.valueOf(bigInt.toByteArray());
            }
        },
        DEFAULT;

        @Override
        public long countTheNumOfKFreeSubsets(int[] nums, int K) {
            BitSet originalBitSet = Arrays.stream(nums).collect(
                BitSet::new, BitSet::set, BitSet::or
            );
            System.out.println("bitSet --> " + originalBitSet);
            BigInteger totalCount = BigInteger.ONE.shiftLeft(originalBitSet.cardinality());
            return totalCount.longValueExact();
        }
    }
}
