package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/search-pattern0205/1">
 *     Search Pattern (KMP-Algorithm)
 * </a></h3>
 * Given an array <b><code>arr[]</code></b> consisting of <b><code>N</code></b> integers,
 * find the length of the longest sub-array with <b>positive (non-zero) product</b>.
 * <ul>
 *   <li>Expected Time Complexity: <b><code>O(N)</code></b></li>
 *   <li>Expected Auxiliary Space: <b><code>O(1)</code></b></li>
 *   <li>Constraints:<pre>
 *       1 ≤ N ≤ 10^5
 * -10^9 ≤ arr[i] ≤ 10^9</pre></li>
 * </ul>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_152__Max_Product_SubArr
 */
public interface GFG_Arrays__Longest_SubArr_Positive_Product {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array of integers
     * @param N the size of input array <code>arr</code> (usual redundant param for arrays at GFG)
     * @return the length of the longest sub-array with <b>positive (non-zero) product</b>
     */
    int maxLength(int[] arr, int N);

    default int maxLength(int[] arr) {
        return maxLength(arr, arr == null ? 0 : arr.length);
    }

    /**
     * The default implementation is based on
     * {@link org.krmdemo.techlabs.leet_code_0000_1000.Problem_152__Max_Product_SubArr.Solution#TWO_LOOPS
     *  two forward and backward loops
     * }
     */
    enum Solution implements GFG_Arrays__Longest_SubArr_Positive_Product {
        DEFAULT;

        @Override
        public int maxLength(int[] arr, int N) {
            if (arr == null || N == 0) {
                return 0;
            }
            System.out.printf("maxLength (N = %d): %s %n", N, Arrays.toString(arr));
            int prodLen = 0;
            int prodLenMax = 0;
            System.out.println("forward loop:");
            for (int i = 0; i < N; i++) {
                int signumValue = Integer.signum(arr[i]);
                int signumProdLen = prodLen < 0 ? -1 : 1; // previous zero is treated as one
                int nextProdLenAbs = Math.abs(prodLen) + 1;
                prodLen = signumProdLen * signumValue * nextProdLenAbs;
                prodLenMax = Math.max(prodLenMax, prodLen);
                System.out.printf("- arr[%d] = %d; prodLen = %d; prodLenMax = %d;%n",
                    i, arr[i], prodLen, prodLenMax);
            }
            prodLen = 0;
            System.out.println("backward loop:");
            for (int i = N - 1; i >= 0; i--) {
                int signumValue = Integer.signum(arr[i]);
                int signumProdLen = prodLen < 0 ? -1 : 1; // previous zero is treated as one
                int nextProdLenAbs = Math.abs(prodLen) + 1;
                prodLen = signumProdLen * signumValue * nextProdLenAbs;
                prodLenMax = Math.max(prodLenMax, prodLen);
                System.out.printf("- arr[%d] = %d; prodLen = %d; prodLenMax = %d;%n",
                    i, arr[i], prodLen, prodLenMax);
            }
            System.out.println("returning: " + prodLenMax);
            System.out.println("........................");
            return prodLenMax;
        }
    }
}
