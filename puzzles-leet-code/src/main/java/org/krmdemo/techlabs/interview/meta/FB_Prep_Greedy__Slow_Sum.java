package org.krmdemo.techlabs.interview.meta;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=2869293499822992&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Greedy #1. Slow_Sum.
 * </a></h3>
 * Suppose we have a list of <b><code>N</code></b> numbers, and repeat the following operation
 * until we're left with only a single number: <i>choose any two numbers and replace them with their sum</i>.
 * Moreover, we associate a penalty with each operation equal to the value of the new number,
 * and call the penalty for the entire list as the sum of the penalties of each operation.
 * <hr/>
 * For example, given the list <code>[1, 2, 3, 4, 5]</code>, we could choose <code>2</code> and <code>3</code>
 * for the first operation, which would transform the list into <code>[1, 5, 4, 5]</code>
 * and incur a penalty of <code>5</code>.
 * <hr/>
 * The goal in this problem is to find the <b>highest possible penalty</b> for a given input.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^6
 *   1 ≤ arr[i] ≤ 10^7
 * </pre>
 *
 * @see org.krmdemo.techlabs.gfg_heap.GFG_Heap__Min_Cost_of_Ropes_to_Connect (the same, but <b>min</b> except <b>max</b>)
 */
public interface FB_Prep_Greedy__Slow_Sum {

    /**
     * Solution entry-point.
     *
     * @param arr an array of positive integers
     * @return <b>highest possible penalty</b> for a given input
     */
    int getTotalTime(int[] arr);

    /**
     * The proposed solution is based on {@link PriorityQueue}
     * and appears to be a classic greedy algorithm (the strict proof is not trivial)
     */
    enum Solution implements FB_Prep_Greedy__Slow_Sum {
        DEFAULT;

        @Override
        public int getTotalTime(int[] arr) {
            PriorityQueue<Integer> pq = Arrays.stream(arr).boxed()
                .collect(toCollection(this::priorityQueueDescending));
            // System.out.println("arr --> " + Arrays.toString(arr));
            int totalCost = 0;
            while (pq.size() > 1) { // <-- it's very important to skip the last single rope
                // System.out.print("totalCost = " + totalCost + "; ");
                // System.out.println("pq --> " + pq);
                int partOne = pq.poll();
                //noinspection DataFlowIssue
                int partTwo = pq.poll();
                int partTotal = partOne + partTwo;
                totalCost += partTotal;
                pq.add(partTotal);
            }
            // System.out.println("finally pq --> " + pq);
            // System.out.println("totalCost = " + totalCost);
            return totalCost;
        }

        /**
         * By default, the head of {@link PriorityQueue} holds the <b>minimal element</b> among all added,
         * and that minimal element is returned one-by-one by {@link PriorityQueue#poll()} method,
         * but in this puzzle we need the maximum element to be polled each time.
         * So, in order to achieve that we have to pass {@link Comparator#reverseOrder()} into constructor.
         *
         * @return the instance of reversed (descending) {@link PriorityQueue}
         */
        private PriorityQueue<Integer> priorityQueueDescending() {
            return new PriorityQueue<>(Comparator.reverseOrder());
        }
    }
}
