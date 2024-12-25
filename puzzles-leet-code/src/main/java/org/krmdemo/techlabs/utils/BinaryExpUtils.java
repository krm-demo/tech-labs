package org.krmdemo.techlabs.utils;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Utility-class to perform binary-exponential algorithm on a repeatable {@link java.util.function.BinaryOperator}
 */
public class BinaryExpUtils {

    public static <T> T binaryExp(T base, long pow, T identity, BinaryOperator<T> binOp) {
        T basePowTwo = base;
        T result = identity;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                result = binOp.apply(result, basePowTwo);
            }
            pow >>= 1;
            if (pow > 0) { // <-- this extra check allows to avoid overflow
                basePowTwo = binOp.apply(basePowTwo, basePowTwo);
            }
        }
        return result;
    }

    public static long powerBy(long base, long pow) {
        if (pow < 0) {
            throw new IllegalArgumentException(
                "negative power: " + pow);
        }
        return binaryExp(base, pow, 1L, Math::multiplyExact);
    }

    public static BigInteger powerBy(BigInteger base, long pow) {
        if (pow < 0) {
            throw new IllegalArgumentException(
                "negative power: " + pow);
        }
        return binaryExp(base, pow, BigInteger.ONE, BigInteger::multiply);
    }

    public static BigInteger powerBy(BigInteger base, BigInteger pow) {
        if (pow.signum() < 0) {
            throw new IllegalArgumentException(
                "negative power: " + pow);
        } else if (pow.bitLength() >= Long.SIZE) {
            throw new IllegalArgumentException(
                "power exceeds the long type: " + pow);
        }
        return binaryExp(base, pow.longValueExact(), BigInteger.ONE, BigInteger::multiply);
    }
}
