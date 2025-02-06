package org.krmdemo.techlabs.gfg_lists;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * This utility-class contains declaration of {@link Node linked-list node},
 * which is used as an argument and return-value type in multiple GFG-puzzles,
 * that are related to linked-list
 */
public class GFG_ListUtils {

    /**
     * ... provided by GFG ...
     */
    public static class Node {
        public int data;
        public Node next;
        public Node(int key) {
            this.data = key;
            this.next = null;
        }
        @Override public String toString() {
            return String.format("%s(%d)", this.getClass().getSimpleName(), this.data);
        }
        public Node next() {
            return this.next;
        }
        public int stepsBefore(Node nodeStart) {
            return stepsAfter(nodeStart, this, Node::next);
        }
        public Node nodeAfter(int steps) {
            return GFG_ListUtils.nodeAfter(this, steps).orElse(null);
        }
        public Node nodeLast() {
            return GFG_ListUtils.lastNode(this);
        }
        public int stepsToEnd() {
            return listSize(this) - 1;
        }
        public boolean isLast() {
            return this.next == null;
        }
        public boolean isNotLast() {
            return this.next != null;
        }
    }

    public static int stepsAfter(Node nodeStart, Node nodeToFind) {
        return stepsAfter(nodeStart, nodeToFind, Node::next);
    }
    public static <T> int stepsAfter(T nodeStart, T nodeToFind, UnaryOperator<T> next) {
        if (nodeStart == null || nodeStart == nodeToFind) {
            return nodeStart == nodeToFind ? 0 : -1;
        } else {
            return 1 + stepsAfter(next.apply(nodeStart), nodeToFind, next);
        }
    }

    public static Optional<Node> nodeAfter(Node nodeStart, int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException(String.format(
                "nodeAfter(..., steps = %d) - steps must not be negative", steps));
        }
        return steps == 0 || nodeStart == null
            ? Optional.ofNullable(nodeStart)
            : nodeAfter(nodeStart.next, steps - 1);
    }

    public static class CycleInfo<T> {
        int pos;
        int len;
        T startNode;
        @Override public String toString() {
            return String.format("in linked-list at pos #%d, %s, length = %d",
                this.pos, startNode, this.len);
        }
    }

    public static Optional<CycleInfo<Node>> cycleInfo(Node head) {
        return cycleInfo(head, Node::next);
    }

    public static <T> Optional<CycleInfo<T>> cycleInfo(T head, UnaryOperator<T> next) {
        // Initialize tortoise and hare pointers
        T slow = head;
        T fast = head;
        // Move tortoise one step and hare two steps
        while (fast != null && next.apply(fast) != null) {
            slow = next.apply(slow);
            fast = next.apply(next.apply(fast));
            // Check if the hare meets the tortoise
            if (slow == fast) {
                break;
            }
        }
        // Check if there is no cycle
        if (fast == null || next.apply(fast) == null) {
            return Optional.empty();
        }

        CycleInfo<T> info = new CycleInfo<>();
        info.pos = 0;
        fast = head;
        while (slow != fast) {
            slow = next.apply(slow);
            fast = next.apply(fast);
            info.pos++;
        }
        info.startNode = slow;
        info.len = stepsAfter(next.apply(slow), slow, next);
        return Optional.of(info);
    }

    public static void assertCycle(Node head, String errMsgPrefix) {
        assertCycle(head, Node::next, errMsgPrefix);
    }
    public static <T> void assertCycle(T head, UnaryOperator<T> next, String errMsgPrefix) {
        cycleInfo(head, next).ifPresent(info -> {
            throw new IllegalStateException(errMsgPrefix + info);
        });
    }

    public static int listSize(Node head) {
        assertCycle(head, "listSize(Node) - cycle is detected: ");
        return stepsAfter(head, null);
    }

    public static Node lastNode(Node head) {
        assertCycle(head, "lastNode(Node) - cycle is detected: ");
        return head == null || head.next == null ? head : lastNode(head.next);
    }

    public static <T> List<T> asNodesList(T head, UnaryOperator<T> next) {
        assertCycle(head, next, "asList(Node) - cycle is detected: ");
        List<T> nodesAsList = new ArrayList<>();
        for (T node = head; node != null; node = next.apply(node)) {
            nodesAsList.add(node);
        }
        return nodesAsList;
    }
    public static List<Integer> toListInt(Node head) {
        return asNodesList(head, Node::next).stream().map(node -> node.data).toList();
    }
    public static Node fromInts(IntStream streamInt) {
        return fromInts(streamInt.boxed().collect(toList()));
    }
    public static Node fromInts(int... arrInt) {
        return fromInts(stream(arrInt).boxed().collect(toList()));
    }
    public static Node fromInts(List<Integer> listInt) {
        return fromInts(listInt, Node::new, (prev, next) -> prev.next = next);
    }

    public static <T> T fromInts(List<Integer> listInt,
                                 Function<Integer, T> nodeFactory,
                                 BiConsumer<T, T> nodeCnnector) {
        if (listInt == null || listInt.isEmpty()) {
            return null;
        }
        T head = null;
        T prev = null;
        for (int val : listInt) {
            T current = nodeFactory.apply(val);
            if (head == null) {
                head = current;
            }
            if (prev != null) {
                nodeCnnector.accept(prev, current);
            }
            prev = current;
        }
        return head;
    }
}
