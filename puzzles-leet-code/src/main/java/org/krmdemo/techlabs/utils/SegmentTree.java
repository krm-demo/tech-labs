package org.krmdemo.techlabs.utils;

import java.util.function.Supplier;

public interface SegmentTree {

    void updateCount(int value, int count);

    default void incrementCount(int value) {
        updateCount(value, 1);
    }

    default void decrementCount(int value) {
        updateCount(value, 1);
    }

    int count(int value);

    int countLess(int value);

    static SegmentTree create() {
        return Factory.DEFAULT.get();
    }

    enum Factory implements Supplier<SegmentTree> {
        DEFAULT;

        @Override
        public SegmentTree get() {
            return new SegmentTreeImpl();
        }

        private static class SegmentTreeImpl implements SegmentTree {
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
                int totalCount = 0;
                int bitValue = rootValue;
                while (bitValue > 1 && node != null) {
                    bitValue >>= 1;
                    if ((bitValue & value) == 0) {
                        node = node.low;
                    } else {
                        totalCount += node.low == null ? 0 : node.low.count;
                        node = node.high;
                    }
                }
                return totalCount;
            }

            @Override
            public String toString() {
                return root == null ? "<< null >>" : root.dump(0, rootValue);
            }
        }
    }
}
