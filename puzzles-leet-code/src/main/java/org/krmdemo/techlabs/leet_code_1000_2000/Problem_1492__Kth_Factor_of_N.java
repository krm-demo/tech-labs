package org.krmdemo.techlabs.leet_code_1000_2000;

import java.math.BigInteger;
import java.util.*;

/**
 * <a href="https://leetcode.com/problems/the-kth-factor-of-n/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     <h3>1492. The kth Factor of n</h3>
 * </a>
 * You are given two positive integers <b><code>N</code></b> and <b><code>K</code></b>.
 * <p><small>
 * A factor of an integer <b><code>n</code></b> is defined as an integer <b><code>i</code></b>
 * where <b><code>n % i == 0</code></b>.
 * </small></p>
 *
 * Consider a list of all factors of <b><code>N</code></b> sorted in ascending order,
 * return the <b><code>K</code></b>th factor in this list or return <b><code>-1</code></b>
 * if <b><code>N</code></b> has less than <b><code>K</code></b> factors.
 * <h4>Constraints:</h4>
 * <code>1 <= <b>K</b> <= <b>N</b> <= 1000</code>
 */
public interface Problem_1492__Kth_Factor_of_N {

    /**
     * Solution entry-point
     *
     * @param N a positive integer to search for the Kth factor of
     * @param K the sequence number of a factor (<b><code>1</code></b> is 1st, <b><code>2</code></b> is 2nd, ...)
     * @return <b><code>K</code></b>th factor of <b><code>N</code></b>
     */
    int kthFactor(int N, int K);

    enum Solution implements Problem_1492__Kth_Factor_of_N {
        DEFAULT;

        @Override
        public int kthFactor(int N, int K) {
            System.out.printf("kthFactor ( N = %d, K = %d ):%n", N, K);
            if (K < 1 || K > N) {
                throw new IllegalArgumentException(
                    String.format("K = %d is not in range [ 1; %d ]", K, N));
            }
            if (K == 1) {
                System.out.println("... returning one ...");
                return 1;
            }
            int factorCount = 1;
            for (int factor = 2; factor <= N; factor++) {
                if (N % factor != 0) {
                    continue;
                }
                factorCount++;
                System.out.printf("found factor %d (count #%d);%n", factor, factorCount);
                if (factorCount >= K) {
                    System.out.printf("... returning: %d ...%n", factor);
                    return factor;
                }
            }
            return -1;
        }

        private Map<Integer, Integer> primeFactors(int n) {
            Map<Integer, Integer> divCntMap = new TreeMap<>();
            // Print the number of 2s that divide n
            while (n % 2 == 0) {
                // System.out.print(2 + " ");
                divCntMap.merge(2, 1, Integer::sum);
                n /= 2;
            }

            // n must be odd at this point.  So we can
            // skip one element (Note i = i + 2)
            for (int i = 3; i <= Math.sqrt(n); i += 2) {
                // While i divides n, print i and divide n
                while (n % i == 0) {
                    // System.out.print(i + " ");
                    divCntMap.merge(i, 1, Integer::sum);
                    n /= i;
                }
            }

            // This condition is to handle the case when
            // n is a prime number greater than 2
            if (n > 2) {
                // System.out.print(n);
                divCntMap.merge(n, 1, Integer::sum);
            }
            return divCntMap;
        }
    }
}
