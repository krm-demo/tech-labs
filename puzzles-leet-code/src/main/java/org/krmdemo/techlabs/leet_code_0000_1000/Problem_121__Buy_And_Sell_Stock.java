package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150">
 *     121. Best Time to Buy and Sell Stock
 * </a></h3>
 * You are given an array <code>prices</code> where <code>prices[<b>i</b>]</code>
 * is the price of a given stock on the <b><code>i</code></b>th day.
 * <hr/>
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * <hr/>
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return <b><code>0</code></b>.
 * <h4>Constraints:</h4><pre>
 * 1 <= prices.length <= 10^5
 *    0 <= prices[i] <= 10^4
 * </pre>
 *
 * @see Problem_053__Maximum_SubArray_Sum
 * @see Problem_122__Buy_And_Sell_Stock_II
 */
public interface Problem_121__Buy_And_Sell_Stock {

    /**
     * Solution entry-point.
     *
     * @param prices daily prices
     * @return the maximum profit you can achieve from one buy and once subsequent sell
     */
    int maxProfit(int[] prices);

    enum Solution implements Problem_121__Buy_And_Sell_Stock {
        /**
         * This approach is based on the second part of Kadane's Algorithm,
         * when we are just looking for the maximum forward elevation
         */
        KADANE_MIN_SO_FAR {
            @Override
            public int maxProfit(int[] prices) {
                int minSoFar = prices[0];
                int maxProfit = 0;
                for (int day = 1; day < prices.length; day++) {
                    if (prices[day] < minSoFar) {
                        minSoFar = prices[day];
                    } else {
                        maxProfit = Math.max(maxProfit, prices[day] - minSoFar);
                    }
                }
                return maxProfit;
            }
        },
        /**
         * The same as above, but using Java-Streams instead of loops
         */
        WITH_PREV_MIN {
            @Override
            public int maxProfit(int[] prices) {
                int maxElev = Arrays.stream(prices)
                    .flatMap(withPrevMin((prevMinValue, value) ->
                        value - prevMinValue
                    )).max().orElse(0);
                return Math.max(maxElev, 0);
            }

            private static IntFunction<IntStream> withPrevMin(
                    ToIntBiFunction<Integer,Integer> withMinFunc) {
                Integer[] prevMin = new Integer[] { null };
                return value -> {
                    Integer prevMinValue = prevMin[0];
                    prevMin[0] = prevMinValue == null ? value : Math.min(prevMinValue, value);
                    return prevMinValue == null ? IntStream.empty() :
                        IntStream.of(withMinFunc.applyAsInt(prevMinValue, value));
                };
            }
        };
    }
}
