package org.krmdemo.techlabs.gfg_lists;

import org.junit.jupiter.api.Test;
import org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.Node;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.asNodesList;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.cycleInfo;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.fromInts;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.lastNode;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.listSize;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.nodeAfter;
import static org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.toListInt;

/**
 * Test-Case for Geek-For-Geeks utility-class {@link GFG_ListUtils}
 */
public class TestCase_GFG_ListUtils {

    @Test
    public void testNodeToString() {
        assertThat("" + asNodesList(fromInts(3, 4, 5, 6), Node::next))
            .isEqualTo("[Node(3), Node(4), Node(5), Node(6)]");
        assertThat(listSize(fromInts(3, 4, 5, 6))).isEqualTo(4);
    }

    @Test
    public void testNodeCycle() {
        assertThat(cycleInfo(null)).isEmpty();
        assertThat(cycleInfo(new Node(123))).isEmpty();

        Node selfCycle = new Node(345);
        selfCycle.next = selfCycle;
        assertThat(cycleInfo(selfCycle)).isNotEmpty();
        assertThatIllegalStateException().isThrownBy(
            () -> listSize(selfCycle)
        ).withMessage("listSize(Node) - cycle is detected: in linked-list at pos #0, Node(345), length = 0");

        Node sevenNodes = fromInts(1, 2, 3, 4, 5, 6, 777);
        assertThat(toListInt(sevenNodes)).hasSize(7);
        assertThat(sevenNodes.isNotLast()).isTrue();
        assertThat(listSize(sevenNodes)).isEqualTo(7);
        assertThat(lastNode(sevenNodes).data).isEqualTo(777);
        assertThat(lastNode(sevenNodes)).isSameAs(sevenNodes.nodeLast());
        assertThat(lastNode(sevenNodes).isLast()).isTrue();
        assertThat(lastNode(sevenNodes).isNotLast()).isFalse();

        Node nodeAtPos3 = sevenNodes.nodeAfter(3);
        assertThat(nodeAtPos3).isNotNull();
        assertThat(nodeAtPos3.data).isEqualTo(4);
        assertThat(nodeAtPos3.stepsBefore(sevenNodes)).isEqualTo(3);
        assertThat(nodeAtPos3.stepsBefore(nodeAtPos3)).isEqualTo(0);
        assertThat(nodeAtPos3.stepsToEnd()).isEqualTo(3);
        assertThat(listSize(nodeAtPos3)).isEqualTo(4);

        assertThat(lastNode(sevenNodes)).isSameAs(lastNode(nodeAtPos3));
        lastNode(sevenNodes).next = nodeAtPos3; // <-- here is the cycle has just benn introduced!!!
        assertThatIllegalStateException().isThrownBy(
            () -> lastNode(sevenNodes)
        ).withMessage("lastNode(Node) - cycle is detected: in linked-list at pos #3, Node(4), length = 3");

        Node hundredNodes = fromInts(IntStream.rangeClosed(1, 100));
        assertThat(toListInt(hundredNodes)).hasSize(100);
        assertThat(listSize(hundredNodes)).isEqualTo(100);
        assertThat(nodeAfter(hundredNodes, 101)).isEmpty();
        assertThat(nodeAfter(hundredNodes, 100)).isEmpty();
        assertThat(nodeAfter(hundredNodes, 99)).isNotEmpty();
        assertThat(nodeAfter(hundredNodes, 98)).isNotEmpty();

        assertThat(hundredNodes.nodeAfter(99)).isSameAs(hundredNodes.nodeLast());
        hundredNodes.nodeLast().next = hundredNodes;
        assertThat(cycleInfo(hundredNodes)).isNotEmpty();
        assertThatIllegalStateException().isThrownBy(
            () -> toListInt(hundredNodes)
        ).withMessage("asList(Node) - cycle is detected: in linked-list at pos #0, Node(1), length = 99");
        assertThatIllegalArgumentException().isThrownBy(
            () -> hundredNodes.nodeAfter(-1)
        );
    }
}
