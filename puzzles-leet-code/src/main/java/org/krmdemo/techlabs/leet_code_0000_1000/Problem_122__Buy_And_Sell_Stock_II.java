package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150">
 *     122. Best Time to Buy and Sell Stock
 * </a></h3>
 * You are given an array <code>prices</code> where <code>prices[<b>i</b>]</code>
 * is the price of a given stock on the <b><code>i</code></b>th day.
 * <hr/>
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold <b>at most one</b> share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * <hr/>
 * Find and return the maximum profit you can achieve.
 * <h4>Constraints:</h4><pre>
 * 1 <= prices.length <= 10^5
 *    0 <= prices[i] <= 10^4
 * </pre>
 *
 * @see Problem_053__Maximum_SubArray_Sum
 * @see Problem_121__Buy_And_Sell_Stock
 */
public interface Problem_122__Buy_And_Sell_Stock_II {

    /**
     * Solution entry-point.
     *
     * @param prices daily prices
     * @return the maximum profit you can achieve from one buy and once subsequent sell
     */
    int maxProfit(int[] prices);

    enum Solution implements Problem_122__Buy_And_Sell_Stock_II {
        /**
         * This approach is based on the second part Kadane's algorithm,
         * when we are just looking for the maximum forward elevation
         */
        POSITIVE_SUM_DIFF {
            @Override
            public int maxProfit(int[] prices) {
                int maxProfit = 0;
                for (int i = 1; i < prices.length; i++) {
                    int diff = prices[i] - prices[i - 1];
                    if (diff > 0) {
                        maxProfit += diff;
                    }
                }
                return maxProfit;
            }
        },

        /**
         * The same as Kadane's algorithm, but using Java-Streams instead of loops
         */
        WITH_PREV_DIFF {
            @Override
            public int maxProfit(int[] prices) {
                return Arrays.stream(prices)
                    .flatMap(withPrev((prevValue, value) ->
                        Math.max(0, value - prevValue)
                    )).sum();
            }

            private IntFunction<IntStream> withPrev(
                    ToIntBiFunction<Integer,Integer> withPrevFunc) {
                Integer[] prev = new Integer[] { null };
                return value -> {
                    Integer prevValue = prev[0];
                    prev[0] = value;
                    return prevValue == null ? IntStream.empty() :
                        IntStream.of(withPrevFunc.applyAsInt(prevValue, value));
                };
            }
        };
    }

}
