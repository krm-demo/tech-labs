package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.range;

/**
 * <h3><a href="https://leetcode.com/problems/graph-valid-tree/">
 *     261. Graph Valid Tree
 * </a></h3>
 * You have a graph of <b><code>N</code></b> nodes. You are given an integer <b><code>n</code></b>
 * and an array <b><code>edges</code></b> where <code>edges[<b>i</b>] = [a<b>i</b>, b<b>i</b>]</code> indicates
 * that there is an edge between <code>a<b>i</b></code> and <code>b<b>i</b></code> in the graph.
 * <hr/>
 * Return the number of connected components in the graph.
 * <h5>Constraints:</h5><pre>
 *        1 <= n <= 2000
 * 0 <= edges.length <= 5000
 *    edges[i].length == 2
 *     0 <= ai, bi < n
 *        ai != bi
 * </pre>
 * There are no self-loops or repeated edges.
 *
 * @see Problem_547__Number_of_Provinces
 * @see Problem_200__Number_of_Islands
 * @see Problem_261__Graph_Valid_Tree
 * @see <a href="https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/">
 *     Introduction to Disjoint Set (Union-Find Algorithm)
 * </a>
 */
public interface Problem_323__Number_of_Connected_SubGraphs {

    /**
     * Solution entry-point.
     *
     * @param N the number of nodes in a graph
     * @param edges the array of un-directed edges
     * @return the number of connected components in the graph
     */
    int countComponents(int N, int[][] edges);

    enum Solution implements Problem_323__Number_of_Connected_SubGraphs {
        DEFAULT;

        @Override
        public int countComponents(int N, int[][] edgesArr) {
            DSU dsu = new DSU(N);
            Arrays.stream(edgesArr).forEach(dsu::union);
            System.out.println("DSU --> " + dsu);
            return dsu.countRoots();
        }
    }

    /**
     * This class encapsulates the storage of DSU-parents
     * and all main parts of Union-Find (DSU) algorithm.
     * <hr/>
     * For the passed pair of nodes (edge) - detect their DSU-parent and unite corresponding DSU-sets.
     * In this puzzle the DSU-parents contains the negative value of minimum index in DSU-set.
     * <hr/>
     * <small>
     * Among all strategies around that it's worth to mention following:<ul>
     *     <li>the negative value could represent the size of child group (like here, in this puzzle)</li>
     *     <li>the negative value could represent the rank (length of maximum path) of child group</li>
     *     <li>the negative value is just an indicator that the node is a root of disjoint union subset</li>
     *     <li>the positive value is a reference to parent</li>
     *     <li>zero-value is unused</li>
     * </ul>
     * </small>
     */
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
}
