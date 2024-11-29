package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.utils.MatrixUtils.M2_EYE;
import static org.krmdemo.techlabs.utils.MatrixUtils.det;
import static org.krmdemo.techlabs.utils.MatrixUtils.matrix2;
import static org.krmdemo.techlabs.utils.MatrixUtils.mult;
import static org.krmdemo.techlabs.utils.MatrixUtils.pow;
import static org.krmdemo.techlabs.utils.MatrixUtils.powBin;

/**
 * Unit-Test for {@link MatrixUtilsTest}
 */
public class MatrixUtilsTest {

    private final MatrixUtils.M2 P = matrix2(2, 5, 3, 8);
    private final MatrixUtils.M2 P_inv = matrix2(8, -5, -3, 2);

    @Test
    void testDet() {
        assertThat(det(P)).isEqualTo(BigInteger.ONE);
        assertThat(det(P_inv)).isEqualTo(BigInteger.ONE);
        assertThat(mult(P, P_inv)).isEqualTo(M2_EYE);
    }

    @Test
    void testPowBin() {
        final int N = 7;
        System.out.println("---- pow(P, i): ----");
        for (int i = 0; i < N; i++) {
            System.out.printf("P^%1$d = pow(P,%d) = %s;%n", i, pow(P, i));
        }
        System.out.println("---- powBin(P, i): ----");
        for (int i = 0; i < N; i++) {
            System.out.printf("P^%1$d = powBin(P,%d) = %s;%n", i, powBin(P, i));
        }
        MatrixUtils.M2 P_123 = powBin(P, 123);
        MatrixUtils.M2 P_minus_123 = pow(P_inv, 123);
        System.out.printf("P^123 = powBin(P,123) = %s;%n", P_123);
        System.out.printf("P^-123 = pow(P_inv,123) = %s;%n", P_minus_123);
        System.out.printf("P^123 = P^-123 = %s;%n", mult(P_123, P_minus_123));
        assertThat(mult(P_123, P_minus_123)).isEqualTo(M2_EYE);
    }
}
