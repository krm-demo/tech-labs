package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.BinaryExpUtils.powerBy;

/**
 * Unit-test for {@link BinaryExpUtils}
 */
public class BinaryExpUtilsTest {

    @Test
    void testLongPower() {
        assertThat(powerBy(10, 5)).isEqualTo(100_000);
        assertThat(powerBy(3, 12)).isEqualTo(531_441);
        assertThat(powerBy(1, 10)).isEqualTo(1);
        assertThat(powerBy(0, 10)).isEqualTo(0);
        for (long i = 1; i < Long.SIZE - 1; i++) {
            assertThat(powerBy(2, i)).isEqualTo(1L << i);
        }
    }

    @Test
    void testBigIntPower() {
        BigInteger bigInt17 = BigInteger.valueOf(17);
        assertThat(powerBy(bigInt17, BigInteger.ZERO)).isEqualTo(BigInteger.ONE);
        for (int i = 1; i <= 20; i++) {
            assertThat(powerBy(bigInt17, i)).isEqualTo(bigInt17.pow(i));
        }
    }
}
