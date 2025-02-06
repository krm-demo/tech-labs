package org.krmdemo.techlabs.gfg_lists;

import org.krmdemo.techlabs.gfg_lists.GFG_ListUtils.Node;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/reverse-a-linked-list-in-groups-of-given-size/1">
 *     Linked List Group Reverse
 * </a></h3>
 * Given the <b>head</b> a linked list, the task is to <b>reverse</b> every <b><code>k</code></b> node in the linked list.
 * If the number of nodes is not a multiple of <b><code>k</code></b> then the left-out nodes in the end,
 * should be considered as a group and must be reversed.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ size of linked list ≤ 10^5
 * 1 ≤ data of nodes ≤ 10^6
 * 1 ≤ k ≤ size of linked list
 * </pre>
 */
public interface GFG_Lists__Group_Reverse {

    /**
     * GFG-Solution entry-point
     *
     * @param head the head of linked-list
     * @param k the length of group
     * @return the head of linked-list with each group reversed
     */
    Node reverseKGroup(Node head, int k);

    enum Solution implements GFG_Lists__Group_Reverse {
        DEFAULT;

        @Override
        public Node reverseKGroup(Node head, int k) {
            return null;
        }
    }
}
