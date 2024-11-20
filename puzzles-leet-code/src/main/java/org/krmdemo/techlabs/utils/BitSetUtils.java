package org.krmdemo.techlabs.utils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility-class with static methods to work {@link BitSet} that are not present in JDK
 */
public class BitSetUtils {

    public static BitSet bitSetOf(int... bitIndexesArr) {
        return bitSetOf(Arrays.stream(bitIndexesArr));
    }

    public static BitSet bitSetOf(IntStream bitIndexes) {
        BitSet indexesSet = new BitSet();
        bitIndexes.forEach(indexesSet::set);
        return indexesSet;
    }

    public static BitSet unionOf(BitSet... bitSetsArr) {
        return unionOf(Arrays.stream(bitSetsArr));
    }
    public static BitSet unionOf(Stream<BitSet> bitSets) {
        return bitSets.reduce(BitSetUtils::union).orElse(new BitSet());
    }
    public static BitSet union(BitSet bsOne, BitSet bsTwo) {
        BitSet bsRes = copy(bsOne);
        bsRes.or(bsTwo);
        return bsRes;
    }

    public static BitSet intersection(BitSet... bitSetsArr) {
        return intersection(Arrays.stream(bitSetsArr));
    }
    public static BitSet intersection(Stream<BitSet> bitSets) {
        return bitSets.reduce(BitSetUtils::intersect).orElse(new BitSet());
    }
    public static BitSet intersect(BitSet bsOne, BitSet bsTwo) {
        BitSet bsRes = copy(bsOne);
        bsRes.and(bsTwo);
        return bsRes;
    }

    /**
     * @param bitSet an instance of {@link BitSet} to copy
     * @return the same as {@link BitSet#clone()} but with casting to {@link BitSet
     */
    public static BitSet copy(BitSet bitSet) {
        return (BitSet) bitSet.clone();
    }

    /**
     * Transform {@link BitSet} to corresponding {@link BigInteger} to calculate
     * a <b>sequence-number of sub-set</b> within any greater parent {@link BitSet}}.
     * The transformation is not trivial, because internal representation
     * of similar <i>words of bits</i> has a very important difference:<ul>
     *     <li>bytes in {@link BitSet} are <b>big-endian</b></li>
     *     <li>bytes in {@link BigInteger} are <b>little-endian</b></li>
     * </ul>
     * And in addition to different (reversed) order of <i>words of bits</i>
     * it's very important to remember that {@link BitSet} does not work with negative values
     * and positive sign should be forced (see the <code>signum</code>-parameter in the code).
     *
     * @param bitSet an instance of {@link BitSet} to transform to {@link BigInteger}
     * @return {@link BigInteger} representation of {@link BitSet}, which is a <b>sequence-number of sub-set</b>
     */
    public static BigInteger toBigInt(BitSet bitSet) {
        if (bitSet == null) {
            return null;
        } else if (bitSet.isEmpty()) {
            return BigInteger.ZERO;
        } else {
            return new BigInteger(1, reversedBytes(bitSet.toByteArray()));
        }
    }

    /**
     * Transform non-negative {@link BigInteger} to corresponding {@link BitSet} in order to obtain
     * the sub-set by its <b>sequence-number</b> within any greater parent {@link BitSet}.
     * The transformation is not trivial, because internal representation
     * of similar <i>words of bits</i> has a very important difference:<ul>
     *     <li>bytes in {@link BitSet} are <b>big-endian</b></li>
     *     <li>bytes in {@link BigInteger} are <b>little-endian</b></li>
     * </ul>
     * For null-value, the null-value will be returned
     * and for negative value an {@link IllegalArgumentException} will be thrown.
     *
     * @param bigInt a <b>sequence-number of seb-set</b> within any greater parent {@link BitSet}
     * @return a sub-set that corresponds to that <b>sequence-number</b> as an instance of {@link BitSet}
     */
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

    public static BitSet fromLong(Long longValue) {
        return fromBigInt(longValue == null ? null : BigInteger.valueOf(longValue));
    }

    /**
     * In-place reversing the bytes in passed array of bytes
     *
     * @param byteArr array of bytes
     * @return the reference to the same array
     */
    public static byte[] reversedBytes(byte[] byteArr) {
        if (byteArr != null && byteArr.length > 0) {
            int left = 0;
            int right = byteArr.length - 1;
            while (left < right) {
                swapBytes(byteArr, left++, right--);
            }
        }
        return byteArr;
    }

    /**
     * Swap to bytes in passed array of bytes
     *
     * @param byteArr array of bytes
     * @param left the first value to swap
     * @param right the second value to swap
     */
    public static void swapBytes(byte[] byteArr, int left, int right) {
        byte leftValue = byteArr[left];
        byteArr[left] = byteArr[right];
        byteArr[right] = leftValue;
    }

    // ------------------------------------------------------------------------------------------------------

    private BitSetUtils() {
        // prohibit the creation of utility-class instance
        throw new UnsupportedOperationException("Cannot instantiate a utility-class " + getClass().getName());
    }
}
