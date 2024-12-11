package org.krmdemo.techlabs.gfg_arrays;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/count-smaller-elements2214/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card">
 *     Count Smaller elements
 * </a></h3>
 * Given an array <b><code>arr</code></b> containing non-negative integers.
 * Count and return an array <b><code>ans</code></b> where <code>ans[<b>i</b>]</code> denotes
 * the number of smaller elements <b>on right side</b> of <code>arr[<b>i</b>]</code>.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.size() ≤ 10^6
 * 0 ≤ arr[i] ≤ 10^8
 * </pre>
 *
 * @see org.krmdemo.techlabs.utils.SegmentTree
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_315__Count_Smaller_Numbers
 */
public interface GFG_Arrays__Count_Smaller_Elements {

    /**
     * GFG-Solution entry-point
     *
     * @param arr an array containing non-negative integers
     * @return an array <b><code>ans</code></b> where <code>ans[<b>i</b>]</code> denotes
     *          the number of smaller elements on right side of <code>arr[<b>i</b>]</code>
     */
    int[] constructLowerArray(int[] arr);

    /**
     * Interface to counting segment-tree, which de-couple the logic of current puzzle
     * from concrete implementation of such data-structure.
     */
    interface SegmentTree {
        default void incrementCount(int value) {
            updateCount(value, 1);
        }
        void updateCount(int value, int count);
        int count(int value);  // <-- not used in a current puzzle
        int countLess(int value);
    }

    /**
     * A factory to instantiate the concrete implementation of {@link SegmentTree}
     */
    interface SegmentTreeFactory {
        SegmentTree createSegmentTree(int capacity);
        default SegmentTree createSegmentTree(int[] arr) {
            return createSegmentTree(Arrays.stream(arr).max().orElseThrow());
        }
    }

    enum Solution implements GFG_Arrays__Count_Smaller_Elements, SegmentTreeFactory {
        /**
         * This solution uses the implementation of {@link SegmentTree} that is based on segment-array.
         *
         * @see <a href="https://en.wikipedia.org/wiki/Segment_tree">(wiki) Segment tree</a>
         * @see <a href="https://www.geeksforgeeks.org/segment-tree-data-structure/">
         *     (GFG) Segment Tree
         * </a>
         */
        SEGMENT_TREE_ARR {
            @Override
            public SegmentTree createSegmentTree(int capacity) {
                return new SegmentTreeArray(capacity);
            }

            /**
             * This implementation of {@link SegmentTree} is based on segment-tree array without offset (like at
             * {@link org.krmdemo.techlabs.leet_code_0000_1000.Problem_315__Count_Smaller_Numbers.Solution#SEGMENT_TREE_ARR
             * corresponding Leet-Code puzzle}), but for values in range <code>[0, maxValue]</code>.
             * <hr/>
             * Unfortunately some test-cases (1109/1115) at GFG are failed with {@link OutOfMemoryError}
             */
            private static class SegmentTreeArray implements SegmentTree{

                private final int capacity;
                private final int[] segmentArr;

                SegmentTreeArray(int capacity) {
                    capacity = Integer.highestOneBit(capacity << 1);
                    int segemntSize = capacity << 1;
                    this.segmentArr = new int[segemntSize];
                    this.capacity = capacity;
                }

                public void incrementCount(int value) {
                    updateCount(value, 1);
                }

                public void updateCount(int value, int count) {
                    int index = checkIndex(value);
                    while (index > 0) {
                        segmentArr[index] += count;
                        index = index >> 1;
                    }
                }

                public int count(int value) {
                    int index = checkIndex(value);
                    return segmentArr[index];
                }

                public int countLess(int value) {
                    int totalCountLess = 0;
                    int index = checkIndex(value);
                    while (index > 0) {
                        int parent = index >> 1;
                        if ((index & 1) == 1) {
                            int countLess = segmentArr[parent << 1];
                            totalCountLess += countLess;
                        }
                        index = parent;
                    }
                    return totalCountLess;
                }

                private int checkIndex(int value) {
                    if (value >= this.capacity) {
                        throw new IllegalArgumentException(String.format(
                            "value must be non-negative and less than %d, but it equals to %d",
                            this.capacity, value));
                    }
                    return value + this.capacity;
                }

                @Override
                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%s ( capacity = %d ):%n",
                        getClass().getSimpleName(), this.capacity));
                    int maxLevel = Integer.numberOfTrailingZeros(this.capacity) + 1;
                    for (int level = 1; level <= maxLevel; level++) {
                        int start = 1 << (level - 1);
                        int end = start << 1;
                        List<Integer> levelList = Arrays.stream(segmentArr, start, end).boxed().toList();
                        sb.append(String.format("%2d :: %s %n", level, levelList));
                    }
                    return sb.toString();
                }
            }
        },
        /**
         * This solution uses the implementation of {@link SegmentTree} that is based on binary-tree.
         */
        BINARY_SEGMENT_TREE {
            private SegmentTree createSegmentTree() {
                return new SegmentTreeImpl();
            }
            @Override
            public SegmentTree createSegmentTree(int capacity) {
                return createSegmentTree();
            }
            @Override
            public SegmentTree createSegmentTree(int[] arr) {
                return createSegmentTree();
            }

            /**
             * Implementation of {@link SegmentTree} that is based on binary segment-tree
             */
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
        };

        @Override
        public int[] constructLowerArray(int[] arr) {
            System.out.printf("%s.constructLowerArray ( %s )%n",
                name(), Arrays.toString(arr));
            int[] ans = new int[arr.length];
            SegmentTree segmentTree = createSegmentTree(arr);
            for (int i = arr.length - 1; i >= 0; i--) {
                int value = arr[i];
                segmentTree.incrementCount(value);
                ans[i] = segmentTree.countLess(value);
            }
            System.out.println(segmentTree);
            System.out.println("ans --> " + Arrays.toString(ans));
            System.out.println("-------------------------------");
            System.out.println();
            return ans;
        }
    }
}
