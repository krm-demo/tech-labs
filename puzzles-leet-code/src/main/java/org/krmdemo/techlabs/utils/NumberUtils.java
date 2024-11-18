package org.krmdemo.techlabs.utils;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static org.krmdemo.techlabs.utils.CumulativeUtils.reversedList;

/**
 * Utility-class with static methods to work with numbers, digits, combinatorics, series ...
 */
public class NumberUtils {

    public static boolean isPow2(int num) {
        return num > 0 && Integer.bitCount(num) == 1;
    }

    public static boolean isAllBitsSet(int num) {
        return num > 0 && isPow2(num + 1);
    }

    public static java.util.stream.IntStream digitsFromInt(int num) {
        Spliterator.OfInt ofInt = Spliterators.spliteratorUnknownSize(
            new PrimitiveIterator.OfInt() {
                private int currentNum = num;
                @Override public boolean hasNext() {
                    return currentNum > 0;
                }
                @Override public int nextInt() {
                    int mod = currentNum % 10;
                    currentNum /= 10;
                    return mod;
                }
            },
            Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL);
        return StreamSupport.intStream(ofInt, false);
    }

    public static int digitsToInt(java.util.stream.IntStream digits) {
        return reversedList(digits).stream().reduce(0, (value, digit) -> 10 * value + digit);
    }

    public static String digitsToString(java.util.stream.IntStream digits) {
        return digits.collect(
            StringBuilder::new,
            (sb, digit) -> sb.insert(0, digit),
            (sb1, sb2) -> { throw new IllegalStateException("combining is prohibited"); }
        ).toString();
    }

    /**
     * Number of ways to choose <code>k</code> items from <code>n</code> items without repetition and without order.
     * Evaluates to: <pre>    n! / (k! * (n - k)!)    </pre> when k <= n and evaluates to zero when k > n.
     * <p/>
     * Also called the binomial coefficient because it is equivalent
     * to the coefficient of k-th term in polynomial expansion of the expression <code>(1 + x)^n</code>.
     *
     * @param n the total number of items to choose from as <code>int</code>
     * @param k the desired number of items to be chosen as <code>int</code>
     * @return <code>n! / (k! * (n - k)!)</code> as <code>int</code>
     *
     * @see <a href="https://docs.python.org/3/library/math.html#math.comb">math.comb(n, k)</a> in Python 3.x
     */
    public static int combInt(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException(String.format(
                "negative arguments in combInt(%d,%d) are not allowed", n, k));
        }
        if (k == 0) {
            return 1;
        }
        if (k > n) {
            return 0;
        }
        long numr = 1;
        long denr = 1;
        for (int i = 1; i <= k; i++) {
            numr *= (n - k + i);
            if (numr <= 0) {
                throw new ArithmeticException(String.format(
                    "overflow in combInt(%d,%d) on calculating the multiplier #%d", n, k, i));
            }
            denr *= i;
            if (numr % denr == 0) {
                // following trick allows to calculate much more values without overflow
                numr /= denr;
                denr = 1;
            }
        }
        return Math.toIntExact(numr / denr);
    }

    /**
     * The same as {@link #combInt(int, int)}, but for <code>long</code> arguments and returning value.
     *
     * @param n the total number of items to choose from as <code>long</code>
     * @param k the desired number of items to be chosen as <code>long</code>
     * @return <code>n! / (k! * (n - k)!)</code> as <code>long</code>
     */
    public static long combLong(long n, long k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException(String.format(
                "negative arguments in combLong(%d,%d) are not allowed", n, k));
        }
        if (k == 0) {
            return 1;
        }
        if (k > n) {
            return 0;
        }
        long numr = 1;
        long denr = 1;
        for (int i = 1; i <= k; i++) {
            numr *= (n - k + i);
            if (numr <= 0) {
                throw new ArithmeticException(String.format(
                    "overflow in combLong(%d,%d) on calculating the multiplier #%d", n, k, i));
            }
            denr *= i;
            if (numr % denr == 0) {
                // following trick allows to calculate much more values without overflow
                numr /= denr;
                denr = 1;
            }
        }
        return numr / denr;
    }

    /**
     * The same as {@link #combInt(int, int)}, but for {@link BigInteger} arguments and returning value.
     *
     * @param n the total number of items to choose from as {@link BigInteger}
     * @param k the desired number of items to be chosen as {@link BigInteger}
     * @return <code>n! / (k! * (n - k)!)</code> as {@link BigInteger}
     */
    public static BigInteger combBigInt(BigInteger n, BigInteger k) {
        if (n.signum() < 0 || k.signum() < 0) {
            throw new IllegalArgumentException(String.format(
                "negative arguments in combLong(%d,%d) are not allowed", n, k));
        }
        if (k.signum() == 0) {
            return BigInteger.ONE;
        }
        if (k.compareTo(n) > 0) {
            return BigInteger.ZERO;
        }
        BigInteger numr = BigInteger.ONE;
        BigInteger denr = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
            numr = numr.multiply ( n.subtract(k).add(i) );
            denr = denr.multiply(i);
            if (numr.remainder(denr).signum() == 0) {
                // following trick allows to calculate a little bit faster
                numr = numr.divide(denr);
                denr = BigInteger.ONE;
            }
        }
        return numr.divide(denr);
    }

    /**
     * The same as {@link #combLong(long, long)}, but for {@link BigInteger} returning value.
     *
     * @param n the total number of items to choose from as <code>long</code>
     * @param k the desired number of items to be chosen as <code>long</code>
     * @return <code>n! / (k! * (n - k)!)</code> as {@link BigInteger}
     */
    public static BigInteger combBigInt(long n, long k) {
        return combBigInt(bigInt(n), bigInt(k));
    }

    /**
     * The same as {@link #combLong(long, long)}, but the result is returned by module <code>mod</code>
     *
     * @param n the total number of items to choose from as <code>long</code>
     * @param k the desired number of items to be chosen as <code>long</code>
     * @param mod the remainder of division the original result on the value <code>mod</code>
     * @return <code>( n! / (k! * (n - k)!)) ) % mod</code> as <code>long</code>
     */
    public static long combLongMod(long n, long k, long mod) {
        if (mod <= 1) {
            throw new IllegalArgumentException(String.format(
                "module less than 1 in combLongMod(%d,%d, %d ) is not allowed", n, k, mod));
        }
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException(String.format(
                "negative arguments in combLongMod(%d,%d, %d ) are not allowed", n, k, mod));
        }
        if (k == 0) {
            return 1;
        }
        if (k > n) {
            return 0;
        }
        long numr = 1;
        long denr = 1;
        for (int i = 1; i <= k; i++) {
            numr *= (n - k + i);
            if (numr <= 0) {
                throw new ArithmeticException(String.format(
                    "overflow in combLongMod(%d,%d, %d ) on calculating the multiplier #%d", n, k, mod, i));
            }
            denr *= i;
            numr %= mod;
            denr %= mod;
        }
        BigInteger denrInverse = bigInt(denr).modInverse(bigInt(mod));
        return (numr * denrInverse.longValue()) % mod;
    }

    /**
     * Short-version of {@link BigInteger#valueOf(long)}
     *
     * @param longValue value of the {@link BigInteger} to return.
     * @return a BigInteger with the specified value.
     */
    public static BigInteger bigInt(long longValue) {
        return BigInteger.valueOf(longValue);
    }

    // TODO: write functions for factorial, permutations, bellNumbers ...

    // ------------------------------------------------------------------------------------------
    //            Calculating the factorial(N):   N! = N * (N-1) * (N-2) ... * 3 * 2 * 1
    // ------------------------------------------------------------------------------------------

    public static BigInteger fastFibo(long n) {
        return fastFibo(BigInteger.valueOf(n));
    }
    public static BigInteger fastFibo(BigInteger N) {
        MatrixUtils.M2 fiboM = MatrixUtils.matrix2(1, 1, 1, 0);
        return fastFiboM2(N).a12;
    }
    public static MatrixUtils.M2 fastFiboM2(BigInteger N) {
        MatrixUtils.M2 fiboM = MatrixUtils.matrix2(1, 1, 1, 0);
        return MatrixUtils.powBin(fiboM, N);
    }

    /**
     * @param n a non-negative <b><code>int</code></b> to calculate the factorial of
     * @return factorial of an <code>int</code> value as <code>int</code>
     * @throws ArithmeticException if the value of {@code this} will not exactly fit in an {@code int}.
     */
    public static int factorialInt(int n) {
        return factorial(n).intValueExact();
    }

    /**
     * @param n a non-negative <b><code>int</code></b> to calculate the factorial of
     * @return factorial of an <code>int</code> value as <code>int</code>
     * @throws ArithmeticException if the value of {@code this} will not exactly fit in an {@code long}.
     */
    public static long factorialLong(long n) {
        return factorial(n).longValueExact();
    }

    /**
     * @param n a non-negative <b><code>long</code></b> to calculate the factorial of
     * @return factorial of a <code>long</code> value as {@link BigInteger}
     */
    public static BigInteger factorial(long n) {
        return factorialCalculated(n);
    }

    /**
     * @param N a non-negative {@link BigInteger} to calculate the factorial of
     * @return factorial of a {@link BigInteger} value as {@link BigInteger}
     */
    public static BigInteger factorial(BigInteger N) {
        return factorialCalculated(N);
    }

    public static final int CALCULATED_FACTORIAL_MAX = 1000;
    public static final BigInteger[] CALCULATED_FACTORIAL_VALUES =
        new BigInteger[CALCULATED_FACTORIAL_MAX + 1];

    private static <T> T calculated(long n, T[] cacheArr, int cacheSize, Function<BigInteger,T> calc) {
        int calcIndex = (int)n;
        BigInteger N = BigInteger.valueOf(n);
        if (calcIndex > cacheSize) {
            return calc.apply(N);
        }
        if (cacheArr[calcIndex] == null) {
            cacheArr[calcIndex] = calc.apply(N);
        }
        return cacheArr[calcIndex];
    }
    @SuppressWarnings("SameParameterValue")
    private static <T> T calculated(BigInteger N, T[] cacheArr, int cacheSize, Function<BigInteger,T> calc) {
        return calculated(N.longValue(), cacheArr, cacheSize, calc);
    }
    private static BigInteger factorialCalculated(long n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format(
                "factorial(%d) - long argument must not be negative", n));
        }
        return calculated(n, CALCULATED_FACTORIAL_VALUES, CALCULATED_FACTORIAL_MAX, NumberUtils::factorialCalc);
    }
    private static BigInteger factorialCalculated(BigInteger N) {
        if (N.signum() < 0) {
            throw new IllegalArgumentException(String.format(
                "factorial(%d) - BigInteger argument must not be negative", N));
        }
        return calculated(N, CALCULATED_FACTORIAL_VALUES, CALCULATED_FACTORIAL_MAX, NumberUtils::factorialCalc);
    }

    /**
     * The simplest and straight-forward way to calculate factorial.
     * More efficient approaches are available, for example, at:
     * <a href="https://guava.dev/releases/snapshot/api/docs/com/google/common/math/BigIntegerMath.html#factorial(int)">
     *      BigIntegerMath.factorial(int)
     * </a>.
     * For precalculated values - use {@link #factorial(BigInteger)}
     *
     * @param N a non-negative {@link BigInteger} to calculate the factorial of
     * @return factorial of a <code>long</code> value
     */
    public static BigInteger factorialCalc(BigInteger N) {
        return BigInteger.ONE.compareTo(N) >= 0 ? BigInteger.ONE :
            N.multiply(factorialCalc(N.subtract(BigInteger.ONE)));
    }

    // ------------------------------------------------------------------------------------------
    //                    Calculating the factorial digits:
    // ------------------------------------------------------------------------------------------

    public static BigInteger fromFactorialDigits(int[] digits) {
        BigInteger fact = BigInteger.ONE;
        BigInteger value = BigInteger.ZERO;
        for (int i = 0; i < digits.length; i++) {
            fact = fact.multiply(BigInteger.valueOf(i + 1));
            BigInteger factPart = fact.multiply(BigInteger.valueOf(digits[i]));
            value = value.add(factPart);
        }
        return value;
    }

    public static BigInteger factorialDigits(BigInteger value, int[] digits) {
        BigInteger fact = factorial(digits.length + 1);
        BigInteger[] divAndRem = value.divideAndRemainder(fact);
        BigInteger higherPart = divAndRem[0];
        int k = digits.length;
        while (k > 0) {
            fact = fact.divide(BigInteger.valueOf(k + 1));
            divAndRem = divAndRem[1].divideAndRemainder(fact);
            k--;
            digits[k] = divAndRem[0].intValue();
        }
        return higherPart;
    }

    public static int highestDigitIndex(int[] digits) {
        int index = digits.length - 1;
        while (index >= 0 && digits[index] == 0) index--;
        return index;
    }
}
