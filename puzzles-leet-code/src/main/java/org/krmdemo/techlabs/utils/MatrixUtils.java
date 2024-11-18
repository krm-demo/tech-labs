package org.krmdemo.techlabs.utils;

import java.math.BigInteger;
import java.util.*;

/**
 * TODO: need to be re-designed
 */
public class MatrixUtils {

    public static M2 M2_EYE = matrix2(1, 0, 0, 1);
    public static M2 M2_ZERO = matrix2(0, 0, 0, 0);

    /**
     * Square-Martix <code>2 x 2</code> of {@link java.math.BigInteger}
     */
    public static class M2 {
        public final BigInteger
            a11, a12,
            a21, a22;
        M2(int... flatArr) {
            this(
                BigInteger.valueOf(flatArr[0]), BigInteger.valueOf(flatArr[1]),
                BigInteger.valueOf(flatArr[2]), BigInteger.valueOf(flatArr[3])
            );
        }
        M2(BigInteger... flatArr) {
            this.a11 = flatArr[0];  this.a12 = flatArr[1];
            this.a21 = flatArr[2];  this.a22 = flatArr[3];
        }
        public M2 copy() {
            return new M2(this.a11, this.a12, this.a21, this.a22);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            M2 m2 = (M2) o;
            return Objects.equals(a11, m2.a11) && Objects.equals(a12, m2.a12)
                && Objects.equals(a21, m2.a21) && Objects.equals(a22, m2.a22);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a11, a12, a21, a22);
        }
        @Override
        public String toString() {
            return String.format("M2[[%d,%d],[%d,%d]]", this.a11, this.a12, this.a21, this.a22);
        }
    }

    public static M2 matrix2(int... flatArr) {
        return new M2(flatArr);
    }
    public static M2 matrix2(BigInteger... flatArr) {
        return new M2(flatArr);
    }

    public static M2 mult(M2 matrixOne, M2 matrixTwo) {
        return matrix2(
            matrixOne.a11.multiply(matrixTwo.a11).add(matrixOne.a12.multiply(matrixTwo.a21)),
            matrixOne.a11.multiply(matrixTwo.a12).add(matrixOne.a12.multiply(matrixTwo.a22)),
            matrixOne.a21.multiply(matrixTwo.a11).add(matrixOne.a22.multiply(matrixTwo.a21)),
            matrixOne.a21.multiply(matrixTwo.a12).add(matrixOne.a22.multiply(matrixTwo.a22))
        );
    }

    public static BigInteger det(M2 matrix) {
        return matrix.a11.multiply(matrix.a22).subtract(matrix.a12.multiply(matrix.a21));
    }

    public static M2 pow(M2 matrix, long n) {
        return pow(matrix, BigInteger.valueOf(n));
    }
    public static M2 pow(M2 matrix, BigInteger N) {
        if (N == null || N.signum() == 0) {
            return M2_EYE;
        } else if (N.signum() < 0) {
            throw new UnsupportedOperationException("no negative power yet");
        }
        M2 res = matrix;
        N = N.subtract(BigInteger.ONE);
        while (N.signum() > 0) {
            res = mult(res, matrix);
            N = N.subtract(BigInteger.ONE);
        }
        return res;
    }

    public static M2 powBin(M2 matrix, long n) {
        return powBin(matrix, BigInteger.valueOf(n));
    }
    public static M2 powBin(M2 matrix, BigInteger N) {
        if (N == null || N.signum() == 0) {
            return M2_EYE;
        } else if (BigInteger.ONE.equals(N)) {
            return matrix;
        }
//        BigInteger[] divAndRem = N.divideAndRemainder(BigInteger.TWO);
//        M2 res = pow(matrix, divAndRem[0]);
//        M2 resPow2 = mult(res, res);
//        if (divAndRem[1].signum() == 0) {
//            return resPow2;
//        } else {
//            return mult(resPow2, matrix);
//        }
        M2 res = pow(matrix, N.shiftRight(1)); // <-- the same as "N / 2"
        M2 resPow2 = mult(res, res);
        if (N.testBit(0)) {  // the same as "N % 2 == 1"
            return mult(resPow2, matrix);
        } else {
            return resPow2;
        }
    }
}
