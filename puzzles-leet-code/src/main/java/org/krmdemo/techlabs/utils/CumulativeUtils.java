package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility-Class to build Java-streams that accumulate and emit following aggregations:<dl>
 *     <dt>{@link #cumSum(int...)}</dt>
 *     <dd>sum of all previous elements (excluding the current one), which is similar to NumPy's function
 *          <a href="https://numpy.org/doc/2.0/reference/generated/numpy.cumsum.html">numpy.cumsum</a><br/>
 *          <small>a leading zero-sum, which corresponds to initial empty stream, could be optionally excluded</small>
 *     </dd>
 *     <dt>{@link #cumMinBefore(int...)}</dt>
 *     <dd>minimal element before the current one (including itself),
 *          which is similar to applying NumPy's <code>ufunc</code>
 *          <a href="https://numpy.org/doc/2.0/reference/generated/numpy.ufunc.accumulate.html">accumulate</a>
 *          to the function <a href="https://numpy.org/doc/2.0/reference/generated/numpy.minimum.html">numpy.minimum</a>
 *     </dd>
 *     <dt>{@link #cumMaxBefore(int...)}</dt>
 *     <dd>minimal element before the current one (including itself),
 *          which is similar to applying NumPy's <code>ufunc</code>
 *          <a href="https://numpy.org/doc/2.0/reference/generated/numpy.ufunc.accumulate.html">accumulate</a>
 *          to the function <a href="https://numpy.org/doc/2.0/reference/generated/numpy.maximum.html">numpy.maximum</a>
 *     </dd>
 *     <dt>{@link #cumMinAfter(int...)}</dt>
 *     <dd>minimal element after the current one (including itself)</dd>
 *     <dt>{@link #cumMaxAfter(int...)}</dt>
 *     <dd>minimal element after the current one (including itself)</dd>
 * </dl>
 * All methods, which accept {@link Stream} or {@link IntStream} parameters, forces the processing to be sequential,
 * otherwise the resulting cumulative stream will be incorrect and unpredictable.
 */
public class CumulativeUtils {

    // --------------------------------------------------------------------------------------------
    //     "Cumulative Sum" methods that performs forward iteration over input values:
    // --------------------------------------------------------------------------------------------

    public static List<Integer> cumSumList(int... valuesArr) {
        return cumSum(valuesArr).boxed().toList();
    }
    public static List<Integer> cumSumList(List<Integer> valuesList, boolean leadingZero) {
        return cumSum(valuesList, leadingZero).toList();
    }

    public static Stream<Integer> cumSum(List<Integer> valuesList, boolean leadingZero) {
        return cumSum(valuesList.stream(), leadingZero);
    }
    public static Stream<Integer> cumSum(Stream<Integer> boxedStream, boolean leadingZero) {
        return cumSum(boxedStream.mapToInt(Integer::intValue), leadingZero).boxed();
    }
    public static IntStream cumSum(int... valuesArr) {
        return cumSum(Arrays.stream(valuesArr), true);
    }
    public static IntStream cumSum(IntStream values, boolean leadingZero) {
        if (leadingZero) {
            values = IntStream.concat(IntStream.of(0), values);
        }
        return values.sequential().map(sumSoFar());
    }

    // --------------------------------------------------------------------------------------------
    //     "Cumulative Minimum" methods that performs forward iteration over input values:
    // --------------------------------------------------------------------------------------------

    public static List<Integer> cumMinBeforeList(int... valuesArr) {
        return cumMinBefore(valuesArr).boxed().toList();
    }
    public static List<Integer> cumMinBeforeList(List<Integer> valuesList) {
        return cumMinBefore(valuesList).toList();
    }

    public static Stream<Integer> cumMinBefore(List<Integer> valuesList) {
        return cumMinBefore(valuesList.stream());
    }
    public static Stream<Integer> cumMinBefore(Stream<Integer> boxedStream) {
        return cumMinBefore(boxedStream.mapToInt(Integer::intValue)).boxed();
    }
    public static IntStream cumMinBefore(int... valuesArr) {
        return cumMinBefore(Arrays.stream(valuesArr));
    }
    public static IntStream cumMinBefore(IntStream values) {
        return values.sequential().map(minSoFar());
    }

    // --------------------------------------------------------------------------------------------
    //     "Cumulative Maximum" methods that performs forward iteration over input values:
    // --------------------------------------------------------------------------------------------

    public static List<Integer> cumMaxBeforeList(int... valuesArr) {
        return cumMaxBefore(valuesArr).boxed().toList();
    }
    public static List<Integer> cumMaxBeforeList(List<Integer> valuesList) {
        return cumMaxBefore(valuesList).toList();
    }

    public static Stream<Integer> cumMaxBefore(List<Integer> valuesList) {
        return cumMaxBefore(valuesList.stream());
    }
    public static Stream<Integer> cumMaxBefore(Stream<Integer> boxedStream) {
        return cumMaxBefore(boxedStream.mapToInt(Integer::intValue)).boxed();
    }
    public static IntStream cumMaxBefore(int... valuesArr) {
        return cumMaxBefore(Arrays.stream(valuesArr));
    }
    public static IntStream cumMaxBefore(IntStream values) {
        return values.sequential().map(maxSoFar());
    }

    // ===========================================================================================
    //        Utility-methods to create a reverse streams from the given array or list
    // ===========================================================================================

    public static IntStream reversed(int... valuesArr) {
        return IntStream.rangeClosed(1, valuesArr.length).map(i -> valuesArr[valuesArr.length - i]);
    }
    public static IntStream reversed(List<Integer> valuesList) {
        return IntStream.rangeClosed(1, valuesList.size()).map(i -> valuesList.get(valuesList.size() - i));
    }

    public static int[] reversedArr(int... valuesArr) {
        return reversed(valuesArr).toArray();
    }
    public static int[] reversedArr(java.util.stream.IntStream values) {
        return reversedArr(values.toArray());
    }

    public static ArrayList<Integer> reversedList(int... valuesArr) {
        return reversedList(Arrays.stream(valuesArr).boxed());
    }
    public static ArrayList<Integer> reversedList(IntStream values) {
        return reversedList(values.boxed());
    }
    public static <T> ArrayList<T> reversedList(Stream<T> boxedStream) {
        return boxedStream.collect(toListReversed());
    }
    public static <T> ArrayList<T> reversedList(List<T> valuesList) {
        return reversedList(valuesList.stream());
    }

    public static LinkedList<Integer> reversedLinkedList(int... valuesArr) {
        return reversedLinkedList(Arrays.stream(valuesArr).boxed());
    }
    public static LinkedList<Integer> reversedLinkedList(IntStream values) {
        return reversedLinkedList(values.boxed());
    }
    public static <T> LinkedList<T> reversedLinkedList(Stream<T> boxedStream) {
        return boxedStream.collect(toLinkedListReversed());
    }
    public static <T> LinkedList<T> reversedLinkedList(List<T> valuesList) {
        return reversedLinkedList(valuesList.stream());
    }

    // --------------------------------------------------------------------------------------------
    //     "Cumulative Minimum" methods that performs backward iteration over input values:
    // --------------------------------------------------------------------------------------------

    public static ArrayList<Integer> cumMinAfterList(int... valuesArr) {
        return reversedList(cumMinBefore(reversed(valuesArr)));
    }
    public static ArrayList<Integer> cumMinAfterList(List<Integer> valuesList) {
        return reversedList(cumMinBefore(reversed(valuesList)));
    }

    public static Stream<Integer> cumMinAfter(List<Integer> valuesList) {
        return reversedList(cumMinBefore(reversed(valuesList))).stream();
    }
    public static Stream<Integer> cumMinAfter(Stream<Integer> boxedStream) {
        return reversedList(cumMinBefore(reversedList(boxedStream))).stream();
    }
    public static IntStream cumMinAfter(int... valuesArr) {
        return reversedList(cumMinBefore(reversed(valuesArr))).stream().mapToInt(Integer::intValue);
    }
    public static IntStream cumMinAfter(IntStream values) {
        return reversedList(cumMinBefore(reversedList(values))).stream().mapToInt(Integer::intValue);
    }

    // --------------------------------------------------------------------------------------------
    //     "Cumulative Maximum" methods that performs backward iteration over input values:
    // --------------------------------------------------------------------------------------------

    public static ArrayList<Integer> cumMaxAfterList(int... valuesArr) {
        return reversedList(cumMaxBefore(reversed(valuesArr)));
    }
    public static ArrayList<Integer> cumMaxAfterList(List<Integer> valuesList) {
        return reversedList(cumMaxBefore(reversed(valuesList)));
    }

    public static Stream<Integer> cumMaxAfter(List<Integer> valuesList) {
        return reversedList(cumMaxBefore(reversed(valuesList))).stream();
    }
    public static Stream<Integer> cumMaxAfter(Stream<Integer> boxedStream) {
        return reversedList(cumMaxBefore(reversedList(boxedStream))).stream();
    }
    public static IntStream cumMaxAfter(int... valuesArr) {
        return reversedList(cumMaxBefore(reversed(valuesArr))).stream().mapToInt(Integer::intValue);
    }
    public static IntStream cumMaxAfter(IntStream values) {
        return reversedList(cumMaxBefore(reversedList(values))).stream().mapToInt(Integer::intValue);
    }

    // ------------------------------------------------------------------------------------------------------
    //    Factory-methods for Java-Stream mappers that are used as arguments to "Stream.map(...)" methods:
    // ------------------------------------------------------------------------------------------------------

    public static IntUnaryOperator sumSoFar() {
        int[] sum = new int[] { 0 };
        return currentValue -> (sum[0] += currentValue);
    }
    public static IntUnaryOperator minSoFar() {
        Integer[] min = new Integer[] { null };
        return currentValue -> {
            if (min[0] == null || min[0] > currentValue) {
                min[0] = currentValue;
            }
            return min[0];
        };
    }
    public static IntUnaryOperator maxSoFar() {
        Integer[] max = new Integer[] { null };
        return currentValue -> {
            if (max[0] == null || max[0] < currentValue) {
                max[0] = currentValue;
            }
            return max[0];
        };
    }

    // ------------------------------------------------------------------------------------------------------

    public static <T> Collector<T, ?, ArrayList<T>> toListReversed() {
        return Collector.of(
            ArrayList::new,
            (list, value) -> list.add(0, value),
            (left, right) -> {
                left.addAll(0, right);
                return left;
            }
        );
    }
    public static <T> Collector<T, ?, LinkedList<T>> toLinkedListReversed() {
        return Collector.of(
            LinkedList::new,
            LinkedList::addFirst,
            (left, right) -> {
                left.addAll(0, right);
                return left;
            }
        );
    }

    // ------------------------------------------------------------------------------------------------------

    public static String csvAligned(int[] valuesArr) {
        return csvAligned(Arrays.stream(valuesArr).boxed().toList());
    }
    public static <T> String csvAligned(List<T> valuesList) {
        int maxElemLen = valuesList.stream()
            .map(String::valueOf)
            .mapToInt(String::length)
            .max().orElse(1);
        return csvAligned(maxElemLen, valuesList);
    }
    public static String csvAligned(int maxElemLen, int[] valuesArr) {
        return csvAligned(maxElemLen, Arrays.stream(valuesArr).boxed().toList());
    }
    public static <T> String csvAligned(int maxElemLen, List<T> valuesList) {
        String fmt = "%" + maxElemLen + "s";
        return valuesList.stream()
            .map(v -> String.format(fmt, v))
            .collect(Collectors.joining(", "));
    }

    // ------------------------------------------------------------------------------------------------------

    public static <T> Collector<T, ?, String> joiningReversed() {
        return joiningReversed("");
    }

    public static <T> Collector<T, ?, String> joiningReversed(String delimiter) {
        return joiningReversed(delimiter, "", "");
    }

    public static <T> Collector<T, ?, String> joiningReversed(String delimiter, String prefix, String suffix) {
        return Collector.of(
            StringBuilder::new,
            (sb, value) -> {
                if (!sb.isEmpty() && !delimiter.isEmpty()) {
                    sb.insert(0, delimiter);
                }
                sb.insert(0, value);
            },
            (left, right) -> { // the combiner is required for parallel streams
                right.append(delimiter).append(left); return right;
            },
            sb -> sb.insert(0, prefix).append(suffix).toString()
        );
    }

    // ------------------------------------------------------------------------------------------------------
    //   "Cumulative Sum" methods that perform stateless non-efficient collecting (not recommended to use):
    // ------------------------------------------------------------------------------------------------------

    public static Deque<Integer> cumSumDeque(int... valuesArr) {
        return cumSumDeque(Arrays.stream(valuesArr));
    }
    public static Deque<Integer> cumSumDeque(List<Integer> valuesList) {
        return cumSumDeque(valuesList.stream().mapToInt(Integer::intValue));
    }
    public static Deque<Integer> cumSumDeque(IntStream values) {
        // the stream is forced to be sequential, so - the whole approach becomes not efficient
        return values.sequential().collect(
            () -> {  // initial result "supplier"
                Deque<Integer> cumList = new LinkedList<>();
                cumList.addLast(0);
                return cumList;
            },
            (cumList, value) -> {  // next value "accumulator"
                Integer previousSum = cumList.getLast();
                cumList.addLast(previousSum + value);
            },
            Deque::addAll // stateless "combiner", which does not work efficiently in our case
        );
    }

    // ------------------------------------------------------------------------------------------------------

    private CumulativeUtils() {
        // prohibit the creation of utility-class instance
        throw new UnsupportedOperationException("Cannot instantiate a utility-class " + getClass().getName());
    }
}
