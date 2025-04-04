package org.krmdemo.techlabs.gfg_heap;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/batch/jbp-asde/track/amazon-heap/problem/minimum-cost-of-ropes-1587115620">
 *     Minimum Cost of ropes
 * </a></h3>
 * Given an array, <b><code>arr[]</code></b> of rope lengths,
 * connect all ropes into a single rope with the <b>minimum total cost</b>.
 * The <b>cost</b> to connect two ropes is the <b>sum of their lengths</b>.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^5
 *   1 ≤ arr[i] ≤ 10^4
 * </pre>
 * <small><i><u>Credentials to GFG:</u></i><pre>
 * aleksey.kurmanov@gmail.com
 * 1qa@WS0ok(IJ
 * </pre></small>
 */
public interface GFG_Heap__Min_Cost_of_Ropes_to_Connect {

    /**
     * GFG-Solution entry-point
     *
     * @param arr <code>i</code>th element represent <code>i</code>th rope length
     * @return <b>minimum total cost</b> after connecting all ropes
     */
    int minCost(int[] arr);

    /**
     * The proposed solution is based on {@link PriorityQueue}
     * and appears to be a classic greedy (the strict proof is not trivial)
     */
    enum Solution implements GFG_Heap__Min_Cost_of_Ropes_to_Connect {
        DEFAULT;

        @Override
        public int minCost(int[] arr) {
            PriorityQueue<Integer> pq =
                Arrays.stream(arr).boxed().collect(
                    java.util.stream.Collectors.toCollection(
                        PriorityQueue::new));
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
    }
}
