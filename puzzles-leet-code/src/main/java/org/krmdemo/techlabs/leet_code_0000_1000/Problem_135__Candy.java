package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150">
 *     135. Candy
 * </a></h3>
 * There are n children standing in a line.
 * Each child is assigned a rating value given in the integer array <b><code>ratings</code></b>.
 * <hr/>
 * You are giving candies to these children subjected to the following requirements:<ul>
 *     <li>Each child must have at least one candy.</li>
 *     <li>Children with a higher rating get more candies than their neighbors.</li>
 * </ul>
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <h5>Constraints:</h5><pre>
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 * </pre>
 */
public interface Problem_135__Candy {

    /**
     * Solution entry-point.
     *
     * @param ratings integer array of children's ratings <code>0 <= ratings[i] <= 2 * 10^4</code>
     * @return minimum number of candies to distribute among the children.
     */
    int candy(int[] ratings);

    enum Solution implements Problem_135__Candy {
        MAPPERS {
            @Override
            public int candy(int[] ratings) {
                int[] diffSignums = Arrays.stream(ratings)
                    .map(diffSignum()).toArray();
                System.out.println("ratings ---> " + Arrays.toString(ratings));
                System.out.println("diffSignums: " + Arrays.toString(diffSignums));
                return super.candy(ratings); // TODO: optimize with one round
            }
            IntUnaryOperator diffSignum() {
                Integer[] prev = new Integer[] { null };
                return curr -> {
                    int result = prev[0] == null ? 0 :
                        Integer.compare(curr, prev[0]);
                    prev[0] = curr;
                    return result;
                };
            }
        },
        TWO_ARR;

        @Override
        public int candy(int[] ratings) {
            final int N = ratings.length;
            int[] left2right = new int[N];
            int[] right2left = new int[N];
            Arrays.fill(left2right, 1);
            Arrays.fill(right2left, 1);
            for (int i = 1; i < N; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    left2right[i] = left2right[i - 1] + 1;
                }
            }
            for (int i = N - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    right2left[i] = right2left[i + 1] + 1;
                }
            }
//            for (int i = 0; i < ratings.length; i++) {
//                sum += Math.max(left2right[i], right2left[i]);
//            }
            return IntStream.range(0, ratings.length)
                .map(i -> Math.max(left2right[i], right2left[i]))
                .sum();
        }
    }
}
