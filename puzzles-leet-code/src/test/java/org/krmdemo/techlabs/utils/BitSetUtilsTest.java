package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.BitSetUtils.bitSetOf;
import static org.krmdemo.techlabs.utils.BitSetUtils.fromBigInt;
import static org.krmdemo.techlabs.utils.BitSetUtils.intersection;
import static org.krmdemo.techlabs.utils.BitSetUtils.toBigInt;
import static org.krmdemo.techlabs.utils.BitSetUtils.union;
import static org.krmdemo.techlabs.utils.BitSetUtils.unionOf;

/**
 * Unit-Test for {@link BitSetUtils}
 */
public class BitSetUtilsTest {

    @Test
    void test_Intersection() {
        BitSet bitSet = bitSetOf(1, 2, 5, 7, 11, 15, 16, 17);
        BitSet bitSetEven = bitSetOf(IntStream.range(0, 20).filter(i -> (i & 1) == 0));
        assertThat(intersection(bitSet, bitSetEven)).isEqualTo(bitSetOf(2, 16));
        BitSet bitSetDigits = bitSetOf(IntStream.range(0, 10));
        assertThat(intersection(bitSet, bitSetDigits)).isEqualTo(bitSetOf(1, 2, 5, 7));
    }

    @Test
    void test_Union() {
        BitSet bitSetOne = bitSetOf(1, 2, 5, 7, 11, 15, 16, 17);
        BitSet bitSetTwo = bitSetOf(10, 11, 6, 5, 14, 15);
        assertThat(union(bitSetOne, bitSetTwo)).isEqualTo(bitSetOf(1, 2, 5, 6, 7, 10, 11, 14, 15, 16, 17));
        assertThat(unionOf(bitSetOne, bitSetOne, bitSetTwo, bitSetTwo)).isEqualTo(unionOf(bitSetOne, bitSetTwo));
    }

    @Test
    void test_fromBigInt() {
        for (int i = 0; i < 40; i++) {
            BigInteger bigInt = BigInteger.ONE.shiftLeft(i);
            BigInteger bigIntPrev = bigInt.subtract(BigInteger.ONE);
            System.out.printf("%2d) fromBigInt(%,d) --> %s :: fromBigInt(%,d) --> %s;%n",
                i, bigInt, fromBigInt(bigInt), bigIntPrev, fromBigInt(bigIntPrev));
            System.out.printf("    toBigInt(%s) = %,d = %s :: toBigInt(%s) = %,d = %s ;%n",
                fromBigInt(bigInt), toBigInt(fromBigInt(bigInt)),
                Arrays.toString(fromBigInt(bigInt).toByteArray()),
                fromBigInt(bigIntPrev), toBigInt(fromBigInt(bigIntPrev)),
                Arrays.toString(fromBigInt(bigIntPrev).toByteArray())
            );
            assertThat(toBigInt(fromBigInt(bigInt))).isEqualTo(bigInt);
            assertThat(toBigInt(fromBigInt(bigIntPrev))).isEqualTo(bigIntPrev);
        }
    }
}
