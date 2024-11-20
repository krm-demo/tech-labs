package org.krmdemo.techlabs.sandbox;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-case to play with parallel streams.
 */
@Disabled("takes more than minute, so - disabled by default")
@SuppressWarnings({"SameParameterValue", "ResultOfMethodCallIgnored"})
public class ParallelStreamTest {

    @Test
    void testIterate_NoDelay_Seq() {
        BigInteger expectedCount = BigInteger.valueOf(100_000_000);
        long count = timesBigInt(expectedCount).count();
        assertThat(count).isEqualTo(expectedCount.longValueExact());
    }

    @Test
    void testIterate_NoDelay_Par() {
        BigInteger expectedCount = BigInteger.valueOf(100_000_000);
        long count = timesBigInt(expectedCount).parallel().count();
        assertThat(count).isEqualTo(expectedCount.longValueExact());
    }

    @Test
    void testIterate_SomeSimpleMath_Seq() {
        BigInteger expectedCount = BigInteger.valueOf(300_000_000);
        long count = timesBigInt(expectedCount)
            .peek(bigInt -> {
                bigInt.mod(BigInteger.valueOf(97)).pow(17);
                if (bigInt.mod(BigInteger.valueOf(50_000_000)).signum() == 0) {
                    String threadName = Thread.currentThread().getName();
                    System.out.printf("%s : bigInt = %,d;%n", threadName, bigInt);
                }
            })
            .count();
        assertThat(count).isEqualTo(expectedCount.longValueExact());
    }

    @Test
    void testIterate_SomeSimpleMath_Par() {
        BigInteger expectedCount = BigInteger.valueOf(300_000_000);
        long count = timesBigInt(expectedCount)
            .parallel()
            .peek(bigInt -> {
                bigInt.mod(BigInteger.valueOf(97)).pow(17);
                if (bigInt.mod(BigInteger.valueOf(50_000_000)).signum() == 0) {
                    String threadName = Thread.currentThread().getName();
                    System.out.printf("%s : bigInt = %,d;%n", threadName, bigInt);
                }
            })
            .count();
        assertThat(count).isEqualTo(expectedCount.longValueExact());
    }

    private Stream<BigInteger> timesBigInt(BigInteger timesCount) {
        return rangeClosedBigInt(BigInteger.ONE, timesCount);
    }

    private Stream<BigInteger> rangeClosedBigInt(BigInteger valueFrom, BigInteger valueTo) {
        return Stream.iterate(
            valueFrom,
            value -> value.compareTo(valueTo) <= 0,
            value -> value.add(BigInteger.ONE));
    }
}
