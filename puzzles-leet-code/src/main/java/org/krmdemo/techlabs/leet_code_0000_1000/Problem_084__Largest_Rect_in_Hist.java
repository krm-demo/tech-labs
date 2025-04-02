package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

/**
 * TODO: adapt the tests appropriately
 * <h3><a href="https://leetcode.com/problems/largest-rectangle-in-histogram/">
 *     84. Largest Rectangle in Histogram
 * </a></h3>
 * Given <code>N</code> non-negative integers representing the histogram's bar heights,
 * where the width of each bar is <code>1</code>, find <b>the area of the largest rectangle</b> in the histogram.
 *
 * @see Problem_739__Daily_Temperatures (which also uses monotonoic stack)
 */
public interface Problem_084__Largest_Rect_in_Hist {

    /**
     * Solution entry-point.
     *
     * @param heights array of vertical bar heights
     * @return the area of the largest rectangle
     */
    int largestRectangleArea(int[] heights);

    enum Solution implements Problem_084__Largest_Rect_in_Hist {
        /**
         * A <i>brute-force</i> solution is based on enumerating all possible rectangles
         * and come up with time-complexity - <b><code>O(N^2)</code></b>
         */
        BRUTE_FORCE {
            @Override
            public int largestRectangleArea(int[] heights) {
                int largestLeft = 0;
                int largetsRight = 0;
                int largestHeight = 0;
                int largestCount = 0;

                int largestArea = 0;
                long totalDuration = 0;

                if (heights == null || heights.length == 0) {
                    return 0;
                }

                long tmStart = System.nanoTime();

                for (int left = 0; left < heights.length; left++) {
                    for (int right = left+1; right <= heights.length; right++) {
                        int width = right - left;
                        int height = Arrays.stream(heights, left, right).min().orElse(0);
                        int area = width * height;
                        if (area > 0 && largestArea <= area) {
                            if (largestArea == area) {
                                largestCount++;
                            } else {
                                largestCount = 1;
                                largestArea = area;
                                largestLeft = left;
                                largetsRight = right;
                                largestHeight = height;
                            }
                        }
                    }
                }

                long tmEnd = System.nanoTime();
                totalDuration += (tmEnd - tmStart);

                return largestArea;
            }
        },
        /**
         * A monotonic stack approach allows to achieve the linear time-complexity - <b><code>O(N)</code></b>
         */
        MONOTONIC_STACK {
            @Override
            public int largestRectangleArea(int... heights) {
                int largestLeft = 0;
                int largetsRight = 0;
                int largestHeight = 0;
                int largestCount = 0;

                int largestArea = 0;
                long totalDuration = 0;

                if (heights == null || heights.length == 0) {
                    return 0;
                }

                long tmStart = System.nanoTime();

                Deque<Integer> previousBarNums = new ArrayDeque<>(heights.length);
                Deque<Integer> previousBarHeights = new ArrayDeque<>(heights.length);
                for (int right = 0; right <= heights.length; right++) {
                    int currentHeight = (right == heights.length) ? 0 : heights[right];
                    if (isBiggerThanTop(currentHeight, previousBarHeights)) {
                        previousBarNums.addLast(right);
                        previousBarHeights.addLast(currentHeight);
                    } else {
                        int left = right;
                        while (isSmallerThanTop(currentHeight, previousBarHeights)) {
                            left = previousBarNums.removeLast();
                            int height = previousBarHeights.removeLast();
                            int area = (right - left) * height;
                            if (largestArea <= area) {
                                if (largestArea == area) {
                                    largestCount++;
                                } else {
                                    largestCount = 1;
                                    largestArea = area;
                                    largestLeft = left;
                                    largetsRight = right;
                                    largestHeight = height;
                                }
                            }
                        }
                        if (isBiggerThanTop(currentHeight, previousBarHeights)) {
                            previousBarNums.addLast(left);
                            previousBarHeights.addLast(currentHeight);
                        }
                    }
                }

                long tmEnd = System.nanoTime();
                totalDuration += (tmEnd - tmStart);

                return largestArea;
            }

            private static boolean isBiggerThanTop(int height, Deque<Integer> barHeights) {
                return height > 0 && (barHeights.isEmpty() || height > barHeights.peekLast());
            }

            private static boolean isSmallerThanTop(int height, Deque<Integer> barHeights) {
                return !barHeights.isEmpty() && height < barHeights.peekLast();
            }
        }
    };

//    // ---------------------------------------------------------------------------------
//
//    @Override
//    public boolean equals(Object thatObj) {
//        if (this == thatObj) return true;
//        if (thatObj == null || getClass() != thatObj.getClass()) return false;
//        Solution that = (Solution) thatObj;
//        return largestArea == that.largestArea
//            && largestCount == that.largestCount
//            && largestLeft == that.largestLeft
//            && largetsRight == that.largetsRight
//            && largestHeight == that.largestHeight;
//    }
//
//    // ---------------------------------------------------------------------------------
//    // ---------------------------------------------------------------------------------
//
//
//    private static void printHistogram(int[] heights) {
//        printHistogram(heights, 0, 0, 0);
//    }
//
//    private static void printHistogram(int[] heights, int rectStart, int rectEnd, int rectHeight) {
//        if (heights == null || heights.length == 0 || heights.length >= 100) {
//            throw new IllegalArgumentException("Invalid heights length: " + (heights == null ? null : heights.length));
//        }
//        out.println(horizontalSeparator(heights));
//        int min = stream(heights).min().orElse(0);
//        int max = stream(heights).max().orElse(0);
//        if (min < 0 || max >= 100) {
//            throw new IllegalArgumentException("Invalid heights range [ " + min + "; " + max + " ]");
//        }
//        rangeClosed(0, max).boxed().sorted(reverseOrder()).forEach(line -> {
//            out.println(range(0, heights.length).mapToObj(barNum -> {
//                int h = heights[barNum];
//                if (h == line) {
//                    return format("%2d", h);
//                } else if (rectStart <= barNum && barNum < rectEnd && rectHeight > line) {
//                    return "XX";
//                } else if (h > line) {
//                    return "##";
//                } else {
//                    return "  ";
//                }
//            }).collect(joining(" ", "|", "|")));
//        });
//        out.println(horizontalSeparator(heights));
//        out.println(footer(heights));
//        out.println(horizontalSeparator(heights));
//        out.println();
//    }
//
//    private static String horizontalSeparator(int[] heights) {
//        return stream(heights)
//            .mapToObj(i -> "--")
//            .collect(joining("+", "+", "+"));
//    }
//
//    private static String footer(int[] heights) {
//        return range(0, heights.length)
//            .mapToObj(barNum -> format("%2d", barNum))
//            .collect(joining(" ", "|", "|"));
//    }
//
//    /**
//     * JVM entry-point
//     *
//     * @param args command-line arguments
//     */
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        Solution solution_O2 = new Solution();
//
//        final int TOTAL_ATTEMPTS = 1_000_000;
//        Random random = new Random(0);
//        for (int attemptNum = 1; attemptNum <= TOTAL_ATTEMPTS; attemptNum++) {
//            int size = random.nextInt(72);
//            int[] heights = random.ints(size, 0, 34).toArray();
//            if (solution.largestRectangleArea(heights) != solution_O2.largestRectangleAreaO2(heights)
//                || solution.largestCount != solution_O2.largestCount) {
//                out.printf("#%,6d) Results are: %,6d != %,d.   Counts are: %,d != %,d%n", attemptNum,
//                    solution.largestArea, solution_O2.largestArea,
//                    solution.largestCount, solution_O2.largestCount);
//                out.println();
//                out.println("Stack-based solution - largestArea is " + solution.largestArea + " :");
//                printHistogram(heights, solution.largestLeft, solution.largetsRight, solution.largestHeight);
//                out.println("O^2-based solution -    largestArea is " + solution_O2.largestArea + " :");
//                printHistogram(heights, solution_O2.largestLeft, solution_O2.largetsRight, solution_O2.largestHeight);
//                out.println();
//                out.println("Errors are detected !!!");
//                return;
//            }
//            if (attemptNum % (TOTAL_ATTEMPTS / 10) == 0 || solution.largestCount > 5) {
//                out.printf("So far, so good! %,6d of %,d:%n", attemptNum, TOTAL_ATTEMPTS);
//                if (heights.length > 0) {
//                    out.printf("... %,d largest rectangles are detected of size %,d%n",
//                        solution.largestCount, solution.largestArea);
//                    printHistogram(heights, solution.largestLeft, solution.largetsRight, solution.largestHeight);
//                    if (heights.length < 20) {
//                        out.printf("--- worth to trying: heights.length = %d ---%n", heights.length);
//                        Solution solutionDebug = new Solution();
//                        solutionDebug.largestRectangleArea(heights);
//                        out.printf("Stack-based solutionDebug duration: %,17d nanos%n", solutionDebug.totalDuration);
//                        out.println("--------------------------------------------");
//                    }
//                } else {
//                    out.println("<< input array of heights is empty >>");
//                    out.println();
//                }
//            }
//        }
//
//        out.println("Done!");
//        out.printf("Stack-based total duration: %,17d nanos%n", solution.totalDuration);
//        out.printf("O^2-based total duration:   %,17d nanos%n", solution_O2.totalDuration);
//    }
}
