package org.krmdemo.techlabs.utils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * An interface to counting segment-tree, which allows to count the values
 * within any contiguous range in logarithmic time (unlike square complexity
 * of a brute-force approach that does not require additional space though)
 */
public interface SegmentTree {

    /**
     * Updating the counter of a given value
     * @param value the value whose counter to update
     * @param count the count to add to existing counter (initial zero is implied)
     */
    void updateCount(int value, int count);

    /**
     * Incrementing the counter of a given value by one
     * @param value the value whose counter to increment
     */
    default void incrementCount(int value) {
        updateCount(value, 1);
    }

    /**
     * Decrementing the counter of a given value by one
     * @param value the value whose counter to decrement
     */
    default void decrementCount(int value) {
        updateCount(value, -1);
    }

    /**
     * Return the counter of a given value
     * <hr/>
     * (the same as {@link CountingUtils#countingMap(Stream) counting-map} does)
     *
     * @param value the value whose counter to return
     * @return the counter of a given value (or zero if a value never was {@link #updateCount(int, int)} updated
     */
    int count(int value);

    /**
     * Return the sum of counters of all values that are less than the given one
     *
     * @param value the ceil-value of counters to summarize
     * @return the sum of counters of all values that are less than the given one
     */
    int countLess(int value);

    /**
     * @return minimal value who counter was ever {@link #updateCount(int, int) updated}
     */
    Integer firstValue();

    /**
     * @return maximal value who counter was ever {@link #updateCount(int, int) updated}
     */
    Integer lastValue();

    /**
     * @return an instance of a binary-tree implementation of {@link SegmentTree}
     */
    static SegmentTree createTree() {
        return Factories.DEFAULT.get();
    }

    /**
     * @return an instance of a segment-array implementation of {@link SegmentTree}
     */
    static SegmentTree createArray() {
        return Factories.ARRAY.create();
    }

    /**
     * @param maxValue maximum capacity (exclusive) of a segment-array
     * @return an instance of a segment-array implementation of {@link SegmentTree}
     */
    static SegmentTree createArray(int maxValue) {
        return Factories.ARRAY.create(maxValue);
    }

    interface Factory extends Supplier<SegmentTree> {
        SegmentTree create();
        default SegmentTree create(int maxValue) {
            return create();
        }
        @Override
        default SegmentTree get() {
            return create();
        }
    }

    enum Factories implements Factory {
        /**
         * A factory to instantiate a binary-tree implementation of {@link SegmentTree}
         */
        DEFAULT {
            @Override
            public SegmentTree create() {
                return new SegmentTreeImpl();
            }
        },
        /**
         * A factory to instantiate a segment-array implementation of {@link SegmentTree}
         */
        ARRAY {
            public static final int DEFAULT_MAX_VALUE = 10 << 1;
            @Override
            public SegmentTree create() {
                return create(DEFAULT_MAX_VALUE);
            }
            @Override
            public SegmentTree create(int maxValue) {
                return new SegmentArray(maxValue);
            }
        };

        private static class MinMaxHolder {
            private Integer firstValue = null;
            private Integer lastValue = null;

            void updateMinMax(int value) {
                firstValue = firstValue == null ? value : Math.min(firstValue, value);
                lastValue = lastValue == null ? value : Math.max(lastValue, value);
            }

            public Integer firstValue() {
                return this.firstValue;
            }
            public Integer lastValue() {
                return this.lastValue;
            }
        }

        /**
         * Implementation of {@link SegmentTree} that is based on binary segment-tree
         */
        private static class SegmentTreeImpl extends MinMaxHolder implements SegmentTree {
            static class Node {
                int count = 0;
                Node high = null;
                Node low = null;

                Node lazyHigh() {
                    if (high == null) {
                        high = new Node();
                    }
                    return high;
                }

                Node lazyLow() {
                    if (low == null) {
                        low = new Node();
                    }
                    return low;
                }

                Node newParent() {
                    Node parent = new Node();
                    parent.low = this;
                    parent.count = this.count;
                    return parent;
                }

                String dump(int lowValue, int highValue) {
                    StringBuilder sb = new StringBuilder();
                    if (lowValue == highValue - 1) {
                        sb.append(String.format("{%d} :: %d %n",
                            lowValue, count));
                    } else {
                        sb.append(String.format("[ %d, %d ) :: %d %n",
                            lowValue, highValue, count));
                    }
                    int midValue = (lowValue + highValue) / 2;
                    if (high != null) {
                        sb.append("-high:\n");
                        sb.append(high.dump(midValue, highValue).indent(2));
                    }
                    if (low != null) {
                        sb.append("-low:\n");
                        sb.append(low.dump(lowValue, midValue).indent(2));
                    }
                    return sb.toString();
                }
            }

            private int rootValue = 0;
            private Node root = null;

            Node rootNode(int value) {
                if (root == null) {
                    root = new Node();
                    rootValue = 2;  // the same as "1 << 1"
                }
                int highBitValue = Integer.highestOneBit(value);
                if (rootValue > highBitValue) {
                    return root;
                }
                Node newRoot = root.newParent();
                for (int newRootValue = rootValue;
                     newRootValue < highBitValue;
                     newRootValue <<= 1)
                {
                    newRoot = newRoot.newParent();
                }
                root = newRoot;
                rootValue = highBitValue << 1;
                return root;
            }

            @Override
            public void updateCount(int value, int count) {
                if (value < 0) {
                    throw new IllegalArgumentException(
                        "value must NOT be negative: " + value);
                }
                updateMinMax(value);
                Node node = rootNode(value);
                node.count += count;
                int bitValue = rootValue;
                while (bitValue > 1) {
                    bitValue >>= 1;
                    if ((bitValue & value) == 0) {
                        node = node.lazyLow();
                    } else {
                        node = node.lazyHigh();
                    }
                    node.count += count;
                }
            }

            @Override
            public int count(int value) {
                if (value >= rootValue) {
                    return 0;
                }
                Node node = root;
                int bitValue = rootValue;
                while (bitValue > 1 && node != null) {
                    bitValue >>= 1;
                    if ((bitValue & value) == 0) {
                        node = node.low;
                    } else {
                        node = node.high;
                    }
                }
                return node != null ? node.count : 0;
            }

            @Override
            public int countLess(int value) {
                if (value >= rootValue) {
                    return root.count;
                }
                Node node = root;
                int totalCountLess = 0;
                int bitValue = rootValue;
                while (bitValue > 1 && node != null) {
                    bitValue >>= 1;
                    if ((bitValue & value) == 0) {
                        node = node.low;
                    } else {
                        totalCountLess += node.low == null ? 0 : node.low.count;
                        node = node.high;
                    }
                }
                return totalCountLess;
            }

            @Override
            public String toString() {
                return root == null ? "<< null >>" : root.dump(0, rootValue);
            }
        }

        /**
         * Implementation of {@link SegmentTree} that is based on segment-array
         */
        private static class SegmentArray extends MinMaxHolder implements SegmentTree {

            private final int rootValue;
            private final int[] countArr;

            SegmentArray(int maxValue) {
                if (maxValue <= 0) {
                    throw new IllegalArgumentException(
                        "maxValue must be positive: " + maxValue);
                }
                maxValue--;
                rootValue = Integer.highestOneBit(maxValue << 1) << 1;
                countArr = new int[rootValue];
            }

            @Override
            public void updateCount(int value, int count) {
                int index = checkIndex(value);
                updateMinMax(value);
                while (index > 0) {
                    countArr[index] += count;
                    index = index >> 1;
                }
            }

            @Override
            public int count(int value) {
                int index = checkIndex(value);
                return countArr[index];
            }

            @Override
            public int countLess(int value) {
                int totalCountLess = 0;
                int index = checkIndex(value);
                while (index > 0) {
                    int parent = index >> 1;
                    if ((index & 1) == 1) {
                        int countLess = countArr[parent << 1];
                        totalCountLess += countLess;
                    }
                    index = parent;
                }
                return totalCountLess;
            }

            private int checkIndex(int value) {
                if (value < 0) {
                    throw new IllegalArgumentException(
                        "value must NOT be negative: " + value);
                }
                int index = value + (rootValue >> 1);
                if (index >= rootValue) {
                    throw new IllegalArgumentException(String.format(
                        "value must be less than %d, but it equals to %d",
                        rootValue >> 1, value));
                }
                return index;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                for (int level = 1; level <= Integer.numberOfTrailingZeros(rootValue); level++) {
                    int start = 1 << (level - 1);
                    int end = start << 1;
                    List<Integer> levelList = Arrays.stream(countArr, start, end).boxed().toList();
                    sb.append(String.format("%2d :: %s %n", level, levelList));
                }
                return sb.toString();
            }
        }
    }
}
