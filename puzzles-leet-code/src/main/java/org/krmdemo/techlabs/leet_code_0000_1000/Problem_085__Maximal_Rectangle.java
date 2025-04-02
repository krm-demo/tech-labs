package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.range;

/**
 * TODO: adapt the tests appropriately
 * <h3><a href="https://leetcode.com/problems/maximal-rectangle">
 *     85. Maximal Rectangle
 * </a></h3>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * <p>
 * For example, given the following matrix:<pre>
 * 1 0 1 0 0
 * 1 0 <b color="red">1 1 1</b>
 * 1 1 <b color="red">1 1 1</b>
 * 1 0 0 1 0
 * </pre>
 * Return <b color="red">6</b>.
 *
 * @see Problem_084__Largest_Rect_in_Hist
 */
public interface Problem_085__Maximal_Rectangle {

    /**
     * Solution entry-point.
     *
     * @param matrix 2-D array of chars (either <code>'0'</code> or <code>'1'</code>)
     * @return the area of the maximal rectangle that consist of <code>'1'</code> chars only
     */
    int maximalRectangle(char[][] matrix);

    enum Solution implements Problem_085__Maximal_Rectangle {
        /**
         * A <i>brute-force</i> solution is based on enumerating all possible rectangles
         * and come up with time-complexity - <b><code>O(N^4)</code></b>
         */
        BRUTE_FORCE {
            @Override
            public int maximalRectangle(char[][] matrix) {
                // TODO: convert existing solution properly
                return 0;
            }
        },
        /**
         * This approach is based on previously resolved puzzle for maximum rectangle in histogram
         * @see Problem_084__Largest_Rect_in_Hist.Solution#MONOTONIC_STACK
         */
        HISTOGRAM_BASED {
            @Override
            public int maximalRectangle(char[][] matrix) {
                // TODO: convert existing solution properly
                return 0;
            }
        };
    };

//    int rowUpMax = 0, rowDownMax = 0,
//        colLeftMax = 0, colRightMax = 0,
//        areaMax = 0, maxCount = 0;
//
//    private long totalAreaDuration = 0;
//
//
//    /**
//     * Histogram-based Solution entry-point
//     *
//     * @param matrix 2-D array of chars (either '0' or '1')
//     * @return the area of the maximal rectangle that consist of '1' chars only
//     */
//    public int maximalRectangle(char[][] matrix) {
//        if (matrix.length == 0 || matrix[0].length == 0) {
//            return 0;
//        }
//        final int ROW_NUM = matrix.length;
//        final int COL_NUM = matrix[0].length;
//        if (stream(matrix).filter(row -> row.length != COL_NUM).count() > 0) {
//            throw new IllegalArgumentException("Matrix is inconsistent horizontally");
//        }
//
//        long tmStart = System.nanoTime();
//
//        rowUpMax = rowDownMax = colLeftMax = colRightMax = areaMax = maxCount = 0;
//        final int[] rollingHistogram = generate(() -> 0).limit(COL_NUM).toArray();
//        for (int row = 0; row < ROW_NUM; row++) {
//            for (int col = 0; col < COL_NUM; col++) {
//                switch (matrix[row][col]) {
//                    case '0': rollingHistogram[col] = 0; break;
//                    case '1': rollingHistogram[col]++;   break;
//                    default : throw new IllegalArgumentException(
//                        format("Invalid char '%c' in matrix[%d,%d]", matrix[row][col], row, col));
//                }
//            }
//            int area = largestRectangleArea(rollingHistogram);
//            if (area >= areaMax) {
//                if (area == areaMax) {
//                    maxCount += largestCount;
//                } else {
//                    maxCount = largestCount;
//                    areaMax = area;
//                    rowDownMax = row + 1;
//                    rowUpMax = rowDownMax - super.largestHeight;
//                    colLeftMax = super.largestLeft;
//                    colRightMax = super.largetsRight;
//                }
//            }
//        }
//
//        long tmEnd = System.nanoTime();
//        totalAreaDuration += (tmEnd - tmStart);
//
//        return areaMax;
//    }
//
//    // ---------------------------------------------------------------------------------
//
//    /**
//     * Alternate O^2 solution entry-point
//     *
//     * @param matrix 2-D array of chars (either '0' or '1')
//     * @return the area of the maximal rectangle that consist of '1' chars only
//     */
//    public int maximalRectangleO2(char[][] matrix) {
//        if (matrix.length == 0 || matrix[0].length == 0) {
//            return 0;
//        }
//
//        long tmStart = System.nanoTime();
//
//        rowUpMax = rowDownMax = colLeftMax = colRightMax = areaMax = maxCount = 0;
//        final int ROW_NUM = matrix.length;
//        final int COL_NUM = matrix[0].length;
//        for (int rowUp = 0; rowUp < ROW_NUM; rowUp++) {
//            for (int colLeft = 0; colLeft < COL_NUM; colLeft++) {
//                int colMax = COL_NUM;
//                for (int rowDown = rowUp+1; rowDown <= ROW_NUM && colLeft < colMax; rowDown++) {
//                    for (int colRight = colLeft+1; colRight <= colMax; colRight++) {
//                        if (matrix[rowDown-1][colRight-1] != '1') {
//                            colMax = colRight-1;
//                            break;
//                        }
//                        int area = (rowDown - rowUp) * (colRight - colLeft);
//                        if (area >= areaMax) {
//                            if (area == areaMax) {
//                                maxCount++;
//                            } else {
//                                maxCount = 1;
//                                areaMax = area;
//                                rowUpMax = rowUp;
//                                rowDownMax = rowDown;
//                                colLeftMax = colLeft;
//                                colRightMax = colRight;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        long tmEnd = System.nanoTime();
//        totalAreaDuration += (tmEnd - tmStart);
//
//        return areaMax;
//    }
//
//    // ---------------------------------------------------------------------------------
//
//    private boolean withinMaxArea(int row, int col) {
//        return row >= rowUpMax && row < rowDownMax && col >= colLeftMax && col < colRightMax;
//    }
//
//    @Override
//    public boolean equals(Object thatObj) {
//        if (this == thatObj) return true;
//        if (thatObj == null || getClass() != thatObj.getClass()) return false;
//        Solution that = (Solution) thatObj;
//        return areaMax == that.areaMax
//            && maxCount == that.maxCount
//            && rowUpMax == that.rowUpMax
//            && rowDownMax == that.rowDownMax
//            && colLeftMax == that.colLeftMax
//            && colRightMax == that.colRightMax;
//    }
//
//    // ---------------------------------------------------------------------------------
//    // ---------------------------------------------------------------------------------
//
//    private static boolean SUPPRESS__PRINTLN_MATRIX = false;
//
//    private static void printMatrix(char[][] matrix) {
//        printMatrix(matrix, null);
//    }
//
//    private static void printMatrix(char[][] matrix, Solution solution) {
//        if (SUPPRESS__PRINTLN_MATRIX) {
//            return;
//        }
//        if (matrix == null || matrix.length == 0 || matrix.length >= 100) {
//            throw new IllegalArgumentException("Matrix is invalid or too big vertically");
//        }
//        int ROW_NUM = matrix.length;
//        int COL_NUM = matrix[0].length;
//        if (COL_NUM >= 100 || stream(matrix).filter(row -> row.length != COL_NUM).count() > 0) {
//            throw new IllegalArgumentException("Matrix is too big or incosistent horizontally");
//        }
//
//        out.println(horizontalSeparator(COL_NUM));
//        out.println(header(COL_NUM));
//        out.println(horizontalSeparator(COL_NUM));
//
//        AtomicInteger errorsCount = new AtomicInteger(0);
//        range(0, ROW_NUM).forEach(row -> {
//            out.println(range(0, COL_NUM).mapToObj(col -> {
//                if (solution != null && solution.withinMaxArea(row, col)) {
//                    if (matrix[row][col] == '1') {
//                        return "XX";
//                    }  else {
//                        errorsCount.incrementAndGet();
//                        return "00";
//                    }
//                } else if (matrix[row][col] == '1') {
//                    return "##";
//                } else {
//                    return "  ";
//                }
//            }).collect(joining(" ", format("| %2d |", row), "|")));
//        });
//
//        out.println(horizontalSeparator(COL_NUM));
//        if (errorsCount.get() > 0) {
//            err.println(matrixAsDeclaration(matrix));
//            throw new IllegalStateException(format("%,d errors are discovered!!!", errorsCount.get()));
//        }
//        out.println();
//    }
//
//    private static String horizontalSeparator(int COL_NUM) {
//        return range(0, COL_NUM)
//            .mapToObj(i -> "--")
//            .collect(joining("+", "+----+", "+"));
//    }
//
//    private static String header(int COL_NUM) {
//        return range(0, COL_NUM)
//            .mapToObj(col -> format("%2d", col))
//            .collect(joining(" ", "|    |", "|"));
//    }
//
//    private static String matrixAsDeclaration(char[][] matrix) {
//        return stream(matrix).map(row -> String.valueOf(row).chars()
//            .mapToObj(ch -> format("'%c'", ch))
//            .collect(joining(", ",
//                "            { ",
//                " }"))
//        ).collect(joining(",\n",
//            "        char[][] matrix = {\n",
//            "\n        };\n"));
//    }
//
//    // ---------------------------------------------------------------------------------
//    // ---------------------------------------------------------------------------------
//
//    private static class Stats {
//        public long totalO2 = 0;
//        public long totalHisto = 0;
//        public double averageO2 = 0;
//        public double averageHisto = 0;
//        public double ratio = 1.0;
//    }
//
//    private static Stats evaluatePerformance(int density, int ROW_NUM, int COL_NUM) {
//        out.println("-----------------------------------------------------------------");
//        out.println("Start evaluating the peformance:");
//        out.println("density = " + density);
//        out.println("ROW_NUM = " + ROW_NUM);
//        out.println("COL_NUM = " + COL_NUM);
//        Stats stats = new Stats();
//        Solution solution = new Solution();
//        Solution solution_O2 = new Solution();
//
//        SUPPRESS__PRINTLN_MATRIX = true;
//        final int TOTAL_ATTEMPTS = 10_000;
//        Random random = new Random(0);
//        for (int attemptNum = 1; attemptNum <= TOTAL_ATTEMPTS; attemptNum++) {
//            char[][] matrixRandom = new char[ROW_NUM][COL_NUM];
//            for (int i = 0; i < ROW_NUM*COL_NUM; i++) {
//                matrixRandom[i/COL_NUM][i%COL_NUM] = random.nextInt(density) == 0 ? '0' : '1';
//            }
//            if (solution.maximalRectangle(matrixRandom) != solution_O2.maximalRectangleO2(matrixRandom)
//                || solution.maxCount != solution_O2.maxCount) {
//                out.printf("#%,d) solution ( %,d X %,d ) <> solution_O2 ( %,d X %,d )%n", attemptNum,
//                    solution.areaMax, solution.maxCount,
//                    solution_O2.areaMax, solution_O2.maxCount);
//                out.println();
//                out.println("Histogram-based solution:");
//                printMatrix(matrixRandom, solution);
//                out.println("O^2-based solution:");
//                printMatrix(matrixRandom, solution_O2);
//                out.println();
//                out.println("Errors are detected !!! ");
//                out.println("Try following input matrix to debug:");
//                out.println();
//                out.println(matrixAsDeclaration(matrixRandom));
//                throw new IllegalStateException("Results are different !!!");
//            }
//            if (attemptNum % (TOTAL_ATTEMPTS / 10) == 0 || solution.maxCount >= 15) {
//                out.printf("So far, so good! %,6d of %,d: ", attemptNum, TOTAL_ATTEMPTS);
//                if (matrixRandom.length > 0) {
//                    out.printf("... %,d largest rectangles are detected of size %,d%n",
//                        solution.maxCount, solution.areaMax);
//                    printMatrix(matrixRandom, solution);
//                } else {
//                    out.println("<< input array of heights is empty >>");
//                    out.println();
//                }
//            }
//        }
//        stats.totalHisto = solution.totalAreaDuration;
//        stats.totalO2 = solution_O2.totalAreaDuration;
//        stats.averageHisto = 1.0 * solution.totalAreaDuration / TOTAL_ATTEMPTS;
//        stats.averageO2 = 1.0 * solution_O2.totalAreaDuration / TOTAL_ATTEMPTS;
//        stats.ratio = 1.0 * stats.totalO2 / stats.totalHisto;
//
//        out.println("Done!");
//        out.printf("Histogram-based total duration: %,17d nanos%n", solution.totalAreaDuration);
//        out.printf("O^2-based total duration:       %,17d nanos%n", solution_O2.totalAreaDuration);
//
//        out.println();
//        out.printf("Histogram-based average duration: %,.2f nanos%n", 1.0 * solution.totalAreaDuration / TOTAL_ATTEMPTS);
//        out.printf("O^2-based average duration:       %,.2f nanos%n", 1.0 * solution_O2.totalAreaDuration / TOTAL_ATTEMPTS);
//        out.printf("ratio is %,.3f%n", 1.0 * solution_O2.totalAreaDuration /solution.totalAreaDuration);
//
//        out.println();
//        return stats;
//    }
//
//    /**
//     * JVM entry-point
//     *
//     * @param args command-line arguments
//     */
//    public static void main(String[] args) {
//        char[][] matrix = {
//            { '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '0', '0', '1', '1', '0', '1', '1', '0' },
//            { '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1' },
//            { '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '1', '1' },
//            { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1' },
//            { '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1' },
//            { '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
//            { '1', '0', '1', '0', '0', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1' },
//            { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '0' },
//            { '0', '1', '0', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1' },
//            { '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0' },
//            { '1', '1', '0', '1', '1', '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '0' },
//            { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
//            { '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1' },
//            { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '1', '0' },
//            { '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '0', '0', '1', '0', '1', '1', '1' },
//            { '1', '1', '0', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
//            { '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '0' },
//            { '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1' },
//            { '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '1' }
//        };
//        out.println("before finding maximal rectangle:");
//        printMatrix(matrix);
//
//        Solution solution_O2 = new Solution();
//        out.printf("after finding maximal rectangle with O^2 approach ( area = %,d; count = %d ) :%n",
//            solution_O2.maximalRectangleO2(matrix), solution_O2.maxCount);
//        printMatrix(matrix, solution_O2);
//
//        Solution solution = new Solution();
//        out.printf("after finding maximal rectangle with main approach ( area = %,d; count = %d ) :%n",
//            solution.maximalRectangle(matrix), solution.maxCount);
//        printMatrix(matrix, solution);
//
//        int density = 100;
//        int[] ROW_NUMs = { 10, 20, 30, 40, 50, 100, 200 };
//        int[] COL_NUMs = { 10, 20, 30, 40, 50, 100, 200 };
//        Stats[][] matrixStats = new Stats[ROW_NUMs.length][COL_NUMs.length];
//        for (int i = 0; i < ROW_NUMs.length; i++)
//            for (int j = 0; j < COL_NUMs.length; j++)
//                matrixStats[i][j] = evaluatePerformance(density, ROW_NUMs[i], COL_NUMs[j]);
//
//        out.println("-----------------------------------------------------------------");
//        out.println("  density = " + density + " : " + stream(ROW_NUMs).boxed().toList());
//        out.println("-----------------------------------------------------------------");
//        for (int i = 0; i < ROW_NUMs.length; i++) {
//            for (int j = 0; j < COL_NUMs.length; j++) {
//                out.printf("%,9.3f", matrixStats[i][j].ratio);
//            }
//            out.println();
//        }
//    }
}
