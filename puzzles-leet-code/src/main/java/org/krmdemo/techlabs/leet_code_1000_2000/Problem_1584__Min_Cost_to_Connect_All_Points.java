package org.krmdemo.techlabs.leet_code_1000_2000;

import org.krmdemo.techlabs.leet_code_0000_1000.Problem_323__Number_of_Connected_SubGraphs;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.range;

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
 * @see Problem_323__Number_of_Connected_SubGraphs
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
        /**
         * @see <a href="https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/">
         *     Kruskal’s Minimum Spanning Tree (MST) Algorithm
         * </a>
         */
        KRUSKAL_DSU {
            @Override
            public int minCostConnectPoints(int[][] pointsArr) {
                PriorityQueue<Line> pq = new PriorityQueue<>(Comparator.comparingInt(Line::distanceM1));
                for (int i = 0; i < pointsArr.length - 1; i++) {
                    for (int j = i + 1; j < pointsArr.length; j++) {
                        pq.add(line(point(pointsArr[i]), point(pointsArr[j])));
                    }
                }
                return 0; // TODO: to be done
            }

            class DSU {
                final int[] parents;
                DSU(int nodesNumber) {
                    this.parents = new int[nodesNumber + 1];
                    // initially all nodes are considered to be connected with itself
                    // ('-1' value - means that the size of sub-set equals to '1')
                    Arrays.fill(this.parents, -1);
                }

                /**
                 * @return the number of disjoint sets (just a count of <b>DSU-root</b>s)
                 */
                int countRoots() {
                    return (int)Arrays.stream(parents, 1, parents.length)
                        .filter(value -> value < 0)
                        .count();
                }

                /**
                 * <b>Union</b>-part of DSU algorithm
                 *
                 * @param edge a pair of nodes to connect with un-directed edge
                 */
                void union(int[] edge) {
                    if (edge == null || edge.length != 2) {
                        throw new IllegalArgumentException("invalid edge: " + Arrays.toString(edge));
                    }
                    if (edge[0] == edge[1]) {
                        throw new IllegalArgumentException("self-loop edge is prohibited: " + Arrays.toString(edge));
                    }
                    int parentOne = find(edge[0] + 1);
                    int parentTwo = find(edge[1] + 1);
                    if (parentOne == parentTwo) {
                        return;
                    }
                    int sizeOne = -parents[parentOne];
                    int sizeTwo = -parents[parentTwo];
                    if (parentOne < parentTwo) { // <-- this strategy makes the lowest node to be a DSU-head
                        parents[parentOne] = -(sizeOne + sizeTwo);
                        parents[parentTwo] = parentOne;
                    } else {
                        parents[parentOne] = parentTwo;
                        parents[parentTwo] = -(sizeOne + sizeTwo);
                    }
                }

                /**
                 * <b>Find</b>-part of DSU algorithm
                 *
                 * @param indexParent the index in array {@link #parents}
                 * @return the parent-index ('1' better than the real index) of <b>DSU-root</b>
                 */
                int find(int indexParent) {
                    if (parents[indexParent] > 0) {
                        parents[indexParent] = find(parents[indexParent]);
                        return parents[indexParent];
                    } else {
                        return indexParent;
                    }
                }

                @Override
                public String toString() {
                    return range(1, parents.length).boxed()
                        .collect(groupingBy(this::find)).toString();
                }
            }
        };

        @Override
        public int minCostConnectPoints(int[][] points) {
            // TODO: implement Prim's algorythm
            int[] zeroPoint = points[0];
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p1, p2) ->
                    Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1])
            );
            return 0;
        }

    }

    private static Point point(int[] xy) {
        return point(xy[0], xy[1]);
    }
    private static Point point(int x, int y) {
        return new Point(x, y);
    }
    record Point(int x, int y) {
    }

    private static Line line(Point p1, Point p2) {
        return new Line(p1, p2);
    }
    record Line(Point p1, Point p2) {
        public int distanceM1() {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        }
        @Override
        public String toString() {
            return String.format("Line:{%d;%d},{%d;%d}:%d", p1.x, p1.y, p2.x, p2.y, distanceM1());
        }
    }
}
