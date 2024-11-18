package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/number-of-provinces/description/?envType=problem-list-v2&envId=5lhmb4mj">
 *     547. Number of Provinces
 * </a></h3>
 * There are <b><code>N</code></b> cities. Some of them are connected, while some are not.
 * If city <b><code>a</code></b> is connected directly with city <b><code>b</code></b>,
 * and city <b><code>b</code></b> is connected directly with city <b><code>c</code></b>,
 * then city <b><code>a</code></b> is connected indirectly with city <b><code>c</code></b>.
 * <hr/>
 * A <i>province</i> is a group of directly or indirectly connected cities and no other cities outside the group.
 * <hr/>
 * You are given an <b><code>N x N</code></b> matrix <code>isConnected</code>
 * where <code>isConnected[<b>i</b>][<b>j</b>] = 1</code> if the <b><code>i</code></b>th city
 * and the <b><code>j</code></b>th city are directly connected,
 * and <code>isConnected[<b>i</b>][<b>j</b>] = 0</code> otherwise.
 * <hr/>
 * Return the total number of <i>provinces</i>.
 *
 * @see Problem_200__Number_of_Islands
 */
public interface Problem_547__Number_of_Provinces {

    /**
     * Solution entry-point.
     *
     * @param isConnected binary-matrix of directly connected cities
     * @return the number of provinces (disjoint sets of directly and indirectly connected cities)
     */
    int findCircleNum(int[][] isConnected);

    enum Solution implements Problem_547__Number_of_Provinces {
        DEFAULT;

        @Override
        public int findCircleNum(int[][] isConnected) {
            int minRowLen = Arrays.stream(isConnected).mapToInt(row -> row.length).min().orElseThrow();
            int maxRowLen = Arrays.stream(isConnected).mapToInt(row -> row.length).min().orElseThrow();
            if (minRowLen != maxRowLen || minRowLen != isConnected.length) {
                throw new IllegalArgumentException("'isConnected' matrix is not square");
            }
            final int N = isConnected.length;
            int[] dsuParent = new int[N + 1];
            // dsuParent[0] is un-used, because all direct connections are provided with matrix
            dsuParent[0] = 0;
            // initially other elements of dsuParent[0] represent each city as a standalone province
            Arrays.fill(dsuParent, 1, dsuParent.length, -1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // self-connection is implied and must not be handled additionally
                    if (i != j && isConnected[i][j] != 0) {
                        dsuUnion(dsuParent, i, j);
                    }
                }
            }
            // after processing - the number of negative elements in dsuParent
            // corresponds to the number of provinces and there negative values correspond to sizes
            System.out.println("dsuParent --> " + Arrays.toString(dsuParent));
            return (int)Arrays.stream(dsuParent).filter(par -> par < 0).count();
        }

        void dsuUnion(int[] dsuParent, int i, int j) {
            int iPar = dsuFind(dsuParent, i + 1);
            int jPar = dsuFind(dsuParent, j + 1);
            int iSize = -dsuParent[iPar];
            int jSize = -dsuParent[jPar];
            if (iPar < jPar) {
                dsuParent[iPar] = -(iSize + jSize);
                dsuParent[jPar] = iPar;
            } else if (iPar > jPar) {
                dsuParent[jPar] = -(iSize + jSize);
                dsuParent[iPar] = jPar;
            }
        }

        int dsuFind(int[] dsuParent, int par) {
            if (dsuParent[par] < 0) {
                return par;
            } else {
                dsuParent[par] = dsuFind(dsuParent, dsuParent[par]);
                return dsuParent[par];
            }
        }
    }
}
