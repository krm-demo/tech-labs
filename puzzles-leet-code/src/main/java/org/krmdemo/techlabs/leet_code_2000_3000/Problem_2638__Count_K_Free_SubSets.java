package org.krmdemo.techlabs.leet_code_2000_3000;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

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
//                System.out.printf("maximum subSetSeqNum = %,d;%n", subSetSeqNum);
                do {
                    subSetSeqNum = subSetSeqNum.subtract(BigInteger.ONE);
                    BitSet indexesSet = fromBigInt(subSetSeqNum);
//                    System.out.println("indexesSet --> " + indexesSet + " : " + subSetSeqNum);
                    BitSet current = new BitSet(); // <-- HashSet will occupy less memory
                    boolean kFreeViolated = indexesSet.stream().anyMatch(index -> {
                        int value = nums[index];
//                        System.out.printf("value = nums[%d] = %d; ", index, value);
                        if (current.get(value + K) || (value >= K && current.get(value - K))) {
//                            System.out.println("violating");
                            return true;
                        } else {
                            current.set(value);
//                            System.out.println("OK, current --> " + current);
                            return false;
                        }
                    });
                    if (!kFreeViolated) {
                        kFreeCount = kFreeCount.add(BigInteger.ONE);
//                        System.out.printf("%3d) k-Free --> %s; count = %3d;%n",
//                            subSetSeqNum, current, kFreeCount);
//                    } else {
//                        System.out.printf("%3d) k-Free is violated;%n", subSetSeqNum);
                    }
                } while(subSetSeqNum.signum() > 0);
                return kFreeCount.longValueExact();
            }
        },

//        /**
//         * See ParallelStreamTest
//         */
//        BRUTE_FORCE_PAR {
//            @Override
//            public long countTheNumOfKFreeSubsets(int[] nums, int K) {
//                BigInteger kFreeCount = BigInteger.ZERO;
//                BigInteger maxSetSeqNum = BigInteger.ONE.shiftLeft(nums.length);
//                Stream<BigInteger> subSetSeqNums = Stream.iterate(
//                    BigInteger.ZERO,
//                    subSetSeqNum -> subSetSeqNum.compareTo(maxSetSeqNum) < 0,
//                    subSetSeqNum -> subSetSeqNum.add(BigInteger.ONE));
//                long countSeq = subSetSeqNums.sequential().count();
//                long countPar = subSetSeqNums.parallel().count();
//                return -1;
//            }
//        },

        MATH {
            public long countTheNumOfKFreeSubsets(int[] nums, int K) {
                record Pair(int left, int right) {
                    @Override
                    public String toString() {
                        return String.format("(%d,%d)", left(), right());
                    }
                }
                Comparator<Pair> comparatorPair =  // <-- no need to use in real calculations, but only for debugging
                    Comparator.<Pair>comparingInt(Pair::left)
                        .thenComparingInt(Pair::right);

                Set<Pair> pairsSet = new HashSet<>();
                BitSet valuesLeft = new BitSet();
                BitSet valuesRight = new BitSet();
                BitSet valuesAll = Arrays.stream(nums).collect(
                    BitSet::new, BitSet::set, BitSet::or
                );
                valuesAll.stream().forEach(value -> {
                    if (value >= K && valuesAll.get(value - K)) {
                        valuesLeft.set(value - K);
                        valuesRight.set(value);
                        pairsSet.add(new Pair(value - K, value));
                    }
                    if (valuesAll.get(value + K)) {
                        valuesLeft.set(value);
                        valuesRight.set(value + K);
                        pairsSet.add(new Pair(value, value + K));
                    }
                });
                BitSet valuesMiddle = intersect(valuesLeft, valuesRight);

                NavigableSet<Pair> pairsSorted = new TreeSet<>(comparatorPair);
                pairsSorted.addAll(pairsSet);
                System.out.printf("-------- Solution.MATH ( K = %d ) : --------%n", K);
                System.out.printf("valuesAll (size = %d) --> %s;%n", valuesAll.cardinality(), valuesAll);
                System.out.printf("pairsSet  (size = %d) --> %s;%n", pairsSorted.size(), pairsSorted);
                System.out.printf("valuesLeft   (size = %d) --> %s;%n", valuesLeft.cardinality(), valuesLeft);
                System.out.printf("valuesRight  (size = %d) --> %s;%n", valuesRight.cardinality(), valuesRight);
                System.out.printf("valuesMiddle (size = %d) --> %s;%n", valuesMiddle.cardinality(), valuesMiddle);

                return -1;
            }

            public static BitSet intersect(BitSet bsOne, BitSet bsTwo) {
                BitSet bsRes = copy(bsOne);
                bsRes.and(bsTwo);
                return bsRes;
            }

            public static BitSet copy(BitSet bitSet) {
                return (BitSet) bitSet.clone();
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

        public static BigInteger toBigInt(BitSet bitSet) {
            if (bitSet == null) {
                return null;
            } else if (bitSet.isEmpty()) {
                return BigInteger.ZERO;
            } else {
                return new BigInteger(1, reversedBytes(bitSet.toByteArray()));
            }
        }

        public static BitSet fromBigInt(BigInteger bigInt) {
            if (bigInt == null) {
                return null;
            } else if (bigInt.signum() < 0) {
                throw new IllegalArgumentException(
                    "fromBigInt: 'bigInt' must NOT be negative - " + bigInt);
            } else if (bigInt.signum() == 0) {
                return new BitSet();
            } else {
                return BitSet.valueOf(reversedBytes(bigInt.toByteArray()));
            }
        }

        private static byte[] reversedBytes(byte[] byteArr) {
            if (byteArr != null && byteArr.length > 0) {
                int left = 0;
                int right = byteArr.length - 1;
                while (left < right) {
                    swapBytes(byteArr, left++, right--);
                }
            }
            return byteArr;
        }

        private static void swapBytes(byte[] byteArr, int left, int right) {
            byte leftValue = byteArr[left];
            byteArr[left] = byteArr[right];
            byteArr[right] = leftValue;
        }
    }
}
