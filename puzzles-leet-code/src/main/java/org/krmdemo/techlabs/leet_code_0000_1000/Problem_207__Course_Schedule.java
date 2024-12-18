package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h3><a href="https://leetcode.com/problems/course-schedule/description/?envType=problem-list-v2&envId=topological-sort">
 *     207. Course Schedule
 * </a></h3>
 * There are a total of <b><code>numCourses</code></b> courses you have to take,
 * labeled from <code>0</code> to <code>numCourses - 1</code>.
 * You are given an array <b><code>prerequisites</code></b>
 * where <code>prerequisites[<b>i</b>] = [a<b>i</b>, b<b>i</b>]</code>
 * indicates that you must take course <code>b<b>i</b></code> first if you want to take course <code>a<b>i</b></code>.
 * <hr/>
 * For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code>
 * you have to first take course <code>1</code>.
 * <hr/>
 * Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.
 * <h5>Constraints:</h5><pre>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses</pre>
 * All the pairs <code>prerequisites[i]</code> are unique.
 *
 * @see <a href="https://www.geeksforgeeks.org/topological-sorting/">
 *    (GFG) Topological Sorting
 * </a>
 */
public interface Problem_207__Course_Schedule {

    /**
     * Solution entry-point.
     *
     * @param numCourses total number of courses you have to take
     * @param prerequisites pair of pre-requisite conditions (the first must precede the second)
     * @return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>
     */
    boolean canFinish(int numCourses, int[][] prerequisites);

    enum Solution implements Problem_207__Course_Schedule {
        /**
         * This version of topology-sort uses {@link TreeSet}
         * <hr>
         * one-by-one topological deletion is not efficient (Leet-Code results in 168ms and 5.11% beats)
         */
        TOPOLOGY_SORT_TREE_SET {
            @Override
            public boolean canFinish(int numCourses, int[][] prerequisites) {
                if (numCourses <= 0) {
                    throw new IllegalArgumentException(
                        "number of courses must be positive, but it equals to " + numCourses);
                }
                CoursesGraph coursesGraph = new CoursesGraph(numCourses);
                coursesGraph.addEdgesArr(prerequisites);
                System.out.println("before topological sort:");
                System.out.println(coursesGraph);
                while (coursesGraph.hasFirstToRemove()) {
                    coursesGraph.removeFirst();
                }
                System.out.println("after topological sort:");
                System.out.println(coursesGraph);
                System.out.println("returning " + coursesGraph.isEmpty());
                System.out.println("===================================");
                return coursesGraph.isEmpty();
            }

        },
        /**
         * This version of topology-sort uses counting-map
         * @see org.krmdemo.techlabs.utils.CountingUtils
         */
        TOPOLOGY_SORT_COUNTING_MAP {
            @Override
            public boolean canFinish(int numCourses, int[][] prerequisites) {
                return false; // TODO: to be done
            }
        };

        private static class CoursesGraph {
            private final Node[] nodes;
            private int size = 0;
            CoursesGraph(int numCourses) {
                this.nodes = new Node[numCourses];
            }
            record Node (int id, BitSet before, BitSet after) {
                Node(int id) {
                    this(id, new BitSet(), new BitSet());
                }
                int countBefore() {
                    return before.cardinality();
                }
                boolean hasBefore() {
                    return countBefore() == 0;
                }
                @Override
                public String toString() {
                    return String.format("#%d --> before: %s, after: %s;",
                        this.id(), this.before(), this.after());
                }
            }
            Node node(int id) {
                if (nodes[id] == null) {
                    nodes[id] = new Node(id);
                    size++;
                }
                return nodes[id];
            }
            boolean isEmpty() {
                return size == 0;
            }

            private static final Comparator<Node> topologicalBefore =
                Comparator.comparingInt(Node::countBefore)
                    .thenComparingInt(Node::id);
            private final NavigableSet<Node> topologicalSet =
                new TreeSet<>(topologicalBefore);

            public void addEdge(int[] pair) {
                Node nodeBefore = node(pair[0]);
                Node nodeAfter = node(pair[1]);
                topologicalSet.remove(nodeBefore);
                topologicalSet.remove(nodeAfter);
                nodeBefore.after.set(nodeAfter.id());
                nodeAfter.before.set(nodeBefore.id());
                topologicalSet.add(nodeBefore);
                topologicalSet.add(nodeAfter);
            }
            public void addEdgesArr(int[][] pairsArr) {
                if (pairsArr != null) {
                    Arrays.stream(pairsArr).forEach(this::addEdge);
                }
            }
            public Optional<Node> topologicalFirst() {
                return topologicalSet.isEmpty() ?
                    Optional.empty() :
                    Optional.of(topologicalSet.first());
            }
            public boolean hasFirstToRemove() {
                return topologicalFirst().map(Node::hasBefore).orElse(false);
            }
            public void removeFirst() {
                topologicalFirst().ifPresent(this::remove);
            }

            public void remove(Node nodeToRemove) {
                final int idToRemove = nodeToRemove.id;
                BitSet nodesToUpdate = new BitSet();
                nodeToRemove.before.stream()
                    .filter(id -> id != idToRemove)
                    .mapToObj(this::node)
                    .forEach(nodeBefore -> {
                        topologicalSet.remove(nodeBefore);
                        nodeBefore.after.clear(idToRemove);
                        nodesToUpdate.set(nodeBefore.id);
                    });
                nodeToRemove.after.stream()
                    .filter(id -> id != idToRemove)
                    .mapToObj(this::node)
                    .forEach(nodeAfter -> {
                        topologicalSet.remove(nodeAfter);
                        nodeAfter.before.clear(idToRemove);
                        nodesToUpdate.set(nodeAfter.id);
                    });
                nodesToUpdate.stream()
                    .mapToObj(this::node)
                    .forEach(topologicalSet::add);
                topologicalSet.remove(nodeToRemove);
                nodes[idToRemove] = null;
                size--;
            }

            @Override
            public String toString() {
                return Arrays.stream(nodes)
                    .filter(Objects::nonNull)
                    .map(Node::toString)
                    .collect(Collectors.joining(
                        System.lineSeparator(),
                        String.format("---- %d courses: ----%n", size),
                        String.format("%n.....................%n")
                            + dumpTopology() +
                        String.format("%n---------------------%n")
                    ));
            }
            private String dumpTopology() {
                return topologicalSet.stream()
                    .map(Node::id)
                    .toList().toString();
            }
        }
    }
}
