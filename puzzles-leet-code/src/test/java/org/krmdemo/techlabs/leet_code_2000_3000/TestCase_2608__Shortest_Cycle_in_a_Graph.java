package org.krmdemo.techlabs.leet_code_2000_3000;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_2608__Shortest_Cycle_in_a_Graph}
 */
public class TestCase_2608__Shortest_Cycle_in_a_Graph {

    final Problem_2608__Shortest_Cycle_in_a_Graph sln =
        Problem_2608__Shortest_Cycle_in_a_Graph.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        // (0) -- (1)  (3) -- (4)
        //     \  |    |      |
        //      (2)   (6) -- (5)
        // Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
        // Output: 3
        // Explanation: The cycle with the smallest length is : 0 -> 1 -> 2 -> 0
        final int N = 7;
        final int[][] edges = new int[][] {
            {0,1}, {1,2}, {2,0}, {3,4}, {4,5}, {5,6}, {6,3}
        };
        assertThat(sln.findShortestCycle(N, edges)).isEqualTo(0);
    }

    @Test
    void test_ex_02() {
        // Input: n = 4, edges = [[0,1],[0,2]]
        // Output: -1
        // Explanation: There are no cycles in this graph.

    }

    /**
     * @see <a href="https://www.geeksforgeeks.org/shortest-cycle-in-an-undirected-unweighted-graph/">
     *     Shortest cycle in an undirected unweighted graph
     * </a>
     */
    @Test
    void test_GFG_article_Shotest_Cycle() {
        // (3) -- (2) -- (6) -- (0)
        //  |         /       /
        // (4) -- (1) ---- (5)
        // Input: n = 7, edges = [[3,2],[3,4],[2,6],[1,6],[1,5],[1,4],[5,0],[6,0]]
        // Output: 4
        // Explanation: The cycle with the smallest length is : 0 -> 1 -> 2 -> 0
        final int N = 7;
        final int[][] edges = new int[][] {
            {3,2}, {3,4}, {2,6}, {1,6}, {1,5}, {1,4}, {5,0}, {6,0}
        };
        assertThat(sln.findShortestCycle(N, edges)).isEqualTo(0);
    }
}
