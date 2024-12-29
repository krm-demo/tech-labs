package org.krmdemo.techlabs.leet_code_1000_2000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/min-cost-to-connect-all-points/description/?envType=problem-list-v2&envId=minimum-spanning-tree">
 *     1584. Min Cost to Connect All Points
 * </a></h3>
 * You are given an array <b><code>points</code></b> representing integer coordinates of some points on a 2D-plane,
 * where <code>points[<b>i</b>] = {x<b>i</b>, y<b>i</b>}</code>.
 * <hr/>
 * The cost of connecting two points <code>{x<b>i</b>, y<b>i</b>}</code> and <code>{x<b>j</b>, y<b>j</b>}</code>
 * is the manhattan distance between them: <code>|x<b>i</b> - x<b>j</b>| + |y<b>i</b> - y<b>j</b>|</code>,
 * where <code>|val|</code> denotes the absolute value of <code>val</code>.
 * <hr/>
 * Return the minimum cost to make all points connected.
 * All points are connected if there is <b>exactly one</b> simple path between any two points.
 * <h5>Constraints:</h5><pre>
 *   1 <= points.length <= 1000
 *    -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 * </pre>
 *
 * @see <a href="https://www.geeksforgeeks.org/what-is-minimum-spanning-tree-mst/">
 *     What is Minimum Spanning Tree (MST)
 * </a>
 */
public interface Problem_1584__Min_Cost_to_Connect_All_Points {

    /**
     * Solution entry-point
     *
     * @param points an array of integer coordinates of some points on a 2D-plane
     * @return the minimum cost to make all points connected (the price of each edge is its "manhattan" distance)
     */
    int minCostConnectPoints(int[][] points);

    /**
     * The default solution is based on
     */
    enum Solution implements Problem_1584__Min_Cost_to_Connect_All_Points {
        DEFAULT;

        @Override
        public int minCostConnectPoints(int[][] points) {
            int[] zeroPoint = points[0];
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p1, p2) ->
                    Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1])
            );
            return 0;
        }
    }
}
