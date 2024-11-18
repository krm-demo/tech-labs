package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     200. Number of Islands
 * </a></h3>
 * Given an m x n 2D binary-matrix <b><code>grid</code></b>,
 * which represents a map of <code>'1'</code>s (land) and <code>'0'</code>s (water),
 * return the number of islands.
 * <hr/>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically
 * (only <b>4 directions</b> to neighbours, <b>no diagonal</b> connections like in other similar puzzles).
 * <hr/>
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * @see Problem_547__Number_of_Provinces
 */
public interface Problem_200__Number_of_Islands {

    /**
     * Solution entry-point.
     *
     * @param grid binary-matrix of "land" and "water"
     * @return the number of islands (connecting adjacent piece of lands)
     */
    int numIslands(char[][] grid);

    enum Solution implements Problem_200__Number_of_Islands {
        DEFAULT;

        @Override
        public int numIslands(char[][] gridData) {

            class Grid {
                final int N = gridData.length;
                final int M = gridData[0].length;
                int size() {
                    return M * N;
                }
                int cellID(int row, int col) {
                    if (row < 0 || row >= N || col < 0 || col >= M) {
                        return -1;
                    }
                    return row * M + col;
                }
                int cellID(int row, int col, GridDelta delta) {
                    return cellID(row + delta.deltaRow, col + delta.deltaCol);
                }
                void dumpDSU(DSU dsu) {
                    List<Integer> dsuParentsList = Arrays.stream(dsu.parents).boxed().toList();
                    for (int row = 0; row < N; row++) {
                        List<Integer> dsuRow = dsuParentsList.subList(
                            row * M + 1,
                            (row + 1) * M + 1
                        );
                        System.out.println(dsuRow);
                    }
                }
            }

            int minRowLen = Arrays.stream(gridData)
                .mapToInt(row -> row.length)
                .min().orElseThrow();
            int maxRowLen = Arrays.stream(gridData)
                .mapToInt(row -> row.length)
                .max().orElseThrow();
            if (minRowLen != maxRowLen) {
                throw new IllegalArgumentException("'grid' is not rectangular");
            }
            Grid grid = new Grid();
            final DSU dsu = new DSU(grid.size());
            for (int row = 0; row < grid.N; row++) {
                for (int col = 0; col < grid.M; col++) {
                    if (gridData[row][col] == '0') {
                        continue; // skip 'water'
                    }
                    int cellID = grid.cellID(row, col);
                    dsu.addSingle(cellID);
                    for (GridDelta delta : NEIGHBOURS_BEFORE) {
                        dsu.dsuUnion(cellID, grid.cellID(row, col, delta));
                    }
                }
            }
//            System.out.println("... finally dumpDSU: ...");
//            grid.dumpDSU(dsu);
            return dsu.parentsNumber();
        }
    }

    /**
     * This enumeration represents the steps to potential neighbours
     */
    enum GridDelta {
        NORTH_WEST(-1, -1), NORTH(-1, 0), NORTH_EAST(-1, 1),
        WEST( 0, -1),                     EAST( 0, 1),
        SOUTH_WEST( 1, -1), SOUTH( 1, 0), SOUTH_EAST( 1, 1);
        GridDelta(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }
        final int deltaRow;
        final int deltaCol;
    }

    /**
     * In current puzzle only vertical and horizontal neighbours are connected.
     * This enum-set contains only backward directions - to potential neighbours,
     * on a forward iteration through the Grid ( top --> down, left --> right )
     */
    EnumSet<GridDelta> NEIGHBOURS_BEFORE = EnumSet.of(
        GridDelta.NORTH, GridDelta.WEST
    );

    /**
     * This class encapsulates the storage of DSU-parents
     * and all main parts of Union-Find (DSU) algorithm.
     */
    class DSU {
        final int[] parents;
        DSU(int elementsNumber) {
            this.parents = new int[elementsNumber + 1];
            // initially there's no information about any elements
            // ('0' value - means 'no info')
            // and the element with zero-index is un-used
        }
        void addSingle(int i) {
            parents[i+1] = -1;
        }
        void dsuUnion(int i, int j) {
            if (i < 0 || j < 0) {
                // out of grid boundaries
                return;
            }
            int iPar = dsuFind(i + 1);
            int jPar = dsuFind(j + 1);
            if (iPar == 0 || jPar == 0) {
                // skip 'water'
                return;
            }
            int iSize = -parents[iPar];
            int jSize = -parents[jPar];
            if (iPar < jPar) {
                parents[iPar] = -(iSize + jSize);
                parents[jPar] = iPar;
            } else if (iPar > jPar) {
                parents[jPar] = -(iSize + jSize);
                parents[iPar] = jPar;
            }
        }
        int dsuFind(int par) {
            if (parents[par] < 0) {
                return par;
            } else if (parents[par] > 0) {
                parents[par] = dsuFind(parents[par]);
                return parents[par];
            } else {
                return 0;
            }
        }
        int parentsNumber() {
            return (int)Arrays.stream(parents).filter(par -> par < 0).count();
        }
    }
}
