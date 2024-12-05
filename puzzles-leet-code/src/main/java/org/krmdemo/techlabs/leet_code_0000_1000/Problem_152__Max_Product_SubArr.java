package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/maximum-product-subarray/description/">
 *     152. Maximum Product Subarray
 * </a></h3>
 * Given an integer array <b><code>nums</code></b>,
 * find a sub-array that has the largest product, and return the product.
 * <h5>Constraints:</h5><pre>
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * </pre>
 * The product of any sub-array of nums is <b>guaranteed</b> to fit in a <b>32-bit</b> integer.
 *
 * @see org.krmdemo.techlabs.gfg_arrays.GFG_Arrays__Longest_SubArr_Positive_Product
 */
public interface Problem_152__Max_Product_SubArr {

    /**
     * Solution entry-point.
     *
     * @param nums an array of integers
     * @return the maximum product of sub-arrays
     */
    int maxProduct(int[] nums);

    @SuppressWarnings("ForLoopReplaceableByForEach")
    enum Solution implements Problem_152__Max_Product_SubArr {
        /**
         * This solution is from <a href="https://leetcode.com/problems/maximum-product-subarray/editorial/#approach-2-dynamic-programming">
         *     Leet-Code Editorial (Approach #2)
         * </a>
         */
        TWO_PRODS {
            @Override
            public int maxProduct(int[] nums) {
                if (nums == null || nums.length == 0) {
                    return 0;
                }
                int prodMin = nums[0];
                int prodMax = nums[0];
                int result = prodMax;
                for (int i = 1; i < nums.length; i++) {
                    int value = nums[i];
                    int newProdMax = maxOf(value, prodMin * value, prodMax * value);
                    prodMin = minOf(value, prodMin * value, prodMax * value);
                    prodMax = newProdMax;
                    result = Math.max(result, prodMax);
                }
                return result;
            }

            public static int maxOf(int... values) {
                return Arrays.stream(values).max().orElseThrow();
            }

            public static int minOf(int... values) {
                return Arrays.stream(values).min().orElseThrow();
            }
        },
        /**
         * This approach is proposed by leet-code user <a href="https://leetcode.com/u/saurav840963/"?>saurav kumar</a>
         * <a href="https://leetcode.com/problems/maximum-product-subarray/solutions/6028839/java-best-solution-100-beats/">
         *     at "09 Nov 2024"
         * </a>
         */
        TWO_LOOPS {
            @Override
            public int maxProduct(int[] nums) {
                if (nums == null || nums.length == 0) {
                    return 0;
                }
                final int N = nums.length;
                int prod = 1;
                int prodMax = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    prod *= nums[i];
                    prodMax = Math.max(prodMax, prod);
                    if (prod == 0) {
                        prod = 1;
                    }
                }
                prod = 1;
                for (int i = N - 1; i >= 0; i--) {
                    prod *= nums[i];
                    prodMax = Math.max(prodMax, prod);
                    if (prod == 0) {
                        prod = 1;
                    }
                }
                return prodMax;
            }
        }
    }
}
