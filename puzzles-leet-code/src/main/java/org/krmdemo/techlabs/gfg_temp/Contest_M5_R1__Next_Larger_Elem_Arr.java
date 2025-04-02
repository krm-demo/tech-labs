package org.krmdemo.techlabs.gfg_temp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.IntStream.range;

/**
 * TODO: convert to appropriate way (interface + enum + @ParameterizedTest)
 * <a href="https://practice.geeksforgeeks.org/contest/mock-5-round-10751/problems">
 *     <h3>Next Larger Element of Array</h3>
 * </a>
 * Given an array <b><code>arr[]</code></b> of integers, the task is to find the <b>next greater element</b> (NGE)
 * for each element of the array in order of their appearance in the array.
 * Next greater element of an element in the array is the nearest element on the right,
 * which is greater than the current element. If there does not exist next greater of current element,
 * then next greater element for current element is <b><code>-1</code></b>.
 * For example, next greater of the last element is always <b><code>-1</code></b>.
 *
 * @see <a href="https://www.geeksforgeeks.org/next-greater-element/">
 *      Next Greater Element (NGE) for every element in given Array
 * </a>
 * @see <a href="https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1">
 *      Next Greater Element
 * </a>
 * @see Problem__Next_Larger_Elem_Arr_Circle
 * @see my.leetcode.algo_739__daily_temperatures.Solution
 * @see my.leetcode.algo_503__next_greater_elem_ii.Solution
 */
@SuppressWarnings("NewClassNamingConvention")
public class Contest_M5_R1__Next_Larger_Elem_Arr {

    private static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public static void initOutput() {
        outputStream = new ByteArrayOutputStream();
    }

    public static String contentOut() {
        return outputStream == null ? "" : outputStream.toString();
    }

    public static PrintStream out() {
        return new PrintStream(outputStream);
    }

    public static void nextLargerElement(long[] arr) {
        nextLargerElement(arr, arr.length);
    }

    /**
     * Entry point of <a href="https://www.geeksforgeeks.org/">GFG</a>-Puzzle
     *
     * @param arr an array of integers (the result should be stored <i>in-place</i> - in the same input array)
     * @param N the size of input array <code>arr</code> (usual redundant param for arrays at GFG)
     */
    public static void nextLargerElement(long[] arr, int N) {
        System.out.println("nextLargerElement: N = " + N + "; arr --> " + Arrays.toString(arr));
        IndexedLongValueList ivList = new IndexedLongValueList(arr);
        ivList.calculateNextIncr();
        System.out.println("nextLargerValues --> " + ivList.nextLargerValues(-1).boxed().toList());
        ivList.nextLargerValues(-1).forEach(value -> out().print(value + " "));
    }

    static class IndexedLongValueList {
        final List<IndexedValue> indexedValueList = new ArrayList<>();
        class IndexedValue {
            final int index;
            final long value;
            IndexedValue nextIncr;
            IndexedValue(long value) {
                this.index = indexedValueList.size();
                this.value = value;
                indexedValueList.add(this);
            }
        }
        IndexedLongValueList(long[] valuesArr) {
            this(Arrays.stream(valuesArr));
        }
        IndexedLongValueList(LongStream values) {
            values.forEach(IndexedValue::new);
        }
        public void calculateNextIncr() {
            Deque<IndexedValue> monoDescStack = new ArrayDeque<>(); // <-- monotonically descending stack
            for (IndexedValue current : indexedValueList) {
                if (monoDescStack.isEmpty()) {
                    monoDescStack.push(current);
                    continue;
                }
                while (!monoDescStack.isEmpty()
                    && monoDescStack.peek().value < current.value) {
                    monoDescStack.pop().nextIncr = current;
                }
                monoDescStack.push(current);
            }
        }
        public LongStream nextLargerValues(long defaultValue) {
            return indexedValueList.stream()
                .mapToLong(iv -> iv.nextIncr == null ? defaultValue : iv.nextIncr.value);
        }
    }

/*
    // ------------------------------------------------------------------------------------------
    //                            Test-Cases' data and methods:
    // ------------------------------------------------------------------------------------------
    final RandomHelper rnd = new RandomHelper(567);

    @Before
    public void before() {
        initOutput();
    }

    @Test
    public void test_req_01() {
        long[] arr = new long[] { 1, 3, 2, 4 };
        nextLargerElement(arr);
        System.out.println("---- content of 'test_req_01' output: ----");
        System.out.println(contentOut());
        assertThat(contentOut().trim(), equalTo("3 4 4 -1"));
    }

    @Test
    public void test_req_02() {
        long[] arr = new long[] { 6, 8, 0, 1, 3 };
        nextLargerElement(arr);
        System.out.println("---- content of 'test_req_02' output: ----");
        System.out.println(contentOut());
        assertThat(contentOut().trim(), equalTo("8 -1 1 3 -1"));
    }

    @Test
    public void test_MatrixMinRow() {
        final int K = 7;
        final int N = 23;
        int[][] matrix = range(0, K).mapToObj(k ->
            rnd.randomSortedIntArr(N, 0, 100)
        ).toArray(int[][]::new);
        System.out.println(dumpMatrix(matrix));

        List<int[]> rowsList = Arrays.asList(matrix);
        int[] minRow = Collections.min(rowsList, Comparator.comparingInt(this::arraySum));
        System.out.println("min row --> " + Arrays.toString(minRow));
        int[] moreOnesRow = Collections.max(rowsList, Comparator.comparingInt(this::countMoreThat90));
        System.out.println("moreOnesRow --> " + Arrays.toString(moreOnesRow));
    }

    private int arraySum(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    private int countMoreThat90(int[] arr) {
        return (int) Arrays.stream(arr).filter(cell -> cell > 90).count();
    }

    private String dumpMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
            .map(row -> arraySum(row) + ": " + Arrays.toString(row)).collect(
                Collectors.joining(System.lineSeparator()));
    }
*/
}
