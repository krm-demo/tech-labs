package org.krmdemo.techlabs.gfg_temp;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/batch/jbp-asde/track/amazon-heap/problem/minimum-cost-of-ropes-1587115620">
 *     <h3>Minimum Cost of ropes</h3>
 * </a>
 * TODO: still no proof that the approach, which is implemented here ({@link PriorityQueue} ...), is optimal
 * <hr/>
 * TODO: convert to appropriate way (interface + enum + @ParameterizedTest)
 * <h5>Credentials to GFG:</h5><pre>
 * aleksey.kurmanov@gmail.com
 * 1qa@WS0ok(IJ
 * </pre>
 */
public class Problem_HEAP_Min_Cost_of_Ropes_to_Connect {


    /**
     * Given an array, <b><code>arr[]</code></b> of rope lengths,
     * connect all ropes into a single rope with the minimum total cost.
     * The cost to connect two ropes is the sum of their lengths.
     */
    private static class Solution {

        /**
         * Entry point of <a href="https://www.geeksforgeeks.org/">GFG</a>-Puzzle
         *
         * @param arr an array of each rope length
         * @return total cost of all ropes connected
         */
        public long minCost(long[] arr) {
            PriorityQueue<Long> pq =
                Arrays.stream(arr).boxed().collect(
                    java.util.stream.Collectors.toCollection(
                        PriorityQueue::new));
            // System.out.println("arr --> " + Arrays.toString(arr));
            long totalCost = 0;
            while (pq.size() > 1) {
                // System.out.print("totalCost = " + totalCost + "; ");
                // System.out.println("pq --> " + pq);
                long partOne = pq.poll();
                //noinspection DataFlowIssue
                long partTwo = pq.poll();
                long partTotal = partOne + partTwo;
                totalCost += partTotal;
                pq.add(partTotal);
            }
            // System.out.println("finally pq --> " + pq);
            // System.out.println("totalCost = " + totalCost);
            return totalCost;
        }
    }

//    // ------------------------------------------------------------------------------------------
//    //                            Test-Cases' data and methods:
//    // ------------------------------------------------------------------------------------------
//
//    private final Solution sln = new Solution();
//
//    @Test
//    public void test_req_01() {
//        assertThat(sln.minCost(new long[] { 4, 3, 2, 6 }), equalTo(29L));
//    }
//
//    @Test
//    public void test_req_02() {
//        assertThat(sln.minCost(new long[] { 4, 2, 7, 6, 9 }), equalTo(62L));
//    }
}
