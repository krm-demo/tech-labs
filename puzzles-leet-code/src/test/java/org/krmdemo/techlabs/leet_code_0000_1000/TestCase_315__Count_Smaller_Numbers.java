package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.RandomHelper;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_315__Count_Smaller_Numbers}
 */
public class TestCase_315__Count_Smaller_Numbers {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { 5,2,6,1 })).isEqualTo(List.of( 2, 1, 1, 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1 })).isEqualTo(List.of( 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_03(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1, -1 })).isEqualTo(List.of( 0, 0 ));
    }

    @EnumSource
    @ParameterizedTest
    void test_dups_middle(Problem_315__Count_Smaller_Numbers.Solution sln) {
        assertThat(sln.countSmaller(new int[] { -1, -2, -3, 0, 0, 0, 1, 2, 3 }))
            .isEqualTo(List.of( 2, 1, 0, 0, 0, 0, 0, 0, 0 ));
        assertThat(sln.countSmaller(new int[] { 1, 2, 3, 0, 0, 0, -1, -2, -3 }))
            .isEqualTo(List.of( 6, 6, 6, 3, 3, 3, 2, 1, 0 ));
    }

    @Test
    void test_Fenwick_Index() {
        for (int i = 5; i < 10_000; i = i * 5) {
            System.out.printf("%,5d :: ", i);
            int index = i;
            for (int j = 0; j < 10; j++) {
                if (j > 0) System.out.print(", ");
                index += index & -index;
                System.out.print(index);
            }
            System.out.println(";");
        }
        for (int N = 130; N < 140; N ++) {
            System.out.printf("... starting %d ...%n", N);
            for (int x = (N + 1); x < 256; x += x & -x) {
                System.out.printf("%,5d :: %9s %n", x, Integer.toBinaryString(x));
            }
        }
    }

    // --------------------------------------------------------------------------------------------

    static class SegmentTree {
        class SegmentTreeNode {
            int count = 0;
            SegmentTreeNode highNode = null;
            SegmentTreeNode lowNode = null;
            SegmentTreeNode high() {
                if (highNode == null) {
                    highNode = new SegmentTreeNode();
                }
                return highNode;
            }
            SegmentTreeNode low() {
                if (lowNode == null) {
                    lowNode = new SegmentTreeNode();
                }
                return lowNode;
            }
        }
        private SegmentTreeNode root = new SegmentTreeNode();
        private final int bitLength;
        SegmentTree(int bitLength) {
            this.bitLength = bitLength;
        }
        public void incrementCount(int value) {
            int maxValue = checkMaxValue(value);
            SegmentTreeNode node = root;
            node.count++;
            while (maxValue > 1) {
                maxValue = maxValue >> 1;
                if ((value & maxValue) == 0) {
                    node = node.low();
                } else {
                    node = node.high();
                }
                node.count++;
            }
        }
        public int count(int value) {
            int maxValue = checkMaxValue(value);
            SegmentTreeNode node = root;
            while (node != null && maxValue > 1) {
                maxValue = maxValue >> 1;
                if ((value & maxValue) == 0) {
                    node = node.lowNode;
                } else {
                    node = node.highNode;
                }
            }
            return node != null ? node.count : 0;
        }
        public int countLess(int value) {
            int totalCount = 0;
            int maxValue = checkMaxValue(value);
            SegmentTreeNode node = root;
            while (node != null && maxValue > 1) {
                maxValue = maxValue >> 1;
                if ((value & maxValue) == 0) {
                    node = node.lowNode;
                } else {
                    totalCount += node.lowNode == null ? 0 : node.lowNode.count;
                    node = node.highNode;
                }
            }
            return totalCount;
        }
        private int checkMaxValue(int value) {
            if (value < 0) {
                throw new IndexOutOfBoundsException(
                    String.format("the value %d must be non-negative", value));
            }
            int maxValue = 1 << bitLength;
            if (value >= maxValue) {
                throw new IndexOutOfBoundsException(
                    String.format("the value %d must be less than %d", value, maxValue));
            }
            return maxValue;
        }
        @Override public String toString() {
            return dump(root, 0, 1 << bitLength);
        }
        private String dump(SegmentTreeNode node, int low, int high) {
            StringBuilder sb = new StringBuilder();
            if (low == high - 1) {
                sb.append(String.format("%d :: { %d }%n", node.count, low));
            } else {
                sb.append(String.format("%d :: [ %d, %d )%n", node.count, low, high));
            }
            int mid = (high + low) / 2;
            if (node.highNode != null) {
                sb.append("high:\n");
                sb.append(dump(node.highNode, mid, high).indent(2));
            }
            if (node.lowNode != null) {
                sb.append("low:\n");
                sb.append(dump(node.lowNode, low, mid).indent(2));
            }
            return sb.toString();
        }
    }

    @Test
    void testSegementTree() {
        SegmentTree st = new SegmentTree(5);
//        IntStream.range(0, 10).map(i -> i * 3).forEach(st::incrementCount);
        st.incrementCount(0);
        st.incrementCount(1);
//        st.incrementCount(2);
//        st.incrementCount(3);
//        st.incrementCount(4);
        System.out.println(st);
        for (int i = 0; i < 32; i++) {
            System.out.printf("%2d) st.count(%d) = %d; st.countLess(%d) = %d;%n",
                i, i, st.count(i), i, st.countLess(i));
        }
//        st.incrementCount(12);
    }

    @Test
    void testHighLowBits() {
        for (int i = 0; i <=32; i++) {
            System.out.printf("%2d) high = %2d; low = %2d; bitCount = %d; '%8s'%n",
                i, Integer.highestOneBit(i), Integer.lowestOneBit(i),
                Integer.bitCount(i), Integer.toBinaryString(i));
        }
    }
}
